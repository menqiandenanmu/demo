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
<meta name="Authors" content="Tomzhou">
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new GeneralForm("validateForm","修改");
    });
</script>
</head>
<body>
  <!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">充值说明管理</div>
                <div class="page-item form-panel">
                   <form action="${base}/manage/account/rechargeInfo!save.htm" id="validateForm" method="post" >
                        <ul>
                            <li><span class="lab-title">标题</span>
                                <div class="form-item">
                                   <input name="rechargeInfo.name" type="text" class="text" id="name" size="30" value=""  validate="{required:true}" />
                                </div>
                            </li>
                           
                          
                            <li><span class="lab-title">充值说明</span>
                                <div class="form-item">
                                    <textarea name="rechargeInfo.contentInfo" id="" class="textArea" validate="{required:true}"></textarea>
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
