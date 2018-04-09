package com.mall.butler.account.model;
import com.mall.butler.model.BaseEntity;

public class RechargeInfo extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String NAME="name";
	public static final String CONTENTINFO="contentInfo";

	private String name;
	private String contentInfo;

	/**
	 *名称
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 *名称
	 */
	public String getName(){
		return this.name;
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
}