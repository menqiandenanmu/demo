package com.mall.butler.order.m.action;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.order.m.service.MOrderService;
import com.mall.butler.order.model.OrderInfo;
import com.mall.butler.store.m.service.MGoodsOrderService;
import com.mall.butler.store.model.GoodsOrder;
import com.mall.butler.store.service.GoodsOrderService;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 类描述：商品订单管理
 * 类名称：OrderAction
 * 创建人：caedmon
 * 创建时间：2013-5-18 下午11:32:48
 * 修改人：caedmon
 * 修改时间：2013-5-18 下午11:32:48
 * 修改备注：
 * 
 * @version
 */
public class OrderAction extends ManageBaseAction {
	private static final long serialVersionUID = 344242052827702620L;
	private static final String FILL_PAYINFO = "fillPayinfo";
	@Autowired
	private MGoodsOrderService mGoodsOrderService;
	@Autowired
	private GoodsOrderService goodsOrderService;
	@Autowired
	private MOrderService mOrderService;

	private Page<GoodsOrder> orderItemPage;
	private Page<OrderInfo> orderInfoPage;
	private GoodsOrder goodsOrder;
	private OrderInfo orderInfo;
	private Integer notifyType;
	private List<GoodsOrder> goList;
	private Date begCreateDate;
	private Date endCreateDate;

	// 订单订单列表
	@SuppressWarnings("unchecked")
	public String execute() {
		PageRequest<Map> filter = super.newPage(Map.class);
		Map<String, Object> params = new Hashtable<String, Object>();
		if (orderInfo == null) {
			orderInfo = new OrderInfo();
			Date date = new Date();
			begCreateDate = DateUtil.getMonthFirstDay(date);
			endCreateDate = DateUtil.getMonthLastDay(date);
		}
		if (!TxtUtil.isEmpty(orderInfo.getOrderNo()))
			params.put(OrderInfo.ORDERNO, orderInfo.getOrderNo().trim());
		if (orderInfo.getOrderStatus() != null)
			params.put(OrderInfo.ORDERSTATUS, orderInfo.getOrderStatus());
		if (orderInfo.getPayStatus() != null)
			params.put(OrderInfo.PAYSTATUS, orderInfo.getPayStatus());
		if (orderInfo.getClosed() != null)
			params.put(OrderInfo.CLOSED, orderInfo.getClosed());
		if (!TxtUtil.isEmpty(orderInfo.getBuyerName()))
			params.put(OrderInfo.BUYERNAME, orderInfo.getBuyerName());
		if (!TxtUtil.isEmpty(orderInfo.getLinkName()))
			params.put(OrderInfo.LINKNAME, orderInfo.getLinkName());
		if (!TxtUtil.isEmpty(orderInfo.getLinkMobile()))
			params.put(OrderInfo.LINKMOBILE, orderInfo.getLinkMobile());
		if (!TxtUtil.isEmpty(orderInfo.getLinkIdcard()))
			params.put(OrderInfo.LINKIDCARD, orderInfo.getLinkIdcard());
		if (begCreateDate != null)
			params.put("begCreateDate", begCreateDate);
		if (endCreateDate != null)
			params.put("endCreateDate", endCreateDate);
		filter.setFilters(params);
		orderInfoPage = mOrderService.queryOrderByMap(filter);
		return LIST;
	}

	// 订单详情
	public String info() {
		orderInfo = mGoodsOrderService.getEntityById(OrderInfo.class, id);
		if (orderInfo == null || orderInfo.getDeleted())
			throw new RuntimeException("订单信息不存在");
		goList = mGoodsOrderService.queryGoodsOrder(orderInfo);
		return INFO;
	}

	public String edit() {
		orderInfo = mOrderService.getEntityById(OrderInfo.class, id);
		if (orderInfo == null || orderInfo.getDeleted())
			throw new RuntimeException("订单信息不存在");
		return EDIT;
	}

	public String update() {
		orderInfo.setId(id);
		goodsOrderService.doUpdateOrder(orderInfo);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("操作成功!");
		return JDIALOG;
	}

	public String cancel() {
		orderInfo = mOrderService.getEntityById(OrderInfo.class, id);
		if (orderInfo == null)
			throw new RuntimeException("订单信息不存在");
		goodsOrderService.doCancelOrder(orderInfo);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("操作成功!");
		return JDIALOG;
	}

	/**
	 * 填定手工订单支付信息
	 * 
	 * @return
	 */
	public String pay() {
		orderInfo = mOrderService.getEntityById(OrderInfo.class, id);
		if (orderInfo == null || orderInfo.getDeleted())
			throw new RuntimeException("订单信息不存在");
		return FILL_PAYINFO;
	}

	/**
	 * 订单支付操作
	 * 
	 * @return
	 */
	public String payed() {
		orderInfo = mOrderService.getEntityById(OrderInfo.class, id);
		if (orderInfo == null || orderInfo.getDeleted())
			throw new RuntimeException("订单信息不存在");
		mGoodsOrderService.doManualPayOrder(orderInfo, orderInfo.getRemark(), this.getLogin());
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("操作成功!");
		return JDIALOG;
	}

	public Page<OrderInfo> getOrderInfoPage() {
		return orderInfoPage;
	}

	public void setOrderInfoPage(Page<OrderInfo> orderInfoPage) {
		this.orderInfoPage = orderInfoPage;
	}

	public GoodsOrder getGoodsOrder() {
		return goodsOrder;
	}

	public void setGoodsOrder(GoodsOrder goodsOrder) {
		this.goodsOrder = goodsOrder;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public Page<GoodsOrder> getOrderItemPage() {
		return orderItemPage;
	}

	public void setOrderItemPage(Page<GoodsOrder> orderItemPage) {
		this.orderItemPage = orderItemPage;
	}

	public Integer getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(Integer notifyType) {
		this.notifyType = notifyType;
	}

	public List<GoodsOrder> getGoList() {
		return goList;
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
