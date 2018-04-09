package com.mall.butler.helper._impl;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.mall.butler.helper.CookieHelper;
import com.mall.butler.util.SerializeUtil;
import com.opensymphony.xwork2.ActionContext;

public class CookieHelperImpl implements CookieHelper {
	// 过期时间
	// private Integer timeOut = 3600*24*365;
	private Integer timeOut = 1800;
	// 路径
	private String path = "/";

	/**
	 * 新增cookie字符串
	 * 
	 * @param name
	 * @param value
	 */
	@Override
	public void set(String name, String value) {
		HttpServletResponse response = getResponse();

		Cookie cookie = new Cookie(path + name, value);
		cookie.setPath("/");
		cookie.setMaxAge(timeOut);
		response.addCookie(cookie);
	}

	/**
	 * 获取cookie字符串
	 * 
	 * @param name
	 * @return
	 */
	@Override
	public String get(String name) {
		HttpServletRequest request = getRequest();
		Cookie[] cookies = request.getCookies();
		if (null == cookies || cookies.length == 0) {
			return null;
		}
		String key = path + name;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(key)) {
				return cookie.getValue();
			}
		}
		return null;
	}

	@Override
	public void setNoPath(String name, String value) {
		HttpServletResponse response = getResponse();

		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 获取cookie字符串
	 * 
	 * @param name
	 * @return
	 */
	@Override
	public String getNoPath(String name) {
		HttpServletRequest request = getRequest();
		Cookie[] cookies = request.getCookies();
		if (null == cookies || cookies.length == 0) {
			return null;
		}
		String key = name;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(key)) {
				return cookie.getValue();
			}
		}
		return null;
	}

	/**
	 * 新增cookie对象(需序列化)
	 * 
	 * @param name
	 * @param value
	 * @throws IOException
	 */
	@Override
	public void setObj(String name, Object value) {
		HttpServletResponse response = getResponse();

		String cookieVal = SerializeUtil.serialize(value);
		Cookie cookie = new Cookie(name, cookieVal);
		response.addCookie(cookie);
	}

	/**
	 * 通过cookie名获取cookie值(需反序列化)
	 * 
	 * @param name
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Override
	public Object geteObj(String name) {
		HttpServletRequest request = getRequest();

		Cookie[] cookies = request.getCookies();
		if (null == cookies || cookies.length == 0) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				String cookieVal = cookie.getValue();
				return SerializeUtil.deSerialize(cookieVal);
			}
		}

		return null;
	}

	private static HttpServletRequest getRequest() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);

		return request;
	}

	private static HttpServletResponse getResponse() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		return response;
	}

	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
