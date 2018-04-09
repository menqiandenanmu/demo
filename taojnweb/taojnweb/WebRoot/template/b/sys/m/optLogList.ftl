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
        <div class="page-item page-title">系统操作日志列表</div>
        <!--索搜区域-->
  	<form action="${base}/manage/sys/sysOptLog.htm" id="searchForm" method="post" >
        <div class="page-item search-panel">
		 <ul class="search-list clearfix">
            <li><span class="lab-title">操作员</span><span><input maxlength="15" name="optLogVo.loginName" type="text" class="text text-small" value="${optLogVo.loginName?default("")}" size="12"/></span></li>
            <li><span class="lab-title">日志主题</span><span><input maxlength="15" name="optLogVo.title" type="text" class="text text-small" value="${optLogVo.title?default("")}" size="12"/></span></li>
            <li><span class="lab-title">内容</span><span><input maxlength="15" name="optLogVo.content" type="text" class="text text-small" value="${optLogVo.content?default("")}" size="12"/></span></li>
            <li><span class="lab-title">操作类型</span><span>
			<select name="optLogVo.optType"  style="width:180px;">
				<option value="">全部</option>
				<option value="0" <#if optLogVo.optType?exists && optLogVo.optType=="0">selected</#if>>系统日志</option>
				<option value="1" <#if optLogVo.optType?exists && optLogVo.optType=="1">selected</#if>>登录日志</option>
			</select>
			</span></li>
            <li><span class="lab-title">开始时间</span><span>
			<input name="optLogVo.beginDate" type="text" value="${(optLogVo.beginDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})" class="text text-small" />
			</span></li>
			 <li><span class="lab-title">结束时间</span><span>
			 <input class="text text-small" name="optLogVo.endDate" type="text" value="${(optLogVo.endDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
			 </span></li>
        </ul>
</div>
        <!--列表操作区域-->
        <div class="page-item list-handle">
           <input type="submit" value="搜索" class="btn btn-small bg-blue" />&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        	</form>
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
          <table class="list-table" id="listTable">
                <thead>
                    <tr>
                    <th class="w10" width="80px;">序号</th>
                       <th class="w20" width="100px;">主题</th>
                       <th class="w30" width="180px;">操作内容</th>
                       <th class="w10" width="80px;">操作员</th>
                       <th class="w20" width="180px;">操作时间</th>
                       <th class="w10" width="80px;">操作</th>
	 				 </tr>
					</thead>
                <tbody>
    	 <#list pages.result as a>
	    <#assign flag=1>
			    <tr >
		 	 <td align="center" width="80px;">${a_index+1}</td>
	      	<td align="center" width="100px;">${a.title}</td>
	      	<td align="left" width="180px;"><#if a.content?exists && a.content?length &gt; 30>${a.content[0..29]}..<#else>${a.content}</#if></td>
	      	<td align="left" width="80px;">${a.loginName!""}</td>
	     	 <td align="center" width="180px;">${(a.logTime?string("yyyy-MM-dd:HH:mm:ss"))!("&nbsp;")}</td>
	      	<td align="center" width="80px;">
		      	&nbsp;<a dialog="{width:800,height:300}" href="${base}/manage/sys/sysOptLog!info.htm?id=${a.id}" class="a4font" >详情</a>
		  </td>
		    </tr>
		</#list>
		<#if flag=0>
		    <tr>
		      <td align="center" colspan="6" class="table_title" >无相关记录</td>
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