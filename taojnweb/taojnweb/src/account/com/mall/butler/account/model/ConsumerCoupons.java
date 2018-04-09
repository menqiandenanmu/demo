package com.mall.butler.account.model;
import com.mall.butler.model.BaseEntity;
import java.util.Date;

public class ConsumerCoupons extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String RULEID="ruleId";
	public static final String COUPONNAME="couponName";
	public static final String COUPONCODE="couponCode";
	public static final String COUPONNUM="couponNum";
	public static final String PRICE="price";
	public static final String EXPIRATDATE="expiratDate";
	public static final String COUPONSTATUS="couponStatus";
	public static final String REMARK2="remark2";
	public static final String REMARK="remark";

	private Long ruleId;
	private String sendName;
	private String couponName;
	private String couponCode;
	private Integer couponNum;
	private Double price;
	private Date expiratDate;
	private Integer couponStatus;
	private String remark2;
	private String remark;
	/**
	 *满送名称
	 */
	public void setSendName(String sendName){
		this.sendName = sendName;
	}
	/**
	 *满送名称
	 */
	public String getSendName(){
		return this.sendName;
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
	 *库存
	 */
	public void setCouponNum(Integer couponNum){
		this.couponNum = couponNum;
	}
	/**
	 *库存
	 */
	public Integer getCouponNum(){
		return this.couponNum;
	}
	/**
	 *卷消费参数
	 */
	public void setPrice(Double price){
		this.price = price;
	}
	/**
	 *卷消费参数
	 */
	public Double getPrice(){
		return this.price;
	}
	/**
	 *赠券失效时间
	 */
	public void setExpiratDate(Date expiratDate){
		this.expiratDate = expiratDate;
	}
	/**
	 *赠券失效时间
	 */
	public Date getExpiratDate(){
		return this.expiratDate;
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