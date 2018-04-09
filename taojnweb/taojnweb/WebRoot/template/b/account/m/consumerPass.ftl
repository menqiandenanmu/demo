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
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new BackUrlForm("validateForm","修改密码","${base}/manage/index.htm");
    });
</script>
</head>
<body>
<!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">修改密码</div>
                <div class="page-item form-panel">
    <div class="tool_1">&nbsp;<font size="2" color="#FF0033"></font></div>
    <form action="${base}/manage/account/consumer!updatePass.htm" id="validateForm" method="get" >
	<input name="id" type="hidden" value="${login.id}" />
	 <ul>
	  <li><span class="lab-title">登录名称：</span>
          <div class="form-item">
	         ${login.loginName}
          </div>
        </li>
          <li><span class="lab-title">登录密码：</span>
          <div class="form-item">
	           <input name="loginPass" type="password" class="text" id="loginPass" size="50" maxlength="50" validate="{required:true,minlength:2}" value=""/>
          </div>
        </li>
      <li><span class="lab-title">确认密码：</span>
          <div class="form-item">
	           <input name="loginPass2" type="password" class="text" id="loginPass2" size="50" maxlength="50"  validate="{equalTo:'#loginPass'}"  value=""/>
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