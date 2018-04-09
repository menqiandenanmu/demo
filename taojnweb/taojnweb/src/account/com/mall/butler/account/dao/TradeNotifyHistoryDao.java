package com.mall.butler.account.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;
import com.mall.butler.account.model.TradeNotifyHistory;


@Repository
public class TradeNotifyHistoryDao extends BaseDao<TradeNotifyHistory, Long>{
	public List<TradeNotifyHistory> find(TradeNotifyHistory tradeNotifyHistory) {
		return this.queryEntitys("ALL", tradeNotifyHistory);
	}
	
	public Page<TradeNotifyHistory> page(PageRequest<TradeNotifyHistory> request){
		Page<TradeNotifyHistory> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
