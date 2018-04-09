package com.mall.butler.account.m.service;

import java.util.List;

import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.service.BaseService;

public interface TradeAccountListService extends BaseService {
	//用户列表

	public List<TradeAccount> getTradeAccountList(TradeAccount tradeAccount);
	//根据ID得到用户
	public TradeAccount getTradeAccountById(Long id);
		
	public void updateTradeAccount(TradeAccount tradeAccount);
		

}
