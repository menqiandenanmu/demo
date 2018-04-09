package com.mall.butler.sys.model;

import com.mall.butler.model.BaseEntity;

public class SysRole extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String ROLENAME = "roleName";
	public static final String ROLEACCTYPE = "roleAccType";
	public static final String REMARK = "remark";
	public static final String USEFLAG = "useFlag";
	public static final String ACCOUNTID = "accountId";
	public static final String CREATELOGINID = "createLoginId";

	private String roleName;
	private Integer roleAccType;
	private String remark;
	private Boolean useFlag;
	private Long accountId;
	private Long createLoginId;

	/**
	 *描述具体角色信息
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 *描述具体角色信息
	 */
	public String getRoleName() {
		return this.roleName;
	}

	/**
	 *0:系统管理
	 */
	public void setRoleAccType(Integer roleAccType) {
		this.roleAccType = roleAccType;
	}

	/**
	 *0:系统管理
	 */
	public Integer getRoleAccType() {
		return this.roleAccType;
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

	/**
	 *状态 0:停用 1:启动
	 */
	public void setUseFlag(Boolean useFlag) {
		this.useFlag = useFlag;
	}

	/**
	 *状态 0:停用 1:启动
	 */
	public Boolean getUseFlag() {
		return this.useFlag;
	}

	/**
	 *
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 *
	 */
	public Long getAccountId() {
		return this.accountId;
	}

	/**
	 *
	 */
	public void setCreateLoginId(Long createLoginId) {
		this.createLoginId = createLoginId;
	}

	/**
	 *
	 */
	public Long getCreateLoginId() {
		return this.createLoginId;
	}
}