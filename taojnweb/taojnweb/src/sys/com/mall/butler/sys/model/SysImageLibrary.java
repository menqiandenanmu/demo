package com.mall.butler.sys.model;

import com.mall.butler.model.BaseEntity;

public class SysImageLibrary extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String IMAGEURL = "imageUrl";
	public static final String IMAGEURLMID = "imageUrlMid";
	public static final String IMAGEURLSMALL = "imageUrlSmall";
	public static final String IMAGETYPE = "imageType";
	public static final String TARGETTYPE = "targetType";
	public static final String TARGETID = "targetId";
	public static final String REMARK = "remark";

	private String imageUrl;
	private String imageUrlMid;
	private String imageUrlSmall;
	private Integer imageType;
	private Integer targetType;
	private Long targetId;
	private String remark;

	/**
	 *原图路径
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 *原图路径
	 */
	public String getImageUrl() {
		return this.imageUrl;
	}

	/**
	 *中图路径
	 */
	public void setImageUrlMid(String imageUrlMid) {
		this.imageUrlMid = imageUrlMid;
	}

	/**
	 *中图路径
	 */
	public String getImageUrlMid() {
		return this.imageUrlMid;
	}

	/**
	 *小图路径
	 */
	public void setImageUrlSmall(String imageUrlSmall) {
		this.imageUrlSmall = imageUrlSmall;
	}

	/**
	 *小图路径
	 */
	public String getImageUrlSmall() {
		return this.imageUrlSmall;
	}

	/**
	 *图片类型 0内部图片 1外部URL
	 */
	public void setImageType(Integer imageType) {
		this.imageType = imageType;
	}

	/**
	 *图片类型 0内部图片 1外部URL
	 */
	public Integer getImageType() {
		return this.imageType;
	}

	/**
	 *1景区 2.酒店 3.饭店 4.线路 5.车辆 6.商品 7.商铺
	 */
	public void setTargetType(Integer targetType) {
		this.targetType = targetType;
	}

	/**
	 *1景区 2.酒店 3.饭店 4.线路 5.车辆 6.商品7.商铺
	 */
	public Integer getTargetType() {
		return this.targetType;
	}

	/**
	 *门票，景区等的ID
	 */
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	/**
	 *门票，景区等的ID
	 */
	public Long getTargetId() {
		return this.targetId;
	}

	/**
	 *说明 审核失败说明
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 *说明 审核失败说明
	 */
	public String getRemark() {
		return this.remark;
	}
}