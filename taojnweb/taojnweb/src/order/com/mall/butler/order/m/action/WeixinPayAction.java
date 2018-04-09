package com.mall.butler.order.m.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.account.dao.TradeOrderDao;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.model.TradeOrder;
import com.mall.butler.account.service.TradeAccountService;
import com.mall.butler.action.w.WebBaseAction;
import com.mall.butler.helper.SessionHelper;
import com.mall.butler.order.model.OrderInfo;
import com.mall.butler.order.model.TransInfo;
import com.mall.butler.order.service.OrderService;
import com.mall.butler.order.service.TransService;
import com.mall.butler.util.OidUtils;
import com.mall.butler.util.WxPayHelper;
import com.mall.butler.util.XmlUtil;
import com.mall.butler.weixin.WxJSApiPayDto;
import com.mall.butler.weixin.WxNotifyReq;
import com.mall.butler.weixin.WxPayConfig;
import com.mall.butler.weixin.WxPrepayDto;
import com.mall.butler.weixin.WxTradeType;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.WeixinHttpUtil;

public class WeixinPayAction extends WebBaseAction {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(WeixinPayAction.class);
	@Autowired
	private OrderService orderService;
	@Autowired
	private TransService transService;
	private WxPrepayDto wxPrepayDto;
	private String prepayId;// 预支付ID
	private String message;
	private Long orderId;
	private Double amount;
	private WxJSApiPayDto wxJSApiPayParam;
	private OrderInfo order;
	@Autowired
	public SessionHelper sessionHelper;
	private String remark;
	@Autowired
	TradeAccountService tradeAccountService;
	@Autowired
	private WxPayConfig wxPayConfig;

