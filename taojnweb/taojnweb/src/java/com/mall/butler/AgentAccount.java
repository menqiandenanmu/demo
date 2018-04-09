package com.mall.butler;

public class AgentAccount {
	// 用户注册地址
	public static String CITIPAY_REGISTER_URL;
	// 用户登录地址
	public static String CITIPAY_ACCOUNT_LOGIN_URL;
	// 中用户支付请求地址
	public static String CITIPAY_PAY_URL;
	// 用户支付前台通知地址
	public static String CITIPAY_PAY_RETURN_URL;
	// 用户支付后台通知地址
	public static String CITIPAY_PAY_NOTIFY_URL;
	// 用户退款请求地址
	public static String CITIPAY_REFUND_URL;
	// 用户退款后台通知地址
	public static String CITIPAY_REFUND_NOTIFY_URL;
	// KEY
	public static String CITIPAY_KEY;

	
	public void setCitipayRegisterUrl(String citipayRegisterUrl) {
		CITIPAY_REGISTER_URL = citipayRegisterUrl;
	}
	public void setCitipayAccountLoginUrl(String citipayAccountLoginUrl) {
		CITIPAY_ACCOUNT_LOGIN_URL = citipayAccountLoginUrl;
	}
	public void setCitipayPayUrl(String citipayPayUrl) {
		CITIPAY_PAY_URL = citipayPayUrl;
	}
	public void setCitipayPayReturnUrl(String citipayPayReturnUrl) {
		CITIPAY_PAY_RETURN_URL = citipayPayReturnUrl;
	}
	public void setCitipayPayNotifyUrl(String citipayPayNotifyUrl) {
		CITIPAY_PAY_NOTIFY_URL = citipayPayNotifyUrl;
	}
	public void setCitipayRefundUrl(String citipayRefundUrl) {
		CITIPAY_REFUND_URL = citipayRefundUrl;
	}
	public void setCitipayRefundNotifyUrl(String citipayRefundNotifyUrl) {
		CITIPAY_REFUND_NOTIFY_URL = citipayRefundNotifyUrl;
	}
	public void setCitipayKey(String citipayKey) {
		CITIPAY_KEY = citipayKey;
	}
	
}
