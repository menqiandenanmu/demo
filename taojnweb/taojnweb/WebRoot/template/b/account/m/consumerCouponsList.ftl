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
     	var fm=new BackUrlForm("messageForm","确定删除","${base}/manage/account/consumerCoupons.htm");
    });
		
</script>
</head>
<body>
<!--列表正文开始-->
    <div class="page-main">
        <div class="page-item page-title">赠券管理</div>
        <!--索搜区域-->
		<form action="${base}/manage/account/consumerCoupons.htm" id="searchForm" method="get" >
		<#--input type="hidden" name="conConsumerCoupons.ruleId" value="${sendRules.id}" /-->
        <div class="page-item search-panel">
 		  <ul class="search-list clearfix">
 		   <li><span class="lab-title">满送名称:</span><span>
				<span><input type="text"  name="conConsumerCoupons.sendName" class="text text-small" value="${(conConsumerCoupons.sendName)!}" />
			</span>
			</li>
 		   <li><span class="lab-title">券名称:</span><span>
				<span><input type="text"  name="conConsumerCoupons.couponName" class="text text-small" value="${(conConsumerCoupons.couponName)!}" />
			</span>
			</li>
			<li><span class="lab-title">状态:</span><span>
				<span>	
				<select name="conConsumerCoupons.couponStatus" style="width:120px;">
					<option	value="">全部</option>
					<option	value="0" <#if conConsumerCoupons.couponStatus==0>selected</#if> >停用</option>
					<option value="1" <#if conConsumerCoupons.couponStatus==1>selected</#if>>启用</option>
				</select>
			</span>
			</li>
			 <#--li><span class="lab-title">开始时间</span><span>
			<input name="conConsumerCoupons.beginTime" type="text"value="${(conConsumerCoupons.beginTime?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})" class="text text-small"/>
			</span></li>
			 <li><span class="lab-title">结束时间</span><span>
			 <input class="text text-small" name="conConsumerCoupons.endTime" type="text" value="${(conConsumerCoupons.endTime?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
			 </span></li-->
        </ul>
	 	 </div>
        <!--索搜区域结束-->
         <!--列表操作区域-->
        <div class="page-item list-handle">
        <input type="submit" value="搜索" class="btn btn-small bg-blue"/>&nbsp;&nbsp;&nbsp;&nbsp;
        			&nbsp;&nbsp;&nbsp;&nbsp;
            <#--a href="javascript:;" onclick="location.href='${base}/manage/account/consumerCoupons!add.htm?id=${sendRules.id}'" class="btn btn-small bg-blue" id="add-btn">+新增</a-->
        </div>
        </form>
       <!--列表操作区域-->
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                     <th class="w10" width="80px;">序号</th>
					  <th class="w10" width="80px;">满送名称</th>
					  <th class="w10" width="80px;">券名称</th>
					   <th class="w10" width="80px;">券编号</th>
					   <th class="w10" width="80px;">价值</th>
					  <th class="w10" width="80px;">状态</th>
					  <th class="w10" width="80px;">库存</th>
					  <th class="w10" width="80px;">赠券失效时间</th>
					  <th class="w20" width="280px;">操作</th>
					 </tr>
					</thead>
                <tbody>
                <#if page??>
	    	<#list page.result as res>
				<#assign flag=1>
			    <tr  id="${res.id}">
			      <td align="center">${res_index+1}</td>
			      <td align="center">${res.sendName?default("")}</td>
			      <td align="center">${res.couponName?default("")}</td>
			        <td align="center">${res.couponCode!}</td>
			        <td align="center">${res.price!}</td>
			      <td align="center"><#if res.couponStatus==0>停用<#else>启用</#if></td>
			        <td align="center">${res.couponNum!}</td>
			         <td align="center">${res.expiratDate?string("yyyy-MM-dd")!}</td>
			      <td align="center">
			      <#if res.couponStatus==0><a href="javascript:common.doPost('${base}/manage/account/consumerCoupons!updateStatus.htm?id=${res.id}&conConsumerCoupons.couponStatus=1','确定启用吗?');">启用</a>
			      	<#else><a href="javascript:common.doPost('${base}/manage/account/consumerCoupons!updateStatus.htm?id=${res.id}&conConsumerCoupons.couponStatus=0','确定停用吗?');">停用</a></#if>
		      		&nbsp;<#--a href="${base}/manage/account/consumerCoupons!edit.htm?id=${res.id}">编辑</a-->
		      		&nbsp;<a href="javascript:common.doPost('${base}/manage/account/consumerCoupons!del.htm?id=${res.id}','确定删除吗?');">删除</a>
			      </td>
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