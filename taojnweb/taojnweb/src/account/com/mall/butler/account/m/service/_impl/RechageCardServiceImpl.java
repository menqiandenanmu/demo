package com.mall.butler.account.m.service._impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.ManageContext;
import com.mall.butler.account.dao.AccountInfoDao;
import com.mall.butler.account.dao.AccountLoginDao;
import com.mall.butler.account.dao.RechageCardDao;
import com.mall.butler.account.dao.RechageCardOrderDao;
import com.mall.butler.account.dao.TradeAccountDao;
import com.mall.butler.account.dao.TradeAccountDetailDao;
import com.mall.butler.account.dao.TradeOrderDao;
import com.mall.butler.account.m.service.MAccountService;
import com.mall.butler.account.m.service.RechageCardService;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.RechageCard;
import com.mall.butler.account.model.RechageCardOrder;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.model.TradeAccountDetail;
import com.mall.butler.account.model.TradeOrder;
import com.mall.butler.helper.SessionHelper;
import com.mall.butler.port.service.MsgPortService;
import com.mall.butler.service.ApplicationLogService;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.sys.m.service.MDictService;
import com.mall.butler.sys.model.SysDictDetail;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class RechageCardServiceImpl extends BaseServiceImpl implements
		RechageCardService {
	@Autowired
	private RechageCardDao rechageCardDao;
	@Autowired
	protected MAccountService accountService;
	@Autowired
	private ApplicationLogService applicationLogService;
	@Autowired
	private SessionHelper sessionHelper;

	@Override
	public Page<RechageCard> page(PageRequest<RechageCard> filter) {
		return rechageCardDao.page(filter);
	}

	@Override
	public void doAddSave(RechageCard rechageCard) {
		rechageCard.setId(rechageCardDao.getNewId());
		rechageCardDao.insert(rechageCard);

	}

	@Override
	public void doUpdate(RechageCard rechageCard) {

		RechageCard rechageCardOld = rechageCardDao
				.getById(rechageCard.getId());
		if (null != rechageCard.getCardName()) {
			rechageCardOld.setCardName(rechageCard.getCardName());
		}
		if (null != rechageCard.getCardCode()) {
			rechageCardOld.setCardCode(rechageCard.getCardCode());
		}
		if (null != rechageCard.getLeftValue()) {
			rechageCardOld.setLeftValue(rechageCard.getLeftValue());
		}
		if (null != rechageCard.getStatus()) {
			rechageCardOld.setStatus(rechageCard.getStatus());
		}
		if (null != rechageCard.getAuditSyatus()) {
			rechageCardOld.setAuditSyatus(rechageCard.getAuditSyatus());
		}
		if (null != rechageCard.getRemark()) {
			rechageCardOld.setRemark(rechageCard.getRemark());
		}
		if (null != rechageCard.getCardPassword()) {
			rechageCardOld.setCardPassword(rechageCard.getCardPassword());
		}
		if (null != rechageCard.getCardType()) {
			rechageCardOld.setCardType(rechageCard.getCardType());
		}
		if (null != rechageCard.getCreateYear()) {
			rechageCardOld.setCreateYear(rechageCard.getCreateYear());
		}
		if (null != rechageCard.getFailureTime()) {
			rechageCardOld.setFailureTime(rechageCard.getFailureTime());
		}
		if (null != rechageCard.getCardMoney()) {
			rechageCardOld.setCardMoney(rechageCard.getCardMoney());
		}
		if (null != rechageCard.getAuditPerson()) {
			rechageCardOld.setAuditPerson(rechageCard.getAuditPerson());
		}
		if (null != rechageCard.getAuditPerson()) {
			rechageCardOld.setAuditPerson(rechageCard.getAuditPerson());
		}
		rechageCardDao.update(rechageCardOld);

	}

	@Override
	@Transactional
	public void doDel(RechageCard rechageCard) {
		rechageCard = rechageCardDao.getById(rechageCard.getId());
		rechageCardDao.delete(rechageCard);

		// 获取当前登录账户
		Long pk = (Long) sessionHelper.get(ManageContext.SESSION_LOGINID);
		AccountLogin login = null;
		if (pk != null)
			login = accountService.getEntityById(AccountLogin.class, pk);

		applicationLogService.generic("操作员" + login.getLoginName() + "删除充值卡:"
				+ rechageCard.getCardName() + "成功,充值卡编号:"
				+ rechageCard.getCardCode(), "删除充值卡",
				ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
				ManageContext.LOG_OPT_TYPE_ACC, null);

	}

	@Override
	public RechageCard getEntity(RechageCard rechageCard) {
		return rechageCardDao.getEntity("ALL", rechageCard);

	}

	@Autowired
	private AccountLoginDao accountLoginDao;
	@Autowired
	private MsgPortService msgService;
	@Autowired
	private RechageCardOrderDao rechageCardOrderDao;
	@Autowired
	private AccountInfoDao accountInfoDao;
	@Autowired
	private TradeAccountDao tradeAccountDao;
	@Autowired
	private TradeOrderDao tradeOrderDao;
	@Autowired
	private TradeAccountDetailDao tradeAccountDetailDao;

	@Transactional
	@Override
	public void doAddRechargeCard(RechageCard rechageCard, Long id) {
		// 查找充值卡
		RechageCard filterCard = new RechageCard();
		filterCard.setCardCode(rechageCard.getCardCode());
		List<RechageCard> list = rechageCardDao.find(rechageCard);
		if (null == list || list.size() == 0)
			throw new RuntimeException("充值卡不存在");
		filterCard = list.get(0);
		if (filterCard.getStatus().intValue() == 0)
			throw new RuntimeException("充值卡已失效");
		if (filterCard.getUseStatus().intValue() == 1)
			throw new RuntimeException("充值卡已失效");
		if (!filterCard.getCardPassword().equals(rechageCard.getCardPassword()))
			throw new RuntimeException("卡密码不正确");
		filterCard.setUseStatus(1);
		rechageCardDao.update(filterCard);
		// 设置充值卡使用人员
		// 记录操作的内容
		AccountLogin login = accountLoginDao.getById(id);
		AccountInfo accountInfo=accountInfoDao.getById(id);
		RechageCardOrder cardOrder=new RechageCardOrder();
		cardOrder.setId(rechageCardOrderDao.getNewId());
		cardOrder.setAccountId(login.getAccountId());
		cardOrder.setAccountName(accountInfo.getAccName());
		cardOrder.setCardCode(filterCard.getCardCode());
		cardOrder.setCardName(filterCard.getCardName());
		cardOrder.setLeftValue(filterCard.getCardMoney());
		cardOrder.setLoginId(login.getId());
		cardOrder.setLoginName(login.getLoginName());
		cardOrder.setRemark("卡充值");
		cardOrder.setOrderNo(rechageCardOrderDao.createTransNo(cardOrder));
		rechageCardOrderDao.insert(cardOrder);
		//更新账户余额
		//查询到旧的账户信息
		TradeAccount temp = tradeAccountDao.getById(login.getId());
		if (temp==null) {
			throw new RuntimeException("用户账户信息出错，请刷新重试!");
		} else {
			//生成订单信息
			TradeOrder order=new TradeOrder();
			order.setId(tradeOrderDao.getNewId());
			order.setOrderNo(String.format("%1$010d", order.getId()));
			order.setAccountId(temp.getAccountId());
			order.setOpValue(cardOrder.getLeftValue());
			order.setOpType(2);
			order.setOpLoginId(login.getId());
			order.setOpLoginName(login.getLoginName());
			order.setTradeAccId(temp.getTradeAccId());
			order.setTradeAccName(temp.getTradeAccName());
			order.setPayStatus(1);
			//设置账户信息进行更新操作
			TradeAccountDetail tradeAccountDetail=new TradeAccountDetail();
			tradeAccountDetail.setId(tradeAccountDetailDao.getNewId());
			tradeAccountDetail.setAccountId(temp.getAccountId());
			tradeAccountDetail.setTradeAccId(temp.getTradeAccId());
			tradeAccountDetail.setTradeAccName(temp.getTradeAccName());
			tradeAccountDetail.setAccountName(temp.getAccountName());
			tradeAccountDetail.setRemark(tradeAccountDetail.getRemark());
			tradeAccountDetail.setRemark1(tradeAccountDetailDao.createTransNo(tradeAccountDetail));
			tradeAccountDetail.setLeftValue(temp.getLeftValue()+order.getOpValue());
			tradeAccountDetail.setOrderNo(order.getOrderNo());
			tradeAccountDetail.setOpValue(filterCard.getCardMoney());
			tradeAccountDetail.setOpType(3);//充值卡充值
			//调整账户信息
			temp.setLeftValue(temp.getLeftValue()+tradeAccountDetail.getOpValue());
			temp.setCurLeftValue(temp.getCurLeftValue()+tradeAccountDetail.getOpValue());
			//记录充值订单
			tradeOrderDao.insert(order);
			//更新资金账户的信息
			tradeAccountDao.update(temp);
			//记录操作的内容
			String msg="尊敬的"+login.getRealname()+"，您好,本次充值金额"+order.getOpValue()+"充值成功，欢迎使用江南钱包！";
			msgService.sendMsg(login.getMobile(), msg);
			tradeAccountDetailDao.insert(tradeAccountDetail);
			applicationLogService.generic("用户：" + temp.getAccountName() +
					 " 卡充值,充值金额【"+tradeAccountDetail.getOpValue()+"】", "用户卡充值", ApplicationLogService.GENERIC,ManageContext.LOG_SYS_TYPE,ManageContext.LOG_OPT_TYPE_ACC,null);
		}
	}

	@Override
	@Transactional
	public void doUse(RechageCard rechageCard) {
		this.doUpdate(rechageCard);

		// 获取当前登录账户
		Long pk = (Long) sessionHelper.get(ManageContext.SESSION_LOGINID);
		AccountLogin login = null;
		if (pk != null)
			login = accountService.getEntityById(AccountLogin.class, pk);

		applicationLogService.generic("操作员" + login.getLoginName()
				+ "启用充值卡:成功,充值卡编号:" + rechageCard.getCardCode(), "启用充值卡",
				ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
				ManageContext.LOG_OPT_TYPE_ACC, null);

	}

	@Transactional
	@Override
	public void doDelAll(Long[] messageIds) {

		// 获取当前登录账户
		Long pk = (Long) sessionHelper.get(ManageContext.SESSION_LOGINID);
		AccountLogin login = null;
		if (pk != null)
			login = accountService.getEntityById(AccountLogin.class, pk);
		for(int i=0;i<messageIds.length;i++){
			RechageCard rechageCard=rechageCardDao.getById(messageIds[i]);
			applicationLogService.generic("操作员"+login.getLoginName()+"批量删除充值卡:成功,充值卡编号:" + rechageCard.getCardCode(), "批量删除充值卡",
					ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
					ManageContext.LOG_OPT_TYPE_ACC, null);
		}
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("auditPerson", login.getLoginName());
		map.put("messageIds", messageIds);
		map.put("deleted", "1");
		rechageCardOrderDao.update("RECHAGECARD", map, "BY_BULK_PK");
	}
	
	@Transactional
	@Override
	public void doUseAll(String[] messageIds) {

		// 获取当前登录账户
		Long pk = (Long) sessionHelper.get(ManageContext.SESSION_LOGINID);
		AccountLogin login = null;
		if (pk != null)
			login = accountService.getEntityById(AccountLogin.class, pk);
		for(int i=0;i<messageIds.length;i++){
			RechageCard rechageCard=rechageCardDao.getById(Long.parseLong(messageIds[i]));
			applicationLogService.generic("操作员"+login.getLoginName()+"批量启用充值卡:成功,充值卡编号:" + rechageCard.getCardCode(), "批量启用充值卡",
					ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
					ManageContext.LOG_OPT_TYPE_ACC, null);
		}
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("auditPerson", login.getLoginName());
		map.put("messageIds", messageIds);
		map.put("status", "1");
		rechageCardOrderDao.update("RECHAGECARD", map, "BY_BULK_PK");
	}
	
	
	@Transactional
	@Override
	public void doAuditAll(String[] messageIds,String auditRemark) {
		//获取当前登录账户
		Long pk = (Long)sessionHelper.get(ManageContext.SESSION_LOGINID);
		AccountLogin login = null;
		if (pk != null)
			 login = accountService.getEntityById(AccountLogin.class, pk);
		for(int i=0;i<messageIds.length;i++){
			RechageCard rechageCard=rechageCardDao.getById(Long.parseLong(messageIds[i]));
			applicationLogService.generic("操作员"+login.getLoginName()+"批量审核充值卡:成功,充值卡编号:" + rechageCard.getCardCode(), "批量审核充值卡",
					ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
					ManageContext.LOG_OPT_TYPE_ACC, null);
		}
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("messageIds", messageIds);
		map.put("auditStatus", "1");
		map.put("auditRemark", auditRemark);
		map.put("auditPerson", login.getLoginName());
		rechageCardOrderDao.update("RECHAGECARD", map, "BY_BULK_PK");
		
	
		
		
	
		
	}
	
@Autowired
private MDictService mDictService;
	@Override
	@Transactional
	public void doInsertRechageCard(List<RechageCard> rechageCardList) {
		RechageCard rechageCard=null;
		for(RechageCard item :rechageCardList){
			rechageCard=new RechageCard();
			rechageCard.setCardCode(item.getCardCode());
			rechageCard.setDeleted(false);
			List<RechageCard> list = this
					.findRechageCardDetailList(rechageCard);
			if (null != list && list.size() > 0)
				throw new RuntimeException("充值卡编号"
						+ rechageCard.getCardCode() + "已经存在，新增失败");
			item.setId(rechageCardDao.getNewId());
			//查找来源
			SysDictDetail detail=mDictService.getDetail(item.getSource());
			if(null==detail)
				throw new RuntimeException("编号"+item.getSource()+"来源不存在");
			//查找类型
			SysDictDetail typeDetail=mDictService.getDetail(item.getCardType());
			if(null==typeDetail)
				throw new RuntimeException("编号"+item.getCardType()+"类型不存在");
			rechageCardDao.insert(item);
		}
		
	}
	
	
	@Override
	public List<RechageCard> findRechageCardDetailList(RechageCard rechageCard) {
		return rechageCardDao.queryEntitys("PAGE_M", rechageCard);
	}

}
