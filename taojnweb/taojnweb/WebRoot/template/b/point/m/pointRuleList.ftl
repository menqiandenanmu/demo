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
        <div class="page-item page-title">积分规则</div>
        <!--索搜区域-->
        <form action="${base}/manage/point/pointRule.htm" id="searchForm" method="post" >
        <div class="page-item search-panel">
 		  <ul class="search-list clearfix">
 		   <li><span class="lab-title">行为:</span><span>
				<select name="pointRule.bnsType" style="width:120px;">
					<option value="">全部</option>
					<option value="0" <#if pointRule.bnsType==0>selected</#if>>充值</option>
					<option value="1" <#if pointRule.bnsType==1>selected</#if>>消费</option>
					<option value="2" <#if pointRule.bnsType==2>selected</#if>>退款</option>
				</select>
			</span>
			</li>
            <li><span class="lab-title">状态:</span><span>
           	 <select name="pointRule.useStatus" style="width:120px;">
					<option value="">全部</option>
					<option value="0" <#if pointRule.useStatus==0>selected</#if>>停用</option>
					<option value="1" <#if pointRule.useStatus==1>selected</#if>>启用</option>
				</select>
			</span>
			</li>
			<li><span class="lab-title">来源:</span><span>
				<span>
				<select name="pointRule.source" style="width:120px;">
						<option	value="" >全部</option>
					<#list sysDictDetails as item>
						<option	value=${item.dictDetailCode} <#if pointRule.source?exists&&pointRule.source==item.dictDetailCode >selected</#if>>${item.dictDetailName}</option>
					</#list>
				</select>
			</span>
			</li>
        </ul>
	 	 </div>
        <!--索搜区域结束-->
         <!--列表操作区域-->
        <div class="page-item list-handle">
        <input type="submit" value="搜索" class="btn btn-small bg-blue"/>&nbsp;&nbsp;&nbsp;&nbsp;
         <a href="javascript:;" onclick="location.href='${base}/manage/point/pointRule!add.htm'" class="btn btn-small bg-blue" id="add-btn">+新增</a>
        </div>
        </form>
       <!--列表操作区域-->
        <div class="page-item list-panel">
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                     <th class="w10" width="80px;">序号</th>
                     <th class="w10" width="80px;">赠送条件</th>
                     <th class="w10" width="80px;">赠送数量</th>
                     <th class="w10" width="80px;">行为</th>
                     <th class="w10" width="80px;">状态</th>
                     <th class="w10" width="80px;">来源</th>
                     <th class="w10" width="80px;">操作</th>
					 </tr>
					</thead>
                <tbody>
    	<#list pages.result as res>
		   <#assign flag=1>
		     <tr  id="${res.id}">
		      <td align="center">${res_index+1}</td>
		      <td align="left">${res.opValue!}</td>
		      <td align="center">${res.amount}</td>
		      <td align="right"><#if res.bnsType==0>充值<#elseif res.bnsType==1>消费<#elseif res.bnsType==2>退款</#if></td>
		      <td align="right"><#if res.useStatus==0>停用<#elseif res.useStatus==1>启用</#if></td>
		      <td align="right">
		      <#list sysDictDetails as item>
						<#if res.source==item.dictDetailCode >${item.dictDetailName}</#if>
					</#list>
		     </td>
		      <td align="center">
		      <a href="${base}/manage/point/pointRule!edit.htm?id=${res.id}">修改</a>
			 	<a href="javascript:common.doPost('${base}/manage/point/pointRule!del.htm?id=${res.id}','确定删除吗?');">删除</a>
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
 <@paginate pageCount=pages.totalPage currentPage=pages.thisPageNumber url=pageUrl></@paginate>

</div>
</div>
</div>
</body>
</html>