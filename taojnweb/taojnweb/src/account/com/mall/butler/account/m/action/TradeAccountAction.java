package com.mall.butler.account.m.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.account.model.TradeAccountInfo;
import com.mall.butler.account.service.TradeAccountService;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.service.ApplicationLogService;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;


public class TradeAccountAction extends ManageBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3699308682439548796L;
	
	private Page<TradeAccountInfo> pages;//列表元素展示账户类型
	private TradeAccountInfo tradeAccountInfo;//前台检索条件
	
	@Autowired
	private TradeAccountService tradeAccountService;
	@Autowired
	private ApplicationLogService applicationLogService;
	
	@Override
	public String execute() throws Exception {
		if(tradeAccountInfo==null){
			tradeAccountInfo = new TradeAccountInfo();
		}
		PageRequest<TradeAccountInfo> filter = this
		.newPage(TradeAccountInfo.class);
		filter.setFilters(tradeAccountInfo);
		filter.setPageNumber(this.currPage);
		pages = tradeAccountService.queryPageTradeAccountInfo(filter);
		return LIST;
	}


	/**
	 * 设置账户类型是否生效
	 * @throws Exception 
	 */
	public String auth() throws Exception{
		//不能直接取反，存在为空的状态
		if(tradeAccountInfo.getUseFlag()!=null){
			tradeAccountInfo.setUseFlag(false);
		}else{
			tradeAccountInfo.setUseFlag(true);
		}
		tradeAccountInfo.setId(id);
		tradeAccountService.updateUseFlag(tradeAccountInfo);
		tradeAccountInfo=tradeAccountService.getEntityById(TradeAccountInfo.class, id);
		String statusString="";
		if(tradeAccountInfo.getUseFlag())
			statusString="启用";
		else
			statusString="停用";
		applicationLogService.generic("账户类型状态修改为：【" + statusString+"】"+tradeAccountInfo.getAccName() +
				 " 成功", "账户类型状态修改", ApplicationLogService.GENERIC,ManageContext.LOG_SYS_TYPE,ManageContext.LOG_OPT_TYPE_ACC,null);
		tradeAccountInfo = null;
		return execute();
	}
	/**
	 * 保存操作
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String save() {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put(tradeAccountInfo.ACCCODE, tradeAccountInfo.getAccCode());
		param.put(tradeAccountInfo.ACCNAME, tradeAccountInfo.getAccName());
		List<TradeAccountInfo> list = this.tradeAccountService.getTradeAccountInfoList(param);
		if(list!=null&&list.size()>0){
			this.msgInfo.setFlag(MessageDialog.ERROR);
			this.msgInfo.addMessage("类型编码或者名称已存在!");
		}else{
			tradeAccountService.insert(tradeAccountInfo);
			applicationLogService.generic("添加账户类型：【"+tradeAccountInfo.getAccName() +
					 "】成功", "添加账户类型", ApplicationLogService.GENERIC,ManageContext.LOG_SYS_TYPE,ManageContext.LOG_OPT_TYPE_ACC,null);
			this.msgInfo.setFlag(MessageDialog.SUCCESS);
			this.msgInfo.addMessage("添加成功!");
		}
		return JDIALOG;
	}
	
	/**
	 * 新增页面
	 * 
	 * @return
	 */
	public String add() {
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
	/*******************************************设置get/set方法**************************************/
	public TradeAccountInfo getTradeAccountInfo() {
		return tradeAccountInfo;
	}

	public void setTradeAccountInfo(TradeAccountInfo tradeAccountInfo) {
		this.tradeAccountInfo = tradeAccountInfo;
	}

	public Page<TradeAccountInfo> getPages() {
		return pages;
	}

	public void setPages(Page<TradeAccountInfo> pages) {
		this.pages = pages;
	}
	
	
}
