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
     	var fm=new BackUrlForm("validateForm","修改活动规则","${base}/manage/account/sendRules.htm");
    });
</script>
</head>
<body>
  <!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">活动规则修改</div>
                <div class="page-item form-panel">
                   <form action="${base}/manage/account/sendRules!update.htm" id="validateForm" method="post" >
                    <input name="id" hidden class="text" size="30" value="${sendRules.id!}"  validate="{required:true}" />
                          <ul>
                           <li><span class="lab-title">活动规则名称</span>
                                <div class="form-item">
                                   <input name="sendRules.sendName"  class="text" size="30" value="${sendRules.sendName!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                           <li><span class="lab-title">使用渠道</span>
                                <div class="form-item">
                                   <input name="sendRules.resourceName"  class="text" size="30" value="${sendRules.resourceName!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                             <li><span class="lab-title">活动规则状态</span>
                                <div class="form-item">
                                   <select name="sendRules.ruleStatus">
										<option	value="0" <#if sendRules.ruleStatus??&&sendRules.ruleStatus==0>selected</#if>>停用</option>
										<option	value="1" <#if sendRules.ruleStatus??&&sendRules.ruleStatus==1>selected</#if>>启用</option>
								</select>
                                </div>
                            </li>
                             <li><span class="lab-title">开始时间</span>
                                <div class="form-item">
                                   <input name="sendRules.beginTime"  class="text" size="15" value="${(sendRules.beginTime?string("yyyy-MM-dd"))!}" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue',dateFmt:'yyyy-MM-dd'})" validate="{required:true}" />
                                </div>
                            </li>
                             <li><span class="lab-title">结束时间</span>
                                <div class="form-item">
                                    <input name="sendRules.endTime"  class="text" value="${(sendRules.endTime?string("yyyy-MM-dd"))!}" size="30" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})" validate="{required:true}"/>
                                </div>
                            </li>
                              <li><span class="lab-title">满额参数</span>
                                <div class="form-item">
                                   <input name="sendRules.sendSum"  class="text" size="30" value="${sendRules.sendSum!}"  validate="{required:true,number:true}" />
                                </div>
                            </li>
                              <li><span class="lab-title">赠送参数</span>
                                <div class="form-item">
                                   <input name="sendRules.sendAmount"  class="text" size="30" value="${sendRules.sendAmount!}"  validate="{required:true,number:true}" />
                                </div>
                            </li>
                             <li><span class="lab-title">取值方式</span>
                                <div class="form-item">
                                   <select name="sendRules.superimposedFalg" onchange="changeVal(this)">
										<option	value="0" <#if sendRules.superimposedFalg??&&sendRules.superimposedFalg==0>selected</#if>>最大值</option>
										<option	value="1" <#if sendRules.superimposedFalg??&&sendRules.superimposedFalg==1>selected</#if>>递增</option>
								</select>
                                </div>
                            </li>
                              <#--li id="maxNum"><span class="lab-title">最大值</span>
                                <div class="form-item">
                                   <input name="sendRules.maxNum"   class="text" size="30" value="${sendRules.maxNum!0}"  validate="{required:true,number:true}" />
                                </div>
                            </li>
                            <li id="remark2"><span class="lab-title">递增参数</span>
                                <div class="form-item">
                                   <input name="sendRules.remark2"  class="text" size="30" value="${sendRules.remark2!0}"  validate="{required:true,number:true}" />
                                </div>
                            </li-->
                              <li><span class="lab-title">当日兑换次数</span>
                                <div class="form-item">
                                   <input name="sendRules.todayCashNum"  class="text" size="30" value="${sendRules.todayCashNum!}"  validate="{required:true,number:true}" />
                                </div>
                            </li>
                              <li><span class="lab-title">期间兑换次数</span>
                                <div class="form-item">
                                   <input name="sendRules.activeCashNum"  class="text" size="30" value="${sendRules.activeCashNum!}"  validate="{required:true,number:true}" />
                                </div>
                            </li>
                              <li><span class="lab-title">最高限送</span>
                                <div class="form-item">
                                   <input name="sendRules.sendMaxNum"  class="text" size="30" value="${sendRules.sendMaxNum!}"  validate="{required:true,number:true}" />
                                </div>
                            </li>
                              <li><span class="lab-title">卷消费参数</span>
                                <div class="form-item">
                                   <input name="sendRules.spendParam"  class="text" size="30" value="${sendRules.spendParam!}"  validate="{required:true,number:true}" />
                                </div>
                            </li>
                              
                            <li><span class="lab-title">活动规则描述</span>
                                <div class="form-item">
                                    <textarea name="sendRules.remark" id="" class="textArea" validate="{maxlength:500}">${sendRules.remark}</textarea>
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
