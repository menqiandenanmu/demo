package com.mall.butler.account.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;
import com.mall.butler.account.model.TradeNotifyTask;


@Repository
public class TradeNotifyTaskDao extends BaseDao<TradeNotifyTask, Long>{
	public List<TradeNotifyTask> find(TradeNotifyTask tradeNotifyTask) {
		return this.queryEntitys("ALL", tradeNotifyTask);
	}
	
	public Page<TradeNotifyTask> page(PageRequest<TradeNotifyTask> request){
		Page<TradeNotifyTask> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
