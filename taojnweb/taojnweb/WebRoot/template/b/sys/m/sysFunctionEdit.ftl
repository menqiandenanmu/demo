<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new GeneralForm("validateForm","修改模块菜单信息");
    });
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>修改模块菜单信息</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1">&nbsp;<font size="2" color="#FF0033"></font></div>
    <form action="${base}/manage/sys/sysFunction!update.htm" id="validateForm" method="get" >
    <div class="tableNav_2">
		<input name="id" type="hidden" value="${sysFunctions.id}" />
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
        <tr>
          <td align="right" class="table_l_tit" width="150">名称：</td>
          <td class="table_l_td" align="left">
  	          <input name="sysFunctions.funName" type="text" class="input_1_light" id="sysFunctions.funName" size="20" maxlength="512"  validate="{required:true,minlength:2}" value="${(sysFunctions.funName)!}"/> 
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">平台类型：</td>
          <td class="table_l_td" align="left">
	          <#if sysFunctions.funAccType=0>系统管理<#elseif sysFunctions.funAccType=1>散客</#if>
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">类型：</td>
          <td class="table_l_td" align="left">
          	<input type="hidden" name="sysFunctions.funType" value="${sysFunctions.funType}"/>
				<#if sysFunctions.funType=0>组</#if>
				<#if sysFunctions.funType=1>功能URL</#if>
          </td>				
        </tr>
        <#if sysFunctions.funType=1>
        <tr>
          <td align="right" class="table_l_tit">URL：</td>
          <td class="table_l_td" align="left">
	          <input name="sysFunctions.funUrl" type="text" class="input_1_light" id="sysFunctions.funUrl" size="50"  maxlength="512" value="${(sysFunctions.funUrl)!}" />
          </td>
        </tr>
        </#if>
        <tr>
          <td align="right" class="table_l_tit">排序：</td>
          <td class="table_l_td" align="left">
	          <input name="sysFunctions.sort" type="text" class="input_1_light" id="sysFunctions.sort" size="10" maxlength="50" validate="{required:true}" value="${sysFunctions.sort}" />
          </td>
        </tr>
        <#if sysFunctions.funType=1>
        <tr>
          <td align="right" class="table_l_tit">父模块：</td>
          <td class="table_l_td" align="left">
          	  <select name="sysFunctions.parentId">
          	  <#list functions as function>
				<option value="${function.id}" <#if sysFunctions.parentId=function.id>selected</#if>>${(function.funName)!}</option>
          	  </#list>
          	  </select>
          </td>
        </tr>
        </#if>
      </table>
    </div>
    <div class="tool_2">
        <div class="tool_2_L">
        <input type="submit" class="btn02" name="button" id="button1" value="修改" />
        <input type="button" class="btn02" name="button" id="button" onclick="window.history.back();" value="返 回" />
        </div>
        <div class="tool_2_R"></div>
    </div>
    </form>
    </div>
</div>
</div>
</body>
</html>