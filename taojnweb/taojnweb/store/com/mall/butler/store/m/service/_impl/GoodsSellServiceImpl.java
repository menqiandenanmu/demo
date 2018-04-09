package com.mall.butler.store.m.service._impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.store.dao.GoodsSellDao;
import com.mall.butler.store.dao.GoodsStockDao;
import com.mall.butler.store.m.service.GoodsSellService;
import com.mall.butler.store.model.GoodsSell;
import com.mall.butler.store.model.GoodsStock;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * GoodsSell信息业务实现
 * 
 * @author zhaoxs
 */

public class GoodsSellServiceImpl extends BaseServiceImpl implements GoodsSellService {

	@Autowired
	private GoodsSellDao goodsSellDao;
	@Autowired
	private GoodsStockDao goodsStockDao;

	@Transactional
	@Override
	public void doSave(GoodsSell goodsSell) {
		GoodsStock goodsStock = goodsStockDao.getById(goodsSell.getStockId());
		if (goodsStock != null) {
			// 出库
			goodsStock.setNum(goodsStock.getNum() - goodsSell.getNum());
			goodsStock.setGoodsName(goodsStock.getGoodsName());
			goodsStockDao.update(goodsStock);
			// 添加出库记录
			goodsSell.setId(goodsSellDao.getNewId());
			goodsSellDao.insert(goodsSell);
			// 日志记录
		}
	}

	@Override
	public List<GoodsSell> getList(Map<String, Object> params) {
		return goodsSellDao.queryEntitys("PAGE_M", params);
	}

	@Override
	public Page<GoodsSell> queryPage(PageRequest<Map<String, Object>> request) {
		return goodsSellDao.pageQuery(request, "PAGE_M");
	}

	@Override
	public Page<GoodsSell> CountPage(PageRequest<Map<String, Object>> filter) {
		return goodsSellDao.pageQuery(filter, "PAGE_COUNT");
	}

	@Override
	public List<GoodsSell> getSellCount(Map<String, Object> filter) {

		return goodsSellDao.queryEntitys("PAGE_COUNT", filter);
	}

}