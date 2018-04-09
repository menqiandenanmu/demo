<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/template/m/common/head.ftl">
<link href="${base}/style/m/css/reset.css" rel="stylesheet" type="text/css">
<link href="${base}/style/m/css/common.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/page.css"/>
<!--[if lte IE 8]>
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/ie8.css" />
<![endif]-->
<script src="${base}/scripts/require.js" data-main="scripts/main"></script>
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new BackUrlForm("validateForm","新增子账户","${base}/manage/account/mAgentAccount.htm");
     	modifyAccountName();
     	modifyTradeAccName();
    });
    
    function  modifyTradeAccName(){
       $("#tradeAccName").val($("#tradeAccId").find("option:selected").text());
    }
    
    function modifyAccountName(){
    	$("#accountName").val($("#accountId").find("option:selected").text());
    }
</script>
</head>
<body>
<!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">新增子账户</div>
                <div class="page-item form-panel">
    	<ul>
        <li>新增子账户</li>
        </ul>
    <form action="${base}/manage/account/mAgentAccount!doAdd.htm" id="validateForm" method="post" >
    <input type="hidden" name="id" value="${tradeAccount.id}" />
  <ul>
          <li><span class="lab-title">用户名称：</span>
          <div class="form-item">
          ${tradeAccount.accountName!}
          </div>
        </li>
          <li><span class="lab-title">账户类型：</span>
         <div class="form-item">
			<select id="tradeAccId" name="tradeAccount.tradeAccId" onchange="modifyTradeAccName();">
			<#if accountList?? >
				<#list accountList as acl>
				<#if !(acl.id=="1")>
				<option value="${acl.id}">${acl.accName}</option>
				</#if>
				</#list>
			</#if>
			</select>
          </div>
        </li>
       </ul>
            <div class="form-handle">
                <input type="submit" value="提交" class="btn btn-small bg-blue"/>
            </div>
        </form>
    </div>
</div>
<!--列表正文结束-->
</body>
</html>