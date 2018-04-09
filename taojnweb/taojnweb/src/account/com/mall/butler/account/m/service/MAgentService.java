package com.mall.butler.account.m.service;

import java.util.List;
import java.util.Map;

import com.mall.butler.account.m.model.AgentExtendInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.AgentInfo;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 用户业务
 *
 */
public interface MAgentService extends BaseService {
	/**
	 * 分页查询用户
	 * @param pageRequest
	 * @return
	 */
	public Page<AgentExtendInfo> queryPageAgent(PageRequest<AgentExtendInfo> pageRequest);
	/**
	 * 根据ID得到详细信息，包括状态
	 * @param pk
	 * @return
	 */
	public AgentExtendInfo findByPkForM(Long pk);
	
	/**
	 * 添加用户
	 * @param agentInfo
	 */
	public Long doAddAgent(AgentInfo agentInfo,AccountLogin accountLogin);
	/**
	 * 编辑更新用户
	 * @param agentInfo
	 */
	public void doUpdateAgent(AgentInfo agentInfo);
	/**
	 * 删除用户
	 * @param agentInfo
	 */
	public void doDelAgent(AgentInfo agentInfo);
	
	/**
	 * 根据条件查询出所有用户的信息
	 * @return
	 */
	public List<AgentExtendInfo> findAll(AgentExtendInfo info);
	
	/**
	 * 得到业务开通的用户
	 * @param bnsType
	 * @return
	 */
	List<AgentInfo> queryAgentByBnsType(String bnsType);
	/**
	 * 得到用户列表
	 * 
	 */
	public List<AgentInfo> queryList(AgentInfo agentInfo);
	/**
	 * 查询集合
	 * @param map
	 * @return
	 */
	public List<AgentExtendInfo> findTradeDetailList(Map<String, Object> map);
	/**
	 * 更新用户条形码
	 * @param task
	 */
	public void updateCode(AgentInfo task);
	/**
	 * 添加积分
	 * @param agentInfo
	 * @param opVal
	 * @param OpType0:订单 1:系统赠送 2:兑换3:消费4：充值
	 */
	public void addPoint(AgentInfo agentInfo,Integer opVal,Integer OpType,String source);
	
	
}
