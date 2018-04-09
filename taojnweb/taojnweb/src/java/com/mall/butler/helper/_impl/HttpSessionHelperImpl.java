package com.mall.butler.helper._impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.mall.butler.helper.SessionHelper;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * 
 * 项目名称：mall_common 类名称：HttpSessionHelperImpl 类描述： 使用httpSession
 * 實現客戶端和服務器session操作 创建人：caedmon 675053447@gmail.com 创建时间：2011-10-24 上午10:46:48
 * 修改人：caedmon 修改时间：2011-10-24 上午10:46:48 修改备注：
 * 
 * @version
 * 
 */
public class HttpSessionHelperImpl implements SessionHelper {

	@Override
	public void clear() {
		HttpSession session = this.getSession();
		while (session.getAttributeNames().hasMoreElements()) {
			String key = (String) session.getAttributeNames().nextElement();
			session.removeAttribute(key);
		}
	}

	@Override
	public Object get(String key) {
		HttpSession session = this.getSession();
		return session.getAttribute(key);
	}

	@Override
	public void set(String key, Object value) {
		HttpSession session = this.getSession();
		session.setAttribute(key, value);
	}

	private HttpSession getSession() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		return request.getSession();
	}

}
