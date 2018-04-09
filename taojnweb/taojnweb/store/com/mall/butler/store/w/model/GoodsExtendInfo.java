package com.mall.butler.store.w.model;

import com.mall.butler.store.model.GoodsInfo;

/**
 * @author caedmon
 * @version 创建时间：2013-3-5 下午01:59:17
 */
public class GoodsExtendInfo extends GoodsInfo {

	private static final long serialVersionUID = 1L;
	/**
	 * 原图
	 */
	private String imageUrl;
	/**
	 * 中图
	 */
	private String imageUrlMid;
	/**
	 * 缩略图
	 */
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
}
