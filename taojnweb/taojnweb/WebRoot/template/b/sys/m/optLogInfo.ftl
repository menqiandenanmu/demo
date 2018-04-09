<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<link href="${base}/style/m/css/reset.css" rel="stylesheet" type="text/css">
<link href="${base}/style/m/css/common.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/page.css"/>
<!--[if lte IE 8]>
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/ie8.css" />
<![endif]-->
<script src="${base}/scripts/require.js" data-main="scripts/main"></script>
</head>
<body>
<!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">系统操作日志信息</div>
                <div class="page-item form-panel">
    <ul>
        <li><span class="lab-title">主题：</span>
            <div class="form-item">
               ${optLog.title!("&nbsp;")}
            </div>
        </li>
        <li><span class="lab-title">内容：</span>
            <div class="form-item">
                ${optLog.content!("&nbsp;")}
            </div>
        </li>
        <li><span class="lab-title">时间：</span>
            <div class="form-item">
             	${(optLog.logTime?string("yyyy-MM-dd:HH:mm:ss"))!("&nbsp;")}
            </div>
        </li>
         <li><span class="lab-title">操作类型：</span>
            <div class="form-item">
             	<#if optLog.optType==0>
             	系统日志
             	<#elseif optLog.optType==1>
             	操作员登录
             	</#if>
            </div>
        </li>
        <li><span class="lab-title">日志类型：</span>
            <div class="form-item">
             	<#if optLog.logType==0>
             	系统日志
             	<#elseif optLog.logType==1>
            	用户账户
             	<#else>
             	其他
             	</#if>
            </div>
        </li>
        <li><span class="lab-title">操作员：</span>
            <div class="form-item">
            	<#--当前操作员被删除时，详情中的操作员查询返回null-->
            	<#if accLogin?exists>
             		${accLogin.loginName!}
             	</#if>
            </div>
        </li>
          </ul>
    </div>
</div>
<!--列表正文结束-->
</body>
</html>
        