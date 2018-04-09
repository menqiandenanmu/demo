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
     	var fm=new GeneralForm("validateForm","确定发送");
    });
   	function selectType(obj){
   		if(obj==0){
   			$("#allUserDiv").show();
   			$("#userDiv").hide();
   		}else if(obj==1){
   			$("#allUserDiv").hide();
   			$("#userDiv").show();
   		}
   	}
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>消息发送</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1">&nbsp;<font size="2" color="#FF0033"></font></div>
    <form action="${base}/manage/account/message!send.htm" id="validateForm" method="get" >
    <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
      <tr >
          <td align="right" class="table_l_tit" width="150px">发送类型：</td>
          <td class="table_l_td" align="left" id="tr" >
			<select name="sendType" id="sendType" onChange="selectType(this.options[this.options.selectedIndex].value)" >
					<option value="1"  selected="selected" id="frist">单个发送</option>
					<option value="0">群发</option>
			</select>	
			 <span id="userDiv" >
				<lable>用户名：<input type="text" name="message.accountName"/></lable>
			</span>	
			<span id="allUserDiv" style="display:none" >
				<select name='userType' id='userType'>
					<option value='1'>普通用户</option>
				</select>
			</span>
          </td>
        </tr>

			
         <tr>
          <td align="right" class="table_l_tit" width="150">标题：</td>
          <td class="table_l_td" align="left">
	          <input name="message.title" type="text" class="input_1_light" id="message.title" size="50" maxlength="50" value="" validate="{required:true,minlength:2}" />
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">消息类型：</td>
          <td class="table_l_td" align="left">
			<select name="message.type" >
					<option value="0">普通消息</option>
					<option value="1">系统消息</option>
			</select>		
          </td>
        </tr>
        <tr>
          <td width="90" align="right" class="table_l_tit">备注:</td>
          <td class="table_l_td" align="left">
			<textarea name="message.content" cols="45" rows="5" class="textarea_1" id="message.content" validate="{required:true,minlength:2}"></textarea>	
	      </td>
        </tr>
      </table>
    </div>
    <div class="tool_2">
        <div class="tool_2_L">
        <input type="submit" class="btn02" name="button" id="button1" value="发送" />
        <input type="button" class="btn02" name="button" id="button" onclick="location.href='${backUrl}'" value="返 回" />
        </div>
        <div class="tool_2_R"></div>
    </div>
    </form>
    </div>
</div>
</div>
</body>
</html>