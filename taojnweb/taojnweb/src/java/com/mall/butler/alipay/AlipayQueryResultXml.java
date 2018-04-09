package com.mall.butler.alipay;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="alipay")
@XmlAccessorType(XmlAccessType.FIELD)
public class AlipayQueryResultXml {
	public static final String TRADE_SUCCESS="TRADE_SUCCESS";
	
	private String sign;
	private String sign_type;
	private List<AlipayParamXml> request;
	@XmlElementWrapper
	@XmlElement(name="trade")
	private List<AlipayTradeXml> response;
	private String is_success;
	private String error;
	public String getIs_success() {
		return is_success;
	}
	public void setIs_success(String isSuccess) {
		is_success = isSuccess;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String signType) {
		sign_type = signType;
	}
	public List<AlipayParamXml> getRequest() {
		return request;
	}
	public void setRequest(List<AlipayParamXml> request) {
		this.request = request;
	}
	public List<AlipayTradeXml> getResponse() {
		return response;
	}
	public void setResponse(List<AlipayTradeXml> response) {
		this.response = response;
	}

	
	
}
