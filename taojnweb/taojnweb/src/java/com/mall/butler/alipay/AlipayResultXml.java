package com.mall.butler.alipay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="alipay")
public class AlipayResultXml
{
  public static final String TRADE_NOT_EXIST = "TRADE_NOT_EXIST";
  public static final String ILLEGAL_ARGUMENT = "ILLEGAL_ARGUMENT";
  public static final String TRADE_STATUS_NOT_AVAILD = "TRADE_STATUS_NOT_AVAILD";
  public static final String GENERAL_FAILTURE = "GENERAL_FAILTURE";
  public static final String SUCCESS = "T";
  public static final String ERROR = "F";
  private String is_success;
  private String error;

  @XmlElement
  public String getIs_success()
  {
    return this.is_success;
  }
  public void setIs_success(String isSuccess) {
    this.is_success = isSuccess;
  }
  @XmlElement
  public String getError() {
    return this.error;
  }
  public void setError(String error) {
    this.error = error;
  }
}
