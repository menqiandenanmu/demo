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
</head>
<body>
  <!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">电子券详情</div>
                <div class="page-item form-panel">
                        <ul>
                           <li><span class="lab-title">客户名称</span>
                                <div class="form-item">
                                ${electronicCoup.accountName!}
                                </div>
                            </li>
                             <li><span class="lab-title">充值来源</span>
                                <div class="form-item">
                                ${electronicCoup.eletName!}
                                </div>
                            </li>
                             <li><span class="lab-title">电子券编号</span>
                                <div class="form-item">
                                ${electronicCoup.eletCode}
                                </div>
                            </li>
                             <li><span class="lab-title">金额</span>
                                <div class="form-item">
                                ${electronicCoup.leftValue!}
                                </div>
                            </li>
                             <li><span class="lab-title">审核状态</span>
                                <div class="form-item">
                               <#if electronicCoup.auditSyatus==0>未审核<#elseif electronicCoup.auditSyatus=1>审核通过</#if>
                                </div>
                            </li>
                             <li><span class="lab-title">充值状态</span>
                                <div class="form-item">
                               <#if electronicCoup.status==1>已充值<#else>未充值</#if>
                                </div>
                            </li>
                             <li><span class="lab-title">充值时间</span>
                                <div class="form-item">
                               	 ${(electronicCoup.rechgeTime?string("yyyy-MM-dd HH:mm:ss"))!"未充值"}
                                </div>
                            </li>
                              <li><span class="lab-title">来源</span>
                                <div class="form-item">
									<#list sysDictDetails as item>
										 <#if electronicCoup.resource==item.dictDetailCode >${item.dictDetailName}</#if>
									</#list>
                                </div>
                            </li>
                          
                            <li><span class="lab-title">电子券描述</span>
                                <div class="form-item">
                                ${electronicCoup.remark}
                                </div>
                            </li>
                                <li><span class="lab-title">审核说明</span>
                                <div class="form-item">
                                ${electronicCoup.auditRemark}
                                </div>
                            </li>
                        </ul>
                </div>
            </div>
            <!--列表正文结束-->


</body>
</html>
