package com.mall.butler.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.sys.model.SysRoleplugs;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class SysRoleplugsDao extends BaseDao<SysRoleplugs, Long> {
	public List<SysRoleplugs> find(SysRoleplugs sysRoleplugs) {
		return this.queryEntitys("ALL", sysRoleplugs);
	}

	public Page<SysRoleplugs> page(PageRequest<SysRoleplugs> request) {
		Page<SysRoleplugs> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
