package com.mall.butler.account.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.AccountLevel;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class AccountLevelDao extends BaseDao<AccountLevel, Long> {
	public List<AccountLevel> find(AccountLevel accountLevel) {
		return this.queryEntitys("ALL", accountLevel);
	}

	public Page<AccountLevel> page(PageRequest<AccountLevel> request) {
		Page<AccountLevel> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
