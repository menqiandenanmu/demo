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
        <div class="page-item page-title">通知管理</div>
        <!--索搜区域-->
        <div class="page-item search-panel">
		<form action="${base}/manage/account/tradeNotify.htm" id="searchForm" method="post" >
 			<ul class="search-list clearfix">
            <li><span class="lab-title">流水号</span><span>
				<span><input type="text" name="tradeNotifyTask.serialNo" class="text text-small" value="${tradeNotifyTask.serialNo!}" />
			</span></li>
             <li><span class="lab-title">通知状态</span><span>
				<span>
				<select name="tradeNotifyTask.syncStatus"> 
				<option value="">未通知</option>
				<option value="success" <#if tradeNotifyTask.syncStatus?exists && tradeNotifyTask.syncStatus=="success">selected</#if>>通知成功</option>
				<option value="fail" <#if tradeNotifyTask.syncStatus?exists && tradeNotifyTask.syncStatus=="fail">selected</#if>>通知失败</option>
				</select>
			</span>
			</li>
            <li class="search-handle"><input type="submit" value="搜索" class="btn btn-small bg-blue"/></li>
        </ul>
		</form>
	 	 </div>
        <!--索搜区域结束-->
       <!--列表操作区域-->
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                     <th class="w10" width="80px;">序号</th>
					  <th class="w20" width="80px;">流水号</th>
					  <th class="w10" width="80px;">通知次数</th>
					  <th class="w10" width="80px;">通知状态</th>
					  <th class="w10" width="80px;">通知时间</th>
					  <th class="w20" width="280px;">操作</th>
					 </tr>
					</thead>
                <tbody>
	    	<#list resultPage.result as res>
				<#assign flag=1>
			    <tr  id="${res.id}">
			      <td align="center">${res_index+1}</td>
			      <td align="center">${res.serialNo?default("")}</td>
			      <td align="center">${res.syncNum!}</td>
			      <td align="center"><#if res.syncStatus=="">未通知<#elseif res.syncStatus=="success">通知成功<#elseif res.syncStatus=="fail">通知失败</#if></td>
			      <td align="center">${res.syncTime?string("yyyy-MM-dd")!}</td>
			      <td align="center">
		      		<#--&nbsp;<a href="javascript:common.doPost('${base}/manage/account/tradeNotify!del.htm?id=${res.id}','确定删除吗?');">删除</a>-->
		      		&nbsp;<a href="${base}/manage/account/notifyHistory.htm?id=${res.id}">通知记录</a>
		      		<#if res.syncStatus?exists>
			      		<#if  res.syncNum gte 3>
			      		&nbsp;<a href="javascript:common.doPost('${base}/manage/account/tradeNotify!sendAgain.htm?id=${res.id}','确定通知吗?');">再次通知</a>
			      		</#if>
		      		</#if>
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
 <@paginate pageCount=resultPage.totalPage currentPage=resultPage.thisPageNumber url=pageUrl></@paginate>

</div>
</div>
</div>
</body>
</html>