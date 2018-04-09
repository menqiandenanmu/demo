package com.mall.butler.account.model;
import java.util.Date;

import com.mall.butler.model.BaseEntity;

public class DiscountRules extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String STORENAME="storeName";
	public static final String ACCOUNTID="accountId";
	public static final String MINNUM="minNum";
	public static final String MAXNUM="maxNum";
	public static final String DISCOUNTNUM="discountNum";
	public static final String REMARK="remark";
	public static final String RULESTATUS="ruleStatus";
	public static final String BEGINTIME="beginTime";
	public static final String ENDTIME="endTime";

	private String storeName;
	private Long accountId;
	private Double minNum;
	private Double maxNum;
	private Integer discountNum;
	private String remark;
	private Integer ruleStatus;
	private Date beginTime;
	private Date endTime;

	/**
	 *店铺名称
	 */
	public void setStoreName(String storeName){
		this.storeName = storeName;
	}
	/**
	 *店铺名称
	 */
	public String getStoreName(){
		return this.storeName;
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
	 *折扣开始值
	 */
	public void setMinNum(Double minNum){
		this.minNum = minNum;
	}
	/**
	 *折扣开始值
	 */
	public Double getMinNum(){
		return this.minNum;
	}
	/**
	 *折扣结束值
	 */
	public void setMaxNum(Double maxNum){
		this.maxNum = maxNum;
	}
	/**
	 *折扣结束值
	 */
	public Double getMaxNum(){
		return this.maxNum;
	}
	/**
	 *折扣比例
	 */
	public void setDiscountNum(Integer discountNum){
		this.discountNum = discountNum;
	}
	/**
	 *折扣比例
	 */
	public Integer getDiscountNum(){
		return this.discountNum;
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
	/**
	 *0:停用 1启用
	 */
	public void setRuleStatus(Integer ruleStatus){
		this.ruleStatus = ruleStatus;
	}
	/**
	 *0:停用 1启用
	 */
	public Integer getRuleStatus(){
		return this.ruleStatus;
	}
	/**
	 *开始时间
	 */
	public void setBeginTime(Date beginTime){
		this.beginTime = beginTime;
	}
	/**
	 *开始时间
	 */
	public Date getBeginTime(){
		return this.beginTime;
	}
	/**
	 *结束时间
	 */
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	/**
	 *结束时间
	 */
	public Date getEndTime(){
		return this.endTime;
	}
}