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
<form action="${base}/user/waccountCoupon.htm" id="validateForm" method="get">
<div class="search-panel">
    <div class="search-form">
        <i class="search-icon"></i>
        <input type="text" name="accountCoupon.couponName" id="" class="search-text" placeholder="查询条件" value="${(accountCoupon.couponName)!""}"/>
    </div>
    <input type="submit" value="查询" class="search-btn"/>
</div>
</form>
<ul class="bill-list">
<#if page?? && page.result.size() gt 0>
<#list page.result as res>
    <li>
   
	<p class="bill-list-explian" style="margin-top: 5px;">
        	赠券名称：
        	<span >${res.couponName!""}</span>
    </p>  
    <p class="bill-list-explian" style="margin-top: 10px;">
	<span >赠券价值：</span>
       <span> ${res.price?c}￥
        </span>
    </p> 
    <#if res.couponStatus==0> 
    <p class="bill-list-explian" style="float: right;" style="margin-top: 10px;">
    <span>
       <button class="order-btn" style="border-radius: 3px; margin: -30px 1px 10px;     line-height: 1.5rem; border: none;">
       <a style="color:white;text-decoration:none;" href="${base}/user/couponPay.htm?id=${res.id}" >去支付</a></button></span>
    </p>
    </#if>
	<p class="bill-list-explian" style="margin-top: 10px;">
	<span >赠券时间：</span>
       <span> ${res.createTime?string("yyyy-MM-dd hh:mm:ss")}
        </span>
    </p>
	<p class="bill-list-explian" style="margin-top: 10px;">
	<span >失效时间：</span>
       <span> ${res.expiratDate?string("yyyy-MM-dd")}
        </span>
    </p>
    
    <p class="bill-list-explian" style="margin-top: 10px;">
	<span >赠券状态：</span>
       <span> <#if res.couponStatus==0>未消费
			<#elseif res.couponStatus==1>已消费(${res.remark})
			<#elseif res.couponStatus==2>已失效
			</#if>
        </span>
    </p>
    <p class="bill-list-explian" style="margin-top: 10px;">
	<span >赠券说明：${res.remark2!}</span>
       <span>
        </span>
    </p>
    </li>
    </#list>
  </#if>
<div id="footdiv" style="height: 150px;"><div>
</ul>
 <#include "template/w/foot.ftl">
</body>
</html>