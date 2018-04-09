package com.mall.butler.sys.m.service._impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.dao.AccountRoleDao;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.AccountRole;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.sys.dao.SysFunctionsDao;
import com.mall.butler.sys.dao.SysRoleplugsDao;
import com.mall.butler.sys.m.service.MRoleService;
import com.mall.butler.sys.model.SysFunctions;
import com.mall.butler.sys.model.SysRole;
import com.mall.butler.sys.model.SysRoleplugs;

public class MRoleServiceImpl extends BaseServiceImpl implements MRoleService {
	@Autowired
	private SysFunctionsDao sysFunctionsDao;
	@Autowired
	private AccountRoleDao accountRoleDao;
	@Autowired
	private SysRoleplugsDao sysRoleplugsDao;

	@Override
	public SysFunctions queryFuncByName(String name) {
		SysFunctions func = new SysFunctions();
		func.setFunName(name);
		List<SysFunctions> funcs = sysFunctionsDao.find(func);
		if (funcs != null && funcs.size() > 0) {
			return funcs.get(0);
		}
		return null;
	}

	@Override
	public void doUpdateRoleFuns(SysRole role, List<SysFunctions> funL,
			AccountLogin login) {
		// 输入权限验证 只能是操作所有的权限才可以被赋值
		Map<Long, SysFunctions> filter = new Hashtable<Long, SysFunctions>();
		for (SysFunctions index : this.queryFuncByLogin(login)) {
			filter.put(index.getId(), index);
		}
		for (SysFunctions index : funL) {
			SysFunctions temp = filter.get(index.getId());
			if (temp == null)
				throw new RuntimeException("输入的权限非法!");
		}
		// 删除原有权限
		SysRoleplugs param = new SysRoleplugs();
		param.setRoleId(role.getId());
		List<SysRoleplugs> roleFunL = sysRoleplugsDao.find(param);
		for (SysRoleplugs index : roleFunL) {
			sysRoleplugsDao.delete(index, true);
		}
		// 插入新的权限
		for (SysFunctions index : funL) {
			SysRoleplugs temp = new SysRoleplugs();
			temp.setId(sysRoleplugsDao.getNewId());
			temp.setFuncId(index.getId());
			temp.setRoleId(role.getId());
			sysRoleplugsDao.insert(temp);
		}
	}

	@Override
	public List<SysFunctions> queryFuncByLogin(AccountLogin login) {
		return sysFunctionsDao.queryEntitys("ACCOUNT_FUNCTIONS", login.getId());
	}

	@Override
	public List<SysFunctions> queryFuncByRole(SysRole role) {
		return sysFunctionsDao.queryEntitys("ROLE", role.getId());
	}

	@Override
	public void doUpdateAdminRoleFuns(SysRole role, List<SysFunctions> funL) {
		// TODO Auto-generated method stub
		// 删除原有权限
		SysRoleplugs param = new SysRoleplugs();
		param.setRoleId(role.getId());
		List<SysRoleplugs> roleFunL = sysRoleplugsDao.find(param);
		for (SysRoleplugs index : roleFunL) {
			sysRoleplugsDao.delete(index, true);
		}
		// 插入新的权限
		for (SysFunctions index : funL) {
			SysRoleplugs temp = new SysRoleplugs();
			temp.setId(sysRoleplugsDao.getNewId());
			temp.setFuncId(index.getId());
			temp.setRoleId(role.getId());
			sysRoleplugsDao.insert(temp);
		}
	}

	@Override
	public void doUpdateOperatorRole(AccountLogin login, List<SysRole> roles) {
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
}
