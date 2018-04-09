package com.mall.butler.account.model;

import com.mall.butler.model.BaseEntity;

public class AccountInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String ACCNAME = "accName";
	public static final String ACCNO = "accNo";
	public static final String ACCTYPE = "accType";
	public static final String ACCSTATUS = "accStatus";
	public static final String ACCLEVELID = "accLevelId";

	private String accName;
	private String accNo;
	private Integer accType;
	private Integer accStatus;
	private Long accLevelId;
	private String accLevelName;

	/**
	 *用户名
	 */
	public void setAccName(String accName) {
		this.accName = accName;
	}

	/**
	 *用户名
	 */
	public String getAccName() {
		return this.accName;
	}

	/**
	 *用户编号(唯一)格式化10 Id
	 */
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	/**
	 *用户编号(唯一)格式化10 Id
	 */
	public String getAccNo() {
		return this.accNo;
	}

	/**
	 *用户类型0:系统管理 1:普通用户3:用户用户
	 */
	public void setAccType(Integer accType) {
		this.accType = accType;
	}

	/**
	 *用户类型0:系统管理 1:普通用户3:用户用户
	 */
	public Integer getAccType() {
		return this.accType;
	}

	/**
	 *0:停用 1:正常
	 */
	public void setAccStatus(Integer accStatus) {
		this.accStatus = accStatus;
	}

	/**
	 *0:停用 1:正常
	 */
	public Integer getAccStatus() {
		return this.accStatus;
	}

	/**
	 *账户等级主键
	 */
	public void setAccLevelId(Long accLevelId) {
		this.accLevelId = accLevelId;
	}

	/**
	 *账户等级主键
	 */
	public Long getAccLevelId() {
		return this.accLevelId;
	}

	/**
	 * 等级名称
	 */
	public String getAccLevelName() {
		return accLevelName;
	}

	public void setAccLevelName(String accLevelName) {
		this.accLevelName = accLevelName;
	}

}