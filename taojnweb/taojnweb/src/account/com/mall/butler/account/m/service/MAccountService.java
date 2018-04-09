package com.mall.butler.account.m.service;

import java.util.List;

import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface MAccountService extends BaseService {

	/**
	 * 处理操作员登录
	 * 
	 * @param accountLogin
	 * @return
	 */
	AccountLogin doOperatorLogin(AccountLogin accountLogin);

	/**
	 * 添加操作员
	 * 
	 * @param accountLogin
	 */
	void doRegOperator(AccountLogin accountLogin);

	/**
	 * 修改操作员
	 * 
	 * @param accountLogin
	 */
	void doEditOperator(AccountLogin accountLogin);

	/**
	 * 删除操作员
	 * 
	 * @param accountLogin
	 */
	void doDeleteOperator(AccountLogin accountLogin);

	/**
	 * 查找系统操作员
	 * 
	 * @param pageRequest
	 * @return
	 */
	Page<AccountLogin> queryOperator(PageRequest<AccountLogin> pageRequest);

	/**
	 * 查找系统管理员操作员
	 * 
	 * @param pageRequest
	 * @return
	 */
	Page<AccountLogin> queryAdminOperator(PageRequest<AccountLogin> pageRequest);

	/**
	 * 修改操作员密码 loginPass 传入明码
	 * 
	 * @param login
	 */
	void doUpdateLoginPass(AccountLogin login);

	/**
	 * 通过accountId查找用户登录信息
	 * 
	 * @param login
	 */
	AccountLogin findAccountById(Long accountId);

	/**
	 * 得到操作用户下的操作员
	 * 
	 * @param id
	 * @return
	 */
	List<AccountLogin> findAllLogin(AccountInfo account);

	/**
	 * 禁用，启用操作员
	 * 
	 * @param accountLogin
	 */
	void enable(AccountLogin accountLogin);
}
