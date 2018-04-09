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
        <div class="page-item page-title">账户统计</div>
        <!--索搜区域-->
		<form action="${base}/manage/account/agentOrderCount.htm" id="searchForm" method="get" >
        <div class="page-item search-panel">
 		  <ul class="search-list clearfix">
			<li><span class="lab-title">充值类型:</span><span>
				<span>
					<select name="tradeAccountDetail.opType">
					<option	value="">全部</option>
					<option	value="0" <#if tradeAccountDetail.opType==0>selected</#if>>充值</option>
					<option value="1" <#if tradeAccountDetail.opType==1>selected</#if>>消费</option>
					<#--option value="1" <#if tradeAccountDetail.opType==2>selected</#if>>退卡</option-->
					<option value="3" <#if tradeAccountDetail.opType==3>selected</#if>>充值卡充值</option>
					<option value="4" <#if tradeAccountDetail.opType==4>selected</#if>>电子券充值</option>
					</select>
				</span>
			</li>
			 <li><span class="lab-title">开始时间:</span><span>
				<span><input type="text" name="tradeAccountDetail.begDate" class="text text-small" value="${(tradeAccountDetail.begDate?string("yyyy-MM-dd"))?default('')}" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
			</span>
			</li>
			 <li><span class="lab-title">结束时间:</span><span>
				<span><input type="text" name="tradeAccountDetail.endDate" class="text text-small" value="${(tradeAccountDetail.endDate?string("yyyy-MM-dd"))?default('')}" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
			</span>
			</li>
        </ul>
	 	 </div>
        <!--索搜区域结束-->
       <!--列表操作区域-->
        <!--列表操作区域-->
        <div class="page-item list-handle">
        <input type="submit" value="搜索" class="btn btn-small bg-blue"/>&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" class="btn btn-small bg-blue" onClick="location.href='${base}/manage/account/agentOrderCount!agentOrderCountExport.htm?${queryString}'" value="导出">
      
        </div>
         <div class="page-item list-handle">
         <#-- 账户总余额：<span style="font-size: 18px; color: red; font-weight: bold;">${amountTotal?c}</span>-->
             </div>
        </form>
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
        <form  id="messageForm" name="messageForm" method="post" >
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                     <th class="w5" width="80px;">序号</th>
					  <th class="w10" width="80px;">日期</th>
					   <th class="w10" width="80px;">操作类型</th>
					  <th class="w10" width="80px;">金额</th>
					 </tr>
					</thead>
                <tbody>
	    	<#list tradeAccountDetailPage.result as res>
				<#assign flag=1>
			    <tr  id="${res.id}">
			      <td align="center">${res_index+1}</td>
			      <td align="center">${res.rechargeTime}</td>
			      <td align="center">
			      <#if res.opType==0>充值</#if>
				  <#if res.opType==1>消费</#if>
				 <#if res.opType==2>退款</#if>
				 <#if res.opType==3>充值卡充值</#if>
				 <#if res.opType==4>电子券充值</#if>
			      </td>
			        <td align="center">${res.remark!}</td>
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
 <@paginate pageCount=tradeAccountDetailPage.totalPage currentPage=tradeAccountDetailPage.thisPageNumber url=pageUrl></@paginate>

</div>
</div>
</div>
</body>
</html>