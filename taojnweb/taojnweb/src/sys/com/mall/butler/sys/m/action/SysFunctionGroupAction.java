package com.mall.butler.sys.m.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.sys.m.service.MSysFunctionService;
import com.mall.butler.sys.model.SysFunctions;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 类描述：功能组管理
 * 类名称：SysFunctionGroupAction
 * 创建人：caedmon
 * 创建时间：2013-5-8 下午10:15:46
 * 修改人：caedmon
 * 修改时间：2013-5-8 下午10:15:46
 * 修改备注：
 * 
 * @version
 */
public class SysFunctionGroupAction extends ManageBaseAction {

	private static final long serialVersionUID = -4397154911830317692L;
	private static final String GROUP_CONFIG = "groupConfig";
	@Autowired
	private MSysFunctionService mSysFunctionService;

	private SysFunctions sysFunctions;
	private Page<SysFunctions> pages;
	// 未指定父模块的子模块列表
	private List<SysFunctions> noSelectList;
	// 当前父模块所包含的子模块列表
	private List<SysFunctions> ownList;
	private Long[] rids;

	/**
	 * 功能组列表
	 */
	public String execute() {
		PageRequest<SysFunctions> pageRequest = this.newPage(SysFunctions.class);
		pageRequest.setPageNumber(currPage);
		SysFunctions param = new SysFunctions();
		if (sysFunctions == null) {
			sysFunctions = new SysFunctions();
		}
		if (!TxtUtil.isEmpty(sysFunctions.getFunName())) {
			param.setFunName(sysFunctions.getFunName().trim());
		}
		if (sysFunctions.getFunAccType() != null) {
			param.setFunAccType(sysFunctions.getFunAccType());
		}
		param.setFunType(0);
		pageRequest.setFilters(param);
		pages = mSysFunctionService.queryMFunctionPage(pageRequest);
		return LIST;
	}

	/**
	 * 新增
	 */
	public String add() {

		return ADD;
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	public String save() {
		mSysFunctionService.doAddSysFunions(sysFunctions);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("新增成功!");
		return JDIALOG;
	}

	/**
	 * 编辑页面
	 * 
	 * @return
	 */
	public String edit() {

		return EDIT;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String del() {
		sysFunctions = mSysFunctionService.getEntityById(SysFunctions.class, id);
		if (sysFunctions == null) {
			throw new RuntimeException("无效的信息!");
		}
		mSysFunctionService.doDelSysFunction(sysFunctions);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("删除成功!");
		return JDIALOG;
	}

	/**
	 * 设置组成员(子功能)
	 * 
	 * @return
	 */
	public String groupConfig() {
		sysFunctions = mSysFunctionService.getEntityById(SysFunctions.class, id);
		SysFunctions paramN = new SysFunctions();
		paramN.setFunAccType(sysFunctions.getFunAccType());
		// 得到未设置父模块功能连接
		noSelectList = mSysFunctionService.findNFunctions(paramN);

		SysFunctions param = new SysFunctions();
		param.setParentId(sysFunctions.getId());
		param.setFunType(1);
		// 得到当前父模块包含的子模块
		ownList = mSysFunctionService.findFunctions(param);

		return GROUP_CONFIG;
	}

	/**
	 * 设置后保存
	 * 
	 * @return
	 */
	public String update() {
		mSysFunctionService.doUpdateSubFunctions(id, rids);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("设置成功!");
		return JDIALOG;
	}

	public SysFunctions getSysFunctions() {
		return sysFunctions;
	}

	public void setSysFunctions(SysFunctions sysFunctions) {
		this.sysFunctions = sysFunctions;
	}

	public Page<SysFunctions> getPages() {
		return pages;
	}

	public void setPages(Page<SysFunctions> pages) {
		this.pages = pages;
	}


	public List<SysFunctions> getNoSelectList() {
		return noSelectList;
	}

	public void setNoSelectList(List<SysFunctions> noSelectList) {
		this.noSelectList = noSelectList;
	}

	public List<SysFunctions> getOwnList() {
		return ownList;
	}

	public void setOwnList(List<SysFunctions> ownList) {
		this.ownList = ownList;
	}

	public Long[] getRids() {
		return rids;
	}

	public void setRids(Long[] rids) {
		this.rids = rids;
	}
}
