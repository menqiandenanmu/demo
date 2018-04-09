package com.mall.butler.store.m.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.MStoreInfoService;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.DiscountRules;
import com.mall.butler.account.model.StoreInfo;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class StoreIndexAction extends ManageBaseAction {

	/**
	 * 商户首页
	 */
	private static final long serialVersionUID = 1L;
	private StoreInfo storeInfo;
	private AccountLogin accountLogin;
	@Autowired
	private MStoreInfoService storeInfoService;

	public String execute() {

		// 查询个人信息
		AccountInfo accountInfo = super.getAccount();
		accountLogin = storeInfoService.getEntityById(AccountLogin.class,
				accountInfo.getId());
		storeInfo = storeInfoService.getEntityById(StoreInfo.class, accountInfo
				.getId());
		return SUCCESS;
	}

	/**
	 * 生成二维码
	 */
	public void grentQr() {

	}

	/**
	 * 更新操作
	 * 
	 * @return
	 */
	public String update() {
		storeInfo.setId(super.getAccount().getId());
		storeInfoService.doUpdateUser(storeInfo, accountLogin);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("更新成功!");
		return JDIALOG;
	}

	
	public StoreInfo getStoreInfo() {
		return storeInfo;
	}

	public void setStoreInfo(StoreInfo storeInfo) {
		this.storeInfo = storeInfo;
	}

	public AccountLogin getAccountLogin() {
		return accountLogin;
	}

	public void setAccountLogin(AccountLogin accountLogin) {
		this.accountLogin = accountLogin;
	}

}
