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
		<li>角色管理</li>
	</ul>
</div>
<div class="r_content" style="min-width:700px">
	<form action="${base}/manage/sys/roleAdmin.htm" id="searchForm" method="get" >
		<div class="tool_search">
			名称：<input name="role.roleName" type="text" class="input_1" value="${role.roleName?default("")}" size="12" />
			平台：<select name="role.roleAccType">
					<option value=""  <#if (role.roleAccType!-1) = -1>selected</#if>>全部</option>
					<option value="0" <#if (role.roleAccType!-1) = 0>selected</#if>>管理平台</option>
					<option value="1" <#if (role.roleAccType!-1) = 1>selected</#if>>散客平台</option>
				</select>
			<input type="submit" class="btn_search" name="button" id="searchButton" value="查询"/>
			<span class="shux"></span>
			<input type="button" class="btn_search" name="button2" id="button2" onclick="location.href='${base}/manage/sys/roleAdmin!add.htm'" value="新增" />
	   </div>
	</form>
 	<div class="tableNav">
	<#assign flag=0>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablelistNav">
		<tr>
		  <td width="50px" align="left" class="table_title">序号</td>
		  <td width="80px" align="left" class="table_title">名称</td>
		  <td width="80px" align="left" class="table_title">类型</td>
		  <td width="80px" align="left" class="table_title">状态</td>
		  <td width="180px" align="left" class="table_title">备注信息</td>
		  <td width="100" align="left" class="table_title tit0">&nbsp;操作</td>
		</tr>
    	<#list rolePage.result as res>
			<#assign flag=1>
		    <#if res_index%2=1>
		    <tr class="tr_cur1">
		    <#else>
		    <tr class="tr_cur2">
		    </#if>
		      <td align="left">${res_index+1}</td>
		      <td align="left">${res.roleName?default("")}</td>
		      <td align="left"><#if res.roleAccType=0>管理平台<#elseif res.roleAccType=1>散客</#if></td>
		      <td align="left">${res.useFlag?string("使用","停用")}</td>
		      <td align="left">${res.remark?default("")}</td>
		      <td align="left">
		      	&nbsp;<a href="${base}/manage/sys/roleAdmin!edit.htm?id=${res.id}">编辑</a>
		      	<#if res.id!=0>&nbsp;<a href="${base}/manage/sys/roleAdmin!infoEdit.htm?id=${res.id}">权限</a>
		      	&nbsp;<a href="javascript:common.doPost('${base}/manage/sys/roleAdmin!del.htm?id=${res.id}','确定要删除角色吗?');">删除</a></#if>
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
 <@paginate pageCount=rolePage.totalPage currentPage=rolePage.thisPageNumber url=pageUrl></@paginate>
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