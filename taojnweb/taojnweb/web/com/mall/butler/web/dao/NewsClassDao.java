package com.mall.butler.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.web.model.NewsClass;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class NewsClassDao extends BaseDao<NewsClass, Long> {
	public List<NewsClass> find(NewsClass newsClass) {
		return this.queryEntitys("ALL", newsClass);
	}

	public Page<NewsClass> page(PageRequest<NewsClass> request) {
		Page<NewsClass> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
