package com.mall.butler.port.xml;

import javax.xml.bind.annotation.XmlRootElement;

import com.mall.butler.xml.BaseXml;
@XmlRootElement(name="OutputParameter")
public class OutputParameter extends BaseXml{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String KEYE;
	private String KEYN;
	private String OCBILLNO;//注册单号
	private String JZDATE;//记账日期
	private String ZSJFTOTAL;//
	private String VIPCARDNO;//会员卡号
	private String CARDFACENO;//卡面号
	private String CARDTYPECODE;//卡类型编码
	private String CARDTYPENAME;//卡类型名称
	
	public String getOCBILLNO() {
		return OCBILLNO;
	}
	public void setOCBILLNO(String oCBILLNO) {
		OCBILLNO = oCBILLNO;
	}
	public String getJZDATE() {
		return JZDATE;
	}
	public void setJZDATE(String jZDATE) {
		JZDATE = jZDATE;
	}
	public String getZSJFTOTAL() {
		return ZSJFTOTAL;
	}
	public void setZSJFTOTAL(String zSJFTOTAL) {
		ZSJFTOTAL = zSJFTOTAL;
	}
	public String getVIPCARDNO() {
		return VIPCARDNO;
	}
	public void setVIPCARDNO(String vIPCARDNO) {
		VIPCARDNO = vIPCARDNO;
	}
	public String getCARDFACENO() {
		return CARDFACENO;
	}
	public void setCARDFACENO(String cARDFACENO) {
		CARDFACENO = cARDFACENO;
	}
	public String getCARDTYPECODE() {
		return CARDTYPECODE;
	}
	public void setCARDTYPECODE(String cARDTYPECODE) {
		CARDTYPECODE = cARDTYPECODE;
	}
	public String getCARDTYPENAME() {
		return CARDTYPENAME;
	}
	public void setCARDTYPENAME(String cARDTYPENAME) {
		CARDTYPENAME = cARDTYPENAME;
	}
	public String getKEYE() {
		return KEYE;
	}
	public void setKEYE(String kEYE) {
		KEYE = kEYE;
	}
	public String getKEYN() {
		return KEYN;
	}
	public void setKEYN(String kEYN) {
		KEYN = kEYN;
	}
	
	
	

}
