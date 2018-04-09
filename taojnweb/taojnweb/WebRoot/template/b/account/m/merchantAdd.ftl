<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商户新增</title>
<#include "/template/m/common/head.ftl">
<#include "/template/m/common/utils.ftl">
<link href="${base}/script/datepicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
<link href="${base}/style/m/css/reset.css" rel="stylesheet" type="text/css">
<link href="${base}/style/m/css/common.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/page.css"/>
<script language="JavaScript">
    $(document).ready(function(){
    	var fm = new BackUrlForm("validateForm","新增客户","${base}/manage/account/merchant.htm");
    });
</script>
</head>
<body>
<!--表单正文开始-->
    <div class="page-main">
        <div class="page-item page-title">商户新增</div>
        <div class="page-item form-panel">
		<form action="${base}/manage/account/merchant!save.htm" id="validateForm" method="get" >
 		  <ul>
 		   <li>
 		   	<span class="lab-title">商户ID</span>
 		   	<div class="form-item">
 		   		<input type="text" name="merchantId" class="text"/>
 		   	</div>
 		   </li>
 		   <li><span class="lab-title">商户名称:</span>
			<div class="form-item">
				<input type="text"  name="merchant.merchantName" class="text"  />
			</div>
			</li>
            <li><span class="lab-title">商户号:</span>
				<div class="form-item">
					<input type="text" name="merchant.merchantNumber" class="text"  />
				</div>
			</li>
			<li>
				<span class="lab-title">商户KEY</span>
				<div class="form-item">
					<input type="text" name="merchant.merchantKey" class="text" />
				</div>
			</li>
			<li><span class="lab-title">商户状态:</span>
				<div class="form-item">	
				<select name="merchant.merchantStatus" style="width:120px;">
					<option	value="0"  >停用</option>
					<option value="1" selected="selected">启用</option>
				</select>
			</div></li>
        </ul>
        	<div >
        	   <input type="submit" value="提交" class="btn btn-small bg-blue" />
        	</div>
        </form>
     </div>
</div>
</body>
</html>