<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<#include "/template/m/common/utils.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/script/datepicker/skin/WdatePicker.css" />
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript" src="${base}/script/common/utiltable.js"></script>
<script language="JavaScript">
 $(document).ready(function(){
     	var fm=new GeneralForm("validateForm","积分日统计");
    });
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
		<ul>
			<li>积分日统计</li>
		</ul>
	</div>
	<div class="r_content">
		<form action="${base}/manage/point/pointDayCount.htm" id="searchForm" method="get" >
			<div class="tool_search">
				交易类型：<select name="pointChangeDetal.opType">
				<option value="">--全部--</option>
				<option value="0" <#if pointChangeDetal.opType=0>selected</#if>>订单</option>
				<option value="1" <#if pointChangeDetal.opType=1>selected</#if>>系统赠送</option>
				<option value="2" <#if pointChangeDetal.opType=2>selected</#if>>兑换</option>
				</select>
			时间：<input name="begCreateDate" type="text"value="${(begCreateDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>-<input name="endCreateDate" type="text" value="${(endCreateDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
				<input type="submit" class="btn_search" name="button" id="searchButton" value="查询"/>
				<span class="shux"></span>
				<input type="button" class="btn_search" onClick="location.href='${base}/manage/point/userPoint!daycountExport.htm?${queryString}'" value="导出">
		   </div>
		</form>
	 	<div class="tableNav">
		<#assign flag=0>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablelistNav">
			<tr>
			  <td width="30%" align="center" class="table_title">日期</td>
			  <td width="30%" align="center" class="table_title">产生</td>
			  <td width="30%" align="center" class="table_title">消费</td>
			</tr>
	    	<#list detailPage.result as res>
				<#assign flag=1>
			    <#if res_index%2=1>
			    <tr class="tr_cur1">
			    <#else>
			    <tr class="tr_cur2">
			    </#if>
			      <td align="center" key="${res.timeDate}">${res.timeDate}</td>
			      <td align="right">${res.addPoint?default(0)}</td>
			      <td align="right">${res.cutPoint?default(0)}</td>
			    </tr>
			</#list>
			<#if detailPage.result?size=0>
			    <tr>
			      <td align="center" colspan="3" class="table_title">无相关记录</td>
			    </tr>
			   <#else>
				<tr  class="tr_cur1">
				<td key="kj" align="right">合计</td>
				<td align="right">${(sumCount['ADD_POINT'])!0}</td>
				<td align="right">${(sumCount['CUT_POINT'])!0}</td>
				</tr>
	    	</#if>
	  	</table>
		</div>
	<@paginate pageCount=detailPage.totalPage currentPage=detailPage.thisPageNumber url=pageUrl>
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