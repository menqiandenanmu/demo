package com.mall.butler.account.m.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.MSendRulesService;
import com.mall.butler.account.model.SendRules;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 满送规则
 * @author caedmon
 *
 */
public class SendRulesAction extends ManageBaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SendRules sendRules;
	private Page<SendRules> page;
	@Autowired
	private MSendRulesService mSendRulesService;
	public String execute() {
		if(null==sendRules)
			sendRules=new SendRules();
		PageRequest<SendRules> pageRequest = super.newPage(SendRules.class);
		pageRequest.setFilters(sendRules);
		pageRequest.setPageNumber(currPage);
		page = mSendRulesService.pageQuery(pageRequest);
		return LIST;
	}
	
	/**
	 * 新增页面
	 * 
	 * @return
	 */
	public String add() {
		sendRules=new SendRules();
		return ADD;
	}

	/**
	 * 保存操作
	 * 
	 * @return
	 */
	public String save() {
		mSendRulesService.doSave(sendRules);
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
		sendRules.setId(id);
		mSendRulesService.doUpdate(sendRules);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("更新成功!");
		return JDIALOG;
	}
	/**
	 * 更新状态操作
	 * 
	 * @return
	 */
	public String updateStatus() {
		SendRules rules=mSendRulesService.getEntityById(SendRules.class,id);
		rules.setRuleStatus(sendRules.getRuleStatus());
		mSendRulesService.doUpdate(rules);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("更新成功!");
		return JDIALOG;
	}
	/**
	 * 删除
	 * 
	 * @return
	 */
	public String del() {
		sendRules=mSendRulesService.getEntityById(SendRules.class,id);
		mSendRulesService.doDel(sendRules);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("删除成功!");
		return JDIALOG;
	}

	/**
	 * 编辑操作
	 * 
	 * @return
	 */
	public String edit() {
		sendRules=mSendRulesService.getEntityById(SendRules.class,id);
		if (sendRules == null) {
			throw new RuntimeException("无效的信息!");
		}
		return EDIT;
	}

	public SendRules getSendRules() {
		return sendRules;
	}

	public void setSendRules(SendRules sendRules) {
		this.sendRules = sendRules;
	}

	public Page<SendRules> getPage() {
		return page;
	}

	public void setPage(Page<SendRules> page) {
		this.page = page;
	}
	
	
}
