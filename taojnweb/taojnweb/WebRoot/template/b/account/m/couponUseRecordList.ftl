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
     	var fm=new BackUrlForm("messageForm","确定删除","${base}/manage/account/couponUseRecord.htm");
    });
		
</script>
</head>
<body>
<!--列表正文开始-->
    <div class="page-main">
        <div class="page-item page-title">赠送消费列表</div>
        <!--索搜区域-->
		<form action="${base}/manage/account/couponUseRecord.htm" id="searchForm" method="get" >
        <div class="page-item search-panel">
 		  <ul class="search-list clearfix">
 		   <li><span class="lab-title">券名称:</span><span>
				<span><input type="text"  name="couponUseRecord.couponName" class="text text-small" value="${(couponUseRecord.couponName)!}" />
			</span>
			</li>
			<li><span class="lab-title">用户名:</span><span>
				<span><input type="text"  name="couponUseRecord.accountName" class="text text-small" value="${(couponUseRecord.accountName)!}" />
			</span>
			</li>
			<li><span class="lab-title">姓名:</span><span>
				<span><input type="text"  name="couponUseRecord.realName" class="text text-small" value="${(couponUseRecord.realName)!}" />
			</span>
			</li>
        </ul>
	 	 </div>
        <!--索搜区域结束-->
         <!--列表操作区域-->
        <div class="page-item list-handle">
        <input type="submit" value="搜索" class="btn btn-small bg-blue"/>&nbsp;&nbsp;&nbsp;&nbsp;
        			&nbsp;&nbsp;&nbsp;&nbsp;
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
					  <th class="w10" width="80px;">券名称</th>
					   <th class="w10" width="80px;">用户名</th>
					   <th class="w10" width="80px;">姓名</th>
					  <th class="w5" width="80px;">状态</th>
					  <th class="w10" width="80px;">交易号</th>
					  <th class="w10" width="80px;">支付金额</th>
					  <th class="w10" width="80px;">钱包余额</th>
					  <th class="w10" width="80px;">赠券金额</th>
					  <th class="w10" width="80px;">使用时间</th>
					 </tr>
					</thead>
                <tbody>
                <#if page??>
	    	<#list page.result as res>
				<#assign flag=1>
			    <tr  id="${res.id}">
			      <td align="center">${res_index+1}</td>
			      <td align="center">${res.couponName?default("")}</td>
			        <td align="center">${res.accountName!}</td>
			        <td align="center">${res.realName!}</td>
			      <td align="center"><#if res.couponStatus==0>未消费<#elseif res.couponStatus==1>已消费<#else>已失效</#if></td>
			        <td align="center">${res.transNo!}</td>
			        <td align="center">${res.paySum!}</td>
			        <td align="center">${res.tradeLeft!}</td>
			        <td align="center">${res.price!}</td>
			         <td align="center">${res.payTime?string("yyyy-MM-dd")!}</td>
			      <#--td align="center">
			      <#if res.couponStatus==0><a href="javascript:common.doPost('${base}/manage/account/couponUseRecord!updateStatus.htm?id=${res.id}&couponUseRecord.couponStatus=1','确定启用吗?');">启用</a>
			      	<#else><a href="javascript:common.doPost('${base}/manage/account/couponUseRecord!updateStatus.htm?id=${res.id}&couponUseRecord.couponStatus=0','确定停用吗?');">停用</a></#if>
		      		&nbsp;<a href="${base}/manage/account/couponUseRecord!edit.htm?id=${res.id}">编辑</a>
		      		&nbsp;<a href="javascript:common.doPost('${base}/manage/account/couponUseRecord!del.htm?id=${res.id}','确定删除吗?');">删除</a>
			      </td-->
			    </tr>
			</#list>
			</#if>
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