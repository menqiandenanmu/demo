package com.mall.butler.order.m.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.store.m.service.MGoodsInfoService;
import com.mall.butler.store.model.GoodsInfo;

/**
 * 类描述：商品下单
 * 类名称：OrderNewAction
 * 创建人：caedmon
 * 创建时间：2013-5-18 下午09:32:42
 * 修改人：caedmon
 * 修改时间：2013-5-18 下午09:32:42
 * 修改备注：
 * 
 * @version
 */
public class OrderNewAction extends ManageBaseAction {
	private static final long serialVersionUID = 2727415911932064856L;
	@Autowired
	private MGoodsInfoService mGoodsInfoService;
	private GoodsInfo goodsInfo;
	private List<GoodsInfo> goodsList;
	private Long[] goodsIds;
	private Integer[] amounts;
	private Long storeInfoId;

	/**
	 * 下单界面
	 */
	public String execute() {
		if (goodsInfo == null) {
			goodsInfo = new GoodsInfo();
		}
		GoodsInfo param = new GoodsInfo();
		param.setDeleted(false);
		param.setUseFlag(true);
		goodsList = mGoodsInfoService.findList(param);
		return SUCCESS;
	}

	// 后台去手工添加订单
	public String add() {
		if (goodsIds == null || goodsIds.length <= 0) {
			throw new RuntimeException("无效的商品信息!");
		}
		goodsList = new ArrayList<GoodsInfo>();
		for (int i = 0; i < goodsIds.length; i++) {
			goodsInfo = mGoodsInfoService.getEntityById(GoodsInfo.class, goodsIds[i]);
			if (goodsInfo == null || goodsInfo.getDeleted())
				throw new RuntimeException("无效的商品信息!");
			// 判断库存
			goodsList.add(goodsInfo);
		}
		return ADD;
	}

	// 后台手工添加订单保存
	public String save() {
		msgInfo.addMessage("新增订单成功!");
		return JDIALOG;
	}

	public GoodsInfo getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public List<GoodsInfo> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<GoodsInfo> goodsList) {
		this.goodsList = goodsList;
	}

	public Integer[] getAmounts() {
		return amounts;
	}

	public void setAmounts(Integer[] amounts) {
		this.amounts = amounts;
	}

	public Long[] getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(Long[] goodsIds) {
		this.goodsIds = goodsIds;
	}

	public Long getStoreInfoId() {
		return storeInfoId;
	}

	public void setStoreInfoId(Long storeInfoId) {
		this.storeInfoId = storeInfoId;
	}
}
