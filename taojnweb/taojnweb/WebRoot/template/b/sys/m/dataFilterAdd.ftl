<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new GeneralForm("validateForm","添加黑名单信息");
     	fun();
    });
    function fun(){
     		$("#filterValue").rules("add",{ip:true});
     	};
    function changeFilter(filterType){
    	var obj=$("#filterValue");
    	obj.rules("remove");
		if(filterType==2){
	    	obj.rules("add",{ip:true});
	    }else if(filterType==0){
	    	obj.rules("add",{mobile:true});
	    }else if(filterType==1){
	    	obj.rules("add",{idcardno:true});
	    }
    };
</script>
</head>
<body>
<div class="rightArea_1">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>添加黑名单</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1">&nbsp;<font size="2" color="#FF0033"></font></div>
    <form action="${base}/manage/sys/dataFilter!save.htm" id="validateForm" method="post" >
    <div class="navcon">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
        <tr>
          <td align="right" class="table_l_tit" width="150">过滤类型：</td>
          <td>
	        <select name="dataFilter.filterType" class="select w80" onchange="changeFilter(this.options[this.options.selectedIndex].value);" id="filterType">
				<option value="0" selected>手机</option>
				<option value="1" selected>身份证</option>
				<option value="2" selected>IP</option>
          	</select>
          </td>
        </tr>
        <tr>
         <td align="right" class="table_l_tit" width="150">过滤对象：</td>
          <td >
	          <input name="dataFilter.filterValue" id="filterValue" type="text" class="inputtext w220" size="32" value="" validate="" />
          </td>
        </tr>
        <tr>
         <td align="right" class="table_l_tit" width="150">备 注：</td>
          <td ><textarea name="dataFilter.remark" cols="45" rows="5" class="textarea_1" id="textarea" validate="{maxlength:500}"></textarea></td>
        </tr>
      </table>
    </div>
 	<div class="tool_2">
        <div class="tool_2_L">
        <input type="submit" class="btn02" name="button" id="button1" value="添加" />
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