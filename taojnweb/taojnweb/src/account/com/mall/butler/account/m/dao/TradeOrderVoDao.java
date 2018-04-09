package com.mall.butler.account.m.dao;

import com.mall.butler.dao.BaseDao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.m.model.TradeOrderVo;

import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class TradeOrderVoDao extends BaseDao<TradeOrderVo,Long>{

	public List<TradeOrderVo> find(TradeOrderVo agentAdvertising) {
		return this.queryEntitys("ALL", agentAdvertising);
	}
	
	public Page<TradeOrderVo> page(PageRequest<TradeOrderVo> request){
		Page<TradeOrderVo> result=this.pageQuery("TRADEORDER", request, "ACCOUNT_PAGE_M");
		return result;
	}
}
