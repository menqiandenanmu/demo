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
<div class="rightArea" >
<div class="r_ContentNav">
	<div class="r_tit1">
		<ul>
			<li>商品订单明细列表</li>
		</ul>
	</div>
	<div class="r_content" style="min-width: 1300px">
		<form action="${base}/manage/store/orderDetail.htm" id="searchForm" method="get" >
			<div class="tool_search">
				订单&nbsp;&nbsp;&nbsp;号：<input name="to.subOrderNo" type="text" class="input_1" value="${(to.subOrderNo)!}" size="15" maxlength="50" />
				&nbsp;&nbsp;&nbsp;订单日期：<input name="begCreateDate" type="text"value="${(begCreateDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>-<input name="endCreateDate" type="text" value="${(endCreateDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
				&nbsp;&nbsp;&nbsp;商品名称：<input name="to.goodsName" type="text" class="input_1" value="${(to.goodsName)!}" size="15" maxlength="50" />
				<br/>
				订单状态：<select name="to.orderStatus" style="width:116px;">
						<option value="">--全部--</option>
						<option value="0" <#if (to.orderStatus)?exists && to.orderStatus=0>selected</#if>>新建订单</option>
						<option value="1" <#if (to.orderStatus)?exists && to.orderStatus=1>selected</#if>>成功订单</option>
						<option value="2" <#if (to.orderStatus)?exists && to.orderStatus=2>selected</#if>>失败订单</option>
						<option value="3" <#if (to.orderStatus)?exists && to.orderStatus=3>selected</#if>>退订单</option>
						</select>
				&nbsp;&nbsp;&nbsp;<input type="submit" class="btn_search" name="button" id="searchButton" value="查询"/>
		   </div>
		</form>
	 	<div class="tableNav" id="table_div">
		<table id="table_union" border="0" cellpadding="0" cellspacing="0" class="tablelistNav" width="100%">
			<tr>
			  <td width="30" align="center" class="table_title">序号</td>
			  <td width="150" align="center" class="table_title">订单号</td>
			  <td width="150" align="center" class="table_title">子订单号</td>
			  <td width="100" align="center" class="table_title">用户名</td>
			  <td width="100" align="center" class="table_title">商品名称</td>
			  <td width="50" align="center" class="table_title">数量</td>
			  <td width="50" align="center" class="table_title">单价</td>
			  <td width="50" align="center" class="table_title">合计</td>
			  <td width="50" align="center" class="table_title">订单状态</td>
			  <td  align="center" class="table_title">订单时间</td>
			</tr>
	    	<#list pages.result as res>
			    <#if res_index%2=1>
			    <tr class="tr_cur1">
			    <#else>
			    <tr class="tr_cur2">
			    </#if>
			      <td align="left">${res_index+1}</td>
			      <td align="left">${res.orderNo!}</td>
			      <td align="center">${res.subOrderNo!}</td>
			      <td align="center">${res.buyerName!}</td>
			      <td align="center">${res.goodsName!}</td>
			      <td align="right">${res.amount!}</td>
			      <td align="right">${(res.price?c)!0.0}</td>
			      <td align="right">${(res.sum?c)!0.0}</td>
			      <td align="center">
			      	<#if res.orderStatus=0>新建订单
			      		<#elseif res.orderStatus=1>成功订单
			      		<#elseif res.orderStatus=2>失败订单
			      		<#elseif res.orderStatus=3>退订单
			      	</#if></td>
			      <td align="center">${res.createTime?string("yyyy-MM-dd HH:mm")!}</td>
			    </tr>
			</#list>
			<#if pages.result?size=0>
			    <tr>
			      <td align="center" colSpan="14" class="table_title">无相关记录</td>
			    </tr>
	    	</#if>
	  	</table>
		</div>
	<@paginate pageCount=pages.totalPage currentPage=pages.thisPageNumber url=pageUrl>
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