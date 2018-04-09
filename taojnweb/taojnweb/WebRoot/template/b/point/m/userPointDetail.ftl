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
        <div class="page-item page-title">积分操作明细</div>
        <!--索搜区域-->
		<form action="${base}/manage/point/userPointDetail.htm" id="searchForm" method="get" >
        <div class="page-item search-panel">
	
			<ul class="search-list clearfix">
            <li><span class="lab-title">
				用户：</span>
				<span><input type="text" name="accName" class="text text-small" value="${accName!}"></span>
				<li><span class="lab-title">订单号：</span><span><input type="text" class="text text-small" name="pointChangeDetal.orderNo" value="${(pointChangeDetal.orderNo)!}"></span>
				<li><span class="lab-title">交易类型：</span><span><select name="pointChangeDetal.opType"  class="select1" style="width:120px;">
				<option value="">全部</option>
				<option value="0" <#if pointChangeDetal.opType=0>selected</#if>>订单</option>
				<option value="1" <#if pointChangeDetal.opType=1>selected</#if>>系统赠送</option>
				<option value="2" <#if pointChangeDetal.opType=2>selected</#if>>兑换</option>
				<option value="3" <#if pointChangeDetal.opType=3>selected</#if>>消费</option>
				<option value="4" <#if pointChangeDetal.opType=4>selected</#if>>充值</option>
				</select>
				</span>
				<br></br>
				<li><span class="lab-title">
				时间：</span><span><input name="begCreateDate" class="text text-small" type="text" value="${(begCreateDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/></span>
				</li>
				<li><span class="lab-title"><input name="endCreateDate" class="text text-small" type="text" value="${(endCreateDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/></span>
				</li>
	 	 </div>
	 	  <!--列表操作区域-->
        <div class="page-item list-handle">
        <input type="submit" value="搜索" class="btn btn-small bg-blue"/>&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" class="btn btn-small bg-blue" onClick="location.href='${base}/manage/point/userPoint!detailExport.htm?${queryString}'" value="导出">
        </div>
        </form>
        <!--索搜区域结束-->
       <!--列表操作区域-->
	<!--列表-->
        <div class="page-item list-panel">
            <table class="list-table" id="listTable">
                <thead>
			<tr>
                     <th class="w5" width="80px;">序号</th>
                     <th class="w10" width="80px;">用户名</th>
                     <th class="w10" width="80px;">时间</th>
                     <th class="w5" width="80px;">交易类型</th>
                     <th class="w10" width="80px;">订单号</th>
                     <th class="w5" width="80px;">数量</th>
                     <th class="w5" width="80px;">当前数量</th>
                     <th class="w10" width="80px;">备注信息</th>
			</tr>
	    	<#list detailPage.result as res>
				 <#assign flag=1>
		    	 <tr  id="${res.id}">
			      <td align="left">${res_index+1}</td>
			      <td align="left">
			      <#if res.accName?length lt 12>
			      ${res.accName!("&nbsp;")}
			      <#else>
			      ${res.accName[1..11]}..
			      </#if>
			     </td>
			      <td align="center">${(res.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
			      <td align="center">
			      	<#if res.opType=0>订单
			      		<#elseif res.opType=1>系统赠送
			      		<#elseif res.opType=2>兑换
			      		<#elseif res.opType=3>消费
			      		<#elseif res.opType=4>充值
			      	</#if>
			      </td>
			      <td align="left">${res.orderNo?default("")}</td>
			      <td align="right">${res.point?default(0)}</td>
			      <td align="right">${res.leftPoint?default(0)}</td>
			      <td align="center">
			      <#if res.remark?length lt 15>${res.remark!("&nbsp;")}
		      		<#else>
		     	 	${res.remark[0..14]}..
		   	 	  </#if>
			    </td>
			    </tr>
			</#list>
			<#if flag=0>
			    <tr>
			      <td align="center" colspan="6" class="table_title">无相关记录</td>
			    </tr>
	    	</#if>
	  </tbody>
        </table>
 <div class="list-bottom clearfix">
 <@paginate pageCount=detailPage.totalPage currentPage=detailPage.thisPageNumber url=pageUrl></@paginate>

</div>
</div>
</div>
</body>
</html>