package com.mall.butler.account.m.service._impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.account.dao.AccountInfoDao;
import com.mall.butler.account.dao.AccountMessageDao;
import com.mall.butler.account.m.service.MAccountMessageService;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountMessage;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MAccountMessageServiceImpl extends BaseServiceImpl implements MAccountMessageService {

	@Autowired
	private AccountMessageDao accountMessageDao;
	@Autowired
	private AccountInfoDao accountInfoDao;

	@SuppressWarnings("unchecked")
	@Override
	public Page<AccountMessage> queryPage(PageRequest<Map<String, Object>> pageRequest) {
		return (Page<AccountMessage>) accountMessageDao.pageQueryObj(pageRequest, "PAGE_M");
	}

	@Override
	public void doSendMessage(AccountMessage message) {
		message.setId(accountMessageDao.getNewId());
		message.setReadFlag(false);

		accountMessageDao.insert(message);
	}

	@Override
	public AccountInfo findUserByName(String accountName) {
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setAccName(accountName);
		List<AccountInfo> accountInfos = accountInfoDao.find(accountInfo);
		if (accountInfos.size() > 0) {
			return accountInfos.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void sendAll(AccountInfo accountInfo, Integer userType, AccountMessage message) {
		AccountInfo accountInfo2 = new AccountInfo();
		accountInfo2.setAccType(userType);
		List<AccountInfo> accountInfos = accountInfoDao.find(accountInfo2);
		for (AccountInfo account : accountInfos) {
			AccountMessage message2 = new AccountMessage();
			message2.setId(accountMessageDao.getNewId());
			message2.setAccountId(account.getId());
			message2.setAccountName(account.getAccName());
			message2.setTitle(message.getTitle());
			message2.setType(message.getType());
			message2.setContent(message.getContent());
			message2.setSenderId(accountInfo.getId());
			message2.setSenderName(accountInfo.getAccName());
			message2.setReadFlag(false);
			accountMessageDao.insert(message2);
		}

	}

	@Transactional
	@Override
	public void doDelAll(Long[] messageIds) {
		Map<String, Long[]> map = new Hashtable<String, Long[]>();
		map.put("messageIds", messageIds);
		accountMessageDao.delete("ACCOUNTMESSAGE", map, "BY_BULK_PK");
	}
}
