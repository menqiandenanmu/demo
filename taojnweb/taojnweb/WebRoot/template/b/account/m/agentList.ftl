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
<script language="JavaScript" type="text/javascript" src="${base}/script/b/account/m/operator.js"></script>
</head>
<body>
<!--列表正文开始-->
    <div class="page-main">
        <div class="page-item page-title">客户管理</div>
        <!--索搜区域-->
		<form action="${base}/manage/account/agent.htm" id="searchForm" method="get" >
        <div class="page-item search-panel">
			
			 <ul class="search-list clearfix">
            <li><span class="lab-title">客户名称</span><span><input maxlength="30" name="agentExtendInfo.corpName" type="text" class="text text-small" value="${agentExtendInfo.corpName?default("")}" size="12"/></span></li>
            <li><span class="lab-title">客户编号</span><span><input maxlength="30" name="agentExtendInfo.code" type="text" class="text text-small" value="${(agentExtendInfo.code)!}" size="30"/></span></li>
            <li><span class="lab-title">联系方式</span><span><input maxlength="15" name="agentExtendInfo.linkmanTel" type="text" class="text text-small" value="${(agentExtendInfo.linkmanTel)!}" size="12"/></span></li>
            <li><span class="lab-title">钱包名称</span><span><input maxlength="30" name="agentExtendInfo.bankName" type="text" class="text text-small" value="${(agentExtendInfo.bankName)!}" size="20"/></span></li>
            <li><span class="lab-title">客户状态</span><span>
            <select name="agentExtendInfo.accStatus" style="width:180px;">
            <option value="">全部</option>
            <option value="1" <#if agentExtendInfo.accStatus?exists&&agentExtendInfo.accStatus==1>selected</#if>>启用</option>
            <option value="0" <#if agentExtendInfo.accStatus?exists&&agentExtendInfo.accStatus==0>selected</#if>>停用</option>
            </select>
            </li>
              <li><span class="lab-title">客户来源</span><span>
            <select name="agentExtendInfo.url" style="width:180px;">
            <option value="">全部</option>
            <#list dictDetails as dict>
            <option value="${dict.dictDetailCode}" <#if agentExtendInfo.url?exists&&agentExtendInfo.url==dict.dictDetailCode>selected</#if>>${dict.dictDetailValue}</option>
           </#list>
            </select>
            </li>
        </ul>
	 	<div class="tableNav">
		 </div>
        <!--索搜区域结束-->
        <!--列表操作区域-->
        <div class="page-item list-handle">
        
        <input type="submit" value="搜索" class="btn btn-small bg-blue"/>&nbsp;&nbsp;&nbsp;&nbsp;
        				<input type="button" class="btn btn-small bg-blue" onClick="location.href='${base}/manage/account/agent!agentExport.htm?${queryString}'" value="导出">&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:;" onclick="location.href='${base}/manage/account/agent!add.htm'" class="btn btn-small bg-blue" id="add-btn">+新增用户</a>
        </div>
		</form>
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                    <th class="w5" width="80px;">序号</th>
                    <th class="w10" width="80px;">客户名称</th>
                    <th class="w5" width="80px;">客户来源</th>
                    <th class="w10" width="80px;">客户编号</th>
                    <th class="w5" width="80px;">钱包名称</th>
                    <th class="w10" width="80px;">联系方式</th>
                    <th class="w5" width="80px;">客户状态</th>
                    <th class="w10" width="140px;">操作</th>
			 </tr>
					</thead>
                <tbody>
	    	<#list pages.result as res>
				<#assign flag=1>
			    <tr id="${res.id}">
			      <td align="center">${res_index+1}</td>
			      <td align="left">${res.corpName?default("")}</td>
			      <td align="left">${res.fax?default("")}</td>
			      <td align="left">${res.code?default("")}</td>
			      <td align="left">${res.bankName!}</td>
			      <td align="left">${res.linkmanTel!}</td>
				  <td align="center">
			      	<#if res.accStatus==0>
			      		停用
			      	<#elseif res.accStatus==1>
			      		启用
			      	</#if>
				  </td>
			      <td align="center">
			      	<a href="${base}/manage/account/agent!info.htm?id=${res.id}">详情</a>
			      	&nbsp;<a href="${base}/manage/account/agent!edit.htm?id=${res.id}">编辑</a>
			      	&nbsp;<a href="${base}/manage/account/mAgentAccount.htm?tradeAccountVo.corpName=${res.corpName}">账户</a>
			      	<#if res.accStatus==0>
			      		&nbsp<a href="javascript:common.doPost('${base}/manage/login!changeAccStatus.htm?id=${res.id}&accStatus=1','确定启用吗?');">启用</a>
			      	<#elseif res.accStatus==1>
			      		&nbsp<a href="javascript:common.doPost('${base}/manage/login!changeAccStatus.htm?id=${res.id}&accStatus=0','确定停用吗?');">停用</a>
			      	</#if>
			      </td>
			    </tr>
			</#list>
			<#if flag=0>
			    <tr>
			      <td align="center" colspan="7" class="table_title">无相关记录</td>
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