package com.mall.butler.account.w.service;

import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.service.BaseService;

/**
 * 前台账户管理业务 wangxy
 */
public interface WAccountService extends BaseService {
	/**
	 * 处理前台用户登录
	 * 
	 * @param accountLogin
	 * @return
	 */
	AccountLogin doOperatorLogin(AccountLogin accountLogin);

	/**
	 * 更新前台用户信息
	 * 
	 * @param agentInfo
	 */
	void doUpateLoginInfo(AccountLogin accountLogin);

	/**
	 * 注册用户
	 * 
	 * @param accountLogin
	 * @return
	 */
	Long doRegConsumer(AccountLogin accountLogin);

	/**
	 * 修改密码
	 * 
	 * @param login
	 */
	public void doUpdateLoginPass(AccountLogin login);

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param accountLogin
	 * @return
	 */
	AccountLogin findByLoginName(String loginName);
/**
 * 更新用户信息
 * @param accountLogin
 * @param tradeAccount 
 */
	void doUpdate(AccountLogin accountLogin, TradeAccount tradeAccount);

}
