package com.mall.butler.order.m.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.netpay.NetpayContext;
import com.mall.butler.netpay.NetpayTransVo;
import com.mall.butler.order.model.OrderInfo;
import com.mall.butler.order.model.TransInfo;
import com.mall.butler.order.model.TransLogs;
import com.mall.butler.order.service.OrderService;
import com.mall.butler.order.service.TransService;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.common.lang.DateUtil;

public class NetpayAction extends ManageBaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private OrderService orderService;
	@Autowired
	private TransService transService;
	// 订单内容
	private Long orderId;
	private String merid;
	private String orderno;
	private String amount;
	private String currencycode;
	private String transdate;
	private String transtype;
	private String status;
	private String checkvalue;
	private NetpayTransVo trans;

	/**
	 * 订单支付操作
	 */

	public String execute() {
		OrderInfo order = orderService.getEntityById(OrderInfo.class, orderId);
		if (order.getPayStatus() != 0)
			return ERROR;
		TransInfo transInfo = transService.netpayTrans(order);
		if (transInfo.getStatus() == 1) { // 已经支付成功
			//orderLiveService.doPayed(order);
			return ERROR;
		}
		trans = new NetpayTransVo();
		trans.setServiceUrl(NetpayContext.SERVICE_URL);
		trans.setReturnPage(NetpayContext.RETURN_PAGE);
		trans.setNotifyPage(NetpayContext.NOTIFY_PAGE);
		trans.setGateId(NetpayContext.GATE_ID);

		trans.setOrdId(transInfo.getTransNo());
		trans.setMerId(NetpayContext.MER_ID);
		trans.setCuryId(NetpayContext.CUR_ID);
		trans.setTransType(NetpayContext.TRANS_TYPE);
		trans.setGateId(NetpayContext.GATE_ID);
		trans.setTransDate(DateUtil.format(transInfo.getCreateTime(), "yyyyMMdd"));
		trans.setReturnPage(this.getBasePath() + NetpayContext.RETURN_PAGE);
		trans.setNotifyPage(this.getBasePath() + NetpayContext.NOTIFY_PAGE);
		int amount = (int) (transInfo.getAmount() * 100);
		trans.setTransAmt(String.format("%1$012d", amount));
		chinapay.PrivateKey key = new chinapay.PrivateKey();
		chinapay.SecureLink t;
		boolean flag;

		flag = key.buildKey(trans.getMerId(), 0, NetpayContext.MER_PRK_PATH);
		if (flag == false)
			throw new RuntimeException("银联支付配置出错!");
		t = new chinapay.SecureLink(key);
		String checkValue = t.signOrder(trans.getMerId(), trans.getOrdId(), trans.getTransAmt(), trans.getCuryId(), trans.getTransDate(), trans.getTransType());
		trans.setCheckValue(checkValue);
		return SUCCESS;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * 支付宝交易通知
	 * 
	 * @return
	 */
	public String doNotify() {
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
	 */
	public String doReturn() {
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
	private TransInfo executeNotify() {
		// 信息验证
		if (!checkBack())
			throw new RuntimeException("验证失败信息!");
		// 支付记录
		TransInfo trans = transService.getNetpayTransByNo(orderno);
		if (trans == null)
			throw new RuntimeException("无效的信息!");
		if (trans.getStatus() == 1)
			return trans;
		if ("1001".equals(status)) {
			// 支付宝订单支付成功通知
			// 支付记录处理
			trans.setStatus(1);
			trans.setGatewayTransNo("");
			trans.setReportTime(new Date());
			trans.setReportInfo(request.getQueryString());
			transService.doUpdateTrans(trans);
			// 订单处理
			//OrderInfo order = orderService.getEntityById(OrderInfo.class, trans.getBusinessId());
			//wOrderService.doPayOrder(order, trans);
		} else if ("1003".equals(status)) {
			// 退货易成功
		}
		return trans;
	}

	/**
	 * 支付返回请求验证
	 * 
	 * @return
	 */
	public boolean checkBack() {
		chinapay.PrivateKey key = new chinapay.PrivateKey();
		chinapay.SecureLink t;
		boolean flag;
		flag = key.buildKey(NetpayContext.RES_CODE, 0, NetpayContext.PG_PUBK_PATH);
		if (flag == false)
			return false;
		t = new chinapay.SecureLink(key);
		flag = t.verifyTransResponse(merid, orderno, amount, currencycode, transdate, transtype, status, checkvalue);
		return flag;
	}

	/**
	 * 记录请求日志
	 */
	@Override
	public void prepare() {
		TransLogs logs = new TransLogs();
		logs.setFlag(1);
		logs.setQueryStr(request.getQueryString());
		logs.setUrl(this.getUri());
		transService.doInserTransLogs(logs);
	}

	public Long getOrderId() {
		return orderId;
	}

	public NetpayTransVo getTrans() {
		return trans;
	}

	public void setTrans(NetpayTransVo trans) {
		this.trans = trans;
	}

	public void setMerid(String merid) {
		this.merid = merid;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	public void setTransdate(String transdate) {
		this.transdate = transdate;
	}

	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCheckvalue(String checkvalue) {
		this.checkvalue = checkvalue;
	}
}
