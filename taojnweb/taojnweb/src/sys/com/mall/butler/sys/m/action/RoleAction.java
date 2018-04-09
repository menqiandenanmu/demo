package com.mall.butler.sys.m.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.sys.m.service.MRoleService;
import com.mall.butler.sys.m.vo.FunctionsVo;
import com.mall.butler.sys.model.SysFunctions;
import com.mall.butler.sys.model.SysRole;
import com.mall.butler.sys.service.RoleService;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 类描述：角色管理
 * 类名称：RoleAction
 * 创建人：caedmon
 * 创建时间：2013-5-8 下午10:15:15
 * 修改人：caedmon
 * 修改时间：2013-5-8 下午10:15:15
 * 修改备注：
 * 
 * @version
 */
public class RoleAction extends ManageBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2148896972382745188L;
	private static final String INFO_EDIT = "infoEdit";

	@Autowired
	private MRoleService mRoleService;
	@Autowired
	private RoleService roleService;
	/**
	 * 列表信息
	 */
	private Page<SysRole> rolePage;
	private SysRole role = new SysRole();
	/**
	 * 主键
	 */
	private Long id;

	public List<FunctionsVo> funcs;
	public Long[] fids = {};

	/**
	 * 列表
	 */
	public String execute() {
		if (role == null)
			role = new SysRole();
		PageRequest<SysRole> pageRequest = super.newPage(SysRole.class);
		pageRequest.setPageNumber(currPage);
		role.setCreateLoginId(getLoginId());
		pageRequest.setFilters(role);
		rolePage = roleService.queryRole(pageRequest);
		return LIST;
	}

	/**
	 * 添加页面
	 * 
	 * @author zhaoxs
	 * @date 2010-10-22 下午03:26:18
	 * @return
	 */
	public String add() {
		return ADD;
	}

	/**
	 * 保存添加
	 * 
	 * @author zhaoxs
	 * @date 2010-10-22 下午03:26:34
	 * @return
	 */
	public String save() {
		if (TxtUtil.isEmpty(role.getRoleName())) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("名称不可以为空!");
		}
		role.setAccountId(getAccount().getId());
		role.setCreateLoginId(getLoginId());

		roleService.doAddRole(role);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("添加角色成功!");
		return JDIALOG;
	}

	/**
	 * 修改页面
	 * 
	 * @author zhaoxs
	 * @date 2010-10-22 下午03:26:43
	 * @return
	 */
	public String edit() {
		role = mRoleService.getEntityById(SysRole.class, id);
		return EDIT;
	}

	/**
	 * 保存修改
	 * 
	 * @author zhaoxs
	 * @date 2010-10-22 下午03:26:57
	 * @return
	 */
	public String update() {
		role.setId(id);
		roleService.doEditRole(role);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("角色更新成功!");
		return JDIALOG;
	}

	/**
	 * 删除权限
	 * 
	 * @author zhaoxs
	 * @date 2010-10-22 下午08:52:07
	 * @return
	 */
	public String del() {
		role = mRoleService.getEntityById(SysRole.class, id);
		if (role == null) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("无效信息,当前角色不存在!");
		} else if (!role.getCreateLoginId().equals(getLoginId())) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("不权限删,当前角色,只有创建人才可以此操作!");
		} else {
			roleService.doDeleteRole(role);
			msgInfo.setFlag(MessageDialog.SUCCESS);
			msgInfo.addMessage("角色删除成功!");
		}
		return JDIALOG;
	}

	/**
	 * 权限页面
	 * 
	 * @author zhaoxs
	 * @date 2010-10-22 下午09:28:32
	 * @return
	 */
	public String infoEdit() {
		role = mRoleService.getEntityById(SysRole.class, id);
		if (role == null) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("无效的角色信息!");
			return DIALOG;
		} else {
			List<SysFunctions> functionsL = new ArrayList<SysFunctions>();
			List<SysFunctions> distFunL = new ArrayList<SysFunctions>();
			if (role.getRoleAccType() == 0) {
				AccountLogin login = this.getLogin();
				functionsL = mRoleService.queryFuncByLogin(login);
				distFunL = mRoleService.queryFuncByRole(role);
			}
			Map<Long, FunctionsVo> map = new Hashtable<Long, FunctionsVo>();
			// 整理内容
			for (SysFunctions sfIndex : functionsL) {
				FunctionsVo indexVo = new FunctionsVo();
				indexVo.setId(sfIndex.getId());
				indexVo.setCheckFlage(false);
				indexVo.setFunName(sfIndex.getFunName());
				indexVo.setFunType(sfIndex.getFunType());
				indexVo.setFunUrl(sfIndex.getFunUrl());
				map.put(sfIndex.getId(), indexVo);
			}
			// 设置先中标志
			for (SysFunctions sfIndex : distFunL) {
				FunctionsVo indexVo = map.get(sfIndex.getId());
				if (indexVo != null)
					indexVo.setCheckFlage(true);
			}

			funcs = new ArrayList<FunctionsVo>();
			// 创建树
			for (SysFunctions sfIndex : functionsL) {
				FunctionsVo indexVo = map.get(sfIndex.getId());
				if (sfIndex.getParentId() == null || sfIndex.getParentId().equals(sfIndex.getId())) {
					funcs.add(indexVo);
				} else {
					FunctionsVo parent = map.get(sfIndex.getParentId());
					parent.getChilds().add(indexVo);
					indexVo.setParent(parent);
				}
			}
		}
		return INFO_EDIT;
	}

	/**
	 * 权限更新
	 * 
	 * @author zhaoxs
	 * @date 2010-10-22 下午09:29:11
	 * @return
	 */
	public String infoUpdate() {
		role = mRoleService.getEntityById(SysRole.class, id);
		if (role == null) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("无效的角色信息!");
		} else {
			List<SysFunctions> distL = new ArrayList<SysFunctions>();
			for (Long index : fids) {
				SysFunctions roleIndex = mRoleService.getEntityById(SysFunctions.class, index);
				distL.add(roleIndex);
			}
			if (role.getRoleAccType() == 0) {
				mRoleService.doUpdateRoleFuns(role, distL, getLogin());
			}
			msgInfo.setFlag(MessageDialog.SUCCESS);
			msgInfo.addMessage("更新角色成功!");
		}
		return JDIALOG;
	}

	public void setFids(Long[] fids) {
		this.fids = fids;
	}

	public SysRole getRole() {
		return role;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}

	public Page<SysRole> getRolePage() {
		return rolePage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<FunctionsVo> getFuncs() {
		return funcs;
	}

}
