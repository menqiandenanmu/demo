<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
	<form action="${base}/pay/pay.htm" method="get" name="E_FORM">
	    <input type="hidden" name="orderId" value="${orderId}">
	    支付宝<input type="radio" name="payGate" value="alipay" checked/>
	    网银支付<input type="radio" name="payGate" value="netpay" />
	    微信支付<input type="radio" name="payGate" value="weixinpay"/>
	    <input type="submit"/>
	</form>
</body>
</html>