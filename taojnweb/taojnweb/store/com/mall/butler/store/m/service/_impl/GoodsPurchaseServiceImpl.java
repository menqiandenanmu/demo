package com.mall.butler.store.m.service._impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.store.dao.GoodsPurchaseDao;
import com.mall.butler.store.dao.GoodsStockDao;
import com.mall.butler.store.m.service.GoodsPurchaseService;
import com.mall.butler.store.model.GoodsPurchase;
import com.mall.butler.store.model.GoodsStock;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * GoodsPurchase信息业务实现
 * 
 * @author zhaoxs
 */

public class GoodsPurchaseServiceImpl extends BaseServiceImpl implements GoodsPurchaseService {

	@Autowired
	private GoodsPurchaseDao goodsPurchaseDao;
	@Autowired
	private GoodsStockDao goodsStockDao;

	@Override
	public Page<GoodsPurchase> queryPage(PageRequest<Map<String, Object>> params) {
		return goodsPurchaseDao.pageQuery(params, "PAGE_M");
	}

	@Override
	public List<GoodsPurchase> getList(Map<String, Object> params) {
		return goodsPurchaseDao.queryEntitys("PAGE_M", params);
	}

	@Transactional
	@Override
	public void doSave(GoodsPurchase goodsPurchase) {
		GoodsStock goodsStock = goodsStockDao.getById(goodsPurchase.getStockId());
		if (goodsStock != null) {
			// 添加库存
			goodsStock.setNum(goodsStock.getNum() + goodsPurchase.getNum());
			goodsStock.setTotalNum(goodsStock.getTotalNum() + goodsPurchase.getNum());
			goodsStockDao.update(goodsStock);
			// 添加库存记录
			goodsPurchase.setGoodsName(goodsStock.getGoodsName());
			goodsPurchase.setId(goodsPurchaseDao.getNewId());
			goodsPurchaseDao.insert(goodsPurchase);
		}
		// 日志记录
	}

	@Override
	public Page<GoodsPurchase> purchaseCountPage(PageRequest<Map<String, Object>> request) {
		return goodsPurchaseDao.pageQuery(request, "PAGE_COUNT");
	}

	@Override
	public List<GoodsPurchase> getCountList(Map<String, Object> params) {
		return goodsPurchaseDao.queryEntitys("PAGE_COUNT", params);
	}

}