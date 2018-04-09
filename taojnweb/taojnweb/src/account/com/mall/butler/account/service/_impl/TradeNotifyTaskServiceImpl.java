package com.mall.butler.account.service._impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.account.dao.TradeNotifyHistoryDao;
import com.mall.butler.account.dao.TradeNotifyTaskDao;
import com.mall.butler.account.model.TradeNotifyHistory;
import com.mall.butler.account.model.TradeNotifyTask;
import com.mall.butler.account.service.TradeNotifyTaskService;

import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class TradeNotifyTaskServiceImpl extends BaseServiceImpl implements
		TradeNotifyTaskService {

	@Autowired
	private TradeNotifyTaskDao tradeNotifyTaskDao;
	@Autowired
	private TradeNotifyHistoryDao tradeNotifyHistoryDao;

	@Override
	public List<TradeNotifyTask> findToBeSend() {
		TradeNotifyTask task = new TradeNotifyTask();
		return tradeNotifyTaskDao.queryEntitys("TASK", task);
	}
@Transactional
	@Override
	public void doNotifyTask(TradeNotifyTask task) {
		// 发送通知
		task.setSyncNum(task.getSyncNum() + 1);
		// 组装发送内容
		// 发送
		String sendResult = "success";
		// 判断发送记录
		if (null == sendResult || "".equals(sendResult)) {
			task.setSyncStatus("fail");
			// 调整发送时间默认一分钟
			task.setSyncTime(DateUtil.addDayHmS(task.getSyncTime(), 0, 1, 0));
		} else {
			if ("success" == sendResult) {
				task.setSyncStatus("success");
				task.setDeleted(true);
			} else {
				task.setSyncStatus("fail");
				// 调整发送时间
				task.setSyncTime(DateUtil
						.addDayHmS(task.getSyncTime(), 0, 1, 0));
			}
		}
		// 超过发送次数默认删除
		if (task.getSyncNum() >= 4) {
			task.setDeleted(true);
		}
		tradeNotifyTaskDao.update(task);
		// 插入发送记录
		TradeNotifyHistory history = new TradeNotifyHistory();
		history.setOpType(task.getOpType());
		history.setSerialNo(task.getSerialNo());
		history.setSyncCon(task.getSerialNo());
		history.setSyncId(task.getId());
		history.setSyncNum(task.getSyncNum());
		history.setSyncStatus(task.getSyncStatus());
		history.setId(tradeNotifyHistoryDao.getNewId());

		tradeNotifyHistoryDao.insert(history);
	}

	@Override
	public Page<TradeNotifyTask> page(PageRequest<TradeNotifyTask> filter) {
		return tradeNotifyTaskDao.page(filter);
	}

	@Override
	public List<TradeNotifyHistory> findHistoryById(Long id) {
		TradeNotifyHistory tradeNotifyHistory = new TradeNotifyHistory();
		tradeNotifyHistory.setSyncId(id);
		return tradeNotifyHistoryDao.find(tradeNotifyHistory);
	}

	@Override
	public void doDel(TradeNotifyTask tradeNotifyTask) {
		// TODO Auto-generated method stub
		
	}

}
