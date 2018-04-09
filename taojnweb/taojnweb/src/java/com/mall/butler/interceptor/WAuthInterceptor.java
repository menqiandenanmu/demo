package com.mall.butler.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.WebsiteContext;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.w.service.WAccountService;
import com.mall.butler.helper.SessionHelper;
import com.mall.util.common.TxtUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 前台登陆拦截器
 * 
 * @author caedmon
 * 
 */
public class WAuthInterceptor implements Interceptor {
	private static final long serialVersionUID = -786211121537462672L;
	@Autowired
	private SessionHelper sessionHelper;
	@Autowired
	private WAccountService wAccountService;

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		AccountLogin login = getLogin(req);
		if (login == null) {
		String url = req.getRequestURL().toString();
		if (!TxtUtil.isEmpty(req.getQueryString()))
			url = url + "?" + req.getQueryString();
		sessionHelper.set(WebsiteContext.BEFORE_LOGIN_URL, url);
		return "login";
		}
		return invocation.invoke();
	}

	public AccountLogin getLogin(HttpServletRequest request) {
		Object object = sessionHelper.get(WebsiteContext.SESSION_LOGINID);
		if (object == null)
			return null;
		Long pk = (Long) object;
		if (pk != null)
			return wAccountService.getEntityById(AccountLogin.class, pk);
		else
			return null;
	}
}
