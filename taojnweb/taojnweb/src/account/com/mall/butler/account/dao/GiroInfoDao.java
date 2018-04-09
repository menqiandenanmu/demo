package com.mall.butler.account.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.GiroInfo;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;


@Repository
public class GiroInfoDao extends BaseDao<GiroInfo, Long>{
	public List<GiroInfo> find(GiroInfo giroInfo) {
		return this.queryEntitys("ALL", giroInfo);
	}
	
	public Page<GiroInfo> page(PageRequest<GiroInfo> request){
		Page<GiroInfo> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
