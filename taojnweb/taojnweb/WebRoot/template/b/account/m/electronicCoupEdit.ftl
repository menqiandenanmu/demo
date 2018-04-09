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
     	var fm=new BackUrlForm("validateForm","修改电子券","${base}/manage/account/electronicCoup.htm");
    });
</script>
</head>
<body>
  <!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">修改电子券</div>
                <div class="page-item form-panel">
                   <form action="${base}/manage/account/electronicCoup!update.htm" id="validateForm" method="post" >
                   <input name="id" hidden class="text" size="30" value="${electronicCoup.id!}"  validate="{required:true}" />
                        <ul>
                           <li><span class="lab-title">客户名称</span>
                                <div class="form-item">
                                   <input type="hidden" name="electronicCoup.accountName"  class="text" size="30" value="${electronicCoup.accountName!}"  validate="{required:true,maxlength:20}" />
                                   ${electronicCoup.accountName}
                                </div>
                            </li>
                             <li><span class="lab-title">充值来源</span>
                                <div class="form-item">
                                   <#if electronicCoup.eletName="现金">
                                   	   <input type="radio" name="electronicCoup.eletName"  value="现金" checked/>现金
	                                   <input type="radio" name="electronicCoup.eletName"  value="银行卡" />银行卡
	                                   <input type="radio" name="electronicCoup.eletName"  value="江南券" />江南券
	                                   <input type="radio" name="electronicCoup.eletName"  value="储蓄卡" />储蓄卡
                                   <#elseif electronicCoup.eletName="银行卡">
                                   	   <input type="radio" name="electronicCoup.eletName"  value="现金" />现金
	                                   <input type="radio" name="electronicCoup.eletName"  value="银行卡" checked/>银行卡
	                                   <input type="radio" name="electronicCoup.eletName"  value="江南券" />江南券
	                                   <input type="radio" name="electronicCoup.eletName"  value="储蓄卡" />储蓄卡
                                   <#elseif electronicCoup.eletName="江南券">
                                   	   <input type="radio" name="electronicCoup.eletName"  value="现金" />现金
	                                   <input type="radio" name="electronicCoup.eletName"  value="银行卡" />银行卡
	                                   <input type="radio" name="electronicCoup.eletName"  value="江南券" checked/>江南券
	                                   <input type="radio" name="electronicCoup.eletName"  value="储蓄卡" />储蓄卡
	                               <#elseif electronicCoup.eletName="储蓄卡">
	                               	   <input type="radio" name="electronicCoup.eletName"  value="现金" />现金
	                                   <input type="radio" name="electronicCoup.eletName"  value="银行卡" />银行卡
	                                   <input type="radio" name="electronicCoup.eletName"  value="江南券" />江南券
	                                   <input type="radio" name="electronicCoup.eletName"  value="储蓄卡" checked/>储蓄卡
	                               </#if>
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
                                   <input name="electronicCoup.leftValue"  class="text" size="30" value="${electronicCoup.leftValue!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                             <#--li><span class="lab-title">充值时间</span>
                                <div class="form-item">
                                    <input name="electronicCoup.rechgeTime" class="text" value="${electronicCoup.rechgeTime?string("yyyy-MM-dd")!}" size="30" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
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
                                    <textarea name="electronicCoup.remark" id="" class="textArea" validate="{maxlength:500}">${electronicCoup.remark}</textarea>
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
