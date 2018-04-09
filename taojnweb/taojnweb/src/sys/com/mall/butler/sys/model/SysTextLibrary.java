package com.mall.butler.sys.model;

import com.mall.butler.model.BaseEntity;

public class SysTextLibrary extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String TITLE = "title";
	public static final String CONTEXT = "context";

	private String title;
	private String context;

	/**
	 *
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 *
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 *
	 */
	public void setContext(String context) {
		this.context = context;
	}

	/**
	 *
	 */
	public String getContext() {
		return this.context;
	}
}