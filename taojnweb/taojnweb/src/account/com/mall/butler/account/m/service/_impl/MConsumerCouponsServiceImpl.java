package com.mall.butler.account.m.service._impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.account.dao.AccountCouponDao;
import com.mall.butler.account.dao.ConsumerCouponsDao;
import com.mall.butler.account.dao.CouponUseRecordDao;
import com.mall.butler.account.m.service.MConsumerCouponsService;
import com.mall.butler.account.model.AccountCoupon;
import com.mall.butler.account.model.ConsumerCoupons;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MConsumerCouponsServiceImpl extends BaseServiceImpl implements
		MConsumerCouponsService {

	@Autowired
	private ConsumerCouponsDao consumerCouponsDao;

	@Transactional
	@Override
	public void doDel(ConsumerCoupons consumerCoupons) {
		// TODO Auto-generated method stub
		consumerCouponsDao.delete(consumerCoupons);
		// 更新用户赠券状态为失效
		AccountCoupon accountCoupon = new AccountCoupon();
		accountCoupon.setCouponCode(consumerCoupons.getCouponCode());
		accountCoupon.setCouponStatus(0);
		List<AccountCoupon> list = accountCouponDao.find(accountCoupon);
		if (null != list && list.size() > 0) {
			for (AccountCoupon accountCoupon2 : list) {
				accountCoupon2.setCouponStatus(2);
				accountCouponDao.update(accountCoupon2);
			}
		}
	}

	@Override
	public void doSave(ConsumerCoupons consumerCoupons) {
		// TODO Auto-generated method stub
		//consumerCoupons.setId(consumerCouponsDao.getNewId());
		consumerCoupons.setCouponCode(getCouponCode(consumerCoupons));
		consumerCouponsDao.insert(consumerCoupons);
	}

	@Autowired
	private AccountCouponDao accountCouponDao;

	@Transactional
	@Override
	public void doExpiraCoupon(ConsumerCoupons conCoupons) {
		// TODO Auto-generated method stub
		// 更新赠券状态
		ConsumerCoupons obj = consumerCouponsDao.getById(conCoupons.getId());
		if(obj.getCouponStatus()==0||obj.getCouponStatus()==1){
		obj.setCouponStatus(2);
		consumerCouponsDao.update(obj);
		// 更新用户赠券状态
		AccountCoupon account = new AccountCoupon();
		account.setCouponCode(obj.getCouponCode());
		account.setCouponStatus(0);
		List<AccountCoupon> accountCoupons = accountCouponDao.find(account);
		if (null != accountCoupons && accountCoupons.size() > 0) {
			for (AccountCoupon accountCoupon : accountCoupons) {
				accountCoupon.setCouponStatus(2);
				accountCouponDao.update(accountCoupon);
			}
		}
		}
	}

	public String getCouponCode(ConsumerCoupons consumerCoupons) {
		String couponsCode = consumerCouponsDao.createCouponNo(consumerCoupons);
		// 查询是否有重复的
		ConsumerCoupons filter = new ConsumerCoupons();
		filter.setCouponCode(couponsCode);
		List<ConsumerCoupons> list = consumerCouponsDao.find(filter);
		if (null != list && list.size() > 0) {
			getCouponCode(consumerCoupons);
		}
		return couponsCode;
	}

	@Override
	public void doUpdate(ConsumerCoupons consumerCoupons) {
		// TODO Auto-generated method stub
		ConsumerCoupons obj = consumerCouponsDao.getById(consumerCoupons
				.getId());
		// obj.setCouponCode(consumerCoupons.getCouponCode());
		obj.setCouponName(consumerCoupons.getCouponName());
		obj.setCouponNum(consumerCoupons.getCouponNum());
		obj.setCouponStatus(consumerCoupons.getCouponStatus());
		obj.setExpiratDate(consumerCoupons.getExpiratDate());
		obj.setPrice(consumerCoupons.getPrice());
		obj.setRemark(consumerCoupons.getRemark());
		obj.setRemark2(consumerCoupons.getRemark2());
		// obj.setRuleId(consumerCoupons.getRuleId());
		consumerCouponsDao.update(obj);
	}

	@Override
	public Page<ConsumerCoupons> pageQuery(
			PageRequest<ConsumerCoupons> pageRequest) {
		// TODO Auto-generated method stub
		return consumerCouponsDao.page(pageRequest);
	}

	@Override
	public Page<AccountCoupon> countCoupon(
			PageRequest<Map<String, Object>> request) {
		// TODO Auto-generated method stub
		return accountCouponDao.pageQuery(request, "PAGE_COUNT");
	}

	@Override
	public List<AccountCoupon> queryCountCoupon(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return accountCouponDao.queryEntitys("PAGE_COUNT", map);
	}

}
