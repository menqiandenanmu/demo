package com.mall.butler.alipay;

public class AlipayTransVo {
	private String service;
	  private String partner;
	  private String notifyUrl;
	  private String returnUrl;
	  private String sign;
	  private String sign_type;
	  private String subject;
	  private String body;
	  private String out_trade_no;
	  private String total_fee;
	  private String payment_type;
	  private String input_charset;
	  private String seller_email;
	  private String paymethod;
	  private String defaultback;
	  private String out_order_no;
	  private String lt_b_pay;
	  private String credit_card_pay;
	  private String credit_card_default_display;
	  private String url;

	  public String getOut_order_no()
	  {
	    return this.out_order_no;
	  }

	  public void setOut_order_no(String outOrderNo) {
	    this.out_order_no = outOrderNo;
	  }

	  public String getService()
	  {
	    return this.service;
	  }

	  public void setService(String service) {
	    this.service = service;
	  }

	  public String getPartner() {
	    return this.partner;
	  }

	  public void setPartner(String partner) {
	    this.partner = partner;
	  }

	  public String getNotifyUrl() {
	    return this.notifyUrl;
	  }

	  public void setNotifyUrl(String notifyUrl) {
	    this.notifyUrl = notifyUrl;
	  }

	  public String getReturnUrl() {
	    return this.returnUrl;
	  }

	  public void setReturnUrl(String returnUrl) {
	    this.returnUrl = returnUrl;
	  }

	  public String getSign() {
	    return this.sign;
	  }

	  public void setSign(String sign) {
	    this.sign = sign;
	  }

	  public String getSign_type() {
	    return this.sign_type;
	  }

	  public void setSign_type(String signType) {
	    this.sign_type = signType;
	  }

	  public String getSubject() {
	    return this.subject;
	  }

	  public void setSubject(String subject) {
	    this.subject = subject;
	  }

	  public String getBody() {
	    return this.body;
	  }

	  public void setBody(String body) {
	    this.body = body;
	  }

	  public String getOut_trade_no() {
	    return this.out_trade_no;
	  }

	  public void setOut_trade_no(String outTradeNo) {
	    this.out_trade_no = outTradeNo;
	  }

	  public String getTotal_fee() {
	    return this.total_fee;
	  }

	  public void setTotal_fee(String totalFee) {
	    this.total_fee = totalFee;
	  }

	  public String getPayment_type() {
	    return this.payment_type;
	  }

	  public void setPayment_type(String paymentType) {
	    this.payment_type = paymentType;
	  }

	  public String getSeller_email() {
	    return this.seller_email;
	  }

	  public void setSeller_email(String sellerEmail) {
	    this.seller_email = sellerEmail;
	  }

	  public String getInput_charset() {
	    return this.input_charset;
	  }

	  public void setInput_charset(String inputCharset) {
	    this.input_charset = inputCharset;
	  }

	  public String getUrl() {
	    return this.url;
	  }

	  public void setUrl(String url) {
	    this.url = url;
	  }

	  public String getPaymethod() {
	    return this.paymethod;
	  }

	  public void setPaymethod(String paymethod) {
	    this.paymethod = paymethod;
	  }

	  public String getDefaultback() {
	    return this.defaultback;
	  }

	  public void setDefaultback(String defaultback) {
	    this.defaultback = defaultback;
	  }

	  public void setLt_b_pay(String lt_b_pay) {
	    this.lt_b_pay = lt_b_pay;
	  }

	  public String getLt_b_pay() {
	    return this.lt_b_pay;
	  }

	  public void setCredit_card_pay(String credit_card_pay) {
	    this.credit_card_pay = credit_card_pay;
	  }

	  public String getCredit_card_pay() {
	    return this.credit_card_pay;
	  }

	  public void setCredit_card_default_display(String credit_card_default_display)
	  {
	    this.credit_card_default_display = credit_card_default_display;
	  }

	  public String getCredit_card_default_display() {
	    return this.credit_card_default_display;
	  }
}
