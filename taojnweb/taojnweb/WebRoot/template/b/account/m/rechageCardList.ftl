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
<!--[if lte IE 8]>
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/ie8.css" />
<![endif]-->
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new BackUrlForm("messageForm","确定删除","${base}/manage/account/rechageCard.htm");
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
				alert("请先选择一个消息");
				return false;
			}
			$("#messageForm").submit();
			
		}
		 function updateMessage(){
			 var $messgeIds=$('input[name="messageIds"]:checked');
			 var messageIds="";
			 if($messgeIds.length==0){
			 	alert("请先选择一个消息");
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
		 	var options={ids:messageIds};
		 	//var options={id,"1"};
		 	
		 	common.doPostData('${base}/manage/account/rechageCard!useAll.htm','确定启用吗?', options);
		 
		 }
		
</script>
</head>
<body>
<!--列表正文开始-->
    <div class="page-main">
        <div class="page-item page-title">充值卡管理</div>
        <!--索搜区域-->
		<form action="${base}/manage/account/rechageCard.htm" id="searchForm" method="get" >
        <div class="page-item search-panel">
 		  <ul class="search-list clearfix">
            <li><span class="lab-title">名称:</span><span>
				<span><input type="text" name="rechageCard.cardName" class="text text-small" value="${rechageCard.cardName!}" />
			</span>
			</li>
			  <li><span class="lab-title">卡号:</span><span>
				<span><input type="text" name="rechageCard.cardCode" class="text text-small" value="${rechageCard.cardCode!}" />
			</span>
			</li>
			 <li><span class="lab-title">类型:</span><span>
				 <select name="rechageCard.cardType"  style="width:120px;">
				 <option value="">全部</option>
					<#list sysDictDetailsType as item>
						<option	value=${item.dictDetailCode} <#if rechageCard.cardType==item.dictDetailCode >selected</#if>>${item.dictDetailName}</option>
					</#list>
				</select>
			</span>
			 <li><span class="lab-title">生成年度:</span><span>
				<span><input type="text" name="rechageCard.createYear" class="text text-small" value="${rechageCard.createYear!}" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue',dateFmt:'yyyy'})"/>
			</span>
			</li>
			<li><span class="lab-title">失效时间:</span><span>
			<span><input name="rechageCard.failureTime" type="text"  class="text text-small" value="" size="30" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>
			</span>
			</li>
			<li><span class="lab-title">状态:</span><span>
				<select name="rechageCard.status"  style="width:120px;">
				<option value="">全部</option>
				<option value="0" <#if rechageCard.status==0>selected</#if>>停用</option>
				<option value="1" <#if rechageCard.status==1>selected</#if>>启用</option>
				</select>
			</span>
			</li>
			 <li><span class="lab-title">审核状态:</span><span>
				<span>
					<select name="rechageCard.auditSyatus"  style="width:180px;">
					<option	value="">全部</option>
					<option	value="0" <#if rechageCard.auditSyatus==0>selected</#if>>未审核</option>
					<option value="1" <#if rechageCard.auditSyatus==1>selected</#if>>审核通过</option>
					</select>
				</span>
			</li>
			<li><span class="lab-title">审核人:</span><span>
				<span><input name="rechageCard.auditPerson" type="text" class="text text-small" size="30" value="${rechageCard.auditPerson}"/>
			</span>
			</li>
            
        </ul>
		
	 	 </div>
        <!--索搜区域结束-->
       <!--列表操作区域-->
        <div class="page-item list-handle">
        <input type="submit" value="搜索" class="btn btn-small bg-blue"/>&nbsp;&nbsp;&nbsp;&nbsp;
           <input type="button" class="btn btn-small bg-blue" name="button3" id="button3" onclick="deleteMessage();" value="删除选中" />&nbsp;&nbsp;&nbsp;&nbsp;
             <input type="button" class="btn btn-small bg-blue" name="button3" id="button3" onclick="updateMessage();" value="启用选中" />&nbsp;&nbsp;&nbsp;&nbsp;
            
				<input type="button" class="btn btn-small bg-blue" onClick="location.href='${base}/manage/account/rechageCard!rechageCardExport.htm?${queryString}'" value="导出">
			&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:;" onclick="location.href='${base}/manage/account/rechageCard!add.htm'" class="btn btn-small bg-blue" id="add-btn">+新增</a>&nbsp;&nbsp;&nbsp;&nbsp;
             <a href="javascript:;" onclick="location.href='${base}/manage/account/rechageCard!addAll.htm'" class="btn btn-small bg-blue" id="add-btn">+批量新增</a>
        </div>
        </form>
        <!--列表操作区域结束-->
 	<!--列表-->
        <div class="page-item list-panel">
        <form action="${base}/manage/account/rechageCard!delAll.htm" id="messageForm" name="messageForm" method="post" >
            <table class="list-table" id="listTable">
                <thead>
                    <tr>
                    <td width="2%" align="left" class="table_title"><input type="checkbox" name="allCheck" value="" onchange="changeBox();" ></input></td>
                     <th class="w5" width="80px;">序号</th>
					  <th class="w20" width="80px;">名称</th>
					  <th class="w20" width="80px;">卡号</th>
					  <th class="w10" width="80px;">类型</th>
					  <th class="w10" width="80px;">生成年度</th>
					  <th class="w10" width="80px;">失效时间</th>
					  <th class="w5" width="80px;">面额</th>
					   <th class="w5" width="80px;">状态</th>
					  <th class="w10" width="80px;">审核状态</th>
					   <th class="w10" width="80px;">审核人</th>
					  <th class="w20" width="280px;">操作</th>
					 </tr>
					</thead>
                <tbody>
	    	<#list pages.result as res>
				<#assign flag=1>
				
			    <tr  id="${res.id}">
			    <td width="2%" align="left" ><input type="checkbox" name="messageIds" value="${res.id?if_exists}"></input></td>
			      <td align="center">${res_index+1}</td>
			      <td align="center">${res.cardName?default("")}</td>
			       <td align="center">${res.cardCode?default("")}</td>
			      <td align="center">
				      	<#list sysDictDetailsType as item>
							<#if res.cardType==item.dictDetailCode >${item.dictDetailName}</#if>
						</#list>
					</td>
			      <td align="center">${res.createYear!}</td>
			       <td align="center">${res.failureTime?string("yyyy-MM-dd")!}</td>
			      <td align="center">${res.cardMoney}</td>
			      <td align="center"><#if res.status==0>停用<#elseif res.status==1>启用</#if></td>
			      <td align="center"><#if res.auditSyatus==0>未审核<#elseif res.auditSyatus==1>审核通过<#elseif res.auditSyatus==2>审核不通过</#if></td>
			      <td align="center">${res.auditPerson?default("未审核")}</td>
			      <td align="center">
			      <#if res.auditSyatus==0>
			      	<a href="${base}/manage/account/rechageCard!edit.htm?id=${res.id}">编辑</a>
		      		&nbsp;<a href="javascript:common.doPost('${base}/manage/account/rechageCard!del.htm?id=${res.id}','确定删除吗?');">删除</a></#if>
		      		<#if res.useStatus==0&&res.status==0><a href="javascript:common.doPost('${base}/manage/account/rechageCard!doUse.htm?id=${res.id}','确定启用吗?');">启用</a></#if>
			     	<a href="${base}/manage/account/rechageCard!info.htm?id=${res.id}">详情</a>
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