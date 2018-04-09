<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "/template/w/common/head.ftl">
<#include "/template/w/common/utilsPage.ftl">
<title>莫高窟电子商务 会员中心（我的积分）</title>
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/user.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/reset-min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/common.css" media="screen" />
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
</head>
<body>
 <#include "/template/w/top.ftl">
<div class="contain">
<#assign userShowTag=3>
 <#include "/template/w/userTop.ftl">
    
    <div class="userinfoChange">
         <div class="changeCon3">
            <div class="searchCon">
             <form action="${base}/point/userPointDetail.htm" id="validateForm" method="get" >
                <div class="searchstate ">状态：<a href="${base}/point/userPointDetail.htm" class="current">全部</a><a href="${base}/point/userPointDetail.htm?opType=1"> 收入</a> <a href="${base}/point/userPointDetail.htm?opType=2">  支出</a></div><!--searchstate-->
                <div class="searchdate">日期：
                  <input name="begCreateDate" id="textfield" type="text" class="text" value="${(begCreateDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>-<input name="endCreateDate"  id="textfield" type="text" class="text" value="${(endCreateDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
                  <input name="" type="submit" class="btn" id="searchButton" value="" />
                <a href="${base}/point/userPointDetail.htm?timeString=1">一个月</a>
                 <a href="${base}/point/userPointDetail.htm?timeString=3">3个月</a>
                 <a href="${base}/point/userPointDetail.htm?timeString=12"> 一年前</a> </div><!--searchstate-->
                 </form>
            </div><!--searchCon-->
            <table class="Myorderlist" width="100%" border="0" cellpadding="0" cellspacing="0">
 		 <tr class="first">
			    <td width="17%" height="38" align="center">编号</td>
			    <td width="22%" height="38" align="center">时间</td>
			    <td width="34%" height="38" align="center">来源/用途</td>
			    <td width="15%" height="38" align="center">收入</td>
			    <td width="12%" height="38" align="center">支出</td>
	    </tr>
  	 <#if pointDetailPage.result?exists && pointDetailPage.result.size() gt 0>
        <#list pointDetailPage.result as pointDetail>
         	   <tr>
	         	  <td width="17%" height="38" align="center">${pointDetail_index+1}</td>
	         	   <td width="22%" height="38" align="center">${(pointDetail.createTime?string("yyyy-MM-dd HH:mm:ss"))!""}</td>
			      <td width="34%" height="38" align="center">${pointDetail.remark!""}/订单号${pointDetail.orderNo!""}</td>
			      <#if pointDetail.opType==1 || pointDetail.opType==0 >
			      <td  width="15%" height="24" align="center" class="price">${pointDetail.point!""}</td>
			      </#if>
			      <td>&nbsp;</td>
			      <#if pointDetail.opType==2>
			      <td width="12%" height="24" align="center" class="expenses">${pointDetail.point!""}</td>
	      		 </#if>
		      </tr>
         </#list>
        <#else>
          <tr>
		      <td align="center" colspan="9" class="table_title">无相关记录</td>
		    </tr>
        </#if>
  	</table>
	<div class="paggings " style=" padding-left:560px">
          <@paginate pageCount=pointDetailPage.totalPage currentPage=pointDetailPage.thisPageNumber url=pageUrl></@paginate>
        </div><!--paggings-->

      </div><!--changeCon-->
         
    </div><!--userinfoChange-->
   
   </div><!--userContain-->
  
</div><!--contain-->
  <#include "/template/w/foot.ftl">
</body>
</html>
