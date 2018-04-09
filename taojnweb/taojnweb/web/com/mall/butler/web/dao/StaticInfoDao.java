package com.mall.butler.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.web.model.StaticInfo;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class StaticInfoDao extends BaseDao<StaticInfo, Long> {
	public List<StaticInfo> find(StaticInfo staticInfo) {
		return this.queryEntitys("ALL", staticInfo);
	}

	public Page<StaticInfo> page(PageRequest<StaticInfo> request) {
		Page<StaticInfo> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
