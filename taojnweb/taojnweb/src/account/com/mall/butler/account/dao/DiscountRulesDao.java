package com.mall.butler.account.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;
import com.mall.butler.account.model.DiscountRules;


@Repository
public class DiscountRulesDao extends BaseDao<DiscountRules, Long>{
	public List<DiscountRules> find(DiscountRules discountRules) {
		return this.queryEntitys("ALL", discountRules);
	}
	
	public Page<DiscountRules> page(PageRequest<DiscountRules> request){
		Page<DiscountRules> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
