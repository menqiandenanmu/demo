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
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new GeneralForm("validateForm","添加数据字典");
    });
</script>
</head>
<body>
<!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">添加数据字典内容</div>
                <div class="page-item form-panel">
    <div class="tool_1">&nbsp;<font size="2" color="#FF0033">&nbsp;${message?default("")}</font></div>
    <form action="${base}/manage/sys/dict!detailSave.htm" id="validateForm" method="post" >
    <input name="id" type="hidden" value="${dict.id}" />
     <ul>
          <li><span class="lab-title">编号：</span>
          <div class="form-item">
	          ${dict.dictCode}<input id="detail.dictDetailCode" name="detail.dictDetailCode" type="text"  class="text text-small" size="3" validate="{required:true,rangelength:[3,3]}"/>3位长度
          </div>
        </li>
         <li><span class="lab-title">名称：</span>
          <div class="form-item">
	          <input id="detail.dictDetailName" name="detail.dictDetailName" type="text"  class="text text-small" size="32" validate="{required:true,minlength:1}" value=''/>
           </div>
        </li>
        <li><span class="lab-title">值：</span>
          <div class="form-item">
	          <input name="detail.dictDetailValue" type="text"  class="text text-small" id="detail.dictDetailValue" size="32" value='' validate="{required:true,regstr:/${dict.valueRegex?default(".*")}/}" />
         </div>
        </li>
        <li><span class="lab-title">排序值：</span>
          <div class="form-item">
	          <input name="detail.listSort" type="text"  class="text text-small" id="detail.listSort" size="32" value='1' validate="{required:true,digits:true}" />
         </div>
        </li>
         <li><span class="lab-title">使用状态：</span>
          <div class="form-item">
			<select name="detail.useFlag" class="select">
				<option value=1>使用中</option>
				<option value=0>停用</option>
			</select>
         </div>
        </li>
       <li><span class="lab-title">备 注：</span>
          <div class="form-item"><textarea name="detail.remark" cols="45" rows="5" class="textarea_1"  maxlength="512" id="textarea"></textarea></td>
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