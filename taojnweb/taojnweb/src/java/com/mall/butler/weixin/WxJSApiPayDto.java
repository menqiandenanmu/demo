package com.mall.butler.weixin;

/**
 * 微信JS api调起支付
 * 
 */
public class WxJSApiPayDto {

	private String appId;

	private String timeStamp;

	private String nonceStr;

	// 统 一 支 付 接 口 返 回 的prepay_id 参数值，提交格式,如：prepay_id=u802345jgfjsdfgsdg888
	private String packageStr;

	private String signType;

	private String paySign;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackageStr() {
		return packageStr;
	}

	public void setPackageStr(String packageStr) {
		this.packageStr = packageStr;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

}
