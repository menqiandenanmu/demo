package com.mall.butler.netpay;

public class NetpayTransVo {
	private String serviceUrl; 
	//商户号长度为15个字节的数字串，由ChinaPay或清算银行分配。
	private String merId;
	
	//订单号，长度为16个字节的数字串，由商户系统生成，失败的订单号允许重复支付。
	private String ordId;
	
	//交易金额，长度为12个字节的数字串，例如：数字串"000000001234"表示12.34元。
	private String transAmt;
	
	//货币代码, 长度为3个字节的数字串，目前只支持人民币，取值为"156" 。
	private String curyId;
	
	//交易日期，长度为8个字节的数字串，表示格式为：YYYYMMDD。
	private String transDate;
	
	//交易类型，长度为4个字节的数字串，取值范围为："0001"和"0002"， 其中"0001"表示消费交易
	private String transType;
	
	// 返回值 即NetPayClient根据上述输入参数生成的商户数字签名，长度为256字节的字符串。
	private String checkValue;
	
	private String gateId;
	private String returnPage;
	private String notifyPage;
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	public String getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}

	public String getCuryId() {
		return curyId;
	}

	public void setCuryId(String curyId) {
		this.curyId = curyId;
	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

	public String getGateId() {
		return gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	public String getReturnPage() {
		return returnPage;
	}

	public void setReturnPage(String returnPage) {
		this.returnPage = returnPage;
	}

	public String getNotifyPage() {
		return notifyPage;
	}

	public void setNotifyPage(String notifyPage) {
		this.notifyPage = notifyPage;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	
}
