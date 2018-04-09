package com.mall.butler.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.exception.NoLoginException;
import com.mall.util.common.action.MessageDialog;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.ExceptionHolder;
import com.opensymphony.xwork2.ognl.OgnlValueStack;

public class ErrorAction extends BaseAction {
	private final static Log log = LogFactory.getLog(ErrorAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 3285900167767311158L;

	public String execute() {
		this.msgInfo.setFlag(MessageDialog.ERROR);
		OgnlValueStack stack = (OgnlValueStack) ActionContext.getContext()
				.getValueStack();
		for (int i = 0; i < stack.size(); i++) {
			Object o = stack.pop();
			if (o instanceof ExceptionHolder) {
				Exception e = ((ExceptionHolder) o).getException();
				e.printStackTrace();
				msgInfo.getMessages().add(e.getMessage() + "");
				log.error(e);
				if (e instanceof NoLoginException) {
					if (!this.isAjaxRequest())
						return LOGIN;
				}
			}
		}
		if (this.isAjaxRequest())
			return JDIALOG;
		else
			return DIALOG;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public AccountInfo getAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountLogin getLogin() {
		// TODO Auto-generated method stub
		return null;
	}
}
