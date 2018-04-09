package com.mall.butler.store.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.store.model.GoodsSell;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class GoodsSellDao extends BaseDao<GoodsSell, Long> {
	public List<GoodsSell> find(GoodsSell goodsSell) {
		return this.queryEntitys("ALL", goodsSell);
	}

	public Page<GoodsSell> page(PageRequest<GoodsSell> request) {
		Page<GoodsSell> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
