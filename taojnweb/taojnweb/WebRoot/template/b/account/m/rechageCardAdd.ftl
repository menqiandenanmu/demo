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
     	var fm=new BackUrlForm("validateForm","新增充值卡","${base}/manage/account/rechageCard.htm");
    });
</script>
</head>
<body>
  <!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">充值卡新增</div>
                <div class="page-item form-panel">
                   <form action="${base}/manage/account/rechageCard!save.htm" id="validateForm" method="post" >
                        <ul>
                           <li><span class="lab-title">充值卡名称</span>
                                <div class="form-item">
                                   <input name="rechageCard.cardName"  class="text" size="30" value="${rechageCard.cardName!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                           <li><span class="lab-title">充值卡卡号</span>
                                <div class="form-item">
                                   <input name="rechageCard.cardCode"  class="text" size="30" value="${rechageCard.cardCode!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                             <li><span class="lab-title">充值卡类型</span>
                                <div class="form-item">
                                   <select name="rechageCard.cardType">
									<#list sysDictDetailsType as item>
										<option	value=${item.dictDetailCode} <#if rechageCard.cardType==item.dictDetailCode >selected</#if>>${item.dictDetailName}</option>
									</#list>
								</select>
                                </div>
                            </li>
                             <li><span class="lab-title">生成年度</span>
                                <div class="form-item">
                                   <input name="rechageCard.createYear"  class="text" size="15" value="${rechageCard.createYear!}" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue',dateFmt:'yyyy'})" validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                             <li><span class="lab-title">失效时间</span>
                                <div class="form-item">
                                    <input name="rechageCard.failureTime"  class="text" value="" size="30" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})" validate="{required:true}"/>
                                </div>
                            </li>
                              <li><span class="lab-title">充值卡面额</span>
                                <div class="form-item">
                                   <input name="rechageCard.cardMoney"  class="text" size="30" value="${rechageCard.cardMoney!}"  validate="{required:true,number:true}" />
                                </div>
                            </li>
                              <li><span class="lab-title">来源</span>
                                <div class="form-item">
                                <select name="rechageCard.source">
									<#list sysDictDetails as item>
										<option	value=${item.dictDetailCode} <#if rechageCard.source==item.dictDetailCode >selected</#if>>${item.dictDetailName}</option>
									</#list>
								</select>
                                </div>
                            </li>
                            <li><span class="lab-title">充值卡密码</span>
                                <div class="form-item">
                                   <input name="rechageCard.cardPassword"  class="text" size="30" value="${rechageCard.cardPassword!}"  validate="{required:true,number:true}" />
                                </div>
                            </li>
                            <li><span class="lab-title">充值卡描述</span>
                                <div class="form-item">
                                    <textarea name="rechageCard.remark" id="" class="textArea" validate="{maxlength:500}">${rechageCard.remark}</textarea>
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
