package com.mall.butler.web.m.model;

import com.mall.butler.web.model.NewsInfo;

/**
 * @author caedmon
 * @version 创建时间：2012-10-25 下午04:14:46
 */
public class NewsExtendInfo extends NewsInfo{

	private static final long serialVersionUID = 1L;
	//扩展字段
	private String context;
	private String loginName;
	private String imageUrl;
	private String imageUrlMid;
	private String imageUrlSmall;
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImageUrlMid() {
		return imageUrlMid;
	}
	public void setImageUrlMid(String imageUrlMid) {
		this.imageUrlMid = imageUrlMid;
	}
	public String getImageUrlSmall() {
		return imageUrlSmall;
	}
	public void setImageUrlSmall(String imageUrlSmall) {
		this.imageUrlSmall = imageUrlSmall;
	}
	
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
}
