package com.mall.butler.account.w.service._impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.account.dao.AccountInfoDao;
import com.mall.butler.account.dao.AccountLoginDao;
import com.mall.butler.account.dao.AgentInfoDao;
import com.mall.butler.account.dao.TradeAccountDao;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.AgentInfo;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.w.service.WAccountService;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.common.TxtUtil;

public class WAccountServiceImpl extends BaseServiceImpl implements
		WAccountService {

	@Override
	public AccountLogin findByLoginName(String loginName) {
		AccountLogin filterAccountLogin=new AccountLogin();
		filterAccountLogin.setLoginName(loginName);
		List<AccountLogin> list=accountLoginDao.findByLoginName(filterAccountLogin);
		if(null!=list&&list.size()>0)
			return list.get(0);
		return null;
	}

	@Autowired
	private AccountLoginDao accountLoginDao;
	@Autowired
	private AccountInfoDao accountInfoDao;

	@Override
	public AccountLogin doOperatorLogin(AccountLogin accountLogin) {
		AccountLogin filter = new AccountLogin();
		filter.setCreateTime(null);
		filter.setModifiedTime(null);
		filter.setLoginPass(accountLogin.getLoginPass());
		filter.setLoginName(accountLogin.getLoginName());
		List<AccountLogin> alL = accountLoginDao.find(filter);
		System.out.println("用户名===="+accountLogin.getLoginName());
		// 用户判断
		if (!alL.isEmpty()) {
			System.out.println("密码=== ="+accountLogin.getLoginPass());
			AccountLogin login = alL.get(0);
			// 密码判断
			if (accountLogin.getLoginPass().equalsIgnoreCase(
					login.getLoginPass())) {
				System.out.println("用户密码===="+login.getLoginPass());
				AccountInfo account = accountInfoDao.getById(login
						.getAccountId());
				// 账户，账户类型，账户状态判断
				if (null != account && account.getAccType() == 3
						&& account.getAccStatus() == 1) {
					// 成功
					// 修改登录信息
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

	/**
	 * 会员中心修改用户信息
	 */
	@Override
	public void doUpateLoginInfo(AccountLogin accountLogin) {
		AccountLogin param = accountLoginDao.getById(accountLogin.getId());
		if (param == null) {
			throw new RuntimeException("信息异常");
		} else {
			if (!TxtUtil.isEmpty(accountLogin.getRealname())) {
				param.setRealname(accountLogin.getRealname());
			}
			if (!TxtUtil.isEmpty(accountLogin.getEmail())) {
				param.setEmail(accountLogin.getEmail());
			}
			if (!TxtUtil.isEmpty(accountLogin.getBirthday())) {
				param.setBirthday(accountLogin.getBirthday());
			}
			param.setSex(accountLogin.getSex());

			if (!TxtUtil.isEmpty(accountLogin.getMobile())) {
				param.setMobile(accountLogin.getMobile());
			}
			if (!TxtUtil.isEmpty(accountLogin.getIdCard())) {
				param.setIdCard(accountLogin.getIdCard());
			}
			if (!TxtUtil.isEmpty(accountLogin.getRemark())) {
				param.setRemark(accountLogin.getRemark());
			}
			accountLoginDao.update(param);
		}
	}

	@Override
	public void doUpdateLoginPass(AccountLogin login) {
		login.setLoginPass(TxtUtil.digest(login.getLoginPass()));
		accountLoginDao.update(login);
	}

	@Override
	public Long doRegConsumer(AccountLogin accountLogin) {
		Long id = accountLoginDao.getNewId();
		// 数据校验
		if (TxtUtil.isEmpty(accountLogin.getLoginName()))
			throw new RuntimeException("登陆名不能为空!");

		if (!TxtUtil.isEmail(accountLogin.getLoginName())
				&& !TxtUtil.isMobile(accountLogin.getLoginName())) {
			throw new RuntimeException("登录名必须是手机号码或者邮箱地址!");
		}
		if (TxtUtil.isEmpty(accountLogin.getLoginPass()))
			throw new RuntimeException("密码名不能为空!");
		// 账户名校验

		AccountLogin filter = new AccountLogin();
		filter.setLoginName(accountLogin.getLoginName());
		List<AccountLogin> alL = accountLoginDao.find(filter);
		if (alL.isEmpty()) {

			// 获得普通用户的默认等级

			AccountInfo account = new AccountInfo();
			account.setId(accountLoginDao.getNewId());
			account.setAccName(accountLogin.getLoginName());
			account.setAccStatus(1);
			account.setAccType(1);
			account.setAccNo("WEB" + account.getAccName());

			accountInfoDao.insert(account);

			// 默认邮箱或者手机号码为登录用户名称
			if (TxtUtil.isEmail(accountLogin.getLoginName())) {
				accountLogin.setEmail(accountLogin.getLoginName());
			}
			if (TxtUtil.isMobile(accountLogin.getLoginName())) {
				accountLogin.setMobile(accountLogin.getLoginName());
			}

			// 保存数据
			accountLogin.setId(id);
			accountLogin.setLoginPass(TxtUtil.digest(accountLogin
					.getLoginPass()));
			accountLogin.setLoginCount(0);
			accountLogin.setAccountId(account.getId());
			accountLogin.setStatus(1);
			accountLogin.setAdminFlag(true);
			accountLoginDao.insert(accountLogin);

		} else
			throw new RuntimeException("登陆名已经被使用!");
		return id;
	}

	@Autowired
	private AgentInfoDao agentInfoDao;
	@Autowired
	private TradeAccountDao tradeAccountDao;

	@Transactional
	@Override
	public void doUpdate(AccountLogin accountLogin, TradeAccount tradeAccount) {
		AccountLogin param = accountLoginDao.getById(accountLogin.getId());
		param.setMobile(accountLogin.getMobile());
		param.setRealname(accountLogin.getRealname());
		param.setIdCard(accountLogin.getIdCard());
		param.setEmail(accountLogin.getEmail());
		accountLoginDao.update(param);
		AgentInfo agentInfo = agentInfoDao.getById(param.getAccountId());
		agentInfo.setLinkmanTel(accountLogin.getMobile());
		agentInfoDao.update(agentInfo);
		TradeAccount trade = tradeAccountDao.getById(param
				.getAccountId());
		trade.setTradeAccName(tradeAccount.getTradeAccName());
		tradeAccountDao.update(trade);
	}

}
