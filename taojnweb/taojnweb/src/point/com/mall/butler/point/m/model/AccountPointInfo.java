package com.mall.butler.point.m.model;

import com.mall.butler.account.model.AccountInfo;

public class AccountPointInfo extends AccountInfo {

	private static final long serialVersionUID = 1L;
	private String idCard;
	private Integer countPoint;
	private Integer point;
	private String mobile;
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getCountPoint() {
		return countPoint;
	}

	public void setCountPoint(Integer countPoint) {
		this.countPoint = countPoint;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
