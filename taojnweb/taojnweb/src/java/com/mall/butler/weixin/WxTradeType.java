package com.mall.butler.weixin;

/**
 * 交易类型
 *
 */
public enum WxTradeType {
	JSAPI("JSAPI","JSAPI"),
	NATIVE("NATIVE","NATIVE"),
	;
	
	private String code;
	private String name;
	
	private WxTradeType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
