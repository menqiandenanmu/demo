package com.mall.butler.account.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.StoreAccountDetail;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;


@Repository
public class StoreAccountDetailDao extends BaseDao<StoreAccountDetail, Long>{
	public List<StoreAccountDetail> find(StoreAccountDetail storeAccountDetail) {
		return this.queryEntitys("ALL", storeAccountDetail);
	}
	
	public Page<StoreAccountDetail> page(PageRequest<StoreAccountDetail> request){
		Page<StoreAccountDetail> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
