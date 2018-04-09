package com.mall.butler.alipay;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
public class AlipayParamXml {
	private String name;
	  private String info;

	  @XmlAttribute(name="name")
	  public String getName()
	  {
	    return this.name;
	  }
	  public void setName(String name) {
	    this.name = name;
	  }
	  @XmlValue
	  public String getInfo() {
	    return this.info;
	  }
	  public void setInfo(String info) {
	    this.info = info;
	  }
}
