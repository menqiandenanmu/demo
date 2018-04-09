package com.mall.butler.account.model;

import com.mall.butler.model.BaseEntity;

public class AccountRole extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String OPERATORID = "operatorId";
	public static final String ROLEID = "roleId";

	private Long operatorId;
	private Long roleId;

	/**
	 *关联Account_login表中的主键ID
	 */
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 *关联Account_login表中的主键ID
	 */
	public Long getOperatorId() {
		return this.operatorId;
	}

	/**
	 *
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 *
	 */
	public Long getRoleId() {
		return this.roleId;
	}
}