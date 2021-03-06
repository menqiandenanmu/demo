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
     	var fm=new GeneralForm("validateForm","添加交易账户类型");
    });
</script>
</head>
<body>
<!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">新增交易账户类型</div>
                <div class="page-item form-panel">
    <form action="${base}/manage/account/tradeAccount!save.htm" id="validateForm" method="get" >
    <ul>
           <li><span class="lab-title">类型名称: </span>
           <div class="form-item">
	          <input name="tradeAccountInfo.accName" type="text" class="text" id="tradeAccountInfo.accName" size="32" maxlength="50" validate="{required:true,minlength:2}" />
          </div>
        </li>
          <li><span class="lab-title">类型编码: </span>
           <div class="form-item">
	          <input name="tradeAccountInfo.accCode" type="text" class="text" id="tradeAccountInfo.accCode" size="50" maxlength="50" validate="{required:true,minlength:2}" />
          </div>
        </li>
          <li><span class="lab-title">是否有效: </span>
           <div class="form-item">
	         		 <select name="tradeAccountInfo.useFlag">
							<option value="1" selected="selected">是</option>
							<option value="0">否</option>
					  </select>
          </div>
        </li>
         <li><span class="lab-title">备注信息: </span>
          <div class="form-item">
	          			<textarea name="tradeAccountInfo.content" cols="45" rows="5" class="textarea_1" id="tradeAccountInfo.content"></textarea>	
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