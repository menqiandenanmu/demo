<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/template/m/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<script language="JavaScript">
    $(document).ready(function(){
     	$("#validateForm").validate({submitHandler:function(form){
            if(confirm("确认修改?")){
            	var options={};
            	$("#distRoleL").find("option").each(function(){$(this).attr("selected","selected")});
            	options.success=function(obj){var message=eval(obj);alert(message.message[0]); if(message.flag=="success"){window.history.back();}};
            	options.dataType="text";
            	$(form).ajaxSubmit(options);
            }
            }});
    });
    function moveitem(sourId,distId){
    	$("#"+sourId+" option:selected").each(
    		function(){
    			var obj=$(this);
    			var temp=$("<option value='"+obj.val()+"'>"+obj.text()+"</option>");
    			var dist=$("#"+distId);
    			temp.appendTo("#"+distId);
    			//dist.appendChild(temp);
    			obj.remove();
    		}
    	);
    }
    function moveitemAll(sourId,distId){
        $("#"+sourId+" option").each(
    		function(){
    			var obj=$(this);
    			var temp=$("<option value='"+obj.val()+"'>"+obj.text()+"</option>");
    			temp.appendTo("#"+distId);
    			obj.remove();
    		}
    	);
    }
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>模块组管理</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1">&nbsp;<font size="2" color="#FF0033"></font></div>
    <form action="${base}/manage/sys/sysFunctionGroup!update.htm" id="validateForm" method="post" >
    <input name="id" type="hidden" value="${sysFunctions.id}" />
    <div class="tableNav_2">
      <table width="500px" border="0" cellpadding="3" cellspacing="0" class="table_l_nav">
        <tr>
        	<td width="215px">
        		<select id="roleL" size="15" multiple="multiple" ondblclick="moveitem('nList','distRoleL');" style="width:215px">
        		<#list noSelectList as index>
        			<option value="${index.id}">${index.funName}</option>
        		</#list>
        		</select>
        	</td>
        	<td>
        	<input type="button" onclick="moveitem('roleL','distRoleL');" value=">" style="width:50px"/>
        	<input type="button" onclick="moveitem('distRoleL','roleL');" value="<" style="width:50px"/>
        	<input type="button" onclick="moveitemAll('roleL','distRoleL');" value=">>" style="width:50px"/>
        	<input type="button" onclick="moveitemAll('distRoleL','roleL');" value="<<" style="width:50px"/>
        	
        	
        	</td>
        	<td width="215px">
        		<select id="distRoleL" name="rids" size="15" multiple="multiple" ondblclick="moveitem('distRoleL','roleL');" style="width:215px">
        		<#list ownList as index>
        			<option value="${index.id}">${index.funName}</option>
        		</#list>
        		</select>
        	</td>
        <tr>
      </table>
    </div>
    <div class="tool_2">
        <div class="tool_2_L">
        <input type="submit" class="btn02" name="button" id="button1" value="保存" />
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