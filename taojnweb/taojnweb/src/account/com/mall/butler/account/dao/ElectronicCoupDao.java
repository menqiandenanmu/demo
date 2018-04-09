package com.mall.butler.account.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;
import com.mall.butler.account.model.ElectronicCoup;


@Repository
public class ElectronicCoupDao extends BaseDao<ElectronicCoup, Long>{
	public List<ElectronicCoup> find(ElectronicCoup electronicCoup) {
		return this.queryEntitys("ALL", electronicCoup);
	}
	
	public Page<ElectronicCoup> page(PageRequest<ElectronicCoup> request){
		Page<ElectronicCoup> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
