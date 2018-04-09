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
     	var fm=new BackUrlForm("validateForm","新增折扣","${base}/manage/account/discountRule.htm");
    });
    
  
</script>
</head>
<body>
  <!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">折扣新增</div>
                <div class="page-item form-panel">
                   <form action="${base}/manage/account/discountRule!save.htm" id="validateForm" method="post" >
                        <ul>
                           <li><span class="lab-title">折扣名称</span>
                                <div class="form-item">
                                   <input name="discountRules.storeName"  class="text" size="30" value="${discountRules.storeName!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                           <li><span class="lab-title">	折扣参数</span>
                                <div class="form-item">
                                   <input name="discountRules.minNum"  class="text" size="30" value="${discountRules.minNum!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                           <li><span class="lab-title">	折扣比例</span>
                                <div class="form-item">
                                   <input name="discountRules.discountNum"  class="text" size="30" value="${discountRules.discountNum!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                             <li><span class="lab-title">状态</span>
                                <div class="form-item">
                                   <select name="discountRules.ruleStatus">
										<option	value="0">停用</option>
										<option	value="1">启用</option>
								</select>
                                </div>
                            </li>
                             <li><span class="lab-title">开始时间</span>
                                <div class="form-item">
                                   <input name="discountRules.beginTime"  class="text" size="15" value="${discountRules.beginTime!}" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue',dateFmt:'yyyy-MM-dd'})" validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                             <li><span class="lab-title">结束时间</span>
                                <div class="form-item">
                                    <input name="discountRules.endTime"  class="text" value="" size="30" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})" validate="{required:true}"/>
                                </div>
                            </li>
                            
                              
                            <li><span class="lab-title">描述</span>
                                <div class="form-item">
                                    <textarea name="discountRules.remark" id="" class="textArea" validate="{maxlength:500}">${discountRules.remark}</textarea>
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
