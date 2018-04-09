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
   	var fm=new BackUrlForm("validateForm","充值","${base}/index.htm");
    });
</script>
</head>
<body class="order-body">
<#assign menu="recharge">
<form action="${base}/user/doRecharge.htm" method="post" id="validateForm">
<div class="order-item">
    <ul>
        <li><label for="" class="lab-title">姓名</label><span>${agentInfo.corpName!}</span></li>
         <li><label for="" class="lab-title">余额</label><span class="price">${tradeAccount.curLeftValue?c}元</span></li>
        <li><label for="" class="lab-title">卡片编号</label>
        <input type="text" value="" name="rechageCard.cardCode" class="order-text" validate="{required:true}" size="20" placeholder="卡片编号"/></li>
                <li><label for="" class="lab-title">卡号密码</label><input type="password" name="password" validate="{required:true}" size="20" class="order-text" placeholder="请输入卡号密码"/></li>
    </ul>
</div>
<div class="order-handle">
    <input type="submit" value="充值" class="btn order-btn"/>
</div>
</form>
 <#include "template/w/foot.ftl">
</body>
</html>