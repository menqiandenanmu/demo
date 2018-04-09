package com.mall.butler.account.model;
import com.mall.butler.model.BaseEntity;
import java.util.Date;

public class CouponUseRecord extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String ACCOUNTID="accountId";
	public static final String ACCOUNTNAME="accountName";
	public static final String REALNAME="realName";
	public static final String RULEID="ruleId";
	public static final String COUPONNAME="couponName";
	public static final String COUPONCODE="couponCode";
	public static final String TRANSNO="transNo";
	public static final String PAYSUM="paySum";
	public static final String PRICE="price";
	public static final String TRADELEFT="tradeLeft";
	public static final String PAYTIME="payTime";
	public static final String COUPONSTATUS="couponStatus";
	public static final String REMARK2="remark2";
	public static final String REMARK="remark";

	private Long accountId;
	private String accountName;
	private String realName;
	private Long ruleId;
	private String couponName;
	private String couponCode;
	private String transNo;
	private Double paySum;
	private Double price;
	private Double tradeLeft;
	private Date payTime;
	private Integer couponStatus;
	private String remark2;
	private String remark;

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
	 *规则id
	 */
	public void setRuleId(Long ruleId){
		this.ruleId = ruleId;
	}
	/**
	 *规则id
	 */
	public Long getRuleId(){
		return this.ruleId;
	}
	/**
	 *券名称
	 */
	public void setCouponName(String couponName){
		this.couponName = couponName;
	}
	/**
	 *券名称
	 */
	public String getCouponName(){
		return this.couponName;
	}
	/**
	 *券编号
	 */
	public void setCouponCode(String couponCode){
		this.couponCode = couponCode;
	}
	/**
	 *券编号
	 */
	public String getCouponCode(){
		return this.couponCode;
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
	 *支付金额
	 */
	public void setPaySum(Double paySum){
		this.paySum = paySum;
	}
	/**
	 *支付金额
	 */
	public Double getPaySum(){
		return this.paySum;
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
	 *钱包余额
	 */
	public void setTradeLeft(Double tradeLeft){
		this.tradeLeft = tradeLeft;
	}
	/**
	 *钱包余额
	 */
	public Double getTradeLeft(){
		return this.tradeLeft;
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
	 *0：未消费:1：已消费:2：已失效
	 */
	public void setCouponStatus(Integer couponStatus){
		this.couponStatus = couponStatus;
	}
	/**
	 *0：未消费:1：已消费:2：已失效
	 */
	public Integer getCouponStatus(){
		return this.couponStatus;
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