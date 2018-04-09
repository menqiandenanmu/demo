<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>江南钱包账户管理平台</title>
<link href="${base}/style/m/css/reset.css" rel="stylesheet" type="text/css">
<link href="${base}/style/m/css/common.css" rel="stylesheet" type="text/css">
<link href="${base}/style/m/css/index.css" rel="stylesheet" type="text/css">
 <!--[if lte IE 8]>
    <link rel="stylesheet" type="text/css" href="${base}/style/m/css/ie8.css" />
    <![endif]-->
 <script src="${base}/scripts/jquery-1.8.3.min.js"></script>
<script src="${base}/scripts/echarts/echarts.js"></script>
<script src="${base}/scripts/index.js"></script>
</head>
<body>
<div class="header" id="header">
    <div class="header-main clearfix">
        <h1 class="logo"><a href="#"></a></h1>
        <ul class="main-nav">
        </ul>
        <span class="top-name"><i class="page-icon"></i><#if login?exists>${login.realname}</#if> (<#if account?exists>${account.accName}</#if>)，<#if greetings?exists>${greetings}</#if>  <a href="${base}/manage/index!panel.htm" target="panel" style="padding:0 7px 0 10px">首页</a><a href="${base}/manage/account/pass.htm" target="rightFrame">修改密码</a>&nbsp;&nbsp;&nbsp;<a href="${base}/manage/login!logout.htm" >注销</a></span>
    </div>
</div>
</body>
</html>