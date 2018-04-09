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
</head>
<body>
<!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">用户信息</div>
                <div class="page-item form-panel">
    <ul>
          <li><span class="lab-title">用户名称：</span>
          <div class="form-item">
	          ${agentExtendInfo.corpName}
          </div>
          </li>
          <li><span class="lab-title">用户编号：</span>
          <div class="form-item">
	          ${agentExtendInfo.code}
          </div>
          </li>
           <li><span class="lab-title">状态：</span>
           <div class="form-item">
			<#if agentExtendInfo.accStatus==0>
			      停用
			<#elseif agentExtendInfo.accStatus==1>
			      正常
			</#if>
          </div>
         </li>
          <#--li><span class="lab-title">上级代理：</span>
          <div class="form-item">
	          ${agentInfo.parentAgentName!"无"}
          </div>
            </li>
          <li><span class="lab-title">系统对接key：</span>
          <div class="form-item">
	          ${agentInfo.partnerKey!"无"}
          </div>
            </li-->
          <li><span class="lab-title">联系人姓名：</span>
          <div class="form-item">
			${agentExtendInfo.linkman!("&nbsp;")}
          </div>
            </li>
          <li><span class="lab-title">联系人电话：</span>
          <div class="form-item">
			${(agentExtendInfo.linkmanTel)!("&nbsp;")}
          </div>
            </li>
          <li><span class="lab-title">公司电话：</span>
          <div class="form-item">
			${(agentExtendInfo.telphone)!("&nbsp;")}
          </div>
          </li>
        <li><span class="lab-title">银行名称：</span>
			<div class="form-item">
			${(agentExtendInfo.bankName)!!("&nbsp;")}
			</div>
       		  </li>
			<li><span class="lab-title">卡号：</span>
          <div class="form-item">
			${agentExtendInfo.bankCode}
          </div>
		  </li>
        
       		<li><span class="lab-title">合同有效期：</span>
			 <div class="form-item">
			${agentExtendInfo.begDate?string("yyyy-MM-dd")}  至   
			${agentExtendInfo.endDate?string("yyyy-MM-dd")}
			</div>
			
         </li>
         <li><span class="lab-title">地址：</span>
         	<div class="form-item">
			${(agentExtendInfo.address)!!("&nbsp;")}
          	</div>
          <li><span class="lab-title">备注信息：</span>
         	<div class="form-item">
			${agentExtendInfo.remark!("&nbsp;")}
          </div>
          </li>
          </ul>
    </div>
</div>
<!--列表正文结束-->
</body>
</html>