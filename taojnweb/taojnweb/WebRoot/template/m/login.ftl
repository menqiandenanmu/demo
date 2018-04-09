
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>江南钱包账户管理系统</title>
<#include "/template/m/common/head.ftl">
 <link rel="stylesheet" type="text/css" href="${base}/style/m/css/reset1.css"/>
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/login1.css"/>
<script src="${base}/script/m/login/login.js" type="text/javascript"></script>
<body>
<div class="header">
    <div class="w950 clearfix login-top">
        <h1 class="logo">江南钱包管理系统</h1>
        <span>欢迎登录！</span>
    </div>
</div>
<div class="content">
    <div class="w950 clearfix">
        <div class="login-img"></div>
        <div class="login-form clearfix">
            <div class="login-left"></div>
            <div class="form-panel">
                <h3 class="login-title">管理员登录</h3>
               <form method="post" action="${base}/manage/login!login.htm" onSubmit="return checkForm(this);">
                    <ul>
                        <li><input type="text" placeholder="用户名" name="loginName" id="loginName" class="login-text"/></li>
                        <li><input type="password" placeholder="密码" type="password" name="loginPass"  id="loginPass" class="login-text"/></li>
                        <li><input type="text" class="login-text card-text"  name="checkCode" /> <span class="card-img">
                         <img  id="checkCodeImg"src="${base}/manage/checkCode.htm"  onclick="changeCode();"/>
                       </span><a <a href="#" onclick="changeCode('${base}');" class="card-handle">换一张</a></li>
                        <li class="form-handle"><input type="submit" value="登录" class="login-btn"/></li>
                    </ul>
                </form>
            </div>
            <div class="login-right"></div>
        </div>
    </div>
</div>
 </body>
</html>