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
		<li>资讯分类</li>
	</ul>
</div>
<div class="r_content">	
	<div class="tool_search">
		<form action="${base}/manage/web/newsClass.htm" id="searchForm" method="get" >
        	  名称：<input type="text" name="newsClass.name" class="width120 inputtext" value="${newsClass.name?default("")}"/>
			<input type="submit" class="btn_search" name="btn" value="查询"/>
			<span class="shux"></span>
			<input type="button" class="btn_search" name="button2" id="button2" onclick="location.href='${base}/manage/web/newsClass!add.htm'" value="新增" />
	 </form>
	</div>
 	<div class="tableNav">
	<#assign flag=0>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablelistNav">
		<tr>
		  <td width="50" align="center" class="table_title">序号</td>
		  <td  align="center" class="table_title">标题</td>
		  <td width="100" align="center" class="table_title">编号</td>
		  <td width="100" align="center" class="table_title">父节点</td>
		  <td align="center" class="table_title">说明信息</td>
		  <td width="200" align="center" class="table_title tit0">&nbsp;操作</td>
		</tr>
    	<#list page.result as news>
		    <#if news_index%2=1>
		    <tr class="tr_cur1">
		    <#else>
		    <tr class="tr_cur2">
		    </#if>
		      <td align="center">${news_index+1}</td>
		      <td align="left">${news.name!("&nbsp;")}</td>
		      <td align="center">${news.code!("&nbsp;")}</td>
		      <td align="left">${news.parentNames!("&nbsp;")}</td>
		      <td align="left">${news.info!("&nbsp;")}</td>
		      <td align="center">
		      	&nbsp;<a href="${base}/manage/web/newsClass!edit.htm?id=${news.id}">编辑</a>
		      	&nbsp;<a href="${base}/manage/web/newsClass!add.htm?id=${news.id}">添加子节点</a>
		      	&nbsp;<a href="javascript:common.doPost('${base}/manage/web/newsClass!del.htm?id=${news.id}','确定要删除吗?');">删除</a>
		      </td>
		    </tr>
		</#list>
		<#if Page.result?size=0>
		    <tr>
		      <td align="center" colspan="9" class="table_title">无相关记录</td>
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