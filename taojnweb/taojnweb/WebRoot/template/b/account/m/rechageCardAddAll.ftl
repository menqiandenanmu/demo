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
<#include "/template/m/common/uploadfile.ftl">

<script type="text/javascript">
    $(document).ready(function(){
     	var fm=new BackUrlForm("validateForm","充值卡批量新增","${base}/manage/account/rechageCard.htm");
    });

</script>
</head>
<body>
  <!--表单正文开始-->
    <div class="page-main">
        <div class="page-item page-title">充值卡批量新增</div>
        <div class="page-item form-panel">
        <form action="${base}/manage/account/rechageCard!readExcel.htm" enctype="multipart/form-data" id="validateForm" method="post" >
              <div class="tableNav_2">
     			<table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
       				<tr style="height:40px">
          				<td width="120" align="right" class="table_l_tit">模板下载：</td>
          				<td class="table_l_td">
          	  			<a href="${base}/template/xsl/rechageCardModel.xls"> 下载模板</a>
          				</td>
        			</tr>
         			<tr>
          				<td align="right" class="table_l_tit">名单上传：</td>
          				<td class="table_l_td">
          					<@upload name="excel" multi="false" url=base+"/manage/uploadFile.htm"> </@upload>
			 				<#if message!="">
          						${message}
          					</#if>
          				</td>
        			</tr> 
      			</table>
			</div>
            <div class="form-handle">
                <input type="submit"  id="btnSave" name="提交" value="提交" class="btn btn-small bg-blue"/>
            </div>
         </form>
        </div>
    </div>
</body>
</html>
