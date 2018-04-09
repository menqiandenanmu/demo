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
        <li>资讯管理</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1">
      <font size="2" color="#FF0033">资讯详细</font>
    </div>
    <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
        <tr>
          <td align="right" class="table_l_tit" width="100">标题：</td>
          <td class="table_l_tit" width="150">
          ${newsInfo.title}
          </td>
          <td align="right" class="table_l_tit" width="100">出处：</td>
          <td class="table_l_tit" width="150">
	          ${newsInfo.source}
          </td>
          <td align="right" class="table_l_tit" width="100">作者：</td>
          <td class="table_l_tit" width="150">
          	${newsInfo.auth}
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit" width="100">关键字：</td>
          <td class="table_l_tit" width="150">
          	${newsInfo.keyWorks!}
          </td>
          <td align="right" class="table_l_tit" width="100">分类名称：</td>
          <td class="table_l_tit" width="150">
          	${newsInfo.className}
          </td>
          <td align="right" class="table_l_tit" width="100">阅读次数：</td>
          <td class="table_l_tit" width="150">
           ${newsInfo.readNum!0}
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit" width="100">创建时间：</td>
          <td class="table_l_tit" width="150">
          ${(newsInfo.createTime?string("yyyy-MM-dd HH"))!}
          </td>
          <td align="right" class="table_l_tit" width="100">摘要：</td>
          <td class="table_l_tit" colSpan="3" width="450">
	          ${newsInfo.summary!}
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
			${newsInfo.orderid}	
	      </td>
          <td width="100" align="right" class="table_l_tit" >页面元描述:</td>
          <td class="table_l_tit" width="150">
			${newsInfo.description}	
	      </td>
          <td width="100" align="right" class="table_l_tit">页面关键字:</td>
          <td class="table_l_tit" width="150">
			${newsInfo.keywords}	
	      </td>
        </tr>
        <tr>
      <td align="right" class="table_l_tit" width="100">内容：</td>
          <td class="table_l_tit" colSpan="5" width="700">
				${content!}
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