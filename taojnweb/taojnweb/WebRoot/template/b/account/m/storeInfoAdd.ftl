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
<meta name="Authors" content="Tomzhou">
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new BackUrlForm("validateForm","新增商户","${base}/manage/account/storeInfo.htm");
    });
    
  
</script>
</head>
<body>
  <!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">商户新增</div>
                <div class="page-item form-panel">
                   <form action="${base}/manage/account/storeInfo!save.htm" id="validateForm" method="post" >
                        <ul>
                           <li><span class="lab-title">店铺名称</span>
                                <div class="form-item">
                                   <input name="storeInfo.storeName"  class="text" size="30" value="${storeInfo.storeName!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                           <li><span class="lab-title">店铺编号</span>
                                <div class="form-item">
                                   <input name="storeInfo.storeCode"  class="text" size="30" value="${storeInfo.storeCode!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                            
                              <li><span class="lab-title">商户姓名</span>
                                <div class="form-item">
                                   <input name="storeInfo.linkman"  class="text" size="30" value="${storeInfo.linkman!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                             <li><span class="lab-title">商户账户</span>
                                <div class="form-item">
                                   <input name="accountLogin.loginName"  class="text" size="30" value="${accountLogin.loginName!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                             <li><span class="lab-title">登录密码</span>
                                <div class="form-item">
                                   <input name="accountLogin.loginPass"  type="password" id="loginPass" class="text" size="30" value="${accountLogin.loginPass!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                             <li><span class="lab-title">登录密码</span>
                                <div class="form-item">
                                  <input class="text" type="password" id="loginPass2" value="" validate="{required:true,minlength:5,equalTo:'#loginPass'}" placeholder="确认登录密码" />
                                </div>
                            </li>
                           <li><span class="lab-title">联系人电话</span>
                                <div class="form-item">
                                   <input name="storeInfo.linkmanTel"  class="text" size="30" value="${storeInfo.linkmanTel!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                              <li><span class="lab-title">店铺电话</span>
                                <div class="form-item">
                                   <input name="storeInfo.telphone"  class="text" size="30" value="${storeInfo.telphone!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                           <li><span class="lab-title">地址</span>
                                <div class="form-item">
                                   <input name="storeInfo.address"  class="text" size="30" value="${storeInfo.address!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                            <li><span class="lab-title">说明</span>
                                <div class="form-item">
                                    <textarea name="storeInfo.remark" id="" class="textArea" validate="{maxlength:500}">${storeInfo.remark}</textarea>
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
