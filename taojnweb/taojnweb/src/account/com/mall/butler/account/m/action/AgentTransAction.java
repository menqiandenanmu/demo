package com.mall.butler.account.m.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.MAgentAccountService;
import com.mall.butler.account.model.StoreAccountDetail;
import com.mall.butler.account.model.TradeAccountDetail;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class AgentTransAction extends ManageBaseAction {

	/**
	 * 交易管理
	 */
	private static final long serialVersionUID = 1L;
	private StoreAccountDetail storeAccountDetail;
	@Autowired
	private MAgentAccountService mAgentAccountService;
	private Page<StoreAccountDetail> accountPages;// 账户查询分页数据

	public String execute() {
		if (storeAccountDetail == null) {
			storeAccountDetail = new StoreAccountDetail();
			storeAccountDetail
					.setBegDate(DateUtil.getMonthFirstDay(new Date()));
			storeAccountDetail.setEndDate(DateUtil.getMonthLastDay(new Date()));
		}
		PageRequest<Map<String, Object>> filter = new PageRequest<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begDate", storeAccountDetail.getBegDate());
		map.put("endDate", storeAccountDetail.getEndDate());
		if (!TxtUtil.isEmpty(storeAccountDetail.getOpLoginName()))
			map.put(StoreAccountDetail.OPLOGINNAME, storeAccountDetail
					.getOpLoginName());
		if (!TxtUtil.isEmpty(storeAccountDetail.getFukuanName()))
			map.put(StoreAccountDetail.FUKUANNAME, storeAccountDetail
					.getFukuanName());
		if (!TxtUtil.isEmpty(storeAccountDetail.getOrderNo()))
			map.put(StoreAccountDetail.ORDERNO, storeAccountDetail.getOrderNo());
		map.put(TradeAccountDetail.ACCOUNTID, super.getAccount().getId());
		filter.setFilters(map);
		filter.setPageNumber(this.currPage);
		accountPages = mAgentAccountService.findStoreDetailCount(filter);
		return LIST;
	}

	public StoreAccountDetail getStoreAccountDetail() {
		return storeAccountDetail;
	}

	public void setStoreAccountDetail(StoreAccountDetail storeAccountDetail) {
		this.storeAccountDetail = storeAccountDetail;
	}

	public Page<StoreAccountDetail> getAccountPages() {
		return accountPages;
	}

	public void setAccountPages(Page<StoreAccountDetail> accountPages) {
		this.accountPages = accountPages;
	}

	

}
