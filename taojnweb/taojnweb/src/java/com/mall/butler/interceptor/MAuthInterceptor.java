package com.mall.butler.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.account.m.service.MAccountService;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.action.BaseAction;
import com.mall.butler.exception.NeedPowerException;
import com.mall.butler.exception.NoLoginException;
import com.mall.butler.helper.SessionHelper;
import com.mall.butler.sys.model.SysFunctions;
import com.mall.butler.sys.service.RoleService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class MAuthInterceptor implements Interceptor {
	private static final long serialVersionUID = -786211121537462672L;
	@Autowired
	private RoleService roleService;
	@Autowired
	private SessionHelper sessionHelper;
	@Autowired
	private MAccountService mAccountService;

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		AccountLogin login = getLogin(req);
		if (login == null)
			throw new NoLoginException("未登录!");
		if (invocation.getAction() instanceof BaseAction) {
			String regStr = "(^(\\w|\\\\)+)(\\!|.)";
			Pattern pattern = Pattern.compile(regStr);
			Matcher m = pattern.matcher(req.getRequestURI());
			if (m.find()) {
				String url = m.group(1);
				SysFunctions funs = roleService.queryFuncByUrl(url);
				if (funs != null)
					if (roleService.checkFunInRole(funs, login))
						throw new NeedPowerException("无权限访问!");
			}
		}
		return invocation.invoke();
	}

	public AccountLogin getLogin(HttpServletRequest request) {
		Object object = sessionHelper.get(ManageContext.SESSION_LOGINID);
		if (object == null)
			return null;
		Long pk = (Long) object;
		if (pk != null)
			return mAccountService.getEntityById(AccountLogin.class, pk);
		else
			return null;
	}
}
