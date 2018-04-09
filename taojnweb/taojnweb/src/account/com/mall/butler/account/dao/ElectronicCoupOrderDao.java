package com.mall.butler.account.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;
import com.mall.butler.account.model.ElectronicCoupOrder;


@Repository
public class ElectronicCoupOrderDao extends BaseDao<ElectronicCoupOrder, Long>{
	public List<ElectronicCoupOrder> find(ElectronicCoupOrder electronicCoupOrder) {
		return this.queryEntitys("ALL", electronicCoupOrder);
	}
	
	public Page<ElectronicCoupOrder> page(PageRequest<ElectronicCoupOrder> request){
		Page<ElectronicCoupOrder> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
