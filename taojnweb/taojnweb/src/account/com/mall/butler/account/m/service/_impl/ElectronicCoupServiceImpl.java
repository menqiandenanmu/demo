package com.mall.butler.account.m.service._impl;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.ManageContext;
import com.mall.butler.account.dao.AccountLoginDao;
import com.mall.butler.account.dao.ElectronicCoupDao;
import com.mall.butler.account.dao.TradeAccountDao;
import com.mall.butler.account.dao.TradeAccountDetailDao;
import com.mall.butler.account.m.service.ElectronicCoupService;
import com.mall.butler.account.m.service.MAccountService;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.ElectronicCoup;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.model.TradeAccountDetail;
import com.mall.butler.helper.SessionHelper;
import com.mall.butler.service.ApplicationLogService;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.common.TxtUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class ElectronicCoupServiceImpl extends BaseServiceImpl implements
		ElectronicCoupService {
	@Autowired
	private ElectronicCoupDao electronicCoupDao;
	
	@Autowired
	private TradeAccountDetailDao tradeAccountDetailDao;
	
	@Autowired
	private TradeAccountDao tradeAccountDao;
	
	@Autowired
	protected MAccountService accountService;
	@Autowired
	private AccountLoginDao accountLoginDao;
	@Autowired
	private ApplicationLogService applicationLogService;
	@Autowired
	private SessionHelper sessionHelper;

	@Override
	public void doSave(ElectronicCoup electronicCoup) {
		electronicCoup.setId(electronicCoupDao.getNewId());
		//查找用户
		if(null==electronicCoup.getAccountId())
			throw new RuntimeException("客户不能为空");
		TradeAccount tradeAccount=tradeAccountDao.getById(electronicCoup.getAccountId());
		if(null==tradeAccount||tradeAccount.getDeleted())
			throw new RuntimeException("客户信息异常");
		electronicCoup.setAccountId(tradeAccount.getId());
		electronicCoup.setAccountName(tradeAccount.getAccountName());
		if(TxtUtil.isEmpty(electronicCoup.getEletName()))
			throw new RuntimeException("电子券名称不能为空");
		if(TxtUtil.isEmpty(electronicCoup.getEletCode()))
			throw new RuntimeException("电子券编号不能为空");
		if(TxtUtil.isEmpty(electronicCoup.getResource()))
			throw new RuntimeException("来源不能为空");
		if(null==electronicCoup.getLeftValue())
			throw new RuntimeException("金额不能为空");
		electronicCoupDao.insert(electronicCoup);

	}

	@Override
	public Page<ElectronicCoup> page(PageRequest<ElectronicCoup> filter) {
		return electronicCoupDao.page(filter);
	}

	@Override
	public void doUpdate(ElectronicCoup electronicCoup) {
		ElectronicCoup electronicCoupOld = electronicCoupDao
				.getById(electronicCoup.getId());

		if (null != electronicCoup.getAccountName()) {
			electronicCoupOld.setAccountName(electronicCoup.getAccountName());

		}
		if (null != electronicCoup.getAuditId()) {
			electronicCoupOld.setAuditId(electronicCoup.getAuditId());

		}
		if (null != electronicCoup.getAuditName()) {
			electronicCoupOld.setAuditName(electronicCoup.getAuditName());

		}
		if (null != electronicCoup.getAuditSyatus()) {
			electronicCoupOld.setAuditSyatus(electronicCoup.getAuditSyatus());

		}
		if (null != electronicCoup.getRemark()) {
			electronicCoupOld.setRemark(electronicCoup.getRemark());

		}
		if (null != electronicCoup.getEletName()) {
			electronicCoupOld.setEletName(electronicCoup.getEletName());

		}
		if (null != electronicCoup.getEletCode()) {
			electronicCoupOld.setEletCode( electronicCoup.getEletCode());

		}
		if (null != electronicCoup.getLeftValue()) {
			electronicCoupOld.setLeftValue(electronicCoup.getLeftValue());

		}
		
		if (null != electronicCoup.getStatus()) {
			electronicCoupOld.setStatus(electronicCoup.getStatus());

		}
		if (null != electronicCoup.getRechgeTime()) {
			electronicCoupOld.setRechgeTime(electronicCoup.getRechgeTime());

		}
		if (null != electronicCoup.getResource()) {
			electronicCoupOld.setResource(electronicCoup.getResource());

		}
		if (null != electronicCoup.getAccountId()) {
			electronicCoupOld.setAccountId(electronicCoup.getAccountId());

		}
		electronicCoupDao.update(electronicCoupOld);

	}

	@Override
	public ElectronicCoup getEntity(ElectronicCoup electronicCoup) {
		return electronicCoupDao.getEntity("ALL", electronicCoup);

	}

	@Override
	@Transactional
	public void doDel(ElectronicCoup electronicCoup) {
		electronicCoup = electronicCoupDao.getById(electronicCoup.getId());
		electronicCoupDao.delete(electronicCoup);

		//获取当前登录账户
		Long pk = (Long)sessionHelper.get(ManageContext.SESSION_LOGINID);
		AccountLogin login = null;
		if (pk != null)
			 login = accountService.getEntityById(AccountLogin.class, pk);
		
		applicationLogService.generic("操作员"+login.getLoginName()+"删除电子券:" + electronicCoup.getEletName()
				+ "成功,电子券编号:" + electronicCoup.getEletCode(), "删除电子券",
				ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
				ManageContext.LOG_OPT_TYPE_ACC, null);

	}

	@Transactional
	@Override
	public void doAudit(ElectronicCoup electronicCoup) {
		electronicCoup.setRechgeTime(new Date());
		if(electronicCoup.getAuditSyatus().intValue()==0)
			throw new RuntimeException("电子券编号"+electronicCoup.getEletCode()+"未审核不能充值");
		if(electronicCoup.getStatus().intValue()==1)
			throw new RuntimeException("电子券编号"+electronicCoup.getEletCode()+"已经充值不能再次充值");
		this.doUpdate(electronicCoup);
		//获取当前登录账户
		Long pk = (Long)sessionHelper.get(ManageContext.SESSION_LOGINID);
		AccountLogin login = null;
		if (pk != null)
			 login = accountService.getEntityById(AccountLogin.class, pk);
		applicationLogService.generic("操作员"+login.getLoginName()+"审核电子券:成功,电子券:" + electronicCoup.getEletName(), "审核电子券",
				ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
				ManageContext.LOG_OPT_TYPE_ACC, null);
	}

	@Override
	@Transactional
	public void doVerify(ElectronicCoup electronicCoup) {
		//更新账户金额
		electronicCoup=electronicCoupDao.getById(electronicCoup.getId());
		TradeAccount tradeAccount=tradeAccountDao.getById(electronicCoup.getAccountId());
		if(null==tradeAccount)throw new RuntimeException("账户不存在");
		if(!tradeAccount.getStatus())throw new RuntimeException("账户"+tradeAccount.getAccountName()+"已停用充值失败");
		tradeAccount.setLeftValue(tradeAccount.getLeftValue()+electronicCoup.getLeftValue());
		tradeAccount.setCurLeftValue(tradeAccount.getCurLeftValue()+electronicCoup.getLeftValue());
		tradeAccountDao.update(tradeAccount);
		
		// 添加操作明细
		TradeAccountDetail tAccountDetail = new TradeAccountDetail();
		tAccountDetail.setId(tradeAccountDetailDao.getNewId());
		tAccountDetail.setAccountId(tradeAccount.getAccountId());
		tAccountDetail.setAccountName(tradeAccount.getAccountName());
		tAccountDetail.setLeftValue(tradeAccount.getCurLeftValue());
		AccountLogin login=accountLoginDao.getById(electronicCoup.getAuditId());
		tAccountDetail.setOpLoginId(login.getId());
		tAccountDetail.setOpLoginName(login.getLoginName());
		tAccountDetail.setOpType(4);//电子券充值
		tAccountDetail.setOpValue(electronicCoup.getLeftValue());
		tAccountDetail.setOrderNo(tradeAccountDetailDao.createTransNo(tAccountDetail));
		tAccountDetail.setTradeAccId(tradeAccount.getTradeAccId());
		tAccountDetail.setTradeAccName(tradeAccount.getTradeAccName());
		tAccountDetail.setRemark("电子券审核金额变动");
		tAccountDetail.setRemark1(tAccountDetail.getOrderNo());
		tradeAccountDetailDao.insert(tAccountDetail);
		
		
	}
	@Override
	public List<ElectronicCoup> findElectronicCoupDetailList(ElectronicCoup electronicCoup) {
		return electronicCoupDao.queryEntitys("PAGE_M", electronicCoup);
	}

	@Transactional
	@Override
	public void doDelAll(Long[] messageIds) {
		//获取当前登录账户
		Long pk = (Long)sessionHelper.get(ManageContext.SESSION_LOGINID);
		AccountLogin login = null;
		if (pk != null)
			 login = accountService.getEntityById(AccountLogin.class, pk);
		for(int i=0;i<messageIds.length;i++){
			ElectronicCoup electronicCoup=electronicCoupDao.getById(messageIds[i]);
			applicationLogService.generic("操作员"+login.getLoginName()+"批量删除电子券:成功,电子券:" + electronicCoup.getEletName(), "批量删除电子券",
					ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
					ManageContext.LOG_OPT_TYPE_ACC, null);
		}
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("messageIds", messageIds);
		map.put("deleted", "1");
		map.put("auditName", login.getLoginName());
		tradeAccountDetailDao.update("ELECTRONICCOUP", map, "BY_BULK_PK");
	}
	
	@Transactional
	@Override
	public void doUseAll(String[] messageIds) {
		//获取当前登录账户
		Long pk = (Long)sessionHelper.get(ManageContext.SESSION_LOGINID);
		AccountLogin login = null;
		if (pk != null)
			 login = accountService.getEntityById(AccountLogin.class, pk);
		for(int i=0;i<messageIds.length;i++){
			ElectronicCoup electronicCoup=electronicCoupDao.getById(Long.parseLong(messageIds[i]));
			//判断是否已经充值
			if(electronicCoup.getAuditSyatus().intValue()==0)
				throw new RuntimeException("电子券编号"+electronicCoup.getEletCode()+"未审核不能充值");
			if(electronicCoup.getStatus().intValue()==1)
				throw new RuntimeException("电子券编号"+electronicCoup.getEletCode()+"已经充值不能再次充值");
			//账户充值
			doVerify(electronicCoup);
			applicationLogService.generic("操作员"+login.getLoginName()+"电子券批量充值:成功,电子券:" + electronicCoup.getEletName(), "电子券批量充值",
					ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
					ManageContext.LOG_OPT_TYPE_ACC, null);
		}
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("messageIds", messageIds);
		map.put("status", "1");
		map.put("auditName", login.getLoginName());
		tradeAccountDetailDao.update("ELECTRONICCOUP", map, "BY_BULK_PK");
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
			ElectronicCoup electronicCoup=electronicCoupDao.getById(Long.parseLong(messageIds[i]));
			applicationLogService.generic("操作员"+login.getLoginName()+"批量审核电子券:成功,电子券:" + electronicCoup.getEletName(), "批量审核电子券",
					ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
					ManageContext.LOG_OPT_TYPE_ACC, null);
		}
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("messageIds", messageIds);
		map.put("auditStatus", "1");
		map.put("auditRemark", auditRemark);
		map.put(ElectronicCoup.RECHGETIME, new Date());
		map.put("auditName", login.getLoginName());
		tradeAccountDetailDao.update("ELECTRONICCOUP", map, "BY_BULK_PK");
		//审核后充值
		doUseAll(messageIds);
		
		
	
	}
}
