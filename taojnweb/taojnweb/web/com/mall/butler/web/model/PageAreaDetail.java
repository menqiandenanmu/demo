package com.mall.butler.web.model;
import com.mall.butler.model.BaseEntity;

public class PageAreaDetail extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String TITLE="title";
	public static final String AREAID="areaId";
	public static final String AREANAME="areaName";
	public static final String SHOWURL="showUrl";
	public static final String INFOURL="infoUrl";
	public static final String INFO="info";
	public static final String ORDERID="orderid";
	public static final String TARGETFLAG="targetFlag";

	private String title;
	private Long areaId;
	private String areaName;
	private String showUrl;
	private String infoUrl;
	private String info;
	private Integer orderid;
	private Boolean targetFlag;

	/**
	 *标题
	 */
	public void setTitle(String title){
		this.title = title;
	}
	/**
	 *标题
	 */
	public String getTitle(){
		return this.title;
	}
	/**
	 *
	 */
	public void setAreaId(Long areaId){
		this.areaId = areaId;
	}
	/**
	 *
	 */
	public Long getAreaId(){
		return this.areaId;
	}
	/**
	 *
	 */
	public void setAreaName(String areaName){
		this.areaName = areaName;
	}
	/**
	 *
	 */
	public String getAreaName(){
		return this.areaName;
	}
	/**
	 *显示内容URL 空，图片，flash 
	 */
	public void setShowUrl(String showUrl){
		this.showUrl = showUrl;
	}
	/**
	 *显示内容URL 空，图片，flash 
	 */
	public String getShowUrl(){
		return this.showUrl;
	}
	/**
	 *连接URL
	 */
	public void setInfoUrl(String infoUrl){
		this.infoUrl = infoUrl;
	}
	/**
	 *连接URL
	 */
	public String getInfoUrl(){
		return this.infoUrl;
	}
	/**
	 *说明内容 ，及图片格式等 。
	 */
	public void setInfo(String info){
		this.info = info;
	}
	/**
	 *说明内容 ，及图片格式等 。
	 */
	public String getInfo(){
		return this.info;
	}
	/**
	 *排序值
	 */
	public void setOrderid(Integer orderid){
		this.orderid = orderid;
	}
	/**
	 *排序值
	 */
	public Integer getOrderid(){
		return this.orderid;
	}
	/**
	 *0:当前页面打开 1：新窗口打开
	 */
	public void setTargetFlag(Boolean targetFlag){
		this.targetFlag = targetFlag;
	}
	/**
	 *0:当前页面打开 1：新窗口打开
	 */
	public Boolean getTargetFlag(){
		return this.targetFlag;
	}
}