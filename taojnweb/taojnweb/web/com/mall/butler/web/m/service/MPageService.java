package com.mall.butler.web.m.service;

import com.mall.butler.service.BaseService;
import com.mall.butler.web.model.PageAreaDetail;
import com.mall.butler.web.model.PageAreaInfo;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 页面区域广告管理
 * 
 * @author zhaoxs
 */
public interface MPageService extends BaseService {
	/**
	 * 添加页面区域信息
	 * 
	 * @param area
	 */
	public void doSavePageArea(PageAreaInfo area);

	/**
	 * 添加区域内容信息
	 * 
	 * @param area
	 * @param detail
	 */
	public void doSavePageDetail(PageAreaInfo area, PageAreaDetail detail);

	/**
	 * 分页查找条件区域内容信息
	 * 
	 * @param area
	 * @return
	 */
	public Page<PageAreaDetail> queryPageDetail(PageRequest<PageAreaDetail> area);

	/**
	 * 修改页面区域信息
	 * 只能修改说明和长度
	 * 
	 * @param area
	 */
	void doUpdatePageArea(PageAreaInfo area);

	/**
	 * 修改页面内容明细
	 * 
	 * @param detail
	 */
	void doUpdatePageDetail(PageAreaDetail detail);

	/**
	 * 分页查找页面区域信息
	 * 
	 * @param area
	 * @return
	 */
	Page<PageAreaInfo> queryPageAreaPage(PageRequest<PageAreaInfo> area);

	/**
	 * 删除页面区域信息
	 * 
	 * @param area
	 */
	public void doDelPageArea(PageAreaInfo area);

	/**
	 * 删除页面内容信息
	 * 
	 * @param detail
	 */
	void doDelPageDetail(PageAreaDetail detail);
}
