package com.mall.butler.account.model;
import com.mall.butler.model.BaseEntity;

public class AccountBuind extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String ACCOUNTID="accountId";
	public static final String ACCOUNTNAME="accountName";
	public static final String MALLNAME="mallName";
	public static final String MALLCODE="mallCode";
	public static final String REMAKR3="remakr3";
	public static final String REMAKR2="remakr2";
	public static final String REMAKR="remakr";

	private Long accountId;
	private String accountName;
	private String mallName;
	private String mallCode;
	private String remakr3;
	private String remakr2;
	private String remakr;

	/**
	 *用户ID
	 */
	public void setAccountId(Long accountId){
		this.accountId = accountId;
	}
	/**
	 *用户ID
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
	 *商城用户名称
	 */
	public void setMallName(String mallName){
		this.mallName = mallName;
	}
	/**
	 *商城用户名称
	 */
	public String getMallName(){
		return this.mallName;
	}
	/**
	 *商城用户编号
	 */
	public void setMallCode(String mallCode){
		this.mallCode = mallCode;
	}
	/**
	 *商城用户编号
	 */
	public String getMallCode(){
		return this.mallCode;
	}
	/**
	 *备用1
	 */
	public void setRemakr3(String remakr3){
		this.remakr3 = remakr3;
	}
	/**
	 *备用1
	 */
	public String getRemakr3(){
		return this.remakr3;
	}
	/**
	 *备用1
	 */
	public void setRemakr2(String remakr2){
		this.remakr2 = remakr2;
	}
	/**
	 *备用1
	 */
	public String getRemakr2(){
		return this.remakr2;
	}
	/**
	 *说明
	 */
	public void setRemakr(String remakr){
		this.remakr = remakr;
	}
	/**
	 *说明
	 */
	public String getRemakr(){
		return this.remakr;
	}
}