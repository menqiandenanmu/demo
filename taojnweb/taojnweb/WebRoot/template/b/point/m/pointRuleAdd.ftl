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
<meta name="Authors" content="Tomzhou">
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new BackUrlForm("validateForm","新增积分规则","${base}/manage/point/pointRule.htm");
    });
</script>
</head>
<body>
  <!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">新增积分规则</div>
                <div class="page-item form-panel">
                   <form action="${base}/manage/point/pointRule!save.htm" id="validateForm" method="post" >
                        <ul>
                           <li><span class="lab-title">赠送条件</span>
                                <div class="form-item">
                                   <input name="pointRule.opValue"  class="text" style="width: 189px;" size="15" value="${pointRule.opValue}"  validate="{required:true,number:true}" />&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">注：例如满10送1，填10</font>
                                </div>
                            </li>
                              <li><span class="lab-title">赠送数量</span>
                                <div class="form-item">
                                   <input name="pointRule.amount"  class="text" style="width: 189px;" size="15" value="${pointRule.amount}"  validate="{required:true,number:true}" />&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">注：例如满10送1，填1</font>
                                </div>
                            </li>
                            <li><span class="lab-title">行为</span>
                              <div class="form-item">
                            	<select name="pointRule.bnsType">
	                            	<option value="0" <#if pointRule.bnsType==0>selected</#if> >充值</option>
	                             	<option value="1" <#if pointRule.bnsType==1>selected</#if> >消费</option>
	                             	<option value="2" <#if pointRule.bnsType==2>selected</#if> >退款</option>
                               </select>
                                </div>
                            </li>
                           
                             <li><span class="lab-title">状态</span>
                               <div class="form-item">
                                <select name="pointRule.useStatus">
	                            	<option value="0" <#if pointRule.useStatus==0>selected</#if> >停用</option>
	                             	<option value="1" <#if pointRule.useStatus==1>selected</#if> >启用</option>
                               </select>
                                </div>
                            </li>
                                <li><span class="lab-title">来源</span>
                                <div class="form-item">
                                <select name="pointRule.source">
									<#list sysDictDetails as item>
										<option	value=${item.dictDetailCode} <#if  pointRule.source==item.dictDetailCode >selected</#if>>${item.dictDetailName}</option>
									</#list>
								</select>
                                </div>
                            </li>
                            <li><span class="lab-title">描述</span>
                                <div class="form-item">
                                    <textarea name="pointRule.remark" id="" class="textArea">${pointRule.remark}</textarea>
                                </div>
                            </li>
                        </ul>
                        <div class="form-handle">
                            <input type="submit" value="提交" class="btn btn-small bg-blue"/>
                        </div>
                    </form>
                </div>
            </div>


</body>
</html>
