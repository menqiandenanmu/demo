package com.mall.butler.alipay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="trade")
public class AlipayTradeXml
{
  private String body;
  private String buyer_email;
  private String buyer_id;
  private String discount;
  private String gmt_create;
  private String gmt_last_modified_time;
  private String gmt_payment;
  private String is_total_fee_adjust;
  private String out_trade_no;
  private String payment_type;
  private String price;
  private String quantity;
  private String seller_email;
  private String seller_id;
  private String subject;
  private String total_fee;
  private String trade_no;
  private String trade_status;
  private String use_coupon;

  @XmlElement
  public String getBody()
  {
    return this.body;
  }
  public void setBody(String body) {
    this.body = body;
  }
  @XmlElement
  public String getBuyer_email() {
    return this.buyer_email;
  }
  public void setBuyer_email(String buyerEmail) {
    this.buyer_email = buyerEmail;
  }
  @XmlElement
  public String getBuyer_id() {
    return this.buyer_id;
  }
  public void setBuyer_id(String buyerId) {
    this.buyer_id = buyerId;
  }
  @XmlElement
  public String getDiscount() {
    return this.discount;
  }
  public void setDiscount(String discount) {
    this.discount = discount;
  }
  @XmlElement
  public String getGmt_create() {
    return this.gmt_create;
  }
  public void setGmt_create(String gmtCreate) {
    this.gmt_create = gmtCreate;
  }
  @XmlElement
  public String getGmt_last_modified_time() {
    return this.gmt_last_modified_time;
  }
  public void setGmt_last_modified_time(String gmtLastModifiedTime) {
    this.gmt_last_modified_time = gmtLastModifiedTime;
  }
  @XmlElement
  public String getGmt_payment() {
    return this.gmt_payment;
  }
  public void setGmt_payment(String gmtPayment) {
    this.gmt_payment = gmtPayment;
  }
  @XmlElement
  public String getIs_total_fee_adjust() {
    return this.is_total_fee_adjust;
  }
  public void setIs_total_fee_adjust(String isTotalFeeAdjust) {
    this.is_total_fee_adjust = isTotalFeeAdjust;
  }
  @XmlElement
  public String getOut_trade_no() {
    return this.out_trade_no;
  }
  public void setOut_trade_no(String outTradeNo) {
    this.out_trade_no = outTradeNo;
  }
  @XmlElement
  public String getPayment_type() {
    return this.payment_type;
  }
  public void setPayment_type(String paymentType) {
    this.payment_type = paymentType;
  }
  @XmlElement
  public String getPrice() {
    return this.price;
  }
  public void setPrice(String price) {
    this.price = price;
  }
  @XmlElement
  public String getQuantity() {
    return this.quantity;
  }
  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }
  @XmlElement
  public String getSeller_email() {
    return this.seller_email;
  }
  public void setSeller_email(String sellerEmail) {
    this.seller_email = sellerEmail;
  }
  @XmlElement
  public String getSeller_id() {
    return this.seller_id;
  }
  public void setSeller_id(String sellerId) {
    this.seller_id = sellerId;
  }
  @XmlElement
  public String getSubject() {
    return this.subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }
  @XmlElement
  public String getTotal_fee() {
    return this.total_fee;
  }
  public void setTotal_fee(String totalFee) {
    this.total_fee = totalFee;
  }
  @XmlElement
  public String getTrade_no() {
    return this.trade_no;
  }
  public void setTrade_no(String tradeNo) {
    this.trade_no = tradeNo;
  }
  @XmlElement
  public String getTrade_status() {
    return this.trade_status;
  }
  public void setTrade_status(String tradeStatus) {
    this.trade_status = tradeStatus;
  }
  @XmlElement
  public String getUse_coupon() {
    return this.use_coupon;
  }
  public void setUse_coupon(String useCoupon) {
    this.use_coupon = useCoupon;
  }
}