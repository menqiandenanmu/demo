package com.mall.butler.account.m.service._impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.account.dao.AccountInfoDao;
import com.mall.butler.account.dao.AccountLoginDao;
import com.mall.butler.account.m.service.MAccountService;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.exception.ManageException;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.common.TxtUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MAccountServiceImpl extends BaseServiceImpl implements MAccountService {

	@Autowired
	AccountLoginDao accountLoginDao;
	@Autowired
	AccountInfoDao accountInfoDao;

	@Override
	public void doUpdateLoginPass(AccountLogin login) {
		// TODO Auto-generated method stub

	}

	@Override
	public AccountLogin findAccountById(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountLogin> findAllLogin(AccountInfo account) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page<AccountLogin> queryAdminOperator(PageRequest<AccountLogin> pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountLogin doOperatorLogin(AccountLogin accountLogin) {
		AccountLogin filter = new AccountLogin();
		filter.setCreateTime(null);
		filter.setModifiedTime(null);
		filter.setLoginName(accountLogin.getLoginName());
		List<AccountLogin> alL = accountLoginDao.find(filter);
		// 用户密码判断
		if (accountLogin.getLoginPass() == null || TxtUtil.isEmpty(accountLogin.getLoginPass()))
			return null;
		// 用户判断
		if (!alL.isEmpty()) {
			AccountLogin login = alL.get(0);
			// 密码判断
			if (accountLogin.getLoginPass().equalsIgnoreCase(login.getLoginPass())) {
				AccountInfo account = accountInfoDao.getById(login.getAccountId());
				// 账户，账户类型，账户状态判断
				if (null != account && (account.getAccType() == 0||account.getAccType() == 4) && account.getAccStatus() == 1
						&& login.getStatus() == 1) {
					// 成功
					// 修改登录信息
					if (login.getLoginCount() == null) {
						login.setLoginCount(0);
					}
					login.setLoginCount(login.getLoginCount() + 1);
					login.setLastLoginTime(new Date());
					login.setLastIp(accountLogin.getLastIp());
					accountLoginDao.update(login);
					// 返回
					return login;
				}
			}
		}
		
		return null;
	}

	@Override
	public void doDeleteOperator(AccountLogin accountLogin) {
		AccountLogin t = accountLoginDao.getById(accountLogin.getId());
		if (accountLogin.getId() != ManageContext.ADMIN_LOGIN_ID) {
			accountLoginDao.delete(t);
		} else {
			throw new ManageException("不能删 除管理员!");
		}
	}

	@Override
	/**
	 * 修改 密码，备注信息，状态，真名。
	 */
	public void doEditOperator(AccountLogin accountLogin) {
		AccountLogin t = accountLoginDao.getById(accountLogin.getId());
		if (TxtUtil.isEmpty(accountLogin.getRealname()))
			throw new RuntimeException("真实名称不可为空");
		if (!t.getRealname().equals(accountLogin.getRealname())) {
			AccountLogin loign = new AccountLogin();
			loign.setRealname(accountLogin.getRealname().trim());
			if (accountLoginDao.find(loign).size() > 0)
				throw new RuntimeException("真实名称" + accountLogin.getRealname() + "已经存在,请重新输入");
		}
//		if (TxtUtil.isEmpty(accountLogin.getEmail()))
//			throw new RuntimeException("邮件地址不可为空");
//		if (!t.getEmail().equals(accountLogin.getEmail())) {
//			AccountLogin emailFilter = new AccountLogin();
//			emailFilter.setEmail(accountLogin.getEmail().trim());
//			if (accountLoginDao.find(emailFilter).size() > 0)
//				throw new RuntimeException("邮件地址" + accountLogin.getEmail() + "已经存在,请重新输入");
//		}
		t.setLoginPass(TxtUtil.digest(accountLogin.getLoginPass()));
		t.setRemark(accountLogin.getRemark());
		t.setRealname(accountLogin.getRealname());
		t.setStatus(accountLogin.getStatus());
		t.setEmail(accountLogin.getEmail());
		accountLoginDao.update(t);
	}

	@Override
	/**
	 * 修改 密码，备注信息，状态，真名。
	 */
	public void enable(AccountLogin accountLogin) {
		accountLoginDao.update(accountLogin);
	}

	@Override
	public void doRegOperator(AccountLogin accountLogin) {
		// 数据校验
		if (TxtUtil.isEmpty(accountLogin.getLoginName()))
			throw new ManageException("登陆名不能为空!");
		if (TxtUtil.isEmpty(accountLogin.getLoginPass()))
			throw new ManageException("密码名不能为空!");
		if (null == accountLogin.getAccountId())
			throw new ManageException("操作员账户不能为空!");
		// 账户名校验
		AccountLogin filter = new AccountLogin();
		filter.setLoginName(accountLogin.getLoginName());
		List<AccountLogin> alL = accountLoginDao.find(filter);
		if (alL.isEmpty()) {
			// 保存数据
			accountLogin.setId(accountLoginDao.getNewId());
			accountLogin.setLoginPass(TxtUtil.digest(accountLogin.getLoginPass()));
			accountLogin.setLoginCount(0);
			accountLoginDao.insert(accountLogin);
		} else
			throw new ManageException("登陆名" + accountLogin.getLoginName() + "已经被使用!");
	}

	@Override
	public Page<AccountLogin> queryOperator(PageRequest<AccountLogin> pageRequest) {
		return accountLoginDao.findPage(pageRequest);
	}

}
