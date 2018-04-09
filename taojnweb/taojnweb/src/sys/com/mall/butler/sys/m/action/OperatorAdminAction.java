package com.mall.butler.sys.m.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.MAccountService;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.sys.m.service.MRoleService;
import com.mall.butler.sys.model.SysRole;
import com.mall.butler.sys.service.RoleService;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 类描述：系统管理操作员管理
 * 类名称：OperatorAdminAction
 * 创建人：caedmon
 * 创建时间：2013-5-8 下午10:14:36
 * 修改人：caedmon
 * 修改时间：2013-5-8 下午10:14:36
 * 修改备注：
 * 
 * @version
 */
public class OperatorAdminAction extends ManageBaseAction {

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
	private Long[] rids = {};
	private Long id;

	/**
	 * 列表页面
	 */
	public String execute() {
		PageRequest<AccountLogin> pageRequest = super.newPage(AccountLogin.class);
		pageRequest.setPageNumber(currPage);
		AccountLogin param = new AccountLogin();
		param.setStatus(accountLogin.getStatus());
		if (!TxtUtil.isEmpty(accountLogin.getLoginName()))
			param.setLoginName("%" + accountLogin.getLoginName().trim() + "%");
		if (!TxtUtil.isEmpty(accountLogin.getRealname()))
			param.setRealname("%" + accountLogin.getRealname().trim() + "%");
		pageRequest.setFilters(param);
		loginPage = mAccountService.queryAdminOperator(pageRequest);
		return LIST;
	}

	/**
	 * 新增操作员
	 * 
	 * @author zhaoxs
	 * @date 2010-10-21 上午08:57:07
	 * @return
	 */
	public String add() {
		return ADD;
	}

	/**
	 * 保存新增
	 * 
	 * @author zhaoxs
	 * @date 2010-10-21 上午08:57:31
	 * @return
	 */
	public String save() {
		if (TxtUtil.isEmpty(accountLogin.getRealname())) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("名字不能为空!");
			return JDIALOG;
		}
		if (TxtUtil.isEmpty(accountLogin.getLoginPass())) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("密码不能为空!");
			return JDIALOG;
		}
		accountLogin.setAccountId(this.getLogin().getAccountId());
		accountLogin.setCreateLoginId(this.getLoginId());
		accountLogin.setAdminFlag(false);
		mAccountService.doRegOperator(accountLogin);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("更新操作员成功!");
		return JDIALOG;
	}

	/**
	 * 修改页面
	 * 
	 * @author zhaoxs
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
	 * @author zhaoxs
	 * @date 2010-10-21 上午09:26:04
	 * @return
	 */
	public String update() {
		accountLogin.setId(id);
		if (TxtUtil.isEmpty(accountLogin.getRealname())) {
			this.addActionError("真实名不可以为空!");
			return EDIT;
		}
		if (TxtUtil.isEmpty(accountLogin.getLoginPass())) {
			this.addActionError("密码不可以为空!");
			return EDIT;
		}
		msgInfo.setFlag(MessageDialog.SUCCESS);
		mAccountService.doEditOperator(accountLogin);
		msgInfo.addMessage("更新操作员成功!");
		return JDIALOG;
	}

	/**
	 * 删除操作
	 * 
	 * @author zhaoxs
	 * @date 2010-10-21 下午04:00:29
	 * @return
	 */
	public String del() {
		// 不可以删除自己
		if (id.equals(this.getLoginId())) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("操作失败!不可以删除自己!");
			return JDIALOG;
		}
		// 不是管理员或者不是自己建的操作员不可以删除
		if (!getLogin().getCreateLoginId().equals(id) && !getLogin().getAdminFlag()) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("操作失败!无权限删除此用户!");
			return JDIALOG;
		}
		accountLogin.setId(id);
		mAccountService.doDeleteOperator(accountLogin);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("更新操作员成功!");
		return JDIALOG;
	}

	/**
	 * 权限分配页面
	 * 
	 * @author zhaoxs
	 * @date 2010-10-21 下午08:01:00
	 * @return
	 */
	public String roleEdit() {
		if (id.equals(getLoginId())) {
			msgInfo.setFlag(MessageDialog.WARNING);
			msgInfo.addMessage("不能对自己进行角色操作!");
			return DIALOG;
		} else {
			distRoleL = new ArrayList<SysRole>();
			accountLogin = mAccountService.getEntityById(AccountLogin.class, id);
			List<SysRole> tempRoleL = roleService.queryRoleAssign(this.getLogin());
			List<SysRole> tempDistRoleL = roleService.queryRoleSelf(accountLogin);
			Map<Long, SysRole> filter = new Hashtable<Long, SysRole>();
			for (SysRole index : tempRoleL) {
				filter.put(index.getId(), index);
			}
			for (SysRole index : tempDistRoleL) {
				SysRole temp = filter.get(index.getId());
				if (temp != null) {
					distRoleL.add(temp);
					filter.remove(index.getId());
				}
			}
			roleL = new ArrayList<SysRole>();
			roleL.addAll(filter.values());
			return ROLE_EDIT;
		}
	}

	/**
	 * 执行权限分配
	 * 
	 * @author zhaoxs
	 * @date 2010-10-21 下午08:00:43
	 * @return
	 */
	public String roleUpdate() {
		AccountLogin distLogin = mAccountService.getEntityById(AccountLogin.class, id);
		if (distLogin == null) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("无效的操作员!");
			return JDIALOG;
		} else {
			List<SysRole> roles = new ArrayList<SysRole>();
			// if (rids != null)
			for (Long index : rids) {
				SysRole temp = mRoleService.getEntityById(SysRole.class, index);
				if (temp == null) {
					msgInfo.setFlag(MessageDialog.ERROR);
					msgInfo.addMessage("无效的操作员!");
					return JDIALOG;
				} else {
					roles.add(temp);
				}
			}
			roleService.doUpdateLoginRole(distLogin, roles, getLogin());
			msgInfo.setFlag(MessageDialog.SUCCESS);
			msgInfo.addMessage("操作员角色更新成功!");
			return JDIALOG;
		}
	}

	// ///////////
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setRids(Long[] rids) {
		this.rids = rids;
	}

	public List<SysRole> getDistRoleL() {
		return distRoleL;
	}

}
