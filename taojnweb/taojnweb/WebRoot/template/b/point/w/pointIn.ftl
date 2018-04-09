<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>用户中心</title>
<#include "/template/w/common/head.ftl">
<#include "/template/w/common/utilsPage.ftl">
<link href="${base}/style/w/css/index/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/style/w/css/index/hyzx.css" rel="stylesheet" type="text/css" />
</head>
<body>
   <div class="contant">
<#include "/template/w/top.ftl">
<!--通用头部-->
  <div class="centant">
 <#include "/template/b/account/w/userCenterLeft.ftl">
<div class="hyzx_r">
 <#include "/template/b/account/w/consumerInfo.ftl">
			 <div class="zfdd_up">
                <ul id="myTab0">
                    <li class="active" onclick="location.href='${base}/point/pointIn.htm'">收入</li>
                    <li class="normal" onclick="location.href='${base}/point/pointOut.htm'">支出</li>
                </ul>
             </div>
			    <div class="zfdd_end" id="myTab0_Content0">
                 <h4>这里列出您所有收入积分记录。
               	  您当前可用积分数量：<strong class="jf">${pointAccountInfo.point!0}</strong>
                 </h4>
                 <table  width="100%" border="1">
                   <tr class="mc">
                     <td width="15%"  align="center">编号</td>
                     <td width="20%"  align="center">时间</td>
                     <td width="10%"  align="center">积分</td>
                     <td width="20%"  align="center">用途</td>
                      <td width="35%"  align="center">说明</td>
                   </tr>
             <#if pointDetailPage.result?exists && pointDetailPage.result.size() gt 0>
       				 <#list pointDetailPage.result as pointDetail>
				<tr class="tdList">
					<td align="center">${pointDetail_index+1}</td>
					<td align="center">${(pointDetail.createTime?string("yyyy-MM-dd HH:mm:ss"))!""}</td>
					<td align="right">${pointDetail.point!""}</td>
					<td align="center">订单号${pointDetail.orderNo!""}</td>
					 <td align="left"><#if pointDetail.remark?length lt 20>${pointDetail.remark!("&nbsp;")}
			      <#else>
			      ${pointDetail.remark[0..19]}..
			      </#if>
		      </td>
				</tr>
			</#list>
			<#else>
		 <tr>
            <td colspan="5" align="center">
				无相关记录
			</td>
		 </tr>
		 </#if>
         </table>
      
         <div class="fnye">
           <@paginate pageCount=pointDetailPage.totalPage currentPage=pointDetailPage.thisPageNumber url=pageUrl></@paginate>            
         </div>
        </div>
		 
</div> 
 </div>
    <!--通用尾部-->
    </div><!--contant-->
 <#include "/template/w/foot.ftl">
</body>
</html>