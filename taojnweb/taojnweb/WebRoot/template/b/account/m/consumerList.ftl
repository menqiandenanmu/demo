<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<#include "/template/m/common/utils.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
		<ul>
			<li>会员管理</li>
		</ul>
	</div>
	<div class="r_content" style="min-width:1084px;">
		<form action="${base}/manage/account/consumer.htm" id="searchForm" method="get" >
			<div class="tool_search">
				登录名：<input name="consumerInfo.loginName" type="text" class="input_1" value="${(consumerInfo.loginName)!}" size="20" maxlength="50" />
				&nbsp;手机号：<input name="consumerInfo.mobile" type="text" class="input_1" value="${(consumerInfo.mobile)!}" size="20" maxlength="50"/>
				&nbsp;身份证号：<input name="consumerInfo.idCard" type="text" class="input_1" value="${(consumerInfo.idCard)!}" size="20" maxlength="50"/>
				&nbsp;电子邮件：<input name="consumerInfo.email" type="text" class="input_1" value="${(consumerInfo.email)!}" size="20" maxlength="50"/>
				<input type="submit" class="btn_search" name="button" id="searchButton" value="查询"/>
				<span class="shux"></span>
				<input type="button" class="btn_search" name="button2" id="button2" onclick="location.href='${base}/manage/account/consumer!add.htm'" value="新增" />
		   </div>
		</form>
	 	<div class="tableNav">
		<#assign flag=0>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablelistNav">
			<tr>
			  <td width="50" align="center" class="table_title">序号</td>
			  <td width="100" align="center" class="table_title">登录名</td>
			  <td width="60" align="center" class="table_title">实名</td>
			  <td width="100" align="center" class="table_title">手机号</td>
			  <td width="150" align="center" class="table_title">身份证号</td>
			  <td width="150" align="center" class="table_title">电子邮件</td>
			  <td width="50" align="center" class="table_title">性别</td>
			  <td width="100" align="center" class="table_title">注册时间</td>
			  <td width="50" align="center" class="table_title">状态</td>
			  <td width="130" align="center" class="table_title tit0">&nbsp;操作</td>
			</tr>
	    	<#list consumerPage.result as res>
				<#assign flag=1>
			    <#if res_index%2=1>
			    <tr class="tr_cur1">
			    <#else>
			    <tr class="tr_cur2">
			    </#if>
			     <td align="left">${res_index+1}</td>
			      <td align="left">${res.loginName?default("")}</td>
			      <td align="left">${res.realname?default("")}</td>
			      <td align="left">${res.mobile!}</td>
			      <td align="left">${res.idCard!}</td>
			      <td align="left">${res.email!}</td>
			      <td align="center"><#if res.sex?? && res.sex==1>男<#elseif res.sex?? && res.sex==0>女<#else>空</#if></td>
			      <td align="center">${res.createTime?string("yyyy-MM-dd")}</td>
			      <td align="center">
			      <#if res.status==0>锁定
			      <#elseif res.status==1>正常
			      </#if>
			      </td>
			      <td align="center">
			      	&nbsp;<a href="${base}/manage/account/consumer!info.htm?id=${res.id}">详情</a>
			      	<#if res.status==0>
			      	<a href="javaScript:common.doPost('${base}/manage/account/consumer!start.htm?id=${res.id}','确定启用吗?');">启用</a>
			      	</#if>
			      		<#if res.status==1>
			      	<a href="javaScript:common.doPost('${base}/manage/account/consumer!lock.htm?id=${res.id}','确定锁定吗?');">锁定</a>
			      	</#if>
			      	&nbsp;<a href="${base}/manage/account/consumer!pass.htm?id=${res.id}">密码修改</a>
			      </td>
			    </tr>
			</#list>
			<#if flag=0>
			    <tr>
			      <td align="center" colSpan="10" class="table_title">无相关记录</td>
			    </tr>
	    	</#if>
	  	</table>
		</div>
	<@paginate pageCount=consumerPage.totalPage currentPage=consumerPage.thisPageNumber url=pageUrl>
	</@paginate>
	</div>
<div class="tool_2">
    <div class="tool_2_L"></div>
    <div class="tool_2_R"></div>
</div>
</div>
</div>
</body>
</html>