package com.mall.butler.sys.task;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.port.service.CrmService;

public class CrmTask {
	private static Logger logger = Logger.getLogger(CrmTask.class);

@Autowired
private CrmService crmService;
	/**
	 * 任务执行入口
	 * 
	 * @return
	 */
	public void execute() {
		logger.info("开始签退!");
		crmService.loginOut("");
		System.out.println("签退结束");
		logger.info("结束签退!");
		logger.info("开始签到!");
		crmService.crmLogin("");
		logger.info("结束签到!");
		System.out.println("签到结束");
	}

}
