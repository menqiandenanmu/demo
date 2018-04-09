package com.mall.butler.sys.task;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.dao.ConsumerCouponsDao;
import com.mall.butler.account.m.service.MConsumerCouponsService;
import com.mall.butler.account.model.ConsumerCoupons;

public class CouponExpiredTask {
	private static Logger logger = Logger.getLogger(CouponExpiredTask.class);
	
	@Autowired
	private ConsumerCouponsDao consumerCouponsDao;
	@Autowired
	private MConsumerCouponsService consumerCouponsService;
	/**
	 * 任务执行入口
	 * 
	 * @return
	 */
	public void execute() {
		logger.info("开始赠券过期任务!");
		//查询过期的赠券
		ConsumerCoupons consumerCoupons=new ConsumerCoupons();
		consumerCoupons.setExpiratDate(new Date());
//		consumerCoupons.setCouponStatus(0);
		List<ConsumerCoupons> list=consumerCouponsDao.queryEntitys("EXPIRA", consumerCoupons);
		if(null!=list&&list.size()>0){
			for(ConsumerCoupons conCoupons:list){
				//处理过期赠券及用户赠券
				
				consumerCouponsService.doExpiraCoupon(conCoupons);
			}
		}
		logger.info("结束赠券过期任务!");
		System.out.println("赠券过期任务结束");
	}
}
