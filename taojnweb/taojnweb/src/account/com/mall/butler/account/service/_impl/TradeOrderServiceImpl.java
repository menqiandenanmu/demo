package com.mall.butler.account.service._impl;

import com.mall.butler.service._impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.dao.TradeAccountDao;
import com.mall.butler.account.dao.TradeAccountDetailDao;
import com.mall.butler.account.dao.TradeOrderDao;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.model.TradeAccountDetail;
import com.mall.butler.account.model.TradeOrder;
import com.mall.butler.account.service.TradeOrderService;

public class TradeOrderServiceImpl extends BaseServiceImpl implements TradeOrderService {

	@Autowired
	private TradeOrderDao tradeOrderDao;
	@Autowired
	private TradeAccountDao tradeAccountDao;
	@Autowired
	private TradeAccountDetailDao tradeAccountDetailDao;

	@Override
	public void doPayed(TradeOrder tOrder) {
		
		TradeOrder old=tradeOrderDao.getById(tOrder.getId());
		if(old.getPayStatus()==0){
			old.setPayStatus(1);
			tradeOrderDao.update(old);
		}
	}

	@Override
	public void dAddtorder(TradeOrder tOrder) {
		
		tOrder.setId(tradeOrderDao.getNewId());
		tOrder.setNetpayNo(tradeOrderDao.createOrderNo(tOrder));
		tOrder.setOrderNo(tOrder.getNetpayNo());
		tradeOrderDao.insert(tOrder);
	}

	@Override
	public void doUpdateA(TradeAccount t) {
		tradeAccountDao.update(t);
		
	}

	@Override
	public void doInsert(TradeAccountDetail td) {
		td.setId(tradeAccountDetailDao.getNewId());
		tradeAccountDetailDao.insert(td);
	}
	
}
