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
        <div class="page-item page-title">通知历史记录</div>
        <!--索搜区域-->
        <div class="page-item search-panel">
		
	 	 </div>
        <!--索搜区域结束-->
       <!--列表操作区域-->
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                     <th class="w10" width="80px;">序号</th>
					  <th class="w10" width="80px;">流水号</th>
					  <th class="w20" width="80px;">通知内容</th>
					  <th class="w10" width="80px;">通知状态</th>
					  <th class="w10" width="80px;">通知时间</th>
					  <th class="w10" width="80px;">通知次数</th>
					 </tr>
					</thead>
                <tbody>
	    	<#list histories as res>
				<#assign flag=1>
			    <tr  id="${res.id}">
			      <td align="center">${res_index+1}</td>
			      <td align="center">${res.serialNo?default("")}</td>
			      <td align="center">${res.syncCon!}</td>
			         <td align="center"><#if res.syncStatus=="">未通知<#elseif res.syncStatus=="success">通知成功<#elseif res.syncStatus=="fail">通知失败</#if></td>
			      <td align="center">${res.createTime?string("yyyy-MM-dd")!}</td>
			      <td align="center">${res.syncNum!}</td>
			    </tr>
			</#list>
			<#if flag=0>
			    <tr>
			      <td align="center" colspan="6" class="table_title">无相关记录</td>
			    </tr>
	    	</#if>
	  </tbody>
        </table>
</div>
</div>
</body>
</html>