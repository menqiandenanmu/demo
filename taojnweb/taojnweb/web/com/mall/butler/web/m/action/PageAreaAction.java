package com.mall.butler.web.m.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.web.m.service.MPageService;
import com.mall.butler.web.model.PageAreaInfo;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 类描述：系统页面区域管理（广告位）
 * 类名称：PageAreaAction
 * 创建人：caedmon
 * 创建时间：2013-5-18 下午09:46:38
 * 修改人：caedmon
 * 修改时间：2013-5-18 下午09:46:38
 * 修改备注：
 * 
 * @version
 */
public class PageAreaAction extends ManageBaseAction {

	private static final long serialVersionUID = -2148896972382745188L;

	@Autowired
	private MPageService pageService;
	/**
	 * 列表信息
	 */
	private Page<PageAreaInfo> areaPage;
	private PageAreaInfo area;
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 列表
	 */
	public String execute() {
		PageRequest<PageAreaInfo> request = super.newPage(PageAreaInfo.class);
		request.setPageNumber(this.currPage);
		PageAreaInfo filter = new PageAreaInfo();
		if (area != null && !TxtUtil.isEmpty(area.getName()))
			filter.setName("%" + area.getName().trim() + "%");
		request.setFilters(filter);
		areaPage = pageService.queryPageAreaPage(request);
		return LIST;
	}

	/**
	 * 添加页面区域
	 * 
	 * @return
	 */
	public String add() {
		return ADD;
	}

	/**
	 * 保存新增
	 * 
	 * @return
	 */
	public String save() {
		if (TxtUtil.isEmpty(area.getCode()))
			throw new RuntimeException("编号不可以为空!");
		area.setCode(area.getCode().trim());
		if (TxtUtil.isEmpty(area.getName()))
			throw new RuntimeException("名称不可以为空!");
		area.setName(area.getName().trim());
		if (area.getLen() <= 0)
			throw new RuntimeException("长度不可以小于0!");
		pageService.doSavePageArea(area);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("添加成功!");
		return JDIALOG;
	}

	/**
	 * 修改页面
	 * 
	 * @return
	 */
	public String edit() {
		area = pageService.getEntityById(PageAreaInfo.class, id);
		if (area == null || area.getDeleted())
			throw new RuntimeException("数据不存在或已经删除!");
		return EDIT;
	}

	/**
	 * 保存修改
	 * 
	 * @return
	 */
	public String update() {
		area.setId(id);
		pageService.doUpdatePageArea(area);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("修改成功!");
		return JDIALOG;
	}

	/**
	 * 删除明细
	 * 
	 * @return
	 */
	public String del() {
		area = pageService.getEntityById(PageAreaInfo.class, id);
		if (area == null) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("无效的数据!");
		} else {
			pageService.doDelPageArea(area);
			msgInfo.setFlag(MessageDialog.SUCCESS);
			msgInfo.addMessage("删除操作完成!");
		}
		return JDIALOG;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PageAreaInfo getArea() {
		return area;
	}

	public void setArea(PageAreaInfo area) {
		this.area = area;
	}

	public Page<PageAreaInfo> getAreaPage() {
		return areaPage;
	}
}
