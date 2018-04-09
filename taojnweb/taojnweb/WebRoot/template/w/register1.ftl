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
    <script type="text/javascript" src="${base}/script/w/sendCode.js"></script>
    <script type="text/javascript" src="${base}/script/w/common.js"></script>
    <script language="JavaScript">
   $(document).ready(function(){
  		$("#validateForm").validate();
    });
</script>
</head>
<body class="register-body">
<div class="register-panel">
    <h3 class="register-icon">
        <i class="icon-tel"></i>
    </h3>
</div>
<form action="${base}/registerNext.htm" method="post"  id="validateForm">
    <ul class="register-form">
        <li>
            <div class="register-item">
                <span>+86</span>
                <input type="text" name="loginName" id="mobile2" value="" validate="{required:true,mobile:true}" size="13" placeholder="请输入手机号" class="register-text"/>
            </div>
        </li>
        <li><input type="text" name="checkCode" id="checkCode" class="login-text card-text" style="width:50%" placeholder="请输入验证码"/>&nbsp;&nbsp;&nbsp;&nbsp;<img id="checkCodeImg" src="${base}/checkCode.htm"  onclick="changeCode('${base}');" width="100" height="40"/></li>
        <li>
            <div class="register-item register-card">
                <input type="text" name="loginPass" placeholder="验证码" class="register-text"/>
            </div>
            <input type="button"  onclick="sendCode2()" id="sendCodeBtn" class="btn card-btn" value="获取验证码"/>
        </li>
    </ul>
    <div class="register-handle">
        <input type="submit" class="btn register-btn" value="下一步" />
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