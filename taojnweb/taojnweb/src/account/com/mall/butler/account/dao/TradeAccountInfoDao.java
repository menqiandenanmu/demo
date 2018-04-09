package com.mall.butler.account.dao;

import com.mall.butler.dao.BaseDao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.TradeAccountInfo;

import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class TradeAccountInfoDao extends BaseDao<TradeAccountInfo, Long>{
	public List<TradeAccountInfo> find(TradeAccountInfo tradeAccountInfo) {
		return this.queryEntitys("ALL", tradeAccountInfo);
	}
	
	public Page<TradeAccountInfo> page(PageRequest<TradeAccountInfo> request){
		Page<TradeAccountInfo> result=this.pageQuery(request,"PAGE");
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<TradeAccountInfo> getTradeAccountInfoList(Map<String, Object> params) {
		return this.queryObjectsWithPath("TRADEACCOUNTINFO.SELECT_TRADEACCOUNTINFO", params);
	}
	
	public void updateUseFlag(TradeAccountInfo tradeAccountInfo) {
		this.updateWithPath("TRADEACCOUNTINFO.UPDATE_ACCOUNTUSEFLAG", tradeAccountInfo);
	}
	
}
