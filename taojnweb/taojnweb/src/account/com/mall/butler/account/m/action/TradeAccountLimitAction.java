package com.mall.butler.account.m.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.model.TradeAccountLimit;
import com.mall.butler.account.service.TradeAccountLimitService;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * @author Mountain 管理交易账户类型的交易范围
 */
public class TradeAccountLimitAction extends ManageBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8371955130078191164L;

	private TradeAccountLimit tradeAccountLimit;
	private Page<TradeAccountLimit> resultPage;
	@Autowired
	private TradeAccountLimitService tradeAccountLimitService;

	public String execute() {
		if (tradeAccountLimit == null)
			tradeAccountLimit = new TradeAccountLimit();
		PageRequest<TradeAccountLimit> filter = this
				.newPage(TradeAccountLimit.class);
		filter.setFilters(tradeAccountLimit);
		filter.setPageNumber(this.currPage);
		resultPage = tradeAccountLimitService.queryPageTradeAccountInfo(filter);
		return LIST;
	}

	/**
	 * 设置账户类型是否生效
	 * 
	 * @throws Exception
	 */
	public String auth() {
		// 避免前台修改的混乱
		tradeAccountLimit.setId(getId());
		tradeAccountLimit = tradeAccountLimitService.getEntityById(
				TradeAccountLimit.class, getId());
		tradeAccountLimitService.updateUseFlag(tradeAccountLimit);
		// 更新之后进行新一次查询，需要把ID放回去
		long tradeAccountId = tradeAccountLimit.getTradeAccountId();
		tradeAccountLimit = new TradeAccountLimit();
		tradeAccountLimit.setTradeAccountId(tradeAccountId);
		return execute();
	}

	/**
	 * 保存操作
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String save() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(tradeAccountLimit.SUPPLYID, tradeAccountLimit.getSupplyId());
		param.put(tradeAccountLimit.TRADEACCOUNTID, tradeAccountLimit
				.getTradeAccountId());
		List<TradeAccountLimit> list = this.tradeAccountLimitService
				.queryByTradeAccountId(param);
		if (list != null && list.size() > 0) {
			this.msgInfo.setFlag(MessageDialog.ERROR);
			this.msgInfo.addMessage("该供应商已存在应用范围内!");
		} else {
			tradeAccountLimitService.insert(tradeAccountLimit);
			this.msgInfo.setFlag(MessageDialog.SUCCESS);
			this.msgInfo.addMessage("添加成功!");
		}
		return JDIALOG;
	}

	/**
	 * 修改页面
	 * 
	 * @return
	 */
	public String add() {
		tradeAccountLimit = tradeAccountLimitService.getEntityById(
				TradeAccountLimit.class, id);
		if (tradeAccountLimit == null || tradeAccountLimit.getDeleted()) {
			throw new RuntimeException("数据不存在或已经删除!");
		}
		return ADD;
	}

	/**
	 * 修改页面
	 * 
	 * @return
	 */
	public String edit() {
		return EDIT;
	}

	/**************************************** get/set方法设置 **************************************************/
	public TradeAccountLimit getTradeAccountLimit() {
		return tradeAccountLimit;
	}

	public void setTradeAccountLimit(TradeAccountLimit tradeAccountLimit) {
		this.tradeAccountLimit = tradeAccountLimit;
	}

	public Page<TradeAccountLimit> getResultPage() {
		return resultPage;
	}

	public void setResultPage(Page<TradeAccountLimit> resultPage) {
		this.resultPage = resultPage;
	}

}
