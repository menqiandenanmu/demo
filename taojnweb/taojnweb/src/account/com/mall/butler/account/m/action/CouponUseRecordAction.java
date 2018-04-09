package com.mall.butler.account.m.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.MAccountCouponService;
import com.mall.butler.account.model.CouponUseRecord;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class CouponUseRecordAction extends ManageBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6600218705727866038L;
	private CouponUseRecord couponUseRecord;
	@Autowired
	private MAccountCouponService accountCouponService;
	private Page<CouponUseRecord> page;
	public String execute() {
		PageRequest<CouponUseRecord> pageRequest = super.newPage(CouponUseRecord.class);
		pageRequest.setFilters(couponUseRecord);
		pageRequest.setPageNumber(currPage);
		page = accountCouponService.pageUseQuery(pageRequest);
		return LIST;
	}
	public CouponUseRecord getCouponUseRecord() {
		return couponUseRecord;
	}
	public void setCouponUseRecord(CouponUseRecord couponUseRecord) {
		this.couponUseRecord = couponUseRecord;
	}
	public Page<CouponUseRecord> getPage() {
		return page;
	}
	public void setPage(Page<CouponUseRecord> page) {
		this.page = page;
	}
	
	
	
}
