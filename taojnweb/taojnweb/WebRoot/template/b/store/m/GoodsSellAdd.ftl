<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<#include "/template/m/common/utils.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
<script language="JavaScript">
$(document).ready(function(){
     	var fm=new GeneralForm("validateForm","出库");
    });
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
<div class="r_tit1">
    	<ul>
        <li>出库</li>
        </ul>
    </div>
	<div class="r_content">
	<form action="${base}/manage/store/goodsSell!save.htm" id="validateForm" method="get" >
	<input type="hidden" name="id" value="${goodsStock.id}" />
	<div class="tableNav">
    <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
      <tr>
            <td align="right" class="table_l_tit">商品名称</td>
             <td class="table_l_td" align="left">
             ${goodsStock.goodsName}
          	</td>
          	 </tr>
          	  <tr>
             <td align="right" class="table_l_tit">入库数量</td>
             <td class="table_l_td" align="left">
	          <input name="goodsSell.num" type="text" class="input_1_light" id="goodsSell.num" size="32" maxlength="50" validate="{required:true,digits:true,min:0}"/>
          	</td>
          	 </tr>
          	  <tr>
             <td align="right" class="table_l_tit">备注</td>
             <td class="table_l_td" align="left">
              <textarea rows="5" name="goodsSell.remark" cols="82" class="pwb_input2" ></textarea>
          	</td>
          </tr>
        </table>
        </div>
    <div class="tool_2">
        <div class="tool_2_L">
        <input type="submit" class="btn02" name="button" id="button1" value="添加" />
        <input type="button" class="btn02" name="button" id="button" onclick="location.href='${backUrl}'" value="返 回" />
        </div>
        <div class="tool_2_R"></div>
    </div>
    </form>
    </div>
</div>
</div>
</body>
</html>