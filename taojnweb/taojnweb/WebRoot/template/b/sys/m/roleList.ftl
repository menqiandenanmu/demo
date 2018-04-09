<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>对账平台</title>
<#include "/template/m/common/head.ftl">
<#include "/template/m/common/utils.ftl">
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
        <div class="page-item page-title">角色管理</div>
        <!--索搜区域-->
	        <form action="${base}/manage/sys/role.htm" id="searchForm" method="get" >
        <div class="page-item search-panel">
	            <ul class="search-list clearfix">
	                <li><span class="lab-title">名称</span><span><input maxlength="15" name="role.roleName" type="text" class="text text-small" value="${role.roleName?default("")}" size="12"/></span></li>
	            </ul>
        </div>
        <!--索搜区域结束-->
        <!--列表操作区域-->
        <div class="page-item list-handle">
           <input type="submit" value="搜索" class="btn btn-small bg-blue" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:;" onclick="location.href='${base}/manage/sys/role!add.htm'" class="btn btn-small bg-blue" id="add-btn">+新增角色</a>
        </div>
	        </form>
        <!--列表操作区域结束-->
        <!--列表-->
        <div class="page-item list-panel">
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                        <th class="w10">序号</th>
                        <th class="w20">名称</th>
                        <th class="w15">状态</th>
                        <th class="w15">备注信息</th>
                        <th class="w20">操作</th>
                    </tr>
                </thead>
                <tbody>
                   <#list rolePage.result as res>
						<#assign flag=1>
					    <tr>
					      <td align="left">${res_index+1}</td>
					      <td align="left">${res.roleName?default("")}</td>
					      <td align="left">${res.useFlag?string("使用","停用")}</td>
					      <td align="left">${res.remark?default("")}</td>
	                        <td>
			              	<a href="${base}/manage/sys/role!edit.htm?id=${res.id}">编辑</a>
					      	<#if res.accountId==res.createLoginId>&nbsp;<a href="${base}/manage/sys/role!infoEdit.htm?id=${res.id}">权限</a></#if>
					      	&nbsp;<a href="javascript:common.doPost('${base}/manage/sys/role!del.htm?id=${res.id}','确定要删除吗?');">删除</a>
	                        </td>
	                    </tr>
                   </#list>
				<#if flag=0>
				 <tr>
			      <td align="center" colSpan="5" >无相关记录</td>
			    </tr>
    			</#if>
                </tbody>
            </table>
           <div class="list-bottom clearfix">
            <@paginate pageCount=rolePage.totalPage currentPage=rolePage.thisPageNumber url=pageUrl></@paginate>
           </div>
        </div>
    </div>
<!--列表正文结束-->
</body>
</html>