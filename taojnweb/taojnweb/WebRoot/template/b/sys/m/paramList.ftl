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
<script language="JavaScript">
	function refresh(){
		$.ajax({
		  url: "${base}/manage/sys/param!refresh.htm",
		  dataType:"json",
		  success: function(obj){
		    	alert(obj.message[0]);
		  }
		});
	}
</script>
</head>

<body>

<!--列表正文开始-->
    <div class="page-main">
        <div class="page-item page-title">参数列表</div>
        <!--索搜区域-->
        <div class="page-item search-panel">
  	<form action="${base}/manage/sys/param.htm" id="searchForm" method="get" >
		 <ul class="search-list clearfix">
            <li><span class="lab-title">名称：</span><span><input name="param.paramName" type="text" class="text text-small" value="${(param.paramName)!}" size="12" /></span></li>
			<li><span class="lab-title">编码：</span><span><input name="param.paramCode" type="text" class="text text-small" value="${(param.paramCode)!}" size="12" /></span></li>
			<li class="search-handle"><input type="submit" value="搜索" class="btn btn-small bg-blue"/></li>
        </ul>
        <#if accountInfo.id=0>
			<input type="button" class="text" name="button2" id="button2" onclick="location.href='${base}/manage/sys/param!add.htm'" value="新增" />
	   		</#if>
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
                    <th class="w20" width="80px;">参数名称</th>  	
                    <th class="w10" width="80px;">编码</th>  	
                    <th class="w10" width="80px;">参数值</th>  	
                    <th class="w20" width="80px;">备注</th>  	
                    <th class="w10" width="80px;">操作</th>  	
	     		</tr>
			</thead>
        <tbody>
	    <#list paramL as a>
	    <#assign flag=1>
	    <tr >
	      <td align="center" width="80px;">${a_index+1}</td>
	      <td align="left">${a.paramName}</td>
	      <td align="left">${a.paramCode}</td>
	      <td align="left">${a.paramValue?default("")}</td>
	      <td align="left"><div style="height:25px;overflow:hidden;">${a.remark?default("")}</div></td>
	      <td align="left">
	      &nbsp;<a href="${base}/manage/sys/param!edit.htm?id=${a.id}"  class="a4font">编辑</a>
	      <#if accountInfo.id=0>
	      &nbsp;<a href="javascript:common.doPost('${base}/manage/sys/param!del.htm?id=${a.id}','确定要删除参数吗?');"  class="a4font">删除</a></td>
	      </#if>
	    </tr>
	    </#list>
    <tr>
		 <td align="center" colspan="6" class="table_title" >
		系统参数设置会景响系统的运行,请谨慎操作.设置完后请按!
        <button  title="刷新" onclick="javascript:common.doPost('${base}/manage/sys/param!refresh.htm','确定要刷新参数吗?');" class="btn btn-small bg-blue">刷新</button>使系统参数生效!
        </td>    
 </tbody>
        </table>
 <div class="list-bottom clearfix">
</div>
</div>
</div>
</body>
</html>
