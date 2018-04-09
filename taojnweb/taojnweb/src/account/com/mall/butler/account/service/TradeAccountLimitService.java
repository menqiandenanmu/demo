package com.mall.butler.account.service;

import com.mall.butler.service.BaseService;
import java.util.List;
import java.util.Map;

import com.mall.butler.account.model.TradeAccountLimit;

import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface TradeAccountLimitService extends BaseService{

	/**
	 * 分页账户类型查询
	 * @param pageRequest
	 * @return
	 */
	public Page<TradeAccountLimit> queryPageTradeAccountInfo(PageRequest<TradeAccountLimit> pageRequest);
	
	/**
	 * 条件查询结果集
	 * @param HashMap
	 * @return 
	 */	
	public List<TradeAccountLimit> queryByTradeAccountId(Map<String, Object> param);
	/**
	 * 新增限制项
	 * 
	 */
	public void insert(TradeAccountLimit tradeAccountLimit);
	/**
	 * 编辑该类型是否生效
	 * @param tradeAccountInfo
	 */
	public void updateUseFlag(TradeAccountLimit tradeAccountLimit);
}
