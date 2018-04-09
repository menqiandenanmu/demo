package com.mall.butler.account.m.service;

import java.util.List;

import com.mall.butler.account.model.AccountCoupon;
import com.mall.butler.account.model.CouponUseRecord;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface MAccountCouponService extends BaseService{

	Page<AccountCoupon> pageQuery(PageRequest<AccountCoupon> pageRequest);

	void doSave(AccountCoupon accountCoupon);

	void doUpdate(AccountCoupon accountCoupon);

	void doDel(AccountCoupon accountCoupon);

	Page<CouponUseRecord> pageUseQuery(PageRequest<CouponUseRecord> pageRequest);
	
	/**
	 * 根据券信息查询券
	 * @param couponUseRecord
	 * @return
	 */
	List<CouponUseRecord> findByTransNo(CouponUseRecord couponUseRecord);

}
