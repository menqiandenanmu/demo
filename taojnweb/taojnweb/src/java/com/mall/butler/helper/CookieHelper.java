package com.mall.butler.helper;

import java.io.IOException;

public interface CookieHelper {

	/**
	 * 新增cookie字符串
	 * 
	 * @param name
	 * @param value
	 */
	public void set(String name, String value);

	/**
	 * 获取cookie字符串
	 * 
	 * @param name
	 * @return
	 */
	public String get(String name);

	/**
	 * 新增cookie[无path区分]
	 * 
	 * @param name
	 * @param value
	 */
	public void setNoPath(String name, String value);

	/**
	 * 获取cookie字符串[无path区分]
	 * 
	 * @param name
	 * @return
	 */
	public String getNoPath(String name);

	/**
	 * 新增cookie对象(需序列化)
	 * 
	 * @param name
	 * @param value
	 * @throws IOException
	 */
	public void setObj(String name, Object value);

	/**
	 * 通过cookie名获取cookie值(需反序列化)
	 * 
	 * @param name
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object geteObj(String name);
}
