package com.mall.butler.weixin;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信通知请求返回
 * 
 */
@XmlRootElement(name = "xml")
public class WxNotifyReq extends WxPayFromXml {
	private static final long serialVersionUID = -5400186234212702501L;

	private String openId;

	/* 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效 */
	private String is_subscribe;

	/* JSAPI、NATIVE、MICROPAY、APP */
	private String trade_type;

	private String bank_type;

	/* 订单总金额，单位为分 */
	private String total_fee;

	/* 现金券支付金额<=订单总金额，订单总金额-现金券金额为现金支付金额 */
	private String coupon_fee;

	private String fee_type;

	/* 微信支付订单号 */
	private String transaction_id;

	/* 商户系统的订单号，与请求一致 */
	private String out_trade_no;

	/* 商家数据包，原样返回 */
	private String attach;

	/*
	 * 支 付 完 成 时 间 ， 格 式 为yyyyMMddhhmmss，如 2009 年12 月 27 日 9 点 10 分 10 秒表 示为
	 * 20091227091010。时区为 GMT+8 beijing。该时间取自微信支付服务器
	 */
	private String time_end;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getCoupon_fee() {
		return coupon_fee;
	}

	public void setCoupon_fee(String coupon_fee) {
		this.coupon_fee = coupon_fee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
}
