package com.mall.butler.account.m.service;

import com.mall.butler.account.model.SendRules;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface MSendRulesService extends BaseService {
	/**
	 * 分页查询
	 * 
	 * @param pageRequest
	 * @return
	 */
	Page<SendRules> pageQuery(PageRequest<SendRules> pageRequest);

	/**
	 * 保存
	 * 
	 * @param sendRules
	 */
	void doSave(SendRules sendRules);

	/**
	 * 更新
	 * 
	 * @param sendRules
	 */
	void doUpdate(SendRules sendRules);

	/**
	 * 删除
	 * 
	 * @param sendRules
	 */
	void doDel(SendRules sendRules);

}
