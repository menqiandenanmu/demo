package com.mall.butler.order.model;

import java.util.Date;

import com.mall.butler.model.BaseEntity;

public class TransInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String ACCOUNTID = "accountId";
	public static final String LOGINID = "loginId";
	public static final String GATEWAY = "gateway";
	public static final String GATEWAYTRANSNO = "gatewayTransNo";
	public static final String BUSINESSTYPE = "businessType";
	public static final String BUSINESSID = "businessId";
	public static final String TRANSNO = "transNo";
	public static final String AMOUNT = "amount";
	public static final String STATUS = "status";
	public static final String REPORTINFO = "reportInfo";
	public static final String REPORTTIME = "reportTime";
	public static final String TRANS_TYPE = "transType";

	private Long accountId;
	private Long loginId;
	private Integer gateway;
	private String gatewayTransNo;
	private Integer businessType;
	private Integer transType;
	private Long businessId;
	private String transNo;
	private Double amount;
	private Integer status;
	private String reportInfo;
	private Date reportTime;
	private String businessNo;
	// 扩展字段
	private Date begCreateDate;
	private Date endCreateDate;

	
	/**
	 * 0支付1退款
	 * @return
	 */
	public Integer getTransType() {
		return transType;
	}

	/**
	 * 0支付1退款
	 * @param transType
	 */
	public void setTransType(Integer transType) {
		this.transType = transType;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public Date getBegCreateDate() {
		return begCreateDate;
	}

	public void setBegCreateDate(Date begCreateDate) {
		this.begCreateDate = begCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	/**
	 *
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 *
	 */
	public Long getAccountId() {
		return this.accountId;
	}

	/**
	 *
	 */
	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	/**
	 *
	 */
	public Long getLoginId() {
		return this.loginId;
	}

	/**
	 *0:无 1:预付款 2:电商支付宝3:电商银联4电商网银5：手机支付宝6：微信支付 9:手动支付(管理员操作）
	 */
	public void setGateway(Integer gateway) {
		this.gateway = gateway;
	}

	/**
	 *0:无 1:预付款 2:电商支付宝3:电商银联4电商网银5：手机支付宝6：微信支付 9:手动支付(管理员操作）
	 */
	public Integer getGateway() {
		return this.gateway;
	}

	/**
	 *
	 */
	public void setGatewayTransNo(String gatewayTransNo) {
		this.gatewayTransNo = gatewayTransNo;
	}

	/**
	 *
	 */
	public String getGatewayTransNo() {
		return this.gatewayTransNo;
	}

	/**
	 *(0, "景区门票 "), (1, "酒店"), (2,"餐饮"),(3,"旅游线路"),(4,"车辆")(5,"商品")6.自由行7.秒杀
	 */
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	/**
	 *(0, "景区门票 "), (1, "酒店"), (2,"餐饮"),(3,"旅游线路"),(4,"车辆")(5,"商品")6.自由行7.秒杀
	 */
	public Integer getBusinessType() {
		return this.businessType;
	}

	/**
	 *
	 */
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	/**
	 *
	 */
	public Long getBusinessId() {
		return this.businessId;
	}

	/**
	 *(支付宝 前缀加+上订单号, 前缀由各个项目定。 如西溪湿地（xxsd))
	 */
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	/**
	 *(支付宝 前缀加+上订单号, 前缀由各个项目定。 如西溪湿地（xxsd))
	 */
	public String getTransNo() {
		return this.transNo;
	}

	/**
	 *
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 *
	 */
	public Double getAmount() {
		return this.amount;
	}

	/**
	 *0:已经提交 1:交易成功 2:交易失败
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 *0:已经提交 1:交易成功 2:交易失败
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 *支付报告内容 手功请情况下需输入信息
	 */
	public void setReportInfo(String reportInfo) {
		this.reportInfo = reportInfo;
	}

	/**
	 *支付报告内容 手功请情况下需输入信息
	 */
	public String getReportInfo() {
		return this.reportInfo;
	}

	/**
	 *
	 */
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	/**
	 *
	 */
	public Date getReportTime() {
		return this.reportTime;
	}
}