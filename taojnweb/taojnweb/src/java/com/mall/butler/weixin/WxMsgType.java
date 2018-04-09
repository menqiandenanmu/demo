package com.mall.butler.weixin;

/**
 * 微信消息类型
 *
 */
public enum WxMsgType {
	NEWS("NEWS","图文消息"),
	IMAGE("image","图片消息"),
	TEXT("TEXT","文本消息"),
	;
	
	private String code;
	private String name;
	
	private WxMsgType(String code, String name) {
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
