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
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new BackUrlForm("validateForm","修改客户信息","${base}/manage/account/agent.htm");
    });
</script>
</head>
<body>
<!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">修改客户信息</div>
                <div class="page-item form-panel">
    <form action="${base}/manage/account/agent!update.htm" id="validateForm" method="get" >
    	<input name="id" type="hidden" value="${agentInfo.id}" />
     <ul>
          <li><span class="lab-title">客户名称：</span>
          <div class="form-item">
	           <input name="agentInfo.corpName" type="text" class="text" id="agentInfo.corpName" size="50" maxlength="50" validate="{required:true,minlength:2}" value="${agentInfo.corpName}"/>
          </div>
        </li>
          <li><span class="lab-title">客户编号：</span>
          <div class="form-item">
	          ${agentInfo.code}
          </div>
        </li>
          <li><span class="lab-title">钱包名称:</span>
          <div class="form-item">
			<input name="agentInfo.bankName" type="text" class="text" id="agentInfo.bankName" size="50" maxlength="200" value="${agentInfo.bankName}" />	
	      </div>
        </li>
          <li><span class="lab-title">联系电话：</span>
          <div class="form-item">
	         ${agentInfo.linkmanTel!} <input name="agentInfo.linkmanTel" type="hidden" class="text" id="agentInfo.linkmanTel" size="50" maxlength="200" value="${agentInfo.linkmanTel}" />
          </div>
        </li>         
      
          <li><span class="lab-title">备注信息:</span>
          <div class="form-item">
			<textarea name="agentInfo.remark" cols="45" rows="5" class="textarea_1" id="agentInfo.remark">${agentInfo.remark}</textarea>	
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