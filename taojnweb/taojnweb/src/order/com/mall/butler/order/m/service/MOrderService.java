package com.mall.butler.order.m.service;

import java.util.List;
import java.util.Map;

import com.mall.butler.order.model.OrderInfo;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface MOrderService extends BaseService {

	/**
	 * 分页查询订单主表
	 * 
	 * @Title:
	 * @auth：caedmon
	 * @date 2013-5-13 下午10:12:30
	 * @return
	 */
	Page<OrderInfo> findPage(PageRequest<OrderInfo> request);

	/**
	 * 得到订单
	 * 
	 * @Title:
	 * @auth：caedmon
	 * @date 2013-5-28 下午10:04:09
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<OrderInfo> queryOrderByMap(PageRequest<Map> request);

	/**
	 * 得到订单集合
	 * 
	 * @Title:
	 * @auth：caedmon
	 * @date 2013-5-28 下午10:03:22
	 * @return
	 */

	List<OrderInfo> getorderList(Map<String, Object> params);
}
