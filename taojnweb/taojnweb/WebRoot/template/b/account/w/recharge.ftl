<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <meta content="email=no" name="format-detection" />
    <title>我的钱包</title>
    <link rel="stylesheet" href="${base}/style/w/css/reset.css"/>
    <link rel="stylesheet" href="${base}/style/w/css/order.css"/>
    <script type="text/javascript" src="${base}/script/w/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${base}/script/w/common.js"></script>
</head>
<body class="order-body">
<#assign menu="recharge">
<div class="user-list">
    <ul>
        <li>
            <a href="${base}/user/alipayRecharge.htm">
                <span class="user-icon icon4"></span>在线充值<i class="arrow-icon"></i>
            </a>
        </li>
        <li>
            <a href="${base}/user/rechargeCard.htm">
                <span class="user-icon icon5"></span>充值卡充值<i class="arrow-icon"></i>
            </a>
        </li>
      
    </ul>
</div>
 <#include "template/w/foot.ftl">
</body>
</html>