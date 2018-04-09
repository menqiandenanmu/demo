package com.mall.butler.account.service;


import com.mall.butler.service.BaseService;

import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.model.TradeAccountDetail;
import com.mall.butler.account.model.TradeOrder;

public interface TradeOrderService extends BaseService {

	/**
	 * 充值完成操作
	 * @ 
	 * 2013-7-12
	 * @param tOrder
	 */
	void doPayed(TradeOrder tOrder);
	/**
	 * 生产充值订单
	 * @ 
	 * 2013-7-12
	 * @param tOrder
	 */
	void dAddtorder(TradeOrder tOrder);
	/**
	 * 充值成功账户更新
	 * @ 
	 * 2013-7-12
	 * @param t
	 */
	void doUpdateA(TradeAccount t);
	/**
	 * 充值插入账户明细
	 * @ 
	 * 2013-7-15
	 * @param td
	 */
	void doInsert(TradeAccountDetail td);

}
