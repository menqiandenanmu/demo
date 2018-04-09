package com.mall.butler.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.web.model.NewsInfo;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class NewsInfoDao extends BaseDao<NewsInfo, Long> {
	public List<NewsInfo> find(NewsInfo newsInfo) {
		return this.queryEntitys("ALL", newsInfo);
	}

	public Page<NewsInfo> page(PageRequest<NewsInfo> request) {
		Page<NewsInfo> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
