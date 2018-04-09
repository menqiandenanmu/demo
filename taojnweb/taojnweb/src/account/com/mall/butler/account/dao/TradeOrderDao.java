package com.mall.butler.account.dao;

import com.mall.butler.dao.BaseDao;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.TradeOrder;

import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class TradeOrderDao extends BaseDao<TradeOrder, Long>{
	public List<TradeOrder> find(TradeOrder tradeOrder) {
		return this.queryEntitys("ALL", tradeOrder);
	}
	
	public Page<TradeOrder> page(PageRequest<TradeOrder> request){
		Page<TradeOrder> result=this.pageQuery(request,"PAGE");
		return result;
	}

	/**
	 * 生产充值订单号
	 * @ 
	 * 2013-7-12
	 * @param tOrder
	 * @return
	 */
	public String createOrderNo(TradeOrder tOrder) {
		Long n = tOrder.getId() % 100000;
		String no = DateUtil.format(new Date(), "yyMMdd") + tOrder.getOpType();
		no = "CZ"+no + String.format("%1$07d", n);
		return no;
	}
}
