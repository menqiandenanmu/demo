package com.mall.butler.account.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.AccountCoupon;
import com.mall.butler.dao.BaseDao;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class AccountCouponDao extends BaseDao<AccountCoupon, Long> {
	public List<AccountCoupon> find(AccountCoupon accountCoupon) {
		return this.queryEntitys("ALL", accountCoupon);
	}

	public Page<AccountCoupon> page(PageRequest<AccountCoupon> request) {
		Page<AccountCoupon> result = this.pageQuery(request, "PAGE");
		return result;
	}

	/**
	 * 生成12位条码号ZQ+10位数
	 * 
	 * @param accountCoupon2
	 * @return
	 */
	public String createBarCode(AccountCoupon accountCoupon2) {
		// TODO Auto-generated method stub
		Long n = accountCoupon2.getId() % 100000;
		String no = DateUtil.format(new Date(), "yyMMdd") ;
		no = "88"+no + String.format("%1$04d", n);
		return no;
	}
}
