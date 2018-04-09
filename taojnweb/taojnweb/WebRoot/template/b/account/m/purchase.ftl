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
        <div class="page-item page-title">消费记录</div>
        <!--索搜区域-->
		<form action="${base}/manage/account/purchase.htm" id="searchForm" method="get" >
        <div class="page-item search-panel">
 		<ul class="search-list clearfix">
            <li><span class="lab-title">商户名称</span><span>
			<input type="text" maxlength="15" name="tradeAccountDetail.serialNo" value="${tradeAccountDetail.serialNo!}" class="text text-small"/>
			</span></li>
            <li><span class="lab-title">账户名称</span><span>
			<input type="text"  maxlength="15" name="tradeAccountDetail.accountName" value="${tradeAccountDetail.accountName!}" class="text text-small"/>
			</span></li>
			 <li><span class="lab-title">交易号</span><span>
			<input type="text"  name="tradeAccountDetail.remark1" value="${tradeAccountDetail.remark1!}" class="text text-small" size="20" />
			</span></li>
            <li><span class="lab-title">操作员</span><span>
			<input type="text" maxlength="15" name="tradeAccountDetail.opLoginName" value="${tradeAccountDetail.opLoginName!}" class="text text-small"/>
			</span></li>
			
            <li><span class="lab-title">开始时间</span><span>
			<input name="tradeAccountDetail.begDate" type="text"value="${(tradeAccountDetail.begDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})" class="text text-small"/>
			</span></li>
			 <li><span class="lab-title">结束时间</span><span>
			 <input class="text text-small" name="tradeAccountDetail.endDate" type="text" value="${(tradeAccountDetail.endDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
			 </span></li>
        </ul>
		
	 	 </div>
        <!--索搜区域结束-->
       <!--列表操作区域-->
        <div class="page-item list-handle">
        <input type="submit" value="搜索" class="btn btn-small bg-blue"/>&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" class="btn btn-small bg-blue" onClick="location.href='${base}/manage/account/mAgentAccount!purchaseExport.htm?${queryString}'" value="导出">
        </div>
        </form>
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                    <th class="w10" width="80px;">序号</th>
					  <th class="w10" width="80px;">客户名称</th>
					  <th class="w20" width="80px;">账户名称</th>
					  <th class="w10" width="80px;">交易号</th>
					  <th class="w10" width="80px;">消费金额</th>
					  <th class="w10" width="80px;">当前剩余金额</th>
					  <th class="w10" width="80px;">优惠券金额</th>
					  <th class="w10" width="80px;">操作员</th>
					  <th class="w20" width="80px;">时间</th>
			 </tr>
					</thead>
                <tbody>
                <#assign totalSum=0>
	    	<#list accountPages.result as res>
				<#assign flag=1>
				 <#assign totalSum=totalSum+res.opValue>
			    <tr  id="${res.id}">
			      <td align="center">${res_index+1}</td>
			      <td align="center">${res.remark2?default("")}</td>
			      <td align="center">${res.accountName?default("")}</td>
			      <td align="center">${res.remark1!}</td>
			      <td align="center">${res.opValue!0}</td>
			      <td align="center">${res.leftValue!0}</td>
			      <td align="center">${res.tradeAccId!0}</td>
			      <td align="center">${res.opLoginName!}</td>
			      <td align="center">${res.createTime?string("yyyy-MM-dd HH:mm:ss")!}</td>
			    </tr>
			</#list>
			<#if flag=1>
				 <tr>
				 	  <td align="center" class="table_title"></td>
				 	  <td align="center" class="table_title"></td>
				 	  <td align="center" class="table_title"></td>
				 	  <td align="center" class="table_title"></td>
				      <td align="center" class="table_title">合计:${totalSum?c}</td>
				      <td align="center" class="table_title"></td>
				      <td align="center" class="table_title"></td>
				      <td align="center" class="table_title"></td>
			    </tr>
		    </#if>
			<#if flag=0>
			    <tr>
			      <td align="center" colspan="9" class="table_title">无相关记录</td>
			    </tr>
	    	</#if>
	  </tbody>
        </table>
 <div class="list-bottom clearfix">
 <@paginate pageCount=accountPages.totalPage currentPage=accountPages.thisPageNumber url=pageUrl></@paginate>
</div>
</div>
</div>
</body>
</html>