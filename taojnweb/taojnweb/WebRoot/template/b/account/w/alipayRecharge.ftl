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
    <title>钱包充值</title>
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
<#assign menu="recharge">
<#if wxFlag=="1">
<form action="${base}/pay/toWxpay.htm" method="get" id="validateForm">
<#else>
<form action="${base}/pay/toAlipay.htm" method="get" id="validateForm">
</#if>
<div class="order-item">
    <ul>
        <li><label for="" class="lab-title">姓名</label><span>${(agentInfo.corpName)!""}</span></li>
       <#if wxFlag=="1">
        <li><label for="" class="lab-title">充值方式</label><span>微信</span></li>
      	<#else>
      	 <li><label for="" class="lab-title">充值方式</label><span>支付宝</span></li>
      	</#if>
        <#--li><label for="" class="lab-title">余额</label><span class="price">${tradeAccount.curLeftValue?c}元</span></li-->
        <li><label for="" class="lab-title">充值金额</label><input type="text" name="amount" validate="{required:true}" size="10" class="order-text" placeholder="充值金额"/></li>
    </ul>
</div>
<div class="order-handle">
    <input type="submit" value="充值" class="btn order-btn"/>
</div>
</form>
 <#include "template/w/foot.ftl">
</body>
</html>