<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<#include "/template/m/common/utils.ftl">
<link href="${base}/script/datepicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
<link href="${base}/style/m/css/reset.css" rel="stylesheet" type="text/css">
<link href="${base}/style/m/css/common.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/page.css"/>
<!--[if lte IE 8]>
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/ie8.css" />
<![endif]-->
<script src="${base}/scripts/require.js" data-main="scripts/main"></script>
<script language="JavaScript" type="text/javascript" src="${base}/script/b/account/m/agent.js"></script>
</head>
<body>
<!--列表正文开始-->
    <div class="page-main">
        <div class="page-item page-title">交易账户类型管理</div>
        <!--索搜区域-->
        <div class="page-item search-panel">
		<form action="${base}/manage/account/tradeAccountLimit.htm" id="searchForm" method="post" >
		 <ul class="search-list clearfix">
            <li><span class="lab-title">供应商名称</span><span><input maxlength="15" name="tradeAccountLimit.supplyName" type="text" class="text text-small" value="${tradeAccountLimit.supplyName?default("")}" size="12"/></span></li>
            <li class="search-handle"><input type="submit" value="搜索" class="btn btn-small bg-blue"/></li>
        </ul>
		<input type="hidden" value="${tradeAccountLimit.tradeAccountId}" name="tradeAccountLimit.tradeAccountId"/>
		</form>
	  </div>
        <!--索搜区域结束-->
        <!--列表操作区域-->
        <div class="page-item list-handle">
        </div>
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                    <th class="w10" width="80px;">序号</th>
                    <th class="w10" width="80px;">供应商名称</th>
                    <th class="w10" width="80px;">是否有效</th>
                    <th class="w10" width="80px;">操作</th>
                    
			 </tr>
					</thead>
                <tbody>
			<#if resultPage.result??&&resultPage.result?size gt 0>
	    	<#list resultPage.result as res>
				<#assign flag=1>
			    <tr  id="${res.id}">
			      <td align="center">${res_index+1}</td>
			      <td align="left">${res.supplyName}</td>
			      <td align="left"><#if res.useFlag==true>是<#else>否</#if></td>
			      <td align="center">
			      	&nbsp;<a href="${base}/manage/account/tradeAccountLimit!auth.htm?id=${res.id}&tradeAccountLimit.useFlag=${res.useFlag}">
						<#if res.useFlag==true>失效<#else>生效</#if>
						</a>
			      </td>
			    </tr>
			</#list>
			<#else>
			    <tr>
			      <td align="center" colspan="4" class="table_title">无相关记录</td>
			    </tr>
			</#if>	
	  	</tbody>
        </table>
 <div class="list-bottom clearfix">
 <@paginate pageCount=resultPage.totalPage currentPage=resultPage.thisPageNumber url=pageUrl></@paginate>
</div>
</div>
</div>
</body>
</html>
</body>
</html>