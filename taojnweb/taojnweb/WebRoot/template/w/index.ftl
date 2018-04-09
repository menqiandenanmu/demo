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
    <link rel="stylesheet" href="${base}/style/w/css/index.css"/>
    <script type="text/javascript" src="${base}/script/w/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${base}/script/w/common.js"></script>
</head>
<body class="index-body">
<#assign menu="index">
<div class="banner">
    <img src="${(pageAreaDetail.showUrl)!}" alt=""/>
</div>
<#--div class="amount-panel">
   	<span style="font-size: 1.5rem;">余额:${tradeAccount.curLeftValue?c}元</span>
   	<span style="font-size: 1.5rem;">积分:${(pointAccountInfo.point)!"0"}</span>
</div-->
<div class="index-item">
    <ul>
        <li><label for="" class="lab-title">余额</label><span>${tradeAccount.curLeftValue?c}元</span></li>
        <li><label for="" class="lab-title">积分</label><span>${(pointAccountInfo.point)!"0"}</span></li>
        <li><label for="" class="lab-title">昵称</label><span>${tradeAccount.accountName}</span></li>
        <#--li><label for="" class="lab-title">钱包名称</label><span>${tradeAccount.tradeAccName!}</span></li>
        <li><label for="" class="lab-title">钱包状态</label><span><#if tradeAccount.status==0>已停用<#else>已激活</#if></span></li-->
    </ul>
</div>
 <#include "template/w/foot.ftl">
 <a href="${base}/user/userBarCode.htm" class="scan"><i class="icon-scan"></i><span class="scan1">扫码付</span></a>
</body>
</html>