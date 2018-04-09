package com.mall.butler.sys.model;

import com.mall.butler.model.BaseEntity;

public class SysFunctions extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String FUNNAME = "funName";
	public static final String FUNICO = "funIco";
	public static final String FUNACCTYPE = "funAccType";
	public static final String FUNTYPE = "funType";
	public static final String FUNURL = "funUrl";
	public static final String SORT = "sort";
	public static final String PARENTID = "parentId";

	private String funName;
	private String funIco;
	private Integer funAccType;
	private Integer funType;
	private String funUrl;
	private Integer sort;
	private Long parentId;
	
	

	/**
	 *
	 */
	public void setFunName(String funName) {
		this.funName = funName;
	}

	/**
	 *
	 */
	public String getFunName() {
		return this.funName;
	}

	/**
	 *功能图标
	 */
	public void setFunIco(String funIco) {
		this.funIco = funIco;
	}

	/**
	 *功能图标
	 */
	public String getFunIco() {
		return this.funIco;
	}

	/**
	 *0:系统管理
	 */
	public void setFunAccType(Integer funAccType) {
		this.funAccType = funAccType;
	}

	/**
	 *0:系统管理
	 */
	public Integer getFunAccType() {
		return this.funAccType;
	}

	/**
	 *0:组 1:功能URL
	 */
	public void setFunType(Integer funType) {
		this.funType = funType;
	}

	/**
	 *0:组 1:功能URL
	 */
	public Integer getFunType() {
		return this.funType;
	}

	/**
	 *
	 */
	public void setFunUrl(String funUrl) {
		this.funUrl = funUrl;
	}

	/**
	 *
	 */
	public String getFunUrl() {
		return this.funUrl;
	}

	/**
	 *
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 *
	 */
	public Integer getSort() {
		return this.sort;
	}

	/**
	 *
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 *
	 */
	public Long getParentId() {
		return this.parentId;
	}

	
	
}