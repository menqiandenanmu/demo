<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>会员信息</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1">&nbsp;<font size="2" color="#FF0033"></font></div>
    <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
        <tr>
          <td align="right" class="table_l_tit" width="150">登录名：</td>
          <td class="table_l_td">
	          ${consumerInfo.loginName}
          </td>
          <td align="right" class="table_l_tit" width="150">实名：</td>
          <td class="table_l_td">
	          ${consumerInfo.realname!}
          </td>
          <#--td align="right" class="table_l_tit" width="150">用户等级：</td>
          <td  align="left" class="table_l_td">
	          ${consumerInfo.accLevelName!}
          </td-->
        </tr>
        <tr>
         	<td align="right" class="table_l_tit">身份证号：</td>
         	<td align="left" class="table_l_td">
			${(consumerInfo.idCard)!}
          	</td>
          <td align="right" class="table_l_tit">email：</td>
          <td align="left" class="table_l_td">
			${(consumerInfo.email)!}
          </td>
          <td align="right" class="table_l_tit">用户状态：</td>
          <td align="left" class="table_l_td">
          	<#if consumerInfo.status=1>正常<#else>锁定</#if>
          </td>
        </tr>
        <tr>
         	<td align="right" class="table_l_tit">生日：</td>
         	<td align="left" class="table_l_td">
			${(consumerInfo.birthday)!}
          	</td>
			<td align="right" class="table_l_tit">性别：</td>
			<td align="left" class="table_l_td">
			<#if consumerInfo.sex?? && consumerInfo.sex==1>男<#elseif consumerInfo.sex?? && consumerInfo.sex==0>女<#else>空</#if>
			</td>
			<td align="right" class="table_l_tit">手机号：</td>
			<td align="left" class="table_l_td">
			${(consumerInfo.mobile)!}
			</td>
        </tr>
        <tr>
         	<td align="right" class="table_l_tit">注册时间：</td>
         	<td align="left" class="table_l_td">
			${(consumerInfo.createTime?string("yyyy-MM-dd HH:mm"))!}
          	</td>
			<td align="right" class="table_l_tit">登录次数：</td>
			<td align="left" class="table_l_td">
			${(consumerInfo.loginCount)!}
			</td>
			<td align="right" class="table_l_tit">最后登录时间：</td>
			<td align="left" class="table_l_td">
			${(consumerInfo.lastLoginTime?string("yyyy-MM-dd HH:mm"))!}
			</td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">备注信息：</td>
         	<td align="left" class="table_l_td" colSpan="5">
			${consumerInfo.remark}
          	</td>
        </tr>
      </table>
    </div>
    <div class="tool_2">
        <div class="tool_2_L">
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