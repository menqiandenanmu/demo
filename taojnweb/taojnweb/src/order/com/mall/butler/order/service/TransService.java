package com.mall.butler.order.service;

import com.mall.butler.service.BaseService;

import com.mall.butler.account.model.TradeOrder;
import com.mall.butler.order.model.OrderInfo;
import com.mall.butler.order.model.TransInfo;
import com.mall.butler.order.model.TransLogs;

/**
 * 交易管理
 * 
 */
public interface TransService extends BaseService {

	/**
	 * 以电商支付宝接口得到交易记录 如果交易记录不存在则新建一条
	 * 
	 * @param order
	 * @return
	 */
	public TransInfo alipayTrans(OrderInfo order);

	/**
	 * 以电商支付宝接口查找交易记录
	 * 
	 * @param no
	 * @return
	 */
	public TransInfo getAlipayTransByNo(String transNo);
	
	
	
	/**
	 * 以wap支付接口查找交易记录
	 * 
	 * @param no
	 * @return
	 */
	public TransInfo getWeixinTransByNo(String transNo);

	/**
	 * 返回更新交易信息（状态等)
	 * 
	 * @param trans
	 */
	public void doUpdateTrans(TransInfo trans);

	/**
	 * 记录请求日志
	 * 
	 * @param logs
	 */
	public void doInserTransLogs(TransLogs logs);

	/**
	 * 以银联支付接口得到交易记录 如果交易记录不存在则新建一条
	 * 
	 * @param order
	 * @return
	 */
	public TransInfo netpayTrans(OrderInfo order);

	/**
	 * 账户支付接口得到交易记录 如果交易记录不存在则新建一条
	 * 
	 * @param order
	 * @return
	 */
	public TransInfo accpayTrans(OrderInfo order);

	/**
	 * 以支付宝接口查找交易记录
	 * 
	 * @param no
	 * @return
	 */
	public TransInfo getNetpayTransByNo(String transNo);

	/**
	 * 根据订单id查询支付记录
	 * 
	 * @param orderId
	 * @return
	 */
	public TransInfo getAlipayTransByOrderId(Long orderId);

	public TransInfo wxTrans(OrderInfo order);

	public TransInfo getWxTransByOrderNo(String outTradeNo);

	TransInfo weixinTrans(OrderInfo order);

	/**
	 * 获取交易号
	 * 
	 * @param gateway
	 * 
	 * @return
	 */
	public String createNo(String gateway);

	/**
	 * 插入交易记录 如果交易记录不存在则新建一条
	 * 
	 * @param order
	 * @param gateway
	 *            0:支付宝 1:银联6:微信 9:手动支付(管理员操作）
	 * @return
	 */
	public TransInfo alipayTrans(TradeOrder order, int gateway);

}
