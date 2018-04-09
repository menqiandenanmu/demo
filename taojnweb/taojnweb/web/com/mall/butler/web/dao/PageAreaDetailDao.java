package com.mall.butler.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.web.model.PageAreaDetail;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class PageAreaDetailDao extends BaseDao<PageAreaDetail, Long> {
	public List<PageAreaDetail> find(PageAreaDetail pageAreaDetail) {
		return this.queryEntitys("ALL", pageAreaDetail);
	}

	public Page<PageAreaDetail> page(PageRequest<PageAreaDetail> request) {
		Page<PageAreaDetail> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
