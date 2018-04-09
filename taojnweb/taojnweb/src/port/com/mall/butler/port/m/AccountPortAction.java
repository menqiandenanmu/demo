package com.mall.butler.port.m;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.CommonContext;
import com.mall.butler.ManageContext;
import com.mall.butler.SignUtil;
import com.mall.butler.account.dao.AccountCouponDao;
import com.mall.butler.account.model.AccountCoupon;
import com.mall.butler.account.model.Merchant;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.model.TradeAccountDetail;
import com.mall.butler.account.service.TradeAccountService;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.port.xml.OrderXml;
import com.mall.butler.port.xml.TradeAccountXml;
import com.mall.butler.port.xml.TransInfoXml;
import com.mall.butler.port.xml.TransListXml;
import com.mall.butler.util.XmlHelper;
import com.mall.butler.xml.BaseXml;
import com.mall.util.common.HttpUtil;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.lang.DateUtil;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

public class AccountPortAction extends ManageBaseAction {
	private static final long serialVersionUID = -4496210396036737937L;
	private String transTime;// 时间
	private String sign;// 加密值
	private String data;// 加密值
	@Autowired
	private TradeAccountService tradeAccountService;

	/**
	 * 账户接口，通过用户编号查询
	 */
	public void userInfo() {

		// 获取请求参数
		TradeAccountXml tradeXml = null;
		TradeAccount tradeAccount = null;
		try {
			tradeXml = XmlHelper.toObj(TradeAccountXml.class, data.trim());
			Merchant merchant = tradeAccountService.getEntityById(Merchant.class, ManageContext.ACCOUNT_TJN_ID);
			if (merchant.getMerchantStatus().intValue() != 1)
				throw new RuntimeException("Interface Is Disabled");
			// 验证参数
			this.auth(merchant.getMerchantNumber());
			if (null == tradeXml)
				throw new RuntimeException("User information cannot be empty");
			// 业务数据组装
			if (TxtUtil.isEmpty(tradeXml.getAccountCode()))
				throw new RuntimeException("User name cannot be empty");
			tradeAccount = tradeAccountService.queryTradeByLoginName(tradeXml
					.getAccountCode().trim());
			if (null == tradeAccount)
				throw new RuntimeException("Wallet not found");
		} catch (Exception e) {
			tradeXml = new TradeAccountXml();
			tradeXml.setCode("0");
			if (null != e.getMessage())
				tradeXml.setMsg(e.getMessage());
			else {
				tradeXml.setMsg("Interface error");
			}
			// 返回结果
			super.writeXml(XmlHelper.toXml(tradeXml));
			return;
		}
		tradeXml.setAmount(tradeAccount.getCurLeftValue());
		tradeXml.setStatus(tradeAccount.getStatus());
		tradeXml.setTradeName(tradeAccount.getTradeAccName());
		tradeXml.setTradeNo(tradeAccount.getTradeType());
		tradeXml.setCode("1");
		tradeXml.setMsg("GetSuccess");
		// 返回结果
		super.writeXml(XmlHelper.toXml(tradeXml));
	}

	@Autowired
	private AccountCouponDao accountCouponDao;

