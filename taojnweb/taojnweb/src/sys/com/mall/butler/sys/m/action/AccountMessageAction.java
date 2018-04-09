package com.mall.butler.sys.m.action;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.MAccountMessageService;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountMessage;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 用户消息
 * 
 * @author zhaoxs
 */
public class AccountMessageAction extends ManageBaseAction {

	private static final long serialVersionUID = 1L;
	private AccountMessage message;
	private Page<AccountMessage> messagePage;
	@Autowired
	private MAccountMessageService mAccountMessageService;
	private Date begCreateDate;
	private Date endCreateDate;
	private Long[] messageIds;
	private Integer userType;
	private Integer sendType;// 发送类型

	public String execute() {
		if (message == null) {
			message = new AccountMessage();
			begCreateDate = DateUtil.getMonthFirstDay(new Date());
			endCreateDate = DateUtil.getMonthLastDay(new Date());
		}
		PageRequest<Map<String, Object>> pageRequest = new PageRequest<Map<String, Object>>();
		pageRequest.setPageNumber(currPage);
		Map<String, Object> params = new Hashtable<String, Object>();
		if (!TxtUtil.isEmpty(message.getAccountName()))
			params.put(AccountMessage.ACCOUNTNAME, message.getAccountName().trim());
		if (message.getType() != null)
			params.put(AccountMessage.TYPE, message.getType());
		if (!TxtUtil.isEmpty(message.getTitle()))
			params.put(AccountMessage.TITLE, message.getTitle());
		if (message.getReadFlag() != null)
			params.put(AccountMessage.READFLAG, message.getReadFlag());
		if (begCreateDate != null)
			params.put("begCreateDate", begCreateDate);
		if (endCreateDate != null)
			params.put("endCreateDate", endCreateDate);
		pageRequest.setFilters(params);
		messagePage = mAccountMessageService.queryPage(pageRequest);
		return SUCCESS;
	}

	public String del() {
		AccountMessage accountMessage = mAccountMessageService.getEntityById(AccountMessage.class,
				id);
		if (accountMessage == null || accountMessage.getDeleted())
			throw new RuntimeException("信息无效，或已被删除");
		mAccountMessageService.delete(accountMessage);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("删除成功");
		return JDIALOG;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */

	public String delAll() {
		mAccountMessageService.doDelAll(messageIds);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("删除成功");
		return JDIALOG;
	}

	/**
	 * 发送消息
	 */
	public String input() {

		return INPUT;
	}

	/**
	 * 保存发送消息
	 * 
	 * @return
	 */
	public String send() {
		if (sendType == 1) {
			AccountInfo user = mAccountMessageService.findUserByName(message.getAccountName());
			if (user == null)
				throw new RuntimeException("用户不存在");
			message.setAccountId(user.getId());
			message.setAccountName(user.getAccName());
			message.setSenderId(super.getAccount().getId());
			message.setSenderName(super.getAccount().getAccName());
			this.msgInfo.setFlag(MessageDialog.SUCCESS);
			this.msgInfo.addMessage("发送成功");
			mAccountMessageService.doSendMessage(message);
			return JDIALOG;
		} else if (sendType == 0) {
			mAccountMessageService.sendAll(this.getAccount(), userType, message);
			this.msgInfo.setFlag(MessageDialog.SUCCESS);
			this.msgInfo.addMessage("群发成功");
			return JDIALOG;
		}
		this.msgInfo.setFlag(MessageDialog.ERROR);
		this.msgInfo.addMessage("操作失败");
		return JDIALOG;

	}

	/**
	 * 消息详细内容
	 * 
	 * @return
	 */
	public String info() {
		message = mAccountMessageService.getEntityById(AccountMessage.class, id);
		return INFO;
	}

	public Long[] getMessageIds() {
		return messageIds;
	}

	public void setMessageIds(Long[] messageIds) {
		this.messageIds = messageIds;
	}

	public AccountMessage getMessage() {
		return message;
	}

	public void setMessage(AccountMessage message) {
		this.message = message;
	}

	public Page<AccountMessage> getMessagePage() {
		return messagePage;
	}

	public void setMessagePage(Page<AccountMessage> messagePage) {
		this.messagePage = messagePage;
	}

	public Date getBegCreateDate() {
		return begCreateDate;
	}

	public void setBegCreateDate(Date begCreateDate) {
		this.begCreateDate = begCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

}
