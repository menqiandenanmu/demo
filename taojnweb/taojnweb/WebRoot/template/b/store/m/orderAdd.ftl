<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/template/m/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<script language="JavaScript" type="text/javascript" src="${base}/script/common/area2.js"></script>
<script language="JavaScript" type="text/javascript" src="${base}/script/jquery/jquery.autocomplete.min.js"></script>
<script language="JavaScript" type="text/javascript" src="${base}/script/b/ticket/m/orderFill.js"></script>
<script language="JavaScript">
    $(document).ready(function(){
    	//区域
    	var area1=new area();
		area1.setupById("country","city","province","areaOcx",-1,"001001001");
     	//表单
     	var option={};
     	option.callBefor=orderFill.checkForm;
     	option.callBack=function(){location.href=applicationContent.backUrl};
     	option.formId="orderForm";
     	option.actionName="新增订单";
     	var fm=new AdvancedForm(option);
     	//被始化合计
     	orderFill.sum();
    });
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>新增订单</li>
        </ul>
    </div>
	<form action="${base}/manage/store/orderNew!save.htm" id="orderForm" method="get" >
    <div class="r_content">
    <div class="tool_1">基本信息</div>
		<div class="tableNav_2">
			<table>
			<tr>
			<td>
			商品名称：
			</td>
			</tr>
			</table>
	   </div>
	</div>
    <div class="r_content">
        <div class="tool_1">商品信息</div>
		<div class="tableNav_2">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablelistNav">
		<tr>
		  <td width="50" align="center" class="table_title">序号</td>
		  <td align="center" class="table_title">商品名称</td>
		  <td width="80" align="center" class="table_title">门市价格</td>
		  <td width="80" align="center" class="table_title">网上价格</td>
		  <td width="80" align="center" class="table_title">单笔最小数量</td>
		  <td width="80" align="center" class="table_title">单笔最大数量</td>
		  <td width="120" align="center" class="table_title">剩余数量</td>
		  <td width="200" align="center" class="table_title">购买数量</td>
		</tr>
    	<#list goodsList as res>
	    <tr class="tr_cur1">
	      <td align="left">${res_index+1}</td>
	      <td align="left">${res.goodsName!}</td>
	      <td align="right">${(res.showPrice?c)!}</td>
	      <td align="right">${(res.price?c)!}</td>
  		  <td align="right">${res.minAmount}</td>
	      <td align="right">${res.maxAmount}</td>
	      <td align="center">${res.leftNum}</td>
	      <td align="center">
	      	<#if res.leftNum gte 0 && res.minAmount gt res.leftNum >
	      		库存不足
      		<#else>
	      	<input type="hidden" name="goodsIds" value="${res.id}" />
	      	<input type="text" name="amounts" value="${res.minAmount}" size="2" price="${res.price}" showPrice="${res.showPrice}" maxAmount="${res.maxAmount}" minAmount="${res.minAmount}"
	      		onChange="orderFill.sum(this)"/>
	      	</#if>
	      </td>
	    </tr>
		</#list>
		<tr>
			<td align="right" colSpan="9">合计:<span id="sum"></span></td>
		</tr>
  		</table>
		</div>
	</div>
	<div class="r_content">
        <div class="tool_1">联系人信息</div>
		<div class="tableNav_2">
		<table width="100%" border="1" cellpadding="5" cellspacing="0" class="tablelistNav">
		<tr>
		<td width="200" align="right">用户账户:</td>
		<td><input type="text" name="orderVo.accountName" id="accountName"/>如不填默认为系统账户!</td>
		</tr>
		<tr>
		<td width="200" align="right">联系人姓名:</td><td><input type="text" name="orderVo.linkName" validate="{required:true,maxlength:20,minlength:2}" size=/></td>
		</tr>
		<tr>
		<td align="right">手机号:</td><td><input type="text" name="orderVo.linkMobile" validate="{required:true,mobile:true}"/></td>
		</tr>
		<tr>
		<td align="right">身份证号码:</td><td><input type="text" name="orderVo.linkIdcard" validate="{required:true,idcardno:true}"/></td>
		</tr>
		<tr>
		<td align="right">客源地:</td><td>
			<select id="country"></select>
          	<select id="city"></select>
          	<select id="province"></select>
          	<input type="hidden" id="areaOcx" name="orderVo.areaCode"/></td>
		</tr>
		<tr>
		<td align="right">备注信息:</td><td><textarea name="orderVo.remark" validate="{maxlength:1000}" cols="50" rows="5"></textarea></td>
		</tr>
		</table>
		</div>
    </div>
    <div class="r_content">
    <#if goodsStoreInfo.payFlag=1>
        <div class="tool_1">支付信息</div>
		<div class="tableNav_2">
			<table width="100%" border="1" cellpadding="5" cellspacing="0" class="tablelistNav">
				<tr>
				<td>支付状态:	</td><td>
					<select name="orderVo.payStatus">
						<option value="0">未支付</option>
						<option value="1">已支付</option>
					</select></td>
				</tr>
				<tr>
				<td>支付信息:</td>
				<td><textarea name="orderVo.payInfo" validate="{maxlength:1000}" rows="2" cols="50"></textarea></td>
				</tr>
			</table>
		</div>
	<#else>
		<input type="hidden" name="orderVo.payStatus" value="0"/>
	</#if>
	    <div class="tool_2">
	        <div class="tool_2_L">
	        <input type="submit" class="btn02"  id="button1" value="保  存"/>
	        <input class="btn02"  id="button1" value="返  回" onclick="location.href='${backUrl}'"/>
	        </div>
	        <div class="tool_2_R"></div>
		</div>
	</div>
    </form>
</div>
</div>
</body>
</html>