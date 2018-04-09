package com.mall.butler.account.dao;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.AgentInfo;
import com.mall.butler.dao.BaseDao;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class AgentInfoDao extends BaseDao<AgentInfo, Long> {
	public List<AgentInfo> find(AgentInfo agentInfo) {
		return this.queryEntitys("ALL", agentInfo);
	}

	public Page<AgentInfo> page(PageRequest<AgentInfo> request) {
		Page<AgentInfo> result = this.pageQuery(request, "PAGE");
		return result;
	}

	/**
	 *用户编号
	 * 
	 * @param orderInfo
	 * @return
	 */
	public String createCode(AgentInfo transInfo) {
		Long n = transInfo.getId() % 100000;
		String no = DateUtil.format(new Date(), "yyyyMMdd");
		no = no + String.format("%1$09d", n);
		return no;
	}

	/**
	 * 12位随机数
	 * 
	 * @return
	 */
	public String createKey(AgentInfo transInfo) {
		Random random = new Random();
		String rands = "";
		for (int i = 0; i < 3; i++) {
			rands = rands + random.nextInt(10);
		}
		Long n =transInfo.getId()% 10000;
		String no = DateUtil.format(new Date(), "mmss");
		no =String.format("%1$04d", n)+ no  ;
		rands="6"+rands+no;
		return rands;
	}
	
	/**
	 * 12位随机数
	 * 
	 * @return
	 */
	public String createKey() {
		Random random = new Random();
		String rands = "";
		for (int i = 0; i < 11; i++) {
			rands = rands + random.nextInt(10);
		}
		return "6"+rands;
	}
}
