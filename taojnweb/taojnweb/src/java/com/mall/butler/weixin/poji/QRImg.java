package com.mall.butler.weixin.poji;

public class QRImg {

	private String ticket;
	private String url;
	private String expire_seconds;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(String expireSeconds) {
		expire_seconds = expireSeconds;
	}

}
