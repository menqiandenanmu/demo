package com.mall.butler.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.sys.model.SysDict;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class SysDictDao extends BaseDao<SysDict, Long> {
	public List<SysDict> find(SysDict sysDict) {
		return this.queryEntitys("ALL", sysDict);
	}

	public Page<SysDict> page(PageRequest<SysDict> request) {
		Page<SysDict> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
