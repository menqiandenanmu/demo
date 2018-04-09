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
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
</head>
<body>
<!--列表正文开始-->
    <div class="page-main">
        <div class="page-item page-title">账户统计列表</div>
        <!--索搜区域-->
        <div class="page-item search-panel">
		<form action="${base}/manage/account/mAccountCount.htm" id="searchForm" method="post" >
			 <ul class="search-list clearfix">
            <li><span class="lab-title">用户</span><span>
			<select name="tradeAccountDetailVo.accountId" >
				<#if agentList?? >
				<#list agentList as res>
				<option value="${res.id}" <#if tradeAccountDetailVo.accountId==res.id>selected</#if>>${res.corpName}</option>
				</#list>
				</#if>
			</span></li>
            <li><span class="lab-title">开始时间</span><span>
			<input name="tradeAccountDetailVo.begDate" type="text"value="${(tradeAccountDetailVo.begDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})" class="text text-small"/>
			</span></li>
			 <li><span class="lab-title">结束时间</span><span>
			 <input class="text text-small" name="tradeAccountDetailVo.endDate" type="text" value="${(tradeAccountDetailVo.endDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
			 </span></li>
            <li class="search-handle"><input type="submit" value="搜索" class="btn btn-small bg-blue"/></li>
        </ul>
		</form>
	 	 </div>
        <!--索搜区域结束-->
        <!--列表操作区域-->
        <div class="page-item list-handle">
        </div>
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                    <th class="w10" width="80px;">序号</th>
                    <th class="w10" width="80px;">用户名称</th>
                    <th class="w10" width="80px;">账户类型</th>
                    <th class="w10" width="80px;">业务类型</th>
                    <th class="w10" width="80px;">变动金额</th>
			 </tr>
					</thead>
                <tbody>
	    	<#list pages.result as res>
				<#assign flag=1>
			    <#if res_index%2=1>
			    <tr class="tr_cur1">
			    <#else>
			    <tr class="tr_cur2">
			    </#if>
			      <td align="left">${res_index+1}</td>
			      <td align="center">${res.accountName?default("")}</td>
			      <td align="center">${res.tradeAccName?default("")}</td>
			      <td align="center">
			      <#switch res.opType >
			      <#case 0>
			     	 充值
			      <#break>
			      <#case 1>
			     	 未定义
			      <#break>
			      <#case 2>
			     	 订单完成扣款
			      <#break>
			      <#case 3>
			     	 退订单
			      <#break>
			      </#switch>
			      </td>
			      <td align="center">${res.opValue?default("0.00")}</td>
			    </tr>
			</#list>
			<#if flag=0>
			    <tr>
			      <td align="center" colspan="9" class="table_title">无相关记录</td>
			    </tr>
	    	</#if>
	   </tbody>
        </table>
 <div class="list-bottom clearfix">
 <@paginate pageCount=pages.totalPage currentPage=pages.thisPageNumber url=pageUrl></@paginate>

</div>
</div>
</div>
</body>
</html>