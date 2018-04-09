package com.mall.butler.account.m.model;

import java.util.Date;

import com.mall.butler.account.model.TradeOrder;


public class TradeOrderVo extends TradeOrder{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date begDate;//查询开始时间
	private Date endDate;//查询结束时间
	private String accountName;//账户名称
	
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Date getBegDate() {
		return begDate;
	}
	public void setBegDate(Date begDate) {
		this.begDate = begDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
