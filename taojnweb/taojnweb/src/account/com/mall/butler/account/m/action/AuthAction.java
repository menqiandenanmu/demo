package com.mall.butler.account.m.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.account.m.service.MAccountService;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.helper.SessionHelper;
import com.mall.butler.service.ApplicationLogService;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;

/**
 * 类名称：AuthAction 类描述： 创建人：caedmon 创建时间：2013-4-26 下午01:56:02 修改人：caedmon
 * 修改时间：2013-4-26 下午01:56:02 修改备注：
 * 
 * @version
 */
public class AuthAction extends ManageBaseAction {

	private static final long serialVersionUID = 1584927878119906872L;
	@Autowired
	private SessionHelper sessionHelper;
	@Autowired
	private MAccountService mAccountService;
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 密码
	 */
	private String loginPass;
	/**
	 * 验证码
	 */
	private String checkCode;
	private Integer accStatus;// 状态

	public String execute() {
		Long id = this.getLoginId();
		if (id == null)
			return INPUT;
		else
			return SUCCESS;
	}
	@Autowired
	private ApplicationLogService applicationLogService;
	/**
	 * @Title: 登陆
	 * @param @return
	 * @date 2013-4-26 下午01:55:15
	 * @return
	 */
	public String login() {
		AccountLogin accountLogin = new AccountLogin();
		Object t = sessionHelper.get(ManageContext.SESSION_CHECKCODE);
		// 开取测试不验证验证码

		if ((null == t || !((String) t).equals(checkCode))) {
			this.addActionError("输入的验证码有误!");
			return INPUT;
		} else {
			accountLogin.setLoginName(loginName);
			accountLogin.setLoginPass(TxtUtil.digest(loginPass));
			accountLogin.setLastIp(this.getClientIp());
			// 执行登录
			accountLogin = mAccountService.doOperatorLogin(accountLogin);
			if (accountLogin != null) {
				// 保存登录信息
				this.putLoginId(accountLogin.getId());
				 applicationLogService.generic("操作员登录:" + accountLogin.getLoginName() +
						 " 成功", "操作员登录", ApplicationLogService.GENERIC,ManageContext.LOG_SYS_TYPE,ManageContext.LOG_OPT_TYPE_LOGIN,accountLogin.getId());
				return SUCCESS;
			} else {
				this.addActionError("输入的用户名密码有误!");
			}
		}
		return INPUT;
	}

	/**
	 * @Title: 注销用户
	 * @param @return
	 * @date 2013-4-26 下午01:53:18
	 * @return
	 */
	public String logout() {
		this.putLoginId(null);
		return INPUT;
	}

	public String changeAccStatus() {

		AccountInfo accountInfo = mAccountService.getEntityById(
				AccountInfo.class, id);
		accountInfo.setAccStatus(accStatus);
		mAccountService.update(accountInfo);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("修改成功!");
		return JDIALOG;
	}

	// 设置参数

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(Integer accStatus) {
		this.accStatus = accStatus;
	}

}
