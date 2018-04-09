package com.mall.butler.account.m.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.model.TradeNotifyHistory;
import com.mall.butler.account.model.TradeNotifyTask;
import com.mall.butler.account.service.TradeNotifyTaskService;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;
/**
 * 订单通知管理
 * @author caedmon
 *
 */
public class MTradeNotifyAction extends ManageBaseAction{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TradeNotifyTaskService tradeNotifyTaskService;
	
	private TradeNotifyTask tradeNotifyTask;
	private List<TradeNotifyHistory> histories;
	private Page<TradeNotifyTask> resultPage;
	public String execute() {
		if (tradeNotifyTask == null) {
			tradeNotifyTask = new TradeNotifyTask();
		}
		if(null==tradeNotifyTask.getSyncStatus())
			//默认为失败的
			tradeNotifyTask.setSyncStatus("fail");
		PageRequest<TradeNotifyTask> filter = this.newPage(TradeNotifyTask.class);
		filter.setFilters(tradeNotifyTask);
		filter.setPageNumber(this.currPage);
		resultPage = tradeNotifyTaskService.page(filter);
		return SUCCESS;
	}
	/**
	 * 发送历史
	 * @return
	 */
	public String history() {
		if(null==id)
			throw new RuntimeException("id不能为空");
		histories = tradeNotifyTaskService.findHistoryById(id);
		return SUCCESS;
	}
	/**
	 * 再次发送
	 * @return
	 */
	public String sendAgain() {
		if(null==id)
			throw new RuntimeException("id不能为空");
		if(null==id)
			throw new RuntimeException("id不能为空");
		tradeNotifyTask=tradeNotifyTaskService.getEntityById(TradeNotifyTask.class, id);
		tradeNotifyTask.setSyncNum(0);
		tradeNotifyTask.setDeleted(false);
		tradeNotifyTaskService.update(tradeNotifyTask);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("发送成功!");
		return JDIALOG;
	}
	/**
	 * 删除
	 * @return
	 */
	public String del() {
		if(null==id)
			throw new RuntimeException("id不能为空");
		tradeNotifyTask=tradeNotifyTaskService.getEntityById(TradeNotifyTask.class, id);
		tradeNotifyTaskService.doDel(tradeNotifyTask);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("删除成功!");
		return JDIALOG;
	}
	
	public TradeNotifyTask getTradeNotifyTask() {
		return tradeNotifyTask;
	}
	public void setTradeNotifyTask(TradeNotifyTask tradeNotifyTask) {
		this.tradeNotifyTask = tradeNotifyTask;
	}
	public Page<TradeNotifyTask> getResultPage() {
		return resultPage;
	}
	public void setResultPage(Page<TradeNotifyTask> resultPage) {
		this.resultPage = resultPage;
	}
	public List<TradeNotifyHistory> getHistories() {
		return histories;
	}
	public void setHistories(List<TradeNotifyHistory> histories) {
		this.histories = histories;
	}

}
