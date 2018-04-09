<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<#include "/template/m/common/utils.ftl">
<link href="${base}/script/datepicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
<link href="${base}/style/m/css/reset.css" rel="stylesheet" type="text/css">
<link href="${base}/style/m/css/common.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/page.css"/>
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new BackUrlForm("messageForm","确定删除","${base}/manage/account/electronicCoup.htm");
    });
    function changeBox(){
			if($("input[name='allCheck']")[0].checked){
				var messages = $("input[name='messageIds']");
				for(var i=0;i<messages.length;i++){
					messages[i].checked=true;
				}
			}else{
				var messages = $("input[name='messageIds']");
				for(var i=0;i<messages.length;i++){
					messages[i].checked=false;
				}
			}
		}
    function deleteMessage(){
			var messageFlag = true;
			var messages = $("input[name='messageIds']");
			for(var i=0;i<messages.length;i++){
				if(messages[i].checked){
					messageFlag = false;
					break;
				}
			}
			if(messageFlag){
				alert("请先选择一个电子券");
				return false;
			}
			$("#messageForm").submit();
			
		}
		 function updateMessage(){
			 var $messgeIds=$('input[name="messageIds"]:checked');
			 var messageIds="";
			 if($messgeIds.length==0){
			 	alert("请先选择一个电子券");
			 	return;
			 }else{
			 	for(var i=0;i<$messgeIds.length;i++){
				 	if(i==0){
				 		messageIds=messageIds+$($messgeIds[i]).val()
				 	}else{
				 		messageIds=messageIds+","+$($messgeIds[i]).val()
				 	}
			 	}
			 }
		 	//var options={id,"1"};
		 	
		 location.href = "${base}/manage/account/electronicCoup!toAudit.htm?ids="+messageIds;
		 }
		
</script>
</head>
<body>
<!--列表正文开始-->
    <div class="page-main">
        <div class="page-item page-title">电子券审核管理</div>
        <!--索搜区域-->
		<form action="${base}/manage/account/electronicCoup!audit.htm" id="searchForm" method="get" >
        <div class="page-item search-panel">
 		  <ul class="search-list clearfix">
 		   <li><span class="lab-title">客户名称:</span><span>
				<span><input type="text" name="electronicCoup.accountName" class="text text-small" value="${electronicCoup.accountName!}" />
			</span>
			</li>
            <li><span class="lab-title">充值来源:</span><span>
				<span><input type="text" name="electronicCoup.eletName" class="text text-small" value="${electronicCoup.eletName!}" />
			</span>
			</li>
			<li><span class="lab-title">审核人:</span><span>
				<span><input type="text" name="electronicCoup.auditName" class="text text-small" value="${electronicCoup.auditName!}" />
			</span>
			</li>
			<li><span class="lab-title">来源:</span><span>
				<span>
				<select name="electronicCoup.resource" style="width:180px;">
						<option	value="" >全部</option>
					<#list sysDictDetails as item>
						<option	value=${item.dictDetailCode} <#if electronicCoup.resource==item.dictDetailCode >selected</#if>>${item.dictDetailName}</option>
					</#list>
				</select>
			</span>
			</li>
			<li><span class="lab-title">审核状态:</span><span>
				<span>
					<select name="electronicCoup.auditSyatus" style="width:180px;">
					<option	value="">全部</option>
					<option	value="0" <#if electronicCoup.auditSyatus==0>selected</#if> >未审核</option>
					<option value="1" <#if electronicCoup.auditSyatus==1>selected</#if>>审核通过</option>
					</select>
				</span>
			</li>
			<li><span class="lab-title">充值状态:</span><span>
				<span>
					<select name="electronicCoup.status" style="width:180px;">
					<option	value="">全部</option>
					<option	value="0" <#if electronicCoup.status==0>selected</#if> >未充值</option>
					<option value="1" <#if electronicCoup.status==1>selected</#if> >已充值</option>
					</select>
				</span>
			</li>
			 <li><span class="lab-title">充值时间:</span><span>
				<span><input type="text" name="electronicCoup.rechgeTime" class="text text-small" value="${(electronicCoup.rechgeTime?string("yyyy-MM-dd"))?default('')}" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
			</span>
			</li>
           
        </ul>
	
	 	 </div>
        <!--索搜区域结束-->
       <!--列表操作区域-->
        <div class="page-item list-handle">
        <input type="submit" value="搜索" class="btn btn-small bg-blue"/>&nbsp;&nbsp;&nbsp;&nbsp;
             <input type="button" class="btn btn-small bg-blue" name="button3" id="button3" onclick="updateMessage();" value="审核选中" />
        </div>
        	</form>
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
        <form action="${base}/manage/account/electronicCoup!delAll.htm" id="messageForm" name="messageForm" method="post" >
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                    <td width="2%" align="left" class="table_title"><input type="checkbox" name="allCheck" value="" onchange="changeBox();" ></input></td>
                     <th class="w5" width="80px;">序号</th>
					  <th class="w10" width="80px;">客户名称</th>
					   <th class="w10" width="80px;">充值来源</th>
					  <th class="w10" width="80px;">金额</th>
					  <th class="w10" width="80px;">来源</th>
					  <th class="w10" width="80px;">充值状态</th>
					  <th class="w10" width="80px;">审核状态</th>
					  <th class="w10" width="80px;">充值时间</th>
					   <th class="w10" width="280px;">审核人</th>
					  <th class="w20" width="280px;">操作</th>
					 </tr>
					</thead>
                <tbody>
	    	<#list pages.result as res>
				<#assign flag=1>
			    <tr  id="${res.id}">
			    <td width="2%" align="left" ><#if res.auditSyatus==0><input type="checkbox" name="messageIds" value="${res.id?if_exists}"></input></#if></td>
			      <td align="center">${res_index+1}</td>
			      <td align="center">${res.accountName?default("")}</td>
			        <td align="center">${res.eletName!}</td>
			      <td align="center">${res.leftValue!}</td>
			      <td align="center">
		       		<#list sysDictDetails as item>
						<#if res.resource==item.dictDetailCode >${item.dictDetailName}</#if>
					</#list></td>
			      <td align="center"><#if res.status==1>已充值<#else>未充值</#if></td>
			      <td align="center"><#if res.auditSyatus==0>未审核<#elseif res.auditSyatus=1>审核通过</#if></td>
			      <td align="center">${(res.rechgeTime?string("yyyy-MM-dd"))!"未充值"}</td>
			      <td align="center">${res.auditName!"未审核"}</td>
			      <td align="center">
		      		<#if res.auditSyatus==0><a href="${base}/manage/account/electronicCoup!toAudit.htm?ids=${res.id}">审核</a></#if>
			      </td>
			    </tr>
			</#list>
			<#if flag=0>
			    <tr>
			      <td align="center" colspan="10" class="table_title">无相关记录</td>
			    </tr>
	    	</#if>
	  </tbody>
        </table>
        </form>
 <div class="list-bottom clearfix">
 <@paginate pageCount=pages.totalPage currentPage=pages.thisPageNumber url=pageUrl></@paginate>

</div>
</div>
</div>
</body>
</html>