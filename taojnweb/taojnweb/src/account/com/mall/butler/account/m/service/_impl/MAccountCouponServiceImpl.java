package com.mall.butler.account.m.service._impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.dao.AccountCouponDao;
import com.mall.butler.account.dao.CouponUseRecordDao;
import com.mall.butler.account.m.service.MAccountCouponService;
import com.mall.butler.account.model.AccountCoupon;
import com.mall.butler.account.model.CouponUseRecord;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MAccountCouponServiceImpl extends BaseServiceImpl implements MAccountCouponService{

	@Autowired
	private AccountCouponDao accountCouponDao;
	@Override
	public void doDel(AccountCoupon accountCoupon) {
		// TODO Auto-generated method stub
		accountCouponDao.delete(accountCoupon);
	}

	@Override
	public void doSave(AccountCoupon accountCoupon) {
		// TODO Auto-generated method stub
		accountCoupon.setId(accountCouponDao.getNewId());
		accountCouponDao.insert(accountCoupon);
	}

	@Override
	public void doUpdate(AccountCoupon accountCoupon) {
		// TODO Auto-generated method stub
		AccountCoupon obj = accountCouponDao.getById(accountCoupon.getId());
		obj.setCouponStatus(accountCoupon.getCouponStatus());
		obj.setExpiratDate(accountCoupon.getExpiratDate());
		accountCouponDao.update(obj);
	}

	@Override
	public Page<AccountCoupon> pageQuery(PageRequest<AccountCoupon> pageRequest) {
		// TODO Auto-generated method stub
		return accountCouponDao.page(pageRequest);
	}

	@Autowired
	private CouponUseRecordDao couponUseRecordDao;
	@Override
	public Page<CouponUseRecord> pageUseQuery(
			PageRequest<CouponUseRecord> pageRequest) {
		// TODO Auto-generated method stub
		return couponUseRecordDao.page(pageRequest);
	}

	@Override
	public List<CouponUseRecord> findByTransNo(CouponUseRecord couponUseRecord) {
		
		return couponUseRecordDao.find(couponUseRecord);
	}

}
