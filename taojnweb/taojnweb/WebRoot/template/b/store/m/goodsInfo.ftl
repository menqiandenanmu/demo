<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<#include "/template/m/common/uploadfile.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>商品管理</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1">
      <font size="2" color="#FF0033">商品详细</font>
    </div>
    <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
        <tr>
          <td align="right" class="table_l_tit" width="100">商品名称：</td>
          <td class="table_l_tit" width="150">
          ${goodsInfo.goodsName}
          </td>
          <td align="right" class="table_l_tit" width="100">商品编号：</td>
          <td class="table_l_tit" width="150">
	          ${goodsInfo.goodsCode}
          </td>
          <td align="right" class="table_l_tit" width="100">商品类型：</td>
          <td class="table_l_tit" width="150">
	          ${goodsInfo.styleClassName}
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit" width="100">商品颜色：</td>
          <td class="table_l_tit" width="150">
          	${goodsInfo.colorClassName}
          </td>
          <td align="right" class="table_l_tit" width="100">尺寸：</td>
          <td class="table_l_tit" width="150">
          	${goodsInfo.sizeClassName!}
          </td>
          <td align="right" class="table_l_tit" width="100">季节：</td>
          <td class="table_l_tit" width="150">
	          <#if goodsInfo.season=="SPRING">春季
		      <#elseif goodsInfo.season=="SUMMER">夏季
		      <#elseif goodsInfo.season=="AUTUMN">秋季
		      <#elseif goodsInfo.season=="WINTER">冬季
		      </#if>
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit" width="100">采购价格：</td>
          <td class="table_l_tit" width="150">
          ${(goodsInfo.purchasePrice)?c}
          </td>
          <td align="right" class="table_l_tit" width="100">销售价格：</td>
          <td class="table_l_tit" width="450">
	          ${goodsInfo.sellPrice?c}
          </td>
          <td align="right" class="table_l_tit" width="100">上架标志：</td>
          <td class="table_l_tit" width="450">
	         ${goodsInfo.useFlag?string("是","否")}
          </td>
          <tr>
          <td align="right" class="table_l_tit" width="100">最大数量：</td>
          <td class="table_l_tit" width="150">
          ${goodsInfo.maxAmount!0}
          </td>
          <td align="right" class="table_l_tit" width="100">最小数量：</td>
          <td class="table_l_tit" width="450">
	          ${goodsInfo.minAmount!0}
          </td>
           <td align="right" class="table_l_tit" width="100">标签：</td>
          <td class="table_l_tit" width="150">
           ${goodsInfo.tagNames!}
          </td>
        </tr>
        <tr>
        <td width="100" align="right" class="table_l_tit">图片:</td>
          <td class="table_l_tit" colSpan="5" width="700">
			<#if titleImg?? >
			<li>
			<span class='upload'>${titleImg}</span>
        	<a href='${titleImg}' target='_blank'>预览</a>
        	</li>
			</#if>
	      </td>
        </tr>
      <tr>
          <td width="100" align="right" class="table_l_tit" width="100">排序值:</td>
          <td class="table_l_tit" width="150">
			${goodsInfo.orderid}	
	      </td>
          <td width="100" align="right" class="table_l_tit" >商品概述:</td>
          <td class="table_l_tit" width="150">
			${goodsInfo.summarize}	
	      </td>
          <td width="100" align="right" class="table_l_tit">备注:</td>
          <td class="table_l_tit" width="150">
			${goodsInfo.remark}	
	      </td>
        </tr>
        <tr>
      <td align="right" class="table_l_tit" width="100">详情信息：</td>
          <td class="table_l_tit" colSpan="5" width="700">
				${goodsInfo.contentStr!}
          </td>
          </tr>
        <tr>
      <td align="right" class="table_l_tit" width="100">提示信息：</td>
          <td class="table_l_tit" colSpan="5" width="700">
				${goodsInfo.infoStr!}
          </td>
          </tr>
      </table>
    </div>
    <div class="tool_2">
        <div class="tool_2_L">
        <input type="button" class="btn02" name="button" id="button" onclick="window.history.back();" value="返 回" />
        </div>
        <div class="tool_2_R"></div>
    </div>
    </form>
    </div>
</div>
</div>
</body>
</html>