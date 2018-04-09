package com.mall.butler.weixin;

import javax.xml.bind.annotation.XmlRootElement;

import com.mall.butler.xml.BaseXml;

/**
 * 微信退款类
 * 
 * 
 *
 */
@XmlRootElement(name = "xml")
public class WxRefundReq extends BaseXml{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String appid;  			//微信分配的公众账号ID
	private String mch_id;			//微信支付分配的商户号
	private String device_info;		//终端设备号    【可不填】
	private String nonce_str;		//随机字符串，不长于32位
	private String sign;				//签名
	private String transaction_id;	//微信订单号
	private String out_trade_no;		//商户系统内部的订单号,transaction_id、out_trade_no二选一，如果同时存在优先级：transaction_id> out_trade_no
	private String out_refund_no;	//商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
	private Integer total_fee;		//订单总金额，单位为分，只能为整数
	private Integer refund_fee;		//退款总金额，订单总金额，单位为分，只能为整数
	private String refund_fee_type;	//货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他    【可不填】
	private String op_user_id;
	
	public String getAppid() {
		return appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public String getOut_refund_no() {
		return out_refund_no;
	}

	public String getRefund_fee_type() {
		return refund_fee_type;
	}
	public String getOp_user_id() {
		return op_user_id;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public void setRefund_fee_type(String refund_fee_type) {
		this.refund_fee_type = refund_fee_type;
	}
	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}
	public Integer getTotal_fee() {
		return total_fee;
	}
	public Integer getRefund_fee() {
		return refund_fee;
	}
	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}
	public void setRefund_fee(Integer refund_fee) {
		this.refund_fee = refund_fee;
	}	

	
	
}
