package com.mall.butler.port.xml;

import javax.xml.bind.annotation.XmlRootElement;

import com.mall.butler.xml.BaseXml;
@XmlRootElement(name="order")
public class OrderXml extends BaseXml{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8199212287481346469L;
	private String orderNo;//商城订单编号
	private Double paySum;//支付金额
	private String accountCode;//登录名，唯一标识
	private String tradeNo;//交易号
	private String transNo;//交易号
	private String password;//账户密码
	private String orderInfo;//商城用户编号
	private String payOverTime;//钱包账户名称
	private Integer status;//支付状态 0未支付 1支付成功2支付过期
	private Double refundSum;//退款金额
	private String opFlag;//交易代码01消费02 查询
	private String storeCode;//门店编号
	private String operateCode;//操作员编号
	private String posCode;//pos编号
	private String payAmount;//crm支付金额以分为单位
	private String payType;//01电子券支付
	private String refundAmount;//退款
	private String tranDay;//交易日期
	private String tranTime;//交易时间
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Double getPaySum() {
		return paySum;
	}
	public void setPaySum(Double paySum) {
		this.paySum = paySum;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}
	public String getPayOverTime() {
		return payOverTime;
	}
	public void setPayOverTime(String payOverTime) {
		this.payOverTime = payOverTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getRefundSum() {
		return refundSum;
	}
	public void setRefundSum(Double refundSum) {
		this.refundSum = refundSum;
	}
	public String getOpFlag() {
		return opFlag;
	}
	public void setOpFlag(String opFlag) {
		this.opFlag = opFlag;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public String getOperateCode() {
		return operateCode;
	}
	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}
	public String getPosCode() {
		return posCode;
	}
	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getTranDay() {
		return tranDay;
	}
	public void setTranDay(String tranDay) {
		this.tranDay = tranDay;
	}
	public String getTranTime() {
		return tranTime;
	}
	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}
	public String getTransNo() {
		return transNo;
	}
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
	
	
}
