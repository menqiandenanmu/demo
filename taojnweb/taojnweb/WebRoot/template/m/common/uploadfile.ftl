<link rel="stylesheet" type="text/css" href="${base}/script/uploadify/uploadify.css"/>
<script src="${base}/script/uploadify/jquery.uploadify.v2.1.4.js" type="text/javascript"></script>
<script src="${base}/script/uploadify/swfobject.js" type="text/javascript"></script>
<script src="${base}/script/uploadify/uploadExtend.js" type="text/javascript"></script>
<#macro upload name,multi,url>
	<#assign id=name?replace(".","")>
	<div id="${id}fileList">
	<#nested/>
	</div>
	<div id="${id}fileQueue" ></div>
	<input type="file" id="${id}file" />
	<script type="text/javascript">
		var ${id}upload=new Upload('${id}file','${id}fileQueue','${id}fileList','${name}',${multi},'${url}');
	</script>
</#macro>
