package com.mall.butler.account.m.service._impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.dao.RechageRuleDao;
import com.mall.butler.account.m.service.MRechageRuleService;
import com.mall.butler.account.model.RechageRule;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MRechageRuleServiceImpl extends BaseServiceImpl implements MRechageRuleService{

	@Autowired
	private RechageRuleDao rechageRuleDao;
	@Override
	public void doDel(RechageRule rechageRule) {
		// TODO Auto-generated method stub
		rechageRuleDao.delete(rechageRule);
	}

	@Override
	public void doSave(RechageRule rechageRule) {
		// TODO Auto-generated method stub
		rechageRule.setId(rechageRuleDao.getNewId());
		rechageRuleDao.insert(rechageRule);
	}

	@Override
	public void doUpdate(RechageRule rechageRule) {
		// TODO Auto-generated method stub
		RechageRule obj=rechageRuleDao.getById(rechageRule.getId());
		obj.setCurRechageTime(rechageRule.getCurRechageTime());
		obj.setEndTime(rechageRule.getEndTime());
		obj.setMaxNum(rechageRule.getMaxNum());
		obj.setTodayNum(rechageRule.getTodayNum());
		rechageRuleDao.update(obj);
	}

	@Override
	public Page<RechageRule> pageQuery(PageRequest<RechageRule> pageRequest) {
		// TODO Auto-generated method stub
		return rechageRuleDao.page(pageRequest);
	}

}
