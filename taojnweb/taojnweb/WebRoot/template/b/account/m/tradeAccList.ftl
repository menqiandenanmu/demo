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
</head>
<body>
<!--列表正文开始-->
    <div class="page-main">
        <div class="page-item page-title">请选择账户</div>
        <!--索搜区域-->
        <div class="page-item search-panel">
		<form action="${base}/manage/account/mAgentAccount.htm" id="searchForm" method="post" >
 			<ul class="search-list clearfix">
            <li><span class="lab-title">账户类型</span><span>
			<select name="tradeAccount.tradeAccId" class="select1" >
						<option value="">全部</option>
						<#if accountList?exists>
						<#list accountList as account>
							<option value="${account.id}" >${account.accName}</option>
						</#list>
						</#if>
					</select>
			</span></li>
            <li class="search-handle"><input type="submit" value="搜索" class="btn btn-small bg-blue"/></li>
        </ul>
		</form>
	 	 </div>
        <!--索搜区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                    <th class="w10" width="80px;">序号</th>
					  <th class="w20" width="80px;">用户名称</th>
					  <th class="w10" width="80px;">账户类型</th>
					  <th class="w10" width="80px;">账户余额</th>
					  <th class="w10" width="80px;">授信额度</th>
					  <th class="w20" width="280px;">操作</th>
			 </tr>
					</thead>
                <tbody>
	    	<#list tradePages.result as res>
				<#assign flag=1>
			    <tr  id="${res.id}">
			      <td align="center">${res_index+1}</td>
			      <td align="center">${res.accountName?default("")}</td>
			      <td>
			     		<#list accountList as account>
			     			<#if account.id==res.tradeAccId>
							${account.accName}
							</#if>
						</#list>
					</td>
			      <td align="center"><#if res.curLeftValue gt 0 >${res.curLeftValue}<#else>0.00</#if></td>
			      <td align="center"><#if res.warnLine gt 0 >${res.warnLine}<#else>0.00</#if></td>
			      <td align="center">
			      	&nbsp;<a href="${base}/manage/account/dunning!add.htm?id=${res.id}">选择</a>
			      </td>
			    </tr>
			</#list>
			<#if flag=0>
			    <tr>
			      <td align="center" colspan="6" class="table_title">无相关记录</td>
			    </tr>
	    	</#if>
	  </tbody>
        </table>
 <div class="list-bottom clearfix">
<@paginate totalCount=tradePages.totalCount pageSize=tradePages.totalPage start=tradePages.thisPageNumber url=pageUrl>
			</@paginate>

</div>
</div>
</div>
</body>
</html>