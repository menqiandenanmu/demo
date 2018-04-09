package com.mall.butler.account.model;
import java.util.Date;

import com.mall.butler.model.BaseEntity;

public class StoreAccountDetail extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String SERIALNO="serialNo";
	public static final String ACCOUNTID="accountId";
	public static final String ACCOUNTNAME="accountName";
	public static final String FUKUANNAME="fukuanName";
	public static final String FUKUANID="fukuanId";
	public static final String OPTYPE="opType";
	public static final String ORDERNO="orderNo";
	public static final String OPVALUE="opValue";
	public static final String LEFTVALUE="leftValue";
	public static final String OPLOGINNAME="opLoginName";
	public static final String OPLOGINID="opLoginId";
	public static final String REMARK="remark";

	private String serialNo;
	private Long accountId;
	private String accountName;
	private String fukuanName;
	private Long fukuanId;
	private Integer opType;
	private String orderNo;
	private Double opValue;
	private Double leftValue;
	private String opLoginName;
	private Long opLoginId;
	private String remark;

	private Date begDate;
	private Date endDate;
	
	
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
	/**
	 *流水号
	 */
	public void setSerialNo(String serialNo){
		this.serialNo = serialNo;
	}
	/**
	 *流水号
	 */
	public String getSerialNo(){
		return this.serialNo;
	}
	/**
	 *收款方ID
	 */
	public void setAccountId(Long accountId){
		this.accountId = accountId;
	}
	/**
	 *收款方ID
	 */
	public Long getAccountId(){
		return this.accountId;
	}
	/**
	 *收款方
	 */
	public void setAccountName(String accountName){
		this.accountName = accountName;
	}
	/**
	 *收款方
	 */
	public String getAccountName(){
		return this.accountName;
	}
	/**
	 *付款方
	 */
	public void setFukuanName(String fukuanName){
		this.fukuanName = fukuanName;
	}
	/**
	 *付款方
	 */
	public String getFukuanName(){
		return this.fukuanName;
	}
	/**
	 *付款方ID
	 */
	public void setFukuanId(Long fukuanId){
		this.fukuanId = fukuanId;
	}
	/**
	 *付款方ID
	 */
	public Long getFukuanId(){
		return this.fukuanId;
	}
	/**
	 *0:支付，1：退款
	 */
	public void setOpType(Integer opType){
		this.opType = opType;
	}
	/**
	 *0:支付，1：退款
	 */
	public Integer getOpType(){
		return this.opType;
	}
	/**
	 *业务订单（订票订单号等）
	 */
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}
	/**
	 *业务订单（订票订单号等）
	 */
	public String getOrderNo(){
		return this.orderNo;
	}
	/**
	 *当前操作值
	 */
	public void setOpValue(Double opValue){
		this.opValue = opValue;
	}
	/**
	 *当前操作值
	 */
	public Double getOpValue(){
		return this.opValue;
	}
	/**
	 *账户余额
	 */
	public void setLeftValue(Double leftValue){
		this.leftValue = leftValue;
	}
	/**
	 *账户余额
	 */
	public Double getLeftValue(){
		return this.leftValue;
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
	 *备注
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	 *备注
	 */
	public String getRemark(){
		return this.remark;
	}
}