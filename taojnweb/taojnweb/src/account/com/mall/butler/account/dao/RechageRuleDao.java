package com.mall.butler.account.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;
import com.mall.butler.account.model.RechageRule;


@Repository
public class RechageRuleDao extends BaseDao<RechageRule, Long>{
	public List<RechageRule> find(RechageRule rechageRule) {
		return this.queryEntitys("ALL", rechageRule);
	}
	
	public Page<RechageRule> page(PageRequest<RechageRule> request){
		Page<RechageRule> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
