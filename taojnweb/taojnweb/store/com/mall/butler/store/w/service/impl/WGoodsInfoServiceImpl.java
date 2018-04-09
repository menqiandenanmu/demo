package com.mall.butler.store.w.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.order.dao.OrderInfoDao;
import com.mall.butler.order.model.OrderInfo;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.store.dao.GoodsInfoDao;
import com.mall.butler.store.dao.GoodsOrderDao;
import com.mall.butler.store.model.GoodsInfo;
import com.mall.butler.store.model.GoodsOrder;
import com.mall.butler.store.w.service.WGoodsInfoService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * @author caedmon
 * @version 创建时间：2013-3-4 下午04:44:37
 */
public class WGoodsInfoServiceImpl extends BaseServiceImpl implements WGoodsInfoService {
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private GoodsOrderDao goodsOrderDao;
	@Autowired
	private GoodsInfoDao goodsInfoDao;

	@Override
	public List<GoodsInfo> queryBookGood(Long goodStoreId, Integer userType) {
		List<GoodsInfo> result = new ArrayList<GoodsInfo>();
		Map<String, Object> filter = new java.util.Hashtable<String, Object>();
		filter.put(GoodsInfo.USEFLAG, true);
		if (userType != null)
			filter.put("userType", userType);
		List<GoodsInfo> goodsInfos = goodsInfoDao.queryEntitys("USEABLED_W", filter);
		for (GoodsInfo info : goodsInfos) {
			// if(this.checkBookTourDate(info,tourDate))
			result.add(info);
		}
		return result;
	}

	@Override
	public Page<GoodsInfo> queryPage(PageRequest<Map<String, Object>> pageRequest) {
		return goodsInfoDao.pageQuery(pageRequest, "PAGE_W");
	}

	@Transactional
	@Override
	public OrderInfo doAddOrder(OrderInfo order, List<GoodsOrder> orderL) {
		order.setId(orderInfoDao.getNewId());
		order.setOrderNo(orderInfoDao.createOrderNo(order));
		order.setOrderStatus(0);
		order.setBookType(0);
		order.setPayType(2);
		order.setPaySum(0.0);
		order.setPayStatus(0);
		Integer indexFlag = 0;
		Integer countNum = 0;
		StringBuffer info = new StringBuffer();
		for (GoodsOrder goodsOrder : orderL) {
			GoodsInfo goodsInfo = goodsInfoDao.getById(goodsOrder.getGoodsId());
			if (goodsOrder.getAmount() != 0) {
				indexFlag = 1;
				goodsOrder.setId(goodsOrderDao.getNewId());
				goodsOrder.setOrderId(order.getId());
				goodsOrder.setBuyerId(order.getBuyerId());
				goodsOrder.setBuyerName(order.getBuyerName());
				goodsOrder.setGoodsId(goodsInfo.getId());
				goodsOrder.setGoodsName(goodsInfo.getGoodsName());
				goodsOrder.setOrderId(order.getId());
				goodsOrder.setOrderNo(order.getOrderNo());
				goodsOrder.setAmount(goodsOrder.getAmount());
				goodsOrder.setPrice(goodsInfo.getSellPrice());
				goodsOrder.setSum(goodsOrder.getAmount() * goodsOrder.getPrice());
				goodsOrder.setOrderStatus(order.getOrderStatus());
				goodsOrder.setRemark("前台订单");
				goodsOrderDao.insert(goodsOrder);
				order.setPaySum(order.getPaySum() + goodsOrder.getSum());
				info.append("[" + goodsOrder.getGoodsName() + "," + goodsOrder.getAmount() + "个]");
				countNum = countNum + goodsOrder.getAmount();
			}
		}
		if (indexFlag == 1) {
			// order.setCountNum(countNum);
			order.setFinalSum(order.getPaySum());
			order.setClosed(false);
			// if (order.getAreaCode() != null) {
			// SysArea area = areaService.findByCode(order.getAreaCode());
			// if (area != null)
			// order.setAreaName(area.getAreaName());
			// }
			order.setInfo(info.toString());
			if (order.getInfo().length() > 1000) {
				order.setInfo(order.getInfo().substring(0, 1000) + "...");
			}
			orderInfoDao.insert(order);
		}

		return order;
	}

}
