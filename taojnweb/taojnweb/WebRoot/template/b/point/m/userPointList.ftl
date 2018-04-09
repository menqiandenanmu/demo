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
        <div class="page-item page-title">用户积分</div>
        <!--索搜区域-->
	<form action="${base}/manage/point/userPoint.htm" id="searchForm" method="post" >
        <div class="page-item search-panel">
		<ul class="search-list clearfix">
            <li><span class="lab-title">
			用户：</span>
			<span><input type="text" name="pointAccountInfo.accName" class="text text-small" value="${pointAccountInfo.accName!}">
			</span></li>
			  <li><span class="lab-title">积分：</span><span>
			  <select name="pointNum" class="select1" style="width:100px;">
					<option value="">全部</option>
					<option value="0" <#if pointNum?exists && pointNum=0>selected</#if>>有积分</option>
					<option value="1" <#if pointNum?exists && pointNum=1>selected</#if>>无积分</option>
					</select>
			</span></li>								
	  </ul>
	 	 </div>
	 	  <!--列表操作区域-->
        <div class="page-item list-handle">
        <input type="submit" value="搜索" class="btn btn-small bg-blue"/>&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" class="btn btn-small bg-blue" onClick="location.href='${base}/manage/point/userPoint!listExport.htm?${queryString}'" value="导出">
        </div>
        </form>
        <!--索搜区域结束-->
       <!--列表操作区域-->
	<!--列表-->
        <div class="page-item list-panel">
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                     <th class="w10" width="80px;">序号</th>
                     <th class="w10" width="80px;">用户名称</th>
                     <th class="w10" width="80px;">数量</th>
                     <th class="w10" width="80px;">最后时间</th>
                     <th class="w10" width="80px;">操作</th>
					 </tr>
					</thead>
                <tbody>
    	<#list pointPage.result as res>
		   <#assign flag=1>
		     <tr  id="${res.id}">
		      <td align="center">${res_index+1}</td>
		      <td align="left">${res.accName!}</td>
		      <td align="right">${res.point!0}</td>
		      <td align="center">${(res.modifiedTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
		      <td align="center">
			 	&nbsp;<a href="${base}/manage/point/userPoint!add.htm?id=${res.id}">赠送</a>
			 	&nbsp;<a href="${base}/manage/point/userPointDetail.htm?accName=${res.accName!}">积分明细</a>
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
 <@paginate pageCount=pointPage.totalPage currentPage=pointPage.thisPageNumber url=pageUrl></@paginate>

</div>
</div>
</div>
</body>
</html>