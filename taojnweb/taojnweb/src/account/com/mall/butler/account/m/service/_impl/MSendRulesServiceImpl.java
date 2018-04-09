package com.mall.butler.account.m.service._impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.dao.SendRulesDao;
import com.mall.butler.account.m.service.MSendRulesService;
import com.mall.butler.account.model.SendRules;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MSendRulesServiceImpl extends BaseServiceImpl implements
		MSendRulesService {

	@Autowired
	private SendRulesDao sendRulesDao;

	@Override
	public Page<SendRules> pageQuery(PageRequest<SendRules> pageRequest) {
		// TODO Auto-generated method stub
		return sendRulesDao.page(pageRequest);
	}

	@Override
	public void doDel(SendRules sendRules) {
		sendRulesDao.delete(sendRules);

	}

	@Override
	public void doSave(SendRules sendRules) {
		// TODO Auto-generated method stub
		sendRules.setId(sendRulesDao.getNewId());
		SendRules obj=new SendRules();
		obj.setSendName(sendRules.getSendName());
		List<SendRules> rules = sendRulesDao.find(obj);
		if(null!=rules&&rules.size()>0)
			throw new RuntimeException("已经存在名称为："+rules.get(0).getSendName()+"的规则");
		sendRulesDao.insert(sendRules);
	}

	@Override
	public void doUpdate(SendRules sendRules) {
		// TODO Auto-generated method stub
		SendRules obj = sendRulesDao.getById(sendRules.getId());
		obj.setActiveCashNum(sendRules.getActiveCashNum());
		obj.setBeginTime(sendRules.getBeginTime());
		obj.setEndTime(sendRules.getEndTime());
		obj.setMaxNum(sendRules.getMaxNum());
		obj.setRemark(sendRules.getRemark());
		obj.setRemark2(sendRules.getRemark2());
		obj.setResourceName(sendRules.getResourceName());
		obj.setSendAmount(sendRules.getSendAmount());
		obj.setSendName(sendRules.getSendName());
		obj.setSpendParam(sendRules.getSpendParam());
		obj.setRuleStatus(sendRules.getRuleStatus());
		obj.setSuperimposedFalg(sendRules.getSuperimposedFalg());
		obj.setTodayCashNum(sendRules.getTodayCashNum());
		obj.setSendSum(sendRules.getSendSum());
		sendRulesDao.update(obj);
	}

}
