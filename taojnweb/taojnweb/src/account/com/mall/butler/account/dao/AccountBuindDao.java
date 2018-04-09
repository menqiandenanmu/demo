package com.mall.butler.account.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;
import com.mall.butler.account.model.AccountBuind;


@Repository
public class AccountBuindDao extends BaseDao<AccountBuind, Long>{
	public List<AccountBuind> find(AccountBuind accountBuind) {
		return this.queryEntitys("ALL", accountBuind);
	}
	
	public Page<AccountBuind> page(PageRequest<AccountBuind> request){
		Page<AccountBuind> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
