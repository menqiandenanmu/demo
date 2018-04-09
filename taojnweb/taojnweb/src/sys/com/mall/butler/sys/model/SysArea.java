package com.mall.butler.sys.model;

import com.mall.butler.model.BaseEntity;

public class SysArea extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String PARENTID = "parentId";
	public static final String AREACODE = "areaCode";
	public static final String AREANAME = "areaName";
	public static final String PYCODE = "pyCode";
	public static final String DEFAREACODE = "defAreaCode";
	public static final String LISTSORT = "listSort";
	public static final String REMARK = "remark";

	private Long parentId;
	private String areaCode;
	private String areaName;
	private String pyCode;
	private String defAreaCode;
	private Integer listSort;
	private String remark;

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

	/**
	 *每三位编号一级，最多4级
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 *每三位编号一级，最多4级
	 */
	public String getAreaCode() {
		return this.areaCode;
	}

	/**
	 *
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 *
	 */
	public String getAreaName() {
		return this.areaName;
	}

	/**
	 *拼音简拼
	 */
	public void setPyCode(String pyCode) {
		this.pyCode = pyCode;
	}

	/**
	 *拼音简拼
	 */
	public String getPyCode() {
		return this.pyCode;
	}

	/**
	 *从数据字典中读取
	 */
	public void setDefAreaCode(String defAreaCode) {
		this.defAreaCode = defAreaCode;
	}

	/**
	 *从数据字典中读取
	 */
	public String getDefAreaCode() {
		return this.defAreaCode;
	}

	/**
	 *
	 */
	public void setListSort(Integer listSort) {
		this.listSort = listSort;
	}

	/**
	 *
	 */
	public Integer getListSort() {
		return this.listSort;
	}

	/**
	 *
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 *
	 */
	public String getRemark() {
		return this.remark;
	}
}