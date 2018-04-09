package com.mall.butler.store.m.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.MAccountService;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.store.m.service.MGoodsStockService;
import com.mall.butler.store.model.GoodsInfo;
import com.mall.butler.store.model.GoodsStock;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 类描述：商店库存管理
 * 类名称：GoodsStockAction
 * 创建人：caedmon
 * 创建时间：2013-5-18 下午10:30:38
 * 修改人：caedmon
 * 修改时间：2013-5-18 下午10:30:38
 * 修改备注：
 * 
 * @version
 */
public class GoodsStockAction extends ManageBaseAction {
	private static final long serialVersionUID = -2264144940815483007L;
	@Autowired
	private MGoodsStockService mGoodsStockService;
	@Autowired
	private MAccountService mAccountService;

	private Page<GoodsStock> resultPage;
	private GoodsStock goodsStock;
	private GoodsInfo goodsInfo;

	// 查询
	public String execute() {
		PageRequest<GoodsStock> pageRequest = super.newPage(GoodsStock.class);
		pageRequest.setPageNumber(currPage);
		if (this.goodsStock == null)
			goodsStock = new GoodsStock();
		goodsStock.setGoodsId(id);
		pageRequest.setFilters(goodsStock);
		this.resultPage = mGoodsStockService.findPage(pageRequest);
		return SUCCESS;
	}

	// 删除保存
	public String del() {
		goodsStock = mGoodsStockService.getEntityById(GoodsStock.class, id);
		if (goodsStock == null || goodsStock.getDeleted())
			throw new RuntimeException("商品库存信息不存在或已被删除");
		mGoodsStockService.doDel(goodsStock);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("删除商品成功!");
		return JDIALOG;
	}

	public Page<GoodsStock> getResultPage() {
		return resultPage;
	}

	public void setResultPage(Page<GoodsStock> resultPage) {
		this.resultPage = resultPage;
	}

	public GoodsStock getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(GoodsStock goodsStock) {
		this.goodsStock = goodsStock;
	}

	public GoodsInfo getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public MAccountService getmAccountService() {
		return mAccountService;
	}

	public void setmAccountService(MAccountService mAccountService) {
		this.mAccountService = mAccountService;
	}
}
