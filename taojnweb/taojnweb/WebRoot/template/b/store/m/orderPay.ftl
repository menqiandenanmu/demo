<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/template/m/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<link href="${base}/script/datepicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new GeneralForm("payForm","支付商城订单");
    });
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>支付商城订单</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1">订单</div>
    <input type="hidden" name="id" value="${orderInfo.id}"/>
    <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
      <tr>
          <td align="right" class="table_l_tit">订单号:</td>
          <td class="table_l_td" align="left">
			${orderInfo.orderNo}
          </td>
          <td align="right" class="table_l_tit">订单状态:</td>
          <td class="table_l_td" align="left" colSpan="2">
			<#if orderInfo.orderStatus=0>新建<#elseif orderInfo.orderStatus=1>成功<#elseif orderInfo.orderStatus=2>失败<#elseif orderInfo.orderStatus=3>退单</#if>
			(${orderInfo.createTime?string("yyyy-MM-dd HH:mm")})
          </td>
          </tr>
          <tr>
          <td align="right">订单内容:</td><td colSpan="5">${orderInfo.info}</td>
        </tr>
      </table>
    </div>
    </div>
    <div class="r_content">
    <div class="tool_1">支付信息</div>
    <form action="${base}/manage/store/order!payed.htm" id="payForm" method="get" >
    <input type="hidden" name="id" value="${orderInfo.id}"/>
    <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
          <tr>
          <td align="right">备注信息:</td>
          <td><textarea name="orderInfo.remark" validate="{required:true,maxlength:1000}" cols="50" rows="5">${orderInfo.remark}</textarea></td>
        </tr>
      </table>
    </div>
    <div class="tool_2">
        <div class="tool_2_L">
        <input type="submit" name="button" id="button1" value="确定已支付" />
        <input type="button" class="btn02" id="button" onclick="location.href='${backUrl}'" value="返 回" />
        </div>
        <div class="tool_2_R"></div>
    </div>
    </form>
    </div>
</div>
</div>
</body>
</html>