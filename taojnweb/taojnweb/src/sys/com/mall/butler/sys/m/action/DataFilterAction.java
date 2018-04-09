package com.mall.butler.sys.m.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.sys.m.service.MSysDataFilterService;
import com.mall.butler.sys.model.SysDataFilter;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * @author zhaoxs 2013-1-31 上午10:56:52
 */
public class DataFilterAction extends ManageBaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private MSysDataFilterService sysDataFilterService;

	/**
	 * 黑名单信息
	 */
	private SysDataFilter dataFilter;
	private Page<SysDataFilter> pages;

	/**
	 * 列表
	 */

	public String execute() {
		PageRequest<SysDataFilter> pageRequest = this.newPage(SysDataFilter.class);
		pageRequest.setPageNumber(currPage);
		if (dataFilter == null) {
			dataFilter = new SysDataFilter();
		}
		pageRequest.setFilters(dataFilter);
		pages = sysDataFilterService.queryDataFilterPage(pageRequest);
		return LIST;
	}

	/**
	 * 添加
	 */
	public String add() {
		return ADD;
	}

	/**
	 * 删除
	 */
	public String del() {
		if (id == null)
			throw new RuntimeException("数据异常");
		dataFilter = sysDataFilterService.getEntityById(SysDataFilter.class, id);
		if (dataFilter == null || dataFilter.getDeleted())
			throw new RuntimeException("数据异常");
		sysDataFilterService.doDelDataFilter(dataFilter);
		msgInfo.addMessage("黑名单删除成功!");
		return JDIALOG;
	}

	/**
	 * 保存
	 */
	public String save() {
		if (TxtUtil.isEmpty(dataFilter.getFilterValue())) {
			throw new RuntimeException("过滤对象为空");
		}
		if (dataFilter.getFilterType() == 1) {
			if (!TxtUtil.isIDNumber(dataFilter.getFilterValue().trim())) {
				throw new RuntimeException("身份证号码格式不正确！");
			}
		} else if (dataFilter.getFilterType() == 0) {
			if (!TxtUtil.isMobile(dataFilter.getFilterValue().trim())) {
				throw new RuntimeException("手机号码格式不正确！");
			}
		} else if (dataFilter.getFilterType() == 2) {
			if (!TxtUtil.isIPAddress(dataFilter.getFilterValue().trim())) {
				throw new RuntimeException("IP地址格式不正确！");
			}
		}
		dataFilter.setCreateUser(getLogin().getId());
		sysDataFilterService.doAddDataFilter(dataFilter);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("黑名单添加成功!");
		return JDIALOG;
	}

	/**
	 * 修改页面
	 */
	public String edit() {
		dataFilter = sysDataFilterService.getEntityById(SysDataFilter.class, id);
		if (dataFilter == null || dataFilter.getDeleted())
			throw new RuntimeException("数据异常");
		return EDIT;
	}

	/**
	 *保存修改
	 */
	public String update() {
		dataFilter.setId(id);
		sysDataFilterService.doUpdateDataFilter(dataFilter);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("信息修改成功!");
		return JDIALOG;
	}

	public SysDataFilter getDataFilter() {
		return dataFilter;
	}

	public void setDataFilter(SysDataFilter dataFilter) {
		this.dataFilter = dataFilter;
	}

	public void setPages(Page<SysDataFilter> pages) {
		this.pages = pages;
	}

	public Page<SysDataFilter> getPages() {
		return pages;
	}

}
