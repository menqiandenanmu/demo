<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<#include "/template/m/common/utils.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript" src="${base}/script/common/utiltable.js"></script>
<script language="JavaScript">
	$(document).ready(function(){
	utiltable.unionByProperty("tableDiv","table","key");
	});
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
		<ul>
			<li>用户账户统计</li>
		</ul>
	</div>
	<div class="r_content">
		<form action="${base}/manage/account/mAgentAccount!accountCount.htm" id="searchForm" method="get" >
			<div class="tool_search">
			
				景区：<select name="tradeVo.ownerSupplyId" style="width:150px;" >
						<option value="">全部</option>
						<#if supplyList?exists>
						<#list supplyList as supply>
							<option value="${supply.id}" <#if tradeVo.ownerSupplyId?exists && tradeVo.ownerSupplyId==supply.id>selected</#if>>${supply.corpName}</option>
						</#list>
						</#if>
					</select>
				&nbsp;用户：<input name="tradeVo.accountName" type="text" class="input_1" value="${(tradeVo.accountName)!}" size="15" maxlength="50" />
				&nbsp;金额类型：<select name="tradeVo.opType">
						<option>--全部--</option>
						<option value="2" <#if tradeVo.opType?exists && tradeVo.opType==2>selected</#if>>订单完成扣款</option>
						<option value="4" <#if tradeVo.opType?exists && tradeVo.opType==4>selected</#if>>订单返利</option>
					  </select>	
			    &nbsp;时间:<input name="tradeVo.begDate" type="text" value="${(tradeVo.begDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>-<input name="tradeVo.endDate" type="text" value="${(tradeVo.endDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
				<input type="submit" class="btn_search" name="button" id="searchButton" value="查询"/>
		   </div>
		</form>
	 	<div class="tableNav">
		<#assign flag=0>
		<#assign sumCount=0>
		<#assign sumHj=0>
		<#assign sumFl=0>
		<#assign SmallsumCount=0>
		<#assign SmallsumHj=0>
		<#assign SmallsumFl=0>
		<#assign rowTotal=0>
		<#assign flag1=0>
		<#assign type="">
		<table id ="table" width="100%" border="1" cellpadding="0" cellspacing="0" class="tablelistNav">
			<tr>
				 <td width="50" align="center" class="table_title">用户</td>
				 <td width="50" align="center" class="table_title">景区</td>
				 <td width="50" align="center" class="table_title">订单笔数</td>
				 <td width="50" align="center" class="table_title">合计消费金额</td>
				 <td width="50" align="center" class="table_title">合计返利金额</td>
			</tr>
	    	<#list accountCountPages.result as res>
			<#assign flag=1>
			<#if res.accountId!=type && flag1!=0>
				<tr class="tr_cur1" style="color: blue;" onMouseMove="this.style.color='red';" onMouseOut="this.style.color='blue';">
					<td  align="right" height="20" colspan="2">小计</td>
					<td align="center">${SmallsumCount}</td>
					<td align="center">${SmallsumHj!}</td>
					<td align="center">${SmallsumFl!}</td>
				</tr>
				<#assign sumCount=sumCount+SmallsumCount>
				<#assign sumHj=sumHj+SmallsumHj>
				<#assign sumFl=sumFl+SmallsumFl>
				<#assign SmallsumCount=0>
				<#assign SmallsumHj=0>
				<#assign SmallsumFl=0>
				<#assign rowTotal=0>
				<#assign type=res.accountId>
				
			</#if>
				<#assign flag1=1>
				<#assign type=res.accountId>
				<#assign SmallsumCount=SmallsumCount+res.orderCount>
				<#assign SmallsumHj=SmallsumHj+res.heJi>
				<#assign SmallsumFl=SmallsumFl+res.fanLi>
				
		    <tr class="tr_cur1">
		    	<td align="center" key="ct_${res.accountId}" >
					${res.accountName!}
		    	</td>
		    	<td align="center">${res.supplyName!}</td>
		    	<td align="center">${res.orderCount!}</td>
		    	<td align="center">${res.heJi!}</td>
		    	<td align="center">${res.fanLi!}</td>
			</tr>
			<#if !res_has_next>
				<tr class="tr_cur1" style="color: blue;" onMouseMove="this.style.color='red';" onMouseOut="this.style.color='blue';">
					<td  align="right" height="20" colspan="2">小计</td>
					<td align="center">${SmallsumCount}</td>
					<td align="center">${SmallsumHj}</td>
					<td align="center">${SmallsumFl!}</td>
				</tr>
				<#assign sumCount=sumCount+SmallsumCount>
				<#assign sumHj=sumHj+SmallsumHj>
				<#assign sumFl=sumFl+SmallsumFl>
				<#assign SmallsumCount=0>
				<#assign SmallsumHj=0>
				<#assign SmallsumFl=0>
				<#assign rowTotal=0>
			</#if>
		</#list>
		    <tr class="tr_cur1"  style="color:red;" onMouseMove="this.style.color='blue';" onMouseOut="this.style.color='red';" >
				<td align="center">&nbsp;</td>
				<td  align="right" colspan="1" height="20">合计</td>
				<td align="center">${sumCount}</td>
				<td align="center">${sumHj!}</td>
				<td align="center">${sumFl!}</td>
			</tr>
		</table>
		</div>
	<@paginate pageCount=accountCountPages.totalPage currentPage=accountCountPages.thisPageNumber url=pageUrl>
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