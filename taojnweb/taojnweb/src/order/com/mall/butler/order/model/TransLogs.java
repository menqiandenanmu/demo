package com.mall.butler.order.model;
import com.mall.butler.model.BaseEntity;

public class TransLogs extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String URL="url";
	public static final String QUERYSTR="queryStr";
	public static final String FLAG="flag";

	private String url;
	private String queryStr;
	private Integer flag;

	/**
	 *
	 */
	public void setUrl(String url){
		this.url = url;
	}
	/**
	 *
	 */
	public String getUrl(){
		return this.url;
	}
	/**
	 *
	 */
	public void setQueryStr(String queryStr){
		this.queryStr = queryStr;
	}
	/**
	 *
	 */
	public String getQueryStr(){
		return this.queryStr;
	}
	/**
	 *0:发送 1:接收
	 */
	public void setFlag(Integer flag){
		this.flag = flag;
	}
	/**
	 *0:发送 1:接收
	 */
	public Integer getFlag(){
		return this.flag;
	}
}