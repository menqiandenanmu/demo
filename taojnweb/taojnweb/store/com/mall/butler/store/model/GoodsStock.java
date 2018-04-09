package com.mall.butler.store.model;

import com.mall.butler.model.BaseEntity;

public class GoodsStock extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String GOODSID = "goodsId";
	public static final String GOODSNAME = "goodsName";
	public static final String TOTALNUM = "totalNum";
	public static final String NUM = "num";
	public static final String LOGINID = "loginId";
	public static final String LOGINNAME = "loginName";

	private Long goodsId;
	private String goodsName;
	private Integer totalNum;
	private Integer num;
	private Long loginId;
	private String loginName;

	/**
	 *商品ID
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 *商品ID
	 */
	public Long getGoodsId() {
		return this.goodsId;
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
	 *总数量
	 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	/**
	 *总数量
	 */
	public Integer getTotalNum() {
		return this.totalNum;
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
}