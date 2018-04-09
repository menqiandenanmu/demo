package com.mall.butler.sys.service._impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.dao.AccountInfoDao;
import com.mall.butler.account.dao.AccountRoleDao;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.AccountRole;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.sys.dao.SysFunctionsDao;
import com.mall.butler.sys.dao.SysRoleDao;
import com.mall.butler.sys.dao.SysRoleplugsDao;
import com.mall.butler.sys.model.SysFunctions;
import com.mall.butler.sys.model.SysRole;
import com.mall.butler.sys.model.SysRoleplugs;
import com.mall.butler.sys.service.RoleService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private AccountInfoDao accountInfoDao;
	@Autowired
	private AccountRoleDao accountRoleDao;
	@Autowired
	private SysRoleplugsDao sysRoleplugsDao;
	@Autowired
	private SysFunctionsDao sysFunctionsDao;

	@Override
	public void doAddRole(SysRole role) {
		// 用户名下能用有一个相同的名字权限组
		SysRole param = new SysRole();
		param.setRoleName(role.getRoleName());
		param.setRoleAccType(role.getRoleAccType());
		param.setAccountId(role.getAccountId());
		List<SysRole> roleL = sysRoleDao.find(param);
		if (roleL.size() > 0)
			throw new RuntimeException("角色组名已经被使用!");
		role.setId(sysRoleDao.getNewId());
		sysRoleDao.insert(role);
	}

	@Override
	public void doEditRole(SysRole role) {
		SysRole sysRole = sysRoleDao.getById(role.getId());
		sysRole.setRoleName(role.getRoleName());
		sysRole.setUseFlag(role.getUseFlag());
		sysRole.setRemark(role.getRemark());
		sysRoleDao.update(sysRole);
	}

	@Override
	public void doDeleteRole(SysRole sysRole) {
		sysRoleDao.delete(sysRole);
	}

	@Override
	public Page<SysRole> queryRole(PageRequest<SysRole> pageRequest) {
		return sysRoleDao.findPage(pageRequest);
	}

	@Override
	public List<SysRole> queryRoleSelf(AccountLogin login) {
		AccountInfo account = accountInfoDao.getById(login.getAccountId());
		AccountRole accountRole = new AccountRole();
		accountRole.setOperatorId(login.getId());
		List<AccountRole> aroleL = accountRoleDao.find(accountRole);
		List<SysRole> result = new ArrayList<SysRole>();
		for (AccountRole index : aroleL) {
			SysRole temp = sysRoleDao.getById(index.getRoleId());
			if (account.getAccType().equals(temp.getRoleAccType()))
				result.add(temp);
		}
		return result;
	}

	@Override
	public List<SysRole> queryRoleAssign(AccountLogin login) {
		List<SysRole> result = new ArrayList<SysRole>();
		Map<Long, SysRole> filter = new Hashtable<Long, SysRole>();
		for (SysRole index : queryRoleSelf(login)) {
			filter.put(index.getId(), index);
		}
		for (SysRole index : queryRoleByOwnerType(login)) {
			filter.put(index.getId(), index);
		}
		result.addAll(filter.values());
		return result;
	}

	@Override
	public List<SysRole> queryRoles(SysRole sysRole) {
		return sysRoleDao.find(sysRole);
	}

	@Override
	public List<SysRole> queryRoleByOwnerType(AccountLogin login) {
		AccountInfo account = accountInfoDao.getById(login.getAccountId());
		SysRole role = new SysRole();
		role.setCreateLoginId(login.getId());
		role.setRoleAccType(account.getAccType());
		List<SysRole> result = sysRoleDao.find(role);
		return result;
	}

	@Override
	public void doUpdateLoginRole(AccountLogin login, List<SysRole> roles,
			AccountLogin operator) {
		// 输入权限验证 只能是操作所有的权限才可以被赋值
		Map<Long, SysRole> filter = new Hashtable<Long, SysRole>();
		for (SysRole index : queryRoleAssign(operator)) {
			filter.put(index.getId(), index);
		}
		for (SysRole index : roles) {
			SysRole temp = filter.get(index.getId());
			if (temp == null)
				throw new RuntimeException("输入的权限非法!");
		}
		// 删除原有权限
		AccountRole accountRole = new AccountRole();
		accountRole.setOperatorId(login.getId());
		List<AccountRole> loginRolesL = accountRoleDao.find(accountRole);
		for (AccountRole index : loginRolesL) {
			accountRoleDao.delete(index, true);
		}
		// 插入新的权限
		for (SysRole index : roles) {
			AccountRole temp = new AccountRole();
			temp.setId(accountRoleDao.getNewId());
			temp.setOperatorId(login.getId());
			temp.setRoleId(index.getId());
			accountRoleDao.insert(temp);
		}
	}

	@Override
	public SysFunctions queryFuncByUrl(String url) {
		SysFunctions func = new SysFunctions();
		func.setFunUrl(url);
		List<SysFunctions> funcs = sysFunctionsDao.find(func);
		if (funcs != null && funcs.size() > 0) {
			return funcs.get(0);
		}
		return null;
	}

	@Override
	public boolean checkFunInRole(SysFunctions funcs, AccountLogin login) {
		AccountRole accountRole = new AccountRole();
		accountRole.setOperatorId(login.getId());
		List<AccountRole> roles = accountRoleDao.find(accountRole);
		for (AccountRole index : roles) {
			SysRoleplugs plugs = new SysRoleplugs();
			plugs.setFuncId(funcs.getId());
			plugs.setRoleId(index.getRoleId());
			List<SysRoleplugs> plugsL = sysRoleplugsDao.find(plugs);
			if (plugsL != null && plugsL.size() > 0)
				return true;
		}
		return false;
	}
}
