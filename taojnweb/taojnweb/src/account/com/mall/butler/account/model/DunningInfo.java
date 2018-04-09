package com.mall.butler.account.model;
import com.mall.butler.model.BaseEntity;

public class DunningInfo extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String ACCOUNTID="accountId";
	public static final String ACCOUNTNAME="accountName";
	public static final String CONTENTINFO="contentInfo";
	public static final String REPAYMENTFLAG="repaymentFlag";

	private Long accountId;
	private String accountName;
	private String contentInfo;
	private Integer repaymentFlag;

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
	 *名称
	 */
	public void setAccountName(String accountName){
		this.accountName = accountName;
	}
	/**
	 *名称
	 */
	public String getAccountName(){
		return this.accountName;
	}
	/**
	 *内容
	 */
	public void setContentInfo(String contentInfo){
		this.contentInfo = contentInfo;
	}
	/**
	 *内容
	 */
	public String getContentInfo(){
		return this.contentInfo;
	}
	/**
	 *是否还款
	 */
	public void setRepaymentFlag(Integer repaymentFlag){
		this.repaymentFlag = repaymentFlag;
	}
	/**
	 *是否还款
	 */
	public Integer getRepaymentFlag(){
		return this.repaymentFlag;
	}
}