package com.mall.butler.weixin;

import javax.xml.bind.annotation.XmlRootElement;

import com.mall.butler.xml.BaseXml;

/**
 * 微信退款 请求后返回的信息
 * 
 */
@XmlRootElement(name = "xml")
public class WxRefundResp extends BaseXml{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String appid;

	private String mch_id;/* 商户号 */

	private String return_code;/* 返回码 */

	private String return_msg;/* 返回信息 */

	private String result_code;

	private String err_code_des;

	private String nonce_str; // 随机字符串，不长于 32 位
	
	private String sign; // 签名
	
	private String transaction_id;// 微信订单号
	
	private String out_trade_no;// 商户订单号
	
	private String out_refund_no;// 商户退款单号
	
	private String refund_id;// 微信退款单号
	
	private String refund_fee;// 退款金额

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mchId) {
		mch_id = mchId;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String returnCode) {
		return_code = returnCode;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String returnMsg) {
		return_msg = returnMsg;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String resultCode) {
		result_code = resultCode;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String errCodeDes) {
		err_code_des = errCodeDes;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonceStr) {
		nonce_str = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transactionId) {
		transaction_id = transactionId;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String outRefundNo) {
		out_refund_no = outRefundNo;
	}

	public String getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(String refundId) {
		refund_id = refundId;
	}

	public String getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(String refundFee) {
		refund_fee = refundFee;
	}
	
	
	

}
