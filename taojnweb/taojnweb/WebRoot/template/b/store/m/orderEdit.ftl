<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/template/m/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<link href="${base}/script/datepicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript" src="${base}/script/common/area2.js"></script>
<script language="JavaScript">
    $(document).ready(function(){
    	var areaCode=$("#areaOcx").val();
    	var area1=new area();
		if(areaCode!=""){
			area1.setupById("country","city","province","areaOcx",-1,areaCode);
		}else{
			area1.setupById("country","city","province","areaOcx",-1,"001001001");
		}
     	var fm=new GeneralForm("editForm","修改商品订单");
    });
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>修改商品订单</li>
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
    <div class="tool_1">联系人信息</div>
    <form action="${base}/manage/store/order!update.htm" id="editForm" method="get" >
    <input type="hidden" name="id" value="${orderInfo.id}"/>
    <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
      <tr>
          <td align="right" class="table_l_tit" width="300">联系人:</td>
          <td class="table_l_td" align="left">
			<input type="text" value="${orderInfo.linkName!}" name="orderInfo.linkName" validate="{required:true,maxlength:20,minlength:2}"/>
          </td>
		</tr>
        <tr>
          <td align="right" class="table_l_tit">手机号:</td>
          <td class="table_l_td" align="left">
			<input type="text" value="${orderInfo.linkMobile!}" name="orderInfo.linkMobile" validate="{required:true,maxlength:11,minlength:11}"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">身份证号:</td>
          <td class="table_l_td" align="left">
			<input type="text" value="${orderInfo.linkIdcard!}" name="orderInfo.linkIdcard" validate="{maxlength:19,minlength:15}"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">客源地:</td>
          <td class="table_l_td" align="left">
          	<select id="country"></select>
          	<select id="city"></select>
          	<select id="province"></select>
          	<input type="hidden" id="areaOcx" name="orderInfo.areaCode" value="${orderInfo.areaCode}"/>
          </td>
        </tr>
        <tr>
          <td align="right">备注信息:</td>
          <td><textarea name="orderInfo.remark" validate="{required:true,maxlength:1000}" cols="50" rows="5">${orderInfo.remark}</textarea></td>
        </tr>
      </table>
    </div>
    <div class="tool_2">
        <div class="tool_2_L">
        <input type="submit" class="btn02" name="button" id="button1" value="确  定" />
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