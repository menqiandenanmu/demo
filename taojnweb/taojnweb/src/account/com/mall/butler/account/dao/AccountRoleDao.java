package com.mall.butler.account.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.AccountRole;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class AccountRoleDao extends BaseDao<AccountRole, Long> {
	public List<AccountRole> find(AccountRole accountRole) {
		return this.queryEntitys("ALL", accountRole);
	}

	public Page<AccountRole> page(PageRequest<AccountRole> request) {
		Page<AccountRole> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
