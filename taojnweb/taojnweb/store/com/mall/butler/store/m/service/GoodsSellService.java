package com.mall.butler.store.m.service;

import java.util.List;
import java.util.Map;

import com.mall.butler.service.BaseService;
import com.mall.butler.store.model.GoodsSell;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * GoodsSell管理
 * 
 * @author zhaoxs
 */
public interface GoodsSellService extends BaseService {
	/**
	 * 分页查询goodsSell信息
	 * 
	 * @param request
	 * @return
	 */
	public Page<GoodsSell> queryPage(PageRequest<Map<String, Object>> request);

	/**
	 * 保存goodsSell信息
	 * 
	 * @param goodsSell
	 */
	public void doSave(GoodsSell goodsSell);

	/**
	 * 获取集合
	 * 
	 * @Title:
	 * @auth：caedmon
	 * @date 2013-5-28 下午10:29:57
	 * @return
	 */
	public List<GoodsSell> getList(Map<String, Object> params);

	/**
	 * 统计分页
	 * 
	 * @Title:
	 * @auth：caedmon
	 * @date 2013-5-28 下午10:52:02
	 * @return
	 */
	public Page<GoodsSell> CountPage(PageRequest<Map<String, Object>> filter);

	/**
	 * 统计集合
	 * 
	 * @Title:
	 * @auth：caedmon
	 * @date 2013-5-28 下午10:52:10
	 * @return
	 */
	public List<GoodsSell> getSellCount(Map<String, Object> params);

}
