package com.mall.butler.web.model;
import com.mall.butler.model.BaseEntity;

public class PageAreaInfo extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String NAME="name";
	public static final String CODE="code";
	public static final String INFO="info";
	public static final String SHOWFLAG="showFlag";
	public static final String LEN="len";

	private String name;
	private String code;
	private String info;
	private Integer showFlag;
	private Integer len;

	/**
	 *标题
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 *标题
	 */
	public String getName(){
		return this.name;
	}
	/**
	 *手动输入
	 */
	public void setCode(String code){
		this.code = code;
	}
	/**
	 *手动输入
	 */
	public String getCode(){
		return this.code;
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
	 *0:不需要图片 1:需要图片 2:flash 添加时需要操作输入
	 */
	public void setShowFlag(Integer showFlag){
		this.showFlag = showFlag;
	}
	/**
	 *0:不需要图片 1:需要图片 2:flash 添加时需要操作输入
	 */
	public Integer getShowFlag(){
		return this.showFlag;
	}
	/**
	 *显示数量
	 */
	public void setLen(Integer len){
		this.len = len;
	}
	/**
	 *显示数量
	 */
	public Integer getLen(){
		return this.len;
	}
}