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
     	var fm=new GeneralForm("validateForm","新增用户");
    });
</script>
</head>
<body>
<!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">新增用户</div>
                <div class="page-item form-panel">
    	<ul>
        <li>新增用户信息</li>
        </ul>
    <form action="${base}/manage/account/agent!save.htm" id="validateForm" method="get" >
    <ul>
          <li><span class="lab-title">用户名称</span>
          <div class="form-item">
	          <input name="agentInfo.corpName" type="text" class="text" id="agentInfo.corpName" size="50" maxlength="50" validate="{required:true,minlength:2}" />
          </div>
        </li>
          <li><span class="lab-title">用户编号</span>
          <div class="form-item">
	          <input name="agentInfo.code" type="text" class="text" id="agentInfo.code" size="32" maxlength="50" validate="{required:true,minlength:2}" />
          </div>
        </li>
          <li><span class="lab-title">用户网站</span>
          <div class="form-item">
	          <input name="agentInfo.url" type="text" class="text" id="agentInfo.url" size="50" maxlength="512"  validate="{url:true}" />
          </div>
        </li>
          <li><span class="lab-title">联系人姓名</span>
          <div class="form-item">
	          <input name="agentInfo.linkman" type="text" class="text" id="agentInfo.linkman" size="20"  maxlength="50" validate="{required:true,minlength:2}" value="" />
          </div>
        </li>
          <li><span class="lab-title">联系人电话</span>
          <div class="form-item">
	          <input name="agentInfo.linkmanTel" type="text" class="text" id="agentInfo.linkmanTel" size="50" maxlength="200" value="" />
          </div>
        </li>
          <li><span class="lab-title">公司电话</span>
          <div class="form-item">
	          <input name="agentInfo.telphone" type="text" class="text" id="agentInfo.telphone" size="50" maxlength="200" value="" />
          </div>
        </li>
          <li><span class="lab-title">传真:</div>
          <div class="form-item">
			<input name="agentInfo.fax" type="text" class="text" id="agentInfo.fax" size="50" maxlength="200" value="" />	
	      </div>
        </li>
          <li><span class="lab-title">地址:</div>
          <div class="form-item">
			<input name="agentInfo.address" type="text" class="text" id="agentInfo.address" size="100" maxlength="500" value="" />	
	      </div>
        </li>
          <li><span class="lab-title">合同有效期:</div>
          <div class="form-item">
          	<input name="agentInfo.begDate" type="text" value="" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>-<input name="agentInfo.endDate" type="text" value="" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
	      </div>
        </li>
          <li><span class="lab-title">备注信息:</div>
          <div class="form-item">
			<textarea name="agentInfo.remark" cols="45" rows="5" class="textarea_1" id="agentInfo.remark"></textarea>	
	      </div>
        </li>
          <li><span class="lab-title">管理账户名:</div>
          <div class="form-item">
			<input name="accountLogin.loginName" type="text" class="text" size="20" id="accountLogin.loginName" validate="{required:true,minlength:2}"/>
	      </div>
        </li>
          <li><span class="lab-title">账户密码:</div>
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
