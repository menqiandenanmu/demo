package com.mall.butler.account.m.dao;

import com.mall.butler.dao.BaseDao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.m.model.AgentExtendInfo;

import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class AgentExtendInfoDao extends BaseDao<AgentExtendInfo, Long> {
	
	public Page<AgentExtendInfo> page(PageRequest<AgentExtendInfo> request) {
		Page<AgentExtendInfo> result = this.pageQuery(request, "BY_PAGE_M");
		return result;
	}
	
	public AgentExtendInfo findByPkForM(Long pk) {
		return this.getObj("AGENTEXTENDINFO", pk, "BY_ID_M");
	}
	
	public List<AgentExtendInfo> findAll(AgentExtendInfo agentExtendInfo) {
		return this.queryEntitys("ALL_M", agentExtendInfo);
	}
	
}
