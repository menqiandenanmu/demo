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
        <li>明细</li>
        </ul>
    </div>
<div class="r_content">
<div class="tableNav_2">
    <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
      <tr>
            <td align="right" class="table_l_tit">商品ID</td>
             <td class="table_l_td" align="left">
	          ${goodsStock.goodsId!}
          	</td>
            <td align="right" class="table_l_tit">商品名称</td>
             <td class="table_l_td" align="left">
	          ${goodsStock.goodsName!}
          	</td>
            <td align="right" class="table_l_tit">总数量</td>
             <td class="table_l_td" align="left">
	          ${goodsStock.totalNum!}
          	</td>
            <td align="right" class="table_l_tit">数量</td>
             <td class="table_l_td" align="left">
	          ${goodsStock.num!}
          	</td>
            <td align="right" class="table_l_tit">操作员ID</td>
             <td class="table_l_td" align="left">
	          ${goodsStock.loginId!}
          	</td>
            <td align="right" class="table_l_tit">操作员名称</td>
             <td class="table_l_td" align="left">
	          ${goodsStock.loginName!}
          	</td>
            <td align="right" class="table_l_tit">创建时间</td>
             <td class="table_l_td" align="left">
	          ${goodsStock.createTime!}
          	</td>
          </tr>
        </table>
        </div>
    <div class="tool_2">
        <div class="tool_2_L">
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