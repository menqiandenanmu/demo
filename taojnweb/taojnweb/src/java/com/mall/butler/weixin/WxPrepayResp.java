package com.mall.butler.weixin;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信预支付返回
 * 
 */
@XmlRootElement(name = "xml")
public class WxPrepayResp extends WxPayFromXml {
	private static final long serialVersionUID = -5112733466309313985L;

	/* 交易类型 */
	private String trade_type;

	/* 预支付 ID */
	private String prepay_id;

	/* 二维码链接 */
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
