package com.mall.butler.store.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.store.model.GoodsInfo;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class GoodsInfoDao extends BaseDao<GoodsInfo, Long> {
	public List<GoodsInfo> find(GoodsInfo goodsInfo) {
		return this.queryEntitys("ALL", goodsInfo);
	}

	public Page<GoodsInfo> page(PageRequest<GoodsInfo> request) {
		Page<GoodsInfo> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
