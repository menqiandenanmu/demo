<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new GeneralForm("validateForm","编辑资讯分类");
    });
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>资讯分类管理</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1">
      <font size="2" color="#FF0033">编辑资讯分类</font>
    </div>
    <form action="${base}/manage/web/newsClass!update.htm" id="validateForm" method="post" >
    <input name="id" type="hidden" value="${newsClass.id}" />
    <input name="newsClass.parent_names" type="hidden" class="inputtext w400" id="info" size="32" value="${newsClass.parent_names}"/>
     <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
      <tr>
          <td align="right" class="table_l_tit">资讯标题：</td>
          <td table_l_td>
            <input name="newsClass.name" type="text" class="input_1_light" id="name" size="32" value="${newsClass.name}" validate="{required:true}" />
          </td>
        </tr>
        <tr>
         <td align="right" class="table_l_tit">编号：</td>
          <td table_l_td>
            <input name="newsClass.code" type="text" class="input_1_light" id="code" size="32" value="${newsClass.code}" validate="{required:true,maxlength:6,minlength:3}" />
	       
          </td>
        </tr>
         <tr>
          <td align="right" class="table_l_tit">说明信息：</td>
          <td table_l_td>
           <textarea name="newsClass.info" cols="45" rows="5" class="textarea_1" id="textarea2" validate="{maxlength:200}">${newsClass.info}</textarea>
          </td>
        </tr>
            
     </table>
    </div>
    <div class="tool_2">
        <div class="tool_2_L">
        <input type="submit" class="btn02" id="button1" value="保存" />
        <input type="button" class="btn02" id="button" onclick="location.href='${backUrl}';" value="返 回" />
        </div>
        <div class="tool_2_R"></div>
    </div>
    </form>
    </div>
</div>
</div>
</body>
</html>