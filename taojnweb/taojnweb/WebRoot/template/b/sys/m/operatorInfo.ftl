<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "/template/m/tabs-header.ftl">
</head>
<body class="easyui-layout">
	<div data-options="region:'center',border:false" class="padding6">
		<table class="formtable">
			<tr>
				<td class="label">用户名：</td>
				<td>${accountInfo.accName!}</td>
				<td class="label">登录名：</td>
				<td>${accountLogin.loginName!}</td>
			</tr>
			<tr>
				<td class="label">真实姓名：</td>
				<td>${accountLogin.realName!}</td>
				<td class="label">用户状态：</td>
				<td><#if accountLogin.status=0>锁定<#else>正常</#if></td>
			</tr>
			<tr>
				<td class="label">生日：</td>
				<td>${accountLogin.birthday!}</td>
				<td class="label">性别：</td>
				<td><#if accountLogin.sex=1>男<#else>女</#if></td>
			</tr>
			<tr>
				<td class="label">手机号：</td>
				<td>${accountLogin.mobile}</td>
				<td class="label">电子邮件：</td>
				<td>${accountLogin.email!}</td>
			</tr>
			<tr>
				<td class="label">身份证号：</td>
				<td>${(accountLogin.idCard)!}</td>
				<td class="label">创建人：</td>
				<td>${(accountLogin2.loginName)!}</td>
			</tr>
			<tr>
				<td class="label">最后登录时间：</td>
				<td>${(accountLogin.lastLoginTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
				<td class="label">最后登录IP：</td>
				<td>${(accountLogin.lastIp)!}</td>
			</tr>
			<tr>
				<td class="label">登录次数：</td>
				<td colspan="3">${(accountLogin.loginCount)!}</td>
			</tr>
			<tr>
				<td class="label">备注：</td>
				<td colspan="3">${(accountLogin.remark)!}</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'south',border:false" class="dialog-button" style="height: 36px; overflow: hidden;">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="util.close();">关闭</a>
	</div>
</body>
</html>