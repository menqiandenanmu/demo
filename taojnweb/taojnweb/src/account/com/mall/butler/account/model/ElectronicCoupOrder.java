package com.mall.butler.account.model;
import com.mall.butler.model.BaseEntity;

public class ElectronicCoupOrder extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String ELETNAME="eletName";
	public static final String ELETCODE="eletCode";
	public static final String LEFTVALUE="leftValue";
	public static final String ACCOUNTID="accountId";
	public static final String ACCOUNTNAME="accountName";
	public static final String LOGINID="loginId";
	public static final String LOGINNAME="loginName";
	public static final String ORDERNO="orderNo";
	public static final String REMARK="remark";

	private String eletName;
	private String eletCode;
	private Double leftValue;
	private Long accountId;
	private String accountName;
	private Long loginId;
	private String loginName;
	private String orderNo;
	private String remark;

	/**
	 *名称
	 */
	public void setEletName(String eletName){
		this.eletName = eletName;
	}
	/**
	 *名称
	 */
	public String getEletName(){
		return this.eletName;
	}
	/**
	 *编号
	 */
	public void setEletCode(String eletCode){
		this.eletCode = eletCode;
	}
	/**
	 *编号
	 */
	public String getEletCode(){
		return this.eletCode;
	}
	/**
	 *金额
	 */
	public void setLeftValue(Double leftValue){
		this.leftValue = leftValue;
	}
	/**
	 *金额
	 */
	public Double getLeftValue(){
		return this.leftValue;
	}
	/**
	 *用户id
	 */
	public void setAccountId(Long accountId){
		this.accountId = accountId;
	}
	/**
	 *用户id
	 */
	public Long getAccountId(){
		return this.accountId;
	}
	/**
	 *用户名
	 */
	public void setAccountName(String accountName){
		this.accountName = accountName;
	}
	/**
	 *用户名
	 */
	public String getAccountName(){
		return this.accountName;
	}
	/**
	 *操作员id
	 */
	public void setLoginId(Long loginId){
		this.loginId = loginId;
	}
	/**
	 *操作员id
	 */
	public Long getLoginId(){
		return this.loginId;
	}
	/**
	 *操作员
	 */
	public void setLoginName(String loginName){
		this.loginName = loginName;
	}
	/**
	 *操作员
	 */
	public String getLoginName(){
		return this.loginName;
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