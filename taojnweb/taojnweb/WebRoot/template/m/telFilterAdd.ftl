<#include "/WEB-INF/template/manage/common/utils.ftl"/>
<#include "/WEB-INF/template/manage/share/head.ftl"/>
<@head>
	<link href="${base}/${theme}/manage/css/right.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript">
	   $(document).ready(function(){
     	var fm=new GeneralForm("validateForm","添加");
    });
	</script>
</@head>
<body>
<div class="rightArea">
<div class="r_ContentNav">
	<div class="r_tit1">
    	<ul>
        <li>手机黑名单新增：</li>
        </ul>
    </div>
    <div class="r_content">
    <div class="tool_1">&nbsp;<font size="2" color="#FF0033">&nbsp;</font></div>
    <form action="${base}/manage/telfilter!telFilterSave.htm" id="validateForm" method="post">
    <div class="tableNav_2">
      <table width="100%" border="0" cellpadding="3" cellspacing="0" class="table_l_nav">
      	<tr>
          <td align="right" class="table_l_tit">手机号码：</td>
          <td class="table_l_td">
	          <input name="filterVo.filterObj" type="text" class="input_1_light" id="roleName" size="30" value=""  validate="{required:true,mobile:true}"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="table_l_tit">备 注：</td>
          <td class="table_l_td"><textarea name="filterVo.remark" cols="45" rows="5" class="textarea_1" maxlength="512" id="textarea" ></textarea></td>
        </tr>
      </table>
    </div>
    <div class="tool_2">
        <div class="tool_2_L">
        <input type="submit" class="btn02" name="button" id="button1" value="保存"/>
        <input type="button" class="btn02" name="button" id="button" onclick="location.href='${base}/manage/telfilter.htm'" value="返 回" />
        </div>
        <div class="tool_2_R"></div>
    </div>
    </form>
    </div>
</div>
</div>
</body>
</html>
