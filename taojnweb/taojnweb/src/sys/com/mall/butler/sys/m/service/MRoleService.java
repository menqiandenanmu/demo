package com.mall.butler.sys.m.service;

import java.util.List;

import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.service.BaseService;
import com.mall.butler.sys.model.SysFunctions;
import com.mall.butler.sys.model.SysRole;

public interface MRoleService extends BaseService {
	/**
	 * 查询name对应Functions
	 * 
	 * @author caedmon
	 * @date 2010-10-15 下午06:59:29
	 * @param name
	 * @return
	 */
	SysFunctions queryFuncByName(String name);

	/**
	 * 查找用户对应功能项
	 * 
	 * @author caedmon
	 * @date 2010-10-19 上午11:07:54
	 * @param login
	 * @return
	 */
	List<SysFunctions> queryFuncByLogin(AccountLogin login);

	/**
	 * 查找权限对应功能项
	 * 
	 * @author caedmon
	 * @date 2010-10-19 上午11:08:20
	 * @param role
	 * @return
	 */
	List<SysFunctions> queryFuncByRole(SysRole role);

	/**
	 * 更新操作员角色
	 * 
	 * @param roles
	 * @param operator
	 */
	void doUpdateOperatorRole(AccountLogin login, List<SysRole> roles);

	/**
	 * 权限组内容修改
	 * 
	 * @author caedmon
	 * @date 2010-10-22 下午11:24:30
	 * @param role
	 * @param funL
	 * @param login
	 */
	void doUpdateRoleFuns(SysRole role, List<SysFunctions> funL, AccountLogin login);

	/**
	 * 超级管理员修改权限组内容
	 * 
	 * @param role
	 * @param funL
	 * @param login
	 */
	void doUpdateAdminRoleFuns(SysRole role, List<SysFunctions> funL);
}
