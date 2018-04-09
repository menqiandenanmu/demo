package com.mall.butler.account.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class AccountLoginDao extends BaseDao<AccountLogin, Long> {
	public List<AccountLogin> find(AccountLogin accountLogin) {
		return this.queryEntitys("ALL", accountLogin);
	}

	public Page<AccountLogin> findPage(PageRequest<AccountLogin> request) {
		Page<AccountLogin> result = this.pageQuery(request, "PAGE");
		return result;
	}

	public List<AccountLogin> findByLoginName(AccountLogin accountLogin) {
		return this.queryEntitys("ALL1", accountLogin);
	}
}
