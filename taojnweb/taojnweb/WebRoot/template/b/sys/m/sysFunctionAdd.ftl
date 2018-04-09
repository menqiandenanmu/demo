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
     	var fm=new GeneralForm("validateForm","新增模块菜单");
    });
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>新增模块菜单：</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1">&nbsp;<font size="2" color="#FF0033"></font></div>
    <form action="${base}/manage/sys/sysFunction!save.htm" id="validateForm" method="post" >
    <input type="hidden" name="sysFunctions.funType" value="1"/>
    <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
      	<tr>
          <td align="right" class="table_l_tit">名称：</td>
          <td class="table_l_td">
	          <input name="sysFunctions.funName" type="text" class="input_1_light" id="sysFunctions.funName" size="30" value=""  validate="{required:true}" />
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">平台类型：</td>
          <td class="table_l_td">
          	<select name="sysFunctions.funAccType" class="select_01">
          		<option value="0" selected>系统管理</option>
          		<option value="1">散客</option>
          	</select>
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">类型：</td>
          <td class="table_l_td">
          	功能URL
          </td>
        </tr>
        
        <tr>
          <td align="right" class="table_l_tit">URL：</td>
          <td class="table_l_td">
	          <input name="sysFunctions.funUrl" type="text" class="input_1_light" id="sysFunctions.funUrl" size="50" validate="{required:true}" />
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">排序：</td>
          <td class="table_l_td">
	          <input name="sysFunctions.sort" type="text" class="input_1_light" id="sysFunctions.sort" size="10" validate="{required:true}" />
          </td>
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
