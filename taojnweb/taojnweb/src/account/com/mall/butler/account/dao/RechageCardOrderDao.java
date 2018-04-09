package com.mall.butler.account.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.RechageCardOrder;
import com.mall.butler.dao.BaseDao;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;


@Repository
public class RechageCardOrderDao extends BaseDao<RechageCardOrder, Long>{
	public List<RechageCardOrder> find(RechageCardOrder rechageCardOrder) {
		return this.queryEntitys("ALL", rechageCardOrder);
	}
	
	public Page<RechageCardOrder> page(PageRequest<RechageCardOrder> request){
		Page<RechageCardOrder> result=this.pageQuery(request,"PAGE");
		return result;
	}
	/**
	 * 得到交易号 yyyyMMdd+orderType+订单ID(9)
	 * @param orderInfo
	 * @return
	 */
	public String createTransNo(RechageCardOrder transInfo) {
		Long n = transInfo.getId() % 100000;
		String no = DateUtil.format(new Date(), "yyyyMMdd");
		no = no + String.format("%1$09d", n);
		return no;
	}
}
