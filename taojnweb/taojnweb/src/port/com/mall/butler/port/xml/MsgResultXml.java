package com.mall.butler.port.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mall.butler.xml.BaseXml;

@XmlRootElement(name="infos")
public class MsgResultXml extends BaseXml{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8110150693937103945L;
	
	private List<MsgInfoXml> msgInfoXmls;

	@XmlElement
	public List<MsgInfoXml> getMsgInfoXmls() {
		return msgInfoXmls;
	}

	public void setMsgInfoXmls(List<MsgInfoXml> msgInfoXmls) {
		this.msgInfoXmls = msgInfoXmls;
	}
	
}
