package com.mall.butler.store.w.service;

import java.util.List;
import java.util.Map;

import com.mall.butler.order.model.OrderInfo;
import com.mall.butler.service.BaseService;
import com.mall.butler.store.model.GoodsInfo;
import com.mall.butler.store.model.GoodsOrder;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * @author caedmon
 * @version 创建时间：2013-3-4 下午04:44:16
 */
public interface WGoodsInfoService extends BaseService {

	/**
	 * 查找日期内可以售的商品。有时间线及平日期检正。
	 * 
	 * @param goodStoreId
	 * @param tourDate
	 * @param userType
	 *            0:不限 1：散客 2：团队
	 * @return
	 */
	List<GoodsInfo> queryBookGood(Long goodStoreId, Integer userType);

	/**
	 * 添加商品订单
	 * 
	 * @param order
	 * @param orderL
	 * @return
	 */
	OrderInfo doAddOrder(OrderInfo order, List<GoodsOrder> orderL);

	/**
	 * 分页查询商品
	 * 
	 * @param pageRequest
	 * @return
	 */

	Page<GoodsInfo> queryPage(PageRequest<Map<String, Object>> pageRequest);

}
