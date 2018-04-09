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
<#--script src="${base}/scripts/require.js" data-main="scripts/main"></script-->
</head>
<body>
<!--列表正文开始-->
    <div class="page-main">
        <div class="page-item page-title">数据字典</div>
        <!--索搜区域-->
        <div class="page-item search-panel">
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
                    <th class="w10" width="80px;">名称</th>
                    <th class="w10" width="80px;">编号</th>
                    <th class="w20" >说明信息</th>
                    <th class="w10" width="80px;">操作</th>
					 </tr>
					</thead>
                <tbody>
			    	<#list dictL as ind>
					      <td align="left">${ind_index+1}</td>
					      <td align="left">${ind.dictName}</td>
					      <td align="center">${ind.dictCode}</td>
					      <td align="left">${ind.remark}</td>
					      <td align="center">
					      	&nbsp;<a href="${base}/manage/sys/dict!detailList.htm?id=${ind.id}" class="a4font">编辑</a>
					      </td>
					    </tr>
					</#list>
					<#if dictL?size=0>
					    <tr>
					      <td align="center" colSpan="5" class="table_title">无相关记录</td>
					    </tr>
			    	</#if>
			</tbody>
        </table>
 <div class="list-bottom clearfix">
 </div>
</div>
</div>
</body>
</html>