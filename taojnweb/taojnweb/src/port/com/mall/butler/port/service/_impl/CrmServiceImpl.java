package com.mall.butler.port.service._impl;

import java.rmi.RemoteException;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.account.dao.TradeAccountDao;
import com.mall.butler.account.m.service._impl.MAgentServiceImpl;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.point.m.model.AccountPointInfo;
import com.mall.butler.point.model.PointChangeDetal;
import com.mall.butler.port.service.CrmService;
import com.mall.butler.port.test.Des3Test;
import com.mall.butler.port.test.RsaUtilTest;
import com.mall.butler.port.xml.OutputParameter;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.sys.dao.SysTextLibraryDao;
import com.mall.butler.sys.model.SysTextLibrary;
import com.mall.butler.util.XmlHelper;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.lang.DateUtil;

import sun.misc.BASE64Decoder;
import crmWebService.IHsCRMWebSrv;
import crmWebService.IHsCRMWebSrvserviceLocator;
import crmWebService.TReturnInfo;

public class CrmServiceImpl extends BaseServiceImpl implements CrmService {
	private static Logger logger = Logger.getLogger(CrmServiceImpl.class);
	@Autowired
	private SysTextLibraryDao sysTextLibraryDao;
//	private static final String clientCode = "9009_0007";
//	private static final String orgCode = "9009";
//	private static final String verifyCode = "ABBC";
//	private static final String userCode = "211";
//	private static final String cardTypeCode = "szy8";
	private static final String clientCode = "001_0001";
	private static final String orgCode = "001";
	private static final String verifyCode = "AABC";
	//private static final String verifyCode = "ABBC";
	private static final String userCode = "9998";
	private static final String cardTypeCode = "90";
	private static final String password = "hisense1";

