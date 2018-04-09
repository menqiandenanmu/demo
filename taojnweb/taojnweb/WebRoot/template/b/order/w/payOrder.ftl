<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body onload="document.E_FORM.submit()">
	<#if payGate=="alipay">
		<#assign payUrl=base+"/pay/toAlipay.htm">
	<#elseif payGate=="netpay">
		<#assign payUrl=base+"/pay/toNetpay.htm">
	<#elseif payGate=="accpay">
		<#assign payUrl=base+"/pay/toWeixinpay.htm">
	<#else>
		<#assign payUrl=base+"/pay/toAlipay.htm">
	</#if>
	<form action="${payUrl}" method="get" name="E_FORM">
	    <input type="hidden" name="orderId" value="${orderId}">
	</form>
</body>
</html>