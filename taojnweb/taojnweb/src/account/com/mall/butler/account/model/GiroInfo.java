package com.mall.butler.account.model;
import com.mall.butler.model.BaseEntity;

public class GiroInfo extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String LEFTVALUE="leftValue";
	public static final String INLEFTVALUE="inLeftValue";
	public static final String OPLOGINNAME="opLoginName";
	public static final String OPLOGINID="opLoginId";
	public static final String SERIALNO="serialNo";
	public static final String ACCOUNTID2="accountId2";
	public static final String ACCOUNTNAME2="accountName2";
	public static final String REALNAME2="realname2";
	public static final String REALNAME="realname";
	public static final String ACCOUNTID="accountId";
	public static final String ACCOUNTNAME="accountName";
	public static final String TRADEACCID="tradeAccId";
	public static final String TRADEACCNAME="tradeAccName";
	public static final String OPTYPE="opType";
	public static final String ORDERNO="orderNo";
	public static final String OPVALUE="opValue";

	private Double leftValue;
	private Double inLeftValue;
	private String opLoginName;
	private Long opLoginId;
	private String serialNo;
	private Long accountId2;
	private String accountName2;
	private String realname2;
	private String realname;
	private Long accountId;
	private String accountName;
	private Long tradeAccId;
	private String tradeAccName;
	private Integer opType;
	private String orderNo;
	private Double opValue;

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
	 *转入账户余额
	 */
	public void setInLeftValue(Double inLeftValue){
		this.inLeftValue = inLeftValue;
	}
	/**
	 *转入账户余额
	 */
	public Double getInLeftValue(){
		return this.inLeftValue;
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
	 *转入用户ID
	 */
	public void setAccountId2(Long accountId2){
		this.accountId2 = accountId2;
	}
	/**
	 *转入用户ID
	 */
	public Long getAccountId2(){
		return this.accountId2;
	}
	/**
	 *转入用户名
	 */
	public void setAccountName2(String accountName2){
		this.accountName2 = accountName2;
	}
	/**
	 *转入用户名
	 */
	public String getAccountName2(){
		return this.accountName2;
	}
	/**
	 *转入姓名
	 */
	public void setRealname2(String realname2){
		this.realname2 = realname2;
	}
	/**
	 *转入姓名
	 */
	public String getRealname2(){
		return this.realname2;
	}
	/**
	 *转出姓名
	 */
	public void setRealname(String realname){
		this.realname = realname;
	}
	/**
	 *转出姓名
	 */
	public String getRealname(){
		return this.realname;
	}
	/**
	 *代理或分销商id号
	 */
	public void setAccountId(Long accountId){
		this.accountId = accountId;
	}
	/**
	 *代理或分销商id号
	 */
	public Long getAccountId(){
		return this.accountId;
	}
	/**
	 *代理或分销商名称
	 */
	public void setAccountName(String accountName){
		this.accountName = accountName;
	}
	/**
	 *代理或分销商名称
	 */
	public String getAccountName(){
		return this.accountName;
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
	 *0:转账
	 */
	public void setOpType(Integer opType){
		this.opType = opType;
	}
	/**
	 *0:转账
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
}