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
<link href="${base}/script/datepicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
<link href="${base}/scripts/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css" media="screen"/>
<script src="${base}/scripts/jquery.mousewheel-3.0.4.pack.js"></script>
<script src="${base}/scripts/jquery.fancybox-1.3.4.js"></script>
<script src="${base}/scripts/dialog.js"></script>
<script language="JavaScript">
dialog.callback=function(obj){
		$("#accountId").val(obj.accountId);
		$("#accountName").val(obj.accountName);
		$("#corpName").html(obj.corpName);
	}
</script>
<meta name="Authors" content="Tomzhou">
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new BackUrlForm("validateForm","审核通过","${base}/manage/account/rechageCard!audit.htm");
    });
</script>
</head>
<body>
  <!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">审核充值卡</div>
                <div class="page-item form-panel">
                   <form action="${base}/manage/account/rechageCard!auditSave.htm" id="validateForm" method="post" >
                        <ul>
                          <input type="hidden" value="${ids}" name="ids">
                            <li><span class="lab-title">审核说明</span>
                                <div class="form-item">
                                    <textarea name="rechageCard.auditRemark" id=""  class="textArea" validate="{required:true,maxlength:500}"></textarea>
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