	/**
	 * 微信支付预处理
	 * 
	 * @return
	 */
	public String execute() {
		if (null==super.getAccount())
			return LOGIN;
		if (null==super.getAccount().getId())
			return ERROR;
		if (null == amount)
			return ERROR;
		TradeAccount tradeAccount = transService.getEntityById(
				TradeAccount.class, super.getAccount().getId());
		if (null == tradeAccount)
			return ERROR;
		if (!tradeAccount.getStatus())
			return ERROR;
		AccountInfo accountInfo = transService.getEntityById(
				AccountInfo.class, tradeAccount.getAccountId());
		if(accountInfo.getAccStatus().intValue()==0)
			return ERROR;
		//先生成交易号再生成生成交易记录和支付支付订单
		String transNo=transService.createNo("weixin");
		TradeOrder order=tradeAccountService.doRechageTrade(tradeAccount, amount, transNo, super.getLoginId(), 1, "微信充值");
		TransInfo transInfo = transService.alipayTrans(order,6);
		if (transInfo.getStatus() == 1) { // 已经支付成功
			return ERROR;
		}
		if(logger.isDebugEnabled()){
			logger.debug("1.【微信支付】发起微信支付，订单ID："+transInfo.getTransNo());
		}else{
			logger.error("1.【微信支付】发起微信支付，订单ID："+transInfo.getTransNo());
		}
		
		wxPrepayDto = new WxPrepayDto();
		wxPrepayDto.setAppid(wxPayConfig.getAppId());
		wxPrepayDto.setMch_id(wxPayConfig.getMchId());
		wxPrepayDto.setNonce_str(OidUtils.newId());
		
		wxPrepayDto.setBody("taojn");
		wxPrepayDto.setOut_trade_no(transInfo.getTransNo());
//		wxPrepayDto.setOut_trade_no("2345678906566955566");
		// 金额需要*100，微信支付要求 12.34需要转换成1234
		String totalFee = String.valueOf(transInfo.getAmount()*100); 
		totalFee = totalFee.substring(0,totalFee.indexOf("."));
		
		wxPrepayDto.setTotal_fee(totalFee);
		wxPrepayDto.setSpbill_create_ip(request.getRemoteAddr());
		wxPrepayDto.setNotify_url(super.getBasePath() +wxPayConfig.getNotifyUrl());
		wxPrepayDto.setTrade_type(WxTradeType.JSAPI.getCode());
		
		HttpServletRequest req = ServletActionContext.getRequest();
		String url = req.getRequestURL().toString();
		Object object = sessionHelper.get(ManageContext.WXSESSION_OPEN_ID);
		System.out.println("我进来了"+(String)object);
		if (object != null) {
			wxPrepayDto.setOpenid((String) object);
		} else {
			if (!TxtUtil.isEmpty(req.getQueryString()))
				url = url + "?" + req.getQueryString();
			sessionHelper.set(ManageContext.WXSESSION_PAY_URL, url);
			return "wxUserCheck";
		}
		wxPrepayDto.setSign(WxPayHelper.signPrePay(wxPrepayDto, wxPayConfig.getKey()));
//		System.out.println("wxPrepayDto.appid===="+wxPrepayDto.getAppid());
//		System.out.println("wxPrepayDto.setMch_id===="+wxPrepayDto.getMch_id());
//		System.out.println("wxPrepayDto.setNonce_str===="+wxPrepayDto.getNonce_str());
//		System.out.println("wxPrepayDto.setBody===="+wxPrepayDto.getBody());
//		System.out.println("wxPrepayDto.setOut_trade_no===="+wxPrepayDto.getOut_trade_no());
//		System.out.println("wxPrepayDto.setTotal_fee===="+wxPrepayDto.getTotal_fee());
//		System.out.println("wxPrepayDto.setSpbill_create_ip===="+wxPrepayDto.getSpbill_create_ip());
//		System.out.println("wxPrepayDto.setNotify_url===="+wxPrepayDto.getNotify_url());
//		System.out.println("wxPrepayDto.setTrade_type===="+wxPrepayDto.getTrade_type());
//		System.out.println("wxPrepayDto.setOpenid===="+wxPrepayDto.getOpenid());
//		System.out.println("wxPrepayDto.setSign===="+wxPrepayDto.getSign());
//		System.out.println("wxPayConfig.getPrePayUrl()===="+wxPayConfig.getPrePayUrl());
	
		// 请求微信统一下单接口，返回微信【预支付ID】
		prepayId = WxPayHelper.doUnifiedPrePay(wxPayConfig.getPrePayUrl(), wxPrepayDto);
		// step2. 页面调用微信JSAPI支付接口
		// 生成微信JSApi调用支付所需的参数
		wxJSApiPayParam = WxPayHelper.getWxJSApiParam(wxPrepayDto, prepayId, wxPayConfig.getKey());
			

		
		String info = "Mch_id:"+wxPrepayDto.getMch_id()+"，Total_fee:"+wxPrepayDto.getTotal_fee()+"，Notify_url:"+wxPrepayDto.getNotify_url()+"，Out_trade_no:"+wxPrepayDto.getOut_trade_no()+"，Body:"+wxPrepayDto.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("3.【微信支付】数据组装完毕，数据："+info);
		}else{
			logger.error("3.【微信支付】数据组装完毕，数据："+info);
		}
		return SUCCESS;
	}
	private String err_msg;//返回的出错信息
	public String getErr_msg() {
		return err_msg;
	}
	public void setErr_msg(String errMsg) {
		err_msg = errMsg;
	}
	/**
	 * 停止支付时转到的界面
	 * 
	 * @return
	 */
	public String stopPay() {
		throw new RuntimeException("已取消支付");
//		return SUCCESS;
	}
	@Autowired
	private TradeOrderDao tradeOrderDao;
	/**
	 * 支付成功通知
	 */
	public String payNotify() {
		try {
			String reqXml = null;
			reqXml = WeixinHttpUtil.getPostContent(request);

			WxNotifyReq wxNotifyReq = getNotifyReq(reqXml);
			
			if(logger.isDebugEnabled()){
				logger.debug("1.【通知方法payNotify】开始执行，交易号："+wxNotifyReq.getTransaction_id()+"返回的xml："+reqXml);
			}else{
				logger.error("1.【通知方法payNotify】开始执行，交易号："+wxNotifyReq.getTransaction_id()+"返回的xml："+reqXml);
			}
			
			System.out.println("支付通知返回交易号===================================="+wxNotifyReq.getOut_trade_no()+"==================");
			// 支付记录
			TransInfo transInfo = transService.getWeixinTransByNo(wxNotifyReq.getOut_trade_no());
			System.out.println("支付通知返回查询交易记录======================================================");
			if (transInfo == null)
				throw new RuntimeException("无效的信息!");
			System.out.println("支付通知返回======================================================");
			if(1==transInfo.getStatus()){
				//支付成功什么都不做
			}else {
				transInfo.setStatus(1);
				transInfo.setGatewayTransNo(wxNotifyReq.getTransaction_id());
				transInfo.setReportTime(new Date());
				transInfo.setGateway(6);
				transInfo.setReportInfo(wxNotifyReq.getReturn_msg());
				transService.doUpdateTrans(transInfo);
				tradeAccountService.payed(transInfo.getTransNo(),wxNotifyReq.getTransaction_id(),request.getQueryString());
			}
			
			// 根据交易号查询充值订单
			TradeOrder tradeOrder = new TradeOrder();
			tradeOrder.setNetpayNo(transInfo.getTransNo());
			List<TradeOrder> trades = tradeOrderDao.find(tradeOrder);
			if (null != trades && trades.size() > 0) {
				tradeOrder = trades.get(0);
			} else {
				throw new RuntimeException("订单未找到");
			}
			orderId = tradeOrder.getId();
			if(logger.isDebugEnabled()){
				logger.debug("2.【后台通知方法payNotify】订单状态修改结束，订单号："+order.getOrderNo());
			}else{
				logger.error("2.【后台通知方法payNotify】订单状态修改结束，订单号："+order.getOrderNo());
			}
			
			return SUCCESS;
		} catch (Exception e) {
			return "fail";
		}
	}


	private WxNotifyReq getNotifyReq(String reqXml) {
		if (StringUtils.isBlank(reqXml))
			throw new RuntimeException("【微信支付】通知失败,错误：请求内容为空");

		WxNotifyReq wxNotifyReq = null;
		try {
			wxNotifyReq = XmlUtil.toObj(WxNotifyReq.class, reqXml);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("【微信支付】后台通知XML转换失败，待转换XML："+reqXml+"，错误：" + e);
			}else{
				logger.error("【微信支付】后台通知XML转换失败，待转换XML："+reqXml+"，错误：" + e);
			}
			throw new RuntimeException("【微信支付】后台通知XML转换失败，待转换XML："+reqXml+"，错误：" + e);
		}

		return wxNotifyReq;
	}
	
	public String failPay() {
		order = orderService.getEntityById(OrderInfo.class, orderId);
		if(logger.isDebugEnabled()){
			logger.debug("【微信支付】前台返回错误信息："+message);
		}else{
			logger.error("【微信支付】前台返回错误信息："+message);
		}
		return "error";
	}
	
	public String successPay() {
		order = orderService.getEntityById(OrderInfo.class, orderId);
		return "success";
	}
	
	
	
	public WxPrepayDto getWxPrepayDto() {
		return wxPrepayDto;
	}

	public void setWxPrepayDto(WxPrepayDto wxPrepayDto) {
		this.wxPrepayDto = wxPrepayDto;
	}


	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public WxJSApiPayDto getWxJSApiPayParam() {
		return wxJSApiPayParam;
	}

	public void setWxJSApiPayParam(WxJSApiPayDto wxJSApiPayParam) {
		this.wxJSApiPayParam = wxJSApiPayParam;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OrderInfo getOrder() {
		return order;
	}

	public void setOrder(OrderInfo order) {
		this.order = order;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
