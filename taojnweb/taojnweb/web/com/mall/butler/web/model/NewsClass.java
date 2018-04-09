package com.mall.butler.web.model;

import com.mall.butler.model.BaseEntity;

public class NewsClass extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String NAME = "name";
	public static final String CODE = "code";
	public static final String PARENTNAMES = "parentNames";
	public static final String PARENTID = "parentId";
	public static final String INFO = "info";

	private String name;
	private String code;
	private String parentNames;
	private Long parentId;
	private String info;

	/**
	 *标题
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *标题
	 */
	public String getName() {
		return this.name;
	}

	/**
	 *自动编号 3位一级
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 *自动编号 3位一级
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 *以，分隔祖先名
	 */
	public void setParentNames(String parentNames) {
		this.parentNames = parentNames;
	}

	/**
	 *以，分隔祖先名
	 */
	public String getParentNames() {
		return this.parentNames;
	}

	/**
	 *父节点
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 *父节点
	 */
	public Long getParentId() {
		return this.parentId;
	}

	/**
	 *内容
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 *内容
	 */
	public String getInfo() {
		return this.info;
	}
}