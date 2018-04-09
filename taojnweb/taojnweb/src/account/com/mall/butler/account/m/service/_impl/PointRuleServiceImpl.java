package com.mall.butler.account.m.service._impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.ManageContext;
import com.mall.butler.account.m.service.MAccountService;
import com.mall.butler.account.m.service.PointRuleService;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.helper.SessionHelper;
import com.mall.butler.point.dao.PointRuleDao;
import com.mall.butler.point.model.PointRule;
import com.mall.butler.service.ApplicationLogService;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class PointRuleServiceImpl extends BaseServiceImpl implements PointRuleService{
	
	@Autowired
	private PointRuleDao pointRuleDao;
	@Autowired
	private ApplicationLogService applicationLogService;
	@Autowired
	private SessionHelper sessionHelper;
	@Autowired
	protected MAccountService accountService;

	@Override
	public void doAddSave(PointRule pointRule) {
		pointRule.setId(pointRuleDao.getNewId());
		pointRuleDao.insert(pointRule);
		
	}

	@Override
	@Transactional
	public void doDel(PointRule pointRule) {
		pointRule=pointRuleDao.getById(pointRule.getId());
		pointRuleDao.delete(pointRule);
		
		//获取当前登录账户
		Long pk = (Long)sessionHelper.get(ManageContext.SESSION_LOGINID);
		AccountLogin login = null;
		if (pk != null)
			 login = accountService.getEntityById(AccountLogin.class, pk);
		
		applicationLogService.generic("操作员"+login.getLoginName()+"删除积分规则id为:" + pointRule.getId()
				, "删除积分规则",
				ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
				ManageContext.LOG_OPT_TYPE_ACC, null);
	}

	@Override
	public void doUpdate(PointRule pointRule) {
		PointRule pointRuleOld=pointRuleDao.getById(pointRule.getId());

		if(null!=pointRule.getUseStatus()){
			pointRuleOld.setUseStatus(pointRule.getUseStatus());
		}
		if(null!=pointRule.getAmount()){
			pointRuleOld.setAmount(pointRule.getAmount());
			
		}
		if(null!=pointRule.getBnsType()){
			pointRuleOld.setBnsType(pointRule.getBnsType());
			
		}
		if(null!=pointRule.getOpValue()){
			pointRuleOld.setOpValue(pointRule.getOpValue());
		}
		if(null!=pointRule.getRemark()){
			pointRuleOld.setRemark(pointRule.getRemark());
		}
		if(null!=pointRule.getSource()){
			pointRuleOld.setSource(pointRule.getSource());
		}
		if(null!=pointRule.getRemark2())
			pointRuleOld.setRemark2(pointRule.getRemark2());
		pointRuleDao.update(pointRuleOld);
		
	}

	@Override
	public PointRule getEntity(PointRule pointRule) {
		return pointRuleDao.getEntity("ALL", pointRule);
	}

	@Override
	public Page<PointRule> page(PageRequest<PointRule> filter) {
		return pointRuleDao.page(filter);
	}

}
