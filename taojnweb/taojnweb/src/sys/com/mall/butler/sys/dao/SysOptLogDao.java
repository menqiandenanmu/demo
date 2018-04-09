package com.mall.butler.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.sys.model.SysOptLog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class SysOptLogDao extends BaseDao<SysOptLog, Long> {
	public List<SysOptLog> find(SysOptLog sysOptLog) {
		return this.queryEntitys("ALL", sysOptLog);
	}

	public Page<SysOptLog> page(PageRequest<SysOptLog> request) {
		Page<SysOptLog> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
