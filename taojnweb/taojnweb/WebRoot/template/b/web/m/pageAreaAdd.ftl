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
<meta name="Authors" content="Tomzhou">
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new BackUrlForm("validateForm","新增页面区域","${base}/manage/web/pageArea.htm");
    });
</script>
</head>
<body>
  <!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">新增页面区域</div>
                <div class="page-item form-panel">
                   <form action="${base}/manage/web/pageArea!save.htm" id="validateForm" method="post" >
                        <ul>
                             <li><span class="lab-title">名称：</span>
                                <div class="form-item">
                                   <input name="area.name"  class="text" size="30" value="${(area.name)!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                             <li><span class="lab-title">编号：</span>
                                <div class="form-item">
                                   <input name="area.code"  class="text" size="30" value="${(area.code)!}"  validate="{required:true,maxlength:20}" />
                                </div>
                            </li>
                             <li><span class="lab-title">说明：</span>
                                <div class="form-item">
                                   <textarea name="area.info" cols="45" rows="5" class="textarea_1" id="textarea" validate="{maxlength:1000}"></textarea>
                                </div>
                            </li>
                              <li><span class="lab-title">显示方式：</span>
                                <div class="form-item">
					              <select name="area.showFlag">
					          		<option value="0">文本</option>
					          		<option value="1">图片</option>
					          	</select>
                                </div>
                            </li>
                            
                            <li><span class="lab-title">内容条数：</span>
                                <div class="form-item">
                                   <input name="area.len" cols="45" rows="5" class="text" id="area.len" validate="{required:true,digits:true,min:1}" />
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
