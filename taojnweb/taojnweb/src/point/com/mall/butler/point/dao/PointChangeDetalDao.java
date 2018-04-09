package com.mall.butler.point.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.dao.BaseDao;
import com.mall.butler.point.model.PointChangeDetal;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class PointChangeDetalDao extends BaseDao<PointChangeDetal, Long>{
	public List<PointChangeDetal> find(PointChangeDetal pointChangeDetal) {
		return this.queryEntitys("ALL", pointChangeDetal);
	}
	
	public Page<PointChangeDetal> page(PageRequest<PointChangeDetal> request){
		Page<PointChangeDetal> result=this.pageQuery(request,"PAGE");
		return result;
	}
	/**
	 * 得到交易号 yyyyMMdd+orderType+订单ID(9)
	 * @param orderInfo
	 * @return
	 */
	public String createTransNo(PointChangeDetal transInfo) {
		Long n = transInfo.getId() % 100000;
		String no = DateUtil.format(new Date(), "yyyyMMdd");
		no = no + String.format("%1$09d", n);
		return no;
	}
}
