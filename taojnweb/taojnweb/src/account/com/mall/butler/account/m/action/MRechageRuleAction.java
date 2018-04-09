package com.mall.butler.account.m.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.MRechageRuleService;
import com.mall.butler.account.model.RechageRule;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MRechageRuleAction extends ManageBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RechageRule rechageRule;
	private Page<RechageRule> page;
	@Autowired
	private MRechageRuleService rechageRuleService;
	public String execute() {
		if(null==rechageRule)
			rechageRule=new RechageRule();
		PageRequest<RechageRule> pageRequest = super.newPage(RechageRule.class);
		pageRequest.setFilters(rechageRule);
		pageRequest.setPageNumber(currPage);
		page = rechageRuleService.pageQuery(pageRequest);
		return LIST;
	}
	
	/**
	 * 新增页面
	 * 
	 * @return
	 */
	public String add() {
		return ADD;
	}

	/**
	 * 保存操作
	 * 
	 * @return
	 */
	public String save() {
		rechageRuleService.doSave(rechageRule);
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
		rechageRule.setId(id);
		rechageRuleService.doUpdate(rechageRule);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("编辑成功!");
		return JDIALOG;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String del() {
		rechageRule=rechageRuleService.getEntityById(RechageRule.class,id);
		rechageRuleService.doDel(rechageRule);
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
		rechageRule=rechageRuleService.getEntityById(RechageRule.class,id);
		if (rechageRule == null) {
			throw new RuntimeException("无效的信息!");
		}
		return EDIT;
	}

	public RechageRule getRechageRule() {
		return rechageRule;
	}

	public void setRechageRule(RechageRule rechageRule) {
		this.rechageRule = rechageRule;
	}

	public Page<RechageRule> getPage() {
		return page;
	}

	public void setPage(Page<RechageRule> page) {
		this.page = page;
	}

}
