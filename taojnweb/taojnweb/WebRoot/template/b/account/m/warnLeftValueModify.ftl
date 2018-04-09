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
     	var fm=new BackUrlForm("validateForm","调整额度","${base}/manage/account/mAgentAccount.htm");
    });
</script>
</head>
<body>
<!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">授信额度调整</div>
                <div class="page-item form-panel">
    	<ul>
        <li>账户信息</li>
        </ul>
    <form action="${base}/manage/account/mAgentAccount!doModifyWarnLeftValue.htm" id="validateForm" method="post" >
 	<ul>
          <li><span class="lab-title">用户名称：</span>
          <div class="form-item">
	           ${tradeAccount.accountName}
          </div>
        </li>
   		 <input type="hidden" value="${tradeAccount.id}" name="id"/>
         <li><span class="lab-title">账户类型：</span>
          <div class="form-item">
	         ${tradeAccount.tradeAccName}
          </div>
        </li>
      <li><span class="lab-title">余额：</span>
          <div class="form-item">
	         ${tradeAccount.leftValue!0}
          </div>
        </li>
          <li><span class="lab-title">授信额度：</span>
          <div class="form-item">
	         ${tradeAccount.warnLine!0}
          </div>
        </li>
        </ul>
	
		   <ul>
		        <li>额度设置</li>
		        </ul>
		        <ul>
		         <li><span class="lab-title">额度值：</span>
		          <div class="form-item">
			         <input type="text" name="opValue" id="opValue" class="text" style="text-align: right" validate="{required:true,number:true,min:1}"/>
		          </div>
		        </li>
		        <li><span class="lab-title">备注：</span>
		          <div class="form-item">
			         <textArea name="order.remark" id="remark" rows="5" class="textArea" cols="30" validate="{required:false,maxlength:200}"></textArea>
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