	/**
	 * CRM账户接口，通过条形码
	 */
	public void crmUserInfo() {
		// 获取请求参数
		TradeAccountXml tradeXml = null;
		TradeAccount tradeAccount = null;
		AccountCoupon accountCoupon = null;
		try {
			System.out.println("收到请求" + data);
			tradeXml = XmlHelper.toObj(TradeAccountXml.class, data.trim());
			Merchant merchant = tradeAccountService.getEntityById(Merchant.class, ManageContext.ACCOUNT_CRM_ID);
			if (merchant.getMerchantStatus().intValue() != 1)
				throw new RuntimeException("Interface Is Disabled");
			// 验证参数
			this.auth(merchant.getMerchantNumber());
			if (null == tradeXml)
  				throw new RuntimeException("User information cannot be empty");
			// 业务数据组装
			if (TxtUtil.isEmpty(tradeXml.getPassword()))
				throw new RuntimeException("Bar code cannot be empty");
			if (tradeXml.getPassword().substring(0, 2).equals("88")) {
				// 赠券
				AccountCoupon filter = new AccountCoupon();
				filter.setBarcode(tradeXml.getPassword().substring(0, 12));
				List<AccountCoupon> list = accountCouponDao.find(filter);
				if (null == list || list.size() == 0)
					throw new RuntimeException("Did not find coupons");
				    accountCoupon = list.get(0);
			} else {
				tradeAccount = tradeAccountService.queryTradeByPassword(tradeXml.getPassword().trim());
				if (null == tradeAccount)
					throw new RuntimeException("Wallet not found");
			}
		} catch (Exception e) {
			BaseXml baseXml = new BaseXml();
			baseXml.setCode("0");
			if (null != e.getMessage())
				baseXml.setMsg(e.getMessage());
			else {
				baseXml.setMsg("Interface error");
			}
			// 返回结果
			// 重新组装xml
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
			sb.append("<response>");
			sb.append("<code>" + baseXml.getCode() + "</code>");
			sb.append("<msg>" + baseXml.getMsg() + "</msg>");
			sb.append("</response>");
			super.writeXml(sb.toString());
			return;
		}
		if (tradeXml.getPassword().substring(0, 2).equals("88")) {

			tradeXml.setAmount(accountCoupon.getPrice());
			tradeXml.setStatus(true);
			tradeXml.setTradeName(accountCoupon.getAccountName());
			tradeXml.setCode("00");
			tradeXml.setMsg("GetSuccess");
			// 返回结果
			// 重新组装xml
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
			sb.append("<response>");
			sb.append("<code>" + tradeXml.getCode() + "</code>");
			sb.append("<msg>" + tradeXml.getMsg() + "</msg>");
			sb.append("<transTime>"+ DateUtil.format(new Date(), ManageContext.TIME_FORMAT)	+ "</transTime>");
			sb.append("<account>");
			double num = tradeXml.getAmount();
			String xxString = String.valueOf(num);
			String[] strings = xxString.split("\\.");
			String endString = "";
			if (strings[1].length() == 0) {
				endString = "00";
			} else if (strings[1].length() == 1) {
				endString = strings[1] + "0";
			} else {
				endString = strings[1];
			}
			endString = String.format("%010d", Long.parseLong(strings[0]))+ endString;
			sb.append("<amount>" + endString + "</amount>");
			sb.append("<password>" + tradeXml.getPassword() + "</password>");
			sb.append("<status>" + tradeXml.getStatus() + "</status>");
			sb.append("<tradeName>" + tradeXml.getTradeName() + "</tradeName>");
			sb.append("</account>");
			sb.append("</response>");
			super.writeXml(sb.toString());
		} else {
			tradeXml.setAmount(tradeAccount.getCurLeftValue());
			tradeXml.setStatus(tradeAccount.getStatus());
			tradeXml.setTradeName(tradeAccount.getTradeAccName());
			tradeXml.setCode("00");
			tradeXml.setMsg("GetSuccess");
			// 返回结果
			// 重新组装xml
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
			sb.append("<response>");
			sb.append("<code>" + tradeXml.getCode() + "</code>");
			sb.append("<msg>" + tradeXml.getMsg() + "</msg>");
			sb.append("<transTime>"+ DateUtil.format(new Date(), ManageContext.TIME_FORMAT)+ "</transTime>");
			sb.append("<account>");
			double num = tradeXml.getAmount();
			String xxString = String.valueOf(num);
			String[] strings = xxString.split("\\.");
			String endString = "";
			if (strings[1].length() == 0) {
				endString = "00";
			} else if (strings[1].length() == 1) {
				endString = strings[1] + "0";
			} else {
				endString = strings[1];
			}
			endString = String.format("%010d", Long.parseLong(strings[0]))+ endString;
			sb.append("<amount>" + endString + "</amount>");
			sb.append("<password>" + tradeXml.getPassword() + "</password>");
			sb.append("<status>" + tradeXml.getStatus() + "</status>");
			sb.append("<tradeName>" + tradeXml.getTradeName() + "</tradeName>");
			sb.append("</account>");
			sb.append("</response>");
			super.writeXml(sb.toString());
		}

	}

