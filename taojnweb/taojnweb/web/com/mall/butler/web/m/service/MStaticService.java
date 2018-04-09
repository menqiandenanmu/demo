package com.mall.butler.web.m.service;

import com.mall.butler.service.BaseService;
import com.mall.butler.web.model.StaticInfo;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 系统静态信息管理
 * 
 * @author zhaoxs
 */

public interface MStaticService extends BaseService {
	/**
	 * 保存静态信息
	 * 
	 * @param info
	 */
	public void doSaveStatic(StaticInfo info, String content);

	/**
	 * 修改静态信息
	 * 只修改文本内容
	 * 
	 * @param info
	 */
	public void doUpdateStatic(StaticInfo info, String content);

	/**
	 * 查询静态信息
	 * 
	 * @param filter
	 * @return
	 */
	public Page<StaticInfo> queryStatic(PageRequest<StaticInfo> filter);

	/**
	 * 删除静态信息
	 * 
	 * @param info
	 */
	public void doDelStatic(StaticInfo info);
}
