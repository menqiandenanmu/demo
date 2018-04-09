package com.mall.butler.account.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;
import com.mall.butler.account.model.RechargeInfo;


@Repository
public class RechargeInfoDao extends BaseDao<RechargeInfo, Long>{
	public List<RechargeInfo> find(RechargeInfo rechargeInfo) {
		return this.queryEntitys("ALL", rechargeInfo);
	}
	
	public Page<RechargeInfo> page(PageRequest<RechargeInfo> request){
		Page<RechargeInfo> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
