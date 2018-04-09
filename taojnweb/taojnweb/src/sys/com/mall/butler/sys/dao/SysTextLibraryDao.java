package com.mall.butler.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.sys.model.SysTextLibrary;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class SysTextLibraryDao extends BaseDao<SysTextLibrary, Long> {
	public List<SysTextLibrary> find(SysTextLibrary sysTextLibrary) {
		return this.queryEntitys("ALL", sysTextLibrary);
	}

	public Page<SysTextLibrary> page(PageRequest<SysTextLibrary> request) {
		Page<SysTextLibrary> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
