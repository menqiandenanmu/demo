package com.mall.butler.order.dao;

import java.util.List;

import com.mall.butler.order.model.TransLogs;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class TransLogsDao extends BaseDao<TransLogs, Long>{
	public List<TransLogs> find(TransLogs transLogs) {
		return this.queryEntitys("ALL", transLogs);
	}
	
	public Page<TransLogs> page(PageRequest<TransLogs> request){
		Page<TransLogs> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
