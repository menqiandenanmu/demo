package com.mall.butler.order.model;

import java.util.Date;

import com.mall.butler.model.BaseEntity;

public class OrderOperate extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String ORDERID = "orderId";
	public static final String OPERATETIME = "operateTime";
	public static final String OPERATETYPE = "operateType";
	public static final String PARAMS = "params";
	public static final String HUNGFLAG = "hungFlag";

	private Long orderId;
	private Date operateTime;
	private Integer operateType;
	private String params;
	private Boolean hungFlag;

	/**
	 *订单主键
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 *订单主键
	 */
	public Long getOrderId() {
		return this.orderId;
	}

	/**
	 *监控时间
	 */
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	/**
	 *监控时间
	 */
	public Date getOperateTime() {
		return this.operateTime;
	}

	/**
	 *0:订单支付过期 1:订单检票过期 2:订单关闭
	 */
	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	/**
	 *0:订单支付过期 1:订单检票过期 2:订单关闭
	 */
	public Integer getOperateType() {
		return this.operateType;
	}

	/**
	 *操作参数
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**
	 *操作参数
	 */
	public String getParams() {
		return this.params;
	}

	/**
	 *0 未挂起 1:挂 起
	 */
	public void setHungFlag(Boolean hungFlag) {
		this.hungFlag = hungFlag;
	}

	/**
	 *0 未挂起 1:挂 起
	 */
	public Boolean getHungFlag() {
		return this.hungFlag;
	}
}