package com.mall.butler.store.m.service._impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.store.dao.GoodsStockDao;
import com.mall.butler.store.m.service.MGoodsStockService;
import com.mall.butler.store.model.GoodsStock;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 商品库存操作
 * 
 * @author zhaoxs
 */
public class MGoodsStockServiceImpl extends BaseServiceImpl implements MGoodsStockService {
	@Autowired
	private GoodsStockDao goodsStockDao;

	@Override
	public void doDel(GoodsStock goodsStock) {
		goodsStock = goodsStockDao.getById(goodsStock.getId());
		goodsStockDao.delete(goodsStock);
	}

	@Override
	public Page<GoodsStock> findPage(PageRequest<GoodsStock> pageRequest) {
		return goodsStockDao.page(pageRequest);
	}

}
