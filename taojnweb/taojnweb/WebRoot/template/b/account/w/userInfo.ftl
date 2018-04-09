<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <meta content="email=no" name="format-detection" />
    <title>个人信息</title>
    <#include "/template/w/common/head.ftl">
        <link rel="stylesheet" href="${base}/style/w/css/reset.css"/>
    <link rel="stylesheet" href="${base}/style/w/css/order.css"/>
    <script type="text/javascript" src="${base}/script/w/common.js"></script>
    <script language="JavaScript">
   $(document).ready(function(){
   	var fm=new BackUrlFormNoConfirm("validateForm","${base}/user/index.htm");
    });
</script>
</head>
<body class="order-body">
<#assign menu="userInfo">
<form action="${base}/user/index!userUpadte.htm" method="post" id="validateForm">
<div class="order-item">
    <ul>
        <li><label for="" class="lab-title">用户名</label><span>${tradeAccount.accountName!}</span></li>
        <li><label for="" class="lab-title">昵称</label><input type="text" value="${accountLogin.realname!}" name="accountLogin.realname" validate="{required:true}" size="10" class="order-text" /></li>
        <li><label for="" class="lab-title">邮箱</label><input type="text" value="${accountLogin.email!}" name="accountLogin.email" validate="{required:true,email:true}" size="20" class="order-text" /></li>
        <li><label for="" class="lab-title">身份证</label><input type="text" value="${accountLogin.idCard!}" name="accountLogin.idCard" validate="{required:true,idcardno:true}" size="20" class="order-text" />
        <input type="hidden" value="${accountLogin.mobile!}" name="accountLogin.mobile" />
        </li>
        <#--li><label for="" class="lab-title">联系方式</label><input type="text" value="${accountLogin.mobile!}" name="accountLogin.mobile" validate="{required:true,mobile:true}" size="11" class="order-text" /></li-->
        <li><label for="" class="lab-title">钱包名称</label><input type="text" value="${tradeAccount.tradeAccName!}" name="tradeAccount.tradeAccName" validate="{required:true}" size="10" class="order-text" /></li>
    </ul>
</div>
<div class="order-handle">
    <input type="submit" value="确认修改" class="btn order-btn"/>
</div>
</form>
 <#include "template/w/foot.ftl">
</body>
</html>