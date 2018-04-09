package com.mall.butler.alipay;

public class AlipayContext {
	public static String SERVICE_URL;
	  public static String SERVICE_URL_HTTPS;
	  public static String PARTNER = "";
	  public static String KEY;
	  public static String PAYMENT_TYPE = "1";
	  public static String SELLER_EMAIL;
	  public static String SERVICE_PAY;
	  public static String PAY_RETURN;
	  public static String PAY_NOTIFY;
	  public static String SUBJECT;
	  public static String BODY;
	  public static String SIGN_TYPE;
	  public static String INPUT_CHARSET;
	  public static String PAYMETHOD;
	  public static String CHECK_NOTIFY_URL;
	  public static String LT_B_PAY;
	  public static String SERVICE_CLOSE_TRADE;
	  public static String SERVICE_TRADE_QUERY;
	  public static String TRANS_BEGSTR;
	  public static String QUERY_INPUT_CHARSET;

	  public void setQuery_input_charset(String query_input_charset)
	  {
	    QUERY_INPUT_CHARSET = query_input_charset;
	  }

	  public void setService_close_trade(String serviceCloseTrade) {
	    SERVICE_CLOSE_TRADE = serviceCloseTrade;
	  }

	  public void setService_trade_query(String serviceTradeQuery) {
	    SERVICE_TRADE_QUERY = serviceTradeQuery;
	  }

	  public void setLt_b_pay(String lt_b_pay) {
	    LT_B_PAY = lt_b_pay;
	  }

	  public void setPartner(String partner) {
	    PARTNER = partner;
	  }

	  public void setKey(String key) {
	    KEY = key;
	  }

	  public void setPayment_type(String paymentType) {
	    PAYMENT_TYPE = paymentType;
	  }

	  public void setSeller_email(String sellerEmail) {
	    SELLER_EMAIL = sellerEmail;
	  }

	  public void setService_pay(String servicePay) {
	    SERVICE_PAY = servicePay;
	  }

	  public void setService_url(String serviceUrl) {
	    SERVICE_URL = serviceUrl;
	  }

	  public void setService_url_https(String serviceUrlHttps) {
	    SERVICE_URL_HTTPS = serviceUrlHttps;
	  }

	  public void setPay_return(String payReturn) {
	    PAY_RETURN = payReturn;
	  }

	  public void setPay_notify(String payNotify) {
	    PAY_NOTIFY = payNotify;
	  }

	  public void setSubject(String subject) {
	    SUBJECT = subject;
	  }

	  public void setBody(String body) {
	    BODY = body;
	  }

	  public void setSign_type(String signType) {
	    SIGN_TYPE = signType;
	  }

	  public void setInput_charset(String inputCharset) {
	    INPUT_CHARSET = inputCharset;
	  }

	  public void setPaymethod(String paymethod) {
	    PAYMETHOD = paymethod;
	  }

	  public void setNotify_query_url(String notify_query_url) {
	    CHECK_NOTIFY_URL = notify_query_url;
	  }

	  public void setTrans_begstr(String transBegstr) {
	    TRANS_BEGSTR = transBegstr;
	  }
}
