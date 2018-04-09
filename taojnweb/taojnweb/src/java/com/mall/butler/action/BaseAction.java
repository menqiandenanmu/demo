package com.mall.butler.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.GsonBuilder;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.helper.json.JSONHelper;
import com.mall.butler.util.JsonMapper;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.common.lang.DateUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public abstract class BaseAction extends ActionSupport implements Preparable, ServletRequestAware,
		ServletResponseAware {

	// 列表
	protected static final String LIST = "list";
	// 新增页面
	protected static final String ADD = "add";
	// 编辑页面
	protected static final String EDIT = "edit";
	// 详情
	protected static final String INFO = "info";

	// 消息框
	protected static final String DIALOG = "dialog";
	// json 消息
	protected static final String JDIALOG = "jdialog";

	// json
	protected static final String JSON = "NONE";

	// 返回消息内容
	protected MessageDialog msgInfo = new MessageDialog();
	/**
	 * 
	 */
	private static final long serialVersionUID = 5221886497551279265L;

	protected HttpServletResponse response;

	protected HttpServletRequest request;

	protected String base;

	// 当前日期
	protected String currentDate;
	// 当前时间段
	protected String greetings;
	// 请求的json标志
	protected boolean ajaxRequest;
	@Autowired
	protected JSONHelper jsonHelper;
	protected String basePath;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 得到请求的客户端IP
	 * 
	 * @author caedmon
	 * @date 2010-10-15 下午04:30:53
	 * @return
	 */
	public String getClientIp() {
		return this.getRequest().getRemoteAddr();
	}

	public String getUri() {
		String uri = request.getRequestURI();
		return uri;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 返回JSON数据对象
	 * 
	 * @param target
	 */
	protected void renderJson(Object target) {
		PrintWriter out = null;
		try {
			if (this.isAjaxRequest())
				response.setContentType("text/json;charset=UTF-8");
			else
				response.setContentType("text/javascript;charset=UTF-8");
			out = response.getWriter();
			out.write(JsonMapper.toJson(target));
			out.flush();
		} catch (IOException e) {
			throw new RuntimeException("Response writing failure.", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	protected void outputJson(Object target) {
		PrintWriter out = null;
		try {
			response.setContentType("text/json;charset=UTF-8");
			out = response.getWriter();
			out.write(JsonMapper.toJson(target));
			out.flush();
		} catch (IOException e) {
			throw new RuntimeException("Response writing failure.", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public String getBase() {
		if (null == base)
			base = getContextPath();
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getCurrentDate() {
		if (TxtUtil.isEmpty(currentDate)) {
			currentDate = DateUtil.getCurrentDayofWeek().replace("日期：", "");
		}
		return currentDate;
	}

	public String getGreetings() {
		if (TxtUtil.isEmpty(currentDate)) {
			greetings = DateUtil.getGreetings();
		}
		return greetings;
	}

	public void setGreetings(String greetings) {
		this.greetings = greetings;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	private String getContextPath() {
		if (this.getRequest() != null) {
			String p = this.getRequest().getScheme() + "://" + this.getRequest().getServerName();
			if (this.getRequest().getServerPort() != 80)
				p = p + ":" + this.getRequest().getServerPort();
			p = p + this.getRequest().getContextPath();
			return p;
		} else
			return "";

	}

	public boolean isAjaxRequest() {
		String s = request.getHeader("ajax");
		boolean ajax = Boolean.parseBoolean((s == null || s.equals("")) ? "false" : "true");
		return ajax;
	}

	public MessageDialog getMsgInfo() {
		return msgInfo;
	}

	public void write(Object target) {
		try {
			response.setContentType("text/javascript;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonHelper.serialize(target));
			out.close();
		} catch (IOException e) {
			throw new RuntimeException("Response writing failure.", e);
		}
	}

	public void writeXml(String xml) {
		try {
			response.setContentType("text/xml;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(xml);
			out.close();
		} catch (IOException e) {
			throw new RuntimeException("Response writing failure.", e);
		}
	}

	public void print(Object target) {
		try {
			response.setContentType("text/javascript;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(target);
			out.close();
		} catch (IOException e) {
			throw new RuntimeException("Response writing failure.", e);
		}
	}

	public void out(HttpServletResponse response, Object target) {
		GsonBuilder gbuild = new GsonBuilder();
		try {
			response.setContentType("text/javascript;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(gbuild.create().toJson(target));
			out.close();
		} catch (IOException e) {
			throw new RuntimeException("Response writing failure.", e);
		}
	}

	public String getBasePath() {
		if (null == basePath)
			basePath = getContextPath();
		return basePath;
	}

	/**
	 * 得到用账户
	 * 
	 * @return
	 */
	public abstract AccountInfo getAccount();

	/**
	 * 得到登录信息
	 * 
	 * @return
	 */
	public abstract AccountLogin getLogin();

}
