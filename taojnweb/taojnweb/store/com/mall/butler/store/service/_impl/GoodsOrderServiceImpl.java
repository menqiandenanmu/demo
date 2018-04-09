package com.mall.butler.store.service._impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.order.dao.OrderInfoDao;
import com.mall.butler.order.model.OrderInfo;
import com.mall.butler.order.model.OrderOperate;
import com.mall.butler.order.service.OrderService;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.store.GoodsContext;
import com.mall.butler.store.dao.GoodsOrderDao;
import com.mall.butler.store.model.GoodsOrder;
import com.mall.butler.store.service.GoodsOrderService;
import com.mall.butler.sys.model.SysArea;
import com.mall.butler.sys.service.AreaService;
import com.mall.util.common.lang.DateUtil;

public class GoodsOrderServiceImpl extends BaseServiceImpl implements GoodsOrderService {
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private GoodsOrderDao goodsOrderDao;
	@Autowired
	private AreaService areaService;
	@Autowired
	private OrderService orderService;

	@Override
	public void doClose(OrderInfo order) {
		// 清除所有任务表操作
		orderService.doClearOperate(order);
		// 更新订单状态
		order.setClosed(true);
		order.setCloseTime(new Date());
		orderInfoDao.update(order);
	}

	@Override
	public void doDestroy(OrderInfo order) {
		GoodsOrder filter = new GoodsOrder();
		filter.setOrderId(order.getId());
		List<GoodsOrder> toL = goodsOrderDao.find(filter);
		for (GoodsOrder toIndex : toL) {
			// 更新记录
			toIndex.setOrderStatus(2);
			goodsOrderDao.update(toIndex);
		}
		order.setOrderStatus(2);
		orderInfoDao.update(order);
	}

	@Override
	public void doPayed(OrderInfo order) {
		if (order.getOrderStatus() != 1)
			throw new RuntimeException("订单状态有误!");
		// 移除订单过期操作
		OrderOperate payOverOp = orderService.queryOperator(order, 0);
		orderService.doDelOperate(payOverOp);
		// 添加订单关闭操作
		orderService.doAddOperate(order, 2, order.getCloseTime(), "");
	}

	@Override
	public void doUpdateOrder(OrderInfo order) {
		OrderInfo orderOld = orderInfoDao.getById(order.getId());
		orderOld.setLinkName(order.getLinkName());
		orderOld.setLinkIdcard(order.getLinkIdcard());
		orderOld.setLinkMobile(order.getLinkMobile());
		orderOld.setAreaCode(order.getAreaCode());
		SysArea area = areaService.findByCode(orderOld.getAreaCode());
		if (area != null)
			orderOld.setAreaName(area.getAreaName());
		orderOld.setRemark(order.getRemark());
		orderInfoDao.update(orderOld);
	}

	@Override
	public void doCancelOrder(OrderInfo order) {
		OrderInfo orderOld = orderInfoDao.getById(order.getId());
		if (orderOld.getOrderStatus() != 1)
			throw new RuntimeException("退订单失败，无效的订单状态!");
		orderOld.setOrderStatus(3);
		orderOld.setFinalSum(order.getFinalSum());
		orderInfoDao.update(orderOld);
	}

	@Override
	public void doAdded(OrderInfo order) {
		// 添加完成后订单插处支付过期操作。
		// 添加订单支付过期操作
		orderService.doAddOperate(order, 0, DateUtil.addDayHmS(order.getCreateTime(), 0,
				GoodsContext.ORDER_PAYOVER_TIME, 0), "");
	}

	@Transactional
	@Override
	public Boolean doCheckPay(OrderInfo order) {
		// 得到景区
		GoodsOrder toFilter = new GoodsOrder();
		toFilter.setOrderId(order.getId());
		List<GoodsOrder> goL = goodsOrderDao.find(toFilter);
		if (goL.size() <= 0)
			throw new RuntimeException("无效的订单!");
		// 更新订单状态
		order.setPayType(0);
		order.setPayStatus(3);
		order.setOrderStatus(1);
		orderInfoDao.update(order);

		// 更新订单明细标志
		for (GoodsOrder goIndex : goL) {
			// 更新记录
			goIndex.setOrderStatus(1);
			goodsOrderDao.update(goIndex);
		}
		// 执行后绪操作
		this.doPayed(order);
		return true;
	}
}
