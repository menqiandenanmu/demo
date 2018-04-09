<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <title></title>
 <#include "/template/m/common/head.ftl">
 <link href="${base}/style/m/css/reset.css" rel="stylesheet" type="text/css">
<link href="${base}/style/m/css/common.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/page.css"/>
<!--[if lte IE 8]>
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/ie8.css" />
<![endif]-->
<script src="${base}/scripts/require.js" data-main="scripts/main"></script>
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
<body>
 <div class="page-main">
    <div class="page-item page-title">权限设置</div>
    <div class="page-item form-panel">
	<form action="${base}/manage/sys/role!infoUpdate.htm" id="validateForm" method="post">
	<input name="id" type="hidden" value="${role.id}"/>
	<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul><li>（${role.roleName?default("")}）角色权限设置</li></ul>
    </div>
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
	<div class="form-handle">
                            <input type="submit" value="提交" class="btn btn-small bg-blue"/>
        </div>
   </div>
 </div>
</form>
</div>
</div>
</div>
</body>
</html>