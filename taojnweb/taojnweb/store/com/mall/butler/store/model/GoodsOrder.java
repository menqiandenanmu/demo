package com.mall.butler.store.model;

import com.mall.butler.model.BaseEntity;

public class GoodsOrder extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String BUYERID = "buyerId";
	public static final String BUYERNAME = "buyerName";
	public static final String ORDERID = "orderId";
	public static final String ORDERNO = "orderNo";
	public static final String SUBORDERNO = "subOrderNo";
	public static final String GOODSID = "goodsId";
	public static final String GOODSNAME = "goodsName";
	public static final String AMOUNT = "amount";
	public static final String PRICE = "price";
	public static final String SUM = "sum";
	public static final String ORDERSTATUS = "orderStatus";
	public static final String REMARK = "remark";

	private Long buyerId;
	private String buyerName;
	private Long orderId;
	private String orderNo;
	private Long goodsId;
	private String goodsName;
	private Integer amount;
	private Double price;
	private Double sum;
	private Integer orderStatus;
	private String remark;

	/**
	 *购买用户id
	 */
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	/**
	 *购买用户id
	 */
	public Long getBuyerId() {
		return this.buyerId;
	}

	/**
	 *购买用户
	 */
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	/**
	 *购买用户
	 */
	public String getBuyerName() {
		return this.buyerName;
	}

	/**
	 *主订单id
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 *主订单id
	 */
	public Long getOrderId() {
		return this.orderId;
	}

	/**
	 *订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 *订单号
	 */
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 *商品ID
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 *商品ID
	 */
	public Long getGoodsId() {
		return this.goodsId;
	}

	/**
	 *商品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 *商品名称
	 */
	public String getGoodsName() {
		return this.goodsName;
	}

	/**
	 *数量
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 *数量
	 */
	public Integer getAmount() {
		return this.amount;
	}

	/**
	 *票型中在线支付的价格
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 *票型中在线支付的价格
	 */
	public Double getPrice() {
		return this.price;
	}

	/**
	 *合计金额
	 */
	public void setSum(Double sum) {
		this.sum = sum;
	}

	/**
	 *合计金额
	 */
	public Double getSum() {
		return this.sum;
	}

	/**
	 *INIT(0,"新建订单"),SUCCESS(1,"订单成功"),FAIL(2,"订单失败"),CANCEL(3,"退单")
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 *INIT(0,"新建订单"),SUCCESS(1,"订单成功"),FAIL(2,"订单失败"),CANCEL(3,"退单")
	 */
	public Integer getOrderStatus() {
		return this.orderStatus;
	}

	/**
	 *备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 *备注
	 */
	public String getRemark() {
		return this.remark;
	}
}