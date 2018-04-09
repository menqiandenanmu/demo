package com.mall.butler.store.model;

import com.mall.butler.model.BaseEntity;

public class GoodsInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String GOODSNAME = "goodsName";
	public static final String STYLECLASSCODE = "styleClassCode";
	public static final String STYLECLASSNAME = "styleClassName";
	public static final String COLORCLASSCODE = "colorClassCode";
	public static final String COLORCLASSNAME = "colorClassName";
	public static final String GOODSCODE = "goodsCode";
	public static final String SIZECLASSCODE = "sizeClassCode";
	public static final String SIZECLASSNAME = "sizeClassName";
	public static final String SEASON = "season";
	public static final String PURCHASEPRICE = "purchasePrice";
	public static final String SELLPRICE = "sellPrice";
	public static final String SUMMARIZE = "summarize";
	public static final String TITLEIMGID = "titleImgId";
	public static final String CONTENTID = "contentId";
	public static final String INFOID = "infoId";
	public static final String MAXAMOUNT = "maxAmount";
	public static final String MINAMOUNT = "minAmount";
	public static final String TAGNAMES = "tagNames";
	public static final String TAGCODES = "tagCodes";
	public static final String USEFLAG = "useFlag";
	public static final String REMARK = "remark";
	public static final String ORDERID = "orderid";

	private String goodsName;
	private String styleClassCode;
	private String styleClassName;
	private String colorClassCode;
	private String colorClassName;
	private String goodsCode;
	private String sizeClassCode;
	private String sizeClassName;
	private String season;
	private Double purchasePrice;
	private Double sellPrice;
	private String summarize;
	private Long titleImgId;
	private Long contentId;
	private Long infoId;
	private Integer maxAmount;
	private Integer minAmount;
	private String tagNames;
	private String tagCodes;
	private Boolean useFlag;
	private String remark;
	private Integer orderid;
	private String contentStr;
	private String infoStr;

	/**
	 * 详细信息
	 */
	public String getContentStr() {
		return contentStr;
	}

	/**
	 * 详细信息
	 */
	public void setContentStr(String contentStr) {
		this.contentStr = contentStr;
	}

	/**
	 * 提示信息
	 */
	public String getInfoStr() {
		return infoStr;
	}

	/**
	 * 提示信息
	 */
	public void setInfoStr(String infoStr) {
		this.infoStr = infoStr;
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
	 *商品款式数据字典得 003
	 */
	public void setStyleClassCode(String styleClassCode) {
		this.styleClassCode = styleClassCode;
	}

	/**
	 *商品款式数据字典得 003
	 */
	public String getStyleClassCode() {
		return this.styleClassCode;
	}

	/**
	 *商品款式
	 */
	public void setStyleClassName(String styleClassName) {
		this.styleClassName = styleClassName;
	}

	/**
	 *商品款式
	 */
	public String getStyleClassName() {
		return this.styleClassName;
	}

	/**
	 *分类编号数据字典得 004
	 */
	public void setColorClassCode(String colorClassCode) {
		this.colorClassCode = colorClassCode;
	}

	/**
	 *分类编号数据字典得 004
	 */
	public String getColorClassCode() {
		return this.colorClassCode;
	}

	/**
	 *商品颜色
	 */
	public void setColorClassName(String colorClassName) {
		this.colorClassName = colorClassName;
	}

	/**
	 *商品颜色
	 */
	public String getColorClassName() {
		return this.colorClassName;
	}

	/**
	 *ID号格式化 8位
	 */
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	/**
	 *ID号格式化 8位
	 */
	public String getGoodsCode() {
		return this.goodsCode;
	}

	/**
	 *分类编号数据字典得 005
	 */
	public void setSizeClassCode(String sizeClassCode) {
		this.sizeClassCode = sizeClassCode;
	}

	/**
	 *分类编号数据字典得 005
	 */
	public String getSizeClassCode() {
		return this.sizeClassCode;
	}

	/**
	 *分类名字
	 */
	public void setSizeClassName(String sizeClassName) {
		this.sizeClassName = sizeClassName;
	}

	/**
	 *分类名字
	 */
	public String getSizeClassName() {
		return this.sizeClassName;
	}

	/**
	 *季节SPRING（0,,春季);SUMMER(1,夏);AUTUMN(2,秋);WINTER(3,冬)
	 */
	public void setSeason(String season) {
		this.season = season;
	}

	/**
	 *季节SPRING（0,,春季);SUMMER(1,夏);AUTUMN(2,秋);WINTER(3,冬)
	 */
	public String getSeason() {
		return this.season;
	}

	/**
	 *采购价格
	 */
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 *采购价格
	 */
	public Double getPurchasePrice() {
		return this.purchasePrice;
	}

	/**
	 *销售价格
	 */
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	/**
	 *销售价格
	 */
	public Double getSellPrice() {
		return this.sellPrice;
	}

	/**
	 *概要信息
	 */
	public void setSummarize(String summarize) {
		this.summarize = summarize;
	}

	/**
	 *概要信息
	 */
	public String getSummarize() {
		return this.summarize;
	}

	/**
	 *
	 */
	public void setTitleImgId(Long titleImgId) {
		this.titleImgId = titleImgId;
	}

	/**
	 *
	 */
	public Long getTitleImgId() {
		return this.titleImgId;
	}

	/**
	 *文本库ID
	 */
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	/**
	 *文本库ID
	 */
	public Long getContentId() {
		return this.contentId;
	}

	/**
	 *提示信息前台展示用
	 */
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	/**
	 *提示信息前台展示用
	 */
	public Long getInfoId() {
		return this.infoId;
	}

	/**
	 *最小值 1
	 */
	public void setMaxAmount(Integer maxAmount) {
		this.maxAmount = maxAmount;
	}

	/**
	 *最小值 1
	 */
	public Integer getMaxAmount() {
		return this.maxAmount;
	}

	/**
	 *默认值 10
	 */
	public void setMinAmount(Integer minAmount) {
		this.minAmount = minAmount;
	}

	/**
	 *默认值 10
	 */
	public Integer getMinAmount() {
		return this.minAmount;
	}

	/**
	 *标签:数据库字典里得名称可以选多个，多个用，分隔 020店铺标签
	 */
	public void setTagNames(String tagNames) {
		this.tagNames = tagNames;
	}

	/**
	 *标签:数据库字典里得名称可以选多个，多个用，分隔 020店铺标签
	 */
	public String getTagNames() {
		return this.tagNames;
	}

	/**
	 *标签:数据库字典里得编号可以选多个，多个用，分隔
	 */
	public void setTagCodes(String tagCodes) {
		this.tagCodes = tagCodes;
	}

	/**
	 *标签:数据库字典里得编号可以选多个，多个用，分隔
	 */
	public String getTagCodes() {
		return this.tagCodes;
	}

	/**
	 *0:未上架 1上架 上架的情况下用户可以通过搜索等方式查找得到。
	 */
	public void setUseFlag(Boolean useFlag) {
		this.useFlag = useFlag;
	}

	/**
	 *0:未上架 1上架 上架的情况下用户可以通过搜索等方式查找得到。
	 */
	public Boolean getUseFlag() {
		return this.useFlag;
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
}