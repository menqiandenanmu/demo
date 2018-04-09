package com.mall.butler.port.m;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.SignUtil;
import com.mall.butler.account.model.Merchant;
import com.mall.butler.account.model.TradeAccountDetail;
import com.mall.butler.account.service.TradeAccountService;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.port.xml.OrderXml;
import com.mall.butler.util.XmlHelper;
import com.mall.butler.xml.BaseXml;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.lang.DateUtil;

/**
 * 退款接口
 * 
 */
public class RefundPortAction extends ManageBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 436104812192744689L;
	private String transTime;// 时间
	private String sign;// 加密值
	private String data;// 加密值

	@Autowired
	private TradeAccountService tradeAccountService;

	public void doRefund() {
		
		// 业务数据组装
		OrderXml orderXml = null;
		try {
			System.out.println("收到请求"+data);
			orderXml = XmlHelper.toObj(OrderXml.class, data.trim());
			Merchant merchant=tradeAccountService.getEntityById(Merchant.class,ManageContext.ACCOUNT_TJN_ID);
			if(merchant.getMerchantStatus().intValue()!=1)
				throw new RuntimeException("接口已经停用");
			// 验证参数
			this.auth(merchant.getMerchantNumber());
		if (null == orderXml)
			throw new RuntimeException("订单信息不能为空");
		// 业务数据组装
		if (TxtUtil.isEmpty(orderXml.getTradeNo()))
			throw new RuntimeException("交易号不能为空请检查");
		if (null == orderXml.getRefundSum())
			throw new RuntimeException("退款金额不能为空");
		
		// 钱包扣款
		tradeAccountService.refund(orderXml);
		} catch (Exception e) {
			orderXml=new OrderXml();
			orderXml.setCode("0");
			if(null!=e.getMessage())
				orderXml.setMsg(e.getMessage());
				else {
					orderXml.setMsg("接口信息出错");
				}
			// 返回结果
			super.writeXml(XmlHelper.toXml(orderXml));
			return ;
		}
		orderXml.setCode("1");
		orderXml.setMsg("RefundSuccess");
		// 返回结果
		super.writeXml(XmlHelper.toXml(orderXml));
	}
	
	
	
	public void doCrmRefund() {
		System.out.println("crm退单收到请求======================"+data);
		// 业务数据组装
		OrderXml orderXml = null;
		try {
			orderXml = XmlHelper.toObj(OrderXml.class, data.trim());
			System.out.println("crm退单收到请求"+data);
			Merchant merchant=tradeAccountService.getEntityById(Merchant.class,ManageContext.ACCOUNT_CRM_ID);
			if(merchant.getMerchantStatus().intValue()!=1)
				throw new RuntimeException("Interface Is Disabled");
			// 验证参数
			this.auth(merchant.getMerchantNumber());
		if (null == orderXml)
			throw new RuntimeException("Order information cannot be empty");
		if (TxtUtil.isEmpty(orderXml.getOpFlag()))
			throw new RuntimeException("Transaction code cannot be empty");
		if(!"04".equals(orderXml.getOpFlag()))
			throw new RuntimeException("Transaction code error");
		// 业务数据组装
		if (TxtUtil.isEmpty(orderXml.getOrderNo()))
			throw new RuntimeException("Please check back the number can not be empty");
		if (TxtUtil.isEmpty(orderXml.getTransNo()))
			throw new RuntimeException("Original transaction number cannot be empty ");
		if (TxtUtil.isEmpty(orderXml.getPayAmount()))
			throw new RuntimeException("Original amount cannot be empty");
		if (TxtUtil.isEmpty(orderXml.getRefundAmount()))
			throw new RuntimeException("退款金额不能为空");
		String paySum=orderXml.getRefundAmount().substring(0, 10)+"."+orderXml.getRefundAmount().substring(10, 12);
		double payAmount=Double.valueOf(paySum);
		// 钱包扣款
		TradeAccountDetail trade=tradeAccountService.crmRefund(orderXml,payAmount);
		orderXml.setTradeNo(trade.getRemark1());
		orderXml.setTranDay(DateUtil.format(new Date(), "yyyymmdd"));
		orderXml.setTranTime(DateUtil.format(new Date(), "hhMMss"));
		} catch (Exception e) {
			BaseXml baseXml=new BaseXml();
			baseXml.setCode("0");
			if(null!=e.getMessage())
				baseXml.setMsg("Wrong order information can not complete the payment");
				else {
					baseXml.setMsg("Interface error");
				}
			// 返回结果
			//重新组装xml
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
			sb.append("<response>");
			sb.append("<code>"+baseXml.getCode()+"</code>");
			sb.append("<msg>"+baseXml.getMsg()+"</msg>");
			sb.append("</response>");
			super.writeXml(sb.toString());
			return ;
		}
		orderXml.setCode("00");
		orderXml.setPayType("01");
		orderXml.setMsg("RefundSuccess");
		// 返回结果
		//重新组装xml
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		sb.append("<response>");
		sb.append("<code>"+orderXml.getCode()+"</code>");
		sb.append("<msg>"+orderXml.getMsg()+"</msg>");
		sb.append("<transTime>"+DateUtil.format(new Date(),ManageContext.TIME_FORMAT)+"</transTime>");
		sb.append("<order>");
		sb.append("<opFlag>"+orderXml.getOpFlag()+"</opFlag>");
		sb.append("<orderNo>"+orderXml.getOrderNo()+"</orderNo>");
		sb.append("<payAmount>"+orderXml.getPayAmount()+"</payAmount>");
		sb.append("<payType>"+orderXml.getPayType()+"</payType>");
		sb.append("<refundAmount>"+orderXml.getRefundAmount()+"</refundAmount>");
		sb.append("<transNo>"+orderXml.getTradeNo()+"</transNo>");
		sb.append("<tranDay>"+orderXml.getTranDay()+"</tranDay>");
		sb.append("<tranTime>"+orderXml.getTranTime()+"</tranTime>");
		sb.append("</order>");
		sb.append("</response>");
		super.writeXml(sb.toString());
	}
	/**
	 * 验证请求信息
	 */
	private void auth(String parent) {
		// 请求时间验证客户端时间和服务器时间误差不能超过一个小时
		if (null == transTime)
			throw new RuntimeException("transTime不能为空");
		if (null == data)
			throw new RuntimeException("data不能为空");
		if (null == sign)
			throw new RuntimeException("sign不能为空");
		try {
			Long mtime = new Date().getTime()- DateUtil.parse(transTime, ManageContext.TIME_FORMAT)	.getTime();
			if (Math.abs(mtime) > 1000 * 60 * 60 * 1)
				throw new RuntimeException("请求时间同服务器时间不符!");
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("请求时间格式和服务器时间不符!");
		}

		// 数据验证
		if (!SignUtil.makeSign(data, transTime,parent).equals(this.sign))
			throw new RuntimeException("数据验证失败!");
	}

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
