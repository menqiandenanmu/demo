package com.mall.butler.port.xml;

import javax.xml.bind.annotation.XmlRootElement;

import com.mall.butler.xml.BaseXml;
@XmlRootElement(name="account")
public class TradeAccountXml extends BaseXml{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4096673213757176000L;
	private String accountCode;//登录名唯一标识
	private String mallCode;//商城用户编号
	private String tradeNo;//钱包账户编号
	private String tradeName;//钱包账户名称
	private String password;//账户密码
	private Double amount;//余额
	private Boolean status;//钱包状态0:停用 1启用
	
	public String getMallCode() {
		return mallCode;
	}
	public void setMallCode(String mallCode) {
		this.mallCode = mallCode;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
	
}
