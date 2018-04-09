package com.mall.butler.account.m.dao;

import com.mall.butler.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.m.model.TradeAccountDetailVo;

import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class TradeAccountDetailVoDao extends BaseDao<TradeAccountDetailVo, Long> {

	public Page<TradeAccountDetailVo> findAccountCount(PageRequest<TradeAccountDetailVo> filter) {
		
		Page<TradeAccountDetailVo> result = this.pageQuery(filter, "PAGE_M");
		return result;
	}

	public Page<TradeAccountDetailVo> finddisAccountCount(PageRequest<TradeAccountDetailVo> filter) {
		Page<TradeAccountDetailVo> result = this.pageQuery(filter, "PAGE_DISM");
		return result;
	}
	
	public Page<TradeAccountDetailVo> finddisAccountCount1(PageRequest<TradeAccountDetailVo> filter) {
		Page<TradeAccountDetailVo> result = this.pageQuery(filter, "PAGE_DISA");
		return result;
	}
	
}
