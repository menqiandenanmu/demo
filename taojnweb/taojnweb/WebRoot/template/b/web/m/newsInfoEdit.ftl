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
     	var fm=new GeneralForm("validateForm","编辑资讯信息");
    });
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>资讯管理</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1"><font size="2" color="#FF0033">编辑资讯信息</font></div>
    <form action="${base}/manage/web/news!update.htm" id="validateForm" method="post" >
    <input name="id" type="hidden" value="${newsInfo.id}" />
     <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
      <tr>
       <td height="28" align="right" class="table_l_tit">标题：</td>
          <td>
            <input name="newsInfo.title" type="text" class="input_1_light" id="title" size="32" value="${newsInfo.title}" validate="{required:true}" />
	     
          </td>
          <tr>
          <td height="28" align="right" class="table_l_tit">图片：</td>
          <td align="left">
          <input name="newsInfo.titleImageId" type="hidden" class="input_1_light" id="newsInfo.titleImageId" value="${newsInfo.titleImageId!}" size="32" maxlengtd="50"/>
			<#if titleImg?exists>
			<@upload name="titleImg" multi="false" url="${base}/manage/uploadFile.htm">
			<li>
			<span class='upload'>${titleImg}</span>
        	<input  type='hidden' value='${titleImg}' name='titleImg' id='titleImg'/>
        	<a href='#' onclick='$(tdis).parent().remove();'>删除</a>
        	<a href='${titleImg}' target='_blank'>预览</a>
        	</li>
			</@upload>
			<#else>
			<@upload name="titleImg" multi="false" url="${base}/manage/uploadFile.htm">
			</@upload>
			</#if>
	      </td>
        </tr>
        </tr>
        <tr>
         <td height="28" align="right" class="table_l_tit">出处：</td>
          <td>
            <input name="newsInfo.source" type="text" class="input_1_light" id="source" size="32" value="${newsInfo.source}" validate="{required:true}" />
	     
          </td>
        </tr>
        <tr>
         <td height="28" align="right" class="table_l_tit">作者：</td>
          <td>
            <input name="newsInfo.auth" type="text" class="input_1_light" id="autd" size="32" value="${newsInfo.auth}" validate="{required:true}" />
	     
          </td>
        </tr>
         <tr>
          <td height="28" align="right" class="table_l_tit">内容：</td>
          <td>
	         ${fckContent}
          </td>
        </tr>
        <tr>
         <td height="28" align="right" class="table_l_tit">关键字：</td>
          <td>
            <input name="newsInfo.keyWorks" type="text" class="input_1_light" id="keyWorks" size="32" value="${newsInfo.keyWorks!}"/>
	       
          </td>
        </tr>
        <tr>
         <td height="28" align="right" class="table_l_tit">分类名称：</td>
          <td class="table_6_td">
          	<select name="newsClassCodeId" class="select w100" validate="{required:true}">
          		<#list classList as res>
          			<option value="${res.id}" <#if newsInfo.classCode?exists && newsInfo.classCode==res.code>selected</#if>>${res.name?default("")}</option>
          		</#list>
          	</select>
          </td>
        </tr>
         <tr>
          <td height="28" align="right" class="table_l_tit">摘要：</td>
          <td>
          <textarea name="newsInfo.summary" cols="45" rows="5" class="textarea_1" id="textarea" validate="maxlength:1000">${newsInfo.summary!}</textarea>
          </td>
        </tr>
         <tr>
          <td height="28" align="right" class="table_l_tit">排序值：</td>
          <td>
            <input name="newsInfo.orderid" type="text" class="input_1_light" id="keyWorks" size="32" value="${newsInfo.orderid!}" validate="{number:true}" />
          </td>
        </tr>
         <tr>
          <td height="28" align="right" class="table_l_tit">页面元描述：</td>
          <td>
            <input name="newsInfo.description" type="text" class="input_1_light" id="keyWorks" size="32" value="${newsInfo.description!}" validate="{required:true}" />
          </td>
        </tr>
        <tr>
        <td height="28" align="right" class="table_l_tit">页面关键字：</td>
          <td>
            <input name="newsInfo.keywords" type="text" class="input_1_light" id="keyWorks" size="32" value="${newsInfo.keywords!}" validate="{required:true}" />
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