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
                <div class="page-item page-title">充值卡详情</div>
                <div class="page-item form-panel">
                        <ul>
                          <li><span class="lab-title">充值卡名称</span>
                                <div class="form-item">
                                   ${rechageCard.cardName!}
                                </div>
                            </li>
                           <li><span class="lab-title">充值卡卡号</span>
                                <div class="form-item">
                                   ${rechageCard.cardCode!}
                                </div>
                            </li>
                             <li><span class="lab-title">充值卡类型</span>
                                <div class="form-item">
                                   ${rechageCard.cardType}
                                </div>
                            </li>
                             <li><span class="lab-title">生成年度</span>
                                <div class="form-item">
                                   ${rechageCard.createYear!}
                                </div>
                            </li>
                             <li><span class="lab-title">失效时间</span>
                                <div class="form-item">
                                    ${rechageCard.failureTime?string("yyyy-MM-dd")!}
                                </div>
                            </li>
                              <li><span class="lab-title">充值卡面额</span>
                                <div class="form-item">
                             	${rechageCard.cardMoney!}
                                </div>
                            </li>
                          
                            <li><span class="lab-title">充值卡描述</span>
                                <div class="form-item">
                                    ${rechageCard.remark}
                                </div>
                            </li>
                        </ul>
                </div>
            </div>
            <!--列表正文结束-->


</body>
</html>
