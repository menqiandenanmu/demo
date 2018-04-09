package com.mall.butler.account.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;
import com.mall.butler.account.model.SendRules;


@Repository
public class SendRulesDao extends BaseDao<SendRules, Long>{
	public List<SendRules> find(SendRules sendRules) {
		return this.queryEntitys("ALL", sendRules);
	}
	
	public Page<SendRules> page(PageRequest<SendRules> request){
		Page<SendRules> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
