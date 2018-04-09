package com.mall.butler.account.service;

import java.util.List;

import com.mall.butler.account.model.TradeNotifyHistory;
import com.mall.butler.account.model.TradeNotifyTask;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface TradeNotifyTaskService extends BaseService {
	/**
	 * 查找待发送记录
	 * 
	 * @return
	 */
	List<TradeNotifyTask> findToBeSend();

	/**
	 *发送通知
	 * 
	 * @param task
	 */
	void doNotifyTask(TradeNotifyTask task);

	/**
	 * 分页查找
	 * 
	 * @param filter
	 * @return
	 */
	Page<TradeNotifyTask> page(PageRequest<TradeNotifyTask> filter);

	/**
	 * 根据id查找发送历史集合
	 * 
	 * @param id
	 * @return
	 */
	List<TradeNotifyHistory> findHistoryById(Long id);

	/**
	 * 删除发送记录
	 * 
	 * @param tradeNotifyTask
	 */
	void doDel(TradeNotifyTask tradeNotifyTask);

}
