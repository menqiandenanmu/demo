<html>
<head>
</head>
<body>
订单提交中......
<form action="${trans.serviceUrl!"http://payment-test.chinapay.com/pay/TransGet"}" method="get" id="validateForm" name="validateForm" >
<input type=hidden name="MerId" value="${trans.merId}">
<input type=hidden name="OrdId" value="${trans.ordId}">
<input type=hidden name="TransAmt" value="${trans.transAmt}">
<input type=hidden name="CuryId" value="${trans.curyId}">
<input type=hidden name="TransDate" value="${trans.transDate}">
<input type=hidden name="TransType" value="${trans.transType}">
<input type=hidden name="Version" value="20040916">
<input type=hidden name="BgRetUrl" value="${trans.notifyPage}">
<input type=hidden name="PageRetUrl" value="${trans.returnPage}">
<input type=hidden name="GateId" value="${trans.gateId}">
<input type=hidden name="Priv1" value="Memo">
<input type=hidden name="ChkValue" value="${trans.checkValue}">
</form>
</body>
<script>
	document.validateForm.submit();
</script>
</html>