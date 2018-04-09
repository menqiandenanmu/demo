package com.mall.butler.account.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class AccountInfoDao extends BaseDao<AccountInfo, Long> {
	public List<AccountInfo> find(AccountInfo accountInfo) {
		return this.queryEntitys("ALL", accountInfo);
	}

	public Page<AccountInfo> pagePage(PageRequest<AccountInfo> request) {
		Page<AccountInfo> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
