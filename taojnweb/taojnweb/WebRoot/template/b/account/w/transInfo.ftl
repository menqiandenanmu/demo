<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <meta content="email=no" name="format-detection" />
    <title>江南钱包</title>
      <link rel="stylesheet" href="${base}/style/w/css/reset.css"/>
    <link rel="stylesheet" href="${base}/style/w/css/order.css"/>
    <script type="text/javascript" src="${base}/script/w/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${base}/script/w/common.js"></script>
</head>
<body class="order-body">
<#assign menu="transInfo">
<form action="${base}/user/transInfo.htm" id="validateForm" method="get">
<div class="search-panel">
    <div class="search-form">
        <i class="search-icon"></i>
        <input type="text" name="tradeAccountDetail.remark" id="" class="search-text" placeholder="查询条件" value="${tradeAccountDetail.remark!}"/>
    </div>
    <input type="submit" value="查询" class="search-btn"/>
</div>
</form>
<ul class="bill-list">
<#list page.result as res>
    <li>
        <h4 class="bill-list-title">
<#if res.opType==0>充值
<#elseif res.opType==1>消费
<#elseif res.opType==2>退款
<#elseif res.opType==3>充值卡充值
<#elseif res.opType==4>电子券充值
<#elseif res.opType==5>管理员充值
</#if>
(${res.remark})
</h4>   
<p class="bill-list-explian">
            ${res.createTime?string("yyyy-MM-dd HH:mm:ss")}
            <span class="bill-money">${res.opValue?c}</span>
        </p>
  		<p class="bill-list-explian" style="margin-top: 10px;">
        	交易号
        	<span class="bill-money" style="float: right;">${res.remark1!""}</span>
        </p>
        
       
    </li>
    </#list>
  
</ul>
<div id="footdiv" style="height: 150px;"><div>
 <#include "template/w/foot.ftl">
</body>
</html>