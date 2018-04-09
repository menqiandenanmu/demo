package com.mall.butler.store.m.service;

import java.util.List;

import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.service.BaseService;
import com.mall.butler.store.model.GoodsInfo;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 商品业务
 * 
 * @author zhaoxs
 */
public interface MGoodsInfoService extends BaseService {
	/**
	 * 保存商品
	 * 
	 * @param obj
	 * @param fileName
	 * @param loginer
	 */
	public void doSave(GoodsInfo obj, String fileName, AccountLogin loginer);

	/**
	 * 删 除商品
	 * 
	 * @param obj
	 */
	public void doDel(GoodsInfo obj);

	/**
	 * 更新商品
	 * 
	 * @param obj
	 * @param titleImg
	 * @param loginer
	 */
	public void doUpdate(GoodsInfo obj, String titleImg, AccountLogin loginer);

	/**
	 * 查找商品
	 * 
	 * @param obj
	 * @return
	 */
	public List<GoodsInfo> findList(GoodsInfo obj);

	/**
	 * 分页查找商品
	 * 
	 * @param pageRequest
	 * @return
	 */
	public Page<GoodsInfo> findPage(PageRequest<GoodsInfo> pageRequest);

	/**
	 * 商品下架
	 * 
	 * @param goods
	 */
	public void doDisableSell(GoodsInfo goods);

	/**
	 * 商品上架
	 * 
	 * @param goods
	 */
	public void doEnabledSell(GoodsInfo goods);
}
