package com.mall.butler.point.m.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.account.m.service.PointRuleService;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.point.model.PointRule;
import com.mall.butler.sys.m.service.MDictService;
import com.mall.butler.sys.model.SysDictDetail;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class PointRuleAction extends ManageBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5314752610730896158L;
	/**
	 * 
	 */
	
	@Autowired
	private PointRuleService  pointRuleService;
	private  PointRule pointRule;
	//分页对象
	private Page<PointRule> pages;
	private  List<SysDictDetail> sysDictDetails;
	@Autowired
	private MDictService mDictService;
	
	
	public String execute() {
		if(null==pointRule)
			pointRule=new PointRule();
		PageRequest<PointRule> filter = this.newPage(PointRule.class);
		filter.setFilters(pointRule);
		filter.setPageNumber(this.currPage);
		pages = pointRuleService.page(filter);
		sysDictDetails= mDictService.queryDetail(ManageContext.DICT_STATIC_CLASS);
		return SUCCESS;
	}
	public String add() {
		if (null == pointRule)
			pointRule = new PointRule();
		sysDictDetails= mDictService.queryDetail(ManageContext.DICT_STATIC_CLASS);
		return ADD;
	}
	
	
	
	public String save() {
		pointRuleService.doAddSave(pointRule);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("操作成功!");
		return JDIALOG;
	}
	


	public String edit() {
		pointRule = pointRuleService.getEntityById(PointRule.class, id);
		if (null == pointRule)
			throw new RuntimeException("记录不存在");
		sysDictDetails= mDictService.queryDetail(ManageContext.DICT_STATIC_CLASS);
		return EDIT;
	}
	
	
	/**
	 * 保存更新
	 * 
	 * @date 2010-10-21 上午09:26:04
	 * @return
	 */
	public String update() {
		pointRule.setId(id);
		pointRuleService.doUpdate(pointRule);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("更新成功!");
		return JDIALOG;
	}
	
	

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String del() {
		if (pointRule == null) {
			pointRule = new PointRule();
		}
		pointRule.setId(id);
		pointRuleService.doDel(pointRule);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("删除成功!");
		return JDIALOG;
	}
	public PointRule getPointRule() {
		return pointRule;
	}
	public void setPointRule(PointRule pointRule) {
		this.pointRule = pointRule;
	}
	public Page<PointRule> getPages() {
		return pages;
	}
	public void setPages(Page<PointRule> pages) {
		this.pages = pages;
	}
	public List<SysDictDetail> getSysDictDetails() {
		return sysDictDetails;
	}
	public void setSysDictDetails(List<SysDictDetail> sysDictDetails) {
		this.sysDictDetails = sysDictDetails;
	}


}
