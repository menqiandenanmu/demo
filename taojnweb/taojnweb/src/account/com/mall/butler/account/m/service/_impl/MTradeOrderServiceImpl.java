package com.mall.butler.account.m.service._impl;

import com.mall.butler.service._impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.dao.TradeOrderVoDao;
import com.mall.butler.account.m.model.TradeOrderVo;
import com.mall.butler.account.m.service.MTradeOrderService;

import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MTradeOrderServiceImpl extends BaseServiceImpl implements MTradeOrderService{

	@Autowired
	private TradeOrderVoDao tradeOrderVoDao;

	@Override
	public Page<TradeOrderVo> countTradeOrderBill(
			PageRequest<TradeOrderVo> filter) {
		return tradeOrderVoDao.page(filter);
	}
	
	
}
