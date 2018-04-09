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
<link href="${base}/scripts/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css" media="screen"/>
<script src="${base}/scripts/jquery.mousewheel-3.0.4.pack.js"></script>
<script src="${base}/scripts/jquery.fancybox-1.3.4.js"></script>
<script src="${base}/scripts/dialog.js"></script>
<script language="JavaScript">
dialog.callback=function(obj){
		$("#accountId").val(obj.accountId);
		$("#accountName").val(obj.accountName);
		$("#corpName").html(obj.corpName);
	}
</script>
<meta name="Authors" content="Tomzhou">
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new BackUrlForm("validateForm","新增电子券","${base}/manage/account/electronicCoup.htm");
    });
</script>
</head>
<body>
  <!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">新增电子券</div>
                <div class="page-item form-panel">
                   <form action="${base}/manage/account/electronicCoup!save.htm" id="validateForm" method="post" >
                        <ul>
                           <li><span class="lab-title">客户名称</span>
                                <div class="form-item">
                                <span id="corpName"></span>
                                   <input type="hidden" name="electronicCoup.accountName" id="accountName"  class="text" size="30" value="${electronicCoup.accountName!}"  validate="{required:true}" />
                                    <input  type="hidden" name="id" id="accountId"  class="text" size="30" value="${electronicCoup.accountId!}"  validate="{required:true}" />
                                   <a dialog="{width:900,height:550}" href="${base}/manage/account/electronicCoup!tradeAccount.htm">选择客户</a> 
                                </div>
                            </li>
                             <li><span class="lab-title">充值来源</span>
                                <div class="form-item">
                                   <input type="radio" name="electronicCoup.eletName"  value="现金" />现金
                                   <input type="radio" name="electronicCoup.eletName"  value="银行卡" />银行卡
                                   <input type="radio" name="electronicCoup.eletName"  value="江南券" />江南券
                                   <input type="radio" name="electronicCoup.eletName"  value="储蓄卡" />储蓄卡
                                   <#--input name="electronicCoup.eletName"  class="text" size="30" value="${electronicCoup.eletName!}"  validate="{required:true,maxlength:20}" /-->
                                </div>
                            </li>
                             <li><span class="lab-title">电子券编号</span>
                                <div class="form-item">
                                   <input name="electronicCoup.eletCode"  class="text" size="30" value="${electronicCoup.eletCode}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                             <li><span class="lab-title">金额</span>
                                <div class="form-item">
                                   <input type="text" name="electronicCoup.leftValue"  class="text" size="30" value="${electronicCoup.leftValue!}"  validate="{required:true,maxlength:20,number:true}" />
                                </div>
                            </li>
                             <#--li><span class="lab-title">充值时间</span>
                                <div class="form-item">
                                    <input name="electronicCoup.rechgeTime" class="text" value="" size="30" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})" validate="{required:true}"/>
                                </div>
                            </li-->
                              <li><span class="lab-title">来源</span>
                                <div class="form-item">
                                <select name="electronicCoup.resource">
									<#list sysDictDetails as item>
										<option	value=${item.dictDetailCode} <#if electronicCoup.resource==item.dictDetailCode >selected</#if>>${item.dictDetailName}</option>
									</#list>
								</select>
                                </div>
                            </li>
                          
                            <li><span class="lab-title">电子券描述</span>
                                <div class="form-item">
                                    <textarea name="electronicCoup.remark" id=""  class="textArea" validate="{maxlength:500}">${electronicCoup.remark}</textarea>
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
