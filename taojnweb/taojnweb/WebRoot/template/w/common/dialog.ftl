<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <meta content="email=no" name="format-detection" />
    <title>江南钱包</title>
    <link rel="stylesheet" href="${base}/style/w/css/reset.css"/>
    <link rel="stylesheet" href="${base}/style/w/css/index.css"/>
    <script type="text/javascript" src="${base}/script/w/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${base}/script/w/common.js"></script>
</head>
<body class="index-body">
<div class="amount-panel">
          		<h2>
					<#if action.msgInfo.flag == "error">
					</#if>
					<#if action.msgInfo.flag == "warning">
						警告！
					</#if>
					<#if action.msgInfo.flag== "forbid">
						禁止！
					</#if>
					<#if action.msgInfo.flag=="question">
						确定?
					</#if>
					<#if action.msgInfo.flag=="success">
						恭喜！
					</#if>
				</h2>
	       		<#list action.msgInfo.messages as index> 
				<p class="err-info">
					${index?default("")}
				</p>
				</#list>
						
				<#list action.msgInfo.buttons as index>
					<#if index.info?exists>
					<p><a href="${base}${index.info}">${index.caption}</a></p>
					<#else>
					<p><a href="${index.script}">${index.caption}</a></p>
					</#if>
				</#list>
	         </div>
        		
   
  <#include "template/w/foot.ftl">
</body>
</html>
