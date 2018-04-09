<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>消息详细</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1">&nbsp;<font size="2" color="#FF0033"></font></div>
    <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
        <tr>
          <td align="right" class="table_l_tit">用户名：</td>
          <td class="table_l_td" align="left">
          ${message.accountName!("&nbsp;")}
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit" width="150">标题：</td>
          <td class="table_l_td" align="left">
	          ${message.title!("&nbsp;")}
          </td>
        </tr>
         <tr>
          <td align="right" class="table_l_tit">消息类型：</td>
          <td class="table_l_td" align="left">
          	 <#if message.type=1>
          	 普通消息
          	 <#else>
          	  系统消息
          	 </#if>
          	
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">发送人：</td>
          <td class="table_l_td" align="left">
          	${message.senderName!("&nbsp;")}
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">内容：</td>
          <td class="table_l_td" align="left">
          	${message.content!0}
          </td>
        </tr>
         <tr>
          <td align="right" class="table_l_tit">是否已读：</td>
          <td class="table_l_td" align="left">
           <#if message.readFlag=0>未读<#else>已读</#if>
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">创建时间：</td>
          <td class="table_l_td" align="left">
	          ${message.createTime?string("yyyy-MM-dd HH:mm:ss")!}
          </td>
        </tr>
      </table>
    </div>
    <div class="tool_2">
        <div class="tool_2_L">
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