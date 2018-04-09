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
        <div class="page-item page-title">转账管理</div>
        <!--索搜区域-->
		<form action="${base}/manage/account/giroInfo.htm" id="searchForm" method="get" >
        <div class="page-item search-panel">
 		<ul class="search-list clearfix">
            <li><span class="lab-title">转入账号</span><span>
			<input type="text" maxlength="15" name="giroInfo.accountName2" value="${giroInfo.accountName2!}" class="text text-small"/>
			</span></li>
            <li><span class="lab-title">转出账号</span><span>
			<input type="text"  maxlength="15" name="giroInfo.accountName" value="${giroInfo.accountName!}" class="text text-small"/>
			</span></li>
			 <li><span class="lab-title">交易号</span><span>
			<input type="text"  name="giroInfo.orderNo" value="${giroInfo.orderNo!}" class="text text-small" size="20" />
			</span></li>
            <li><span class="lab-title">转入姓名</span><span>
			<input type="text" maxlength="15" name="giroInfo.realname2" value="${giroInfo.realname2!}" class="text text-small"/>
			</span></li>
            <li><span class="lab-title">转出姓名</span><span>
			<input type="text" maxlength="15" name="giroInfo.realname" value="${giroInfo.realname!}" class="text text-small"/>
			</span></li>
			
            <#--li><span class="lab-title">开始时间</span><span>
			<input name="giroInfo.begDate" type="text"value="${(giroInfo.begDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})" class="text text-small"/>
			</span></li>
			 <li><span class="lab-title">结束时间</span><span>
			 <input class="text text-small" name="giroInfo.endDate" type="text" value="${(giroInfo.endDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
			 </span></li-->
        </ul>
		
	 	 </div>
        <!--索搜区域结束-->
       <!--列表操作区域-->
        <div class="page-item list-handle">
        <input type="submit" value="搜索" class="btn btn-small bg-blue"/>&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" class="btn btn-small bg-blue" onClick="location.href='${base}/manage/account/giroInfo!purchaseExport.htm?${queryString}'" value="导出">
        </div>
        </form>
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                    <th class="w10" width="80px;">序号</th>
					  <th class="w10" width="80px;">转入账号</th>
					  <th class="w10" width="80px;">转入姓名</th>
					  <th class="w20" width="80px;">转出账号</th>
					  <th class="w20" width="80px;">转出姓名</th>
					  <th class="w10" width="80px;">交易号</th>
					  <th class="w10" width="80px;">操作值</th>
					  <th class="w10" width="80px;">转出前剩余金额</th>
					  <th class="w10" width="80px;">转出后剩余金额</th>
					  <th class="w20" width="80px;">时间</th>
			 </tr>
					</thead>
                <tbody>
	    	<#list page.result as res>
				<#assign flag=1>
			    <tr  id="${res.id}">
			      <td align="center">${res_index+1}</td>
			      <td align="center">${res.accountName2?default("")}</td>
			      <td align="center">${res.realname2?default("")}</td>
			      <td align="center">${res.accountName?default("")}</td>
			      <td align="center">${res.realname?default("")}</td>
			      <td align="center">${res.orderNo!}</td>
			      <td align="center">${res.opValue!0}</td>
			      <td align="center">${res.leftValue!0}</td>
			      <td align="center">${res.inLeftValue!0}</td>
			      <td align="center">${res.createTime?string("yyyy-MM-dd HH:mm:ss")!}</td>
			    </tr>
			</#list>
			<#if flag=0>
			    <tr>
			      <td align="center" colspan="10" class="table_title">无相关记录</td>
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