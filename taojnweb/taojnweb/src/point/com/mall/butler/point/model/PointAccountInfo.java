package com.mall.butler.point.model;
import com.mall.butler.model.BaseEntity;

public class PointAccountInfo extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String ACCNAME="accName";
	public static final String COUNTPOINT="countPoint";
	public static final String POINT="point";

	private String accName;
	private Integer countPoint;
	private Integer point;

	/**
	 *用户名
	 */
	public void setAccName(String accName){
		this.accName = accName;
	}
	/**
	 *用户名
	 */
	public String getAccName(){
		return this.accName;
	}
	/**
	 *累积积分
	 */
	public void setCountPoint(Integer countPoint){
		this.countPoint = countPoint;
	}
	/**
	 *累积积分
	 */
	public Integer getCountPoint(){
		return this.countPoint;
	}
	/**
	 *当前积分
	 */
	public void setPoint(Integer point){
		this.point = point;
	}
	/**
	 *当前积分
	 */
	public Integer getPoint(){
		return this.point;
	}
}