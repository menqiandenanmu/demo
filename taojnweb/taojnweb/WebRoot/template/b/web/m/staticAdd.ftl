<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<#include "/template/m/common/uploadfile.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new GeneralForm("validateForm","添加系统静态说明信息");
    });
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>添加系统静态说明信息</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1">&nbsp;<font size="2" color="#FF0033"></font></div>
    <form action="${base}/manage/web/static!save.htm" id="validateForm" method="post" >
    <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
      	<tr>
          <td align="right" class="table_l_tit">名称：</td>
          <td class="table_l_td">
	          <input name="info.name" type="text" class="input_1_light" id="info.name" size="30" validate="{required:true,minlength:2,maxlength:200}" />
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">关键字：</td>
          <td class="table_l_td">
	          <input name="info.keystr" type="text" class="input_1_light" id="info.title" size="30" validate="{required:true,minlength:2,maxlength:200}" />
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">分类：</td>
          <td class="table_l_td">
			<select name="info.classCode" validate="{required:true}">
				<option value="">请选择</option>
				<#list dictList as dictIndex>
				<option value="${dictIndex.dictDetailCode}">${dictIndex.dictDetailName}</option>
				</#list>			
			</select>
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">说明文字：</td>
          <td class="table_l_td">
          	${fckContent}
          </td>
        </tr>
      </table>
    </div>
    <div class="tool_2">
        <div class="tool_2_L">
        <input type="submit" class="btn02" value="添加" />
        <input type="button" class="btn02" onclick="location.href='${backUrl}';" value="返 回" />
        </div>
        <div class="tool_2_R"></div>
    </div>
    </form>
    </div>
</div>
</div>
</body>
</html>