package com.mall.butler.account.m.service._impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.account.dao.AccountInfoDao;
import com.mall.butler.account.dao.AccountLevelDao;
import com.mall.butler.account.dao.AccountLoginDao;
import com.mall.butler.account.m.service.MConsumerInfoService;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLevel;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.ConsumerInfo;
import com.mall.butler.exception.ManageException;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.common.TxtUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MConsumerInfoServiceImpl extends BaseServiceImpl implements MConsumerInfoService {
	@Autowired
	AccountLoginDao accountLoginDao;
	@Autowired
	AccountInfoDao accountInfoDao;
	@Autowired
	AccountLevelDao accountLevelDao;

	@Override
	public void doRegConsumer(ConsumerInfo consumer) {
		// 数据校验
		if (TxtUtil.isEmpty(consumer.getLoginName()))
			throw new ManageException("登陆名不能为空!");
		if (TxtUtil.isEmpty(consumer.getLoginPass()))
			throw new ManageException("密码名不能为空!");
		// 账户名校验
		AccountLogin filter = new AccountLogin();
		filter.setLoginName(consumer.getLoginName());
		List<AccountLogin> alL = accountLoginDao.find(filter);
		if (alL.isEmpty()) {
			AccountLevel level = accountLevelDao.getById(ManageContext.COUNSUMER_LEVEL);
			// 保存数据
			AccountInfo account = new AccountInfo();
			account.setId(accountLoginDao.getNewId());
			account.setAccName(consumer.getLoginName());
			account.setAccStatus(1);
			account.setAccType(1);
			account.setAccLevelId(ManageContext.COUNSUMER_LEVEL);
			if (level != null)
				account.setAccLevelName(level.getLevelName());
			accountInfoDao.insert(account);
			AccountLogin login = new AccountLogin();
			login.setAccountId(account.getId());
			login.setId(accountLoginDao.getNewId());
			login.setAdminFlag(true);
			login.setBirthday(consumer.getBirthday());
			login.setCreateLoginId(consumer.getCreateLoginId());
			login.setEmail(consumer.getEmail());
			login.setIdCard(consumer.getIdCard());
			login.setLoginCount(0);
			login.setLoginName(consumer.getLoginName());
			login.setLoginPass(TxtUtil.digest(consumer.getLoginPass()));
			login.setMobile(consumer.getMobile());
			login.setRealname(consumer.getRealname());
			login.setRemark(consumer.getRemark());
			login.setSex(consumer.getSex());
			login.setStatus(1);
			accountLoginDao.insert(login);
		} else
			throw new ManageException("用户名已经被使用!");

	}

	@Override
	public void doUpdateLoginPass(AccountLogin login) {
		AccountLogin accountLogin = accountLoginDao.getById(login.getId());
		accountLogin.setLoginPass(TxtUtil.digest(login.getLoginPass()));
		accountLoginDao.update(accountLogin);

	}

	@Override
	public AccountLogin findAccountById(Long accountId) {
		AccountLogin accountLogin = new AccountLogin();
		accountLogin.setAccountId(accountId);
		List<AccountLogin> accountLogins = accountLoginDao.find(accountLogin);
		if (accountLogins.size() > 0) {

			return accountLogins.get(0);
		} else {
			return null;
		}
	}

	@Override
	public ConsumerInfo getConsumerById(Long id) {
		return (ConsumerInfo) accountInfoDao.getObject("CONSUMER_ID", id);
	}

	@Override
	public List<AccountLevel> queryAccountLevel(AccountLevel filter) {
		return accountLevelDao.find(filter);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<ConsumerInfo> queryConsumer(PageRequest<ConsumerInfo> pageRequest) {
		return (Page<ConsumerInfo>) accountInfoDao.pageQueryObj(pageRequest, "CONSUMER");
	}

}
