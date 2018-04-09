package com.mall.butler.sys.m.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.sys.m.service.MSysParamService;
import com.mall.butler.sys.model.SysParam;
import com.mall.util.common.action.MessageDialog;

/**
 * 类描述：系统参数管理
 * 类名称：ParamAction
 * 创建人：caedmon
 * 创建时间：2013-5-8 下午10:14:46
 * 修改人：caedmon
 * 修改时间：2013-5-8 下午10:14:46
 * 修改备注：
 * 
 * @version
 */
public class ParamAction extends ManageBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2148896972382745188L;

	@Autowired
	private MSysParamService mSysParamService;
	/**
	 * 列表信息
	 */
	private List<SysParam> paramL;
	private SysParam param;
	private AccountInfo accountInfo;
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 列表
	 */
	public String execute() {
		accountInfo = getAccount();
		if (param == null) {
			param = new SysParam();
		}
		paramL = mSysParamService.queryAll(param);
		return LIST;
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	public String add() {
		return ADD;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String del() {
		if (id == null) {
			throw new RuntimeException("数据异常");
		}
		if (param == null) {
			param = new SysParam();
		}
		param.setId(id);
		mSysParamService.doDelParam(param);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("参数删除成功!");
		return JDIALOG;
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	public String save() {
		mSysParamService.doAddParam(param, getLogin());
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("参数添加成功!");
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
		param = mSysParamService.getEntityById(SysParam.class, id);
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
		param.setId(id);
		mSysParamService.doUpdate(param);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("参数修改成功!");
		return JDIALOG;
	}

	public String refresh() {
		mSysParamService.refresnParam();
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("刷新成功!");
		return JDIALOG;
	}

	public SysParam getParam() {
		return param;
	}

	public void setParam(SysParam param) {
		this.param = param;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SysParam> getParamL() {
		return paramL;
	}

	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}

}
