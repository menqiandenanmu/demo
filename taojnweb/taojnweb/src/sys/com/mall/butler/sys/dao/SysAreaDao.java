package com.mall.butler.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.sys.model.SysArea;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class SysAreaDao extends BaseDao<SysArea, Long> {
	public List<SysArea> find(SysArea sysArea) {
		return this.queryEntitys("ALL", sysArea);
	}

	public Page<SysArea> page(PageRequest<SysArea> request) {
		Page<SysArea> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
