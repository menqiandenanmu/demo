package com.mall.butler.account.m.service._impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.ManageContext;
import com.mall.butler.account.dao.AccountLoginDao;
import com.mall.butler.account.dao.StoreAccountDetailDao;
import com.mall.butler.account.dao.TradeAccountDao;
import com.mall.butler.account.dao.TradeAccountDetailDao;
import com.mall.butler.account.dao.TradeAccountInfoDao;
import com.mall.butler.account.dao.TradeOrderDao;
import com.mall.butler.account.m.dao.TradeAccountDetailVoDao;
import com.mall.butler.account.m.dao.TradeOrderDetailDao;
import com.mall.butler.account.m.model.TradeAccountDetailVo;
import com.mall.butler.account.m.model.TradeAccountVo;
import com.mall.butler.account.m.model.TradeOrderDetailExtend;
import com.mall.butler.account.m.service.MAgentAccountService;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.StoreAccountDetail;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.model.TradeAccountDetail;
import com.mall.butler.account.model.TradeAccountInfo;
import com.mall.butler.account.model.TradeOrder;
import com.mall.butler.port.service.MsgPortService;
import com.mall.butler.service.ApplicationLogService;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MAgentAccountServiceImpl extends BaseServiceImpl implements
		MAgentAccountService {

	private final static Integer PAYED = 1;// 支付类型，已支付
	@Autowired
	private TradeAccountDao tradeAccountDao;
	@Autowired
	private TradeAccountDetailDao tradeAccountDetailDao;
	@Autowired
	private TradeAccountDetailVoDao tradeAccountDetailVoDao;
	@Autowired
	private TradeOrderDao tradeOrderDao;
	@Autowired
	private ApplicationLogService applicationLogService;
	@Autowired
	private TradeOrderDetailDao tradeOrderDetailDao;
	@Autowired
	private TradeAccountInfoDao tradeAccountInfoDao;

	@SuppressWarnings("unchecked")
	@Override
	public Page<TradeAccountVo> queryPageAgentAccount(
			PageRequest<TradeAccountVo> filter) {
		return (Page<TradeAccountVo>) tradeAccountDao.pageQueryObj(filter,
				"BY_PAGE_M");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TradeAccountVo> findTradeAccountList(Map<String, Object> map) {
		return tradeAccountDao.queryObjects("PAGE_M", map);
	}

	@Override
	@Transactional
	public void saveTradeAccount(TradeAccount tradeAccount,
			TradeAccountDetail tradeAccountDetail) {
		// 当操作类型为充值时
		TradeAccount temp = new TradeAccount();
		temp.setAccountId(tradeAccount.getAccountId());
		List<TradeAccount> oldTList = tradeAccountDao.find(temp);
		if (oldTList.isEmpty()) {
			throw new RuntimeException("用户账户信息出错，请刷新!");
		} else {
			for (TradeAccount obj : oldTList) {
				/**
				 * 账户更新
				 */
				obj.setLeftValue(tradeAccountDetail.getOpValue()
						+ obj.getLeftValue());
				tradeAccountDao.update(obj);

				// if(obj.getAgentBuyFlag()==0){
				/**
				 * 生成订单
				 */
				TradeOrder order = new TradeOrder();
				order.setId(tradeOrderDao.getNewId());
				order.setOrderNo(String.format("%1$010d", order.getId()));
				order.setAccountId(obj.getAccountId());
				order.setOpValue(tradeAccountDetail.getOpValue());
				order.setOpLoginId(tradeAccountDetail.getOpLoginId());
				order.setOpLoginName(tradeAccountDetail.getOpLoginName());
				tradeOrderDao.insert(order);
				/**
				 * 订单明细
				 */
				tradeAccountDetail.setId(tradeAccountDetailDao.getNewId());
				tradeAccountDetail.setAccountId(obj.getAccountId());
				tradeAccountDetail.setLeftValue(obj.getLeftValue());
				tradeAccountDetail.setOrderNo(order.getOrderNo());
				tradeAccountDetailDao.insert(tradeAccountDetail);
			}
		}
	}

	@Override
	public void doAddSubAcc(Long id, Long tradeAccId) {
		TradeAccountInfo tradeAccountInfo = tradeAccountInfoDao
				.getById(tradeAccId);
		if (null == tradeAccountInfo)
			throw new RuntimeException("账户类型不存在");
		TradeAccount filter = new TradeAccount();
		filter.setId(id);
		filter.setTradeAccId(tradeAccountInfo.getId());
		List<TradeAccount> list = tradeAccountDao.find(filter);
		// 判断用户是否已经拥有该类型的账户
		if (null != list && list.size() > 0) {
			throw new RuntimeException("子账户类型已经存在");

		} else {
			TradeAccount trade = tradeAccountDao.getById(id);
			// 初始化信用额度 、余额等信息
			TradeAccount tradeAccount = new TradeAccount();
			tradeAccount.setId(tradeAccountDao.getNewId());
			tradeAccount.setLeftValue(0.00d);
			tradeAccount.setCurLeftValue(0.00d);
			tradeAccount.setWarnLine(0.00d);
			tradeAccount.setAccountId(trade.getAccountId());
			tradeAccount.setAccountName(trade.getAccountName());
			tradeAccount.setStatus(true);
			tradeAccount.setTradeAccId(tradeAccountInfo.getId());
			tradeAccount.setTradeAccName(tradeAccountInfo.getAccName());
			tradeAccountDao.insert(tradeAccount);
			applicationLogService.generic("操作员新增子账户：" + trade.getAccountName()
					+ " 新增子账户【" + tradeAccountInfo.getAccName() + "】成功",
					"新增子账户息", ApplicationLogService.GENERIC,
					ManageContext.LOG_SYS_TYPE, ManageContext.LOG_OPT_TYPE_ACC,
					null);
		}
	}

	@Override
	@Transactional
	public void saveCutTradeAccount(TradeAccount tradeAccount,
			TradeAccountDetail tradeAccountDetail) {
		TradeAccount temp = new TradeAccount();
		temp.setAccountId(tradeAccount.getAccountId());
		// temp.setSupplyId(tradeAccount.getSupplyId());
		List<TradeAccount> oldTList = tradeAccountDao.find(temp);
		if (oldTList.isEmpty()) {
			throw new RuntimeException("用户账户信息出错，请刷新!");
		} else {
			for (TradeAccount obj : oldTList) {
				/*** 账户更新 */
				if (obj.getLeftValue() < tradeAccountDetail.getOpValue()) {
					throw new RuntimeException("用户账户余额已被分配至用户或余额不足以扣款，请联系用户!");
				}
				obj.setLeftValue(obj.getLeftValue()
						- tradeAccountDetail.getOpValue());
				tradeAccountDao.update(obj);

				tradeAccountDetail.setId(tradeAccountDetailDao.getNewId());
				tradeAccountDetail.setAccountId(obj.getAccountId());
				tradeAccountDetail.setLeftValue(obj.getLeftValue());
				tradeAccountDetailDao.insert(tradeAccountDetail);
			}
		}
	}

	@Override
	public Page<TradeAccountDetail> findTradeAccountDetail(
			PageRequest<TradeAccountDetail> filter) {
		return tradeAccountDetailDao.findTradeAccountDetail(filter);
	}

	@Override
	public void editTradeAccount(TradeAccount tradeAccount) {
		TradeAccount temp = new TradeAccount();
		temp.setAccountId(tradeAccount.getAccountId());
		List<TradeAccount> oldTList = tradeAccountDao.find(temp);
		if (oldTList.isEmpty()) {
			throw new RuntimeException("用户账户信息出错，请刷新!");
		} else {
			for (TradeAccount obj : oldTList) {
				tradeAccountDao.update(obj);
			}
		}
	}

	@Override
	public void updateWarnLine(Long id, Double opValue) {
		TradeAccount tradeAccount = tradeAccountDao.getById(id);
		if (null == tradeAccount)
			throw new RuntimeException("用户账户信息出错，请刷新!");
		tradeAccount.setWarnLine(opValue);
		tradeAccountDao.update(tradeAccount);
		applicationLogService.generic("操作员给用户：" + tradeAccount.getAccountName()
				+ " 设置信用,调整额度【" + opValue + "】", "操作员调整额度",
				ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
				ManageContext.LOG_OPT_TYPE_ACC, null);
	}

	@Override
	public void signTradeAccount(TradeAccount tradeAccount) {
		TradeAccount temp = new TradeAccount();
		temp.setAccountId(tradeAccount.getAccountId());
		List<TradeAccount> oldTList = tradeAccountDao.find(temp);
		if (oldTList.isEmpty()) {
			throw new RuntimeException("用户账户信息出错，请刷新!");
		} else {
			for (TradeAccount obj : oldTList) {
				// obj.setSignAmount(tradeAccount.getSignAmount());
				tradeAccountDao.update(obj);
			}
			applicationLogService.generic("操作员给用户："
					+ tradeAccount.getAccountName() + " 设置签单", "用户签单设置",
					ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
					ManageContext.LOG_OPT_TYPE_ACC, null);
		}
	}

	@Override
	public Page<TradeAccountDetail> finddisTradeAccountDetail(
			PageRequest<TradeAccountDetail> filter) {
		return tradeAccountDetailDao.finddisTradeAccountDetail(filter);
	}

	@Override
	public Page<TradeAccountDetailVo> findAccountCount(
			PageRequest<TradeAccountDetailVo> filter) {
		return tradeAccountDetailVoDao.findAccountCount(filter);
	}

	@Override
	public Page<TradeAccountDetailVo> finddisAccountCount(
			PageRequest<TradeAccountDetailVo> filter) {
		return tradeAccountDetailVoDao.finddisAccountCount(filter);
	}

	@Override
	public List<TradeAccountDetail> getAgentAccountList(
			Map<String, Object> params) {

		return tradeAccountDetailDao.getAgentAccountList(params);
	}

	@Override
	public List<TradeAccountDetail> getdisAgentAccountList(
			Map<String, Object> params) {

		return tradeAccountDetailDao.getdisAgentAccountList(params);
	}

	@Autowired
	private MsgPortService msgService;

	@Override
	public void insert(TradeAccount tradeAccount) {
		tradeAccount.setId(tradeAccountDao.getNewId());
		tradeAccountDao.insert(tradeAccount);
	}

	@Autowired
	private AccountLoginDao accountLoginDao;

	@Override
	@Transactional
	public void saveTradeAccountCharge(TradeAccount temp,
			TradeAccountDetail tradeAccountDetail, TradeOrder order) {
		// 查询到旧的账户信息
		temp = tradeAccountDao.getById(temp.getId());
		if (temp == null) {
			throw new RuntimeException("用户账户信息出错，请刷新重试!");
		} else {
			// 生成订单信息
			order.setId(tradeOrderDao.getNewId());
			order.setOrderNo(String.format("%1$010d", order.getId()));
			order.setAccountId(temp.getAccountId());
			order.setOpValue(tradeAccountDetail.getOpValue());
			order.setOpType(tradeAccountDetail.getOpType());
			order.setOpLoginId(tradeAccountDetail.getOpLoginId());
			order.setOpLoginName(tradeAccountDetail.getOpLoginName());
			order.setTradeAccId(temp.getTradeAccId());
			order.setTradeAccName(temp.getTradeAccName());
			order.setPayStatus(PAYED);
			// 设置账户信息进行更新操作
			tradeAccountDetail.setId(tradeAccountDetailDao.getNewId());
			tradeAccountDetail.setAccountId(temp.getAccountId());
			tradeAccountDetail.setTradeAccId(temp.getTradeAccId());
			tradeAccountDetail.setTradeAccName(temp.getTradeAccName());
			tradeAccountDetail.setAccountName(temp.getAccountName());
			tradeAccountDetail.setRemark(tradeAccountDetail.getRemark());
			tradeAccountDetail.setRemark1(tradeAccountDetailDao
					.createTransNo(tradeAccountDetail));
			tradeAccountDetail.setLeftValue(temp.getLeftValue()
					+ order.getOpValue());
			tradeAccountDetail.setOrderNo(order.getOrderNo());
			// 调整账户信息
			temp.setLeftValue(temp.getLeftValue()
					+ tradeAccountDetail.getOpValue());
			temp.setCurLeftValue(temp.getCurLeftValue()
					+ tradeAccountDetail.getOpValue());
			// 记录充值订单
			tradeOrderDao.insert(order);
			// 更新资金账户的信息
			tradeAccountDao.update(temp);

			AccountLogin login = accountLoginDao.getById(temp.getId());
			String msg = "尊敬的" + login.getRealname() + "，您好,本次充值金额"
					+ order.getOpValue() + "充值成功，欢迎使用江南钱包！";
			msgService.sendMsg(login.getMobile(), msg);
			// //消费后送积分
			// AgentInfo agentInfo=agentInfoDao.getById(login.getId());
			// mAgentService.addPoint(agentInfo,
			// tradeAccountDetail.getOpValue().intValue(),
			// 3,ManageContext.POINT_RULE_TJN);
			tradeAccountDetailDao.insert(tradeAccountDetail);
			applicationLogService.generic("操作员给用户：" + temp.getAccountName()
					+ " 余额充值,充值金额【" + tradeAccountDetail.getOpValue() + "】",
					"操作员余额充值", ApplicationLogService.GENERIC,
					ManageContext.LOG_SYS_TYPE, ManageContext.LOG_OPT_TYPE_ACC,
					null);
		}

	}

	@Override
	public Page<TradeAccountDetail> findAgentAccountCount(
			PageRequest<TradeAccountDetailVo> filter) {
		return tradeAccountDetailDao.pageQuery(filter, "ACCOUNT_PAGE_M");
	}

	@Override
	public Page<TradeOrderDetailExtend> findAgentAccountDetail(
			PageRequest<TradeOrderDetailExtend> filter) {
		return tradeOrderDetailDao.pageQuery(filter, "ACCOUNT_DETAIL_PAGE_M");
	}

	@Autowired
	private StoreAccountDetailDao storeAccountDetailDao;

	@Override
	public Page<TradeAccountDetail> findTradeDetailCount(
			PageRequest<Map<String, Object>> filter) {

		return tradeAccountDetailDao.pageQuery(filter,
				"BY_ACCOUNT_DETAIL_PAGE_M");
	}

	@Override
	public Page<StoreAccountDetail> findStoreDetailCount(
			PageRequest<Map<String, Object>> filter) {

		return storeAccountDetailDao.pageQuery(filter, "PAGE_M");
	}

	@Override
	public List<TradeAccountDetail> findTradeDetailList(Map<String, Object> map) {
		return tradeAccountDetailDao.queryEntitys("ACCOUNT_DETAIL_PAGE_M", map);
	}

}
