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
        <div class="page-item page-title">催款管理</div>
        <!--索搜区域-->
        <div class="page-item search-panel">
		<form action="${base}/manage/account/dunning.htm" id="searchForm" method="post" >
 <ul class="search-list clearfix">
            <li><span class="lab-title">账户类型</span>
				<span><input type="text" name="dunningInfo.accountName" class="text text-small" value="${dunningInfo.accountName!}" />
			</span></li>
             <li><span class="lab-title">是否还款</span>
				<span>
				<select name="dunningInfo.repaymentFlag"> 
				<option value="">请选择类型</option>
				<option value="0" <#if dunningInfo.repaymentFlag?exists && dunningInfo.repaymentFlag==0>selected</#if>>未还</option>
				<option value="1" <#if dunningInfo.repaymentFlag?exists && dunningInfo.repaymentFlag==1>selected</#if>>已还</option>
				</select>
			</span>
			</li>
            <li class="search-handle"><input type="submit" value="搜索" class="btn btn-small bg-blue"/></li>
        </ul>
		</form>
	 	 </div>
        <!--索搜区域结束-->
       <!--列表操作区域-->
        <div class="page-item list-handle">
            <a href="javascript:;" onclick="location.href='${base}/manage/account/dunning!add.htm'" class="btn btn-small bg-blue" id="add-btn">+新增催款</a>
        </div>
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                     <th class="w10" width="80px;">序号</th>
					  <th class="w20" width="80px;">用户名称</th>
					  <th class="w10" width="80px;">催款说明</th>
					  <th class="w10" width="80px;">是否已还</th>
					  <th class="w10" width="80px;">催款时间</th>
					  <th class="w20" width="280px;">操作</th>
					 </tr>
					</thead>
                <tbody>
	    	<#list pages.result as res>
				<#assign flag=1>
			    <tr  id="${res.id}">
			      <td align="center">${res_index+1}</td>
			      <td align="center">${res.accountName?default("")}</td>
			      <td align="center">${res.contentInfo!}</td>
			      <td align="center"><#if res.repaymentFlag==1>已还<#else>未还</#if></td>
			      <td align="center">${res.createTime?string("yyyy-MM-dd")!}</td>
			      <td align="center">
			      	<a href="${base}/manage/account/dunning!edit.htm?id=${res.id}">编辑</a>
		      		&nbsp;<a href="javascript:common.doPost('${base}/manage/account/dunning!del.htm?id=${res.id}','确定删除吗?');">删除</a>
		      		<#if res.repaymentFlag==0>
		      		&nbsp;<a href="javascript:common.doPost('${base}/manage/account/dunning!repayment.htm?id=${res.id}','确定还款吗?');">还款</a>
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
 <@paginate pageCount=pages.totalPage currentPage=pages.thisPageNumber url=pageUrl></@paginate>

</div>
</div>
</div>
</body>
</html>