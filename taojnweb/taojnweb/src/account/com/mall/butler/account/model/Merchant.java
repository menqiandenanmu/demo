package com.mall.butler.account.model;

import com.mall.butler.model.BaseEntity;

public class Merchant extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 静态变量
	public static final String MERCHANTNAME = "merchantName";
	public static final String MERCHANTNUMBER = "merchantNumber";
	public static final String MERCHANTKEY = "merchantKey";
	public static final String MERCHANTSTATUS = "merchantStatus";

	private String merchantName;
	private String merchantNumber;
	private String merchantKey;
	private Integer merchantStatus;

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}


	public String getMerchantNumber() {
		return merchantNumber;
	}

	public void setMerchantNumber(String merchantNumber) {
		this.merchantNumber = merchantNumber;
	}

	public String getMerchantKey() {
		return merchantKey;
	}

	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}

	public Integer getMerchantStatus() {
		return merchantStatus;
	}

	public void setMerchantStatus(Integer merchantStatus) {
		this.merchantStatus = merchantStatus;
	}

}
