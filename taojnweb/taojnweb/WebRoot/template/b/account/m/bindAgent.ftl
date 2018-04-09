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
     	var fm=new BackUrlForm("validateForm","新增用户","${base}/manage/account/agent.htm");
    });
</script>
</head>
<body>
<!--表单正文开始-->
    <div class="page-main">
        <div class="page-item page-title">新增用户</div>
        <div class="page-item form-panel">
    <form action="${base}/manage/port/bindAgent!bindAgentSave.htm" id="validateForm" method="get" >
    <#if agentInfo.parentAgentId??>
    	<input type="hidden" name="agentInfo.parentAgentId" value="${agentInfo.parentAgentId}"/>
    	<input type="hidden" name="agentInfo.parentAgentName" value="${agentInfo.parentAgentId}"/>
    </#if>
     <ul>
          <li><span class="lab-title">用户名称：</span>
          <div class="form-item">
	          <input name="agentInfo.corpName" type="text" value="${agentInfo.corpName}" class="text" id="agentInfo.corpName" size="50" maxlength="50" validate="{required:true,minlength:2}" />
          </div>
          </li>
         <li><span class="lab-title">用户编号：</span>
          <div class="form-item">
	          <input name="agentInfo.code" type="text" class="text" id="agentInfo.code" size="32" maxlength="50" validate="{required:true,minlength:2}" />
         </div>
         </li>
         <li><span class="lab-title">用户类型：</span>
          <div class="form-item">
		    <#if agentInfo.parentAgentId??>
		      <select name="agentInfo.agentType" id="select1">
	          	<option value="1" selected>用户</option>
	          </select>
	        <#else>
	          <select name="agentInfo.agentType" id="select1">
	          	<option value="0">用户</option>
	          	<option value="2">OTA</option>
	          </select>
		    </#if>
          </div>
          </li>
         <li><span class="lab-title">联系人姓名：</span>
          <div class="form-item">
	          <input name="agentInfo.linkman" type="text" class="text" id="agentInfo.linkman" size="20"  maxlength="50" validate="{required:true,minlength:2}" value="" />
         </div>
         </li>
          <li><span class="lab-title">联系人手机：</span>
          <div class="form-item">
			<input name="accountLogin.mobile" type="text" class="text" size="20" id="accountLogin.mobile" validate="{required:true,mobile:true}"/>
	      </div>
	      </li>
          <li><span class="lab-title">联系人邮箱：</span>
          <div class="form-item">
			<input name="accountLogin.email" type="text" class="text" size="20" id="accountLogin.email" validate="{required:true,email:true}"/>
	     </div>
	     </li>
         <li><span class="lab-title">公司电话：</span>
          <div class="form-item">
	          <input name="agentInfo.telphone" type="text" class="text" id="agentInfo.telphone" size="50" maxlength="200" value="" />
          </div>
          </li>
        <li><span class="lab-title">银行名称:</span>
          <div class="form-item">
			<input name="agentInfo.bankName" type="text" class="text" id="agentInfo.bankName" size="50" maxlength="200" />	
	      </div>
        </li>
        <li><span class="lab-title">卡号:</span>
          <div class="form-item">
			<input name="agentInfo.bankCode" type="text" class="text" id="agentInfo.bankCode" size="50" maxlength="200"  />	
	      </div>
        </li>
         <li><span class="lab-title">地址:</span>
          <div class="form-item">
			<input name="agentInfo.address" type="text" class="text" id="agentInfo.address" size="100" maxlength="500" value="" />	
	      </div>
	      </li>
         <li><span class="lab-title">合同有效期:</span>
          <div class="form-item">
          	<input name="agentInfo.begDate" type="text" value="" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>-<input name="agentInfo.endDate" type="text" value="" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
	     </div>
	     </li>
        <#if agentInfo.parentAgentId??>
         <li><span class="lab-title">上级代理:</span>
          <div class="form-item">
          	${agentInfo.parentAgentName}
	     </div>
        </#if>
        </li>
         <li><span class="lab-title">备注信息:</span>
          <div class="form-item">
			<textarea name="agentInfo.remark" cols="45" rows="5" class="textarea_1" id="agentInfo.remark"></textarea>	
	      </div>
	      </li>
         <li><span class="lab-title">管理账户名:</span>
          <div class="form-item">
			<input name="accountLogin.loginName" type="text" class="text" size="20" id="accountLogin.loginName" validate="{required:true,minlength:2}"/>
	      </div>
	      </li>
         <li><span class="lab-title">账户密码:</span>
          <div class="form-item">
			<input name="accountLogin.loginPass" type="password" class="text" size="20" id="accountLogin.loginPass" validate="{required:true,minlength:2}"/>
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