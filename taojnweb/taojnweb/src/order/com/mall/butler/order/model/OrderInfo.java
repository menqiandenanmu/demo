package com.mall.butler.order.model;

import java.util.Date;

import com.mall.butler.model.BaseEntity;

public class OrderInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String BUYERID = "buyerId";
	public static final String BUYERNAME = "buyerName";
	public static final String LOGINID = "loginId";
	public static final String LOGINNAME = "loginName";
	public static final String BUYERACCTYPE = "buyerAccType";
	public static final String ORDERNO = "orderNo";
	public static final String BOOKTYPE = "bookType";
	public static final String PAYTYPE = "payType";
	public static final String PAYSTATUS = "payStatus";
	public static final String PAYSUM = "paySum";
	public static final String PAYTIME = "payTime";
	public static final String FINALSUM = "finalSum";
	public static final String ORDERSTATUS = "orderStatus";
	public static final String CLOSETIME = "closeTime";
	public static final String HUNGFLAG = "hungFlag";
	public static final String CLOSED = "closed";
	public static final String LINKNAME = "linkName";
	public static final String LINKIDCARD = "linkIdcard";
	public static final String LINKMOBILE = "linkMobile";
	public static final String LINKADDR = "linkAddr";
	public static final String AREACODE = "areaCode";
	public static final String AREANAME = "areaName";
	public static final String DELIVERYID = "deliveryId";
	public static final String DELIVERYNO = "deliveryNo";
	public static final String DELIVERYDATE = "deliveryDate";
	public static final String REMARK = "remark";
	public static final String INFO = "info";

	private Long buyerId;
	private String buyerName;
	private Long loginId;
	private String loginName;
	private Integer buyerAccType;
	private String orderNo;
	private Integer bookType;
	private Integer payType;
	private Integer payStatus;
	private Double paySum;
	private Date payTime;
	private Double finalSum;
	private Integer orderStatus;
	private Date closeTime;
	private Boolean hungFlag;
	private Boolean closed;
	private String linkName;
	private String linkIdcard;
	private String linkMobile;
	private String linkAddr;
	private String areaCode;
	private String areaName;
	private Long deliveryId;
	private String deliveryNo;
	private Date deliveryDate;
	private String remark;
	private String info;

	/**
	 *买家用户ID
	 */
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	/**
	 *买家用户ID
	 */
	public Long getBuyerId() {
		return this.buyerId;
	}

	/**
	 *买家名称
	 */
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	/**
	 *买家名称
	 */
	public String getBuyerName() {
		return this.buyerName;
	}

	/**
	 *添加订单 的 登录用户，可能是前台用户也可能是后台操作员
	 */
	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	/**
	 *添加订单 的 登录用户，可能是前台用户也可能是后台操作员
	 */
	public Long getLoginId() {
		return this.loginId;
	}

	/**
	 *操作员名
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 *操作员名
	 */
	public String getLoginName() {
		return this.loginName;
	}

	/**
	 *用户类型SYS(0:系统管理)CON( 1:普通用户)
	 */
	public void setBuyerAccType(Integer buyerAccType) {
		this.buyerAccType = buyerAccType;
	}

	/**
	 *用户类型SYS(0:系统管理)CON( 1:普通用户)
	 */
	public Integer getBuyerAccType() {
		return this.buyerAccType;
	}

	/**
	 *yyyyMMddHHmm(ID % 10000)
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 *yyyyMMddHHmm(ID % 10000)
	 */
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 *0:网上购买 1:网上预订
	 */
	public void setBookType(Integer bookType) {
		this.bookType = bookType;
	}

	/**
	 *0:网上购买 1:网上预订
	 */
	public Integer getBookType() {
		return this.bookType;
	}

	/**
	 *0:无 1:预付款 2:支付宝 9:手动支付(管理员操作）
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	/**
	 *0:无 1:预付款 2:支付宝 9:手动支付(管理员操作）
	 */
	public Integer getPayType() {
		return this.payType;
	}

	/**
	 *支付状态 0：待支付 1：已支付 2:支付过期 3:不需要支付
	 */
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 *支付状态 0：待支付 1：已支付 2:支付过期 3:不需要支付
	 */
	public Integer getPayStatus() {
		return this.payStatus;
	}

	/**
	 *订单支付金额
	 */
	public void setPaySum(Double paySum) {
		this.paySum = paySum;
	}

	/**
	 *订单支付金额
	 */
	public Double getPaySum() {
		return this.paySum;
	}

	/**
	 *成功支付后的时间
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	/**
	 *成功支付后的时间
	 */
	public Date getPayTime() {
		return this.payTime;
	}

	/**
	 *订单最终金额，
	 */
	public void setFinalSum(Double finalSum) {
		this.finalSum = finalSum;
	}

	/**
	 *订单最终金额，
	 */
	public Double getFinalSum() {
		return this.finalSum;
	}

	/**
	 *INIT(0,"新建订单"),SUCCESS(1,"订单成功"),FAIL(2,"订单失败"),CANCEL(3,"退单"),SIGNIN(4,'签收')
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 *INIT(0,"新建订单"),SUCCESS(1,"订单成功"),FAIL(2,"订单失败"),CANCEL(3,"退单"),SIGNIN(4,'签收')
	 */
	public Integer getOrderStatus() {
		return this.orderStatus;
	}

	/**
	 *订单未支付前为订单时间加上30分钟,如果订单成功时间为overtime+5天 订单关闭后不能任何操作
	 */
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	/**
	 *订单未支付前为订单时间加上30分钟,如果订单成功时间为overtime+5天 订单关闭后不能任何操作
	 */
	public Date getCloseTime() {
		return this.closeTime;
	}

	/**
	 *0 未挂起 1:挂 起
	 */
	public void setHungFlag(Boolean hungFlag) {
		this.hungFlag = hungFlag;
	}

	/**
	 *0 未挂起 1:挂 起
	 */
	public Boolean getHungFlag() {
		return this.hungFlag;
	}

	/**
	 *0:可操作 1:不可操作
	 */
	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	/**
	 *0:可操作 1:不可操作
	 */
	public Boolean getClosed() {
		return this.closed;
	}

	/**
	 *取票人姓名，默认为账户真实姓名
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	/**
	 *取票人姓名，默认为账户真实姓名
	 */
	public String getLinkName() {
		return this.linkName;
	}

	/**
	 *身份证号
	 */
	public void setLinkIdcard(String linkIdcard) {
		this.linkIdcard = linkIdcard;
	}

	/**
	 *身份证号
	 */
	public String getLinkIdcard() {
		return this.linkIdcard;
	}

	/**
	 *手机号码
	 */
	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}

	/**
	 *手机号码
	 */
	public String getLinkMobile() {
		return this.linkMobile;
	}

	/**
	 *收件人地址。
	 */
	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}

	/**
	 *收件人地址。
	 */
	public String getLinkAddr() {
		return this.linkAddr;
	}

	/**
	 *区域编码
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 *区域编码
	 */
	public String getAreaCode() {
		return this.areaCode;
	}

	/**
	 *区域名
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 *区域名
	 */
	public String getAreaName() {
		return this.areaName;
	}

	/**
	 *发货ID
	 */
	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}

	/**
	 *发货ID
	 */
	public Long getDeliveryId() {
		return this.deliveryId;
	}

	/**
	 *发货编号
	 */
	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	/**
	 *发货编号
	 */
	public String getDeliveryNo() {
		return this.deliveryNo;
	}

	/**
	 *发货时间
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 *发货时间
	 */
	public Date getDeliveryDate() {
		return this.deliveryDate;
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

	/**
	 *订单内容
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 *订单内容
	 */
	public String getInfo() {
		return this.info;
	}
}