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
     	var fm=new BackUrlForm("validateForm","新增客户","${base}/manage/account/agent.htm");
    });
</script>
</head>
<body>
<!--表单正文开始-->
    <div class="page-main">
        <div class="page-item page-title">新增客户</div>
        <div class="page-item form-panel">
    <form action="${base}/manage/account/agent!save.htm" id="validateForm" method="get" >
     <ul>
          <li><span class="lab-title">客户名称：</span>
          <div class="form-item">
	          <input name="agentInfo.corpName" type="text" class="text" id="agentInfo.corpName" size="50" maxlength="50" validate="{required:true,minlength:2}" />
          </div>
          </li>
         <li><span class="lab-title">客户来源：</span>
          <div class="form-item">
          		<select name="agentInfo.url" validate="{required:true}">
          		<#list dictDetails as dict>
          		<option value="${dict.dictDetailCode}">${dict.dictDetailValue}</option>
          		</#list>
          		</select>
         </div>
         </li-->
          <li><span class="lab-title">钱包名称:</span>
          <div class="form-item">
			<input name="agentInfo.bankName" type="text" class="text" id="agentInfo.bankName" size="50" maxlength="200" value="" />	
	      </div>
        </li>
          <li><span class="lab-title">联系方式：</span>
          <div class="form-item">
			<input name="accountLogin.mobile" type="text" class="text" size="20" id="accountLogin.mobile" validate="{required:true,mobile:true}"/>
	      </div>
	      </li>
          <li><span class="lab-title">身份证：</span>
          <div class="form-item">
			<input name="accountLogin.idCard" type="text" class="text" size="20" id="accountLogin.idCard" validate="{required:true,idcardno:true}"/>
	      </div>
	      </li>
          <li><span class="lab-title">电子邮箱：</span>
          <div class="form-item">
			<input name="accountLogin.email" type="text" class="text" size="20" id="accountLogin.email" validate="{required:true,email:true}"/>
	      </div>
	      </li>
          <li><span class="lab-title">性别：</span>
          <div class="form-item">
			<input name="accountLogin.sex"  id="radio" value="0" size="18"  type="radio" />女
				<input name="accountLogin.sex"   id="radio" value="1" size="18"  type="radio"  checked="true"/>男
	      </div>
	      </li>
	      <li><span class="lab-title">账户密码:</span>
          <div class="form-item">
			<input name="accountLogin.loginPass" type="password" class="text" size="20" id="accountLogin.loginPass" validate="{required:true,minlength:2}"/>
			</div>
			</li>
		  <li><span class="lab-title">交易密码:</span>
          <div class="form-item">
			<input name="agentInfo.address" type="text" class="text" id="agentInfo.address" size="50" maxlength="200" value="" />	
	      </div>
        </li>
			 <li><span class="lab-title">备注信息:</span>
          <div class="form-item">
			<textarea name="agentInfo.remark" cols="45" rows="5" class="textarea_1" id="agentInfo.remark"></textarea>	
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