<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <meta content="email=no" name="format-detection" />
    <title>江南钱包找回密码</title>
    <#include "/template/w/common/head.ftl">
  <link rel="stylesheet" href="${base}/style/w/css/reset.css"/>
    <link rel="stylesheet" href="${base}/style/w/css/login.css"/>
    <script type="text/javascript" src="${base}/script/w/common.js"></script>
    <script type="text/javascript" src="${base}/script/w/findpassSendCode.js"></script>
    <script language="JavaScript">
    $(document).ready(function(){
   	var fm=new BackUrlFormNoConfirm("validateForm","${base}/index.htm");
    });
</script>
</head>
<body class="register-body">
<div class="register-panel passw-panel">
    <h3 class="register-icon">
        <i class="icon-suo"></i>
    </h3>
    <p class="top-info">输入你的手机号码，获取验证码 <br/>进行密码重置</p>
</div>
<form action="${base}/mobileResetPassword.htm" method="post"  id="validateForm">
    <ul class="register-form">
        <li>
            <div class="register-item">
                <span>+86</span>
                <input type="text" name="loginName" id="mobile2" value="" validate="{required:true,mobile:true}" size="13" placeholder="请输入手机号" class="register-text"/>
            </div>
        </li>
        <li>
            <div class="register-item register-card">
                <input type="text" name="loginPass" placeholder="验证码" class="register-text"/>
            </div>
            <input type="button"  onclick="sendCode2()" id="sendCodeBtn" class="btn card-btn" value="获取验证码"/>
        </li>
          <li>
            <div class="register-item">
                <input type="password" id="password" name="accountLogin.loginPass" validate="{required:true}" placeholder="设置密码" size="18" class="register-text"/>
            </div>
        </li>
        <li>
            <div class="register-item">
                <input type="password" name="loginPass1" validate="{required:true,equalTo:'#password'}" placeholder="重复密码" size="18" class="register-text"/>
            </div>
        </li>
    </ul>
    <div class="register-handle passw-handle">
        <input type="submit" class="btn register-btn" value="立即找回" />
    </div>
    <#if (actionErrors?exists && actionErrors?size > 0)>
					<ul>
						<#list actionErrors as error>
							<script>
			 					alert("${error}");
			 				</script>
						</#list>
					</ul>
				</#if>
</form>
</body>
</html>