package com.mall.butler.account.model;
import java.util.Date;

import com.mall.butler.model.BaseEntity;

public class SendRules extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String SENDNAME="sendName";
	public static final String RULESTATUS="ruleStatus";
	public static final String SUPERIMPOSEDFALG="superimposedFalg";
	public static final String RESOURCENAME="resourceName";
	public static final String BEGINTIME="beginTime";
	public static final String ENDTIME="endTime";
	public static final String SENDSUM="sendSum";
	public static final String SENDAMOUNT="sendAmount";
	public static final String MAXNUM="maxNum";
	public static final String TODAYCASHNUM="todayCashNum";
	public static final String ACTIVECASHNUM="activeCashNum";
	public static final String SPENDPARAM="spendParam";
	public static final String REMARK2="remark2";
	public static final String REMARK="remark";

	private String sendName;
	private Integer ruleStatus;
	private Integer superimposedFalg;
	private String resourceName;
	private Date beginTime;
	private Date endTime;
	private Double sendSum;
	private Double sendMaxNum;
	private Integer sendAmount;
	private Integer maxNum;
	private Integer todayCashNum;
	private Integer activeCashNum;
	private Integer spendParam;
	private String remark2;
	private String remark;

	/**
	 *支付满额名称
	 */
	public void setSendName(String sendName){
		this.sendName = sendName;
	}
	/**
	 *支付满额名称
	 */
	public String getSendName(){
		return this.sendName;
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
	 *取值方式:0 最大值，1递增
	 */
	public void setSuperimposedFalg(Integer superimposedFalg){
		this.superimposedFalg = superimposedFalg;
	}
	/**
	 *取值方式:0最大值，1 递增
	 */
	public Integer getSuperimposedFalg(){
		return this.superimposedFalg;
	}
	/**
	 *使用渠道
	 */
	public void setResourceName(String resourceName){
		this.resourceName = resourceName;
	}
	/**
	 *使用渠道
	 */
	public String getResourceName(){
		return this.resourceName;
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
	/**
	 *满额参数
	 */
	public void setSendSum(Double sendSum){
		this.sendSum = sendSum;
	}
	/**
	 *满额参数
	 */
	public Double getSendSum(){
		return this.sendSum;
	}
	/**
	 *赠送参数
	 */
	public void setSendAmount(Integer sendAmount){
		this.sendAmount = sendAmount;
	}
	/**
	 *赠送参数
	 */
	public Integer getSendAmount(){
		return this.sendAmount;
	}
	/**
	 *最大值
	 */
	public void setMaxNum(Integer maxNum){
		this.maxNum = maxNum;
	}
	/**
	 *最大值
	 */
	public Integer getMaxNum(){
		return this.maxNum;
	}
	/**
	 *当日兑换次数
	 */
	public void setTodayCashNum(Integer todayCashNum){
		this.todayCashNum = todayCashNum;
	}
	/**
	 *当日兑换次数
	 */
	public Integer getTodayCashNum(){
		return this.todayCashNum;
	}
	/**
	 *活动期兑换次数
	 */
	public void setActiveCashNum(Integer activeCashNum){
		this.activeCashNum = activeCashNum;
	}
	/**
	 *活动期兑换次数
	 */
	public Integer getActiveCashNum(){
		return this.activeCashNum;
	}
	/**
	 *卷消费参数
	 */
	public void setSpendParam(Integer spendParam){
		this.spendParam = spendParam;
	}
	/**
	 *卷消费参数
	 */
	public Integer getSpendParam(){
		return this.spendParam;
	}
	/**
	 *递增值
	 */
	public void setRemark2(String remark2){
		this.remark2 = remark2;
	}
	/**
	 *递增值
	 */
	public String getRemark2(){
		return this.remark2;
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
	/**
	 * 最高限额
	 * @return
	 */
	public Double getSendMaxNum() {
		return sendMaxNum;
	}
	/**
	 * 最高限额
	 * @return
	 */
	public void setSendMaxNum(Double sendMaxNum) {
		this.sendMaxNum = sendMaxNum;
	}
	
	
}