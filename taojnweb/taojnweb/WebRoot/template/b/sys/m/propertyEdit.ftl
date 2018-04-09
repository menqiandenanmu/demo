<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/template/m/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<script>
	$(document).ready(function(){
     	var fm=new GeneralForm("validateForm","修改配置参数信息");
    });
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>配置参数编辑：</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1"></div>
    <form action="${base}/manage/sys/property!update.htm" id="validateForm" method="post" submit="submit.disable=true">
    <input type="hidden" name="key" value="${key}" />
    <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
        <tr>
          <td align="right" class="table_l_tit">参数key:</td>
          <td class="table_l_td">
          	${key}
          </td>
        </tr>
        <tr>
          <td width="90" align="right" class="table_l_tit">参数值:</td>
          <td class="table_l_td">
          	<input name="value" type="text" class="input_1_light" validate="{required:true}" value="${value}" size="100" />
	      </td>
        </tr> 
      </table>
    </div>
    
    <div class="tool_2">
        <div class="tool_2_L">
        <input type="submit" class="btn02" name="submit" id="button" value="修改" />
        <input type="button" class="btn02" name="button" id="button" onclick="window.history.back();" value="返 回" />
        </div>
        <div class="tool_2_R">
        </div>
    </div>
    </form>
    </div>
</div>
</div>


</body>
</html>
