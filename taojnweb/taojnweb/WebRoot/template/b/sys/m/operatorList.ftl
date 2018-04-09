<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/tabs-header.ftl">
<script type="text/javascript" src="${base}/js/b/sys/m/operator-list.js"></script>
</head>
<body>
	<div id="toolbar">
		<div id="searchpanel" class="padding6" style="background: #f4f4f4; border-bottom: 1px solid #95b8e7;">
			<label style="padding-left: 5px;">姓名：</label>
			<input id="realName" style="width: 90px;" class="easyui-validatebox">
			<label style="padding-left: 5px;">登录名：</label>
			<input id="loginName" style="width: 90px;" class="easyui-validatebox">
			<label style="padding-left: 5px;">管理员标识：</label>
			<select id="adminFlag" style="width: 100px" class="easyui-combobox">
				<option value="">--全部--</option>
				<option value="1">管理员</option>
				<option value="0">非管理员</option>
         	</select>
			<label style="padding-left: 5px;">用户状态：</label>
			<select id="status" style="width: 100px" class="easyui-combobox">
				<option value="">--全部--</option>
				<option value="1">正常</option>
				<option value="0">锁定</option>
         	</select>
			<a id="btnSearch" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a>
		</div>
		<div class="buttons">
			<a id="btnAdd" href="javascript:void(0)" class="easyui-linkbutton" onclick="btnAddHandler()" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a id="btnEdit" href="javascript:void(0)" class="easyui-linkbutton" onclick="btnEditHandler()" data-options="iconCls:'icon-edit',plain:true">编辑</a>
			<a id="btnDel" href="javascript:void(0)" class="easyui-linkbutton" onclick="btnDelHandler()" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<div class="datagrid-btn-separator"></div>
			<a id="btnEdit" href="javascript:void(0)" class="easyui-linkbutton" onclick="btnDetailHandler()" data-options="iconCls:'icon-info',plain:true">详细</a>
			<a id="btnEdit" href="javascript:void(0)" class="easyui-linkbutton" onclick="btnRoleHandler()" data-options="iconCls:'icon-remove',plain:true">角色</a>
			<div class="datagrid-btn-separator"></div>
			<a id="btnRefund" href="javascript:void(0)" class="easyui-linkbutton" onclick="btnRefundHandler()" data-options="iconCls:'icon-remove',plain:true">核销权限</a>
		</div>
		<div style="clear: both;"></div>
	</div>
	<table id="datagrid">
		<thead>
		<tr>
		  <th width="100" data-options="field:'realName'">姓名</th>
		  <th width="100" data-options="field:'loginName'">登录名</th>
		  <th  data-options="field:'email'">邮件</th>
		  <th width="60" data-options="field:'sex',formatter:formatter.sex">性别</th>
		  <th width="60" data-options="field:'status',formatter:formatter.status">状态</th>
		  <th width="80" data-options="field:'adminFlag',formatter:formatter.adminFlag">管理员标识</th>
		  <th width="80" data-options="field:'refundFlag',formatter:formatter.refundFlag">核销标识</th>
		  <th width="60" data-options="field:'loginCount'">登录次数</th>
		  <th width="160" data-options="field:'lastLoginTime',formatter:Format.datetime">最后登录时间</th>
		  <th data-options="field:'lastIp'">登录IP</th>
		</tr>
		</thead>
	</table>
</body>
</html>