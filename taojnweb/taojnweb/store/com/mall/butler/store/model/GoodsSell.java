package com.mall.butler.store.model;

import com.mall.butler.model.BaseEntity;

public class GoodsSell extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String STOCKID = "stockId";
	public static final String GOODSNAME = "goodsName";
	public static final String NUM = "num";
	public static final String LOGINID = "loginId";
	public static final String LOGINNAME = "loginName";
	public static final String REMARK = "remark";

	private Long stockId;
	private String goodsName;
	private Integer num;
	private Long loginId;
	private String loginName;
	private String remark;

	/**
	 *库存ID
	 */
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	/**
	 *库存ID
	 */
	public Long getStockId() {
		return this.stockId;
	}

	/**
	 *商品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 *商品名称
	 */
	public String getGoodsName() {
		return this.goodsName;
	}

	/**
	 *数量
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 *数量
	 */
	public Integer getNum() {
		return this.num;
	}

	/**
	 *操作员ID
	 */
	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	/**
	 *操作员ID
	 */
	public Long getLoginId() {
		return this.loginId;
	}

	/**
	 *操作员名称
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 *操作员名称
	 */
	public String getLoginName() {
		return this.loginName;
	}

	/**
	 *备注信息管理后台使用
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 *备注信息管理后台使用
	 */
	public String getRemark() {
		return this.remark;
	}
}