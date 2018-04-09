package com.mall.butler.port.m;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.SignUtil;
import com.mall.butler.account.model.Merchant;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.service.TradeAccountService;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.port.xml.OrderXml;
import com.mall.butler.port.xml.TradeAccountXml;
import com.mall.butler.util.XmlHelper;
import com.mall.butler.xml.BaseXml;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.lang.DateUtil;

/**
 * 支付接口
 * 
 */
public class PayPortAction extends ManageBaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -605672084569594907L;

	private String transTime;// 时间
	private String sign;// 加密值
	private String data;// 加密值

	@Autowired
	private TradeAccountService tradeAccountService;

	/**
	 * 账户绑定
	 */
	public void doBindUser() {
		
		// 获取请求参数
		TradeAccountXml tradeXml = null;
		TradeAccount tradeAccount =null;
		try {
			System.out.println("收到请求"+data);
			tradeXml = XmlHelper.toObj(TradeAccountXml.class, data.trim());
			Merchant merchant=tradeAccountService.getEntityById(Merchant.class,ManageContext.ACCOUNT_TJN_ID);
			if(merchant.getMerchantStatus().intValue()!=1)
				throw new RuntimeException("接口已经停用");
			// 验证参数
			this.auth(merchant.getMerchantNumber());
		if (null == tradeXml)

			throw new RuntimeException("用户信息不能为空");
		// 业务数据组装
		if (TxtUtil.isEmpty(tradeXml.getAccountCode()))
			throw new RuntimeException("钱包账户不能为空");
		if (TxtUtil.isEmpty(tradeXml.getPassword()))
			throw new RuntimeException("钱包账户密码不能为空");
		if (TxtUtil.isEmpty(tradeXml.getMallCode()))
			throw new RuntimeException("商城用户编号不能为空");
		// 用户绑定
		// 判断密码是否正确
		tradeAccountService.bindTrade(tradeXml);
		tradeAccount= tradeAccountService.queryTradeByLoginName(tradeXml.getAccountCode().trim());
		if (null == tradeAccount)
			throw new RuntimeException("钱包没有找到");
		} catch (Exception e) {
			tradeXml=new TradeAccountXml();
			tradeXml.setCode("0");
			if(null!=e.getMessage())
				tradeXml.setMsg(e.getMessage());
				else {
					tradeXml.setMsg("系统信息出错");
				}
			// 返回结果
			super.writeXml(XmlHelper.toXml(tradeXml));
			return ;
		}
		tradeXml.setAmount(tradeAccount.getCurLeftValue());
		tradeXml.setStatus(tradeAccount.getStatus());
		tradeXml.setTradeName(tradeAccount.getTradeAccName());
		tradeXml.setTradeNo(tradeAccount.getTradeType());
		tradeXml.setCode("1");
		tradeXml.setMsg("绑定成功");
		// 返回结果
		super.writeXml(XmlHelper.toXml(tradeXml));
	}
	
	/**
	 * 账户解除绑定
	 */
	public void doUnBindUser() {
		
		// 获取请求参数
		TradeAccountXml tradeXml = null;
		try {
			System.out.println("收到请求"+data);
			tradeXml = XmlHelper.toObj(TradeAccountXml.class, data.trim());
			Merchant merchant=tradeAccountService.getEntityById(Merchant.class,ManageContext.ACCOUNT_TJN_ID);
			if(merchant.getMerchantStatus().intValue()!=1)
				throw new RuntimeException("接口已停用");
			// 验证参数
			this.auth(merchant.getMerchantNumber());
		if (null == tradeXml)

			throw new RuntimeException("用户信息不能为空");
		// 业务数据组装
		if (TxtUtil.isEmpty(tradeXml.getAccountCode()))
			throw new RuntimeException("钱包账户不能为空");
		if (TxtUtil.isEmpty(tradeXml.getPassword()))
			throw new RuntimeException("钱包账户密码不能为空");
		if (TxtUtil.isEmpty(tradeXml.getMallCode()))
			throw new RuntimeException("商城用户编号不能为空");
		// 用户绑定
		// 判断密码是否正确
		tradeAccountService.unBindTrade(tradeXml);
		} catch (Exception e) {
			tradeXml=new TradeAccountXml();
			tradeXml.setCode("0");
			if(null!=e.getMessage())
				tradeXml.setMsg(e.getMessage());
				else {
					tradeXml.setMsg("出错了");
				}
			// 返回结果
			super.writeXml(XmlHelper.toXml(tradeXml));
			return ;
		}
		tradeXml.setCode("1");
		tradeXml.setMsg("解除绑定成功");
		// 返回结果
		super.writeXml(XmlHelper.toXml(tradeXml));
	}

	/**
	 * 订单支付
	 */
	public void doPay() {
		
		// 业务数据组装
		OrderXml orderXml = null;
		try {
			System.out.println("收到请求"+data);
			orderXml = XmlHelper.toObj(OrderXml.class, data.trim());
			Merchant merchant=tradeAccountService.getEntityById(Merchant.class,ManageContext.ACCOUNT_TJN_ID);
			if(merchant.getMerchantStatus().intValue()!=1)
				throw new RuntimeException("接口已停用");
			// 验证参数
			this.auth(merchant.getMerchantNumber());
		if (null == orderXml)
			throw new RuntimeException("订单信息不能为空");
		// 业务数据组装
		if (TxtUtil.isEmpty(orderXml.getOrderNo()))
			throw new RuntimeException("订单号不能为空");
		if (null == orderXml.getPaySum())
			throw new RuntimeException("支付金额不能为空");
		if (TxtUtil.isEmpty(orderXml.getAccountCode()))
			throw new RuntimeException("用户名不能为空");
		if (TxtUtil.isEmpty(orderXml.getTradeNo()))
			throw new RuntimeException("交易号不能为空");
		if (TxtUtil.isEmpty(orderXml.getPassword()))
			throw new RuntimeException("钱包账户密码不能为空");
		if (TxtUtil.isEmpty(orderXml.getOrderInfo()))
			throw new RuntimeException("订单内容不能为空");
		if (TxtUtil.isEmpty(orderXml.getPayOverTime()))
			throw new RuntimeException("支付过期时间不能为空");
		// 钱包扣款
		tradeAccountService.payed(orderXml);
		} catch (Exception e) {
			orderXml=new OrderXml();
			orderXml.setCode("0");
			if(null!=e.getMessage())
				orderXml.setMsg(e.getMessage());
				else {
					orderXml.setMsg("系统信息出错");
				}
			// 返回结果
			super.writeXml(XmlHelper.toXml(orderXml));
			return ;
		}
		orderXml.setCode("1");
		orderXml.setMsg("successPayment");
		// 返回结果
		super.writeXml(XmlHelper.toXml(orderXml));
	}
	/**
	 * crm订单支付
	 */
	public void doCrmPay() {
		System.out.println("crm支付收到数据======================"+data.trim());
		// 业务数据组装
		OrderXml orderXml = null;
		String tranNo="";
		try {
			orderXml = XmlHelper.toObj(OrderXml.class, data.trim());
			System.out.println("crm收到请求"+data);
			System.out.println("crm收到用户======================"+orderXml.getOrderNo());
			Merchant merchant=tradeAccountService.getEntityById(Merchant.class,ManageContext.ACCOUNT_CRM_ID);
			if(merchant.getMerchantStatus().intValue()!=1)
				throw new RuntimeException("Interface Is Disabled");
			// 验证参数
			this.auth(merchant.getMerchantNumber());
			if (null == orderXml)
				
				throw new RuntimeException("Order information cannot be empty");
			// 业务数据组装
			if (TxtUtil.isEmpty(orderXml.getOrderNo()))
				throw new RuntimeException("Merchant order number cannot be empty");
			if (TxtUtil.isEmpty(orderXml.getOpFlag()))
				throw new RuntimeException("Transaction code cannot be empty");
			if(!"01".equals(orderXml.getOpFlag()))
				throw new RuntimeException("Transaction code error");
			if (TxtUtil.isEmpty(orderXml.getStoreCode()))
				throw new RuntimeException("Store number can not be empty");
			if (TxtUtil.isEmpty(orderXml.getOperateCode()))
				throw new RuntimeException("Operator number cannot be empty");
			if (TxtUtil.isEmpty(orderXml.getPosCode()))
				throw new RuntimeException("POS number cannot be empty");
			if (TxtUtil.isEmpty(orderXml.getOrderInfo()))
				throw new RuntimeException("Order content cannot be empty");
			if (TxtUtil.isEmpty(orderXml.getPayAmount()))
				throw new RuntimeException("Payment amount cannot be empty");
			String paySum=orderXml.getPayAmount().substring(0, 10)+"."+orderXml.getPayAmount().substring(10, 12);
			double payAmount=Double.valueOf(paySum);
			if (TxtUtil.isEmpty(orderXml.getPassword()))
				throw new RuntimeException("Dynamic code cannot be empty");
			// 钱包扣款
			 tranNo=tradeAccountService.crmPayed(orderXml,payAmount);
			 System.out.println("支付成功返回交易号======================"+tranNo);
		} catch (Exception e) {
			System.out.println("出错了======================"+e.getMessage());
			BaseXml baseXml=new BaseXml();
			baseXml.setCode("0");
			if(null!=e.getMessage())
				baseXml.setMsg("Wrong order information can not complete the payment");
			else {
				baseXml.setMsg("System error");
			}
			//重新组装xml
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
			sb.append("<response>");
			sb.append("<code>"+baseXml.getCode()+"</code>");
			sb.append("<msg>"+baseXml.getMsg()+"</msg>");
			sb.append("</response>");
			System.out.println("出错了======================返回"+sb.toString());
			super.writeXml(sb.toString());
			return ;
		}
		orderXml.setTradeNo(tranNo);
		orderXml.setTranDay(DateUtil.format(new Date(), "yyyymmdd"));
		orderXml.setTranTime(DateUtil.format(new Date(), "hhMMss"));
		orderXml.setPayType("01");
		orderXml.setCode("00");
		orderXml.setMsg("successPayment");
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
		sb.append("<operateCode>"+orderXml.getOperateCode()+"</operateCode>");
		sb.append("<orderInfo>"+orderXml.getOrderInfo()+"</orderInfo>");
		sb.append("<orderNo>"+orderXml.getOrderNo()+"</orderNo>");
		sb.append("<payAmount>"+orderXml.getPayAmount()+"</payAmount>");
		sb.append("<payType>"+orderXml.getPayType()+"</payType>");
		sb.append("<posCode>"+orderXml.getPosCode()+"</posCode>");
		sb.append("<storeCode>"+orderXml.getStoreCode()+"</storeCode>");
		sb.append("<transNo>"+orderXml.getTradeNo()+"</transNo>");
		sb.append("<tranDay>"+orderXml.getTranDay()+"</tranDay>");
		sb.append("<tranTime>"+orderXml.getTranTime()+"</tranTime>");
		sb.append("</order>");
		sb.append("</response>");
		System.out.println("支付成功==========================="+sb.toString());
		super.writeXml(sb.toString());
	}

	/**
	 * 验证请求信息
	 */
	private void auth(String parent) {
		// 请求时间验证客户端时间和服务器时间误差不能超过一个小时
		try {
			if (null == transTime)
				throw new RuntimeException("transTime不能为空");
			if (null == data)
				throw new RuntimeException("data不能为空");
			if (null == sign)
				throw new RuntimeException("sign不能为空");
			Long mtime = new Date().getTime()
					- DateUtil.parse(transTime, ManageContext.TIME_FORMAT)
							.getTime();
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
