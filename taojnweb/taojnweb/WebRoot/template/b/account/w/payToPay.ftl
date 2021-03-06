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
    <title>转账</title>
    <link rel="stylesheet" href="${base}/style/w/css/reset.css"/>
    <link rel="stylesheet" href="${base}/style/w/css/order.css"/>  
    <script type="text/javascript" src="${base}/script/w/common.js"></script>
     <script language="JavaScript">
   $(document).ready(function(){
     	var fm=new BackMessageForm("validateForm","转账");
    });
</script>
</head>
<body class="order-body">
<form action="${base}/user/giroAccount.htm" method="post" id="validateForm">
<div class="order-item">
    <ul>
        <li><label for="" class="lab-title">转账用户</label><input type="text" name="accountName" validate="{required:true}" size="10" class="order-text" placeholder="转账账户"/></li>
        <li><label for="" class="lab-title">转账金额</label><input type="text" name="payNum" validate="{required:true}" size="10" class="order-text" placeholder="转账金额"/></li>
        <li><label for="" class="lab-title">交易密码</label><input type="password" name="password" validate="{required:true}" size="10" class="order-text" placeholder=""/></li>
    </ul>
</div>
<div class="order-handle">
    <input type="submit" value="确认转账" class="btn order-btn"/>
</div>
</form>
 <#include "template/w/foot.ftl">
</body>
</html>

