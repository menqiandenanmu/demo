package com.mall.butler.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.sys.model.SysImageLibrary;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class SysImageLibraryDao extends BaseDao<SysImageLibrary, Long> {
	public List<SysImageLibrary> find(SysImageLibrary sysImageLibrary) {
		return this.queryEntitys("ALL", sysImageLibrary);
	}

	public Page<SysImageLibrary> page(PageRequest<SysImageLibrary> request) {
		Page<SysImageLibrary> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
