package com.mall.butler.account.model;

import com.mall.butler.model.BaseEntity;

public class TradeAccount extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//闈欐?鍙橀噺
	public static final String ACCOUNTNAME="accountName";
	public static final String ACCOUNTID="accountId";
	public static final String LEFTVALUE="leftValue";
	public static final String WARNLINE="warnLine";
	public static final String CURLEFTVALUE="curLeftValue";
	public static final String TRADEACCID="tradeAccId";
	public static final String TRADEACCNAME="tradeAccName";
	public static final String STATUS="status";

	private String accountName;
	private Long accountId;
	private Double leftValue;
	private Double warnLine;
	private Double curLeftValue;
	private Long tradeAccId;
	private String tradeAccName;
	private Boolean status;
	private String tradeType;
	private String tranPassword;//交易密码

	/**
	 *代理或用户名称
	 */
	public void setAccountName(String accountName){
		this.accountName = accountName;
	}
	/**
	 *代理或用户名称
	 */
	public String getAccountName(){
		return this.accountName;
	}
	/**
	 *代理或用户id号
	 */
	public void setAccountId(Long accountId){
		this.accountId = accountId;
	}
	/**
	 *代理或用户id号
	 */
	public Long getAccountId(){
		return this.accountId;
	}
	/**
	 *账户累积余额
	 */
	public void setLeftValue(Double leftValue){
		this.leftValue = leftValue;
	}
	/**
	 *账户累积余额
	 */
	public Double getLeftValue(){
		return this.leftValue;
	}
	/**
	 *信用额度
	 */
	public void setWarnLine(Double warnLine){
		this.warnLine = warnLine;
	}
	/**
	 *信用额度
	 */
	public Double getWarnLine(){
		return this.warnLine;
	}
	/**
	 *虚拟当前账户余额 当前未付款订单金额
	 */
	public void setCurLeftValue(Double curLeftValue){
		this.curLeftValue = curLeftValue;
	}
	/**
	 *虚拟当前账户余额 当前未付款订单金额
	 */
	public Double getCurLeftValue(){
		return this.curLeftValue;
	}
	/**
	 *账户类型I
	 */
	public void setTradeAccId(Long tradeAccId){
		this.tradeAccId = tradeAccId;
	}
	/**
	 *账户类型I
	 */
	public Long getTradeAccId(){
		return this.tradeAccId;
	}
	/**
	 *账户类型名
	 */
	public void setTradeAccName(String tradeAccName){
		this.tradeAccName = tradeAccName;
	}
	/**
	 *账户类型名
	 */
	public String getTradeAccName(){
		return this.tradeAccName;
	}
	/**
	 *0:停用 1启用
	 */
	public void setStatus(Boolean status){
		this.status = status;
	}
	/**
	 *0:停用 1启用
	 */
	public Boolean getStatus(){
		return this.status;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getTranPassword() {
		return tranPassword;
	}
	public void setTranPassword(String tranPassword) {
		this.tranPassword = tranPassword;
	}

	
	
}