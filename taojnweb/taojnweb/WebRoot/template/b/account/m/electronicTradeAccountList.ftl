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
<script src="${base}/scripts/dialog.js"></script>
<!--[if lte IE 8]>
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/ie8.css" />
<![endif]-->
</head>
<body>
<!--列表正文开始-->
    <div class="page-main">
        <div class="page-item page-title">客户列表</div>
        <!--索搜区域-->
        <div class="page-item search-panel">
		<form action="${base}/manage/account/electronicCoup!tradeAccount.htm" id="searchForm" method="post" >
			 <ul class="search-list clearfix">
            <li><span class="lab-title">客户名称</span><span>
            <input type="text" name="tradeAccount.corpName" value="${tradeAccount.corpName!}" class="text text-small" /> 
			</span></li>
			 <li><span class="lab-title">帐号名称</span><span>
            <input type="text" name="tradeAccount.accountName" value="${tradeAccount.accountName!}" class="text text-small" /> 
			</span></li>
            <li class="search-handle"><input type="submit" value="搜索" class="btn btn-small bg-blue"/></li>
        </ul>
		</form>
	 	 </div>
        <!--索搜区域结束-->
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
            <table class="list-table" id="listTable" style="width:900px;">
                <thead>
                    <tr>
                    <th  width="30px;">序号</th>
                    <th width="50px;">客户名称</th>
                    <th  width="50px;">账户名称</th>
                    <th  width="50px;">钱包名称</th>
                     <th  width="30px;">操作</th>
			 		</tr>
					</thead>
                <tbody>
			<#if tradeAccountPages.result??&&tradeAccountPages.result.size() gt 0>
	    	<#list tradeAccountPages.result as res>
				<#assign flag=1>
			    <tr id="${res.id}">
			      <td align="center">${res_index+1}</td>
			      <td align="left">${res.corpName}</td>
			      <td align="left">${res.accountName}</td>
			      <td align="left">${res.tradeAccName}</td>
			      <td align="left"><a href="javascript:void(0);" onclick="dialog.closeMe({corpName:'${res.corpName!}',accountId:'${res.accountId}',accountName:'${res.accountName!}'});">选择</a></td>
			    </tr>
			</#list>
			<#else>
			    <tr>
			      <td align="center" colspan="6" class="table_title">无相关记录</td>
			    </tr>
			</#if>	
	  </tbody>
        </table>
 <div class="list-bottom clearfix">
 <@paginate pageCount=tradeAccountPages.totalPage currentPage=tradeAccountPages.thisPageNumber url=pageUrl></@paginate>
</div>
</div>
</div>
</body>
</html>