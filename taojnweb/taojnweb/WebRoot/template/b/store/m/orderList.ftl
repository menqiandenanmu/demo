<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/template/m/common/head.ftl">
<#include "/template/m/common/utils.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<link href="${base}/script/datepicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
		<ul>
			<li>商品订单列表</li>
		</ul>
	</div>
	<div class="r_content" style="width:1800px">
		<form action="${base}/manage/store/order.htm" id="searchForm" method="get" >
			<div class="tool_search">
				订单&nbsp;&nbsp;&nbsp;号：<input name="orderInfo.orderNo" type="text" class="input_1" value="${(orderInfo.orderNo)!}" size="15" maxlength="50"/>
				&nbsp;&nbsp;&nbsp;订单日期：<input name="begCreateDate" type="text"value="${(begCreateDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>-<input name="endCreateDate" type="text" value="${(endCreateDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
				<br/>
				用户名称：<input name="orderInfo.buyerName" type="text" class="input_1" value="${(orderInfo.buyerName)!}" size="15" maxlength="50" />
				&nbsp;&nbsp;&nbsp;联系&nbsp;&nbsp;&nbsp;人：<input name="orderInfo.linkName" type="text" class="input_1" value="${(orderInfo.linkName)!}" size="15" maxlength="50" />
				&nbsp;&nbsp;&nbsp;手机号码：<input name="orderInfo.linkMobile" type="text" class="input_1" value="${(orderInfo.linkMobile)!}" size="15" maxlength="50" />
				&nbsp;&nbsp;&nbsp;身份证号：<input name="orderInfo.linkIdcard" type="text" class="input_1" value="${(orderInfo.linkIdcard)!}" size="15" maxlength="50" />
				<br/>
				订单状态：<select name="orderInfo.orderStatus" style="width:116px;">
							<option value="">--全部--</option>
						<option value="0" <#if (orderInfo.orderStatus)?exists && orderInfo.orderStatus=0>selected</#if>>新建订单</option>
						<option value="1" <#if (orderInfo.orderStatus)?exists && orderInfo.orderStatus=1>selected</#if>>成功订单</option>
						<option value="2" <#if (orderInfo.orderStatus)?exists && orderInfo.orderStatus=2>selected</#if>>失败订单</option>
						<option value="3" <#if (orderInfo.orderStatus)?exists && orderInfo.orderStatus=3>selected</#if>>退订单</option>
						</select>
				&nbsp;&nbsp;&nbsp;支付状态：<select name="orderInfo.payStatus" style="width:116px;"> 
						<option value="">--全部--</option>
						<option value="0" <#if (orderInfo.payStatus)?exists && orderInfo.payStatus=0>selected</#if>>待支付</option>
						<option value="1" <#if (orderInfo.payStatus)?exists && orderInfo.payStatus=1>selected</#if>>已支付</option>
						<option value="2" <#if (orderInfo.payStatus)?exists && orderInfo.payStatus=2>selected</#if>>支付过期</option>
						<option value="3" <#if (orderInfo.payStatus)?exists && orderInfo.payStatus=3>selected</#if>>无需支付</option>
						</select>
				&nbsp;&nbsp;&nbsp;关闭标志：<select name="orderInfo.closed"  style="width:116px;">
						<option value="" >--全部--</option>
						<option value="0" <#if (orderInfo.closed)?exists && orderInfo.closed=1>selected</#if>>未关闭</option>
						<option value="1" <#if (orderInfo.closed)?exists && orderInfo.closed=1>selected</#if>>已关闭</option>
						</select>
				&nbsp;&nbsp;&nbsp;
				<input type="submit" class="btn_search" name="button" id="searchButton" value="查询"/>
		   </div>
		</form>
	 	<div class="tableNav">
		<table border="0" cellpadding="0" cellspacing="0" class="tablelistNav" width="100%">
			<tr>
			  <td width="50" align="center" class="table_title">序号</td>
			  <td width="180" align="center" class="table_title">订单号</td>
			  <td width="160"align="center" class="table_title">时间</td>
			  <td width="80" align="center" class="table_title">用户名</td>
			  <td width="80" align="center" class="table_title">联系人</td>
			  <td width="80" align="center" class="table_title">手机号</td>
			  <td width="160" align="center" class="table_title">身份证号</td>
			  <td width="50 align="center" class="table_title">订单状态</td>
			  <td width="50" align="center" class="table_title">支付状态</td>
			  <td width="50" align="center" class="table_title">关闭标志</td>
			  <td width="50" align="center" class="table_title">订单金额</td>
			  <td align="center" class="table_title">订单信息</td>
			  <td width="180" align="center" class="table_title">操作</td>
			</tr>
	    	<#list orderInfoPage.result as res>
			    <#if res_index%2=1>
			    <tr class="tr_cur1">
			    <#else>
			    <tr class="tr_cur2">
			    </#if>
			      <td align="left">${res_index+1}</td>
			      <td align="left">${res.orderNo!}</td>
			      <td align="center">${res.createTime?string("yyyy-MM-dd HH:mm")!}</td>
			      <td align="left">${res.buyerName!}</td>
			      <td align="left">${res.linkName!}</td>
			      <td align="left">${res.linkMobile!}</td>
			      <td align="left">${res.linkIdcard!}</td>
			      <td align="center">
			      	<#if res.orderStatus=0>新建订单
			      		<#elseif res.orderStatus=1>成功订单
			      		<#elseif res.orderStatus=2>失败订单
			      		<#elseif res.orderStatus=3>退订单
			      	</#if>
			      </td>
			      <td align="center">
			      	<#if res.payStatus=0>待支付
			      		<#elseif res.payStatus=1>已支付
			      		<#elseif res.payStatus=2>过期
			      		<#elseif res.payStatus=3>无需支付
			      	</#if>
			      </td>
			      <td align="center">
			      	<#if res.closed>已关闭
			      	<#else>未关闭
			      	</#if>
			      </td>
			      <td align="right">${(res.finalSum?c)!}</td>
			      <td align="left">${res.info}&nbsp;</td>
			      <td align="center">
			      	<a href="${base}/manage/store/order!info.htm?id=${res.id}">详情</a>
			      	<#if !res.closed >
			      		<#if (res.orderStatus=0 || res.orderStatus=1) && res.payStatus=0 >
			      		&nbsp;<a href="${base}/manage/store/order!pay.htm?id=${res.id}">支付</a>
			      		</#if>
			      		<#if !(res.orderStatus =2)>
			      	&nbsp;<a href="${base}/manage/store/order!edit.htm?id=${res.id}">编辑</a>
			      	&nbsp;<a href="${base}/manage/store/order!orderNotify.htm?id=${res.id}">通知</a>
			      		</#if>
				      	<#if res.orderStatus=0 || res.orderStatus=1>
				      	&nbsp;<a href="javascript:common.doPost('${base}/manage/store/order!cancel.htm?id=${res.id}','确定退单吗?');">退单</a>
				      	</#if>
			      	</#if>
			      </td>
			    </tr>
			</#list>
			<#if orderInfoPage.result?size=0>
			    <tr>
			      <td align="center" colSpan="15" class="table_title">无相关记录</td>
			    </tr>
	    	</#if>
	  	</table>
		</div>
	<@paginate pageCount=orderInfoPage.totalPage currentPage=orderInfoPage.thisPageNumber url=pageUrl>
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