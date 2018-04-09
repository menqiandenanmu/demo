package com.mall.butler.account.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;
import com.mall.butler.account.model.DunningInfo;


@Repository
public class DunningInfoDao extends BaseDao<DunningInfo, Long>{
	public List<DunningInfo> find(DunningInfo dunningInfo) {
		return this.queryEntitys("ALL", dunningInfo);
	}
	
	public Page<DunningInfo> page(PageRequest<DunningInfo> request){
		Page<DunningInfo> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