	@Override
	public String getKey() {
		IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		IHsCRMWebSrv da;
		try {
			da = lo.getIHsCRMWebSrvPort();
			String Random = "00"
					+ DateUtil.format(new Date(), "yyyyMMddHHmmss");
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<InputParameter>");
			sb.append("<Random>" + Random + "</Random>");
			sb.append("<ClientCode>" + clientCode + "</ClientCode>");
			sb.append("</InputParameter>");
			//System.out.println(sb.toString());
			TReturnInfo rst;
			try {
				rst = da.IWsPosCommOperate(8, sb.toString());
				if (null == rst||0 != rst.getReturnCode())
					return null;
				// 保存工作秘钥
				OutputParameter outputParameter = XmlHelper.toObj(
						OutputParameter.class, getFromBASE64(rst
								.getOutputPara()));
				SysTextLibrary textkeye = sysTextLibraryDao
						.getById(ManageContext.CRM_KEYE);
				textkeye.setContext(outputParameter.getKEYE());
				sysTextLibraryDao.update(textkeye);
				SysTextLibrary textkeyn = sysTextLibraryDao
						.getById(ManageContext.CRM_KEYN);
				textkeyn.setContext(outputParameter.getKEYN());
				sysTextLibraryDao.update(textkeyn);
				return getFromBASE64(rst.getRtnMsg());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String crmLogin(String key) {
		IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		IHsCRMWebSrv da;
		 String e="65537";
		   SysTextLibrary keyn = sysTextLibraryDao
			.getById(ManageContext.CRM_KEYN);
//		   String n="20963770985082037258267013644832680819194444170865674109439404719885226241714481879640783348297997948427404211373894962551236372533411432647622255530836353218272251916811821629043426707565400351131213303148172115928391315808665241847484993407632783520603053639514468062034674617971413297279493510927957440275568732809417989268857024667945756452767120447211373511007441264575103012675179769758922918966654874533285783639668932301859385955501408660891072760643889895703821362288397513136466372572877510678561083413134328083977102081885958523282887072436899591195972865308007615085235131433633410539409896249248231311437";
		   String n=keyn.getContext();
		try {
			da = lo.getIHsCRMWebSrvPort();
			String Random = "00"
					+ DateUtil.format(new Date(), "yyyyMMddHHmmss");
			  String sb = new String();
				sb=sb+"<InputParameter>";
				sb=sb+"<Random>"+Random+"</Random>";
				sb=sb+"<ClientCode>"+clientCode+"</ClientCode>";
				sb=sb+"<WorkKey>"+Random+"</WorkKey>";
				sb=sb+"<UserCode>"+userCode+"</UserCode>";
				sb=sb+"<Passwd>"+password+"</Passwd>";
				sb=sb+"<VerifyInfo>"+verifyCode+"</VerifyInfo>";
				sb=sb+"</InputParameter>";
			  TReturnInfo rst = da.IWsPosCommOperate(12,RsaUtilTest.encrypt(sb.toString(), e, n));
			if (null == rst||0 != rst.getReturnCode())
				return null;
			// 保存workguID
			SysTextLibrary workGuid = sysTextLibraryDao
					.getById(ManageContext.CRM_WORKGUID);
			String doudString=Des3Test.decrypt(rst.getOutputPara(), Random);
			String [] aStrings=doudString.split("<WorkGuid>");
			String [] bStrings=aStrings[1].split("</WorkGuid>");
			workGuid.setContext(bStrings[0]);
			sysTextLibraryDao.update(workGuid);
			//保存随机数
			SysTextLibrary workkey = sysTextLibraryDao
			.getById(ManageContext.CRM_WORKKEYID);
			workkey.setContext(Random);
			sysTextLibraryDao.update(workkey);
			return getFromBASE64(rst.getRtnMsg());
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return null;
	}

	@Override
	public String loginOut(String loginout) {
		IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		IHsCRMWebSrv da;
		try {
			SysTextLibrary workKey = sysTextLibraryDao
			.getById(ManageContext.CRM_WORKKEYID);
			SysTextLibrary workGuid = sysTextLibraryDao
			.getById(ManageContext.CRM_WORKGUID);
			da = lo.getIHsCRMWebSrvPort();
			String stringbBody = "";
			da = lo.getIHsCRMWebSrvPort();
			String Random = "00"
					+ DateUtil.format(new Date(), "yyyyMMddHHmmss");
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<InputParameter>");
			sb.append("<Head>");
			sb.append("<ClientCode>"+clientCode+"</ClientCode>");
			sb.append("</Head>");
			sb.append("<Body>");
			stringbBody = stringbBody
					+ "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			stringbBody = stringbBody + "<InputParameter>";
			stringbBody = stringbBody + "<Random>" + Random + "</Random>";
			stringbBody = stringbBody
					+ "<WorkGuid>"+workGuid.getContext()+"</WorkGuid>";
			stringbBody = stringbBody + "</InputParameter>";
			sb.append(Des3Test.encrypt(stringbBody, workKey.getContext()));
			sb.append("</Body>");
			sb.append("</InputParameter>");
			TReturnInfo rst;
			rst = da.IWsPosCommOperate(2, sb.toString());
			if(null==rst||0!=rst.getReturnCode())
				return null;
			return getFromBASE64(rst.getRtnMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String doRegAccount(AccountLogin accountLogin, String reg) {
		IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		IHsCRMWebSrv da;
		try {
			SysTextLibrary workGuid = sysTextLibraryDao
			.getById(ManageContext.CRM_WORKGUID);
			SysTextLibrary workKey = sysTextLibraryDao
			.getById(ManageContext.CRM_WORKKEYID);
			// 注册完成后保存会员卡号用生日做字段
			String Random = "00"
					+ DateUtil.format(new Date(), "yyyyMMddHHmmss");
			da = lo.getIHsCRMWebSrvPort();
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<InputParameter>");
			sb.append("<Random>" + Random + "</Random>");
			sb.append("<WorkGuid>" + workGuid.getContext() + "</WorkGuid>");
			//sb.append("<AppID></AppID>");
			sb.append("<YwType>4</YwType>");
			sb.append("<Mobile>" + accountLogin.getMobile() + "</Mobile>");
			//sb.append("<Mailaddr></Mailaddr>");
			sb.append("<OrgCode>"+orgCode+"</OrgCode>");
			sb.append("<CertNo>" + accountLogin.getIdCard() + "</CertNo>");
			sb.append("<Password>123456</Password>");
			sb.append("<Gender>0</Gender>");
			sb.append("<UserCode>"+userCode+"</UserCode>");
			sb.append("<VipName>" + accountLogin.getRealname() + "</VipName>");
			//sb.append("<DepCode></DepCode>");
			sb.append("<CardTypeCode>"+cardTypeCode+"</CardTypeCode>");
			sb
			.append("<OldBillNo>" + Random
							+ "</OldBillNo>");
			sb.append("</InputParameter>");
			String body =Des3Test.encrypt(sb.toString().trim(), workKey.getContext()) ;
			String tag = TxtUtil.digest(body+verifyCode).toUpperCase();
			
			StringBuilder request = new StringBuilder();
			request.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			request.append("<InputParameter>");
			request.append("<Head>");
			request.append("<Tag>" + tag + "</Tag>");
			request.append("<ClientCode>"+clientCode+"</ClientCode>");
			request.append("</Head>");
			request.append("<Body>");
			request.append(body);
			request.append("</Body>");
			request.append("</InputParameter>");
			TReturnInfo rst;
				rst = da.IWsPosCommOperate(122, request.toString());
				if(null==rst||0!=rst.getReturnCode()){
					logger.error("失败编号==========================="+rst.getReturnCode()+"失败原因"+getFromBASE64(rst.getRtnMsg()));
					System.out.println("失败编号==========================="+rst.getReturnCode()+"失败原因"+getFromBASE64(rst.getRtnMsg()));					
					return null;
				}
				
				return Des3Test.decrypt(rst.getOutputPara(),workKey.getContext());
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public String updateAccount(AccountLogin accountLogin, String workGuid) {
		IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		IHsCRMWebSrv da;
		try {
			// 注册完成后保存会员卡号用生日做字段
			String Random = "00"
					+ DateUtil.format(new Date(), "yyyyMMddHHmmss");
			da = lo.getIHsCRMWebSrvPort();
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<InputParameter>");
			sb.append("<RandomNo>" + Random + "</RandomNo>");
			sb.append("<WorkKey>" + workGuid + "</WorkKey>");
			sb.append("<OrgCode>" + accountLogin.getId() + "</OrgCode>");
			sb.append("<POSNo></POSNo>");
			sb.append("<CardNoType>3</CardNoType>");
			sb.append("<CardNo>" + accountLogin.getBirthday() + "</CardNo>");
			sb.append("<UserCode>" + userCode+ "</UserCode>");
			sb.append("<VIPName>" + accountLogin.getRealname() + "</VIPName>");
			sb.append("<Birthday></Birthday>");
			sb.append("<CertType></CertType>");
			sb.append("<CertNo>" + accountLogin.getIdCard() + "</CertNo>");
			sb.append("</InputParameter>");
			String body = sb.toString();
			String tag = TxtUtil.digest(body + clientCode);

			StringBuilder request = new StringBuilder();
			request.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			request.append("<InputParameter>");
			request.append("<Head>");
			request.append("<Tag>" + tag + "</Tag>");
			request.append("<ClientCode>" + clientCode + "</ClientCode>");
			request.append("</Head>");
			request.append("<Body>");
			request.append(body);
			request.append("</Body>");
			request.append("</InputParameter>");
			System.out.println(request.toString());
			TReturnInfo rst;
			try {
				rst = da.IWsPosCommOperate(105, request.toString());
				return getFromBASE64(rst.getRtnMsg());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String queryAccount(AccountLogin accountLogin, String rew) {
		IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		IHsCRMWebSrv da;
		try {
			SysTextLibrary workGuid = sysTextLibraryDao
			.getById(ManageContext.CRM_WORKGUID);
			SysTextLibrary workKey = sysTextLibraryDao
			.getById(ManageContext.CRM_WORKKEYID);
			// 注册完成后保存会员卡号用生日做字段
//			String Random = "00"
//					+ DateUtil.format(new Date(), "yyyyMMddHHmmss");
			da = lo.getIHsCRMWebSrvPort();
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<InputParameter>");
			sb.append("<CardNoType>2</CardNoType>");
			sb.append("<CardNo>" + accountLogin.getIdCard()+ "</CardNo>");
			sb.append("<WorkGuid>" + workGuid.getContext() + "</WorkGuid>");
			sb.append("</InputParameter>");
			String body =Des3Test.encrypt(sb.toString(), workKey.getContext()) ;
			String tag = TxtUtil.digest(body + verifyCode).toUpperCase();

			StringBuilder request = new StringBuilder();
			request.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			request.append("<InputParameter>");
			request.append("<Head>");
			request.append("<Tag>" + tag + "</Tag>");
			request.append("<ClientCode>" + clientCode + "</ClientCode>");
			request.append("</Head>");
			request.append("<Body>");
			request.append(body);
			request.append("</Body>");
			request.append("</InputParameter>");
			//System.out.println(request.toString());
			TReturnInfo rst;
				rst = da.IWsPosCommOperate(907, request.toString());
				System.out.println("===========================查找用户返回结果====================================="+rst);
				if(null==rst||0!=rst.getReturnCode())
					return null;
				return Des3Test.decrypt(getFromBASE64(rst.getRtnMsg()),workKey.getContext());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String doAddPoint(AccountLogin accountLogin, String rew,PointChangeDetal point) {
		IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		IHsCRMWebSrv da;
		try {
			SysTextLibrary workGuid = sysTextLibraryDao
			.getById(ManageContext.CRM_WORKGUID);
			SysTextLibrary workKey = sysTextLibraryDao
			.getById(ManageContext.CRM_WORKKEYID);
			// 注册完成后保存会员卡号用生日做字段
			String Random = "00"
					+ DateUtil.format(new Date(), "yyyyMMddHHmmss");
			da = lo.getIHsCRMWebSrvPort();
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<InputParameter>");
			sb.append("<RandomNo>" + Random + "</RandomNo>");
			sb.append("<UserCode>"+userCode+"</UserCode>");
			sb.append("<WorkGuid>"+workGuid.getContext()+"</WorkGuid>");
			sb.append("<OrgCode>"+orgCode+"</OrgCode>");
			// sb.append("<DepCode></DepCode>");
			// sb.append("<DepName></DepName>");
			sb.append("<DataSet>");
			sb.append("<Rec>");
			sb.append("<OldBillNo>"+point.getOrderNo()+"</OldBillNo>");// 原始单据号（第三方交易流水号）
			sb.append("<OldJfBillNo>"+point.getOrderNo()+"</OldJfBillNo>");// 第三方积分单号
			// sb.append("<TranType><TranType>");//交易类型
			sb.append("<CardNo>"+accountLogin.getBirthday()+"</CardNo>");
			sb.append("<CardNoType>2</CardNoType>");
			//sb.append("<JfPrjCode></JfPrjCode >");// 积分活动方案如果为空，则由CRM系统自动取长期活动方案编码
			sb.append("<YwType>0002</YwType>");// 0002-网站消费产生积分
			sb.append("<JfTotal>"+point.getPoint()+"</JfTotal>");// 积分金额
			sb.append("<XsDate>"
					+ DateUtil.format(new Date(), ManageContext.TIME_FORMAT)
					+ "</XsDate>");// 格式YYYY-MM-DD hh:mm:ss
			//sb.append("<XsDate>2016-04-16 15:16:22</XsDate>");// 格式YYYY-MM-DD hh:mm:ss
			sb.append("</Rec>");
			sb.append("</DataSet>");
			//sb.append("<Remark>3</Remark>");// 备注
			//sb.append("<CardNo>2014120302</CardNo>");
			sb.append("</InputParameter>");
			String body =Des3Test.encrypt(sb.toString().trim(), workKey.getContext()) ;
			String tag = TxtUtil.digest(body+verifyCode).toUpperCase();
			
			StringBuilder request = new StringBuilder();
			request.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			request.append("<InputParameter>");
			request.append("<Head>");
			request.append("<Tag>" + tag + "</Tag>");
			request.append("<ClientCode>"+clientCode+"</ClientCode>");
			request.append("</Head>");
			request.append("<Body>");
			request.append(body);
			request.append("</Body>");
			request.append("</InputParameter>");
			//System.out.println(request.toString());
			TReturnInfo rst;
				rst = da.IWsPosCommOperate(166, request.toString());
				if(null==rst||0!=rst.getReturnCode()){
					logger.error("添加积分失败编号==========================="+rst.getReturnCode()+"失败原因"+getFromBASE64(rst.getRtnMsg()));
					System.out.println("添加积分失败编号==========================="+rst.getReturnCode()+"失败原因"+getFromBASE64(rst.getRtnMsg()));					
					return null;
				}
				return rst.getRtnMsg();
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public String queryPoint(AccountLogin accountLogin, String rew,
			Date date) {
		IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		IHsCRMWebSrv da;
		try {
			SysTextLibrary workGuid = sysTextLibraryDao
			.getById(ManageContext.CRM_WORKGUID);
			SysTextLibrary workKey = sysTextLibraryDao
			.getById(ManageContext.CRM_WORKKEYID);
			// String Random="00"+DateUtil.format(new Date(), "yyyyMMddHHmmss");
			da = lo.getIHsCRMWebSrvPort();
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<InputParameter>");
			// 0-卡内号 1-卡面号 2-会员卡号 3-手机号（2012-07-10增加）必填
			sb.append("<CardNoType>2</CardNoType>");
			sb.append("<CardNo>" + accountLogin.getBirthday() + "</CardNo>");
			sb.append("<Year>" + DateUtil.format(date, "yyyy") + "</Year>");
			sb.append("<Month>" + DateUtil.format(date, "MM") + "</Month>");
			sb.append("<BgnRowNum>0</BgnRowNum>");
			sb.append("<EndRowNum>100</EndRowNum>");
			sb.append("<WorkGuid>{" + workGuid.getContext() + "}</WorkGuid>");
			sb.append("</InputParameter>");
			String body =Des3Test.encrypt(sb.toString(), workKey.getContext()) ;
			String tag = TxtUtil.digest(body+verifyCode).toUpperCase();
			StringBuilder request = new StringBuilder();
			request.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			request.append("<InputParameter>");
			request.append("<Head>");
			request.append("<Tag>" + tag + "</Tag>");
			request.append("<ClientCode>" + clientCode + "</ClientCode>");
			request.append("</Head>");
			request.append("<Body>");
			request.append(body);
			request.append("</Body>");
			request.append("</InputParameter>");
			//System.out.println(request.toString());
			TReturnInfo rst;
				rst = da.IWsPosCommOperate(903, sb.toString());
				if(null==rst||0!=rst.getReturnCode())
					return null;
				return Des3Test.decrypt(getFromBASE64(rst.getRtnMsg()),workKey.getContext());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Autowired
	private TradeAccountDao tradeAccountDao;

	@Override
	public String displayAccount(AccountLogin accountLogin, String rew) {
		IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		IHsCRMWebSrv da;
		try {
			SysTextLibrary workGuid = sysTextLibraryDao
			.getById(ManageContext.CRM_WORKGUID);
			SysTextLibrary workKey = sysTextLibraryDao
			.getById(ManageContext.CRM_WORKKEYID);
			String Random = "00"
					+ DateUtil.format(new Date(), "yyyyMMddHHmmss");
			da = lo.getIHsCRMWebSrvPort();
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<InputParameter>");
			sb.append("<RandomNo>" + Random + "</RandomNo>");
			// 0-卡内号 1-卡面号 2-会员卡号 3-手机号（2012-07-10增加）必填
			sb.append("<UserCode>" +userCode+ "</UserCode>");
			sb.append("<WorkGuid>" + workGuid.getContext() + "</WorkGuid>");
			sb.append("<OrgCode>"+orgCode+"</OrgCode>");
			sb.append("<PosNo></PosNo>");
			sb.append("<CardNoType>3</CardNoType>");
			sb.append("<CardNo>" + accountLogin.getBirthday() + "</CardNo>");
			sb.append("<VipName>" + accountLogin.getRealname() + "</VipName>");
			sb.append("<CertNo>" + accountLogin.getIdCard() + "</CertNo>");
			sb.append("<Mobile>" + accountLogin.getMobile() + "</Mobile>");
			TradeAccount tradeAccount = tradeAccountDao.getById(accountLogin
					.getId());
			sb.append("<Pwd>" + tradeAccount.getTranPassword() + "</Pwd>");
			sb.append("<Remark></Remark>");
			sb.append("</InputParameter>");
			String body =Des3Test.encrypt(sb.toString(), workKey.getContext()) ;
			String tag = TxtUtil.digest(body+verifyCode).toUpperCase();

			StringBuilder request = new StringBuilder();
			request.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			request.append("<InputParameter>");
			request.append("<Head>");
			request.append("<Tag>" + tag + "</Tag>");
			request.append("<ClientCode>" + clientCode + "</ClientCode>");
			request.append("</Head>");
			request.append("<Body>");
			request.append(body);
			request.append("</Body>");
			request.append("</InputParameter>");
		//	System.out.println(request.toString());
			TReturnInfo rst;
				rst = da.IWsPosCommOperate(160, sb.toString());
				if(null==rst||0!=rst.getReturnCode())
					return null;
				return rst.getRtnMsg();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String playAccount(AccountLogin accountLogin, String rew) {
		IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		IHsCRMWebSrv da;
		try {
			SysTextLibrary workGuid = sysTextLibraryDao
			.getById(ManageContext.CRM_WORKGUID);
			SysTextLibrary workKey = sysTextLibraryDao
			.getById(ManageContext.CRM_WORKKEYID);
			String Random = "00"
					+ DateUtil.format(new Date(), "yyyyMMddHHmmss");
			da = lo.getIHsCRMWebSrvPort();
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<InputParameter>");
			sb.append("<RandomNo>" + Random + "</RandomNo>");
			// 0-卡内号 1-卡面号 2-会员卡号 3-手机号（2012-07-10增加）必填
			sb.append("<UserCode>" + accountLogin.getId() + "</UserCode>");
			sb.append("<WorkGuid>" + workGuid.getContext() + "</WorkGuid>");
			sb.append("<OrgCode>"+orgCode+"</OrgCode>");
			sb.append("<PosNo></PosNo>");
			sb.append("<CardNoType>3</CardNoType>");
			sb.append("<CardNo>" + accountLogin.getBirthday() + "</CardNo>");
			sb.append("<VipName>" + accountLogin.getRealname() + "</VipName>");
			sb.append("<CertNo>" + accountLogin.getIdCard() + "</CertNo>");
			sb.append("<Mobile>" + accountLogin.getMobile() + "</Mobile>");
			TradeAccount tradeAccount = tradeAccountDao.getById(accountLogin
					.getId());
			sb.append("<Pwd>" + tradeAccount.getTranPassword() + "</Pwd>");
			sb.append("<Remark></Remark>");
			sb.append("</InputParameter>");
			String body =Des3Test.encrypt(sb.toString(), workKey.getContext()) ;
			String tag = TxtUtil.digest(body+verifyCode).toUpperCase();

			StringBuilder request = new StringBuilder();
			request.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			request.append("<InputParameter>");
			request.append("<Head>");
			request.append("<Tag>" + tag + "</Tag>");
			request.append("<ClientCode>" + clientCode + "</ClientCode>");
			request.append("</Head>");
			request.append("<Body>");
			request.append(body);
			request.append("</Body>");
			request.append("</InputParameter>");
			//System.out.println(request.toString());
			TReturnInfo rst;
				rst = da.IWsPosCommOperate(161, sb.toString());
				if(null==rst||0!=rst.getReturnCode())
					return null;
				return rst.getRtnMsg();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 将 BASE64 编码的字符串 s 进行解码
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b, "gbk");
		} catch (Exception e) {
			return null;
		}
	}
}
