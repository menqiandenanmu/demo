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
<#include "/template/m/common/uploadfile.ftl">
<meta name="Authors" content="Tomzhou">
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new BackUrlForm("validateForm","新增页面区域内容","${base}/manage/web/pageAreaDetail.htm");
    });
</script>
</head>
<body>
  <!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">新增页面区域内容</div>
                <div class="page-item form-panel">
                   <form action="${base}/manage/web/pageAreaDetail!save.htm" id="validateForm" method="post" >
                    <input name="id" type="hidden" value="${area.id}" />
                        <ul>
                             <li><span class="lab-title">页面区域：</span>
                                <div class="form-item">
                                  ${area.name}
                                </div>
                            </li>
                             <li><span class="lab-title">标题名称：</span>
                                <div class="form-item">
                                 <input name="areaDetail.title" class="text" type="text" class="input_1_light" id="areaDetail.title" size="30" validate="{required:true,minlength:2}" />
                                </div>
                            </li>
                            <#if area.showFlag=1 || area.showFlag=2>
                             <li><span class="lab-title">图片文件：</span>
                                <div class="form-item">
                                 <@upload name="areaDetail.showUrl" multi="false" url=base+"/manage/uploadFile.htm">
          						</@upload>
                                </div>
                            </li>
                             </#if>
                              <li><span class="lab-title">地址url：</span>
                                <div class="form-item">
					             <input name="areaDetail.infoUrl" class="text" type="text" class="input_1_light" id="areaDetail.infoUrl" size="32" value="http://" validate="{required:true,url:true}" />
                                </div>
                            </li>
                            <li><span class="lab-title">说明文字：</span>
                                <div class="form-item">
                                  <textarea name="areaDetail.info" cols="45" rows="5" class="textarea_1" id="areaDetail.info" validate="{maxlength:1000}"></textarea>
                                </div>
                            </li>
                             <li><span class="lab-title">打开方式：</span>
                                <div class="form-item">
                                  <select name="areaDetail.targetFlag">
					          		<option value="1">新页面打开</option>
					          		<option value="0">当前页面打开</option>
					          	</select>
                                </div>
                            </li>
                             <li><span class="lab-title">排序值：</span>
                                <div class="form-item">
                                  <input name="areaDetail.orderid" class="text" type="text" class="input_1_light" id="areaDetail.orderid" size="32" validate="{required:true,digits:true,min:1}" />
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
