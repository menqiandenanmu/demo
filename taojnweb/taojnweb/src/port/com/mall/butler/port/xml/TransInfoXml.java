package com.mall.butler.port.xml;

import javax.xml.bind.annotation.XmlRootElement;

import com.mall.butler.xml.BaseXml;
@XmlRootElement(name="transInfo")
public class TransInfoXml extends BaseXml{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8199212287481346469L;
	private String opType;//0:充值 1：消费 2退款
	private Double amount;//金额
	private Double currValue;//账户余额
	private String tradeNo;//交易号
	private String orderNo;//订单号
	private String tradeName;//钱包名称
	private String accountCode;//登录名
	private String orderInfo;//订单详情
	private String transTime;//钱包账户名称
	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Double getCurrValue() {
		return currValue;
	}
	public void setCurrValue(Double currValue) {
		this.currValue = currValue;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
