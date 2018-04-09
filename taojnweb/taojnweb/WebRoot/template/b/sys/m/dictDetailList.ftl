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
        <div class="page-item page-title">数据字典管理</div>
        <!--索搜区域-->
        <div class="page-item search-panel">
		</div>
        <!--索搜区域结束-->
        <!--列表操作区域-->
        <div class="page-item list-handle">
         <a onclick="location.href='${base}/manage/sys/dict!detailAdd.htm?id=${dict.id}'" class="btn btn-small bg-blue" id="add-btn">+新增</a>
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
                    <th class="w10" width="80px;">值</th>
                    <th class="w10" width="80px;">使用标志</th>
                    <th class="w20" width="80px;">说明信息</th>
                    <th class="w10" width="80px;">操作</th>
					</tr>
					</thead>
                <tbody>
			    	<#list detailL as ind>
					      <td align="left">${ind_index+1}</td>
					      <td align="center">${ind.dictDetailName}</td>
					      <td align="center">${ind.dictDetailCode}</td>
					      <td align="center">${ind.dictDetailValue}</td>
					      <td align="center">${ind.useFlag?string("使用中","未使用")}</td>
					      <td align="left">${ind.remark}&nbsp;</td>
					      <td align="center">
					      	<a href="${base}/manage/sys/dict!detailEdit.htm?id=${ind.id}"  class="a4font">编辑</a>
					      	<#--&nbsp;<a href="javascript:common.doPost('${base}/manage/sys/dict!detailDel.htm?id=${ind.id}','确定删除吗?');"  class="a4font">删除</a>-->
					      </td>
					    </tr>
					</#list>
					<#if detailL?size=0>
					    <tr>
					      <td align="center" colSpan="9" class="table_title">无相关记录</td>
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