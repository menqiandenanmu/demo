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

</head>
<body>
<!--列表正文开始-->
    <div class="page-main">
        <div class="page-item page-title">页面显示内容管理</div>
        <!--索搜区域-->
        <div class="page-item search-panel">
		<form action="${base}/manage/web/pageAreaDetail.htm" id="searchForm" method="get" >
 		  <ul class="search-list clearfix">
 		   <li><span class="lab-title">区域名:</span><span>
				<span><input type="text"  name="areaDetail.areaName" class="text text-small"  value="${(areaDetail.areaName)!}" />
			</span>
			</li>
			<li><span class="lab-title">标题名:</span><span>
				<span><input type="text"  name="areaDetail.title" class="text text-small"  value="${(areaDetail.title)!}" />
			</span>
			</li>
            <li class="search-handle"><input type="submit" value="搜索" class="btn btn-small bg-blue"/></li>
        </ul>
		</form>
	 	 </div>
        <!--索搜区域结束-->
       <!--列表操作区域-->
        <#--div class="page-item list-handle">
            <a href="javascript:;" onclick="location.href='${base}/manage/web/pageArea!add.htm'" class="btn btn-small bg-blue" id="add-btn">+新增</a>
        </div-->
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
        <form action="${base}/manage/account/electronicCoup!delAll.htm" id="messageForm" name="messageForm" method="post" >
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                     <th class="w5" width="80px;">序号</th>
					  <th class="w10" width="80px;">区域名</th>
					   <th class="w10" width="80px;">标题名</th>
					  <th class="w10" width="80px;">地址url</th>
					  <th class="w10" width="80px;">打开方式</th>
					  <th class="w20" width="280px;">排序</th>
					  <th class="w20" width="280px;">操作</th>
					 </tr>
					</thead>
                <tbody>
	    	<#list detailPage.result as res>
				<#assign flag=1>
			    <tr  id="${res.id}">
			      <td align="center">${res_index+1}</td>
			      <td align="center">${res.areaName?default("")}</td>
			        <td align="center">${res.title?default("")}</td>
			      <td align="center">${res.infoUrl?default("")}</td>
			      <td align="center"><#if res.targetFlag>当前页面打开<#else>新页面打开</#if></td>
			      <td align="center">${res.orderid?default("")}</td>
			      <td align="center">
				   &nbsp;<a href="${base}/manage/web/pageAreaDetail!edit.htm?id=${res.id}">编辑</a>
		      		<#--&nbsp;<a href="javascript:common.doPost('${base}/manage/web/pageAreaDetail!del.htm?id=${res.id}','确定要删除吗?');">删除</a>-->
		      	</td>
			    </tr>
			</#list>
			<#if flag=0>
			    <tr>
			      <td align="center" colspan="10" class="table_title">无相关记录</td>
			    </tr>
	    	</#if>
	  </tbody>
        </table>
        </form>
 <div class="list-bottom clearfix">
<@paginate pageCount=detailPage.totalPage currentPage=detailPage.thisPageNumber url=pageUrl>
</@paginate>

</div>
</div>
</div>
</body>
</html>