package com.mall.butler.weixin;

import org.springframework.stereotype.Component;

/**
 * 
 * 微信 配置信息
 *
 */
@Component
public class WxPayConfig {

	/* 商户号 */
	private String mchId;
	/**/
	private String appId;
	/* 私钥 */
	private String appSecret;
	
	private String key;
	
	/* 普通微信支付成功通知URL */
	private String notifyUrl;
	
	/* 扫码支付成功通知URL */
	private String qrcodeNotifyUrl;
	
	/* 预支付URL */
	private String prePayUrl;
	
	/* 退款URL */
	private String refundUrl;
	
	/* 证书URL */
	private String certPath;
	
	/* 线上会员卡充值通知URL*/
	private String rechargeNotifyUrl;

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getPrePayUrl() {
		return prePayUrl;
	}

	public void setPrePayUrl(String prePayUrl) {
		this.prePayUrl = prePayUrl;
	}

	public String getRechargeNotifyUrl() {
		return rechargeNotifyUrl;
	}

	public void setRechargeNotifyUrl(String rechargeNotifyUrl) {
		this.rechargeNotifyUrl = rechargeNotifyUrl;
	}

	public String getRefundUrl() {
		return refundUrl;
	}

	public String getCertPath() {
		return certPath;
	}

	public void setRefundUrl(String refundUrl) {
		this.refundUrl = refundUrl;
	}

	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}

	public String getQrcodeNotifyUrl() {
		return qrcodeNotifyUrl;
	}

	public void setQrcodeNotifyUrl(String qrcodeNotifyUrl) {
		this.qrcodeNotifyUrl = qrcodeNotifyUrl;
	}
	
}
