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
 <#include "/template/m/common/head.ftl">
<script src="${base}/scripts/echarts/echarts.js"></script>
<script src="${base}/scripts/index.js"></script>

</head>
<body>
    <dl class="side-nav">
    <#if funcs?exists> 
			<#list funcs as funIndex>
				<#if funIndex.funType==0>
				 	<dt class="main-menu"><i <#if funIndex_index==0>class="page-icon icon-system"<#elseif funIndex_index==1>class="page-icon icon-enterprise"<#else>class="page-icon icon-invoice"</#if>></i>${funIndex.funName}</dt>
		        </#if>
				<#list funIndex.childs as funItem>
				 <dd class="nav-header"><a href="${base}/${funItem.funUrl}" target="panel" id="${funItem.id}" data-id="${funItem.id}" >${funItem.funName}</a></dd>
				</#list>
			</#list>
	</#if>
    </dl>
</body>
</html>