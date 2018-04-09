package com.mall.butler.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.order.model.OrderOperate;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class OrderOperateDao extends BaseDao<OrderOperate, Long> {
	public List<OrderOperate> find(OrderOperate orderOperate) {
		return this.queryEntitys("ALL", orderOperate);
	}

	public Page<OrderOperate> page(PageRequest<OrderOperate> request) {
		Page<OrderOperate> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
