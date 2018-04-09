<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<meta name="Authors" content="Tomzhou">
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new GeneralForm("validateForm","新增操作员角色");
    });
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>新增操作员角色：</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1">&nbsp;<font size="2" color="#FF0033"></font></div>
    <form action="${base}/manage/sys/roleAdmin!save.htm" id="validateForm" method="post" >
    <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
      	<tr>
          <td align="right" class="table_l_tit">角色名称：</td>
          <td class="table_l_td">
	          <input name="role.roleName" type="text" class="input_1_light" id="roleName" size="30" value=""  validate="{required:true}" />
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">用户平台类型：</td>
          <td class="table_l_td">
          	<select name="role.roleAccType" class="select_01" id="select3">
          		<option value="0">系统管理</option>
          	</select>
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">启用状态：</td>
          <td class="table_l_td">
          	<select name="role.useFlag" class="select_01" id="select3">
          		<option value="1" selected>启用</option>
          		<option value="0">停用</option>
          	</select>
          </td>
        </tr> 
        <tr>
          <td align="right" class="table_l_tit">备 注：</td>
          <td class="table_l_td"><textarea name="role.remark" cols="45" rows="5" class="textarea_1" maxlength="512" id="textarea"></textarea></td>
        </tr>
      </table>
    </div>
    <div class="tool_2">
        <div class="tool_2_L">
    	    <input type="submit" class="btn02"id="button1" value="保存" />
	        <input type="button" class="btn02"id="button" onclick="window.history.back();" value="返 回" />
        </div>
        <div class="tool_2_R"></div>
    </div>
    </form>
    </div>
</div>
</div>


</body>
</html>
