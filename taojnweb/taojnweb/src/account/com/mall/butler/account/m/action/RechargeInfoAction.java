package com.mall.butler.account.m.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.RechargeInfoService;
import com.mall.butler.account.model.RechargeInfo;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;


public class RechargeInfoAction extends ManageBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RechargeInfo rechargeInfo;
	private Page<RechargeInfo> pages;
	@Autowired
	private RechargeInfoService rechargeInfoService;

	public String execute() {
		rechargeInfo=rechargeInfoService.find();
		if (null == rechargeInfo)
			rechargeInfo = new RechargeInfo();
		
		return ADD;
	}

	/**
	 * 新增操作
	 * 
	 * @return
	 */
	public String add() {
		if (null == id) {
			rechargeInfo = new RechargeInfo();
		} else {

			rechargeInfo = rechargeInfoService.getEntityById(
					RechargeInfo.class, id);
		}
		return ADD;
	}

	/**
	 * 保存存新增
	 * 
	 * @return
	 */
	public String save() {
		
		rechargeInfoService.doAddRecharge(rechargeInfo,id);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("操作成功!");
		return JDIALOG;
	}

	public RechargeInfo getRechargeInfo() {
		return rechargeInfo;
	}

	public void setRechargeInfo(RechargeInfo rechargeInfo) {
		this.rechargeInfo = rechargeInfo;
	}

	public Page<RechargeInfo> getPages() {
		return pages;
	}

	public void setPages(Page<RechargeInfo> pages) {
		this.pages = pages;
	}

}
