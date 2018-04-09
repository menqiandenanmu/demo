package com.mall.butler.sys.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.MAgentService;
import com.mall.butler.account.model.AgentInfo;
import com.mall.butler.account.model.TradeNotifyTask;

public class AccountCodeTask {
	private static Logger logger = Logger.getLogger(AccountCodeTask.class);
	@Autowired
	private MAgentService agentService;
	/**
	 * 任务执行入口
	 * 
	 * @return
	 */
	public void execute() {
		logger.info("开始执行用户条形码更新任务!");
		// 查找未发送通知
		List<AgentInfo> list = agentService.queryList(new AgentInfo());
		if (null != list && list.size() > 0) {
			for (AgentInfo task : list) {
				agentService.updateCode(task);
			}
		}
		
		logger.info("结束执行用户条形码更新任务!");
	}
}
