package com.mall.butler.account.model;
import com.mall.butler.model.BaseEntity;

public class TradeOrder extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String ACCOUNTID="accountId";
	public static final String TRADEACCID="tradeAccId";
	public static final String TRADEACCNAME="tradeAccName";
	public static final String ORDERNO="orderNo";
	public static final String PAYSTATUS="payStatus";
	public static final String OPTYPE="opType";
	public static final String NETPAYNO="netpayNo";
	public static final String OPVALUE="opValue";
	public static final String OPLOGINNAME="opLoginName";
	public static final String OPLOGINID="opLoginId";
	public static final String REMARK="remark";

	private Long accountId;
	private Long tradeAccId;
	private String tradeAccName;
	private String orderNo;
	private Integer payStatus;
	private Integer opType;
	private String netpayNo;
	private Double opValue;
	private String opLoginName;
	private Long opLoginId;
	private String remark;

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
	 *账户类型ID
	 */
	public void setTradeAccId(Long tradeAccId){
		this.tradeAccId = tradeAccId;
	}
	/**
	 *账户类型ID
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
	 *流水号
	 */
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}
	/**
	 *流水号
	 */
	public String getOrderNo(){
		return this.orderNo;
	}
	/**
	 *0:未支付 1:已支付 
	 */
	public void setPayStatus(Integer payStatus){
		this.payStatus = payStatus;
	}
	/**
	 *0:未支付 1:已支付 
	 */
	public Integer getPayStatus(){
		return this.payStatus;
	}
	/**
	 *0:手动充值 1:自助网上充值2.充值卡充值
	 */
	public void setOpType(Integer opType){
		this.opType = opType;
	}
	/**
	 *0:手动充值 1:自助网上充值2.充值卡充值
	 */
	public Integer getOpType(){
		return this.opType;
	}
	/**
	 *网上充值订单号
	 */
	public void setNetpayNo(String netpayNo){
		this.netpayNo = netpayNo;
	}
	/**
	 *网上充值订单号
	 */
	public String getNetpayNo(){
		return this.netpayNo;
	}
	/**
	 *数作数量
	 */
	public void setOpValue(Double opValue){
		this.opValue = opValue;
	}
	/**
	 *数作数量
	 */
	public Double getOpValue(){
		return this.opValue;
	}
	/**
	 *操作员
	 */
	public void setOpLoginName(String opLoginName){
		this.opLoginName = opLoginName;
	}
	/**
	 *操作员
	 */
	public String getOpLoginName(){
		return this.opLoginName;
	}
	/**
	 *操作员ID
	 */
	public void setOpLoginId(Long opLoginId){
		this.opLoginId = opLoginId;
	}
	/**
	 *操作员ID
	 */
	public Long getOpLoginId(){
		return this.opLoginId;
	}
	/**
	 *备注信息
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	 *备注信息
	 */
	public String getRemark(){
		return this.remark;
	}
}