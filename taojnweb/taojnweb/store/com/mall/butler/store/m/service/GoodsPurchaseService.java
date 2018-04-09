package com.mall.butler.store.m.service;

import java.util.List;
import java.util.Map;

import com.mall.butler.service.BaseService;
import com.mall.butler.store.model.GoodsPurchase;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * GoodsPurchase管理
 * 
 * @author zhaoxs
 */
public interface GoodsPurchaseService extends BaseService {
	/**
	 * 分页查询goodsPurchase信息
	 * 
	 * @param request
	 * @return
	 */
	public Page<GoodsPurchase> queryPage(PageRequest<Map<String, Object>> request);

	/**
	 * 保存goodsPurchase信息
	 * 
	 * @param goodsPurchase
	 */
	public void doSave(GoodsPurchase goodsPurchase);

	/**
	 * 获取集合
	 * 
	 * @Title:
	 * @auth：caedmon
	 * @date 2013-5-28 下午10:11:31
	 * @return
	 */
	public List<GoodsPurchase> getList(Map<String, Object> params);

	/**
	 * 统计分页
	 * 
	 * @Title:
	 * @auth：caedmon
	 * @date 2013-5-28 下午10:42:54
	 * @return
	 */
	public Page<GoodsPurchase> purchaseCountPage(PageRequest<Map<String, Object>> request);

	/**
	 * 统计集合
	 * 
	 * @Title:
	 * @auth：caedmon
	 * @date 2013-5-28 下午10:42:39
	 * @return
	 */
	public List<GoodsPurchase> getCountList(Map<String, Object> params);

}
