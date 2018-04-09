package com.mall.butler.account.dao;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.mall.butler.dao.BaseDao;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;
import com.mall.butler.account.model.ConsumerCoupons;


@Repository
public class ConsumerCouponsDao extends BaseDao<ConsumerCoupons, Long>{
	public List<ConsumerCoupons> find(ConsumerCoupons consumerCoupons) {
		return this.queryEntitys("ALL", consumerCoupons);
	}
	
	public Page<ConsumerCoupons> page(PageRequest<ConsumerCoupons> request){
		Page<ConsumerCoupons> result=this.pageQuery(request,"PAGE");
		return result;
	}

	/**
	 * 长度12位ZQ+年月日+四位随机数
	 * @param consumerCoupons
	 * @return
	 */
	public String createCouponNo(ConsumerCoupons consumerCoupons) {
		// TODO Auto-generated method stub
		Long n = consumerCoupons.getId() % 100000;
		String no = DateUtil.format(new Date(), "yyMMdd") ;
		no = "COUPON"+no + String.format("%1$04d", n);
		return no;
	}
}
