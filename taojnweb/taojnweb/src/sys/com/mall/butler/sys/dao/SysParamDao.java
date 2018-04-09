package com.mall.butler.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.sys.model.SysParam;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class SysParamDao extends BaseDao<SysParam, Long> {
	public List<SysParam> find(SysParam sysParam) {
		return this.queryEntitys("ALL", sysParam);
	}

	public Page<SysParam> page(PageRequest<SysParam> request) {
		Page<SysParam> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
