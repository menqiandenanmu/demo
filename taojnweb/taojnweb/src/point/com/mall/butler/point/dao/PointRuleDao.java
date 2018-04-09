package com.mall.butler.point.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;
import com.mall.butler.point.model.PointRule;


@Repository
public class PointRuleDao extends BaseDao<PointRule, Long>{
	public List<PointRule> find(PointRule pointRule) {
		return this.queryEntitys("ALL", pointRule);
	}
	
	public Page<PointRule> page(PageRequest<PointRule> request){
		Page<PointRule> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
