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
 * 类描述：系统模块(菜单)添加
 * 类名称：SysFunctionAction
 * 创建人：caedmon
 * 创建时间：2013-5-8 下午10:15:38
 * 修改人：caedmon
 * 修改时间：2013-5-8 下午10:15:38
 * 修改备注：
 * 
 * @version
 */
public class SysFunctionAction extends ManageBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4172380945231890982L;
	@Autowired
	private MSysFunctionService mSysFunctionService;

	private SysFunctions sysFunctions;
	private Page<SysFunctions> pages;
	// 父模块列表
	private List<SysFunctions> functions;

	/**
	 * 功能列表
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
		if ((sysFunctions.getFunAccType() != null) && (-1 != sysFunctions.getFunAccType()))
			param.setFunAccType(sysFunctions.getFunAccType());
		pageRequest.setFilters(param);
		pages = mSysFunctionService.queryMFunctionPage(pageRequest);
		return LIST;
	}

	/**
	 * 新增界面
	 * 
	 * @return
	 */
	public String add() {
		// 获得系统平台类型下的组模块列表

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
	 * 编辑
	 * 
	 * @return
	 */
	public String edit() {
		sysFunctions = mSysFunctionService.getEntityById(SysFunctions.class, id);
		if (sysFunctions.getFunType() == 1) {
			SysFunctions param = new SysFunctions();
			param.setFunType(0);
			param.setFunAccType(sysFunctions.getFunAccType());
			functions = mSysFunctionService.findFunctions(param);
		}
		return EDIT;
	}

	/**
	 * 编辑保存
	 * 
	 * @return
	 */
	public String update() {
		sysFunctions.setId(id);
		mSysFunctionService.doEditSysFunctions(sysFunctions);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("修改成功!");
		return JDIALOG;
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

	public List<SysFunctions> getFunctions() {
		return functions;
	}

	public void setFunctions(List<SysFunctions> functions) {
		this.functions = functions;
	}
}
