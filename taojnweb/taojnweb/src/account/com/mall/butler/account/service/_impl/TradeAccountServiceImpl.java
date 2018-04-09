package com.mall.butler.account.service._impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.ManageContext;
import com.mall.butler.account.dao.AccountBuindDao;
import com.mall.butler.account.dao.AccountCouponDao;
import com.mall.butler.account.dao.AccountInfoDao;
import com.mall.butler.account.dao.AccountLoginDao;
import com.mall.butler.account.dao.AgentInfoDao;
import com.mall.butler.account.dao.ConsumerCouponsDao;
import com.mall.butler.account.dao.CouponUseRecordDao;
import com.mall.butler.account.dao.GiroInfoDao;
import com.mall.butler.account.dao.SendRulesDao;
import com.mall.butler.account.dao.TradeAccountDao;
import com.mall.butler.account.dao.TradeAccountDetailDao;
import com.mall.butler.account.dao.TradeAccountInfoDao;
import com.mall.butler.account.dao.TradeOrderDao;
import com.mall.butler.account.m.service.MAgentService;
import com.mall.butler.account.model.AccountBuind;
import com.mall.butler.account.model.AccountCoupon;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.AgentInfo;
import com.mall.butler.account.model.ConsumerCoupons;
import com.mall.butler.account.model.CouponUseRecord;
import com.mall.butler.account.model.GiroInfo;
import com.mall.butler.account.model.SendRules;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.model.TradeAccountDetail;
import com.mall.butler.account.model.TradeAccountInfo;
import com.mall.butler.account.model.TradeOrder;
import com.mall.butler.account.service.TradeAccountService;
import com.mall.butler.order.dao.TransInfoDao;
import com.mall.butler.order.model.TransInfo;
import com.mall.butler.port.service.MsgPortService;
import com.mall.butler.port.xml.OrderXml;
import com.mall.butler.port.xml.TradeAccountXml;
import com.mall.butler.service.ApplicationLogService;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class TradeAccountServiceImpl extends BaseServiceImpl implements
		TradeAccountService {
	private static final Logger logger=Logger.getLogger(TradeAccountServiceImpl.class);

	@Autowired
	private TradeAccountInfoDao tradeAccountInfoDao;
	@Autowired
	private TradeAccountDao tradeAccountDao;
	@Autowired
	private AccountLoginDao accountLoginDao;
	@Autowired
	private AccountBuindDao accountBuindDao;
	@Autowired
	private TradeAccountDetailDao tradeAccountDetailDao;
	@Autowired
	private ApplicationLogService applicationLogService;

	@Override
	public Page<TradeAccountInfo> queryPageTradeAccountInfo(
			PageRequest<TradeAccountInfo> pageRequest) {
		return tradeAccountInfoDao.page(pageRequest);
	}

	@Override
	public List<TradeAccountInfo> getTradeAccountInfoList(
			Map<String, Object> params) {
		return tradeAccountInfoDao.getTradeAccountInfoList(params);
	}

	@Override
	public void insert(TradeAccountInfo tradeAccountInfo) {
		tradeAccountInfo.setId(tradeAccountInfoDao.getNewId());
		tradeAccountInfoDao.insert(tradeAccountInfo);
	}

	@Override
	public void updateUseFlag(TradeAccountInfo tradeAccountInfo) {
		tradeAccountInfoDao.updateUseFlag(tradeAccountInfo);
	}

	@Override
	public List<TradeAccountInfo> findAllAccount() {
		return this.tradeAccountInfoDao.find(new TradeAccountInfo());
	}

	// 用户列表
	@Override
	public List<TradeAccount> getTradeAccountList(TradeAccount tradeAccount) {
		return tradeAccountDao.find(tradeAccount);
	}

	// 根据ID得到用户

	@Override
	public TradeAccount getTradeAccountById(Long id) {

		return tradeAccountDao.getById(id);
	}

	@Override
	public void updateTradeAccount(TradeAccount tradeAccount) {
		tradeAccountDao.update(tradeAccount);

	}

	@Override
	public void unBindTrade(TradeAccountXml tradeXml) {
		// 根据用户名获取密码，判断密码是否正确
		AccountLogin login = new AccountLogin();
		login.setLoginName(tradeXml.getAccountCode().trim());
		List<AccountLogin> logins = accountLoginDao.find(login);
		if (null == logins || logins.size() == 0)
			throw new RuntimeException("用户未找到");
		login = logins.get(0);
		if (!tradeXml.getPassword().trim().equals(login.getLoginPass())) {
			throw new RuntimeException("密码不正确");
		}
		AccountBuind accountBuind = accountBuindDao.getById(login
				.getAccountId());
		if (null == accountBuind) {
			throw new RuntimeException("钱包未绑定");
		}
		// 判断用户是否已经绑定
		if (null != accountBuind) {
			accountBuindDao.delete(accountBuind, true);
		}

		applicationLogService.generic("钱包解除绑定：【" + login.getLoginName()
				+ "】 成功", "钱包解除绑定", ApplicationLogService.GENERIC,
				ManageContext.LOG_SYS_TYPE, ManageContext.LOG_OPT_TYPE_ACC,
				null);

	}

	@Override
	public void bindTrade(TradeAccountXml tradeXml) {
		// 根据用户名获取密码，判断密码是否正确
		AccountLogin login = new AccountLogin();
		login.setLoginName(tradeXml.getAccountCode().trim());
		List<AccountLogin> logins = accountLoginDao.find(login);
		if (null == logins || logins.size() == 0)
			throw new RuntimeException("用户未找到");
		login = logins.get(0);
		if (!tradeXml.getPassword().trim().equals(login.getLoginPass())) {
			throw new RuntimeException("密码不正确");
		}
		if (login.getStatus().intValue() == 0)
			throw new RuntimeException("用户已经锁定");
		AccountInfo accountInfo = accountInfoDao.getById(login.getId());
		if (accountInfo.getAccStatus().intValue() == 0) {
			throw new RuntimeException("账户已经停用");
		}
		// 判断用户是否已经绑定
		AccountBuind accountBuind = accountBuindDao.getById(login
				.getAccountId());
		if (null != accountBuind) {
			throw new RuntimeException("该用户已经绑定成功，无需再次绑定");
		}
		// 用户绑定
		AccountBuind build = new AccountBuind();
		build.setId(login.getAccountId());
		build.setAccountName(login.getLoginName());
		build.setAccountId(login.getAccountId());
		build.setMallCode(tradeXml.getMallCode().trim());
		accountBuindDao.insert(build);
		applicationLogService.generic("钱包绑定：【" + login.getLoginName() + "】 成功",
				"钱包绑定", ApplicationLogService.GENERIC,
				ManageContext.LOG_SYS_TYPE, ManageContext.LOG_OPT_TYPE_ACC,
				null);
	}

	@Autowired
	private AgentInfoDao agentInfoDao;

	@Override
	public TradeAccount queryTradeByLoginName(String loginName) {
		AccountLogin login = new AccountLogin();
		login.setLoginName(loginName);
		List<AccountLogin> logins = accountLoginDao.find(login);
		if (null == logins || logins.size() == 0)
			throw new RuntimeException("用户未找到");
		login = logins.get(0);
		if (login.getStatus().intValue() == 0)
			throw new RuntimeException("用户已经锁定");
		AccountInfo accountInfo = accountInfoDao.getById(login.getId());
		if (accountInfo.getAccStatus().intValue() == 0) {
			throw new RuntimeException("账户已经停用");
		}
		// 查看用用是否绑定
		AccountBuind build = accountBuindDao.getById(login.getAccountId());
		if (null == build)
			throw new RuntimeException("用户未绑定钱包");
		return tradeAccountDao.getById(login.getAccountId());
	}

	@Override
	public TradeAccount queryTradeByPassword(String password) {
		AgentInfo agentInfo = new AgentInfo();
		agentInfo.setPartnerKey(password.substring(0, 12));
		List<AgentInfo> agentInfos = agentInfoDao.find(agentInfo);
		if (null == agentInfos || agentInfos.size() == 0)
			throw new RuntimeException("用户未找到");
		agentInfo = agentInfos.get(0);
		AccountLogin login = accountLoginDao.getById(agentInfo.getId());
		if (login.getStatus().intValue() == 0)
			throw new RuntimeException("用户已经锁定");
		AccountInfo accountInfo = accountInfoDao.getById(login.getId());
		if (accountInfo.getAccStatus().intValue() == 0) {
			throw new RuntimeException("账户已经停用");
		}
		return tradeAccountDao.getById(login.getAccountId());
	}

	@Autowired
	private AccountCouponDao accountCouponDao;
	@Autowired
	private CouponUseRecordDao couponUseRecordDao;

	@Transactional
	@Override
	public String crmPayed(OrderXml orderXml, double payAmount) {
		// 判断此订单是否已经支付成功,支付成功的交易直接返回，否则继续交易
		TradeAccountDetail detailFilter = new TradeAccountDetail();
		detailFilter.setOrderNo(orderXml.getOrderNo());
		detailFilter.setOpType(1);
		List<TradeAccountDetail> details = tradeAccountDetailDao.find(detailFilter);
		if (null != details && details.size() > 0) {			
			throw new RuntimeException("该笔交易已经存在，支付失败");
		}
		// 识别条形码，如果条形码前面带88说明是赠券走赠券的业务
		if (orderXml.getPassword().substring(0, 2).equals("88")) {
			// 通过条形码查找用户赠券
			AccountCoupon accountCoupon = new AccountCoupon();
			accountCoupon.setBarcode(orderXml.getPassword().substring(0, 12));
			List<AccountCoupon> accountCoupons = accountCouponDao.find(accountCoupon);
			if (null == accountCoupons || accountCoupons.size() == 0)
				throw new RuntimeException("用户赠券未找到");
			accountCoupon = accountCoupons.get(0);
			AccountLogin login = accountLoginDao.getById(accountCoupon.getAccountId());
			if (login.getStatus().intValue() == 0)
				throw new RuntimeException("用户已经锁定");
			AccountInfo accountInfo = accountInfoDao.getById(login.getId());
			if (accountInfo.getAccStatus().intValue() == 0) {
				throw new RuntimeException("账户已经停用");
			}
			TradeAccount trade = tradeAccountDao.getById(login.getAccountId());
			if (null == trade) {
				throw new RuntimeException("账户未找到");
			}
			if (!trade.getStatus())
				throw new RuntimeException("账户已经停用");
			// 判断使用状态及支付金额
			if (accountCoupon.getCouponStatus().intValue() != 0) {
				throw new RuntimeException("赠券状态无效");
			}
			// 判断券是否过期
			if (new Date().after(DateUtil.addDays(accountCoupon.getExpiratDate(),1)))
				throw new RuntimeException("赠券已过期");
			//判断支付金额是否可以使用赠券
			SendRules rules=sendRulesDao.getById(accountCoupon.getRuleId());
			if(rules.getSpendParam()>payAmount){
				throw new RuntimeException("消费金额不满足赠券使用条件");
			}
			if (payAmount > accountCoupon.getPrice())// 如果支付金额大于赠券扣账户余额
			{
				if (payAmount - accountCoupon.getPrice() > trade.getCurLeftValue())
					throw new RuntimeException("余额不足");
				trade.setCurLeftValue(trade.getCurLeftValue()- (payAmount - accountCoupon.getPrice()));
				tradeAccountDao.update(trade);
				// 更新赠券状态
				accountCoupon.setCouponStatus(1);
				accountCoupon.setRemark(DateUtil.format(new Date(), ManageContext.TIME_FORMAT));
				accountCoupon.setRemark2(orderXml.getOrderNo());//券支付交易号
				accountCouponDao.update(accountCoupon);
				// 添加使用记录
				CouponUseRecord useRecord = new CouponUseRecord();
				useRecord.setId(couponUseRecordDao.getNewId());
				useRecord.setAccountId(accountInfo.getId());
				useRecord.setAccountName(accountInfo.getAccName());
				useRecord.setCouponCode(accountCoupon.getBarcode());
				useRecord.setCouponName(accountCoupon.getCouponName());
				useRecord.setCouponStatus(1);
				useRecord.setPaySum(payAmount);
				useRecord.setPayTime(new Date());
				useRecord.setPrice(accountCoupon.getPrice());
				useRecord.setRealName(accountCoupon.getRealName());
				useRecord.setRuleId(accountCoupon.getRuleId());
				useRecord.setTradeLeft(trade.getCurLeftValue());
				useRecord.setTransNo(orderXml.getOrderNo());
				useRecord.setRemark(orderXml.getOrderInfo());
				couponUseRecordDao.insert(useRecord);
			} else {
				// 更新赠券状态
				accountCoupon.setCouponStatus(1);
				accountCoupon.setRemark(DateUtil.format(new Date(), ManageContext.TIME_FORMAT));
				accountCoupon.setRemark2(orderXml.getOrderNo());//券支付交易号
				accountCouponDao.update(accountCoupon);
				// 添加使用记录
				CouponUseRecord useRecord = new CouponUseRecord();
				useRecord.setId(couponUseRecordDao.getNewId());
				useRecord.setAccountId(accountInfo.getId());
				useRecord.setAccountName(accountInfo.getAccName());
				useRecord.setCouponCode(accountCoupon.getBarcode());
				useRecord.setCouponName(accountCoupon.getCouponName());
				useRecord.setCouponStatus(1);
				useRecord.setPaySum(payAmount);
				useRecord.setPayTime(new Date());
				useRecord.setPrice(accountCoupon.getPrice());
				useRecord.setRealName(accountCoupon.getRealName());
				useRecord.setRuleId(accountCoupon.getRuleId());
				useRecord.setTradeLeft(trade.getCurLeftValue());
				useRecord.setTransNo(orderXml.getOrderNo());
				useRecord.setRemark(orderXml.getOrderInfo());
				couponUseRecordDao.insert(useRecord);
			}
			// 添加操作明细
			TradeAccountDetail tAccountDetail = new TradeAccountDetail();
			tAccountDetail.setId(tradeAccountDetailDao.getNewId());
			tAccountDetail.setAccountId(trade.getAccountId());
			tAccountDetail.setAccountName(trade.getAccountName());
			tAccountDetail.setLeftValue(trade.getCurLeftValue());
			tAccountDetail.setOpLoginId(login.getId());
			tAccountDetail.setOpLoginName(orderXml.getOperateCode());// 操作员编号
			tAccountDetail.setOpType(1);// 扫码支付
			tAccountDetail.setOpValue(payAmount - accountCoupon.getPrice());
			tAccountDetail.setOrderNo(orderXml.getOrderNo());
			tAccountDetail.setTradeAccId(trade.getTradeAccId());
			tAccountDetail.setTradeAccName(trade.getTradeAccName());
			tAccountDetail.setRemark("赠券支付" + orderXml.getOrderInfo());
			tAccountDetail.setRemark1(tAccountDetail.getOrderNo());
			tAccountDetail.setRemark2(orderXml.getPosCode());// pos编号
			tAccountDetail.setRemark3(orderXml.getStoreCode());// 店铺编号
			tAccountDetail.setSerialNo(ManageContext.ACCOUNT_CRM);
			tradeAccountDetailDao.insert(tAccountDetail);
			// 支付成功后更新用户条形码
			// 保存用户条形码
			AgentInfo agentInfo = agentInfoDao.getById(accountInfo.getId());
			agentInfo.setPartnerKey(getAgentKey());
			agentInfoDao.update(agentInfo);
			applicationLogService.generic("CRM扫码支付：【" + trade.getAccountName()+ "】 成功,支付金额" + payAmount, "CRM扫码支付",ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,ManageContext.LOG_OPT_TYPE_ACC, null);
			return tAccountDetail.getRemark1();
		} else {
			// 通过条形码查找用户
			orderXml.getPassword();
			AgentInfo agentInfo = new AgentInfo();
			agentInfo.setPartnerKey(orderXml.getPassword().substring(0, 12));
			List<AgentInfo> agentInfos = agentInfoDao.find(agentInfo);
			if (null == agentInfos || agentInfos.size() == 0)
				throw new RuntimeException("用户未找到");
			agentInfo = agentInfos.get(0);
			AccountLogin login = accountLoginDao.getById(agentInfo.getId());
			if (login.getStatus().intValue() == 0)
				throw new RuntimeException("用户已经锁定");
			AccountInfo accountInfo = accountInfoDao.getById(login.getId());
			if (accountInfo.getAccStatus().intValue() == 0) {
				throw new RuntimeException("账户已经停用");
			}
			TradeAccount trade = tradeAccountDao.getById(login.getAccountId());
			if (null == trade) {
				throw new RuntimeException("账户未找到");
			}
			if (!trade.getStatus())
				throw new RuntimeException("账户已经停用");
			if (payAmount > trade.getCurLeftValue())
				throw new RuntimeException("余额不足");
			trade.setCurLeftValue(trade.getCurLeftValue() - payAmount);
			// 添加操作明细
			TradeAccountDetail tAccountDetail = new TradeAccountDetail();
			tAccountDetail.setId(tradeAccountDetailDao.getNewId());
			tAccountDetail.setAccountId(trade.getAccountId());
			tAccountDetail.setAccountName(trade.getAccountName());
			tAccountDetail.setLeftValue(trade.getCurLeftValue());
			tAccountDetail.setOpLoginId(login.getId());
			tAccountDetail.setOpLoginName(orderXml.getOperateCode());// 操作员编号
			tAccountDetail.setOpType(1);// 扫码支付
			tAccountDetail.setOpValue(payAmount);
			tAccountDetail.setOrderNo(orderXml.getOrderNo());
			tAccountDetail.setTradeAccId(trade.getTradeAccId());
			tAccountDetail.setTradeAccName(trade.getTradeAccName());
			tAccountDetail.setRemark(orderXml.getOrderInfo());
			tAccountDetail.setRemark1(tAccountDetail.getOrderNo());
			tAccountDetail.setRemark2(orderXml.getPosCode());// pos编号
			tAccountDetail.setRemark3(orderXml.getStoreCode());// 店铺编号
			tAccountDetail.setSerialNo(ManageContext.ACCOUNT_CRM);
			tradeAccountDetailDao.insert(tAccountDetail);
			tradeAccountDao.update(trade);
			// 支付成功后更新用户条形码
			// 保存用户条形码
			agentInfo.setPartnerKey(getAgentKey());
			agentInfoDao.update(agentInfo);
			// 满送券业务
			sendCoupon(orderXml, payAmount, accountInfo);
			applicationLogService.generic("CRM扫码支付：【" + trade.getAccountName()+ "】 成功,支付金额" + payAmount, "CRM扫码支付",ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,ManageContext.LOG_OPT_TYPE_ACC, null);
			return tAccountDetail.getRemark1();
		}

	}
	
	
	
	public String getAgentKey(){
		String barCode=agentInfoDao.createKey();
		if(barCode.substring(0, 2).equals("88"))
			getAgentKey();
		if(barCode.substring(0, 1).equals("0"))
			getAgentKey();
		//查询是否有重复的
		AgentInfo filter=new AgentInfo();
		filter.setPartnerKey(barCode);
		List<AgentInfo> list=agentInfoDao.find(filter);
		if(null!=list&&list.size()>0){
			getAgentKey();
		}
		return barCode;
	}
	public String getCouponCode(AccountCoupon accountCoupon){
		String barCode=accountCouponDao.createBarCode(accountCoupon);
		//查询是否有重复的
		AccountCoupon filter=new AccountCoupon();
		filter.setBarcode(barCode);
		List<AccountCoupon> list=accountCouponDao.find(filter);
		if(null!=list&&list.size()>0){
			getCouponCode(accountCoupon);
		}
		return barCode;
	}
	@Autowired
	private ConsumerCouponsDao consumerCouponsDao;
	@Autowired
	private SendRulesDao sendRulesDao;

	public void sendCoupon(OrderXml orderXml, double payAmount,
			AccountInfo accountInfo) {
		AccountLogin login = accountLoginDao.getById(accountInfo.getId());
		// 查询所有满送活动
		SendRules sendRules = new SendRules();
		sendRules.setSendSum(payAmount);
		List<SendRules> rules = sendRulesDao.queryEntitys("FIND_USED",
				sendRules);
		if (null != rules && rules.size() > 0) {
			SendRules rule = rules.get(0);
			//获取最大赠送值
			double sendMaxNum=rule.getSendMaxNum();
			double sendNum=0.0;
			// 判断是否符合规则
			if (payAmount >= rule.getSendSum()) {
				// 判断是否已经赠送过
				ConsumerCoupons consumerCoupons = new ConsumerCoupons();
				consumerCoupons.setRuleId(rule.getId());
				List<ConsumerCoupons> coupons = consumerCouponsDao
						.find(consumerCoupons);
				if (null != coupons && coupons.size() > 0) {
					ConsumerCoupons counp = coupons.get(0);
					AccountCoupon accountCoupon = new AccountCoupon();
					accountCoupon.setAccountId(accountInfo.getId());
					accountCoupon.setCouponCode(counp.getCouponCode());
					List<AccountCoupon> accountCoupons = accountCouponDao
							.find(accountCoupon);
					// 未兑换或者兑换次数小于活动期间可兑换次数的
					System.out.println("优惠券赠送数量"+accountCoupons.size());
					if (null == accountCoupons||accountCoupons.size()==0
							|| rule.getActiveCashNum() > accountCoupons.size()) {
						// 判断当日兑换次数是否超过值
						AccountCoupon filter = new AccountCoupon();
						filter.setAccountId(accountInfo.getId());
						filter.setCouponCode(counp.getCouponCode());
						filter.setPayTime(new Date());
						List<AccountCoupon> filters = accountCouponDao
								.queryEntitys("TODAY_NUM", filter);
						System.out.println("当日优惠券赠送数量============="+filters.size());
						if (null == filters||filters.size()==0
								|| rule.getTodayCashNum() > filters.size()) {
							// 赠送
							System.out.println("最大值赠券======================");
							if (rule.getSuperimposedFalg() == 0) {
								ConsumerCoupons counp1 =consumerCouponsDao.getById(counp.getId());
								if (counp1.getCouponNum() > 0) {
									sendNum+=counp1.getPrice();
									if(sendMaxNum>sendNum){
									// 最大值
									AccountCoupon accountCoupon2 = new AccountCoupon();
									accountCoupon2.setId(accountCouponDao
											.getNewId());
									accountCoupon2.setAccountId(accountInfo
											.getId());
									accountCoupon2.setAccountName(accountInfo
											.getAccName());
									accountCoupon2.setCouponCode(counp1
											.getCouponCode());
									accountCoupon2.setCouponName(counp1
											.getCouponName());
									accountCoupon2.setCouponStatus(0);
									accountCoupon2.setExpiratDate(counp1
											.getExpiratDate());
									accountCoupon2.setPaySum(payAmount);
									accountCoupon2.setPayTime(new Date());
									accountCoupon2.setPrice(counp1.getPrice());
									accountCoupon2.setRealName(login
											.getRealname());
									accountCoupon2.setRuleId(counp1.getRuleId());
									accountCoupon2.setTransNo(orderXml
											.getOrderNo());
									accountCoupon2.setBarcode(getCouponCode(accountCoupon2));
									accountCoupon2.setRemark2(rule.getRemark());
									accountCouponDao.insert(accountCoupon2);
									// 更新库存
									counp1
											.setCouponNum(counp.getCouponNum() - 1);
									consumerCouponsDao.update(counp1);}
								}
							} else {
								System.out.println("递增赠券======================");
								// 递增 支付金额/递增金额
								int paynum=(int) payAmount;
								int sendnum= rule.getSendSum().intValue();
								
								double sendTotalNum=0.0;
								int sendCoupNum=0;
								int num = paynum/ sendnum;
								for (int i = 0; i < num; i++) {
									ConsumerCoupons counp1 =consumerCouponsDao.getById(counp.getId());
									if (counp1.getCouponNum() > 0) {
										sendNum+=counp1.getPrice();
										if(sendMaxNum>=sendNum){
											sendTotalNum+=counp1.getPrice();
											sendCoupNum+=1;
											if((i+1)==num){
												AccountCoupon accountCoupon2 = new AccountCoupon();
												accountCoupon2.setId(accountCouponDao
														.getNewId());
												accountCoupon2.setAccountId(accountInfo
														.getId());
												accountCoupon2
														.setAccountName(accountInfo
																.getAccName());
												accountCoupon2.setCouponCode(counp1
														.getCouponCode());
												accountCoupon2.setCouponName(counp1
														.getCouponName());
												accountCoupon2.setCouponStatus(0);
												accountCoupon2.setExpiratDate(counp1
														.getExpiratDate());
												accountCoupon2.setPaySum(payAmount);
												accountCoupon2.setPayTime(new Date());
												accountCoupon2.setPrice(sendTotalNum);
												accountCoupon2.setRealName(login
														.getRealname());
												accountCoupon2.setRuleId(counp1
														.getRuleId());
												accountCoupon2.setTransNo(orderXml
														.getOrderNo());
												accountCoupon2.setBarcode(getCouponCode(accountCoupon2));
												accountCoupon2.setRemark2(rule.getRemark());
												accountCouponDao.insert(accountCoupon2);
												// 更新库存
												counp1
														.setCouponNum(counp1
																.getCouponNum() - sendCoupNum);
												consumerCouponsDao.update(counp1);	
											}
										}else {
											AccountCoupon accountCoupon2 = new AccountCoupon();
											accountCoupon2.setId(accountCouponDao
													.getNewId());
											accountCoupon2.setAccountId(accountInfo
													.getId());
											accountCoupon2
													.setAccountName(accountInfo
															.getAccName());
											accountCoupon2.setCouponCode(counp1
													.getCouponCode());
											accountCoupon2.setCouponName(counp1
													.getCouponName());
											accountCoupon2.setCouponStatus(0);
											accountCoupon2.setExpiratDate(counp1
													.getExpiratDate());
											accountCoupon2.setPaySum(payAmount);
											accountCoupon2.setPayTime(new Date());
											accountCoupon2.setPrice(sendTotalNum);
											accountCoupon2.setRealName(login
													.getRealname());
											accountCoupon2.setRuleId(counp1
													.getRuleId());
											accountCoupon2.setTransNo(orderXml
													.getOrderNo());
											accountCoupon2.setBarcode(getCouponCode(accountCoupon2));
											accountCouponDao.insert(accountCoupon2);
											// 更新库存
											counp1
													.setCouponNum(counp1
															.getCouponNum() - sendCoupNum);
											consumerCouponsDao.update(counp1);
										}
									}
								}
							}
						}

					}
				}

			}
		}
	}

	public Boolean codeFlag(String code) {
		AgentInfo filter = new AgentInfo();
		filter.setPartnerKey(code);
		if (agentInfoDao.find(filter).size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Autowired
	private MAgentService mAgentService;

	@Transactional
	@Override
	public void payed(OrderXml orderXml) {
		AccountLogin login = new AccountLogin();
		login.setLoginName(orderXml.getAccountCode());
		List<AccountLogin> logins = accountLoginDao.find(login);
		if (null == logins || logins.size() == 0)
			throw new RuntimeException("用户未找到");
		login = logins.get(0);
		if (login.getStatus().intValue() == 0)
			throw new RuntimeException("用户已经锁定");
		AccountInfo accountInfo = accountInfoDao.getById(login.getId());
		if (accountInfo.getAccStatus().intValue() == 0) {
			throw new RuntimeException("账户已经停用");
		}
		TradeAccount tradeAccount = tradeAccountDao.getById(login.getId());
		if (!orderXml.getPassword().trim().equals(
				tradeAccount.getTranPassword())) {
			throw new RuntimeException("密码不正确");
		}
		// 查看用用是否绑定
		AccountBuind build = accountBuindDao.getById(login.getAccountId());
		if (null == build)
			throw new RuntimeException("用户未绑定钱包");
		TradeAccount trade = tradeAccountDao.getById(login.getAccountId());
		if (null == trade) {
			throw new RuntimeException("账户未找到");
		}
		if (!trade.getStatus())
			throw new RuntimeException("账户已经停用");
		if (orderXml.getPaySum() > trade.getCurLeftValue())
			throw new RuntimeException("余额不足");
		trade.setCurLeftValue(trade.getCurLeftValue() - orderXml.getPaySum());
		// 添加操作明细
		TradeAccountDetail tAccountDetail = new TradeAccountDetail();
		tAccountDetail.setId(tradeAccountDetailDao.getNewId());
		tAccountDetail.setAccountId(trade.getAccountId());
		tAccountDetail.setAccountName(trade.getAccountName());
		tAccountDetail.setLeftValue(trade.getCurLeftValue());
		tAccountDetail.setOpLoginId(login.getId());
		tAccountDetail.setOpLoginName(login.getLoginName());
		tAccountDetail.setOpType(1);
		tAccountDetail.setOpValue(orderXml.getPaySum());
		tAccountDetail.setOrderNo(orderXml.getOrderNo());
		tAccountDetail.setTradeAccId(trade.getTradeAccId());
		tAccountDetail.setTradeAccName(trade.getTradeAccName());
		tAccountDetail.setRemark(orderXml.getOrderInfo());
		tAccountDetail.setRemark1(orderXml.getTradeNo());
		tAccountDetail.setSerialNo(ManageContext.ACCOUNT_TJN);
		tradeAccountDetailDao.insert(tAccountDetail);
		tradeAccountDao.update(trade);
		// 消费后送积分
		AgentInfo agentInfo = agentInfoDao.getById(login.getId());
		mAgentService.addPoint(agentInfo, tAccountDetail.getOpValue()
				.intValue(), 3, ManageContext.POINT_RULE_TJN);
		applicationLogService.generic("订单支付：【" + trade.getAccountName()
				+ "】 成功,支付金额" + orderXml.getPaySum(), "订单支付",
				ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
				ManageContext.LOG_OPT_TYPE_ACC, null);
	}

	@Transactional
	@Override
	public TradeAccountDetail crmRefund(OrderXml orderXml, double payAmount) {
		// 查找交易记录
		TradeAccountDetail filter = new TradeAccountDetail();
		filter.setOpType(1);
		filter.setRemark1(orderXml.getTransNo());
		List<TradeAccountDetail> transDetails = tradeAccountDetailDao.find(filter);
		if (null == transDetails || transDetails.size() == 0)
			throw new RuntimeException("交易记录未找到");
		// 查找退款记录
		TradeAccountDetail refundDetail = new TradeAccountDetail();
		refundDetail.setOpType(2);// crm退款
		refundDetail.setRemark1(orderXml.getTransNo());
		List<TradeAccountDetail> refundDetails = tradeAccountDetailDao.find(refundDetail);
		if (null != refundDetails && refundDetails.size() > 0) {
			System.out.println("交易号" + orderXml.getTransNo() + "退款已经完成,订单号"	+ orderXml.getOrderNo());
			throw new RuntimeException("该笔交易已完成退款");
		}
		// 查看是否使用赠券
		CouponUseRecord useRecord = new CouponUseRecord();
		useRecord.setTransNo(transDetails.get(0).getOrderNo());
		List<CouponUseRecord> useRecords = couponUseRecordDao.find(useRecord);
		Boolean couponFlag = false;
		if (null != useRecords && useRecords.size() > 0) {
			CouponUseRecord record = useRecords.get(0);
			AccountCoupon accountCoupon = new AccountCoupon();
			accountCoupon.setRemark2(useRecord.getTransNo());
			List<AccountCoupon> list = accountCouponDao.find(accountCoupon);
			if (null != list && list.size() > 0) {
				AccountCoupon conCoupons = list.get(0);
				conCoupons.setCouponStatus(0);// 更新为未使用状态
				accountCouponDao.update(conCoupons);
			}
			// 删除使用记录
			couponUseRecordDao.delete(record);
			couponFlag = true;
		}
		
		//查询是否赠送赠券，赠送的赠券删除
		AccountCoupon accountCoupon2 = new AccountCoupon();
		accountCoupon2.setTransNo(orderXml.getTransNo());
		List<AccountCoupon> accountCoupons=accountCouponDao.find(accountCoupon2);
		if(null!=accountCoupons&&accountCoupons.size()>0){
			for(AccountCoupon coupon:accountCoupons){
				//更新数量
				ConsumerCoupons consumerCoupons=new ConsumerCoupons();
				consumerCoupons.setCouponCode(coupon.getCouponCode());
				List<ConsumerCoupons> list=consumerCouponsDao.find(consumerCoupons);
				for(ConsumerCoupons consumerCoupons2:list){
					//根据规则查询赠送类型
					SendRules rules=sendRulesDao.getById(consumerCoupons2.getRuleId());
					if(rules.getSuperimposedFalg().intValue()==0){
						consumerCoupons2.setCouponNum(consumerCoupons2.getCouponNum()+1);
					}else {
						//判断赠送数量
						int num=coupon.getPrice().intValue()/consumerCoupons2.getPrice().intValue();
						consumerCoupons2.setCouponNum(consumerCoupons2.getCouponNum()+num);
					}
					consumerCouponsDao.update(consumerCoupons2);
				}
				//删除赠券
				accountCouponDao.delete(coupon);
			}
		}
		// AccountLogin login = new AccountLogin();
		// login.setLoginName(orderXml.getAccountCode());
		// List<AccountLogin> logins = accountLoginDao.find(login);
		// if (null == logins || logins.size() == 0)
		// throw new RuntimeException("用户未找到");
		// login = logins.get(0);
		AccountLogin login = accountLoginDao.getById(transDetails.get(0).getAccountId());
		if (null == login)
			throw new RuntimeException("用户未找到");
		if (login.getStatus().intValue() == 0)
			throw new RuntimeException("用户已经锁定");
		AccountInfo accountInfo = accountInfoDao.getById(login.getId());
		if (accountInfo.getAccStatus().intValue() == 0) {
			throw new RuntimeException("账户已经停用");
		}
		// 查看用用是否绑定
		// AccountBuind build=accountBuindDao.getById(login.getAccountId());
		// if(null==build)
		// throw new RuntimeException("用户未绑定钱包");
		TradeAccount trade = tradeAccountDao.getById(login.getAccountId());
		if (null == trade) {
			throw new RuntimeException("账户未找到");
		}
		if (!trade.getStatus())
			throw new RuntimeException("账户已经停用");
		// 判断退款金额是否大于交易金额
		if (couponFlag) {
			trade.setCurLeftValue(trade.getCurLeftValue()+ (payAmount - useRecords.get(0).getPrice()));
			if (payAmount - useRecords.get(0).getPrice() > transDetails.get(0).getOpValue())
				throw new RuntimeException("退款金额不能大于支付金额");
		} else {
			trade.setCurLeftValue(trade.getCurLeftValue() + payAmount);
			if (payAmount > transDetails.get(0).getOpValue())
				throw new RuntimeException("退款金额不能大于支付金额");
		}

		// 添加操作明细
		TradeAccountDetail tAccountDetail = new TradeAccountDetail();
		tAccountDetail.setId(tradeAccountDetailDao.getNewId());
		tAccountDetail.setAccountId(trade.getAccountId());
		tAccountDetail.setAccountName(trade.getAccountName());
		tAccountDetail.setLeftValue(trade.getCurLeftValue());
		tAccountDetail.setOpLoginId(login.getId());
		if (!TxtUtil.isEmpty(orderXml.getOperateCode())) {
			tAccountDetail.setOpLoginName(orderXml.getOperateCode());
		} else {

			tAccountDetail.setOpLoginName(login.getLoginName());
		}
		tAccountDetail.setOpType(2);// crm退款
		tAccountDetail.setOpValue(payAmount);
		tAccountDetail.setOrderNo(orderXml.getOrderNo());
		tAccountDetail.setTradeAccId(trade.getTradeAccId());
		tAccountDetail.setTradeAccName(trade.getTradeAccName());
		tAccountDetail.setRemark1(orderXml.getTransNo());
		tAccountDetail.setSerialNo(ManageContext.ACCOUNT_CRM);
		tradeAccountDetailDao.insert(tAccountDetail);
		System.out.println("退款成功" + orderXml.getOrderNo());
		tradeAccountDao.update(trade);
		applicationLogService.generic("订单退款：【" + trade.getAccountName()+ "】 成功,退款金额" + payAmount, "订单退款",ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,ManageContext.LOG_OPT_TYPE_ACC, null);
		return tAccountDetail;

	}

	@Transactional
	@Override
	public void refund(OrderXml orderXml) {
		// 查找交易记录
		TradeAccountDetail filter = new TradeAccountDetail();
		filter.setRemark1(orderXml.getTradeNo());
		filter.setOpType(1);
		List<TradeAccountDetail> transDetails = tradeAccountDetailDao
				.find(filter);
		if (null == transDetails || transDetails.size() == 0)
			throw new RuntimeException("交易记录未找到");
		// AccountLogin login = new AccountLogin();
		// login.setLoginName(orderXml.getAccountCode());
		// List<AccountLogin> logins = accountLoginDao.find(login);
		AccountLogin login = accountLoginDao.getById(transDetails.get(0)
				.getAccountId());
		if (null == login)
			throw new RuntimeException("用户未找到");
		if (login.getStatus().intValue() == 0)
			throw new RuntimeException("用户已经锁定");
		AccountInfo accountInfo = accountInfoDao.getById(login.getId());
		if (accountInfo.getAccStatus().intValue() == 0) {
			throw new RuntimeException("账户已经停用");
		}
		// 查看用用是否绑定
		// AccountBuind build=accountBuindDao.getById(login.getAccountId());
		// if(null==build)
		// throw new RuntimeException("用户未绑定钱包");
		TradeAccount trade = tradeAccountDao.getById(login.getAccountId());
		if (null == trade) {
			throw new RuntimeException("账户未找到");
		}
		if (!trade.getStatus())
			throw new RuntimeException("账户已经停用");
		trade
				.setCurLeftValue(trade.getCurLeftValue()
						+ orderXml.getRefundSum());

		// 判断退款金额是否大于交易金额
		if (orderXml.getRefundSum() > transDetails.get(0).getOpValue())
			throw new RuntimeException("退款金额不能大于支付金额");
		// 查找退款记录
		TradeAccountDetail refundDetail = new TradeAccountDetail();
		refundDetail.setOpType(2);// crm退款
		refundDetail.setRemark1(orderXml.getTradeNo());
		List<TradeAccountDetail> refundDetails = tradeAccountDetailDao
				.find(refundDetail);
		if (null != refundDetails && refundDetails.size() > 0) {
			System.out.println("该笔交易已经完成退款");
		} else {

			// 添加操作明细
			TradeAccountDetail tAccountDetail = new TradeAccountDetail();
			tAccountDetail.setId(tradeAccountDetailDao.getNewId());
			tAccountDetail.setAccountId(trade.getAccountId());
			tAccountDetail.setAccountName(trade.getAccountName());
			tAccountDetail.setLeftValue(trade.getCurLeftValue());
			tAccountDetail.setOpLoginId(login.getId());
			tAccountDetail.setOpLoginName(login.getLoginName());
			tAccountDetail.setOpType(2);
			tAccountDetail.setOpValue(orderXml.getRefundSum());
			tAccountDetail.setOrderNo(transDetails.get(0).getOrderNo());
			tAccountDetail.setTradeAccId(trade.getTradeAccId());
			tAccountDetail.setTradeAccName(trade.getTradeAccName());
			// tAccountDetail.setRemark(orderXml.getOrderInfo());
			tAccountDetail.setRemark1(orderXml.getTradeNo());
			tAccountDetail.setSerialNo(ManageContext.ACCOUNT_TJN);
			tradeAccountDetailDao.insert(tAccountDetail);
			tradeAccountDao.update(trade);
			applicationLogService.generic("订单退款：【" + trade.getAccountName()
					+ "】 成功,退款金额" + orderXml.getRefundSum(), "订单退款",
					ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
					ManageContext.LOG_OPT_TYPE_ACC, null);
		}
	}

	@Override
	public List<TradeAccountDetail> queryTradeDetails(String accountName,
			String opType) {
		TradeAccountDetail tradeDetail = new TradeAccountDetail();
		tradeDetail.setAccountName(accountName);
		tradeDetail.setOpType(Integer.parseInt(opType));
		return tradeAccountDetailDao.find(tradeDetail);
	}

	@Override
	public Page<TradeAccountDetail> pageQuery(
			PageRequest<TradeAccountDetail> tradeRequest) {
		return tradeAccountDetailDao.page(tradeRequest);
	}

	@Autowired
	private TradeOrderDao tradeOrderDao;
	@Autowired
	private AccountInfoDao accountInfoDao;
	@Autowired
	private TransInfoDao transInfoDao;

	@Transactional
	@Override
	public TradeOrder doRechageTrade(TradeAccount tradeAccount, Double opVal,
			String transNo, Long opId, Integer opType, String remark) {
		// 得到账户信息
		if (null == tradeAccount.getId())
			throw new RuntimeException("账户id不能为空");
		TradeAccount trade = tradeAccountDao.getById(tradeAccount.getId());
		if (null == trade || trade.getDeleted())
			throw new RuntimeException("账户不存在");
		if (!trade.getStatus())
			throw new RuntimeException("账户已经停用");
		// 判断用户状态
		AccountInfo accountInfo = accountInfoDao.getById(trade.getAccountId());
		if (accountInfo.getDeleted()
				|| accountInfo.getAccStatus().intValue() == 0)
			throw new RuntimeException("用户已经停用");
		if (null == opVal)
			throw new RuntimeException("操作金额不能为空");
		if (opType == 0)
			transNo = transInfoDao.createTransNo("manage");
		if (null == transNo)
			throw new RuntimeException("交易订单号不能为空");
		if (null == opId)
			throw new RuntimeException("操作员不能为空");
		// 添加充值订单
		TradeOrder tradeOrder = new TradeOrder();
		tradeOrder.setId(tradeOrderDao.getNewId());
		tradeOrder.setAccountId(trade.getAccountId());

		AccountLogin login = accountLoginDao.getById(opId);
		tradeOrder.setOpLoginId(opId);
		tradeOrder.setOpLoginName(login.getLoginName());
		tradeOrder.setOpType(opType);
		tradeOrder.setOpValue(opVal);
		tradeOrder.setOrderNo(tradeOrderDao.createOrderNo(tradeOrder));
		tradeOrder.setPayStatus(0);// 未支付
		tradeOrder.setRemark(remark);
		// tradeOrder.setTradeAccId(trade.getTradeAccId());
		tradeOrder.setTradeAccName(trade.getTradeAccName());
		tradeOrder.setNetpayNo(transNo);
		tradeOrderDao.insert(tradeOrder);
		applicationLogService.generic("提交账户充值：<" + trade.getAccountName()
				+ "> ,充值金额" + opVal, "提交账户充值", ApplicationLogService.GENERIC,
				ManageContext.LOG_SYS_TYPE, ManageContext.LOG_OPT_TYPE_ACC,
				null);
		return tradeOrder;
	}

	@Autowired
	private MsgPortService msgService;

	@Transactional
	@Override
	public void payed(String transNo, String gatewayTransNo, String reportInfo) {
		// 更新交易状态
		TransInfo transInfo = new TransInfo();
		transInfo.setTransNo(transNo);
		List<TransInfo> trans = transInfoDao.find(transInfo);
		if (null != trans && trans.size() > 0) {
			transInfo = trans.get(0);
		} else {
			throw new RuntimeException("交易记录未找到");
		}
		transInfo.setStatus(1);
		transInfo.setGatewayTransNo(gatewayTransNo);
		transInfo.setReportTime(new Date());
		transInfo.setReportInfo(reportInfo);
		transInfoDao.update(transInfo);
		// 根据交易号查询充值订单
		TradeOrder tradeOrder = new TradeOrder();
		tradeOrder.setNetpayNo(transNo);
		List<TradeOrder> trades = tradeOrderDao.find(tradeOrder);
		if (null != trades && trades.size() > 0) {
			tradeOrder = trades.get(0);
		} else {
			throw new RuntimeException("订单未找到");
		}
		// 查找用户账户
		TradeAccount trade = new TradeAccount();
		trade.setAccountId(tradeOrder.getAccountId());
		// trade.setTradeAccId(tradeOrder.getTradeAccId());
		List<TradeAccount> tAccounts = tradeAccountDao.find(trade);
		if (null != tAccounts && tAccounts.size() > 0) {
			trade = tAccounts.get(0);
		} else {
			throw new RuntimeException("账户未找到");
		}
		trade
				.setCurLeftValue(trade.getCurLeftValue()
						+ tradeOrder.getOpValue());
		trade.setLeftValue(trade.getLeftValue() + tradeOrder.getOpValue());
		// 更新充值订单状态
		tradeOrder.setPayStatus(1);
		tradeOrderDao.update(tradeOrder);
		// 添加操作明细
		TradeAccountDetail tAccountDetail = new TradeAccountDetail();
		tAccountDetail.setId(tradeAccountDetailDao.getNewId());
		tAccountDetail.setAccountId(trade.getAccountId());
		tAccountDetail.setAccountName(trade.getAccountName());
		tAccountDetail.setLeftValue(trade.getCurLeftValue());
		tAccountDetail.setOpLoginId(transInfo.getLoginId());
		AccountLogin login = accountLoginDao.getById(transInfo.getLoginId());
		tAccountDetail.setOpLoginName(login.getLoginName());
		tAccountDetail.setOpType(0);
		tAccountDetail.setOpValue(tradeOrder.getOpValue());
		tAccountDetail.setOrderNo(tradeOrder.getOrderNo());
		tAccountDetail.setTradeAccId(trade.getTradeAccId());
		tAccountDetail.setTradeAccName(trade.getTradeAccName());
		// tAccountDetail.setTradeType(0);
		tAccountDetail.setRemark(tradeOrder.getRemark());
		tAccountDetail.setRemark1(transNo);
		tradeAccountDetailDao.insert(tAccountDetail);
		tradeAccountDao.update(trade);
		// 记录操作的内容
		String msg = "尊敬的" + login.getRealname() + "，您好,本次充值金额"
				+ tradeOrder.getOpValue() + "充值成功，欢迎使用江南钱包！";
		msgService.sendMsg(login.getMobile(), msg);
		applicationLogService.generic("账户充值：【" + trade.getAccountName()
				+ "】成功,充值金额" + tradeOrder.getOpValue(), "账户充值",
				ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
				ManageContext.LOG_OPT_TYPE_ACC, null);
	}

	@Override
	public Page<TradeAccountDetail> page(PageRequest<TradeAccountDetail> request) {
		Page<TradeAccountDetail> result = tradeAccountDetailDao.pageQuery(
				request, "BY_PAGE_M");
		return result;
	}

	@Override
	public List<TradeAccountDetail> findAgentOrderCountList(
			TradeAccountDetail tradeAccountDetail) {
		return tradeAccountDetailDao.queryEntitys("PAGE_M", tradeAccountDetail);
	}

	@Override
	public TradeAccountDetail findByOrderNo(String orderNo) {
		TradeAccountDetail tAccountDetail = new TradeAccountDetail();
		tAccountDetail.setOrderNo(orderNo);
		List<TradeAccountDetail> list = tradeAccountDetailDao
				.find(tAccountDetail);
		if (null != list && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Autowired
	private GiroInfoDao giroInfoDao;
	@Transactional
	@Override
	public synchronized Long payToPay(TradeAccount tradeAccount, String accountName,
			Double paysum) {
		TradeAccount payAccount=tradeAccountDao.getById(tradeAccount.getId());
		logger.error("转账开始"+payAccount.getTradeAccName()+"给【"+accountName+"】转账金额："+paysum);
		System.out.println("转账开始"+payAccount.getTradeAccName()+"给【"+accountName+"】转账金额："+paysum);
		//查询账户余额是否足够
		if(paysum>payAccount.getCurLeftValue())
		throw new RuntimeException("账户余额不足，转账失败");
		double	beforesum=payAccount.getCurLeftValue();
		//扣款
		payAccount.setCurLeftValue(payAccount.getCurLeftValue()-paysum);
		tradeAccountDao.update(payAccount);
		// 添加操作明细
		TradeAccountDetail tAccountDetail = new TradeAccountDetail();
		tAccountDetail.setId(tradeAccountDetailDao.getNewId());
		tAccountDetail.setAccountId(payAccount.getAccountId());
		tAccountDetail.setAccountName(payAccount.getAccountName());
		tAccountDetail.setLeftValue(payAccount.getCurLeftValue());
		tAccountDetail.setOpLoginId(payAccount.getId());
		AccountLogin login = accountLoginDao.getById(payAccount.getId());
		tAccountDetail.setOpLoginName(login.getLoginName());
		tAccountDetail.setOpType(7);
		tAccountDetail.setOpValue(paysum);
		tAccountDetail.setOrderNo(tradeAccountDetailDao.createTransNo(tAccountDetail));
		tAccountDetail.setTradeAccId(payAccount.getTradeAccId());
		tAccountDetail.setTradeAccName(payAccount.getTradeAccName());
		// tAccountDetail.setTradeType(0);
		tAccountDetail.setRemark("转账付款");
		tAccountDetail.setRemark1(tAccountDetail.getOrderNo());
		tradeAccountDetailDao.insert(tAccountDetail);
		// TODO Auto-generated method stub
		//查询用户是否在
		TradeAccount filter=new TradeAccount();
		filter.setAccountName(accountName);
		List<TradeAccount> trades=tradeAccountDao.find(filter);
		if(null!=trades&&trades.size()>0){
			filter=trades.get(0);
		}
		else {
			throw new RuntimeException("转账账户不存在");
		}
		
		//增加收入
		filter.setCurLeftValue(filter.getCurLeftValue()+paysum);
		tradeAccountDao.update(filter);
		//添加流水
		// 添加操作明细
		TradeAccountDetail tAccountDetail2 = new TradeAccountDetail();
		tAccountDetail2.setId(tradeAccountDetailDao.getNewId());
		tAccountDetail2.setAccountId(filter.getAccountId());
		tAccountDetail2.setAccountName(filter.getAccountName());
		tAccountDetail2.setLeftValue(filter.getCurLeftValue());
		tAccountDetail2.setOpLoginId(payAccount.getId());
		tAccountDetail2.setOpLoginName(login.getLoginName());
		tAccountDetail2.setOpType(6);
		tAccountDetail2.setOpValue(paysum);
		tAccountDetail2.setOrderNo(tAccountDetail.getOrderNo());
		tAccountDetail2.setTradeAccId(filter.getTradeAccId());
		tAccountDetail2.setTradeAccName(filter.getTradeAccName());
		// tAccountDetail2.setTradeType(0);
		tAccountDetail2.setRemark("转账收款");
		tAccountDetail2.setRemark1(tAccountDetail.getOrderNo());
		tradeAccountDetailDao.insert(tAccountDetail2);
		//添加转账记录
		GiroInfo giroInfo=new GiroInfo();
		giroInfo.setId(giroInfoDao.getNewId());
		giroInfo.setAccountId(payAccount.getId());
		giroInfo.setAccountId2(filter.getId());
		giroInfo.setAccountName(payAccount.getAccountName());
		giroInfo.setAccountName2(filter.getAccountName());
		giroInfo.setInLeftValue(payAccount.getCurLeftValue());
		giroInfo.setLeftValue(beforesum);
		giroInfo.setOpLoginId(login.getId());
		giroInfo.setOpLoginName(login.getLoginName());
		giroInfo.setOpType(0);
		giroInfo.setOpValue(paysum);
		giroInfo.setOrderNo(tAccountDetail.getOrderNo());
		AgentInfo info=agentInfoDao.getById(payAccount.getId());
		giroInfo.setRealname(info.getCorpName());
		AgentInfo out=agentInfoDao.getById(filter.getId());
		giroInfo.setRealname2(out.getCorpName());
		giroInfo.setSerialNo(tAccountDetail.getOrderNo());
		giroInfo.setTradeAccId(payAccount.getAccountId());
		giroInfo.setTradeAccName(payAccount.getTradeAccName());
		giroInfoDao.insert(giroInfo);
		logger.error("转账结束"+payAccount.getTradeAccName()+"给【"+accountName+"】转账金额："+paysum);
		System.out.println("转账结束"+payAccount.getTradeAccName()+"给【"+accountName+"】转账金额："+paysum);
		applicationLogService.generic("转账成功：【" + tradeAccount.getAccountName()
				+ "】转账给 "+accountName+"成功,转账金额：" + paysum, "转账",
				ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
				ManageContext.LOG_OPT_TYPE_ACC, null);
		return giroInfo.getId();
	}

	@Override
	public Page<GiroInfo> pageQueryGiro(PageRequest<GiroInfo> pageRequest) {
		// TODO Auto-generated method stub
		return giroInfoDao.page(pageRequest);
	}

	@Override
	public List<GiroInfo> findGiroInfo(GiroInfo giroInfo) {
		// TODO Auto-generated method stub
		return giroInfoDao.find(giroInfo);
	}

	@Override
	public List<TradeAccountDetail> findReback(TradeAccountDetail reback) {
		
		return tradeAccountDetailDao.find(reback);
	}

}
