package com.mall.butler.sys.model;

import com.mall.butler.model.BaseEntity;

public class SysDictDetail extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String DICTID = "dictId";
	public static final String DICTDETAILCODE = "dictDetailCode";
	public static final String DICTDETAILNAME = "dictDetailName";
	public static final String DICTDETAILVALUE = "dictDetailValue";
	public static final String PYJM = "pyjm";
	public static final String LISTSORT = "listSort";
	public static final String USEFLAG = "useFlag";
	public static final String REMARK = "remark";

	private Long dictId;
	private String dictDetailCode;
	private String dictDetailName;
	private String dictDetailValue;
	private String pyjm;
	private Integer listSort;
	private Boolean useFlag;
	private String remark;

	/**
	 *关联数据字典主表的DICT_CODE
	 */
	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	/**
	 *关联数据字典主表的DICT_CODE
	 */
	public Long getDictId() {
		return this.dictId;
	}

	/**
	 *字典明细编号为6位，前三位位主表的编号，后三位递增；001001
	 */
	public void setDictDetailCode(String dictDetailCode) {
		this.dictDetailCode = dictDetailCode;
	}

	/**
	 *字典明细编号为6位，前三位位主表的编号，后三位递增；001001
	 */
	public String getDictDetailCode() {
		return this.dictDetailCode;
	}

	/**
	 *字典名称
	 */
	public void setDictDetailName(String dictDetailName) {
		this.dictDetailName = dictDetailName;
	}

	/**
	 *字典名称
	 */
	public String getDictDetailName() {
		return this.dictDetailName;
	}

	/**
	 *字典值
	 */
	public void setDictDetailValue(String dictDetailValue) {
		this.dictDetailValue = dictDetailValue;
	}

	/**
	 *字典值
	 */
	public String getDictDetailValue() {
		return this.dictDetailValue;
	}

	/**
	 *拼音简拼
	 */
	public void setPyjm(String pyjm) {
		this.pyjm = pyjm;
	}

	/**
	 *拼音简拼
	 */
	public String getPyjm() {
		return this.pyjm;
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
	 *启用标志 0：不启用 1：启用
	 */
	public void setUseFlag(Boolean useFlag) {
		this.useFlag = useFlag;
	}

	/**
	 *启用标志 0：不启用 1：启用
	 */
	public Boolean getUseFlag() {
		return this.useFlag;
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