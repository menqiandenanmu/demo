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
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new BackUrlForm("messageForm","确定删除","${base}/manage/account/electronicCoup.htm");
    });
		
</script>
</head>
<body>
<!--列表正文开始-->
    <div class="page-main">
        <div class="page-item page-title">商户管理</div>
        <!--索搜区域-->
		<form action="${base}/manage/account/merchant.htm" id="searchForm" method="get" >
        <div class="page-item search-panel">
 		  <ul class="search-list clearfix">
 		   <li><span class="lab-title">商户名称:</span><span>
				<span><input type="text"  name="merchant.merchantName" class="text text-small" value="${(merchant.merchantName)!}" />
			</span>
			</li>
            <li><span class="lab-title">商户号:</span><span>
				<span><input type="text" name="merchant.merchantNumber" class="text text-small" value="${(merchant.merchantNumber)!}" />
			</span>
			</li>
			<li><span class="lab-title">商户状态:</span><span>
				<span>	
				<select name="merchant.merchantStatus" style="width:120px;">
					<option	value="">全部</option>
					<option	value="0" <#if merchant.merchantStatus==0>selected</#if> >停用</option>
					<option value="1" <#if merchant.merchantStatus==1>selected</#if>>启用</option>
				</select>
			</span>
        </ul>
	 	 </div>
        <!--索搜区域结束-->
         <!--列表操作区域-->
        <div class="page-item list-handle">
        <input type="submit" value="搜索" class="btn btn-small bg-blue"/>&nbsp;&nbsp;&nbsp;&nbsp;
	        <!--新增功能
	        <a href="javascript:;" onclick="location.href='${base}/manage/account/merchant!add.htm'" class="btn btn-small bg-blue" id="add-btn">+新增商户</a>-->
        </div>
        </form>
       <!--列表操作区域-->
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
        <form action="${base}/manage/account/electronicCoup!delAll.htm" id="messageForm" name="messageForm" method="post" >
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                     <th class="w5" width="80px;">序号</th>
					  <th class="w10" width="80px;">商户名称</th>
					   <th class="w10" width="80px;">商户号</th>
					  <th class="w10" width="80px;">商户状态</th>
					  <th class="w20" width="280px;">操作</th>
					 </tr>
					</thead>
                <tbody>
	    	<#list pages.result as res>
				<#assign flag=1>
			    <tr  id="${res.id}">
			      <td align="center">${res_index+1}</td>
			      <td align="center">${res.merchantName?default("")}</td>
			        <td align="center">${res.merchantNumber!}</td>
			      <td align="center"><#if res.merchantStatus==0>停用<#else>启用</#if></td>
			      <td align="center">
			      <#if res.merchantStatus==0><a href="javascript:common.doPost('${base}/manage/account/merchant!update.htm?id=${res.id}&merchant.merchantStatus=1','确定启用吗?');">启用</a>
			      	<#else><a href="javascript:common.doPost('${base}/manage/account/merchant!update.htm?id=${res.id}&merchant.merchantStatus=0','确定停用吗?');">停用</a></#if>
		      		&nbsp;<a href="javascript:common.doPost('${base}/manage/account/merchant!del.htm?id=${res.id}','确定删除吗?');">删除</a>
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
 <@paginate pageCount=pages.totalPage currentPage=pages.thisPageNumber url=pageUrl></@paginate>

</div>
</div>
</div>
</body>
</html>