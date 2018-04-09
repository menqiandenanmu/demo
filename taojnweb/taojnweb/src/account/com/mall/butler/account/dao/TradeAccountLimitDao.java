package com.mall.butler.account.dao;

import com.mall.butler.dao.BaseDao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.TradeAccountLimit;

import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class TradeAccountLimitDao extends BaseDao<TradeAccountLimit, Long>{
	public List<TradeAccountLimit> find(TradeAccountLimit tradeAccountLimit) {
		return this.queryEntitys("ALL", tradeAccountLimit);
	}
	
	public Page<TradeAccountLimit> page(PageRequest<TradeAccountLimit> request){
		Page<TradeAccountLimit> result=this.pageQuery(request,"PAGE");
		return result;
	}
	
	public void updateUseFlag(TradeAccountLimit tradeAccountLimit) {
		this.updateWithPath("TRADEACCOUNTLIMIT.UPDATE_ACCOUNTUSEFLAG", tradeAccountLimit);
	}
}
