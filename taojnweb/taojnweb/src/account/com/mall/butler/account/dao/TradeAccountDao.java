package com.mall.butler.account.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.dao.BaseDao;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository()
public class TradeAccountDao extends BaseDao<TradeAccount, Long>{
	public List<TradeAccount> find(TradeAccount tradeAccount) {
		return this.queryEntitys("ALL", tradeAccount);
	}
	
	public Page<TradeAccount> page(PageRequest<TradeAccount> request){
		Page<TradeAccount> result=this.pageQuery(request,"PAGE");
		return result;
	}
	
	public List<TradeAccount> findByLimit(Map<String,Object> param) {
		return this.queryEntitys("LIMIT", param);
	}
	
	/**
	 * 得到交易号 yyyyMMdd+orderType+订单ID(9)
	 * @param orderInfo
	 * @return
	 */
	public String createTransNo(TradeAccount transInfo) {
		Long n = transInfo.getId() % 100000;
		String no = DateUtil.format(new Date(), "yyyyMMdd");
		no = no + String.format("%1$09d", n);
		return no;
	}
}
