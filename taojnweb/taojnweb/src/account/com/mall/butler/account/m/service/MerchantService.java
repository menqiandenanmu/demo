package com.mall.butler.account.m.service;

import com.mall.butler.account.model.Merchant;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface MerchantService extends BaseService {

	/**
	 * 分页查询
	 * 
	 * @param filter
	 * @return
	 */
	public Page<Merchant> page(PageRequest<Merchant> filter);

	/**
	 * 新增
	 * 
	 * @param electronicCoup
	 */
	public void doSave(Merchant merchant);

	/**
	 * 修改
	 * 
	 * @param electronicCoup
	 */
	public void doUpdate(Merchant merchant);

	
	/**
	 * 删除
	 * @param merchant
	 */

	public void doDel(Merchant merchant);


}
