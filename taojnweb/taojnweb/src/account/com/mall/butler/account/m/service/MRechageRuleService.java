package com.mall.butler.account.m.service;

import com.mall.butler.account.model.RechageRule;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface MRechageRuleService extends BaseService{

	Page<RechageRule> pageQuery(PageRequest<RechageRule> pageRequest);

	void doSave(RechageRule rechageRule);

	void doUpdate(RechageRule rechageRule);

	void doDel(RechageRule rechageRule);

}
