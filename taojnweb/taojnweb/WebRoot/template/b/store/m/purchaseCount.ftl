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

</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
<div class="r_tit1">
	<ul>
        <li>入库统计</li>
   </ul>
</div>
<div class="r_content">
	<form action="${base}/manage/store/purchaseCount.htm" id="searchForm" method="get" >
<div class="tool_search">
&nbsp;&nbsp;&nbsp;时间：<input name="begCreateDate" type="text"value="${(begCreateDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>-<input name="endCreateDate" type="text" value="${(endCreateDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
<input type="submit" class="btn_search" name="button" id="searchButton" value="查询"/>
  </div>
	</form>
<div class="tableNav">
		<#assign flag=0>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="list">
          <tr>
           <td width="50" align="center" class="table_title">序号</td>
            <td width="80" align="center" class="table_title">商品名称</td>
            <td width="80" align="center" class="table_title">数量</td>
            <td width="80" align="center" class="table_title">日期</td>
          </tr>
         	<#list goodsPurchasePage.result as goodsSell>
	         	<#assign flag=1>
			    <#if goodsSell_index%2=1>
			    <tr class="tr_cur1">
			    <#else>
			    <tr class="tr_cur2">
			    </#if>
	            <td align="center">${(goodsSell_index+1)!}</td>
	         	<td align="left">${goodsSell.goodsName!}</td>
	         	<td align="right">${goodsSell.num!}</td>
	         	<td align="right">${goodsSell.createTime?string("yyyy-MM-dd")!}</td>
            </#list>
	    		
          </tr>
          <#if flag=0>
          <tr>
            <td align="center" colSpan="3" class="table_title">无相关记录</td>
          </tr>
          </#if>
        </table>
        </div>
		<@paginate pageCount=goodsPurchasePage.totalPage currentPage=goodsPurchasePage.thisPageNumber url=pageUrl>
		</@paginate>
    </div>
<div class="tool_2">
    <div class="tool_2_L"></div>
    <div class="tool_2_R"></div>
</div>
</div>
</div>
</div>
</body>
</html>
