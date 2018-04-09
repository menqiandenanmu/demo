package com.mall.butler.account.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.TradeAccountDetail;
import com.mall.butler.dao.BaseDao;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class TradeAccountDetailDao extends BaseDao<TradeAccountDetail, Long>{
	public List<TradeAccountDetail> find(TradeAccountDetail tradeAccountDetail) {
		return this.queryEntitys("ALL", tradeAccountDetail);
	}
	
	public Page<TradeAccountDetail> page(PageRequest<TradeAccountDetail> request){
		Page<TradeAccountDetail> result=this.pageQuery(request,"PAGE");
		return result;
	}
	/**
	 * 得到交易号 yyyyMMdd+orderType+订单ID(9)
	 * @param orderInfo
	 * @return
	 */
	public String createTransNo(TradeAccountDetail transInfo) {
		Long n = transInfo.getId() % 100000;
		String no = DateUtil.format(new Date(), "yyyyMMdd");
		no = no + String.format("%1$09d", n);
		return no;
	}
	public Page<TradeAccountDetail> findTradeAccountDetail(PageRequest<TradeAccountDetail> filter) {
		Page<TradeAccountDetail> result=this.pageQuery("TRADEACCOUNTDETAIL", filter, "FINDACCOUNTDETAIL");
		return result;
	}

	public Page<TradeAccountDetail> finddisTradeAccountDetail(PageRequest<TradeAccountDetail> filter) {
		Page<TradeAccountDetail> result=this.pageQuery("TRADEACCOUNTDETAIL", filter, "FINDDISACCOUNTDETAIL");
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<TradeAccountDetail> getAgentAccountList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return this.queryObjectsWithPath("TRADEACCOUNTDETAIL.SELECT_FINDACCOUNTDETAIL", params);
	}

	@SuppressWarnings("unchecked")
	public List<TradeAccountDetail> getdisAgentAccountList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return this.queryObjectsWithPath("TRADEACCOUNTDETAIL.SELECT_FINDDISACCOUNTDETAIL", params);
	}

}
