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
    <title>扫码支付成功</title>
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
<form action="${base}/index.htm" method="post" id="validateForm">
<div class="order-item1">
    <ul>
        <li><label for="" >恭喜您支付成功</label></li>
        <li><label for="" >支付金额￥${paysum?c}</label></li>
    </ul>
</div>
<div class="order-handle">
    <input type="submit" value="返回首页" class="btn order-btn"/>
</div>
</form>
 <#include "template/w/foot.ftl">
</body>
</html>
