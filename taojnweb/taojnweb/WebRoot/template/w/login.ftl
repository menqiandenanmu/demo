<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<meta content="email=no" name="format-detection" />
<title>江南钱包登录</title>
  <link rel="stylesheet" href="${base}/style/w/css/reset.css"/>
<link rel="stylesheet" href="${base}/style/w/css/login.css"/>
<script type="text/javascript" src="${base}/script/w/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${base}/script/w/common.js"></script>
<script src="${base}/script/b/account/w/login.js"></script>
</head>
<body class="logo-body">
<div class="logo-panel">
    <h1 class="logo"></h1>
</div>
<form action="${base}/login!login.htm" method="post">
    <ul class="login-form">
        <li><input type="text" name="loginName" value="" class="login-text" placeholder="请输入用户名"/></li>
        <li><input type="password" name="loginPass" value="" class="login-text" placeholder="请输入密码"/></li>
        <li><input type="text" name="checkCode" class="login-text card-text"  placeholder="请输入验证码"/><img id="checkCodeImg" src="${base}/checkCode.htm"  onclick="changeCode('${base}');" width="74" height="26"/></li>
    </ul>
    <div class="login-link">
        <a href="${base}/toFindByMobile.htm">忘记密码？</a>
    </div>
    <div class="login-handle">
        <input type="submit" value="登录" class="btn2 login-btn"/>
        <input type="button" onclick="location.href='${base}/toCodeReg.htm'" value="注册" class="btn2 login-btn"/>
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
     
    <#--p class="login-bottom">
        <a href="${base}/toCodeReg.htm">注册</a>
    </p-->
  
</body>
</html>