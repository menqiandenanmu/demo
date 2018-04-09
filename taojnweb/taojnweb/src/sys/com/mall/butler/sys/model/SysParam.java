package com.mall.butler.sys.model;

import com.mall.butler.model.BaseEntity;

public class SysParam extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String PARAMCODE = "paramCode";
	public static final String PARAMNAME = "paramName";
	public static final String PARAMVALUE = "paramValue";
	public static final String PARAMREGEX = "paramRegex";
	public static final String OPERATORID = "operatorId";
	public static final String REMARK = "remark";

	private String paramCode;
	private String paramName;
	private String paramValue;
	private String paramRegex;
	private Long operatorId;
	private String remark;

	/**
	 *参数编号 4位的编号；0001开始
	 */
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	/**
	 *参数编号 4位的编号；0001开始
	 */
	public String getParamCode() {
		return this.paramCode;
	}

	/**
	 *参数名称
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	/**
	 *参数名称
	 */
	public String getParamName() {
		return this.paramName;
	}

	/**
	 *参数值 具体参数的合法性需要根据参数类型进行判断
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	/**
	 *参数值 具体参数的合法性需要根据参数类型进行判断
	 */
	public String getParamValue() {
		return this.paramValue;
	}

	/**
	 *参数正则表达式控制页面输入验证
	 */
	public void setParamRegex(String paramRegex) {
		this.paramRegex = paramRegex;
	}

	/**
	 *参数正则表达式控制页面输入验证
	 */
	public String getParamRegex() {
		return this.paramRegex;
	}

	/**
	 *
	 */
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 *
	 */
	public Long getOperatorId() {
		return this.operatorId;
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