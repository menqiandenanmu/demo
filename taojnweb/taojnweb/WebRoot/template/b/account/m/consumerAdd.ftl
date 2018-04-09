<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<link href="${base}/script/datepicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new GeneralForm("validateForm","新增会员");
    });
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>新增会员</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1">&nbsp;<font size="2" color="#FF0033"></font></div>
    <form action="${base}/manage/account/consumer!save.htm" id="validateForm" method="get" >
    <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
        <tr>
          <td align="right" class="table_l_tit" width="150">登录名：</td>
          <td class="table_l_td">
          	<input name="consumerInfo.loginName" type="text" class="input_1_light" id="consumerInfo.loginName" size="30" maxlength="512"  validate="{required:true,minlength:3}" value=""/>
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">实名：</td>
          <td class="table_l_td">
			<input name="consumerInfo.realname" type="text" class="input_1_light" id="consumerInfo.realname" size="30" maxlength="512"  validate="{required:true,minlength:3}" value=""/>
          </td>
		</tr>
        <#--tr>
          <td align="right" class="table_l_tit">用户等级：</td>
          <td  align="left" class="table_l_td">
          	<select name="consumerInfo.accLevelId">
          		<#list levelList as level>
          			<option value="${level.id}">${level.levelName}</option>
          		</#list>
          	</select>
          </td>
        </tr-->
        <tr>
          <td align="right" class="table_l_tit">登录密码：</td>
          <td class="table_l_td">
          	<input name="consumerInfo.loginPass" type="password" class="input_1_light" id="consumerInfo.loginPass" size="30" maxlength="30"  validate="{required:true,minlength:3}" value=""/>
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">确认密码：</td>
          <td class="table_l_td" colSpan="3">
			<input name="loginPass2" type="password" class="input_1_light" id="loginPass2" size="30" maxlength="30"  validate="{equalTo:'#consumerInfo\\.loginPass'}" value=""/>
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">email：</td>
          <td align="left" class="table_l_td">
			<input name="consumerInfo.email" type="text" class="input_1_light" id="consumerInfo.email" size="50" maxlength="100"  validate="{email:true}" value=""/>
          </td>
        </tr>
        <tr>
         	<td align="right" class="table_l_tit">生日：</td>
         	<td align="left" class="table_l_td">
         	
			<input name="consumerInfo.birthday" type="text" class="input_1_light" id="consumerInfo.birthday" size="15" value="" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})" />         	
          	</td>
        </tr>
        <tr>
			<td align="right" class="table_l_tit">性别：</td>
			<td align="left" class="table_l_td">
			<select name="consumerInfo.sex">
				<option value="1">男</option>
				<option value="0">女</option>
			</select>
			</td>
        </tr>
        <tr>
        	<td align="right" class="table_l_tit">手机号：</td>
			<td align="left" class="table_l_td">
				<input name="consumerInfo.mobile" type="text" class="input_1_light" id="consumerInfo.mobile" size="30" maxlength="512" value="" validate="{mobile:true}" />         	
			</td>
		</tr>
        <tr>
         	<td align="right" class="table_l_tit">身份证号：</td>
         	<td align="left" class="table_l_td">
         		<input name="consumerInfo.idCard" type="text" class="input_1_light" id="consumerInfo.idCard" size="38" validate="{idcardno:true}"  maxlength="512" value=""/>         	
          	</td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">备注信息：</td>
         	<td align="left" class="table_l_td" colSpan="5">
				<textarea name="consumerInfo.remark" cols="45" rows="5" maxlength="100" class="textarea_1" id="consumerInfo.remark"></textarea>	
          	</td>
        </tr>
      </table>
    </div>
    <div class="tool_2">
        <div class="tool_2_L">
        <input type="submit" class="btn02" name="button" id="button1" value="添加" />
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