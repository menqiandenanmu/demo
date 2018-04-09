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
        <#include "/template/w/common/head.ftl">
    <link rel="stylesheet" href="${base}/style/w/css/reset.css"/>
    <link rel="stylesheet" href="${base}/style/w/css/order.css"/>
    <script type="text/javascript" src="${base}/script/w/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${base}/script/w/common.js"></script>
</head>
<body class="order-body">
<#assign menu="userInfo">
<div class="user-list">
    <ul>
        <li>
            <a href="${base}/user/userInfo.htm">
                <span class="user-icon icon1"></span>我的信息<i class="arrow-icon"></i>
            </a>
        </li>
        <li>
            <a href="${base}/user/payToPay.htm">
                <span class="user-icon icon7"></span>转账<i class="arrow-icon"></i>
            </a>
        </li>
        <li>
            <a href="${base}/user/transInfo.htm">
                <span class="user-icon icon2"></span>消费记录<i class="arrow-icon"></i>
            </a>
        </li>
        <li>
            <a href="${base}/user/waccountCoupon.htm">
                <span class="user-icon icon4"></span>我的赠券<i class="arrow-icon"></i>
            </a>
        </li>
         <li>
            <a href="${base}/user/logPassword.htm">
                <span class="user-icon icon3"></span>修改密码<i class="arrow-icon"></i>
            </a>
        </li>
        <li>
            <a href="${base}/user/tranPassword.htm">
                <span class="user-icon icon3"></span>设置交易密码<i class="arrow-icon"></i>
            </a>
        </li>
        <li>
            <a  href="javascript:common.doPost('${base}/login!logout.htm','确定退出吗?');">
                <span class="user-icon icon1"></span>退出<i class="arrow-icon"></i>
            </a>
        </li>
    </ul>
</div>
 <#include "template/w/foot.ftl">
</body>
</html>