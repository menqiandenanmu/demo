package com.mall.butler.alipay;

public class AlipayRoyaltyVo {
	private String url;
	  private String service;
	  private String partner;
	  private String notify_url;
	  private String sign;
	  private String sign_type;
	  private String input_charset;
	  private String trade_no;
	  private String out_bill_no;
	  private String royalty_type;
	  private String royalty_parameters;

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
	  public String getNotify_url() {
	    return this.notify_url;
	  }
	  public void setNotify_url(String notifyUrl) {
	    this.notify_url = notifyUrl;
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
	  public String getInput_charset() {
	    return this.input_charset;
	  }
	  public void setInput_charset(String inputCharset) {
	    this.input_charset = inputCharset;
	  }
	  public String getTrade_no() {
	    return this.trade_no;
	  }
	  public void setTrade_no(String tradeNo) {
	    this.trade_no = tradeNo;
	  }
	  public String getOut_bill_no() {
	    return this.out_bill_no;
	  }
	  public void setOut_bill_no(String outBillNo) {
	    this.out_bill_no = outBillNo;
	  }
	  public String getRoyalty_type() {
	    return this.royalty_type;
	  }
	  public void setRoyalty_type(String royaltyType) {
	    this.royalty_type = royaltyType;
	  }
	  public String getRoyalty_parameters() {
	    return this.royalty_parameters;
	  }
	  public void setRoyalty_parameters(String royaltyParameters) {
	    this.royalty_parameters = royaltyParameters;
	  }
	  public String getUrl() {
	    return this.url;
	  }
	  public void setUrl(String url) {
	    this.url = url;
	  }
}
