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
<meta name="Authors" content="Tomzhou">
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new BackUrlForm("validateForm","更新赠送券","${base}/manage/account/sendRules.htm");
    });
</script>
</head>
<body>
  <!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">赠送券管理</div>
                <div class="page-item form-panel">
                   <form action="${base}/manage/account/consumerCoupons!update.htm" id="validateForm" method="post" >
                        <ul>
                           <li><span class="lab-title">券名称</span>
                                <div class="form-item">
                                   <input name="conConsumerCoupons.couponName"  class="text" size="30" value="${conConsumerCoupons.couponName!}"  validate="{required:true,maxlength:20}" />
                                   <input name="conConsumerCoupons.ruleId" type="hidden" class="text" size="30" value="${conConsumerCoupons.ruleId !}"  validate="{required:true,maxlength:20}" />
                                   <input name="id" type="hidden" class="text" size="30" value="${conConsumerCoupons.id !}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                           <li><span class="lab-title">价值</span>
                                <div class="form-item">
                                   <input name="conConsumerCoupons.price"  class="text" size="30" value="${conConsumerCoupons.price!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                             <li><span class="lab-title">状态</span>
                                <div class="form-item">
                                   <select name="conConsumerCoupons.couponStatus">
										<option	value="0" <#if conConsumerCoupons.couponStatus??&&conConsumerCoupons.couponStatus==0>selected</#if>>停用</option>
										<option	value="1" <#if conConsumerCoupons.couponStatus??&&conConsumerCoupons.couponStatus==1>selected</#if>>启用</option>
								</select>
                                </div>
                            </li>
                             <li><span class="lab-title">失效时间</span>
                                <div class="form-item">
                                    <input name="conConsumerCoupons.expiratDate" value="${conConsumerCoupons.expiratDate?string("yyyy-MM-dd")}"  class="text" value="" size="30" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})" validate="{required:true}"/>
                                </div>
                            </li>
                              <li><span class="lab-title">库存</span>
                                <div class="form-item">
                                   <input name="conConsumerCoupons.couponNum"  class="text" size="30" value="${conConsumerCoupons.couponNum!}"  validate="{required:true,number:true}" />
                                </div>
                            </li>
                             
                              
                            <li><span class="lab-title">描述</span>
                                <div class="form-item">
                                    <textarea name="conConsumerCoupons.remark" id="" class="textArea" validate="{maxlength:500}">${conConsumerCoupons.remark}</textarea>
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
