<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<script language="JavaScript">
    $(document).ready(function(){
   var obj='${dataFilter.filterType}';
			if(obj==2){
		    	$("#filterValue").rules("add",{ip:true});
		    }else if(obj==0){
		    	$("#filterValue").rules("add",{mobile:true});
		    }else if(obj==1){
		    	$("#filterValue").rules("add",{idcardno:true});
		    }
     	var fm=new GeneralForm("validateForm","修改黑名单信息");
     	
    });
    function fun(){
     		var obj='${dataFilter.filterType}';
			if(obj==2){
		    	$("#filterValue").rules("add",{ip:true});
		    }else if(obj==0){
		    	$("#filterValue").rules("add",{mobile:true});
		    }else if(obj==1){
		    	$("#filterValue").rules("add",{idcardno:true});
		    }
     	};
</script>
</head>
<body>
<div class="rightArea_1">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>修改黑名单</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1">&nbsp;<font size="2" color="#FF0033"></font></div>
    <form action="${base}/manage/sys/dataFilter!update.htm" id="validateForm" method="post" >
    <input name="id" type="hidden" value="${dataFilter.id}" />
    <div class="tableNav_2">
       <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
        <tr>
      <td align="right" class="table_l_tit" width="150">过滤类型：</td>
          <td>
          <input type="hidden" name="dataFilter.filterType" value="${dataFilter.filterType}" />
          	<#if dataFilter.filterType=0>手机
          	<#elseif dataFilter.filterType=1>身份证
          	<#elseif dataFilter.filterType=2>IP</#if>
          </td>
        </tr>
        <tr>
        <td align="right" class="table_l_tit" width="150">过滤对象：</td>
          <td>
	          <input name="dataFilter.filterValue" id="filterValue" type="text" class="inputtext w220" id="dataFilter.filterValue" size="32" value='${dataFilter.filterValue}' validate="" />
          </td>
        </tr>
		 <tr>
		 <td align="right" class="table_l_tit" width="150">备 注：</td>
          	<td>
          		<textarea name="dataFilter.remark" id="dataFilter.remark" cols="45" rows="5" class="textarea_1" id="textarea" validate="{maxlength:500}">${dataFilter.remark}</textarea>
          	</td>
        </tr>
      </table>
    </div>
    <div class="tool_2">
        <div class="tool_2_L">
        <input type="submit" class="btn02" name="button" id="button1" value="修改" />
        <input type="button" class="btn02" name="button" id="button" onclick="window.history.back();" value="返 回" />
        </div>
        <div class="tool_2_R"></div>
    </div>
    </form>
    </div>
</div>
</div>
</body>
</html>