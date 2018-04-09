package com.mall.butler.order.service;

import java.util.Date;
import java.util.List;

import com.mall.butler.order.model.OrderInfo;
import com.mall.butler.order.model.OrderOperate;
import com.mall.butler.service.BaseService;

/**
 * 订单操作
 * 
 * @author zhaoxs
 */
public interface OrderService extends BaseService {

	/**
	 * 添加订单订时任务操作
	 * 
	 * @param order
	 * @param operatorType
	 *            0:订单支付过期 1:订单检票过期 2:订单关闭
	 * @param time
	 * @param params
	 */
	void doAddOperate(OrderInfo order, Integer operatorType, Date time, String params);

	/**
	 * 挂起订单所有操作
	 * 
	 * @param order
	 */
	void doHungOperate(OrderInfo order);

	/**
	 * 取消订单所有挂起操作
	 * 
	 * @param order
	 */
	void doUnhungOperate(OrderInfo order);

	/**
	 * 查找订单所有未完成的操作
	 * 
	 * @param order
	 * @return
	 */
	List<OrderOperate> queryOperate(OrderInfo order);

	/**
	 * 查找订单指定操作
	 * 
	 * @param order
	 * @param optype
	 *            0:订单支付过期 1:订单检票过期 2:订单关闭
	 * @return
	 */
	OrderOperate queryOperator(OrderInfo order, Integer optype);

	/**
	 * 取消所有订单未完成操作
	 * 
	 * @param order
	 */
	void doClearOperate(OrderInfo order);

	/**
	 * 删 除订单操作
	 * 
	 * @param operate
	 */
	void doDelOperate(OrderOperate operate);

	/**
	 * 得到当前可执行订单操作
	 * 
	 * @return
	 */
	List<OrderOperate> queryExecOperate();
}
