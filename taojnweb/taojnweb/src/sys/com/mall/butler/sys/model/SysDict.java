package com.mall.butler.sys.model;

import com.mall.butler.model.BaseEntity;

public class SysDict extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String DICTCODE = "dictCode";
	public static final String DICTNAME = "dictName";
	public static final String VALUEREGEX = "valueRegex";
	public static final String LISTSORT = "listSort";
	public static final String REMARK = "remark";

	private String dictCode;
	private String dictName;
	private String valueRegex;
	private Integer listSort;
	private String remark;

	/**
	 *字典编号(3位编号 001开始)
	 */
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	/**
	 *字典编号(3位编号 001开始)
	 */
	public String getDictCode() {
		return this.dictCode;
	}

	/**
	 *字典名称
	 */
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	/**
	 *字典名称
	 */
	public String getDictName() {
		return this.dictName;
	}

	/**
	 *对值的正则表达式值验证
	 */
	public void setValueRegex(String valueRegex) {
		this.valueRegex = valueRegex;
	}

	/**
	 *对值的正则表达式值验证
	 */
	public String getValueRegex() {
		return this.valueRegex;
	}

	/**
	 *排序
	 */
	public void setListSort(Integer listSort) {
		this.listSort = listSort;
	}

	/**
	 *排序
	 */
	public Integer getListSort() {
		return this.listSort;
	}

	/**
	 *备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 *备注
	 */
	public String getRemark() {
		return this.remark;
	}
}