package com.mall.butler.account.m.service._impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.dao.TradeAccountDao;
import com.mall.butler.account.m.service.TradeAccountListService;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.service._impl.BaseServiceImpl;

public class TradeAccountListServiceImpl extends BaseServiceImpl implements
		TradeAccountListService {
	@Autowired
	private TradeAccountDao tradeAccountDao;
	//用户列表
	@Override
	public List<TradeAccount> getTradeAccountList(TradeAccount tradeAccount){
		return tradeAccountDao.find(tradeAccount);
	}
	//根据ID得到用户

	@Override
	public TradeAccount getTradeAccountById(Long id) {
		
		return tradeAccountDao.getById(id);
	}

	@Override
	public void updateTradeAccount(TradeAccount tradeAccount) {
		tradeAccountDao.update(tradeAccount);
		
	}

}
