<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>乌镇电子商务管理系统</title>
<meta name="Keywords" content="网上购票" />
<meta name="Description" content="乌镇电子商务管理系统" />
<meta name="robots" content="index, follow" />
<meta name="googlebot" content="index, follow" />
<meta name="Authors" content="sendinfo.com.cn, 深大智能">
<meta name="content" baseUrl="${base}" backUrl="${backUrl}"/>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache,must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="0">
<!-- 新增内容 -->
<link type="text/css" rel="stylesheet" href="${base}/css/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet" href="${base}/css/themes/default/main.css" />
<link type="text/css" rel="stylesheet" href="${base}/css/icons/icon.css" />
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
<script type="text/javascript" src="${base}/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${base}/easyui/jquery.hotkeys.js"></script>
<script type="text/javascript" src="${base}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${base}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${base}/js/common/basemain.js"></script>
