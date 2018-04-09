<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/template/m/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/left.css" media="screen" />
<script src="${base}/script/jquery/jquery.js" type="text/javascript"></script>
<meta name="Authors" content="sendinfo">
<script language="JavaScript">
	function exchange(id){
		var manu=$("#manu"+id);
		var submanu=$("#submanu"+id);
		if(manu.attr("openFlag")=="1"){
			manu.attr("openFlag","0");
			manu.removeClass("menu_1_off");
			manu.addClass("menu_1_on");
			submanu.hide();
		}else{
			manu.attr("openFlag","1");
			manu.removeClass("menu_1_on");
			manu.addClass("menu_1_off");
			submanu.show();
		}
	}
</script>
</head>
<body class="webBg">
	<div class="leftArea">
		<#if funcs?exists> 
			<#list funcs as funIndex>
				<div class="l_menu_1">
			    	<ul>
			        <li class="menu_1_on"  id="manu${funIndex.id}" openFlag="0">
			        	<#if funIndex.funType==0>
			        	<a href="javascript:void(0);" onclick="exchange(${funIndex.id});">${funIndex.funName}</a>
			        	<#else>
			        	<a href="${base}/${funIndex.funUrl}" target="panel" onclick="exchange(${funIndex.id});">${funIndex.funName}</a>
			        	</#if>
			        </li>
			        </ul>
			    </div>
				<div class="l_menu_2" id="submanu${funIndex.id}" style="display: none;">
					<ul>
						<#list funIndex.childs as funItem>
								<li class="menu_2_off" ><a id="menu2${funItem.id}" href="${funItem.funUrl}" target="panel" onclick="menu2Change(${funItem.id});">${funItem.funName}</a></li>
						</#list>
					</ul>
				</div>
			</#list>
		</#if>
	</div>
</body>
</html>
