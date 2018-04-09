<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <meta content="email=no" name="format-detection" />
     <#include "/template/w/common/head.ftl">
    <title>扫码支付</title>
    <link rel="stylesheet" href="${base}/style/w/css/reset.css"/>
    <link rel="stylesheet" href="${base}/style/w/css/order.css"/>  
    <script type="text/javascript" src="${base}/script/w/common.js"></script>
     <script language="JavaScript">
   $(document).ready(function(){
   	$("#validateForm").validate();
    });
</script>
</head>
<body class="order-body">
<form action="${base}/user/qrPay.htm" method="post" id="validateForm">
<div class="order-item">
    <ul>
        <li><label for="" class="lab-title">店铺</label><span><input type="hidden" name="id" value="${storeInfo.id!}" /> ${storeInfo.storeName!}</span></li>
        <li><label for="" class="lab-title">支付金额</label><input type="text" name="payNum" validate="{required:true}" size="10" class="order-text" placeholder="支付金额"/></li>
    </ul>
</div>
<div class="order-handle">
    <input type="submit" value="确认支付" class="btn order-btn"/>
</div>
</form>
 <#include "template/w/foot.ftl">
</body>
</html>

