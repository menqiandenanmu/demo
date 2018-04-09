<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<link href="${base}/style/m/css/reset.css" rel="stylesheet" type="text/css">
<link href="${base}/style/m/css/common.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/page.css"/>
<!--[if lte IE 8]>
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/ie8.css" />
<![endif]-->
<script src="${base}/scripts/require.js" data-main="scripts/main"></script>
<link href="${base}/script/datepicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new GeneralForm("validateForm","新增账户消费对象");
     	$("#tradeAccountLimitSupplyName").val($("#tradeAccountLimitSupplyId").find("option:selected").text());
    });
    
    function modifyValue(){
    	$("#tradeAccountLimitSupplyName").val($("#tradeAccountLimitSupplyId").find("option:selected").text());
    }
</script>
</head>
<body>
<!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">新增账户消费对象</div>
                <div class="page-item form-panel">
    	<ul>
        <li>新增账户消费对象</li>
        </ul>
    <form action="${base}/manage/account/tradeAccountLimit!save.htm" id="validateForm" method="post" >
    <input type="hidden" name="tradeAccountLimit.tradeAccountId" value='${tradeAccountLimit.tradeAccountId!}' />
    <input id="tradeAccountLimitSupplyName" type="hidden" name="tradeAccountLimit.supplyName" value='${tradeAccountLimit.supplyName}' />
  <ul>
        <li><span class="lab-title">登录名</span>
            <div class="form-item">
               <input name="accountLogin.loginName" type="text" class="text" id="roleName" size="30" value="${accountLogin.loginName}"  validate="{required:true}" />
            </div>
        </li>
          <li><span class="lab-title">供应商名称</span>
           <div class="form-item">
             <select id="tradeAccountLimitSupplyId" onchange="modifyValue();" name="tradeAccountLimit.supplyId">
	          <#if supply??&&supply.size() gt 0>
	         	<#list supply as res>
	         	  <option value='${res.id}'> ${res.corpName}</option>
	         	</#list>
	          </#if>
	         </select>
        </div>
          </li>
          <li><span class="lab-title">  是否有效</span>
           <div class="form-item">
	       				<select name="tradeAccountLimit.useFlag" >
							<option value="true" selected="selected">是</option>
							<option value="false">否</option>
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