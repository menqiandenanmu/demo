package com.mall.butler.account.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;
import com.mall.butler.account.model.RechageCard;


@Repository
public class RechageCardDao extends BaseDao<RechageCard, Long>{
	public List<RechageCard> find(RechageCard rechageCard) {
		return this.queryEntitys("ALL", rechageCard);
	}
	
	public Page<RechageCard> page(PageRequest<RechageCard> request){
		Page<RechageCard> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
