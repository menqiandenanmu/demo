package com.mall.butler.sys.m.vo;

import java.util.Date;

import com.mall.butler.sys.model.SysOptLog;

/**
 * @author caedmon
 * @version 创建时间：2013-1-30 上午09:49:49
 */
public class SysOptLogVo extends SysOptLog {

	private static final long serialVersionUID = 1L;
	// 操作员名称
	private String loginName;
	private Date beginDate;
	private Date endDate;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
