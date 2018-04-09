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
    $(document).ready(function(){
     	var fm=new BackUrlForm("messageForm","确定删除","${base}/manage/account/message.htm");
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
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
		<ul>
			<li>消息管理</li>
		</ul>
	</div>
	<div class="r_content">
		<form action="${base}/manage/account/message.htm" id="searchForm" method="post" >
			<div class="tool_search">
				用户：<input name="message.accountName" type="text" class="input_1" value="${(message.accountName)!}" maxlength="50" />
				消息类型：<select name="message.type">
						<option value="">--全部--</option>
						<option value="0" <#if message.type?exists && message.type==0>selected</#if>>普通消息</option>
						<option value="1" <#if message.type?exists && message.type==1>selected</#if>>系统通知</option>
					  </select>
				时间：<input name="begCreateDate" type="text"value="${(begCreateDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>-<input name="endCreateDate" type="text" value="${(endCreateDate?string("yyyy-MM-dd"))!}" size="10" onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'blue'})"/>	  
					  </br>
				 标题：<input name="message.title" type="text" class="input_1" value="${(message.title)!}" maxlength="50"/>
				是否已读：<select name="message.readFlag">
						<option value="">--全部--</option>
						<option value="0" <#if message.readFlag?exists && message.readFlag==false>selected</#if>>未读</option>
						<option value="1" <#if message.readFlag?exists && message.readFlag==true>selected</#if>>已读</option>
					  </select>
				<input type="submit" class="btn_search" name="button" id="searchButton" value="查询"/>
				<span class="shux"></span>
				<input type="button" class="btn_search" name="button2" id="button2" onclick="location.href='${base}/manage/account/message!input.htm'" value="发送" />
				<span class="shux"></span> 
				<input type="button" class="btn_search" name="button3" id="button3" onclick="deleteMessage();" value="删除选中" />
		   </div>
		</form>
	 	<div class="tableNav">
		<#assign flag=0>
			<form action="${base}/manage/account/message!delAll.htm" id="messageForm" name="messageForm" method="post" >
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablelistNav">
			<tr>
			<td width="2%" align="left" class="table_title"><input type="checkbox" name="allCheck" value="" onchange="changeBox();" ></input></td>
			  <td width="50" align="center" class="table_title">序号</td>
			  <td align="center" class="table_title">用户名</td>
			  <td align="center" class="table_title">标题</td>
			  <td width="80" align="center" class="table_title">消息类型</td>
			  <td align="center" class="table_title">发送人</td>
			  <td align="center" class="table_title">内容</td>
			  <td width="60" align="center" class="table_title">是否已读</td>
			  <td width="140" align="center" class="table_title">创建时间</td>
			  <td width="80" align="center" class="table_title tit0">&nbsp;操作</td>
			</tr>
	    	<#list messagePage.result as res>
				<#assign flag=1>
			    <#if res_index%2=1>
			    <tr class="tr_cur1">
			    <#else>
			    <tr class="tr_cur2">
			    </#if>
			     <td width="2%" align="left" ><input type="checkbox" name="messageIds" value="${res.id?if_exists}"></input></td>
			      <td align="left">${res_index+1}</td>
			      <td align="left">${res.accountName!("&nbsp;")}</td>
			      <td align="left">${res.title?default("")}</td>
			      <td align="left">
					<#if res.type=0>
					普通消息
					<#else>
					系统通知
					</#if>
					</td>
			      <td align="left">${res.senderName!}</td>
			        <td align="left"><#if res.content?length lt 11>${res.content!""}
				      <#else>
				      ${res.content[0..10]}..
				      </#if>
				      </td>
			      <td align="left">
					<#if res.readFlag==false>
					未读
					<#else>
					已读
					</#if>
					</td>
				<td align="center">
				${res.createTime?string("yyyy-MM-dd HH:mm:ss")}
				</td>
			      <td align="left">
			       	&nbsp;<a href="${base}/manage/account/message!info.htm?id=${res.id}">详情</a>
			      	&nbsp;<a href="javascript:common.doPost('${base}/manage/account/message!del.htm?id=${res.id}','确定删除吗?');">删除</a>
			      </td>
			    </tr>
			</#list>
			<#if flag=0>
			    <tr>
			      <td align="center" colspan="10" class="table_title">无相关记录</td>
			    </tr>
	    	</#if>
	  	</table>
	  	</form>
		</div>
	<@paginate pageCount=messagePage.totalPage currentPage=messagePage.thisPageNumber url=pageUrl>
	</@paginate>
	</div>
<div class="tool_2">
    <div class="tool_2_L"></div>
    <div class="tool_2_R"></div>
</div>
</div>
</div>
</body>
</html>