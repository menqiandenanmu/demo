package com.mall.butler.order.m.service._impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.order.dao.OrderInfoDao;
import com.mall.butler.order.m.service.MOrderService;
import com.mall.butler.order.model.OrderInfo;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MOrderServiceImpl extends BaseServiceImpl implements MOrderService {

	@Autowired
	private OrderInfoDao orderInfoDao;

	@Override
	public Page<OrderInfo> findPage(PageRequest<OrderInfo> request) {
		return this.orderInfoDao.page(request);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<OrderInfo> queryOrderByMap(PageRequest<Map> request) {
		return orderInfoDao.pageQuery(request, "PAGE_M");
	}

	@Override
	public List<OrderInfo> getorderList(Map<String, Object> params) {
		return (List<OrderInfo>) orderInfoDao.queryEntitys("PAGE_M", params);
	}
}
