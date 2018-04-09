package com.mall.butler.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.mall.butler.RequestContext;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.action.BaseAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class RequestContextInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6732277495928277831L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		// 设置ip
		String ip = req.getRemoteAddr();
		RequestContext.setRemotIp(ip);
		if (invocation.getAction() instanceof BaseAction) {
			BaseAction action = (BaseAction) invocation.getAction();
			// 设置用户
			AccountInfo account = action.getAccount();
			if (account != null)
				RequestContext.setAccount(account);
			// 设置操作员
			AccountLogin login = action.getLogin();
			if (login != null)
				RequestContext.setLogin(login);
		}
		return invocation.invoke();
	}
}
