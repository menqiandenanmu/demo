package com.mall.butler.account.model;
import com.mall.butler.model.BaseEntity;
import java.util.Date;

public class GiftRecord extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String RULEID="ruleId";
	public static final String ACCOUNTID="accountId";
	public static final String ACCOUNTNAME="accountName";
	public static final String REALNAME="realName";
	public static final String RECHAGEAWAY="rechageAway";
	public static final String TRANSNO="transNo";
	public static final String RECHAGESUM="rechageSum";
	public static final String PRICE="price";
	public static final String PAYTIME="payTime";
	public static final String REMARK2="remark2";
	public static final String REMARK="remark";

	private Long ruleId;
	private Long accountId;
	private String accountName;
	private String realName;
	private String rechageAway;
	private String transNo;
	private Double rechageSum;
	private Double price;
	private Date payTime;
	private String remark2;
	private String remark;

	/**
	 *充值规则ID
	 */
	public void setRuleId(Long ruleId){
		this.ruleId = ruleId;
	}
	/**
	 *充值规则ID
	 */
	public Long getRuleId(){
		return this.ruleId;
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
	 *用户账号
	 */
	public void setAccountName(String accountName){
		this.accountName = accountName;
	}
	/**
	 *用户账号
	 */
	public String getAccountName(){
		return this.accountName;
	}
	/**
	 *姓名
	 */
	public void setRealName(String realName){
		this.realName = realName;
	}
	/**
	 *姓名
	 */
	public String getRealName(){
		return this.realName;
	}
	/**
	 *充值方式:0:微信1:支付宝
	 */
	public void setRechageAway(String rechageAway){
		this.rechageAway = rechageAway;
	}
	/**
	 *充值方式:0:微信1:支付宝
	 */
	public String getRechageAway(){
		return this.rechageAway;
	}
	/**
	 *订单支付交易号
	 */
	public void setTransNo(String transNo){
		this.transNo = transNo;
	}
	/**
	 *订单支付交易号
	 */
	public String getTransNo(){
		return this.transNo;
	}
	/**
	 *充值金额
	 */
	public void setRechageSum(Double rechageSum){
		this.rechageSum = rechageSum;
	}
	/**
	 *充值金额
	 */
	public Double getRechageSum(){
		return this.rechageSum;
	}
	/**
	 *赠券金额
	 */
	public void setPrice(Double price){
		this.price = price;
	}
	/**
	 *赠券金额
	 */
	public Double getPrice(){
		return this.price;
	}
	/**
	 *支付时间
	 */
	public void setPayTime(Date payTime){
		this.payTime = payTime;
	}
	/**
	 *支付时间
	 */
	public Date getPayTime(){
		return this.payTime;
	}
	/**
	 *备注
	 */
	public void setRemark2(String remark2){
		this.remark2 = remark2;
	}
	/**
	 *备注
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
}