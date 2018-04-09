package com.mall.butler.web.model;

import com.mall.butler.model.BaseEntity;

public class NewsInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String LOGINID = "loginId";
	public static final String TITLE = "title";
	public static final String TITLEIMAGEID = "titleImageId";
	public static final String SOURCE = "source";
	public static final String AUTH = "auth";
	public static final String CONTENTID = "contentId";
	public static final String KEYWORKS = "keyWorks";
	public static final String CLASSNAME = "className";
	public static final String CLASSCODE = "classCode";
	public static final String READNUM = "readNum";
	public static final String SUMMARY = "summary";
	public static final String ORDERID = "orderid";
	public static final String DESCRIPTION = "description";
	public static final String KEYWORDS = "keywords";

	private Long loginId;
	private String title;
	private Long titleImageId;
	private String source;
	private String auth;
	private Long contentId;
	private String keyWorks;
	private String className;
	private String classCode;
	private Integer readNum;
	private String summary;
	private Integer orderid;
	private String description;
	private String keywords;

	/**
	 *操作员
	 */
	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	/**
	 *操作员
	 */
	public Long getLoginId() {
		return this.loginId;
	}

	/**
	 *标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 *标题
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 *图片
	 */
	public void setTitleImageId(Long titleImageId) {
		this.titleImageId = titleImageId;
	}

	/**
	 *图片
	 */
	public Long getTitleImageId() {
		return this.titleImageId;
	}

	/**
	 *出处
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 *出处
	 */
	public String getSource() {
		return this.source;
	}

	/**
	 *作者
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}

	/**
	 *作者
	 */
	public String getAuth() {
		return this.auth;
	}

	/**
	 *内容
	 */
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	/**
	 *内容
	 */
	public Long getContentId() {
		return this.contentId;
	}

	/**
	 *关键字以空格分开
	 */
	public void setKeyWorks(String keyWorks) {
		this.keyWorks = keyWorks;
	}

	/**
	 *关键字以空格分开
	 */
	public String getKeyWorks() {
		return this.keyWorks;
	}

	/**
	 *分类名称
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 *分类名称
	 */
	public String getClassName() {
		return this.className;
	}

	/**
	 *分类编号
	 */
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	/**
	 *分类编号
	 */
	public String getClassCode() {
		return this.classCode;
	}

	/**
	 *阅读次数
	 */
	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}

	/**
	 *阅读次数
	 */
	public Integer getReadNum() {
		return this.readNum;
	}

	/**
	 *
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 *
	 */
	public String getSummary() {
		return this.summary;
	}

	/**
	 *排序值
	 */
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	/**
	 *排序值
	 */
	public Integer getOrderid() {
		return this.orderid;
	}

	/**
	 *页面元描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 *页面元描述
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 *页面关键字
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 *页面关键字
	 */
	public String getKeywords() {
		return this.keywords;
	}
}