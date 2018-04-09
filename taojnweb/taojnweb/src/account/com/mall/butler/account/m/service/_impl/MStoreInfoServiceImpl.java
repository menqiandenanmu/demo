package com.mall.butler.account.m.service._impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.ManageContext;
import com.mall.butler.account.dao.AccountInfoDao;
import com.mall.butler.account.dao.AccountLoginDao;
import com.mall.butler.account.dao.DiscountRulesDao;
import com.mall.butler.account.dao.StoreAccountDetailDao;
import com.mall.butler.account.dao.StoreInfoDao;
import com.mall.butler.account.dao.TradeAccountDao;
import com.mall.butler.account.dao.TradeAccountDetailDao;
import com.mall.butler.account.m.service.MStoreInfoService;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.DiscountRules;
import com.mall.butler.account.model.StoreAccountDetail;
import com.mall.butler.account.model.StoreInfo;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.model.TradeAccountDetail;
import com.mall.butler.service.ApplicationLogService;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.common.TxtUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MStoreInfoServiceImpl extends BaseServiceImpl implements
		MStoreInfoService {

	@Autowired
	private StoreInfoDao storeInfoDao;
	@Autowired
	private AccountLoginDao accountLoginDao;
	@Autowired
	private AccountInfoDao accountInfoDao;
	@Autowired
	private TradeAccountDao tradeAccountDao;
	@Autowired
	private ApplicationLogService applicationLogService;

	@Transactional
	@Override
	public void doDel(StoreInfo storeInfo) {
		// TODO Auto-generated method stub
		storeInfoDao.delete(storeInfo);
		// 删除操作员
		AccountLogin accountLogin = accountLoginDao.getById(storeInfo.getId());
		accountLoginDao.delete(accountLogin);
		AccountInfo accountInfo = accountInfoDao.getById(storeInfo.getId());
		accountInfoDao.delete(accountInfo);
		TradeAccount tradeAccount = tradeAccountDao.getById(storeInfo.getId());
		tradeAccountDao.delete(tradeAccount);
		// 删除账户
	}

	@Override
	public StoreInfo queryByQrcode(String barCode) {
		StoreInfo filter = new StoreInfo();
		filter.setQrCode(barCode);
		List<StoreInfo> list = storeInfoDao.find(filter);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Autowired
	private TradeAccountDetailDao tradeAccountDetailDao;
	@Autowired
	private StoreAccountDetailDao storeAccountDetailDao;
	@Transactional
	@Override
	public Double qrPay(StoreInfo storeInfo, AccountLogin login, double payAmount) {
		// TODO Auto-generated method stub
		// 根据支付金额查找折扣规则
		DiscountRules discountRules=new DiscountRules();
		discountRules.setAccountId(storeInfo.getId());
		discountRules.setMinNum(payAmount);
		List<DiscountRules> rules=discountRulesDao.queryEntitys("FIND_USED", discountRules);
		if (null != rules && rules.size() > 0) {
			discountRules= rules.get(0);
			payAmount=payAmount*discountRules.getDiscountNum()/100;
		}
		// 查找用户余额
		TradeAccount tradeAccount=tradeAccountDao.getById(login.getId());
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
		if(payAmount>tradeAccount.getCurLeftValue())
			throw new RuntimeException("账户余额不足");
		trade.setCurLeftValue(trade.getCurLeftValue() - payAmount);
		// 添加操作明细
		TradeAccountDetail tAccountDetail = new TradeAccountDetail();
		tAccountDetail.setId(tradeAccountDetailDao.getNewId());
		tAccountDetail.setAccountId(trade.getAccountId());
		tAccountDetail.setAccountName(trade.getAccountName());
		tAccountDetail.setLeftValue(trade.getCurLeftValue());
		tAccountDetail.setOpLoginId(login.getId());
		tAccountDetail.setOpLoginName(login.getLoginName());// 操作员编号
		tAccountDetail.setOpType(1);// 扫码支付
		tAccountDetail.setOpValue(payAmount);
		tAccountDetail.setOrderNo(tradeAccountDetailDao.createTransNo(tAccountDetail));
		tAccountDetail.setTradeAccId(trade.getTradeAccId());
		tAccountDetail.setTradeAccName(trade.getTradeAccName());
		tAccountDetail.setRemark("扫码支付");
		tAccountDetail.setRemark1(tAccountDetail.getOrderNo());
		tAccountDetail.setRemark3(storeInfo.getStoreCode());// 店铺编号
		tAccountDetail.setSerialNo(storeInfo.getStoreName());
		tradeAccountDetailDao.insert(tAccountDetail);
		tradeAccountDao.update(trade);
		//查询店铺账号
		TradeAccount storeTradeAccount=tradeAccountDao.getById(storeInfo.getId());
		if(null==storeTradeAccount||storeTradeAccount.getStatus().equals("0")||storeTradeAccount.getDeleted())
			throw new RuntimeException("店铺未找到");
		//增加收入
		storeTradeAccount.setCurLeftValue(storeTradeAccount.getCurLeftValue()+payAmount);
		storeTradeAccount.setLeftValue(storeTradeAccount.getLeftValue()+payAmount);
		tradeAccountDao.update(storeTradeAccount);
		// 添加店铺交易流水
		StoreAccountDetail storeAccountDetail=new StoreAccountDetail();
		storeAccountDetail.setId(storeAccountDetailDao.getNewId());
		storeAccountDetail.setAccountId(storeInfo.getId());
		storeAccountDetail.setAccountName(storeInfo.getStoreName());
		storeAccountDetail.setFukuanId(trade.getId());
		storeAccountDetail.setFukuanName(login.getRealname());
		storeAccountDetail.setLeftValue(storeTradeAccount.getCurLeftValue());
		storeAccountDetail.setOpLoginId(login.getId());
		storeAccountDetail.setOpLoginName(login.getLoginName());
		storeAccountDetail.setOpType(0);
		storeAccountDetail.setOpValue(payAmount);
		storeAccountDetail.setOrderNo(tAccountDetail.getOrderNo());
		storeAccountDetail.setRemark(tAccountDetail.getRemark());
		//storeAccountDetail.setSerialNo(storeAccountDetailDao.);
		storeAccountDetailDao.insert(storeAccountDetail);
		applicationLogService.generic("店铺扫码支付：【" + trade.getAccountName()
				+ "】 成功,支付金额" + payAmount, "店铺扫码支付",
				ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
				ManageContext.LOG_OPT_TYPE_ACC, null);
		return payAmount;
	}

	@Transactional
	@Override
	public void doSave(StoreInfo storeInfo, AccountLogin accountLogin) {
		// TODO Auto-generated method stub

		storeInfo.setQrCode(getBarCode(storeInfo));
		// 添加账户及操作员
		// 验证数据
		validate(storeInfo, accountLogin);
		// 用户信息
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setId(accountInfoDao.getNewId());
		accountInfo.setAccName(accountLogin.getLoginName());
		accountInfo.setAccNo(storeInfo.getStoreCode());
		accountInfo.setAccStatus(1);
		accountInfo.setAccLevelId(0l);
		accountInfo.setAccType(4);
		accountInfo.setAccLevelId(0L);
		accountInfoDao.insert(accountInfo);
		storeInfo.setId(accountInfo.getId());
		// 保存数据
		accountLogin.setId(accountInfo.getId());
		accountLogin.setLoginPass(TxtUtil.digest(accountLogin.getLoginPass()));
		accountLogin.setLoginCount(0);
		accountLogin.setAccountId(accountInfo.getId());
		accountLogin.setStatus(1);
		accountLogin.setAdminFlag(true);
		accountLogin.setLoginName(accountLogin.getLoginName());
		accountLogin.setMobile(storeInfo.getTelphone());
		accountLogin.setRealname(storeInfo.getStoreName());
		accountLogin.setRemark(storeInfo.getRemark());
		accountLoginDao.insert(accountLogin);
		// 新增用户账户
		TradeAccount tradeAccount = new TradeAccount();
		tradeAccount.setAccountId(accountInfo.getId());
		tradeAccount.setAccountName(accountLogin.getLoginName());
		tradeAccount.setLeftValue(0d);
		tradeAccount.setId(accountInfo.getId());
		tradeAccount.setWarnLine(0d);
		tradeAccount.setCurLeftValue(0d);
		tradeAccount.setTradeAccName(storeInfo.getStoreName());// 钱包名称
		tradeAccount.setTradeType(tradeAccountDao.createTransNo(tradeAccount));
		tradeAccount.setStatus(true);
		tradeAccount.setTranPassword(accountLogin.getLoginPass());
		tradeAccountDao.insert(tradeAccount);
		storeInfoDao.insert(storeInfo);
	}

	/**
	 * 保存信息验证
	 * 
	 * @return
	 */
	private void validate(StoreInfo storeInfo, AccountLogin accountLogin) {
		AccountLogin accountLogin2 = new AccountLogin();
		accountLogin2.setLoginName(accountLogin.getLoginName());
		if (accountLoginDao.find(accountLogin2).size() > 0) {
			throw new RuntimeException("登录名已经存在" + accountLogin.getLoginName());
		}
		StoreInfo filter = new StoreInfo();
		filter.setStoreCode(storeInfo.getStoreCode());
		List<StoreInfo> al = storeInfoDao.find(filter);
		if (al != null && al.size() > 0)
			throw new RuntimeException("店铺编号已经存在" + storeInfo.getStoreCode());
	}

	public String getBarCode(StoreInfo storeInfo) {
		String qrCode = storeInfoDao.createQrCode(storeInfoDao.getNewId());
		// 查询是否有重复的
		StoreInfo filter = new StoreInfo();
		filter.setQrCode(qrCode);
		List<StoreInfo> list = storeInfoDao.find(filter);
		if (null != list && list.size() > 0) {
			getBarCode(storeInfo);
		}
		return qrCode;
	}

	@Override
	public void doUpdate(StoreInfo storeInfo) {
		// TODO Auto-generated method stub
		StoreInfo obj = storeInfoDao.getById(storeInfo.getId());
		obj.setAddress(storeInfo.getAddress());
		// obj.setFax(storeInfo.getFax());
		obj.setLinkman(storeInfo.getLinkman());
		obj.setLinkmanTel(storeInfo.getLinkmanTel());
		// obj.setQrCode(storeInfo.getQrCode());
		obj.setRemark(storeInfo.getRemark());
		obj.setStoreCode(storeInfo.getStoreCode());
		obj.setStoreName(storeInfo.getStoreName());
		obj.setTelphone(storeInfo.getTelphone());
		storeInfoDao.update(obj);
	}

	@Transactional
	@Override
	public void doUpdateUser(StoreInfo storeInfo, AccountLogin accountLogin) {
		StoreInfo obj = storeInfoDao.getById(storeInfo.getId());
		obj.setAddress(storeInfo.getAddress());
		obj.setLinkman(storeInfo.getLinkman());
		obj.setLinkmanTel(storeInfo.getLinkmanTel());
		obj.setStoreName(storeInfo.getStoreName());
		obj.setTelphone(storeInfo.getTelphone());
		storeInfoDao.update(obj);
		// AccountLogin login=accountLoginDao.getById(accountLogin.getId());
		// //
		// if(!TxtUtil.digest(accountLogin.getLoginName()).equals(login.getLoginPass()))
		// // throw new RuntimeException("原始密码不争取");
		// login.setLoginPass(TxtUtil.digest(accountLogin.getLoginPass()));
		// accountLoginDao.update(login);
	}

	@Override
	public Page<StoreInfo> pageQuery(PageRequest<StoreInfo> pageRequest) {
		// TODO Auto-generated method stub
		return storeInfoDao.pageQuery(pageRequest, "PAGE_M");
	}

	@Autowired
	private DiscountRulesDao discountRulesDao;

	@Override
	public Page<DiscountRules> pageRuleQuery(
			PageRequest<DiscountRules> pageRequest) {
		// TODO Auto-generated method stub
		return discountRulesDao.pageQuery(pageRequest, "PAGE_M");
	}

	@Override
	public void doDelRule(DiscountRules discountRules) {
		// TODO Auto-generated method stub
		discountRulesDao.delete(discountRules);
	}

	@Override
	public void doSaveRule(DiscountRules discountRules) {
		// TODO Auto-generated method stub
		discountRules.setId(discountRulesDao.getNewId());
		discountRulesDao.insert(discountRules);
	}

	@Override
	public void doUpdateRule(DiscountRules discountRules) {
		// TODO Auto-generated method stub
		DiscountRules filter = discountRulesDao.getById(discountRules.getId());
		filter.setMaxNum(discountRules.getMaxNum());
		filter.setMinNum(discountRules.getMinNum());
		filter.setDiscountNum(discountRules.getDiscountNum());
		filter.setStoreName(discountRules.getStoreName());
		filter.setBeginTime(discountRules.getBeginTime());
		filter.setEndTime(discountRules.getEndTime());
		filter.setRemark(discountRules.getRemark());
		filter.setRuleStatus(discountRules.getRuleStatus());
		discountRulesDao.update(filter);
	}

}
