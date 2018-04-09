package com.mall.butler.order.dao;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.mall.butler.order.model.TransInfo;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class TransInfoDao extends BaseDao<TransInfo, Long>{
	public List<TransInfo> find(TransInfo transInfo) {
		return this.queryEntitys("ALL", transInfo);
	}
	
	public Page<TransInfo> page(PageRequest<TransInfo> request){
		Page<TransInfo> result=this.pageQuery(request,"PAGE");
		return result;
	}
	/**
	 * 得到交易号 yyyyMMdd+orderType+订单ID(9)
	 * @param orderInfo
	 * @return
	 */
	public String createTransNo(TransInfo transInfo) {
		Long n = transInfo.getId() % 100000;
		String no = DateUtil.format(new Date(), "yyyyMMdd") + transInfo.getBusinessType();
		no = no + String.format("%1$09d", n);
		return no;
	}

	/**
	 * 得到交易号gateway+ 4位随机数+yyyyMMddhhmmss+4位随机数
	 * @param gateway 
	 * @return
	 */
	public String createTransNo(String gateway) {
		Random random = new Random();
		String rands = "";
		for (int i = 0; i < 3; i++) {
			rands = rands + random.nextInt(10);
		}
		String no =gateway+rands+ DateUtil.format(new Date(), "yyMMddhhmmss");
		String rands2 = "";
		for (int i = 0; i < 2; i++) {
			rands2 = rands2 + random.nextInt(10);
		}
		no = no + rands2;
		return no;
	}
}
