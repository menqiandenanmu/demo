package com.mall.butler.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.sys.model.SysRole;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class SysRoleDao extends BaseDao<SysRole, Long> {
	public List<SysRole> find(SysRole sysRole) {
		return this.queryEntitys("ALL", sysRole);
	}

	public Page<SysRole> findPage(PageRequest<SysRole> request) {
		Page<SysRole> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
