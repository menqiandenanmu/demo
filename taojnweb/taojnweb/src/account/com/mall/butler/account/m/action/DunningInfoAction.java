package com.mall.butler.account.m.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.DunningInfoService;
import com.mall.butler.account.model.DunningInfo;
import com.mall.butler.account.model.TradeAccountInfo;
import com.mall.butler.account.service.TradeAccountService;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class DunningInfoAction extends ManageBaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DunningInfo dunningInfo;
	private Page<DunningInfo> pages;
	@Autowired
	private DunningInfoService dunningInfoService;
	private TradeAccountInfo tradeAccountInfo;
	private Page<TradeAccountInfo> tradePages;

	@Autowired
	private TradeAccountService tradeAccountService;

	public String execute() {
		if (dunningInfo == null) {
			dunningInfo = new DunningInfo();
		}
		PageRequest<DunningInfo> filter = this.newPage(DunningInfo.class);
		filter.setFilters(dunningInfo);
		filter.setPageNumber(this.currPage);
		pages = dunningInfoService.page(filter);
		return SUCCESS;
	}

	public String choseAcc() {
		if (tradeAccountInfo == null) {
			tradeAccountInfo = new TradeAccountInfo();
		}
		PageRequest<TradeAccountInfo> filter = this
				.newPage(TradeAccountInfo.class);
		filter.setFilters(tradeAccountInfo);
		filter.setPageNumber(this.currPage);
		tradePages = tradeAccountService.queryPageTradeAccountInfo(filter);
		return LIST;
	}

	/**
	 * 新增操作
	 * 
	 * @return
	 */
	public String add() {
		if(null!=id){
			tradeAccountInfo=tradeAccountService.getEntityById(TradeAccountInfo.class, id);
			if (null == tradeAccountInfo)
				throw new RuntimeException("账户不存在");
			dunningInfo=new DunningInfo();
			dunningInfo.setAccountId(tradeAccountInfo.getId());
			dunningInfo.setAccountName(tradeAccountInfo.getAccName());
		}
		return ADD;
	}

	/**
	 * 保存存新增
	 * 
	 * @return
	 */
	public String save() {
		if (null == dunningInfo.getAccountId())
			throw new RuntimeException("请选择用户");
		dunningInfo.setRepaymentFlag(0);// 默认未还款
		dunningInfoService.doAddSave(dunningInfo);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("操作成功!");
		return JDIALOG;
	}

	/**
	 * 修改页面
	 * 
	 * @date 2010-10-21 上午09:26:13
	 * @return
	 */
	public String edit() {
		dunningInfo = dunningInfoService.getEntityById(DunningInfo.class, id);
		if (null == dunningInfo)
			throw new RuntimeException("记录不存在");
		return EDIT;
	}

	/**
	 * 保存更新
	 * 
	 * @date 2010-10-21 上午09:26:04
	 * @return
	 */
	public String update() {
		dunningInfo.setId(id);
		if (TxtUtil.isEmpty(dunningInfo.getContentInfo())) {
			throw new RuntimeException("内容不可以为空!");
		}
		dunningInfoService.doUpdate(dunningInfo);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("更新成功!");
		return JDIALOG;
	}

	/**
	 * 删除操作
	 * 
	 * @date 2010-10-21 下午04:00:29
	 * @return
	 */
	public String del() {
		dunningInfo = dunningInfoService.getEntityById(DunningInfo.class, id);
		if (null == dunningInfo)
			throw new RuntimeException("记录不存在");
		dunningInfoService.delete(dunningInfo);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("删除成功!");
		return JDIALOG;
	}

	public DunningInfo getDunningInfo() {
		return dunningInfo;
	}

	public void setDunningInfo(DunningInfo dunningInfo) {
		this.dunningInfo = dunningInfo;
	}

	public Page<DunningInfo> getPages() {
		return pages;
	}

	public void setPages(Page<DunningInfo> pages) {
		this.pages = pages;
	}

	public TradeAccountInfo getTradeAccountInfo() {
		return tradeAccountInfo;
	}

	public void setTradeAccountInfo(TradeAccountInfo tradeAccountInfo) {
		this.tradeAccountInfo = tradeAccountInfo;
	}

	public Page<TradeAccountInfo> getTradePages() {
		return tradePages;
	}

	public void setTradePages(Page<TradeAccountInfo> tradePages) {
		this.tradePages = tradePages;
	}

}
