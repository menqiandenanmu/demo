package com.mall.butler.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * xml 对象
 * @author caedmon
 */
@XmlRootElement(name="respone")
public class BaseXml implements Serializable{
	private static final long serialVersionUID = 5595459563125465843L;
	public static final String ERROR="1";//失败
	public static final String SUCCESS="0";//成功
	
	private String code;		//返回编号
	private String msg;			//返回消息
	
	private String result;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
