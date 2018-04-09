package com.mall.butler.store.m.service;

import java.util.List;
import java.util.Map;

import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.order.model.OrderInfo;
import com.mall.butler.service.BaseService;
import com.mall.butler.store.model.GoodsOrder;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 管理后台添加商品订单
 * 
 * @author zhaoxs
 */
public interface MGoodsOrderService extends BaseService {
	public Page<GoodsOrder> findPage(PageRequest<GoodsOrder> request);

	public List<GoodsOrder> findList(GoodsOrder goodsOrder);

	/**
	 * 添加订单
	 * 
	 * @param order
	 * @param goodsOrders
	 */
	public OrderInfo doAddGoodsOrder(OrderInfo order, List<GoodsOrder> goodsOrders);

	/**
	 * 手动订单支付处理，支付成功后order.orderStatus订单标志置1
	 * 
	 * @param order
	 * @param remark
	 * @param login
	 */
	void doManualPayOrder(OrderInfo order, String remark, AccountLogin login);

	/**
	 * 得到订单的订单明细
	 * 
	 * @param order
	 * @return
	 */
	public List<GoodsOrder> queryGoodsOrder(OrderInfo order);

	/**
	 * 得到分页订单明细
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<GoodsOrder> queryGoodsOrder(PageRequest<Map> request);

}
