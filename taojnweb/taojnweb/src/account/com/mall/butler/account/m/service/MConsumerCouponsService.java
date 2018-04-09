package com.mall.butler.account.m.service;

import java.util.List;
import java.util.Map;

import com.mall.butler.account.model.AccountCoupon;
import com.mall.butler.account.model.ConsumerCoupons;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface MConsumerCouponsService extends BaseService {

	Page<ConsumerCoupons> pageQuery(PageRequest<ConsumerCoupons> pageRequest);

	void doSave(ConsumerCoupons conConsumerCoupons);

	void doUpdate(ConsumerCoupons conConsumerCoupons);

	void doDel(ConsumerCoupons conConsumerCoupons);

	/**
	 * 处理过期的赠券
	 * 
	 * @param conCoupons
	 */
	void doExpiraCoupon(ConsumerCoupons conCoupons);

	/**
	 * 赠券消费统计
	 * 
	 * @param request
	 * @return
	 */
	Page<AccountCoupon> countCoupon(PageRequest<Map<String, Object>> request);

	/**
	 * 查询赠券集合
	 * 
	 * @param map
	 * @return
	 */
	List<AccountCoupon> queryCountCoupon(Map<String, Object> map);

}
