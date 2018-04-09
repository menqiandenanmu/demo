<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<#include "/template/m/common/utils.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
</head>
<div>
<div class="r_ContentNav">
<div class="r_tit1">
	<ul>
		<li>资讯信息列表</li>
	</ul>
</div>
<div class="r_content">
	<form action="${base}/manage/web/news.htm" id="searchForm" method="get" >
		<div class="tool_search">
			选择分类:<select name="newsInfo.classCode">
          		<option value="">--全部--</option>
          		<#list classList as res>
          			<option value="${res.code}" <#if newsInfo.classCode?exists && newsInfo.classCode==res.code>selected</#if>>${res.name?default("")}</option>
          		</#list>
          	</select>
			<input type="submit" class="btn_search" name="button" id="searchButton" value="查询"/>
			<span class="shux"></span>
			<input type="button" class="btn_search" name="button2" id="button2" onclick="location.href='${base}/manage/web/news!add.htm'" value="新增" />
		</div>
	</form>
 	<div class="tableNav">
	<#assign flag=0>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablelistNav">
		<tr>
		  <td width="50" align="center" class="table_title">序号</td>
		  <td width="100" align="center" class="table_title">操作员</td>
		  <td width="150" align="center" class="table_title">标题</td>
		  <td width="100" align="center" class="table_title">出处</td>
		  <td width="100" align="center" class="table_title">作者</td>
		  <td width="100" align="center" class="table_title">关键字</td>
		  <td width="100" align="center" class="table_title">分类名称</td>
		  <td width="150" align="center" class="table_title">创建时间</td>
		  <td width="100" align="center" class="table_title">阅读次数</td>
		  <td width="80"  align="center" class="table_title">排序值</td>
		  <td width="250" align="center" class="table_title tit0">&nbsp;操作</td>
		</tr>
    	<#list page.result as newsinfo>
		    <#if newsinfo_index%2=1>
		    <tr class="tr_cur1">
		    <#else>
		    <tr class="tr_cur2">
		    </#if>
		        <td align="center">${newsinfo_index+1}</td>
		        <td align="left">${newsinfo.loginName?default("")}</td>
		        <td align="left">${newsinfo.title?default("")}</td>
		        <td align="left">${newsinfo.source?default("")}</td>
		        <td align="left">${newsinfo.auth?default("")}</td>
		        <td align="left">${newsinfo.keyWorks?default("")}</td>
		        <td align="left">${newsinfo.className?default("")}</td>
		        <td align="center">${newsinfo.createTime?default("")}</td>
		        <td align="center">${newsinfo.readNum?default("")}</td>
		        <td align="center">${newsinfo.orderid?default("")}</td>
		        <td align="center">
		      	&nbsp;<a href="${base}/manage/web/news!info.htm?id=${newsinfo.id}">详细</a>
		      	&nbsp;<a href="${base}/manage/web/news!edit.htm?id=${newsinfo.id}">编辑</a>
		      	&nbsp;<a href="javascript:common.doPost('${base}/manage/web/news!del.htm?id=${newsinfo.id}','确定要删除吗?');">删除</a>
		      	&nbsp;<a href="javascript:common.doPost('${base}/manage/web/news!top.htm?id=${newsinfo.id}','确定要置顶吗?');">置顶</a>
		      </td>
		    </tr>
		</#list>
		<#if Page.result?size=0>
		    <tr>
		      <td align="center" colspan="12" class="table_title">无相关记录</td>
		      
		    </tr>
    	</#if>
  </table>
</div>
<@paginate pageCount=page.totalPage currentPage=page.thisPageNumber url=pageUrl>
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