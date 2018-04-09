package com.mall.butler.order.service._impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.order.dao.OrderOperateDao;
import com.mall.butler.order.model.OrderInfo;
import com.mall.butler.order.model.OrderOperate;
import com.mall.butler.order.service.OrderService;
import com.mall.butler.service._impl.BaseServiceImpl;

/**
 * @author zhaoxs
 */
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

	@Autowired
	private OrderOperateDao orderOperateDao;

	@Override
	public void doAddOperate(OrderInfo order, Integer operatorType, Date time, String params) {
		OrderOperate op = new OrderOperate();
		op.setId(orderOperateDao.getNewId());
		op.setOrderId(order.getId());
		op.setHungFlag(false);
		op.setOperateTime(time);
		op.setOperateType(operatorType);
		op.setParams(params);
		orderOperateDao.insert(op);
	}

	@Override
	@Transactional
	public void doHungOperate(OrderInfo order) {
		List<OrderOperate> opL = this.queryOperate(order);
		for (OrderOperate oiIndex : opL) {
			oiIndex.setHungFlag(true);
			orderOperateDao.update(oiIndex);
		}
	}

	@Override
	@Transactional
	public void doUnhungOperate(OrderInfo order) {
		List<OrderOperate> opL = this.queryOperate(order);
		for (OrderOperate oiIndex : opL) {
			oiIndex.setHungFlag(false);
			orderOperateDao.update(oiIndex);
		}
	}

	@Override
	public List<OrderOperate> queryOperate(OrderInfo order) {
		OrderOperate filter = new OrderOperate();
		filter.setOrderId(order.getId());
		return orderOperateDao.find(filter);
	}

	@Override
	public OrderOperate queryOperator(OrderInfo order, Integer optype) {
		OrderOperate filter = new OrderOperate();
		filter.setOrderId(order.getId());
		filter.setOperateType(optype);
		List<OrderOperate> opL = orderOperateDao.find(filter);
		return opL.size() > 0 ? opL.get(0) : null;
	}

	@Override
	public void doClearOperate(OrderInfo order) {
		List<OrderOperate> opL = this.queryOperate(order);
		for (OrderOperate oiIndex : opL) {
			orderOperateDao.delete(oiIndex);
		}
	}

	@Override
	public void doDelOperate(OrderOperate operate) {
		orderOperateDao.delete(operate);
	}

	@Override
	public List<OrderOperate> queryExecOperate() {
		return orderOperateDao.queryEntitys("EXECLIST", new Date());
	}
}
