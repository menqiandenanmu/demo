package com.mall.butler.order.m.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.model.TradeOrder;
import com.mall.butler.account.service.TradeAccountService;
import com.mall.butler.action.w.WebBaseAction;
import com.mall.butler.alipay.AlipayTransVo;
import com.mall.butler.order.alipay.config.AlipayConfig;
import com.mall.butler.order.alipay.util.AlipayNotify;
import com.mall.butler.order.alipay.util.AlipaySubmit;
import com.mall.butler.order.model.TransInfo;
import com.mall.butler.order.model.TransLogs;
import com.mall.butler.order.service.TransService;
import com.mall.util.common.action.MessageDialog;

public class AlipayAction extends WebBaseAction {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AlipayAction.class);
	@Autowired
	private TransService transService;
	// 订单内容
	private String orderInfo;
	private String returnPage;
	private String trans_time;
	private String merid;
	private String operator;
	private String sign;
	private String sign_type;
	private Long orderId;
	private AlipayTransVo trans;
	private String batch_no;
	private Double amount;
	private String remark;
	private String payHtml;
	@Autowired
	TradeAccountService tradeAccountService;
	/**
	 * 订单支付操作
	 */
	public String execute() {
		if (null==super.getAccount())
			return LOGIN;
		if (null==super.getAccount().getId())
			return ERROR;
		if (null == amount)
			return ERROR;
		TradeAccount tradeAccount = tradeAccountService.getEntityById(
				TradeAccount.class, super.getAccount().getId());
		if (null == tradeAccount)
			return ERROR;
		if (!tradeAccount.getStatus())
			return ERROR;
		AccountInfo accountInfo = tradeAccountService.getEntityById(
				AccountInfo.class, tradeAccount.getAccountId());
		if(accountInfo.getAccStatus().intValue()==0)
			return ERROR;
		//先生成交易号再生成生成交易记录和支付支付订单
		String transNo=transService.createNo("alipay");
		TradeOrder order=tradeAccountService.doRechageTrade(tradeAccount, amount, transNo, super.getLoginId(), 1, remark);
		TransInfo transInfo = transService.alipayTrans(order,5);
		if (transInfo.getStatus() == 1) { // 已经支付成功
			return ERROR;
		}
		// 商户订单号
		String out_trade_no = transInfo.getTransNo();
//		// 商户网站订单系统中唯一订单号，必填
//		// 订单名称
		String subject = "taojnOnlineRecharge";
		// 付款金额
		String total_fee = String.valueOf(amount);
		// 服务器异步通知页面路径
		String notify_url = super.getBasePath() + "/pay/alipayNotify.htm";
		// 需http://格式的完整路径，不能加?id=123这类自定义参数

		// 页面跳转同步通知页面路径
		String return_url = super.getBasePath() + "/pay/alipayReturn.htm";
		// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

		// 操作中断返回地址
		String show_url = this.getBasePath() + "/user/toRecharge.htm";
		// 用户付款中途退出返回商户的地址。需http://格式的完整路径，不允许加?id=123这类自定义参数
		//支付类型
		String payment_type = "1";
		//订单描述
		String body = "tjn";
		//超时时间
		String it_b_pay = "3837m";
		//钱包token
		String extern_token = null;
		//选填
		// ////////////////////////////////////////////////////////////////////////////////
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", "jnds@aliyun.com");
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("body", body);
		sParaTemp.put("it_b_pay", it_b_pay);
		sParaTemp.put("extern_token", extern_token);

		//建立请求
		payHtml = AlipaySubmit.buildRequest("https://mapi.alipay.com/gateway.do?",sParaTemp, "get", "确认");
		if(logger.isDebugEnabled()){
			logger.debug("3.【execute】数据组装完毕，准备提交，提交数据："+payHtml);
		}else{
			logger.error("3.【execute】数据组装完毕，准备提交，提交数据："+payHtml);
		}
//		// 记录请求日志
		try{
			recordTransLog(payHtml);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 支付宝通知参数
	private String out_trade_no;
	private String trade_no;

	/**
	 * 支付宝交易通知
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String doNotify() throws UnsupportedEncodingException {
		logger.error("支付宝服务器通知返回===="+out_trade_no);
		TransInfo transInfo = executeNotify();
		if (transInfo != null)
			return SUCCESS;
		else
			return ERROR;
	}

	/**
	 * 支付宝交易返回业务处理
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String doReturn() throws UnsupportedEncodingException {
		logger.error("支付宝页面通知返回===="+out_trade_no);
		TransInfo transInfo = executeNotify();
		if (transInfo != null) {
			orderId = transInfo.getBusinessId();
			return SUCCESS;
		} else {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.getMessages().add("非法请求!");
			return ERROR;
		}
	}

	// 处理通知业务
	@SuppressWarnings("unchecked")
	private TransInfo executeNotify() throws UnsupportedEncodingException {
		// 支付记录
		TransInfo trans = transService.getAlipayTransByNo(out_trade_no);
		if (trans == null){
			logger.error("无效的数据"+out_trade_no);
			throw new RuntimeException("无效的信息!");
		}
		// 信息验证
		// 记录返回的信息参数
		StringBuffer info = new StringBuffer();

		// 获取支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i]
						+ ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			info.append("[name:" + name + ",value:" + valueStr + "]");
			params.put(name, valueStr);
		}

		boolean verify_result = AlipayNotify.verifyReturn(params);
		
		if (!verify_result){
			logger.error("验证信息失败");
			throw new RuntimeException("验证失败信息!");
			}
		
		if (trans.getStatus() == 1) {
			return trans;
		}
		else {
			// 支付宝订单支付成功通知
			// 支付记录处理
			trans.setStatus(1);
			trans.setGatewayTransNo(trade_no);
			trans.setReportTime(new Date());
			trans.setReportInfo(request.getQueryString());
			transService.doUpdateTrans(trans);
			tradeAccountService.payed(trans.getTransNo(),trade_no,request.getQueryString());
			logger.error("支付处理成功===================="+trans.getTransNo());
		}
		return trans;
	}

	/**
	 * 记录请求日志
	 * 
	 * @param QueryStr
	 */
	public void recordTransLog(String QueryStr) {
		TransLogs logs = new TransLogs();
		logs.setFlag(1);
		logs.setQueryStr(QueryStr);
		logs.setUrl(this.getUri());
		transService.doInserTransLogs(logs);
	}
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public void setReturnPage(String returnPage) {
		this.returnPage = returnPage;
	}

	public void setTrans_time(String trans_time) {
		this.trans_time = trans_time;
	}

	public void setMerid(String merid) {
		this.merid = merid;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public void setSign_type(String signType) {
		sign_type = signType;
	}

	public String getOrderInfo() {
		return orderInfo;
	}

	public String getMerid() {
		return merid;
	}

	public String getOperator() {
		return operator;
	}

	public String getSign() {
		return sign;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getReturnPage() {
		return returnPage;
	}

	public String getTrans_time() {
		return trans_time;
	}

	public AlipayTransVo getTrans() {
		return trans;
	}

	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}


	public void setTrade_no(String tradeNo) {
		trade_no = tradeNo;
	}


	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batchNo) {
		batch_no = batchNo;
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

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrans(AlipayTransVo trans) {
		this.trans = trans;
	}

	public String getPayHtml() {
		return payHtml;
	}

	public void setPayHtml(String payHtml) {
		this.payHtml = payHtml;
	}


}
