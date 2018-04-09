package com.mall.butler.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.helper.SessionHelper;
import com.mall.butler.util.UrlStack;
import com.mall.util.common.TxtUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class MUrlStackInterceptor implements Interceptor {
	private static final long serialVersionUID = -786211121537462672L;
	@Autowired
	private SessionHelper sessionHelper;

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		if (invocation.getAction() instanceof ManageBaseAction) {
			ManageBaseAction action = (ManageBaseAction) invocation.getAction();
			if (!action.isAjaxRequest()) {
				Object obj = sessionHelper
						.get(ManageContext.SESSION_URLSTACKID);
				UrlStack urlStack;
				if (obj != null)
					urlStack = (UrlStack) obj;
				else {
					urlStack = new UrlStack();
					sessionHelper.set(ManageContext.SESSION_URLSTACKID,
							urlStack);

				}
				String url = req.getRequestURL().toString();
				if (!TxtUtil.isEmpty(req.getQueryString()))
					url = url + "?" + req.getQueryString();
				urlStack.addUrl(url);
			}
		}
		return invocation.invoke();
	}
}
