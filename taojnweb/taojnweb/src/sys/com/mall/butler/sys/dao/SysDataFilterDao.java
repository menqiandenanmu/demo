package com.mall.butler.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.sys.model.SysDataFilter;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class SysDataFilterDao extends BaseDao<SysDataFilter, Long> {
	public List<SysDataFilter> find(SysDataFilter sysDataFilter) {
		return this.queryEntitys("ALL", sysDataFilter);
	}

	public Page<SysDataFilter> page(PageRequest<SysDataFilter> request) {
		Page<SysDataFilter> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
