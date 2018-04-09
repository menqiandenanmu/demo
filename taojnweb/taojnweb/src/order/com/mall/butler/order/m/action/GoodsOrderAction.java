package com.mall.butler.order.m.action;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.store.m.service.MGoodsOrderService;
import com.mall.butler.store.model.GoodsOrder;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 类描述：商品订单页面
 * 类名称：GoodsOrderAction
 * 创建人：caedmon
 * 创建时间：2013-5-18 下午11:32:35
 * 修改人：caedmon
 * 修改时间：2013-5-18 下午11:32:35
 * 修改备注：
 * 
 * @version
 */
public class GoodsOrderAction extends ManageBaseAction {
	private static final long serialVersionUID = 3234108219082325235L;
	@Autowired
	private MGoodsOrderService mGoodsOrderService;

	// 商品订单
	private GoodsOrder to;
	private Page<GoodsOrder> pages;
	private Date begCreateDate;
	private Date endCreateDate;

	/**
	 * 商品订单列表
	 */
	@SuppressWarnings("unchecked")
	public String execute() {
		PageRequest<Map> pageRequest = this.newPage(Map.class);
		Map<String, Object> filter = new Hashtable<String, Object>();
		pageRequest.setPageNumber(currPage);
		if (to == null) {
			to = new GoodsOrder();
			to.setOrderStatus(1);
			Date date = new Date();
			begCreateDate = DateUtil.getMonthFirstDay(date);
			endCreateDate = DateUtil.getMonthLastDay(date);
		}
		if (begCreateDate != null)
			filter.put("begCreateDate", begCreateDate);
		if (endCreateDate != null)
			filter.put("endCreateDate", endCreateDate);
		if (!TxtUtil.isEmpty(to.getOrderNo())) {
			filter.put(GoodsOrder.ORDERNO, to.getOrderNo().trim());
		}
		if (!TxtUtil.isEmpty(to.getGoodsName())) {
			filter.put(GoodsOrder.GOODSNAME, to.getGoodsName().trim());
		}
		if (to.getOrderStatus() != null)
			filter.put(GoodsOrder.ORDERSTATUS, to.getOrderStatus());
		pageRequest.setFilters(filter);
		pages = mGoodsOrderService.queryGoodsOrder(pageRequest);
		return LIST;
	}

	public Page<GoodsOrder> getPages() {
		return pages;
	}

	public void setPages(Page<GoodsOrder> pages) {
		this.pages = pages;
	}

	public GoodsOrder getTo() {
		return to;
	}

	public void setTo(GoodsOrder to) {
		this.to = to;
	}

	public Date getBegCreateDate() {
		return begCreateDate;
	}

	public void setBegCreateDate(Date begCreateDate) {
		this.begCreateDate = begCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

}
