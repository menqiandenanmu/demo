package com.mall.butler.account.m.model;

import com.mall.butler.account.model.TradeAccountDetail;


public class TradeOrderDetailExtend extends TradeAccountDetail {

	private static final long serialVersionUID = 1L;
	private Integer payStatus;//订单的支付状态
	private Integer orderOpType;// 充值订单类型0是手工充值 1是网上充值

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getOrderOpType() {
		return orderOpType;
	}

	public void setOrderOpType(Integer orderOpType) {
		this.orderOpType = orderOpType;
	}

}
