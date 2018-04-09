package com.mall.butler.account.model;
import com.mall.butler.model.BaseEntity;
import java.util.Date;

public class ElectronicCoup extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String ACCOUNTNAME="accountName";
	public static final String AUDITID="auditId";
	public static final String AUDITNAME="auditName";
	public static final String AUDITSYATUS="auditSyatus";
	public static final String REMARK="remark";
	public static final String AUDITREMARK="auditRemark";
	public static final String ELETNAME="eletName";
	public static final String ELETCODE="eletCode";
	public static final String LEFTVALUE="leftValue";
	public static final String STATUS="status";
	public static final String RECHGETIME="rechgeTime";
	public static final String RESOURCE="resource";
	public static final String ACCOUNTID="accountId";

	private String accountName;
	private Long auditId;
	private String auditName;
	private Integer auditSyatus;
	private String remark;
	private String auditRemark;
	private String eletName;
	private String eletCode;
	private Double leftValue;
	private Integer status;
	private Date rechgeTime;
	private String resource;
	private Long accountId;

	/**
	 *用户名称
	 */
	public void setAccountName(String accountName){
		this.accountName = accountName;
	}
	/**
	 *用户名称
	 */
	public String getAccountName(){
		return this.accountName;
	}
	/**
	 *审核操作员id
	 */
	public void setAuditId(Long auditId){
		this.auditId = auditId;
	}
	/**
	 *审核操作员id
	 */
	public Long getAuditId(){
		return this.auditId;
	}
	/**
	 *审核操作员
	 */
	public void setAuditName(String auditName){
		this.auditName = auditName;
	}
	/**
	 *审核操作员
	 */
	public String getAuditName(){
		return this.auditName;
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
	 *充值时间
	 */
	public void setRechgeTime(Date rechgeTime){
		this.rechgeTime = rechgeTime;
	}
	/**
	 *充值时间
	 */
	public Date getRechgeTime(){
		return this.rechgeTime;
	}
	/**
	 *来源
	 */
	public void setResource(String resource){
		this.resource = resource;
	}
	/**
	 *来源
	 */
	public String getResource(){
		return this.resource;
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
	public String getAuditRemark() {
		return auditRemark;
	}
	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}
}