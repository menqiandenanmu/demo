package com.mall.butler.account.m.service;

import com.mall.butler.point.model.PointRule;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface PointRuleService extends BaseService{
	/**
	 * 分页查询
	 * @param filter
	 * @return
	 */
	public Page<PointRule> page(PageRequest<PointRule> filter);

	public void doAddSave(PointRule pointRule);

	public void doUpdate(PointRule pointRule);

	public void doDel(PointRule pointRule);

	public PointRule getEntity(PointRule pointRule);

}
