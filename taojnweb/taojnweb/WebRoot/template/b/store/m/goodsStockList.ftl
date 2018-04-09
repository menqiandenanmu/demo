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
        <li>库存管理</li>
   </ul>
</div>
<div class="r_content">
	<form action="${base}/manage/stock/goodsStock.htm" id="searchForm" method="get" >
<div class="tool_search">
<input type="submit" class="btn_search" name="button" id="searchButton" value="查询"/>
  </div>
	</form>
<div class="tableNav">
		<#assign flag=0>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="list">
          <tr>
               <td width="50" align="center" class="table_title">序号</td>
             <td align="center" class="table_title">商品名称</td>
             <td width="100" align="center" class="table_title">总数量</td>
             <td width="100" align="center" class="table_title">当前数量</td>
             <td align="center" class="table_title">操作员名称</td>
             <td width="300" align="center" class="table_title">&nbsp;操作</td>
          </tr>
         	<#list resultPage.result as goodsStock>
	         	<#assign flag=1>
			    <#if goodsStock_index%2=1>
			    <tr class="tr_cur1">
			    <#else>
			    <tr class="tr_cur2">
			    </#if>
	            <td align="center">${goodsStock_index+1!}</td>
	         	<td align="left">${goodsStock.goodsName!}</td>
	         	<td align="right">${goodsStock.totalNum!}</td>
	         	<td align="right">${goodsStock.num!}</td>
	         	<td align="left">${goodsStock.loginName!}</td>
               <td align="center">
                	<a href="${base}/manage/store/goodsPurchase!add.htm?id=${goodsStock.id}">商品入库</a>
                	<a href="${base}/manage/store/goodsSell!add.htm?id=${goodsStock.id}">商品出库</a>
			        <a href="${base}/manage/store/goodsPurchase.htm?id=${goodsStock.id}">入库记录</a>
			        <a href="${base}/manage/store/goodsSell.htm?id=${goodsStock.id}">出库记录</a>
			    </td>
            </#list>
	    		
          </tr>
          <#if flag=0>
          <tr>
            <td align="center" colSpan="6" class="table_title">无相关记录</td>
          </tr>
          </#if>
        </table>
        </div>
		<@paginate pageCount=resultPage.totalPage currentPage=resultPage.thisPageNumber url=pageUrl>
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
