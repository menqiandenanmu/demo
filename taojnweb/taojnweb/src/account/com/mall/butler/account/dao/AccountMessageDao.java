package com.mall.butler.account.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.AccountMessage;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class AccountMessageDao extends BaseDao<AccountMessage, Long> {
	public List<AccountMessage> find(AccountMessage accountMessage) {
		return this.queryEntitys("ALL", accountMessage);
	}

	public Page<AccountMessage> page(PageRequest<AccountMessage> request) {
		Page<AccountMessage> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
