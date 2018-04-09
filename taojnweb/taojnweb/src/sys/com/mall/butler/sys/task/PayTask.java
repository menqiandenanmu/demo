package com.mall.butler.sys.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.model.TradeNotifyTask;
import com.mall.butler.account.service.TradeNotifyTaskService;

public class PayTask {
	private static Logger logger = Logger.getLogger(PayTask.class);

	@Autowired
	private TradeNotifyTaskService tradeNotifyTaskService;

	/**
	 * 任务执行入口
	 * 
	 * @return
	 */
	public void execute() {
		logger.info("开始执行订单任务!");
		// 查找未发送通知
		List<TradeNotifyTask> list = tradeNotifyTaskService.findToBeSend();
		if (null != list && list.size() > 0) {
			for (TradeNotifyTask task : list) {
				tradeNotifyTaskService.doNotifyTask(task);
			}
		}
		
		logger.info("结束执行订单任务!");
	}

}
