package com.mall.butler.store.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.store.model.GoodsPurchase;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class GoodsPurchaseDao extends BaseDao<GoodsPurchase, Long> {
	public List<GoodsPurchase> find(GoodsPurchase goodsPurchase) {
		return this.queryEntitys("ALL", goodsPurchase);
	}

	public Page<GoodsPurchase> page(PageRequest<GoodsPurchase> request) {
		Page<GoodsPurchase> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
