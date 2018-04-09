package com.mall.butler.account.m.model;

import java.util.Date;

import com.mall.butler.model.BaseEntity;


public class TradeAccountDetailVo extends BaseEntity{
	
	private static final long serialVersionUID = -5050960954028746039L;
	

	private String accountName;
	
	private String ownerName;
	
	private String supplyName;
	
	private Integer orderCount;
	
	private Double fanLi;
	
	private Double heJi;
	
	private Integer opType;
	
	private Date begDate;
	
	private Date endDate;
	
	private String accountId;
	
	private String ownerSupplyId;
	
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getOwnerSupplyId() {
		return ownerSupplyId;
	}

	public void setOwnerSupplyId(String ownerSupplyId) {
		this.ownerSupplyId = ownerSupplyId;
	}

	public Integer getOpType() {
		return opType;
	}

	public void setOpType(Integer opType) {
		this.opType = opType;
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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}



	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Double getFanLi() {
		return fanLi;
	}

	public void setFanLi(Double fanLi) {
		this.fanLi = fanLi;
	}

	public Double getHeJi() {
		return heJi;
	}

	public void setHeJi(Double heJi) {
		this.heJi = heJi;
	}




	
	

}
