package com.mall.butler.sys.m.service;

import java.util.List;

import com.mall.butler.service.BaseService;
import com.mall.butler.sys.model.SysFunctions;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 模块菜单
 * 
 * @author caedmon
 * 
 */
public interface MSysFunctionService extends BaseService {

	/**
	 * 管理平台分页查询模块菜单
	 * 
	 * @param pageRequest
	 */
	public Page<SysFunctions> queryMFunctionPage(
			PageRequest<SysFunctions> pageRequest);

	/**
	 * 删除功能URL
	 * 
	 * @param sysFunctions
	 */
	public void doDelSysFunction(SysFunctions sysFunctions);

	/**
	 * 查找功能菜单
	 * 
	 * @param sysFunctions
	 * @return
	 */
	public List<SysFunctions> findFunctions(SysFunctions sysFunctions);

	/**
	 * 查找未指定父模块功能连接
	 * 
	 * @param sysFunctions
	 * @return
	 */
	public List<SysFunctions> findNFunctions(SysFunctions sysFunctions);

	/**
	 * 编辑保存
	 * 
	 * @param sysFunctions
	 */
	public void doEditSysFunctions(SysFunctions sysFunctions);

	/**
	 * 新增
	 * 
	 * @param sysFunctions
	 */
	public void doAddSysFunions(SysFunctions sysFunctions);

	/**
	 * 更新父模块子模块
	 * 
	 * @param fId
	 *            父ID
	 * @param cIds
	 *            子模块ID
	 */
	public void doUpdateSubFunctions(Long fId, Long[] cIds);
}
