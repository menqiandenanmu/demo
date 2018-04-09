package com.mall.butler.action;

import java.io.IOException;
import java.util.Random;

import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.util.common.action.MessageDialog;

public class ReSubmitAction extends BaseAction {
	private static final long serialVersionUID = -3626815387061897877L;
	private String url;

	@Override
	public String execute() throws IOException {
		Random random = new Random();
		String rands = "";
		for (int i = 0; i < 4; i++) {
			rands = rands + random.nextInt(10);
		}
		if (request.getHeader("Referer") != null) {
			url = request.getHeader("Referer") + "&rad=" + rands;
		} else {
			url = null;
		}

		if (this.ajaxRequest) {
			this.msgInfo.setFlag(MessageDialog.ERROR);
			return JDIALOG;
		} else {
			return SUCCESS;
		}
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

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
