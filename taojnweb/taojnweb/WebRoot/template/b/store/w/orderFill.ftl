<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "/template/w/common/head.ftl">
<title>江南钱包对账订单成功</title>
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/reset-min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/common.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/ticket.css" media="screen" />
</head>

<body>
<#assign showTag=4> 
    <#include "/template/w/top.ftl">
	<div class="contain mar_top">
	   <div class="conNavTitle">订单成功提示</div><!---->
	    <div class="conNavCon">
	      <div class="conNavConL"><img src="${base}/style/w/images/tickets/success.jpg" width="131" height="135" /></div><!--conNavConL-->
	    <div class="conNavConR">
	       <h2><b>订单成功!</b> <span class="oracolor">订单号:${order.orderNo}</span></h2>
	       <p>你已成功订购：${(order.info)!}</p>
	       <p>您本次需要支付：<span class="oracolor">￥${(order.finalSum?c)!}</span></p>
	        <p>支付成功后，您将收到一条短信通知！凭短信订单号到指定的商铺换取商品。</p>
	       <p>非常感谢您对江南钱包的支持</p>
	    </div><!--conNavConR-->
	    <div class="operate">
	   	 	<input type="button" name="button" id="button" value="我要支付" onclick="location.href='${base}/order/toAlipay.htm?orderId=${order.id}'"/>
	    </div>
	  </div><!---->
	</div><!--contain-->


  <#include "/template/w/foot.ftl">
</body>
</html>
