package com.mall.butler.weixin;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信通知请求返回
 */
@XmlRootElement(name = "xml")
public class WxNotifyRes extends WxPayFromXml {
	private static final long serialVersionUID = -5400186234212702501L;

	/* JSAPI、NATIVE、MICROPAY、APP */
	private String trade_type;

	/* 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效 */
	private String prepay_id;

	private String code_url;

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getCode_url() {
		return code_url;
	}

	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}

}
