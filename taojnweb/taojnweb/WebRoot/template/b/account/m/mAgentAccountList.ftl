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
        <div class="page-item page-title">账户管理</div>
        <!--索搜区域-->
		<form action="${base}/manage/account/mAgentAccount.htm" id="searchForm" method="get" >
        <div class="page-item search-panel">
 			<ul class="search-list clearfix">
 			 <li><span class="lab-title">客户名称</span><span><input maxlength="30" name="tradeAccountVo.corpName" type="text" class="text text-small" value="${tradeAccountVo.corpName?default("")}" size="12"/></span></li>
            <li><span class="lab-title">账户名称</span><span><input maxlength="30" name="tradeAccountVo.accountName" type="text" class="text text-small" value="${(tradeAccountVo.accountName)!}" size="12"/></span></li>
            <li><span class="lab-title">钱包名称</span><span><input maxlength="30" name="tradeAccountVo.tradeAccName" type="text" class="text text-small" value="${(tradeAccountVo.tradeAccName)!}" size="12"/></span></li>
            <li><span class="lab-title">状态</span><span>
				<select name="tradeAccountVo.status" class="select1" style="width:180px;">
						<option value="">全部</option>
						<option value="1" <#if tradeAccountVo.status?exists && tradeAccountVo.status=="true">selected</#if>>生效</option>
						<option value="0" <#if tradeAccountVo.status?exists && !(tradeAccountVo.status=="true")>selected</#if>>失效</option>
						
					</select>
			 </span></li>
        </ul>
		
	 	 </div>
        <!--索搜区域结束-->
       <!--列表操作区域-->
        <div class="page-item list-handle">
            <input type="submit" value="搜索" class="btn btn-small bg-blue"/>&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" class="btn btn-small bg-blue" onClick="location.href='${base}/manage/account/mAgentAccount!agentAccountExport.htm?${queryString}'" value="导出">
        </div>
        </form>
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                    <th class="w10" width="80px;">序号</th>
					  <th class="w20" width="80px;">客户名称</th>
					  <th class="w20" width="80px;">客户来源</th>
					  <th class="w20" width="80px;">账户名称</th>
					  <th class="w10" width="80px;">钱包名称</th>
					  <th class="w10" width="80px;">账户余额</th>
					  <th class="w20" width="280px;">操作</th>
			 </tr>
					</thead>
                <tbody>
	    	<#list pages.result as res>
				<#assign flag=1>
			    <tr id="${res.id}">
			      <td align="center">${res_index+1}</td>
			      <td align="center">${res.corpName?default("")}</td>
			      <td align="center">${res.accResource?default("")}</td>
			      <td align="center">${res.accountName?default("")}</td>
			      <td>
			     		${res.tradeAccName!}
			    </td>
			      <td align="center"><#if res.curLeftValue gt 0 >${res.curLeftValue}<#else>0.00</#if></td>
			      <#-->td align="center"><#if res.warnLine gt 0 >${res.warnLine}<#else>0.00</#if></td-->
			      <td align="center">
			      	<#--&nbsp;<a href="${base}/manage/account/mAgentAccount!artificialCharge.htm?id=${res.id}">充值</a>-->
			      	&nbsp;<a href="${base}/manage/account/mAgentAccount!useFlagModify.htm?id=${res.id}">
							<#if res.status==true>失效<#else>生效</#if>		
							</a>
					 &nbsp;<a href="${base}/manage/point/userPoint.htm?pointAccountInfo.accName=${res.accountName}">积分</a>
			      </td>
			    </tr>
			</#list>
			<#if flag=1>
				 <tr>
				 	  <td align="center" class="table_title"></td>
				 	  <td align="center" class="table_title"></td>
				 	  <td align="center" class="table_title"></td>
				 	  <td align="center" class="table_title"></td>
				 	  <td align="center" class="table_title"></td>
				      <td align="center" class="table_title">合计:${totalSum?c}</td>
				      <td align="center" class="table_title"></td>
			    </tr>
		    </#if>
			<#if flag=0>
			    <tr>
			      <td align="center" colspan="6" class="table_title">无相关记录</td>
			    </tr>
	    	</#if>
	  </tbody>
        </table>
 <div class="list-bottom clearfix">
 <@paginate pageCount=pages.totalPage currentPage=pages.thisPageNumber url=pageUrl></@paginate>

</div>
</div>
</div>
</body>
</html>