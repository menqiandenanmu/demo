package com.mall.butler.sys.m.service;

import java.util.List;

import com.mall.butler.service.BaseService;
import com.mall.butler.sys.model.SysDataFilter;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * @author caedmon
 * @version 创建时间：2013-1-31 上午10:58:57
 */
public interface MSysDataFilterService extends BaseService {
	/**
	 * 查找所有黑名单信息
	 * 
	 * @param sysDataFilter
	 * @return
	 */
	List<SysDataFilter> queryAll(SysDataFilter sysDataFilter);

	/**
	 * 修改黑名单信息
	 * 
	 * @param sysDataFilter
	 */
	void doUpdateDataFilter(SysDataFilter sysDataFilter);

	/**
	 * 添加黑名单信息
	 * 
	 * @param sysDataFilter
	 */
	void doAddDataFilter(SysDataFilter sysDataFilter);

	/**
	 * 删除黑名单信息
	 * 
	 * @param sysDataFilter
	 */
	void doDelDataFilter(SysDataFilter sysDataFilter);

	/**
	 * 黑名单分页查询
	 * 
	 * @param pageRequest
	 */
	public Page<SysDataFilter> queryDataFilterPage(PageRequest<SysDataFilter> pageRequest);
}
