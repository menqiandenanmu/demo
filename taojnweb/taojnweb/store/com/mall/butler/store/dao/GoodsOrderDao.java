package com.mall.butler.store.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.store.model.GoodsOrder;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class GoodsOrderDao extends BaseDao<GoodsOrder, Long> {
	public List<GoodsOrder> find(GoodsOrder goodsOrder) {
		return this.queryEntitys("ALL", goodsOrder);
	}

	public Page<GoodsOrder> page(PageRequest<GoodsOrder> request) {
		Page<GoodsOrder> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
