<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "/template/m/tabs-header.ftl">
<script type="text/javascript" src="${base}/js/b/sys/m/operator-list.js"></script>
<script type="text/javascript">
jQuery(document).ready(function() {
	  $(document).ready(function() {
		var fm=new GeneralForm("operatorForm");
		$("#btnSave").click(function(){
		//验证表单
		var validateFp=$("#operatorForm").form('validate');
		if(validateFp) {
			fm.submit();
			}
		})	
	});
	// 扩展equals规则 
    $.extend($.fn.validatebox.defaults.rules, {  
        equals: {  
           validator: function(value,param){  
            return value == $(param[0]).val();  
           },  
           message: '密码不一致'  
        }  
    });  
});
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',border:false" class="padding6">
		<form id="operatorForm" action="${base}/manage/sys/operator!update.htm" method="post">
		 <input name="id" type="hidden" value="${accountLogin.id}" />
			<table class="formtable">
				<tr>
					<td class="label">登录名：</td>
					<input name="accountLogin.loginName" type="hidden"  id="loginName" size="30" value="${accountLogin.loginName?default("")}"/>
					<td> ${accountLogin.loginName!}</td>
					<td class="label">真实姓名：</td>
					<td><input name="accountLogin.realName" type="text" class="easyui-validatebox" data-options="required:true" value="${accountLogin.realName?default("")}"></td>
				</tr>
				<tr>
					<td class="label">用户密码：</td>
					<td><input id="pwd" name="accountLogin.loginPass" type="password" class="easyui-validatebox" data-options="required:true"></td>
					<td class="label">确认密码：</td>
					<td><input id="rpwd" type="password" class="easyui-validatebox" data-options="required:true" validType="equals['#pwd']"></td>					
				</tr>
				<tr>
				    <td class="label">用户状态：</td>
					<td>
						<select name="accountLogin.status" class="easyui-combobox" id="status" style="width:150px">
							<option value="1" <#if accountLogin.status==1>selected</#if>>正常</option>
							<option value="0" <#if accountLogin.status==0>selected</#if>>停用</option>
			         	</select>
					</td>
					 <td class="label">性别：</td>
				    <td>
						<select name="accountLogin.sex" class="easyui-combobox" id="sex" style="width:150px">
		         		<option value="1" <#if accountLogin.sex==1>selected</#if>>男</option>
		         		<option value="0" <#if accountLogin.sex==0>selected</#if>>女</option>
		         	    </select>	
		         	</td>		
				</tr>
				<tr>
					<td class="label">电子邮件：</td>
					<td colspan="3"><input name="accountLogin.email" type="text" class="easyui-validatebox"  data-options="required:true" value="${accountLogin.email?default("")}"></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'south',border:false" class="dialog-button" style="height: 36px; overflow: hidden;">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" id="btnSave">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="util.close();">关闭</a>
	</div>
</body>
</html>


