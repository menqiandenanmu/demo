package com.mall.butler.account.model;

import java.util.Date;

import com.mall.butler.model.BaseEntity;

public class TradeAccountDetail extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量 0:充值 2:订单完成扣款 3:退订单 4:订单返利
	/**
	 * 充值
	 */
	public final static Integer OP_TYPE_CZ = 0;
	/**
	 * 单完成扣款
	 */
	public final static Integer OP_TYPE_WCKK = 2;
	/**
	 * 退订单
	 */
	public final static Integer OP_TYPE_TD = 3;
	/**
	 * 订单返利
	 */
	public final static Integer OP_TYPE_DDFL = 4;
	public static final String ACCOUNTID = "accountId";
	public static final String ACCOUNTNAME = "accountName";
	public static final String TRADEACCID = "tradeAccId";
	public static final String TRADEACCNAME = "tradeAccName";
	public static final String OPTYPE = "opType";
	public static final String ORDERNO = "orderNo";
	public static final String OPVALUE = "opValue";
	public static final String LEFTVALUE = "leftValue";
	public static final String OPLOGINNAME = "opLoginName";
	public static final String OPLOGINID = "opLoginId";

	private Long accountId;
	private String accountName;
	private Long tradeAccId;
	private String tradeAccName;
	private Integer opType;//0:充值 1：消费 2退款3.充值卡充值4.电子券充值5：后台充值6：转账收款7：转账付款
	private String orderNo;
	private String serialNo;//商户名称
	private Double opValue;
	private Double leftValue;
	private Long opLoginId;
	private String opLoginName;
	private Date begDate;
	private Date endDate;
	private Boolean tradeType; //0虚拟账户1授信账户
	
	private String remark;//操作明细
	private String remark1;//备用字段交易号
	private String remark2;//备用字段pos编号
	private String remark3;//备用字段店铺编号
	
	private String rechargeTime;
	private Double couponSum;//优惠券金额
	
	public Date getBegDate() {
		return begDate;
	}
	public void setBegDate(Date begDate) {
		this.begDate = begDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Long getTradeAccId() {
		return tradeAccId;
	}

	public void setTradeAccId(Long tradeAccId) {
		this.tradeAccId = tradeAccId;
	}

	public String getTradeAccName() {
		return tradeAccName;
	}

	public void setTradeAccName(String tradeAccName) {
		this.tradeAccName = tradeAccName;
	}

	public Integer getOpType() {
		return opType;
	}

	public void setOpType(Integer opType) {
		this.opType = opType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getOpValue() {
		return opValue;
	}

	public void setOpValue(Double opValue) {
		this.opValue = opValue;
	}

	public Double getLeftValue() {
		return leftValue;
	}

	public void setLeftValue(Double leftValue) {
		this.leftValue = leftValue;
	}

	public Long getOpLoginId() {
		return opLoginId;
	}

	public void setOpLoginId(Long opLoginId) {
		this.opLoginId = opLoginId;
	}

	public String getOpLoginName() {
		return opLoginName;
	}

	public void setOpLoginName(String opLoginName) {
		this.opLoginName = opLoginName;
	}
	public Boolean getTradeType() {
		return tradeType;
	}
	public void setTradeType(Boolean tradeType) {
		this.tradeType = tradeType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 交易号
	 * @return
	 */
	public String getRemark1() {
		return remark1;
	}
	/**
	 * 交易号
	 * @return
	 */
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public String getRemark3() {
		return remark3;
	}
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	public String getRechargeTime() {
		return rechargeTime;
	}
	public void setRechargeTime(String rechargeTime) {
		this.rechargeTime = rechargeTime;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public Double getCouponSum() {
		return couponSum;
	}
	public void setCouponSum(Double couponSum) {
		this.couponSum = couponSum;
	}
	

}