package com.mall.butler.port.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.mall.butler.xml.BaseXml;
@XmlRootElement(name="transList")
public class TransListXml extends BaseXml {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8362722272269237546L;
	private List<TransInfoXml> transInfoXmls;

	public List<TransInfoXml> getTransInfoXmls() {
		return transInfoXmls;
	}

	public void setTransInfoXmls(List<TransInfoXml> transInfoXmls) {
		this.transInfoXmls = transInfoXmls;
	}

}
