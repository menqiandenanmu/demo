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
    <title>修改密码</title>
        <link rel="stylesheet" href="${base}/style/w/css/reset.css"/>
    <link rel="stylesheet" href="${base}/style/w/css/order.css"/>
    <script type="text/javascript" src="${base}/script/w/sendPassCode.js"></script>
    <script type="text/javascript" src="${base}/script/w/common.js"></script>
     <script language="JavaScript">
   $(document).ready(function(){
   	var fm=new BackUrlFormNoConfirm("validateForm","${base}/login.htm");
    });
</script>
</head>
<body class="order-body">
<#assign menu="userInfo">
<form action="${base}/user/index!doSavePassword.htm" id="validateForm" method="post"/>
<div class="order-item">
    <ul>
        <li><label for="" class="lab-title">动态码</label><input type="tel" name="accountLogin.loginPass" validate="{required:true}" size="4" class="order-text1" placeholder="动态码"/>
          <input type="button"  onclick="sendCode2()" id="sendCodeBtn" class="btn order-btn1" value="获取动态码"/>
        </li>
        <li><label for="" class="lab-title">新密码</label><input type="password" name="password" class="order-text" validate="{required:true}" size="18" placeholder="新密码"/></li>
        <li><label for="" class="lab-title">确认密码</label><input type="password" name="password1" class="order-text" validate="{required:true}" size="18" placeholder="确认密码"/></li>
    </ul>
</div>
<div class="order-handle">
    <input type="submit" value="确认修改" class="btn order-btn"/>
</div>
</form>
 <#include "template/w/foot.ftl">
</body>
</html>