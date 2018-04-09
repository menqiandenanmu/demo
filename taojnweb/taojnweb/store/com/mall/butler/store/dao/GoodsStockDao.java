package com.mall.butler.store.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.store.model.GoodsStock;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class GoodsStockDao extends BaseDao<GoodsStock, Long> {
	public List<GoodsStock> find(GoodsStock goodsStock) {
		return this.queryEntitys("ALL", goodsStock);
	}

	public Page<GoodsStock> page(PageRequest<GoodsStock> request) {
		Page<GoodsStock> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
