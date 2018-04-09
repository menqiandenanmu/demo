package com.mall.butler.order.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.order.model.OrderInfo;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class OrderInfoDao extends BaseDao<OrderInfo, Long> {
	public List<OrderInfo> find(OrderInfo orderInfo) {
		return this.queryEntitys("ALL", orderInfo);
	}

	public Page<OrderInfo> page(PageRequest<OrderInfo> request) {
		Page<OrderInfo> result = this.pageQuery(request, "PAGE");
		return result;
	}

	/**
	 * 得到订单号 yyyyMMdd+orderType+订单ID(9)
	 * 
	 * @param orderInfo
	 * @return
	 */
	public String createOrderNo(OrderInfo orderInfo) {
		Long n = orderInfo.getId() % 100000;
		String no = DateUtil.format(new Date(), "yyyyMMdd");
		no = no + String.format("%1$09d", n);
		return no;
	}

	/**
	 * 临时
	 * 
	 * @param orderInfo
	 * @return
	 */
	public String createCheckNo(OrderInfo orderInfo) {
		Long n = orderInfo.getId() % 100000;
		String no = DateUtil.format(new Date(), "yyyyMMdd");
		no = "WT" + no + String.format("%1$09d", n);
		return no;
	}
}