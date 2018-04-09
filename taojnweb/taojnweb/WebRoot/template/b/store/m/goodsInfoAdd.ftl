<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<#include "/template/m/common/fckeditor.ftl">
<#include "/template/m/common/uploadfile.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<link href="${base}/script/datepicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
     	var fm=new BackUrlForm("validateForm","添加商品","${base}/manage/store/goods.htm");
    });
   
</script>
</head>
<body>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>商品新增：</li>
        </ul>
    </div>
    <div class="r_content">
    <form action="${base}/manage/store/goods!save.htm" id="validateForm" method="post" name="myForm">
    <input type="hidden" name="goodsInfo.storeId" value="${(goodsStoreInfo.id)!}"/>
    <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
      	  <tr>
          <td width="90" align="right" colSpan="2" class="table_l_tit">商品名称：</td>
          <td class="table_l_td" width="300">
         	<input name="goodsInfo.goodsName" type="text" class="input_1_light" size="30" value="" validate="{required:true}"/>
	      </td>
          <td width="90" align="right" class="table_l_tit">商品编号：</td>
          <td class="table_l_td">
         	<input name="goodsInfo.goodsCode" type="text" class="input_1_light" size="30" value="" validate="{required:true}"/>
	      </td>
        </tr>
      	<tr>
      	<td width="90" align="right" colSpan="2" class="table_l_tit">商品类型：</td>
          <td class="table_l_td" >
          	<select name="goodsInfo.styleClassCode" validate="{required:true}">
	          	<#list styleDicts as style>
	          		<option value="${style.dictDetailCode}">${style.dictDetailValue}</option>
	          	</#list>
          	</select>
	      </td>
	      <td width="90" align="right" class="table_l_tit">尺寸：</td>
          <td class="table_l_td">
          	<select name="goodsInfo.sizeClassCode" validate="{required:true}">
	          	<#list sizeDicts as size>
	          		<option value="${size.dictDetailCode}">${size.dictDetailValue}</option>
	          	</#list>
          	</select>
	      </td>
        </tr>
        <tr>
          <td width="90" align="right" colSpan="2" class="table_l_tit">采购价：</td>
          <td class="table_l_td">
         	<input name="goodsInfo.purchasePrice" maxlength="10" type="text" class="input_1_light" size="30" value="" validate="{required:true,number:true,min:0}"/>
	      </td>
          <td width="90" align="right" class="table_l_tit">销售价格：</td>
          <td class="table_l_td">
         	<input name="goodsInfo.sellPrice" maxlength="10" type="text" class="input_1_light" size="30" value="" validate="{required:true,number:true,min:0}"/>
	      </td>
        </tr>
        <tr>
          <td width="90" align="right" class="table_l_tit" colSpan="2">颜色类型：</td>
          <td class="table_l_td">
          	<select name="goodsInfo.colorClassCode" validate="{required:true}">
	          	<#list colorDicts as color>
	          		<option value="${color.dictDetailCode}">${color.dictDetailValue}</option>
	          	</#list>
          	</select>
	      </td>
          <td align="right" class="table_l_tit">图片：</td>
          <td class="table_l_td">
          	  <@upload name="fileName" multi="false" url="${base}/manage/uploadFile.htm"/>
          </td>
        </tr> 
        <tr>
          <td width="90" align="right" colSpan="2" class="table_l_tit">单次购买最大数量：</td>
          <td class="table_l_td">
         	<input name="goodsInfo.maxAmount" maxlength="10" type="text" class="input_1_light" size="30" value=""  validate="{required:true,digits:true,min:0}"/>
	      </td>
          <td width="90" align="right" class="table_l_tit">购买最小数量：</td>
          <td class="table_l_td">
         	<input name="goodsInfo.minAmount" maxlength="10" type="text" class="input_1_light" size="30" value=""  validate="{required:true,digits:true,min:0}"/>
	      </td>
        </tr>
         <tr>
          <td width="90" align="right" colSpan="2" class="table_l_tit">季节：</td>
          <td class="table_l_td">
         	<select name="goodsInfo.season" validate="{required:true}">
          		<option value="SPRING">春季</option>
          		<option value="SUMMER">夏季</option>
          		<option value="AUTUMN">秋季</option>
          		<option value="WINTER">冬季</option>
          	</select>
	      </td>
          <td align="right" class="table_l_tit">标签：</td>
          <td class="table_l_td" align="left">
          	<#list dictTagsL as tag>
          	${tag.dictDetailValue}<input name="tagsCode" type="checkbox" value="${tag.dictDetailCode}"/>
          	</#list>
          </td>
          </tr>
        <tr>
          <td width="90" align="right" colSpan="2" class="table_l_tit">上架标志：</td>
          <td class="table_l_td">
          	  <input name="goodsInfo.useFlag" type="radio"  value="false"/>未上架
          	  <input name="goodsInfo.useFlag" type="radio"  value="true" checked="true"/>上架 
          </td>
          <td width="90" align="right" class="table_l_tit">排序：</td>
          <td class="table_l_td">
         	<input name="goodsInfo.orderid" type="text" class="input_1_light" size="30" value="" />
	      </td>
        </tr>
         <tr>
          <td  width="90" align="right" colSpan="2" class="table_l_tit">商品概述：</td>
          <td class="table_l_td" colSpan="3">
          	  <textarea rows="5" name="goodsInfo.summarize" cols="82" class="pwb_input2"  validate="{required:true}"></textarea>
          </td>
        </tr> 
         <tr>
          <td width="90" align="right" colSpan="2" class="table_l_tit">备注：</td>
          <td class="table_l_td"  colSpan="3">
          	  <textarea rows="5" name="goodsInfo.remark" cols="82" class="pwb_input2" ></textarea>
          </td>
        </tr> 
        <tr>
          <td align="right" colSpan="2" class="table_l_tit">详情信息：</td>
          <td class="table_l_td"  colSpan="3">
          	  <@fck name=goodsInfo.contentStr/>
          </td>
        </tr>
        <tr>
          <td align="right" colSpan="2" class="table_l_tit">提示信息：</td>
          <td class="table_l_td"  colSpan="3">
          	  <@fck name=goodsInfo.infoStr/>
          </td>
        </tr> 
      </table>
    </div>
    <div class="tool_2">
        <div class="tool_2_L">
        <input type="submit" class="btn02" name="button" id="button1" value="保存">
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