	/**
	 * 账户明细接口
	 */
	public void transInfo() {

		// 获取请求参数
		TransInfoXml transXml = null;
		TransListXml transListXml = new TransListXml();
		List<TransInfoXml> list = new ArrayList<TransInfoXml>();
		TransInfoXml transInfoXml = null;
		try {
			System.out.println("收到请求" + data);
			transXml = XmlHelper.toObj(TransInfoXml.class, data.trim());
			Merchant merchant = tradeAccountService.getEntityById(
					Merchant.class, ManageContext.ACCOUNT_TJN_ID);
			if (merchant.getMerchantStatus().intValue() != 1)
				throw new RuntimeException("Interface Is Disabled");
			// 验证参数
			this.auth(merchant.getMerchantNumber());
			if (null == transXml)
				throw new RuntimeException("User information cannot be empty");
			// 业务数据组装
			if (TxtUtil.isEmpty(transXml.getAccountCode()))
				throw new RuntimeException("User information cannot be empty");
			if (TxtUtil.isEmpty(transXml.getOpType()))
				throw new RuntimeException("Type cannot be empty");
			List<TradeAccountDetail> tradeDetails = tradeAccountService.queryTradeDetails(transXml.getAccountCode().trim(),	transXml.getOpType());
			if (null != tradeDetails & tradeDetails.size() > 0) {
				for (TradeAccountDetail trans : tradeDetails) {
					transInfoXml = new TransInfoXml();
					transInfoXml.setAccountCode(trans.getAccountName());
					transInfoXml.setAmount(trans.getOpValue());
					transInfoXml.setCurrValue(trans.getLeftValue());
					transInfoXml.setOrderInfo(trans.getRemark());
					transInfoXml.setTradeName(trans.getTradeAccName());
					transInfoXml.setTradeNo(trans.getRemark1());
					transInfoXml.setOrderNo(trans.getOrderNo());
					transInfoXml.setTransTime(DateUtil.format(trans.getCreateTime(), ManageContext.TIME_FORMAT));
					list.add(transInfoXml);
				}
				transListXml.setTransInfoXmls(list);
			}
		} catch (Exception e) {
			transListXml = new TransListXml();
			transListXml.setCode("0");
			if (null != e.getMessage())
				transListXml.setMsg(e.getMessage());
			else {
				transListXml.setMsg("Interface error");
			}
			// 返回结果
			super.writeXml(XmlHelper.toXml(transListXml));
			return;
		}
		transListXml.setCode("1");
		transListXml.setMsg("GetSuccess");
		// 返回结果
		super.writeXml(XmlHelper.toXml(transListXml));
	}

