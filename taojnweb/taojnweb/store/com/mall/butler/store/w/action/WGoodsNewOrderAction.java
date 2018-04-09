package com.mall.butler.store.w.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.action.w.WebBaseAction;
import com.mall.butler.order.model.OrderInfo;
import com.mall.butler.store.model.GoodsInfo;
import com.mall.butler.store.model.GoodsOrder;
import com.mall.butler.store.w.service.WGoodsInfoService;
import com.mall.util.common.TxtUtil;

/**
 * 商品订单
 * 
 * @author caedmon
 * @version 创建时间：2013-3-4 下午04:42:28
 */
public class WGoodsNewOrderAction extends WebBaseAction {

	private static final long serialVersionUID = 1L;

	private OrderInfo order;
	/*** 订单ID **/
	private Long orderId;
	/**** 传过来的票型ID **/
	private Long[] goodsIds;
	/**** 传过来的购买数量 **/
	private Integer[] amounts;
	/**** 传过来的后手机号码 **/
	private String mobile;
	/**** 确认手机号码 **/
	private String mobile1;
	/**** 联系人姓名 ****/
	private String linkName;
	/**** 确认身份证号码 ***/
	private String tempIdCard;
	/**** 身份证号码 ****/
	private String idCard;
	@Autowired
	private WGoodsInfoService wGoodsInfoService;
	private List<GoodsInfo> goodsinfos;

	public String execute() {
		// 商品集合
		goodsinfos = wGoodsInfoService.queryBookGood(super.getId(), 1);
		return SUCCESS;
	}

	public String saveOrder() {
		if (order == null) {
			order = new OrderInfo();
		}
		// 票型信息
		if (goodsIds == null || goodsIds.length <= 0) {
			throw new RuntimeException("无效的票型信息!");
		} else {
			List<GoodsOrder> OrderL = new ArrayList<GoodsOrder>();
			Integer amount = 0;
			for (int i = 0; i < goodsIds.length; i++) {
				GoodsOrder pOrder = new GoodsOrder();
				pOrder.setGoodsId(goodsIds[i]);
				pOrder.setAmount(amounts[i]);
				OrderL.add(pOrder);
				amount = amount + pOrder.getAmount();
			}
			if (amount == 0) {
				throw new RuntimeException("请选择数量!");
			}

			order = new OrderInfo();
			if (TxtUtil.isEmpty(linkName))
				throw new RuntimeException("无效的联系人!");
			if (TxtUtil.isEmpty(mobile) || !TxtUtil.isMobile(mobile))
				throw new RuntimeException("无效的联系人手机号!");
			if (TxtUtil.isEmpty(mobile1) || !TxtUtil.isMobile(mobile1))
				throw new RuntimeException("请输入确认手机号码!");
			if (!mobile.equals(mobile1)) {
				throw new RuntimeException("输入的手机号码不一致!");
			}
			if (TxtUtil.isEmpty(idCard) || !TxtUtil.isIDNumber(idCard))
				throw new RuntimeException("无效的身份证号码!");
			if (TxtUtil.isEmpty(tempIdCard) || !TxtUtil.isIDNumber(tempIdCard))
				throw new RuntimeException("请输入确认身份证号码!");
			if (!idCard.equals(tempIdCard)) {
				throw new RuntimeException("输入的身份证号码不一致!");
			}
			String remark = order.getRemark() == null ? "" : order.getRemark();
			order.setBuyerId(this.getAccount().getId());
			order.setBuyerName(this.getAccount().getAccName());
			order.setLinkName(linkName == null ? (this.getLogin().getRealname() == null ? this
					.getLogin().getLoginName() : this.getLogin().getLoginName()) : linkName);
			order.setLinkIdcard(this.getLogin().getIdCard());
			order.setLinkMobile(mobile);
			order.setLoginId(this.getLogin().getId());
			order.setLoginName(this.getLogin().getLoginName());
			order.setBuyerAccType(1);
			order.setRemark(remark);
			// 订单保存
			order = wGoodsInfoService.doAddOrder(order, OrderL);
		}
		return SUCCESS;
	}

	/**
	 * 下单成功 ，展示订单的详细
	 * 
	 * @return
	 */
	public String fillOrder() {
		order = this.wGoodsInfoService.getEntityById(OrderInfo.class, orderId);
		return SUCCESS;
	}

	public OrderInfo getOrder() {
		return order;
	}

	public void setOrder(OrderInfo order) {
		this.order = order;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long[] getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(Long[] goodsIds) {
		this.goodsIds = goodsIds;
	}

	public Integer[] getAmounts() {
		return amounts;
	}

	public void setAmounts(Integer[] amounts) {
		this.amounts = amounts;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public List<GoodsInfo> getGoodsinfos() {
		return goodsinfos;
	}

	public void setGoodsinfos(List<GoodsInfo> goodsinfos) {
		this.goodsinfos = goodsinfos;
	}

	public String getTempIdCard() {
		return tempIdCard;
	}

	public void setTempIdCard(String tempIdCard) {
		this.tempIdCard = tempIdCard;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

}
