package com.mall.butler.order.m.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.model.TradeOrder;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.order.service.OrderService;
import com.mall.util.common.TxtUtil;

public class PayOrderAction extends ManageBaseAction {
	private static final long serialVersionUID = 6286195124134047981L;

	// 选择支付网关
	private final String SELECT = "select";
	// 未支付
	private final String UNPAY = "unpay";
	// 无需支付
	private final String NOPAY = "nopay";
	@Autowired
	private OrderService orderService;

	// 订单ID
	private long orderId;
	// 支付网关
	private String payGate;
	// 订单信息
	private TradeOrder order;

	// 支付处理
	public String execute() {
		TradeOrder order = orderService.getEntityById(TradeOrder.class, orderId);
		if (order == null)
			throw new RuntimeException("无效的订单信息!");
		if (order.getPayStatus() == 0) { // 等待支付
			if (TxtUtil.isEmpty(payGate))
				return SELECT;
			else
				return SUCCESS;
		} else {
			return ERROR;
		}
	}


	public String payReturn() {
		order = orderService.getEntityById(TradeOrder.class, orderId);
		if (order == null)
			throw new RuntimeException("无效的订单信息!");
		if (order.getPayStatus() == 0) // 未支付
			return UNPAY;
		else if (order.getPayStatus() == 1) // 支付完成
			return SUCCESS;
		else if (order.getPayStatus() == 2) // 支付过期
			return ERROR;
		else
			// 无需支付
			return NOPAY;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getPayGate() {
		return payGate;
	}

	public void setPayGate(String payGate) {
		this.payGate = payGate;
	}


	public TradeOrder getOrder() {
		return order;
	}


	public void setOrder(TradeOrder order) {
		this.order = order;
	}


}
