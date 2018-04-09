<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <title></title>
 <#include "/template/m/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<script language="JavaScript">
	    $(document).ready(function(){
	     	var fm=new GeneralForm("validateForm","角色权限设置");
		    $("input[tagle=subFids]").click(function(){
		     	var me=$(this);
		     	var val=me.attr("checked");
		     	if(val!=undefined && (val=="checked" || val==true ) ){
		     		$("#"+me.attr("parentId")).attr("checked","checked"); 
				}
		  	});
		  	
		  	$("input[tagle=parentFids]").click(function(){
		     	var me=$(this);
		     	var val=me.attr("checked");
		     	if(val!=undefined && (val=="checked" || val==true ) ){
		     	$("input[parentId="+$(this).attr("id")+"]").each(function(){$(this).attr("checked","checked")});
		     	}else{
		     		$("input[parentId="+$(this).attr("id")+"]").each(function(){$(this).removeAttr("checked")});
				}
		  	});
		  	
	    });
	</script>

</head>
<form action="${base}/manage/sys/roleAdmin!infoUpdate.htm" id="validateForm" method="post">
<input name="id" type="hidden" value="${role.id}"/>
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul><li>角色权限设置</li></ul>
    </div>
    <div class="r_content">
    <div class="tool_search">
		<table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
			<tr>
				<td align="left">角色名称：<font size="2" color="#F086B6">${role.roleName?default("")}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
		</table>
	</div>
	<div>
		<ul id="root" class="checktree">
			<#list funcs as fuLis>
				<li class="minus-last"  id="parentfu"><input type=checkbox name="fids" tagle="parentFids" id="m${fuLis.id}" value="${fuLis.id}" <#if fuLis.checkFlage?exists && fuLis.checkFlage==true>checked</#if> />${fuLis.funName}
					<ul>
						<#list fuLis.childs as subLis>
							<li  id="childsfu">&nbsp;&nbsp;&nbsp;&nbsp;
								<input type=checkbox name="fids" tagle="subFids"  class="selects" parentId="m${fuLis.id}" value="${subLis.id}" <#if subLis.checkFlage?exists && subLis.checkFlage==true>checked</#if> />${subLis.funName}</li>
						</#list>
					</ul>
				</li>
			</#list>
		</ul>
	</div>
	
	<br>
	<div class="tool_2">
        <div class="tool_2_L">
        <input type="submit" class="btn02" name="button" id="button" value="保存"/>
        <input type="button" class="btn02" name="button" id="button" onclick="window.history.back();" value="返 回" />
        </div>
        <div class="tool_2_R"></div>
    </div>
   </div>
 </div>
</form>

</body>
</html>