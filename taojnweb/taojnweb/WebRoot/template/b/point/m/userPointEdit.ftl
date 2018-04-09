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
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new BackUrlForm("validateForm","赠送积分","${base}/manage/point/userPoint.htm");
    });
</script>
</head>
<body>
<!--表单正文开始-->
    <div class="page-main">
        <div class="page-item page-title">赠送积分</div>
        <div class="page-item form-panel">
    <form action="${base}/manage/point/userPoint!savePoint.htm" id="validateForm" method="get" >
    <ul>
          <li><span class="lab-title">用户名称：</span>
          <div class="form-item">
            <input name="pointAccountInfo.accName" type="hidden"  class="text" size="18" value="${(pointAccountInfo.accName)!}" />
            <input name="id" type="hidden" class="text" value="${(pointAccountInfo.id)!}" />
            <span class="lab-title">${(pointAccountInfo.accName)!}</span>
          </div>
        </li>
        <li><span class="lab-title">数量：</span>
          <div class="form-item">
          <input name="pointChangeDetal.point" type="text"  value="0" class="text" style="width:200px;" validate="{digits:true}"/>
          </div>
        </li>
       <li><span class="lab-title">备注信息：</span>
              <div class="form-item">
            <textarea name="pointChangeDetal.remark" cols="45" rows="5" class="textarea_1" id="textarea" validate="{maxlength:1000}"></textarea>
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