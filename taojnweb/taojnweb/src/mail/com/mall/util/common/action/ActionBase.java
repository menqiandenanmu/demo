package com.mall.util.common.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * 类名称：ActionBase
 * 类描述：
 * 创建人：caedmon
 * 创建时间：2013-4-26 下午01:58:51
 * 修改人：caedmon
 * 修改时间：2013-4-26 下午01:58:51
 * 修改备注：
 * 
 * @version
 */
public class ActionBase extends ActionSupport implements Preparable,
		SessionAware, ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 4833974912206849234L;
	protected static final String THEME_ROOT = "theme";
	protected String theme = "default";
	protected static final String MESSAGE = "message";
	protected static final String MESSAGEX = "messageX";
	protected static final String REDIRECT = "redirect";
	private Map<String, Object> session;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected String base;
	protected String message;
	private String javascript;
	private String title = "淘江南对账";

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJavascript() {
		return javascript;
	}

	public void setJavascript(String javascript) {
		this.javascript = javascript;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTheme() {
		// return THEME_ROOT + "/" + theme;
		return theme.startsWith(THEME_ROOT + "/") ? theme : THEME_ROOT + "/"
				+ theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	protected MessageDialog msg = new MessageDialog();

	public MessageDialog getMsg() {
		return msg;
	}

	public void setMsg(MessageDialog msg) {
		this.msg = msg;
	}

	public void prepare() throws Exception {
	}

	@SuppressWarnings("unchecked")
	public void setSession(Map session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	protected boolean anonymousAccess = false;

	public boolean isAnonymousAccess() {
		return anonymousAccess;
	}

	public void setAnonymousAccess(boolean anonymousAccess) {
		this.anonymousAccess = anonymousAccess;
	}

	/**
	 * @Title: ajxa 返回页面json格式文本数据
	 * @auth：caedmon
	 * @date 2013-4-26 下午02:00:21
	 * @return
	 */
	public void outJsonString(String str) {
		this.getResponse().setContentType("text/javascript;charset=UTF-8");
		outString(str);
	}

	/**
	 * @Title:write给rpc调用者
	 * @auth：caedmon
	 * @date 2013-4-26 下午02:00:40
	 * @return
	 */
	public void outString(String str) {
		try {
			PrintWriter out = this.getResponse().getWriter();
			out.write(str);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: ajax 返回页面xml格式文本数据
	 * @auth：caedmon
	 * @date 2013-4-26 下午02:00:51
	 * @return
	 */
	public void outXMLString(String xmlStr) {
		this.getResponse().setContentType("application/xml;charset=UTF-8");
		outString(xmlStr);
	}

	private String getContextPath() {
		return this.getRequest() == null ? "" : this.getRequest()
				.getContextPath();
	}

	/**
	 * @Title:获取项目根目录
	 * @auth：caedmon
	 * @date 2013-4-26 下午02:01:02
	 * @return
	 */
	public String getBase() {
		if (null == base)
			base = getContextPath();
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}
}
