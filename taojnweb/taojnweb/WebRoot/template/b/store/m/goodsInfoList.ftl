<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<#include "/template/m/common/utils.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<script type="text/javascript">
	
</script>
</head>
<body>
<div class="rightArea">
	<div class="r_ContentNav">
		<div class="r_tit1">
			<ul>
				<li>商品列表</li>
			</ul>
		</div>
		<div class="r_content">
			<form action="" id="searchForm" action="${base}/manage/store/goods.htm" method="get" >
				<div class="tool_search">
					商品名称：<input name="goodsInfo.goodsName" type="text" class="input_1" <#if goodsInfo.goodsName?exists>value="${goodsInfo.goodsName}"</#if> size="15" />
					商品类型：
					<select name="goodsInfo.styleClassCode" style="width:120px;">
						<option value="">--全部--</option>
						<#list styleDicts as styleType>
							<option value="${styleType.dictDetailCode}" <#if goodsInfo.styleClassCode?exists && goodsInfo.styleClassCode==styleType.dictDetailCode>selected</#if>>${styleType.dictDetailValue!}</option>
						</#list>
					</select>
					颜色分类：
					<select name="goodsInfo.colorClassCode" style="width:120px;">
						<option value="">--全部--</option>
						<#list colorDicts as colorType>
							<option value="${colorType.dictDetailCode}" <#if goodsInfo.colorClassCode?exists && goodsInfo.colorClassCode==colorType.dictDetailCode>selected</#if>>${colorType.dictDetailValue!}</option>
						</#list>
					</select>
					尺寸类型：
					<select name="goodsInfo.sizeClassCode" style="width:120px;">
						<option value="">--全部--</option>
						<#list sizeDicts as size>
							<option value="${size.dictDetailCode}" <#if goodsInfo.sizeClassCode?exists && goodsInfo.sizeClassCode==size.dictDetailCode>selected</#if>>${size.dictDetailValue!}</option>
						</#list>
					</select>
					上架标志：<select name="goodsInfo.useFlag">
								<option value="" <#if !(goodsInfo.useFlag??)>selected</#if>>--全部--</option>
								<option value="1" <#if goodsInfo.useFlag=true>selected</#if>>是</option> 
								<option value="0" <#if (goodsInfo.useFlag!true)=false>selected</#if>>否</option>
							</select>															
					<input type="submit" class="btn_search" name="button" id="searchButton" value="查询"/>
					<span class="shux"></span>
			<input type="button" class="btn_search" name="button2" id="button2" onclick="location.href='${base}/manage/store/goods!add.htm'" value="新增" />
			   </div>
			</form>
		 	<div class="tableNav">
				<#assign flag=0>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablelistNav">
					<tr>
					  <td width="50" align="center" class="table_title">序号</td>
					  <td align="120" align="center" class="table_title">商品名称</td>
					  <td width="100" align="center" class="table_title">商品类型</td>
					  <td width="60" align="center" class="table_title">商品颜色</td>
					  <td width="60" align="center" class="table_title">尺寸</td>
					  <td width="60" align="center" class="table_title">季节</td>
					  <td width="60" align="center" class="table_title">采购价格</td>
					  <td width="60" align="center" class="table_title">销售价格</td>
					  <td align="100" align="center" class="table_title">标签</td>
					  <td width="50" align="center" class="table_title">上架标志</td>
					  <td width="200" width="center" align="center" class="table_title">操作</td>
					</tr>
			    	<#list resultPage.result as res>
						<#assign flag=1>
					    <#if res_index%2=1>
					    <tr class="tr_cur1">
					    <#else>
					    <tr class="tr_cur2">
					    </#if>
					      <td align="left">${res_index+1}</td>
					      <td align="left">${res.goodsName!}</td>
					      <td align="left">${res.styleClassName!}</td>
					      <td align="left">${res.colorClassName!}</td>
					      <td align="left">${res.sizeClassName!}</td>
					      <td align="left">
						      <#if res.season=="SPRING">春季
						      <#elseif res.season=="SUMMER">夏季
						      <#elseif res.season=="AUTUMN">秋季
						      <#elseif res.season=="WINTER">冬季
						      </#if>
					      </td>
					      <td align="right">${res.purchasePrice?c}</td>
					      <td align="right">${res.sellPrice?c}</td>
					      <td align="right">${res.tagNames!}</td>
					      <td align="center">${res.useFlag?string("是","否")}</td>
					      <td align="left">
					        <a href="${base}/manage/store/goods!info.htm?id=${res.id}">详细</a>
					        &nbsp;<a href="${base}/manage/store/goods!edit.htm?id=${res.id}">修改</a>
					        <#if res.useFlag>
					        &nbsp;<a href="javascript:common.doPost('${base}/manage/store/goods!disableSell.htm?id=${res.id}','确定下架吗?');">下架</a>
					        <#else>
					        &nbsp;<a href="javascript:common.doPost('${base}/manage/store/goods!enableSell.htm?id=${res.id}','确定上架吗?');">上架</a>
					        </#if>
					        <a href="${base}/manage/store/stock.htm?stock.goodsId=${res.id}">库存管理</a>
					        &nbsp;<a href="javascript:common.doPost('${base}/manage/store/goods!del.htm?id=${res.id}','确定删除吗?');">删除</a>
					      </td>
					    </tr>
					</#list>
					<#if flag=0>
					    <tr>
					      <td align="center" colSpan="11" class="table_title">无相关记录</td>
					    </tr>
			    	</#if>
			  </table>
			</div>
			<#if flag==1>
				<@paginate pageCount=resultPage.totalPage currentPage=resultPage.thisPageNumber url=pageUrl></@paginate>
			</#if>
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
