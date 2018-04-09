<#--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	</head>
	<body onload="document.E_FORM.submit()">
		<form action="${trans.url}" method="get" name="E_FORM">
		    <input type=hidden name="_input_charset" value="${trans.input_charset}">
			<input type=hidden name="body" value="${trans.body}">
			<input type=hidden name="notify_url" value="${trans.notifyUrl}">
			<input type=hidden name="out_trade_no" value="${trans.out_trade_no}">
		    <input type=hidden name="partner" value="${trans.partner}">
		    <input type=hidden name="payment_type" value="${trans.payment_type}">
		    <input type=hidden name="paymethod" value="${trans.paymethod}">
		    <input type=hidden name="return_url" value="${trans.returnUrl}">
		    <input type=hidden name="seller_email" value="${trans.seller_email}">
		    <input type=hidden name="service" value="${trans.service}">
		    <input type=hidden name="subject" value="${trans.subject}">
		    <input type=hidden name="total_fee" value="${trans.total_fee}">
		    <input type=hidden name="sign" value="${trans.sign}">
		    <input type=hidden name="lt_b_pay" value="${trans.lt_b_pay}">
		    <input type=hidden name="credit_card_default_display" value="${trans.credit_card_default_display}">
		    <input type=hidden name="credit_card_pay" value="${trans.credit_card_pay}">
		    <input type=hidden name="sign_type" value="${trans.sign_type}">
		</form>
	</body>
</html-->

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝手机网页支付</title>
	</head>
	<body>
	${payHtml!}
	</body>
</html>
