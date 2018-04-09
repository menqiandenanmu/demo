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
     	var fm=new BackUrlForm("messageForm","确定删除","${base}/manage/account/sendRules.htm");
    });
		
</script>
</head>
<body>
<!--列表正文开始-->
    <div class="page-main">
        <div class="page-item page-title">商户管理</div>
        <!--索搜区域-->
		<form action="${base}/manage/account/storeInfo.htm" id="searchForm" method="get" >
        <div class="page-item search-panel">
 		  <ul class="search-list clearfix">
 		   <li><span class="lab-title">店铺名称:</span><span>
				<span><input type="text"  name="storeInfo.storeName" class="text text-small" value="${(storeInfo.storeName)!}" />
			</span>
			</li>
			<li><span class="lab-title">商户姓名:</span><span>
				<span><input type="text"  name="storeInfo.linkman" class="text text-small" value="${(storeInfo.linkman)!}" />
			</span>
			</li>
			<li><span class="lab-title">联系电话:</span><span>
				<span><input type="text"  name="storeInfo.linkmanTel" class="text text-small" value="${(storeInfo.linkmanTel)!}" />
			</span>
			</li>
        </ul>
	 	 </div>
        <!--索搜区域结束-->
         <!--列表操作区域-->
        <div class="page-item list-handle">
        <input type="submit" value="搜索" class="btn btn-small bg-blue"/>&nbsp;&nbsp;&nbsp;&nbsp;
        			&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:;" onclick="location.href='${base}/manage/account/storeInfo!add.htm'" class="btn btn-small bg-blue" id="add-btn">+新增</a>
        </div>
        </form>
       <!--列表操作区域-->
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                     <th class="w5" width="80px;">序号</th>
					  <th class="w10" width="80px;">店铺名称</th>
					   <th class="w10" width="80px;">商户账户</th>
					  <th class="w10" width="80px;">商户姓名</th>
					  <th class="w10" width="80px;">联系电话</th>
					  <th class="w10" width="80px;">商户账户余额</th>
					  <th class="w25" width="80px;">店铺地址</th>
					  <th class="w10" width="280px;">操作</th>
					 </tr>
					</thead>
                <tbody>
                <#if page??>
	    	<#list page.result as res>
				<#assign flag=1>
			    <tr  id="${res.id}">
			      <td align="center">${res_index+1}</td>
			      <td align="center">${res.storeName?default("")}</td>
			        <td align="center">${res.storeCode!}</td>
			        <td align="center">${res.linkman!}</td>
			        <td align="center">${res.linkmanTel!}</td>
			        <td align="center">${res.telphone!}</td>
			        <td align="center">${res.address!}</td>
			      <td align="center">
	      			&nbsp;<a href="${base}/manage/account/storeInfo!edit.htm?id=${res.id}">编辑</a>
		      		&nbsp;<a href="javascript:common.doPost('${base}/manage/account/storeInfo!del.htm?id=${res.id}','确定删除吗?');">删除</a>
			      </td>
			    </tr>
			</#list>
			</#if>
			<#if flag=0>
			    <tr>
			      <td align="center" colspan="8" class="table_title">无相关记录</td>
			    </tr>
	    	</#if>
	  </tbody>
        </table>
 <div class="list-bottom clearfix">
 <@paginate pageCount=page.totalPage currentPage=page.thisPageNumber url=pageUrl></@paginate>

</div>
</div>
</div>
</body>
</html>