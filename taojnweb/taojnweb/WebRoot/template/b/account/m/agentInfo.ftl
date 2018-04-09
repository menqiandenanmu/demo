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
                <div class="page-item page-title">用户信息</div>
                <div class="page-item form-panel">
    <ul>
          <li><span class="lab-title">客户名称：</span>
          <div class="form-item">
	          ${agentInfo.corpName}
          </div>
          </li>
          <li><span class="lab-title">客户编号：</span>
          <div class="form-item">
	          ${agentInfo.code}
          </div>
          </li>
           <li><span class="lab-title">状态：</span>
           <div class="form-item">
			<#if accountLogin.status==0>
			      停用
			<#elseif accountLogin.status==1>
			      正常
			</#if>
          </div>
         </li>
          <li><span class="lab-title">钱包名称：</span>
          <div class="form-item">
			${agentInfo.bankName!("&nbsp;")}
          </div>
            </li>
          <li><span class="lab-title">联系方式：</span>
          <div class="form-item">
			${(accountLogin.mobile)!("&nbsp;")}
          </div>
            </li>
          <li><span class="lab-title">身份证：</span>
          <div class="form-item">
			${(accountLogin.idCard)!("&nbsp;")}
          </div>
            </li>
          <li><span class="lab-title">电子邮箱：</span>
          <div class="form-item">
			${(accountLogin.email)!("&nbsp;")}
          </div>
            </li>
          <li><span class="lab-title">性别：</span>
          <div class="form-item">
			<#if accountLogin.sex?exists>
			<#if accountLogin.sex==0>女
			<#else>男
			</#if>
			</#if>
          </div>
            </li>
          
          <li><span class="lab-title">备注信息：</span>
         	<div class="form-item">
			${agentInfo.remark!("&nbsp;")}
          </div>
          </li>
          </ul>
    </div>
</div>
<!--列表正文结束-->
</body>
</html>