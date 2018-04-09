package com.mall.butler.store.m.service;

import com.mall.butler.service.BaseService;
import com.mall.butler.store.model.GoodsStock;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 类描述：库存管理
 * 类名称：MGoodsStockService
 * 创建人：caedmon
 * 创建时间：2013-5-26 下午12:44:07
 * 修改人：caedmon
 * 修改时间：2013-5-26 下午12:44:07
 * 修改备注：
 * 
 * @version
 */
public interface MGoodsStockService extends BaseService {

	/**
	 * 删除
	 * 
	 * @param goodsStock
	 */
	public void doDel(GoodsStock goodsStock);

	/**
	 * 分页查询
	 * 
	 * @param pageRequest
	 * @return
	 */
	public Page<GoodsStock> findPage(PageRequest<GoodsStock> pageRequest);
}
