package com.mall.butler.store.service;

import com.mall.butler.order.model.OrderInfo;
import com.mall.butler.service.BaseService;

public interface GoodsOrderService extends BaseService {
	/**
	 * 检查处理订单是否需要支付
	 * 
	 * @param order
	 * @return
	 */
	Boolean doCheckPay(OrderInfo order);

	/**
	 * 添加完成后操作
	 * 
	 * @param order
	 */
	void doAdded(OrderInfo order);

	/**
	 * 订单取消操作
	 * 
	 * @param order
	 */
	void doDestroy(OrderInfo order);

	/**
	 * 订单结束关闭操作
	 * 
	 * @param order
	 */
	void doClose(OrderInfo order);

	/**
	 * 支付完成后操作
	 * 
	 * @param order
	 */
	void doPayed(OrderInfo order);

	/**
	 * 更新订单
	 * 
	 * @param order
	 */
	void doUpdateOrder(OrderInfo order);

	/**
	 * 退订单
	 * 
	 * @param order
	 */
	void doCancelOrder(OrderInfo order);
}
