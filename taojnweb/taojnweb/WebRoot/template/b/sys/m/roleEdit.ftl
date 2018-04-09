<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<link href="${base}/style/m/css/reset.css" rel="stylesheet" type="text/css">
<link href="${base}/style/m/css/common.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/page.css"/>
<!--[if lte IE 8]>
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/ie8.css" />
<![endif]-->
<script src="${base}/scripts/require.js" data-main="scripts/main"></script>
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new BackUrlForm("validateForm","编辑操作员角色","${base}/manage/sys/role.htm");
});
</script>
</head>
<body>
<!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">角色编辑</div>
                <div class="page-item form-panel">
 				<form action="${base}/manage/sys/role!update.htm" id="validateForm" method="post" >
 				  <input name="id" type="hidden" value="${role.id}"/>
                        <ul>
                            <li><span class="lab-title">角色名称</span>
                                <div class="form-item">
                                   <input name="role.roleName" type="text" class="text" id="roleName" size="30" value="${role.roleName}"  validate="{required:true}" />
                                   	<input type="hidden" name="role.roleAccType" value="0" />
                                </div>
                            </li>
                           
                            <li><span class="lab-title">启用状态</span>
                                <div class="form-item">
                                	<select name="role.useFlag"  id="select">
						          		<option value="true" <#if role.useFlag?exists && role.useFlag==true>selected</#if>>启用</option>
          								<option value="false" <#if role.useFlag?exists && role.useFlag==false>selected</#if>>停用</option>
						          	</select>
                                </div>
                            </li>
                            <li><span class="lab-title">描述</span>
                                <div class="form-item">
                                    <textarea name="role.remark" id="" class="textArea">${role.remark?default("")}</textarea>
                                </div>
                            </li>
                        </ul>
                        <div class="form-handle">
                            <input type="submit" value="提交" class="btn btn-small bg-blue"/>
                        </div>
                    </form>
                </div>
            </div>
            <!--列表正文结束-->

</body>
</html>
