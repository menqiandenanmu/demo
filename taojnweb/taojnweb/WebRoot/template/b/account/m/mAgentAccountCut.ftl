<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/template/a/common/head.ftl">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/right.css" media="screen" />
<link href="${base}/script/datepicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
<script language="JavaScript">
    $(document).ready(function(){
     	var fm=new GeneralForm("validateForm","用户扣款");
    });
</script>
</head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>用户扣款</li>
        </ul>
    </div>
    <form action="${base}/manage/account/mAgentAccount!doSaveCut.htm" id="validateForm" method="get" >
    <div class="r_content">
    <div class="tool_1">用户扣款</div>
    <input type="hidden" value="${tradeAccount.id}" name="id"/>
    <div class="tableNav_2">
      <table width="100%" border="1" cellpadding="3" cellspacing="0" class="table_l_nav">
        <tr>
          <td align="right" class="table_l_tit" width="100">用户名称：</td>
          <td class="table_l_td" align="left" colspan="7">
			${tradeAccount.accountName}
          </td>
        </tr>
          <tr>
          <td align="right" class="table_l_tit" width="100">余额：</td>
          <td class="table_l_td" align="left">
			${tradeAccount.leftValue}
          </td>
          <td align="right" class="table_l_tit" width="100">余额警戒线：</td>
          <td class="table_l_td" align="left">
	        ${tradeAccount.creditLine}
          </td>
           <td align="right" class="table_l_tit" width="100">当前冻结：</td>
          <td class="table_l_td" align="left">
	        ${tradeAccount.frozenValue}
          </td>
           <td align="right" class="table_l_tit" width="100">当前签单：</td>
          <td class="table_l_td" align="left">
	        ${tradeAccount.signAmount!}
          </td>
        </tr>
      </table>
    </div>
	</div>
	
    <div class="r_content">
    	<div class="tool_1">扣款</div>
	    <div class="tableNav_2">
	      <table class="tablelistNav" border="0" cellpadding="0" cellspacing="0" >
    		<tr>
    			<td align="center" >&nbsp;&nbsp;本次扣款：</td>
    			<td align="left"><input type="text" name="opValue" id="opValue" style="text-align: right" validate="{required:true,number:true,min:1}"/></td>
    		</tr>
    		<tr>
    			<td align="center" colspan="2">&nbsp;</td>
    		</tr>
    		<tr>
    			<td align="center" colspan="2">&nbsp;</td>
    		</tr>
    		<tr>
    			<td align="center" >备注</td>
    			<td align="left"><textArea name="remark" id="remark" rows="5" cols="30" validate="{required:false,maxlength:200}"></textArea></td>
    		</tr>
    	</table>
		</div>
    <div class="tool_2">
        <div class="tool_2_L">
        <input type="submit" class="btn02" value="确 定" />
        <input type="button" class="btn02" onclick="javascript:history.back();" value="返 回" />
        </div>
        <div class="tool_2_R"></div>
    </div>
	</div>
    </form>
    </div>
</div>
</div>
</body>
</html>