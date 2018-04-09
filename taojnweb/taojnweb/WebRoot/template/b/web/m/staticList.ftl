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
		<li>静态信息内容管理</li>
	</ul>
</div>
<div class="r_content">
	<form action="${base}/manage/web/static.htm" id="searchForm" method="get" >
		<div class="tool_search">
			名称：<input name="info.name" type="text" class="input_1" value="${(info.name)!}" size="12" />
			类别：<select name="info.classCode">
				<option value="">--全部--</option>
				<#list dictList as dictIndex>
				<option value="${dictIndex.dictDetailCode}" <#if ((info.classCode)!)=dictIndex.dictDetailCode>selected</#if>>${dictIndex.dictDetailName}</option>				
				</#list>
				</select>
			<input type="submit" class="btn_search" id="searchButton" value="查询"/>
			<span class="shux"></span>
			<input type="button" class="btn_search" id="button2" onclick="location.href='${base}/manage/web/static!add.htm'" value="新增" />
		</div>
	</form>
 	<div class="tableNav">
	<#assign flag=0>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablelistNav">
		<tr>
		  <td width="50" align="left" class="table_title">序号</td>
		  <td width="100" align="center" class="table_title">分类</td>
		  <td  align="center" class="table_title">名称</td>
		  <td width="100" align="center" class="table_title">关键字</td>
		  <td width="100" align="center" class="table_title tit0">&nbsp;操作</td>
		</tr>
    	<#list staticPage.result as res>
		    <#if res_index%2=1>
		    <tr class="tr_cur1">
		    <#else>
		    <tr class="tr_cur2">
		    </#if>
		      <td align="left">${res_index+1}</td>
		      <td align="left">${res.className?default("")}</td>
		      <td align="left">${res.name?default("")}</td>
		      <td align="left">${res.keystr?default("")}</td>
		      <td align="left">
		      	&nbsp;<a href="${base}/manage/web/static!edit.htm?id=${res.id}">编辑</a>
		      	&nbsp;<a href="javascript:common.doPost('${base}/manage/web/static!del.htm?id=${res.id}','确定要删除吗?');">删除</a>
		      </td>
		    </tr>
		</#list>
		<#if staticPage.result?size=0>
		    <tr>
		      <td align="center" colSpan="9" class="table_title">无相关记录</td>
		    </tr>
    	</#if>
  </table>
</div>
<@paginate pageCount=staticPage.totalPage currentPage=staticPage.thisPageNumber url=pageUrl>
</@paginate>

</div>
<div class="tool_2">
    <div class="tool_2_L"></div>
    <div class="tool_2_R"></div>
</div>
</div>
</div>
</body>
</html>