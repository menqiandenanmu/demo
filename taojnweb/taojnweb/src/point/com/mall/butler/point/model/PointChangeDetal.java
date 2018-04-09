package com.mall.butler.point.model;
import com.mall.butler.model.BaseEntity;

public class PointChangeDetal extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String ACCOUNTID="accountId";
	public static final String OPTYPE="opType";
	public static final String ORDERNO="orderNo";
	public static final String POINT="point";
	public static final String LEFTPOINT="leftPoint";
	public static final String REMARK="remark";
	public static final String LOGINID="loginId";
	public static final String LOGINNAME="loginName";

	private Long accountId;
	private Integer opType;
	private String orderNo;
	private Integer point;
	private Integer leftPoint;
	private String remark;
	private Long loginId;
	private String loginName;

	/**
	 *代理或分销商id号
	 */
	public void setAccountId(Long accountId){
		this.accountId = accountId;
	}
	/**
	 *代理或分销商id号
	 */
	public Long getAccountId(){
		return this.accountId;
	}
	/**
	 *0:订单 1:系统赠送 2:兑换3:消费4：充值//目前只有消费送积分，充值或其他不产生积分，钱包只有淘江南商城产生的消费才有积分积分
	 *积分规则10元1积分，消费9元不产生积分，19元只有一个积分。
	 *
	 */
	public void setOpType(Integer opType){
		this.opType = opType;
	}
	/**
	 *0:订单 1:系统赠送 2:兑换
	 */
	public Integer getOpType(){
		return this.opType;
	}
	/**
	 *业务订单（订票订单号等）
	 */
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}
	/**
	 *业务订单（订票订单号等）
	 */
	public String getOrderNo(){
		return this.orderNo;
	}
	/**
	 *当前操作数量
	 */
	public void setPoint(Integer point){
		this.point = point;
	}
	/**
	 *当前操作数量
	 */
	public Integer getPoint(){
		return this.point;
	}
	/**
	 *账户剩余数量
	 */
	public void setLeftPoint(Integer leftPoint){
		this.leftPoint = leftPoint;
	}
	/**
	 *账户剩余数量
	 */
	public Integer getLeftPoint(){
		return this.leftPoint;
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
	 *操作员ID
	 */
	public void setLoginId(Long loginId){
		this.loginId = loginId;
	}
	/**
	 *操作员ID
	 */
	public Long getLoginId(){
		return this.loginId;
	}
	/**
	 *操作员名
	 */
	public void setLoginName(String loginName){
		this.loginName = loginName;
	}
	/**
	 *操作员名
	 */
	public String getLoginName(){
		return this.loginName;
	}
}