	/**
	 * CRM交易查询
	 */
	public void crmTransInfo() {
		System.out.println("收到数据======================" + data.trim());
		// 业务数据组装
		OrderXml orderXml = null;
		try {

			System.out.println("收到请求" + data);
			orderXml = XmlHelper.toObj(OrderXml.class, data.trim());
			Merchant merchant = tradeAccountService.getEntityById(Merchant.class, ManageContext.ACCOUNT_CRM_ID);
			if (merchant.getMerchantStatus().intValue() != 1)
				throw new RuntimeException("Interface Is Disabled");
			// 验证参数
			this.auth(merchant.getMerchantNumber());
			if (null == orderXml)
				throw new RuntimeException("Order information cannot be empty");
			// 业务数据组装
			if (TxtUtil.isEmpty(orderXml.getOrderNo()))
				throw new RuntimeException("Merchant order number cannot be empty");
			// 钱包扣款
			TradeAccountDetail tradeAccountDetail = tradeAccountService.findByOrderNo(orderXml.getOrderNo());
			if (null == tradeAccountDetail) {
				// 返回结果
				// 重新组装xml
				StringBuilder sb = new StringBuilder();
				sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
				sb.append("<response>");
				sb.append("<code>0</code>");
				sb.append("<msg>SuccessfulTrade</msg>");
				sb.append("</response>");
				super.writeXml(sb.toString());
				return;
			} else {
				orderXml.setTranDay(DateUtil.format(tradeAccountDetail.getCreateTime(), "yyyymmdd"));
				orderXml.setTranTime(DateUtil.format(tradeAccountDetail.getCreateTime(), "hhMMss"));
				orderXml.setOrderNo(tradeAccountDetail.getOrderNo());
				orderXml.setTradeNo(tradeAccountDetail.getRemark1());
				orderXml.setOrderInfo(tradeAccountDetail.getRemark());
				double paysum = tradeAccountDetail.getOpValue();
				String str = String.valueOf(paysum);
				String[] strings = str.split("\\.");
				long num = Long.parseLong(strings[0]);
				String endString = "";
				if (strings[1].length() == 0) {
					endString = "00";
				} else if (strings[1].length() == 1) {
					endString = strings[1] + "0";
				} else {
					endString = strings[1];
				}
				endString = String.format("%010d", num) + endString;
				orderXml.setPayAmount(endString);
				orderXml.setOperateCode(tradeAccountDetail.getOpLoginName());
				orderXml.setPosCode(tradeAccountDetail.getRemark2());// pos编号
				orderXml.setStoreCode(tradeAccountDetail.getRemark3());// 店铺编号
			}
		} catch (Exception e) {
			BaseXml baseXml = new BaseXml();
			baseXml.setCode("0");
			if (null != e.getMessage())
				baseXml.setMsg(e.getMessage());
			else {
				baseXml.setMsg("接口信息出错");
			}
			// 返回结果
			// 重新组装xml
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
			sb.append("<response>");
			sb.append("<code>" + baseXml.getCode() + "</code>");
			sb.append("<msg>" + baseXml.getMsg() + "</msg>");
			sb.append("</response>");
			super.writeXml(sb.toString());
			return;
		}
		orderXml.setPayType("01");
		orderXml.setCode("00");
		orderXml.setMsg("successPayment");
		// 返回结果
		// 重新组装xml
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		sb.append("<response>");
		sb.append("<code>" + orderXml.getCode() + "</code>");
		sb.append("<msg>" + orderXml.getMsg() + "</msg>");
		sb.append("<transTime>"	+ DateUtil.format(new Date(), ManageContext.TIME_FORMAT)+ "</transTime>");
		sb.append("<order>");
		sb.append("<operateCode>" + orderXml.getOperateCode()+ "</operateCode>");
		sb.append("<orderInfo>" + orderXml.getOrderInfo() + "</orderInfo>");
		sb.append("<orderNo>" + orderXml.getOrderNo() + "</orderNo>");
		sb.append("<payAmount>" + orderXml.getPayAmount() + "</payAmount>");
		sb.append("<payType>" + orderXml.getPayType() + "</payType>");
		sb.append("<posCode>" + orderXml.getPosCode() + "</posCode>");
		sb.append("<storeCode>" + orderXml.getStoreCode() + "</storeCode>");
		sb.append("<transNo>" + orderXml.getTradeNo() + "</transNo>");
		sb.append("<tranDay>" + orderXml.getTranDay() + "</tranDay>");
		sb.append("<tranTime>" + orderXml.getTranTime() + "</tranTime>");
		sb.append("</order>");
		sb.append("</response>");
		super.writeXml(sb.toString());
	}

	/**
	 * 验证请求信息
	 */
	private void auth(String partner) {
		// 请求时间验证客户端时间和服务器时间误差不能超过一个小时
		try {
			if (null == transTime)
				throw new RuntimeException("transTime不能为空");
			if (null == data)
				throw new RuntimeException("data不能为空");
			if (null == sign)
				throw new RuntimeException("sign不能为空");
			Long mtime = new Date().getTime()- DateUtil.parse(transTime, ManageContext.TIME_FORMAT).getTime();
			if (Math.abs(mtime) > 1000 * 60 * 60 * 1)
				throw new RuntimeException("请求时间同服务器时间不符!");
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("请求时间格式和服务器时间不符!");
		}
		// 数据验证
		if (!SignUtil.makeSign(data, transTime, partner).equals(this.sign))
			throw new RuntimeException("数据验证失败!");
	}

	/**
	 * 下载用户信息接口
	 */
	public void accountUpload() {
		BaseXml baseXml = new BaseXml();
		sendPost(baseXml);

	}

	private void sendPost(BaseXml baseXml) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", XmlHelper.toXml(baseXml));
		params.put("transTime", DateUtil.format(new Date(),ManageContext.TIME_FORMAT));
		String sign = SignUtil.makeSign(params.get("data"), params.get("transTime"), CommonContext.PARTNER);
		params.put("signType", "MD5");
		params.put("sign", sign);
		HttpUtil.doPost(CommonContext.API_URL_UPDATE, params, "UTF-8");
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
