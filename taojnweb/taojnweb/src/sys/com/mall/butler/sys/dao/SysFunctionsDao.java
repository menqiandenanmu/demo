package com.mall.butler.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.sys.model.SysFunctions;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class SysFunctionsDao extends BaseDao<SysFunctions, Long> {
	public List<SysFunctions> find(SysFunctions sysFunctions) {
		return this.queryEntitys("ALL", sysFunctions);
	}

	public Page<SysFunctions> page(PageRequest<SysFunctions> request) {
		Page<SysFunctions> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
