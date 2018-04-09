package com.mall.butler.account.model;
import java.util.Date;

import com.mall.butler.model.BaseEntity;

public class RechageCard extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String CARDNAME="cardName";
	public static final String CARDCODE="cardCode";
	public static final String LEFTVALUE="leftValue";
	public static final String STATUS="status";
	public static final String AUDITSYATUS="auditSyatus";
	public static final String REMARK="remark";
	public static final String AUDITREMARK="auditRemark";
	
	public static final String CARDPASSWORD="cardPassword";
	public static final String CARDTYPE="cardType";
	public static final String CREATEYEAR="createYear";
	public static final String FAILURETIME="failureTime";
	public static final String CARDMONEY="cardMoney";

	private String cardName;
	private String cardCode;
	private Double leftValue;
	private Integer status;
	private Integer auditSyatus;
	private String remark;
	private String cardPassword;
	private String cardType;
	private String createYear;
	private Date failureTime;
	private Double cardMoney;
	private String auditPerson;
	private Integer useStatus;
	
	private String source;
	private String auditRemark;
	

	/**
	 *卡片名称
	 */
	public void setCardName(String cardName){
		this.cardName = cardName;
	}
	/**
	 *卡片名称
	 */
	public String getCardName(){
		return this.cardName;
	}
	/**
	 *卡片编号
	 */
	public void setCardCode(String cardCode){
		this.cardCode = cardCode;
	}
	/**
	 *卡片编号
	 */
	public String getCardCode(){
		return this.cardCode;
	}
	/**
	 *卡片金额
	 */
	public void setLeftValue(Double leftValue){
		this.leftValue = leftValue;
	}
	/**
	 *卡片金额
	 */
	public Double getLeftValue(){
		return this.leftValue;
	}
	/**
	 *0:停用 1启用
	 */
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	 *0:停用 1启用
	 */
	public Integer getStatus(){
		return this.status;
	}
	/**
	 *审核状态默认0未审核1审核通过
	 */
	public void setAuditSyatus(Integer auditSyatus){
		this.auditSyatus = auditSyatus;
	}
	/**
	 *审核状态默认0未审核1审核通过
	 */
	public Integer getAuditSyatus(){
		return this.auditSyatus;
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
	 * 卡片密码
	 */
	public String getCardPassword() {
		return cardPassword;
	}
	/**
	 * 卡片密码
	 */
	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}
	/**
	 * 卡片类型
	 */
	public String getCardType() {
		return cardType;
	}
	/**
	 * 卡片类型
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	/**
	 * 创建年份
	 */
	public String getCreateYear() {
		return createYear;
	}
	/**
	 * 创建年份
	 */
	public void setCreateYear(String createYear) {
		this.createYear = createYear;
	}
	/**
	 * 失效时间
	 */
	public Date getFailureTime() {
		return failureTime;
	}
	/**
	 * 失效时间
	 */
	public void setFailureTime(Date failureTime) {
		this.failureTime = failureTime;
	}
	/**
	 * 卡片金额
	 */
	public Double getCardMoney() {
		return cardMoney;
	}
	/**
	 * 卡片金额
	 */
	public void setCardMoney(Double cardMoney) {
		this.cardMoney = cardMoney;
	}
	/**
	 * 审核人
	 */
	public String getAuditPerson() {
		return auditPerson;
	}
	/**
	 * 审核人
	 */
	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}
	/**
	 * 是否使用0未使用1已使用
	 */
	public Integer getUseStatus() {
		return useStatus;
	}
	/**
	 * 是否使用0未使用1已使用
	 */
	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAuditRemark() {
		return auditRemark;
	}
	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}
}