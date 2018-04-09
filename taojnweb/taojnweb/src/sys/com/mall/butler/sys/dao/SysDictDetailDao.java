package com.mall.butler.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.sys.model.SysDictDetail;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class SysDictDetailDao extends BaseDao<SysDictDetail, Long> {
	public List<SysDictDetail> find(SysDictDetail sysDictDetail) {
		return this.queryEntitys("ALL", sysDictDetail);
	}

	public Page<SysDictDetail> page(PageRequest<SysDictDetail> request) {
		Page<SysDictDetail> result = this.pageQuery(request, "PAGE");
		return result;
	}
}
