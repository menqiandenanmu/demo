package com.mall.butler.account.m.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.MConsumerInfoService;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLevel;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.ConsumerInfo;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class ConsumerAction extends ManageBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8722713532353995930L;
	@Autowired
	private MConsumerInfoService mConsumerInfoService;
	private Page<ConsumerInfo> consumerPage;
	private Long id;
	private String loginPass;
	// 做输入条件及页面显示用
	private ConsumerInfo consumerInfo;
	private List<AccountLevel> levelList;

	public String execute() {
		if(null==consumerInfo)
			consumerInfo=new ConsumerInfo();
		PageRequest<ConsumerInfo> pageRequest = super.newPage(ConsumerInfo.class);
		pageRequest.setFilters(consumerInfo);
		pageRequest.setPageNumber(currPage);
		consumerPage = mConsumerInfoService.queryConsumer(pageRequest);
		return LIST;
	}

	/**
	 * 详细
	 * 
	 * @return
	 */
	public String info() {
		consumerInfo = mConsumerInfoService.getConsumerById(id);
		if (consumerInfo == null || consumerInfo.getDeleted())
			throw new RuntimeException("无效的信息!");
		return INFO;
	}

	/**
	 * 新增操作
	 * 
	 * @return
	 */
	public String add() {
		AccountLevel filter = new AccountLevel();
		filter.setLevelAccType(1);
		levelList = mConsumerInfoService.queryAccountLevel(filter);
		return ADD;
	}

	/**
	 * 保存存新增
	 * 
	 * @return
	 */
	public String save() {
		// 用户名判断
		if (TxtUtil.isEmpty(consumerInfo.getLoginName()))
			throw new RuntimeException("登录名不能为空!");
		consumerInfo.setLoginName(consumerInfo.getLoginName().trim());
		// 密码判断
		if (TxtUtil.isEmpty(consumerInfo.getLoginPass()))
			throw new RuntimeException("登录名码不可以为空!");
		consumerInfo.setLoginPass(consumerInfo.getLoginPass().trim());

		mConsumerInfoService.doRegConsumer(consumerInfo);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("添加成功!");
		return JDIALOG;
	}

	/**
	 * 编辑操作
	 * 
	 * @return
	 */
	public String pass() {
		return "pass";
	}

	/**
	 * 编辑保存
	 * 
	 * @return
	 */
	public String updatePass() {
		if (TxtUtil.isEmpty(loginPass))
			throw new RuntimeException("密码不能为空!");
		AccountLogin login = new AccountLogin();
		login.setId(id);
		login.setLoginPass(loginPass.trim());
		mConsumerInfoService.doUpdateLoginPass(login);
		this.putLoginId(null);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("修改成功!");
		return JDIALOG;
	}

	/**
	 * 启用账户
	 * 
	 * @return
	 */
	public String start() {
		AccountInfo accountInfo = mConsumerInfoService.getEntityById(AccountInfo.class, id);
		if (accountInfo == null || accountInfo.getDeleted())
			throw new RuntimeException("无效的信息!");
		AccountLogin accountLogin = mConsumerInfoService.findAccountById(id);
		if (accountLogin == null || accountLogin.getDeleted())
			throw new RuntimeException("无效的信息!");
		accountLogin.setStatus(1);
		mConsumerInfoService.update(accountLogin);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("修改成功!");
		return JDIALOG;
	}

	/**
	 * 锁定账户
	 * 
	 * @return
	 */
	public String lock() {
		AccountInfo accountInfo = mConsumerInfoService.getEntityById(AccountInfo.class, id);
		if (accountInfo == null || accountInfo.getDeleted())
			throw new RuntimeException("无效的信息!");
		AccountLogin accountLogin = mConsumerInfoService.findAccountById(id);
		if (accountLogin == null || accountLogin.getDeleted())
			throw new RuntimeException("无效的信息!");
		accountLogin.setStatus(0);
		mConsumerInfoService.update(accountLogin);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("修改成功!");
		return JDIALOG;
	}

	public Page<ConsumerInfo> getConsumerPage() {
		return consumerPage;
	}

	public ConsumerInfo getConsumerInfo() {
		return consumerInfo;
	}

	public void setConsumerInfo(ConsumerInfo consumerInfo) {
		this.consumerInfo = consumerInfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<AccountLevel> getLevelList() {
		return levelList;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

}
