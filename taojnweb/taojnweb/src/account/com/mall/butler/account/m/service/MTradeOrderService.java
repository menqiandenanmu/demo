package com.mall.butler.account.m.service;

import com.mall.butler.account.m.model.TradeOrderVo;

import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface MTradeOrderService extends BaseService{

	public Page<TradeOrderVo> countTradeOrderBill(PageRequest<TradeOrderVo> filter);
}
