<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <meta content="email=no" name="format-detection" />
    <title>江南钱包注册</title>
    <#include "/template/w/common/head.ftl">
  <link rel="stylesheet" href="${base}/style/w/css/reset.css"/>
    <link rel="stylesheet" href="${base}/style/w/css/login.css"/>
    <script language="JavaScript">
   $(document).ready(function(){
   	var fm=new BackUrlFormNoConfirm("validateForm","${base}/index.htm");
    });
</script>
</head>
<body class="register-body">
<div class="register-panel">
    <h3 class="register-icon">
        <i class="icon-text"></i>
    </h3>
</div>
<form action="${base}/doReg.htm" method="post" id="validateForm">
<@s.token />
<input type="hidden" value="${loginName!}" name="accountLogin.loginName" />
<input type="hidden" value="${loginName!}" name="accountLogin.mobile" />
<#--input type="hidden" value="${loginName!}" name="accountLogin.loginName" />
<input type="hidden" value="${loginName!}" name="accountLogin.mobile" /-->
    <ul class="register-form">
        <li>
            <div class="register-item">
                <span>姓名</span>
                <input type="text" name="agentInfo.corpName" validate="{required:true}" value="" size="18" class="register-text"/>
                <input type="hidden" name="agentInfo.bankName" validate="{required:true}"  size="18" value="江南钱包" class="register-text"/>
            </div>
        </li>
        <li>
            <div class="register-item">
                <span>身份证</span>
                <input type="text" name="accountLogin.idCard" validate="{required:true,idcardno:true}"  size="18" value="" class="register-text"/>
            </div>
        </li>
         <li>
            <div class="register-item">
                <span>电子邮箱</span>
                <input type="text" name="accountLogin.email" validate="{required:true,email:true}"  size="18" value="" class="register-text"/>
            </div>
        </li>
        <li>
            <div class="register-item">
                <span>性别</span>
                <input name="accountLogin.sex"  id="radio" value="0" size="18"  type="radio" />女
				<input name="accountLogin.sex"   id="radio" value="1" size="18"  type="radio"  checked="true"/>男
            </div>
        </li>
        <li>
            <div class="register-item">
                <span>交易密码</span>
                <input type="password" value="" name="agentInfo.address" validate="{required:true}" size="18" class="register-text" />
            </div>
        </li>
        <li>
            <div class="register-item">
                <span>登录密码</span>
                <input type="password" name="accountLogin.loginPass" value="" validate="{required:true}" size="18" class="register-text" />
            </div>
        </li>
    </ul>
    <div class="register-handle register2-handle">
        <#--a href="#" class="btn register-btn">完成</a-->
        <input type="submit" class="btn register-btn" value="完成" />
    </div>
</form>
</body>
</html>