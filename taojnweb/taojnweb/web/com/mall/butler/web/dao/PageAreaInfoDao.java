package com.mall.butler.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.web.model.PageAreaInfo;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class PageAreaInfoDao extends BaseDao<PageAreaInfo, Long> {
	public List<PageAreaInfo> find(PageAreaInfo pageAreaInfo) {
		return this.queryEntitys("ALL", pageAreaInfo);
	}

	public Page<PageAreaInfo> page(PageRequest<PageAreaInfo> request) {
		Page<PageAreaInfo> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
