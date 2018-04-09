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
     	var fm=new BackUrlForm("validateForm","新增催款","${base}/manage/account/dunning.htm");
    });
</script>
</head>
<body>
  <!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">催款新增</div>
                 <div class="page-item page-title"><a href="${base}/manage/account/dunning!choseAcc.htm" >点击选择用户</a></div>
                <div class="page-item form-panel">
                   <form action="${base}/manage/account/dunning!save.htm" id="validateForm" method="post" >
                        <ul>
                            <#if dunningInfo?exists&&dunningInfo.accountName?exists>
                           <li><span class="lab-title">已选用户</span>
                                <div class="form-item">
                                ${dunningInfo.accountName!}
                                   <input name="dunningInfo.accountName" type="hidden" class="text" size="30" value="${dunningInfo.accountName!}"  validate="{required:true}" />
                                   <input name="dunningInfo.accountId" type="hidden" class="text"  size="30" value="${dunningInfo.accountId}"  validate="{required:true}" />
                                </div>
                            </li>
                            </#if>
                          
                            <li><span class="lab-title">催款描述</span>
                                <div class="form-item">
                                    <textarea name="dunningInfo.contentInfo" id="" class="textArea"></textarea>
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
