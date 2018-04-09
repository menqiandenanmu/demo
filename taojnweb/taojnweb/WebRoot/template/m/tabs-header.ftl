<link type="text/css" rel="stylesheet" href="${base}/css/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet" href="${base}/css/themes/common.css" />
<link type="text/css" rel="stylesheet" href="${base}/css/icons/icon.css" />
<script type="text/javascript" src="${base}/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${base}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${base}/easyui/jquery.easyui.datagrid-detailview.js"></script>
<script type="text/javascript" src="${base}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${base}/js/common/basetabs.js"></script>
<script type="text/javascript" src="${base}/js/common/jquery.easyui.extend.js"></script>

<script src="${base}/script/jquery/jquery.form.js"></script>
<script src="${base}/script/jquery/jquery.autocomplete.js"></script>
<script src="${base}/script/jquery/jquery.validator.js"></script>
<script src="${base}/script/jquery/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/script/common/formExtends.js" ></script>
<script src="${base}/script/common/applicationContent.js" type="text/javascript"></script>
<script src="${base}/script/common/applicationSetup.js" type="text/javascript"></script>
<script src="${base}/script/jquery/jquery.metadata.js"></script>




<#--导入富文本编辑器ueditor-->
<#--include "/template/m/common/UEdit.ftl"-->
<#--导入上传图片控件-->
<#include "/template/m/common/uploadfile.ftl">

<script type="text/javascript">
	var context = {
		path : '${basePath}',
		suffix : '.htm',
		url : function(path, params) {
			var url = context.path + path + context.suffix;
			if (params) {
				url += "?" + params;
			}
			return url;
		}
	}
</script>