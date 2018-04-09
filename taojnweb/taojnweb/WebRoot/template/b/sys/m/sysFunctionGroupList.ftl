<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<#include "/template/m/common/utils.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
<div class="r_tit1">
	<ul>
		<li>模块功能组管理</li>
	</ul>
</div>
<div class="r_content" style="min-width:700px">
	<form action="${base}/manage/sys/sysFunctionGroup.htm" id="searchForm" method="get" >
		<div class="tool_search">
			名称：<input name="sysFunctions.funName" type="text" class="input_1" value="${(sysFunctions.funName)!}" size="12" />
			平台类型：<select name="sysFunctions.funAccType">
				<option value="">全部</option>				
				<option value="0" <#if sysFunctions.funAccType?exists && sysFunctions.funAccType=0>selected</#if>>系统管理</option>
				<option value="1" <#if sysFunctions.funAccType?exists && sysFunctions.funAccType=1>selected</#if>>散客</option>
			  </select>
			<input type="submit" class="btn_search" name="button" id="searchButton" value="查询"/>
			<span class="shux"></span>
			<input type="button" class="btn_search" name="button2" id="button2" onclick="location.href='${base}/manage/sys/sysFunctionGroup!add.htm'" value="新增" />
	   </div>
	</form>
 	<div class="tableNav">
	<#assign flag=0>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablelistNav">
		<tr>
		  <td width="50" align="center" class="table_title">序号</td>
		  <td width="200" align="center" class="table_title">名称</td>
		  <td width="100" align="center" class="table_title">平台类型</td>
		  <td width="90" align="center" class="table_title">类型</td>
		  <td width="150" align="center" class="table_title tit0">&nbsp;操作</td>
		</tr>
    	<#list pages.result as res>
			<#assign flag=1>
		    <#if res_index%2=1>
		    <tr class="tr_cur1">
		    <#else>
		    <tr class="tr_cur2">
		    </#if>
		      <td align="left">${res_index+1}</td>
		      <td align="left">${(res.funName)!}</td>
		      <td align="center"><#if res.funAccType=0>系统管理<#elseif res.funAccType=1>散客</#if></td>
		      <td align="center"><#if res.funType=0>组<#elseif res.funType=1>功能URL</#if></td>
		      <td align="center">
		      	<a href="${base}/manage/sys/sysFunction!edit.htm?id=${res.id}">编辑</a>
		      	&nbsp;<a href="${base}/manage/sys/sysFunctionGroup!groupConfig.htm?id=${res.id}">子模块管理</a>
		      	&nbsp;<a href="javascript:common.doPost('${base}/manage/sys/sysFunctionGroup!del.htm?id=${res.id}','确定要删除功能组吗?');">删除</a>
		      </td>
		    </tr>
		</#list>
		<#if flag=0>
		    <tr>
		      <td align="center" colSpan="6" class="table_title">无相关记录</td>
		    </tr>
    	</#if>
  </table>
</div>
 <@paginate pageCount=pages.totalPage currentPage=pages.thisPageNumber url=pageUrl></@paginate>
</div>
<div class="tool_2">
    <div class="tool_2_L"></div>
    <div class="tool_2_R"></div>
</div>
</div>
</div>
</div>
</body>
</html>