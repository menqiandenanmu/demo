package com.mall.butler.sys.service;

import java.util.List;

import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.service.BaseService;
import com.mall.butler.sys.model.SysFunctions;
import com.mall.butler.sys.model.SysRole;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 通用角色操作方法
 * 
 * @author caedmon
 * 
 */
public interface RoleService extends BaseService {

	/**
	 * 查找角色
	 * 
	 * @param sysRole
	 * @return
	 */
	List<SysRole> queryRoles(SysRole sysRole);

	/**
	 * 得到当前用户权限组
	 * 
	 * @author caedmon
	 * @date 2010-10-21 下午07:17:28
	 * @param login
	 * @return
	 */
	List<SysRole> queryRoleSelf(AccountLogin login);

	/**
	 * 分页查找用权限信息
	 * 
	 * @author caedmon
	 * @date 2010-10-22 下午03:59:16
	 * @param pageRequest
	 * @return
	 */
	Page<SysRole> queryRole(PageRequest<SysRole> pageRequest);

	/**
	 * 新建权限组
	 * 
	 * @author caedmon
	 * @date 2010-10-22 下午07:49:11
	 * @param role
	 */
	void doAddRole(SysRole role);

	/**
	 * 更新权限组信息
	 * 
	 * @author caedmon
	 * @date 2010-10-22 下午08:13:29
	 * @param role
	 */
	void doEditRole(SysRole role);

	/**
	 * 删除权限组
	 * 
	 * @author caedmon
	 * @date 2010-10-22 下午08:50:39
	 * @param sysRole
	 */
	void doDeleteRole(SysRole sysRole);

	/**
	 * 得到当前用户可以分配的权限组
	 * 
	 * @author caedmon
	 * @date 2010-10-21 下午07:28:45
	 * @param login
	 * @return
	 */
	List<SysRole> queryRoleAssign(AccountLogin login);

	/**
	 * 得到操作员创建的权限组
	 * 
	 * @author caedmon
	 * @date 2010-10-21 上午09:18:22
	 * @param login
	 * @return
	 */
	List<SysRole> queryRoleByOwnerType(AccountLogin login);

	/**
	 * 更新操作员权限
	 * 
	 * @author caedmon
	 * @date 2010-10-21 下午07:54:40
	 * @param login
	 * @param roles
	 * @param operator
	 */
	void doUpdateLoginRole(AccountLogin login, List<SysRole> roles,
			AccountLogin operator);

	/**
	 * 检查权限和功能
	 * 
	 * @author caedmon
	 * @date 2010-10-16 下午05:28:14
	 * @param funcs
	 * @param login
	 * @return
	 */
	boolean checkFunInRole(SysFunctions funcs, AccountLogin login);

	/**
	 * 查询url对应Functions
	 * 
	 * @author caedmon
	 * @date 2010-10-16 下午03:21:15
	 * @param url
	 * @return
	 */
	SysFunctions queryFuncByUrl(String url);
}
