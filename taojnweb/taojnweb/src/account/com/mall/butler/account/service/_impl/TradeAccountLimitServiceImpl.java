package com.mall.butler.account.service._impl;

import com.mall.butler.service._impl.BaseServiceImpl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.dao.TradeAccountLimitDao;
import com.mall.butler.account.model.TradeAccountLimit;
import com.mall.butler.account.service.TradeAccountLimitService;

import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class TradeAccountLimitServiceImpl extends BaseServiceImpl implements TradeAccountLimitService{

	@Autowired
	private TradeAccountLimitDao tradeAccountLimitDao;
	
	@Override
	public List<TradeAccountLimit> queryByTradeAccountId(Map<String,Object> param) {
		/**
		 * warning 这个sql语句明明不正确是tradeAccountId加上supplyId两个条件进行查询
		 * 所以后来者尽量不要修改sql语句，直接修改sql语句的ID
		 */
		return tradeAccountLimitDao.queryEntitys("TRADEACCOUNTID", param);
	}

	@Override
	public Page<TradeAccountLimit> queryPageTradeAccountInfo(
			PageRequest<TradeAccountLimit> pageRequest) {
		return tradeAccountLimitDao.page(pageRequest);
	}

	@Override
	public void insert(TradeAccountLimit tradeAccountLimit) {
		tradeAccountLimit.setId(tradeAccountLimitDao.getNewId());
		tradeAccountLimitDao.insert(tradeAccountLimit);
	}

	@Override
	public void updateUseFlag(TradeAccountLimit tradeAccountLimit) {
		tradeAccountLimitDao.updateUseFlag(tradeAccountLimit);
	}

}
