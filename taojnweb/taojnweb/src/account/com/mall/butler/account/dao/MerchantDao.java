package com.mall.butler.account.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.Merchant;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;


@Repository
public class MerchantDao extends BaseDao<Merchant, Long>{
	public List<Merchant> find(Merchant merchant) {
		return this.queryEntitys("ALL", merchant);
	}
	
	public Page<Merchant> page(PageRequest<Merchant> request){
		Page<Merchant> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
