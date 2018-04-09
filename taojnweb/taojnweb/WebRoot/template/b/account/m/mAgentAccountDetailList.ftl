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
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
		<ul>
			<li>用户账户清单</li>
		</ul>
	</div>
	<div class="r_content" style="width: 1300px">
		<form action="${base}/manage/account/accountList.htm" id="searchForm" method="post" >
			<div class="tool_search">
				&nbsp;订单号：<input name="tradeOrderDetailExtend.orderNo" type="text" class="input_1" value="${(tradeOrderDetailExtend.orderNo)!}" size="15" maxlength="50" />
				
				&nbsp;&nbsp;账户类型：<select name="tradeOrderDetailExtend.tradeAccId" style="width:150px;" >
						<option value="">全部</option>
						<#if accountList?exists>
						<#list accountList as account>
							<option value="${account.id}" <#if tradeOrderDetailExtend.tradeAccId==account.id >selected</#if> >${account.accName}</option>
						</#list>
						</#if>
					</select>
				&nbsp;清单日期:<input name="tradeOrderDetailExtend.begDate" type="text" value="${(tradeOrderDetailExtend.begDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>-<input name="tradeOrderDetailExtend.endDate" type="text" value="${(tradeOrderDetailExtend.endDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
				</br>
				&nbsp;用户：
				<select name="tradeOrderDetailExtend.accountId" >
				<option >全部</option>
				<#if agentList?? >
				<#list agentList as res>
				<option value="${res.id}" <#if tradeOrderDetailExtend.accountId==res.id>selected</#if>>${res.corpName}</option>
				</#list>
				</#if>
				</select>
				
				<input type="submit" class="btn_search" name="button" id="searchButton" value="查询"/>
				<!--<input type="button" class="btn_search" onClick="location.href='${base}/manage/account/mAgentAccount!mAgentAccountExport.htm?${queryString}'" value="导出">-->
		   </div>
		</form>
	 	<div class="tableNav" >
		<#assign flag=0>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablelistNav">
			<tr>
			  <td width="30" align="center" class="table_title">序号</td>	  
			  <td width="50" align="center" class="table_title">订单号</td>
			  <td width="50" class="table_title">用户</td>
			  <td width="50" align="center" class="table_title">交易账户类型</td>
			  <td width="60" align="center" class="table_title">交易时间</td>
			  <td width="50" align="center" class="table_title">充值订单类型</td>
			  <td width="50" align="center" class="table_title">操作金额</td>
			  <td width="50" align="center" class="table_title">原有余额</td>
			</tr>
			<#if accountDetailPages.result??  >
	    	<#list accountDetailPages.result as res>
				<#assign flag=1>
			    <#if res_index%2=1>
			    <tr class="tr_cur1" id="${res.id}">
			    <#else>
			    <tr class="tr_cur2" id="${res.id}">
			    </#if>
			      <td align="center">${res_index+1}</td>
			      <td align="left">${res.orderNo?default("--")}</td>
			      <td align="left">${res.accountName?default("")}</td>
			      <td align="center">${res.tradeAccName?default("")}</td>
			      <td align="center"><#if res.createTime?? >${res.createTime?string("yyyy-MM-dd HH:mm:ss")}<#else>--</#if></td>
			      <td align="center">
			      <#if res.orderOpType??&&res.orderOpType==0 >
			      	人工充值
			      	<#elseif res.orderOpType??&&res.orderOpType==1 >
			      	自助网上充值
			      	<#else>
			      	其他
			      </#if></td>
			      <td align="center">${res.opValue?default("0.00")}</td>
			      <td align="center">${res.leftValue?default("0.00")}</td>
			    </tr>
			</#list>
			<#else>
			    <tr>
			      <td align="center" colspan="9" class="table_title">无相关记录</td>
			    </tr>
	    	</#if>
	  	</table>
		</div>
	<@paginate pageCount=accountDetailPages.totalPage currentPage=accountDetailPages.thisPageNumber url=pageUrl>
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