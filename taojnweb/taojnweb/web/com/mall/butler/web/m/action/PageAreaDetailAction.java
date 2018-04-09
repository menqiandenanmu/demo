package com.mall.butler.web.m.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.web.m.service.MPageService;
import com.mall.butler.web.model.PageAreaDetail;
import com.mall.butler.web.model.PageAreaInfo;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 类描述：页面区域内容管理
 * 类名称：PageAreaDetailAction
 * 创建人：caedmon
 * 创建时间：2013-5-18 下午09:46:48
 * 修改人：caedmon
 * 修改时间：2013-5-18 下午09:46:48
 * 修改备注：
 * 
 * @version
 */
public class PageAreaDetailAction extends ManageBaseAction {

	private static final long serialVersionUID = -2148896972382745188L;

	@Autowired
	private MPageService pageService;
	/**
	 * 列表信息
	 */
	private Page<PageAreaDetail> detailPage;
	private PageAreaDetail areaDetail;
	private PageAreaInfo area;
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 列表
	 */
	public String execute() {
		PageRequest<PageAreaDetail> request = super.newPage(PageAreaDetail.class);
		PageAreaDetail filter = new PageAreaDetail();
		if (areaDetail != null && !TxtUtil.isEmpty(areaDetail.getAreaName()))
			filter.setAreaName("%" + areaDetail.getAreaName().trim() + "%");
		if (areaDetail != null && !TxtUtil.isEmpty(areaDetail.getTitle()))
			filter.setTitle("%" + areaDetail.getTitle().trim() + "%");
		if (areaDetail != null && areaDetail.getAreaId() != null)
			filter.setAreaId(areaDetail.getAreaId());
		request.setPageNumber(this.currPage);
		request.setFilters(filter);
		detailPage = pageService.queryPageDetail(request);
		return LIST;
	}

	/**
	 * 添加页面内容
	 * 
	 * @return
	 */
	public String add() {
		area = pageService.getEntityById(PageAreaInfo.class, id);
		if (area == null || area.getDeleted())
			throw new RuntimeException("无效的区域信息!");
		return ADD;
	}

	/**
	 * 保存新增
	 * 
	 * @return
	 */
	public String save() {
		if (TxtUtil.isEmpty(areaDetail.getTitle()))
			throw new RuntimeException("标题不可以为空!");
		areaDetail.setTitle(areaDetail.getTitle().trim());
		if (TxtUtil.isEmpty(areaDetail.getInfoUrl()))
			throw new RuntimeException("地址URL不可以为空!");
		areaDetail.setInfoUrl(areaDetail.getInfoUrl().trim());
		area = pageService.getEntityById(PageAreaInfo.class, id);
		if (area == null || area.getDeleted())
			throw new RuntimeException("无效的区域信息!");
		pageService.doSavePageDetail(area, areaDetail);
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
		areaDetail = pageService.getEntityById(PageAreaDetail.class, id);
		if (areaDetail == null || areaDetail.getDeleted())
			throw new RuntimeException("数据不存在或已经删除!");
		area = pageService.getEntityById(PageAreaInfo.class, areaDetail.getAreaId());
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
		areaDetail.setId(id);
		pageService.doUpdatePageDetail(areaDetail);
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
		areaDetail = pageService.getEntityById(PageAreaDetail.class, id);
		if (areaDetail == null) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("无效的数据!");
		} else {
			pageService.doDelPageDetail(areaDetail);
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

	public PageAreaDetail getAreaDetail() {
		return areaDetail;
	}

	public void setAreaDetail(PageAreaDetail areaDetail) {
		this.areaDetail = areaDetail;
	}

	public Page<PageAreaDetail> getDetailPage() {
		return detailPage;
	}
}
