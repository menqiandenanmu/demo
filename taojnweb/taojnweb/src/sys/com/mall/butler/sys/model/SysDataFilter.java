package com.mall.butler.sys.model;

import com.mall.butler.model.BaseEntity;

public class SysDataFilter extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String FILTERTYPE = "filterType";
	public static final String FILTERVALUE = "filterValue";
	public static final String REMARK = "remark";
	public static final String CREATEUSER = "createUser";

	private Integer filterType;
	private String filterValue;
	private String remark;
	private Long createUser;
	/**
	 * 创建用户名称
	 */
	private String createUserName;

	/**
	 *过滤类型(0,手机，1身份证，2ip,3 其他)
	 */
	public void setFilterType(Integer filterType) {
		this.filterType = filterType;
	}

	/**
	 *过滤类型(0,手机，1身份证，，2ip,3 其他)
	 */
	public Integer getFilterType() {
		return this.filterType;
	}

	/**
	 *手机号，或者身份证号
	 */
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	/**
	 *手机号，或者身份证号
	 */
	public String getFilterValue() {
		return this.filterValue;
	}

	/**
	 *简介
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 *简介
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 *创建用户
	 */
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	/**
	 *创建用户
	 */
	public Long getCreateUser() {
		return this.createUser;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

}