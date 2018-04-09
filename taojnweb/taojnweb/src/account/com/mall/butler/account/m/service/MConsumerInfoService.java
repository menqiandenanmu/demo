package com.mall.butler.account.m.service;

import java.util.List;

import com.mall.butler.account.model.AccountLevel;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.ConsumerInfo;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface MConsumerInfoService extends BaseService {
	/**
	 * 查找用户信息
	 * 
	 * @param pageRequest
	 * @return
	 */
	Page<ConsumerInfo> queryConsumer(PageRequest<ConsumerInfo> pageRequest);

	/**
	 * 查找用户等级
	 * 
	 * @param filter
	 * @return
	 */
	List<AccountLevel> queryAccountLevel(AccountLevel filter);

	/**
	 * 添加用户
	 * 
	 * @param consumer
	 */
	void doRegConsumer(ConsumerInfo consumerInfo);

	/**
	 * 得到id会员信息
	 * 
	 * @param id
	 * @return
	 */
	ConsumerInfo getConsumerById(Long id);

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
	AccountLogin findAccountById(Long id);

}
