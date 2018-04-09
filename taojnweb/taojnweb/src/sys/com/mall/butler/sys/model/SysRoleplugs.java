package com.mall.butler.sys.model;

import com.mall.butler.model.BaseEntity;

public class SysRoleplugs extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String ROLEID = "roleId";
	public static final String FUNCID = "funcId";

	private Long roleId;
	private Long funcId;

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

	/**
	 *关联系统功能模块表中的ID
	 */
	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

	/**
	 *关联系统功能模块表中的ID
	 */
	public Long getFuncId() {
		return this.funcId;
	}
}