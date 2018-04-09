package com.mall.butler.account.m.service;

import java.util.Map;

import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountMessage;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface MAccountMessageService extends BaseService {

	/**
	 * 用户消息
	 * 
	 * @param pageRequest
	 * @return
	 */
	Page<AccountMessage> queryPage(PageRequest<Map<String, Object>> pageRequest);

	/**
	 * 发送消息
	 * 
	 * @param message
	 */
	void doSendMessage(AccountMessage message);

	/**
	 * 通过用户名称找到用户
	 * 
	 * @param accountName
	 * @return
	 */
	AccountInfo findUserByName(String accountName);

	/**
	 * 群发消息
	 * 
	 * @param accountInfo
	 * @param userType
	 * @param message
	 */
	void sendAll(AccountInfo accountInfo, Integer userType, AccountMessage message);

	/**
	 * 批量删除消息
	 * 
	 * @param messageIds
	 */
	void doDelAll(Long[] messageIds);

}
