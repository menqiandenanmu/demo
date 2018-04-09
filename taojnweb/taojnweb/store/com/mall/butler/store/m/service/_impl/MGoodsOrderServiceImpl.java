package com.mall.butler.store.m.service._impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.order.model.OrderInfo;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.store.dao.GoodsOrderDao;
import com.mall.butler.store.m.service.MGoodsOrderService;
import com.mall.butler.store.model.GoodsOrder;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MGoodsOrderServiceImpl extends BaseServiceImpl implements MGoodsOrderService {

	@Autowired
	private GoodsOrderDao goodsOrderDao;

	// @Autowired
	// private OrderInfoDao orderInfoDao;
	// @Autowired
	// private GoodsOrderService goodsOrderService;
	// @Autowired
	// private GoodsInfoDao goodsInfoDao;

	@Override
	public Page<GoodsOrder> findPage(PageRequest<GoodsOrder> request) {
		return this.goodsOrderDao.page(request);
	}

	@Override
	public List<GoodsOrder> findList(GoodsOrder goodsOrder) {
		return goodsOrderDao.find(goodsOrder);
	}

	@Override
	@Transactional
	public OrderInfo doAddGoodsOrder(OrderInfo order, List<GoodsOrder> goodsOrders) {
		return order;
	}

	@Override
	@Transactional
	public void doManualPayOrder(OrderInfo order, String remark, AccountLogin login) {
	}

	@Override
	public List<GoodsOrder> queryGoodsOrder(OrderInfo order) {
		GoodsOrder f = new GoodsOrder();
		f.setOrderId(order.getId());
		return goodsOrderDao.find(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<GoodsOrder> queryGoodsOrder(PageRequest<Map> request) {
		return goodsOrderDao.pageQuery(request, "GOODSORDER_M");
	}
}
