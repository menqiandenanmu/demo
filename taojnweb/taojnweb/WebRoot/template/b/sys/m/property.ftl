<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<script language="JavaScript">
	function refresh(){
		$.ajax({
		  url: "${base}/manage/sys/property.htm",
		  dataType:"json",
		  success: function(obj){
		    	alert(obj.message[0]);
		  }
		});
	}
</script>
</head>

<body>

<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>参数列表</li>
        </ul>
    </div>
    <div class="r_content">
  	  	
  <div class="tableNav">
	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablelistNav">
	    <tr>
	      <td width="50" align="center" class="table_title">序号</td>
	      <td width="200" align="center" class="table_title">编码</td>
	      <td align="center" class="table_title">参数值</td>
	      <td width="50" align="center" class="table_title tit0">&nbsp;操作</td>
	    </tr>
	    <#list properties?keys as a>
	    <#assign flag=1>
	    <#if a_index%2=1>
	    <tr class="tr_cur1">
	    <#else>
	    <tr class="tr_cur2">
	    </#if>
	      <td align="left">${a_index+1}</td>
	      <td align="left">${a}</td>
	      <td align="left">${properties[a]}</td>
	      <td align="left">
	      &nbsp;<a href="${base}/manage/sys/property!edit.htm?key=${a}">编辑</a>
	    </tr>
	    </#list>
	  </table>
	</div>
    </div>
    
    <div class="tool_2">
        <div class="tool_2_L">系统参数设置会景响系统的运行,请谨慎操作.设置完后请按!
        <button  title="刷新" onclick="javascript:common.doPost('${base}/manage/sys/param!refresh.htm','确定要刷新参数吗?');">刷新</button>使系统参数生效!</div>       
        </div>
    </div>
    </div>
    
</div>
</div>


</body>
</html>
