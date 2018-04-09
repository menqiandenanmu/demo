package com.mall.butler.port.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mall.butler.xml.BaseXml;

@XmlRootElement(name = "info")
public class MsgInfoXml extends BaseXml {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4425694144709740514L;
	private String state;
	private String msg_id;
	@XmlElement
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@XmlElement
	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msgId) {
		msg_id = msgId;
	}

}
