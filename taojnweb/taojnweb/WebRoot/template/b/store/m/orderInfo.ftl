<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/template/m/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>订单信息</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1">订单</div>
    <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
        <tr>
          <td align="right" class="table_l_tit">订单号：</td>
          <td class="table_l_td" align="left">
			${orderInfo.orderNo}
          </td>
          <td align="right" class="table_l_tit">订单状态：</td>
          <td class="table_l_td" align="left" colSpan="2">
			<#if orderInfo.orderStatus=0>新建<#elseif orderInfo.orderStatus=1>成功<#elseif orderInfo.orderStatus=2>失败<#elseif orderInfo.orderStatus=3>退单</#if>
			(${orderInfo.createTime?string("yyyy-MM-dd HH:mm")})
          </td>
          <td align="right" class="table_l_tit" width="150">用户名：</td>
          <td class="table_l_td" align="left">
	          ${orderInfo.buyerName}(<#if orderInfo.buyerAccType==0>系统账户<#elseif orderInfo.buyerAccType==1>前台会员</#if>)
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit" width="150">操作员：</td>
          <td class="table_l_td" align="left">
	          ${orderInfo.loginName}
          </td>
          <td align="right" class="table_l_tit">消费类型：</td>
          <td class="table_l_td" align="left">
	          <#if orderInfo.bookType==0>网上购买<#elseif orderInfo.bookType==1>网上预订</#if>
          </td>
          <td align="right" class="table_l_tit">支付：</td>
          <td class="table_l_td" align="left">
	          <#if (orderInfo.payType)?exists>
	          <#if (orderInfo.payType)==0>无
	          <#elseif (orderInfo.payType)==1>支付宝
	          <#elseif (orderInfo.payType)==9>操作员手动
	          </#if>
	          <#else> 无
	          </#if>
	          (
	          	<#if (orderInfo.payStatus)?exists>
		          	<#if (orderInfo.payStatus)==0>	待支付
		          	<#elseif (orderInfo.payStatus)==1>已支付
		          	<#elseif (orderInfo.payStatus)==2>过期
		          	<#elseif (orderInfo.payStatus)==3>无需支付
	          	</#if>
	          <#else> 无
	          	</#if>)
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">订单金额：</td>
          <td class="table_l_td" align="left">
          ${orderInfo.paySum?c}
          </td>
          <td align="right" class="table_l_tit">最终金额：</td>
          <td class="table_l_td" align="left">
          	${orderInfo.finalSum?c}
          </td>
          <td align="right" class="table_l_tit">支付时间：</td>
          <td class="table_l_td" align="left">
          	${(orderInfo.payTime?string("yyyy-MM-dd HH:mm"))!"无"}
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">订单关闭：</td>
          <td class="table_l_td" align="left">
          <#if orderInfo.closed=0>打开<#else>关闭</#if>
          </td>
          <td align="right" class="table_l_tit">关闭时间：</td>
          <td class="table_l_td" align="left">
          ${(orderInfo.closeTime?string("yyyy-MM-dd HH:mm"))!}
          </td>
          <td align="right" class="table_l_tit">挂起标志：</td>
          <td class="table_l_td" align="left">
          <#if orderInfo.hungFlag>挂起<#else>正常</#if>
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">联系人：</td>
          <td class="table_l_td" align="left">
          ${orderInfo.linkName}
          </td>
          <td align="right" class="table_l_tit">身份证：</td>
          <td class="table_l_td" align="left">
          ${orderInfo.linkIdcard}
          </td>
          <td align="right" class="table_l_tit">手机号：</td>
          <td class="table_l_td" align="left">
          ${orderInfo.linkMobile}
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">客源地：</td>
          <td class="table_l_td" align="left" colSpan="3">
          ${orderInfo.areaName}
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">内容：</td>
          <td class="table_l_td" align="left" colSpan="5">
          ${orderInfo.info}
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">备注信息：</td>
          <td class="table_l_td" align="left" colSpan="5">
          ${orderInfo.remark}
          </td>
        </tr>
      </table>
    </div>
    </div>
    <div class="r_content">
    <div class="tool_1">订单明细</div>
    <div class="tableNav">
      <table width="100%" border="0" cellpadding="3" cellspacing="0" class="tablelistNav">
      	<tr>
		  <td width="50" align="center" class="table_title">序号</td>
		  <td align="center" class="table_title">商品</td>
		  <td width="150" align="center" class="table_title">数量</td>
		  <td width="150" align="center" class="table_title">单价</td>
		  <td width="150" align="center" class="table_title">合计</td>
		</tr>
		<#list goList as res>
			<#assign flag=1>
		    <#if res_index%2=1>
		 <tr class="tr_cur1">
		    <#else>
		 <tr class="tr_cur2">
		    </#if>
		 	<td align="left">${res_index+1}</td>
	      	<td align="left">${res.goodsName!}</td>
	      	<td align="right">${res.amount!}</td>
	      	<td align="right">${res.price?c}</td>
	      	<td align="right">${res.sum?c}</td>
		 </tr>
		 </#list>
      </table>
    </div>
    <div class="tool_2">
        <div class="tool_2_L">
        <input type="button" class="btn02" name="button" id="button" onclick="location.href='${backUrl}'" value="返 回" />
        </div>
        <div class="tool_2_R"></div>
    </div>
    </div>
</div>
</div>
</body>
</html>