package com.mall.butler.sys.m.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.mall.butler.account.m.service.MAccountService;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.easyui.Response;
import com.mall.butler.exception.BusinessException;
import com.mall.butler.sys.m.service.MRoleService;
import com.mall.butler.sys.model.SysRole;
import com.mall.butler.sys.service.RoleService;
import com.mall.butler.util.JsonMapper;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 供应商操作员管理
 * 
 * @author juror
 * @date 2010-10-20 下午05:39:37
 */
public class OperatorAction extends ManageBaseAction {
	private static Logger logger = Logger.getLogger(OperatorAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -1786350121423063578L;

	private static final String ROLE_EDIT = "roleEdit";

	@Autowired
	private MAccountService mAccountService;
	@Autowired
	private MRoleService mRoleService;
	@Autowired
	private RoleService roleService;
	// 操作员信息
	private AccountLogin accountLogin = new AccountLogin();
	// 列表信息
	private Page<AccountLogin> loginPage;
	// 用户等级
	private List<SysRole> roleL;
	// 目标用户权限数组
	private List<SysRole> distRoleL;
	// 权限主键数组
	private Long[] rids;
	private AccountInfo accountInfo;

	// 旧密码
	private String oldPassword;
	// 新密码
	private String newPassword;

	private String idsStr;

	/**
	 * 列表页面
	 */
	public String execute() {

		return LIST;
	}

	/**
	 * 列表页面
	 */
	@Async
	public String datagrid() {
		PageRequest<AccountLogin> pageRequest = this.buildPageRequest();
		AccountLogin param = new AccountLogin();
		param.setAccountId(super.getAccount().getId());
		param.setStatus(accountLogin.getStatus());
		param.setAdminFlag(accountLogin.getAdminFlag());
		if (!TxtUtil.isEmpty(accountLogin.getLoginName()))
			param.setLoginName(accountLogin.getLoginName().trim());
		pageRequest.setFilters(param);
		loginPage = mAccountService.queryOperator(pageRequest);
		this.renderDatagridJson(loginPage);
		return NONE;
	}

	/**
	 * 明细
	 */

	public String info() {
		accountLogin = mAccountService.getEntityById(AccountLogin.class, id);
		if (accountLogin == null || accountLogin.getDeleted())
			this.renderJson(Response.error("操作员信息不存在"));
		accountInfo = mAccountService.getEntityById(AccountInfo.class, accountLogin.getAccountId());
		if (accountInfo == null || accountInfo.getDeleted())
			this.renderJson(Response.error("用户信息不存在"));
		return INFO;
	}

	/**
	 * 新增操作员
	 * 
	 * @author juror
	 * @date 2010-10-21 上午08:57:07
	 * @return
	 */
	public String add() {
		return ADD;
	}

	/**
	 * 保存新增
	 * 
	 * @author juror
	 * @date 2010-10-21 上午08:57:31
	 */
	@Async
	public String save() {

		logger.info(JsonMapper.toJson("操作员新增" + accountLogin));
		try {
			accountLogin.setAccountId(super.getAccount().getId());
			accountLogin.setCreateLoginId(this.getLoginId());
			accountLogin.setAdminFlag(false);
			mAccountService.doRegOperator(accountLogin);
		} catch (Exception e) {
			logger.info("操作员新增失败" + JsonMapper.toJson(Response.error(e.getMessage())));
			this.renderJson(Response.error("保存失败" + e.getMessage()));
		}
		this.renderJson(Response.success("保存成功"));
		logger.info("操作员新增成功" + JsonMapper.toJson(Response.success(accountLogin.getLoginName() + "保存成功")));
		return NONE;
	}

	/**
	 * 修改页面
	 * 
	 * @author juror
	 * @date 2010-10-21 上午09:26:13
	 * @return
	 */
	public String edit() {
		accountLogin = mAccountService.getEntityById(AccountLogin.class, id);
		return EDIT;
	}

	/**
	 * 保存更新
	 * 
	 * @author juror
	 * @date 2010-10-21 上午09:26:04
	 */
	@Async
	public String update() {
		accountLogin.setId(id);
		if (TxtUtil.isEmpty(accountLogin.getRealname())) {
			this.renderJson(Response.error("真实姓名不能为空"));
		}
		if (TxtUtil.isEmpty(accountLogin.getLoginPass())) {
			this.renderJson(Response.error("密码不能为空"));
		}
		mAccountService.doEditOperator(accountLogin);
		this.renderJson(Response.success("更新操作员成功"));
		return NONE;
	}

	/**
	 * 删除操作
	 * 
	 * @author juror
	 * @date 2010-10-21 下午04:00:29
	 */
	public void del() {
		// 不可以删除自己
		if (id.equals(this.getLoginId())) {
			throw new BusinessException("操作失败!不能删除自己!");
		}
		// 不是管理员或者不是自己建的操作员不可以删除
		if (!getLogin().getCreateLoginId().equals(id) && !getLogin().getAdminFlag()) {
			throw new BusinessException("操作失败!无权限删除此用户!");
		}
		accountLogin.setId(id);
		mAccountService.doDeleteOperator(accountLogin);
		this.outputJson(Response.success("删除成功!"));
	}

	/**
	 * 删除操作
	 * 
	 * @author juror
	 * @date 2010-10-21 下午04:00:29
	 */
	public void refund() {
		// 只有admin才能执行此操作
		if (1L != this.getLoginId()) {
			this.outputJson(Response.success("操作失败!只有系统管理员admin才能执行此操作!"));
			return;
		}
		// 不可以删除自己
		if (id.equals(this.getLoginId())) {
			this.outputJson(Response.success("操作失败!不能修改自己!"));
			return;
		}
		// 不是管理员或者不是自己建的操作员不可以删除
		if (!getLogin().getCreateLoginId().equals(id) && !getLogin().getAdminFlag()) {
			throw new BusinessException("操作失败!无权限修改此用户!");
		}
		accountLogin.setId(id);
		// mAccountService.doRefundOperator(accountLogin);
		this.outputJson(Response.success("操作成功!"));
	}

	/**
	 * 权限分配页面
	 * 
	 * @author juror
	 * @date 2010-10-21 下午08:01:00
	 */
	public String roleEdit() {
		// AccountLoginVo login = getLoginFromUC();
		AccountLogin login = null;
		if (login != null) {
			if (id.equals(login.getId())) {
				msgInfo.setFlag(MessageDialog.WARNING);
				msgInfo.addMessage("不能对自己进行角色操作!");
				return DIALOG;
			} else {
				distRoleL = new ArrayList<SysRole>();
				accountLogin = mAccountService.getEntityById(AccountLogin.class, id);
				List<SysRole> tempRoleL = roleService.queryRoleAssign(login);
				List<SysRole> tempDistRoleL = roleService.queryRoleSelf(accountLogin);
				Map<Long, SysRole> filter = new HashMap<Long, SysRole>();
				for (SysRole index : tempRoleL) {
					filter.put(index.getId(), index);
				}
				for (SysRole index : tempDistRoleL) {
					SysRole temp = filter.get(index.getId());
					if (temp != null) {
						distRoleL.add(index);
						filter.remove(index.getId());
					}
				}
				roleL = new ArrayList<SysRole>();
				roleL.addAll(filter.values());
				return ROLE_EDIT;
			}
		} else {
			msgInfo.setFlag(MessageDialog.WARNING);
			msgInfo.addMessage("获取登陆信息失败");
			return DIALOG;
		}
	}

	/**
	 * 执行权限分配
	 * 
	 * @author juror
	 * @date 2010-10-21 下午08:00:43
	 * @return
	 */
	@Async
	public String roleUpdate() {
		AccountLogin distLogin = mAccountService.getEntityById(AccountLogin.class, id);
		if (distLogin == null || distLogin.getDeleted()) {
			this.renderJson(Response.error("无效的操作员!"));
			throw new RuntimeException("无效的操作员!");
		} else {
			List<SysRole> roles = new ArrayList<SysRole>();
			// if (rids != null)
			String[] ss = {};
			if (!TxtUtil.isEmpty(idsStr)) {
				ss = idsStr.split(",");
			}
			for (String index : ss) {
				SysRole temp = mRoleService.getEntityById(SysRole.class, Long.parseLong(index));
				if (temp == null || temp.getDeleted()) {
					this.renderJson(Response.error("无效的角色!"));
					throw new RuntimeException("无效的角色!");

				} else {
					roles.add(temp);
				}
			}
			// roleService.doUpdateLoginRole(distLogin, roles,
			// getLoginFromUC());
			this.renderJson(Response.success("操作员角色更新成功!"));
			return NONE;
		}
	}

	/**
	 * 更新密码
	 * 
	 * @return
	 */
	public String updatePass() {
		accountLogin.setLoginPass(newPassword);
		AccountLogin login = accountService.getEntityById(AccountLogin.class, getLoginId());
		if (!TxtUtil.digest(oldPassword).equals(login.getLoginPass())) {
			super.renderJson(Response.success("原密码错误"));
			return NONE;
		}
		accountLogin.setId(getLogin().getId());
		// accountService.doUpdatePass(accountLogin);
		// this.renderJson(Response.success("完成密码更新"));
		super.renderJson(Response.success("修改成功"));
		return NONE;
	}

	/**
	 * 注销用户
	 * 
	 * @author juror
	 * @date 2010-10-19 上午10:15:17
	 * @return
	 */
	public String logout() {
		this.putLoginId(null);
		return INPUT;
	}

	public AccountLogin getAccountLogin() {
		return accountLogin;
	}

	public void setAccountLogin(AccountLogin accountLogin) {
		this.accountLogin = accountLogin;
	}

	public List<SysRole> getRoleL() {
		return roleL;
	}

	public Page<AccountLogin> getLoginPage() {
		return loginPage;
	}

	public List<SysRole> getDistRoleL() {
		return distRoleL;
	}

	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Long[] getRids() {
		return rids;
	}

	public void setRids(Long[] rids) {
		this.rids = rids;
	}

	public String getIdsStr() {
		return idsStr;
	}

	public void setIdsStr(String idsStr) {
		this.idsStr = idsStr;
	}

}
