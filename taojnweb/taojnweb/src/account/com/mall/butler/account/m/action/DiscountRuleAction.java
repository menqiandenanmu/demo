package com.mall.butler.account.m.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.MStoreInfoService;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.DiscountRules;
import com.mall.butler.account.model.StoreInfo;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class DiscountRuleAction extends ManageBaseAction{

	/**
	 * 商户折扣设置
	 */
	private static final long serialVersionUID = 1L;
	
	private DiscountRules discountRules;
	private Page<DiscountRules> rulePage;
	@Autowired
	private MStoreInfoService storeInfoService;
	private StoreInfo storeInfo;
	/**
	 * 折扣信息
	 * 
	 * @return
	 */
	public String execute() {
		if (null == discountRules)
			discountRules = new DiscountRules();
		PageRequest<DiscountRules> pageRequest = super
				.newPage(DiscountRules.class);
		discountRules.setAccountId(super.getLoginId());
		pageRequest.setFilters(discountRules);
		pageRequest.setPageNumber(currPage);
		rulePage = storeInfoService.pageRuleQuery(pageRequest);
		return LIST;
	}

	public DiscountRules getDiscountRules() {
		return discountRules;
	}

	public void setDiscountRules(DiscountRules discountRules) {
		this.discountRules = discountRules;
	}

	public Page<DiscountRules> getRulePage() {
		return rulePage;
	}

	public void setRulePage(Page<DiscountRules> rulePage) {
		this.rulePage = rulePage;
	}

	/**
	 * 新增页面
	 * 
	 * @return
	 */
	public String add() {
		// 查询个人信息
		AccountInfo accountInfo = super.getAccount();
		storeInfo = storeInfoService.getEntityById(StoreInfo.class, accountInfo
				.getId());
		discountRules=new DiscountRules();
		return ADD;
	}

	/**
	 * 保存操作
	 * 
	 * @return
	 */
	public String save() {
		// 查询个人信息
		AccountInfo accountInfo = super.getAccount();
		storeInfo = storeInfoService.getEntityById(StoreInfo.class, accountInfo
				.getId());
		discountRules.setAccountId(accountInfo.getId());
		storeInfoService.doSaveRule(discountRules);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("添加成功!");
		return JDIALOG;
	}

	/**
	 * 更新操作
	 * 
	 * @return
	 */
	public String update() {
		discountRules.setId(id);
		storeInfoService.doUpdateRule(discountRules);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("编辑成功!");
		return JDIALOG;
	}
	/**
	 * 编辑
	 * 
	 * @return
	 */
	public String edit() {
		discountRules=storeInfoService.getEntityById(DiscountRules.class,id);
		return EDIT;
	}
	/**
	 * 删除
	 * 
	 * @return
	 */
	public String del() {
		discountRules=storeInfoService.getEntityById(DiscountRules.class,id);
		storeInfoService.doDelRule(discountRules);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("删除成功!");
		return JDIALOG;
	}
	/**
	 * 更新状态操作
	 * 
	 * @return
	 */
	public String updateStatus() {
		DiscountRules rules=storeInfoService.getEntityById(DiscountRules.class,id);
		rules.setRuleStatus(discountRules.getRuleStatus());
		storeInfoService.doUpdateRule(rules);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("更新成功!");
		return JDIALOG;
	}
	public StoreInfo getStoreInfo() {
		return storeInfo;
	}

	public void setStoreInfo(StoreInfo storeInfo) {
		this.storeInfo = storeInfo;
	}
	
}
