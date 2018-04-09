package com.mall.butler.account.model;

import com.mall.butler.model.BaseEntity;

public class AccountLevel extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String LEVELNAME = "levelName";
	public static final String LEVELVALUE = "levelValue";
	public static final String DEFAULTFLAG = "defaultFlag";
	public static final String LEVELACCTYPE = "levelAccType";
	public static final String REMARK = "remark";

	private String levelName;
	private Integer levelValue;
	private Boolean defaultFlag;
	private Integer levelAccType;
	private String remark;

	/**
	 *等级名
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	/**
	 *等级名
	 */
	public String getLevelName() {
		return this.levelName;
	}

	/**
	 *等级
	 */
	public void setLevelValue(Integer levelValue) {
		this.levelValue = levelValue;
	}

	/**
	 *等级
	 */
	public Integer getLevelValue() {
		return this.levelValue;
	}

	/**
	 *
	 */
	public void setDefaultFlag(Boolean defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	/**
	 *
	 */
	public Boolean getDefaultFlag() {
		return this.defaultFlag;
	}

	/**
	 *用户类型0:系统管理 1:普通用户
	 */
	public void setLevelAccType(Integer levelAccType) {
		this.levelAccType = levelAccType;
	}

	/**
	 *用户类型0:系统管理 1:普通用户
	 */
	public Integer getLevelAccType() {
		return this.levelAccType;
	}

	/**
	 *
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 *
	 */
	public String getRemark() {
		return this.remark;
	}
}