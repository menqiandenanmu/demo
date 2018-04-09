package com.mall.butler.account.m.model;

import com.mall.butler.account.model.TradeAccount;

public class TradeAccountVo extends TradeAccount {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8231286679268735511L;
	private String corpName;
	private String accResource;//用户来源

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getAccResource() {
		return accResource;
	}

	public void setAccResource(String accResource) {
		this.accResource = accResource;
	}

}
