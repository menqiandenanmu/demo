<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "/template/w/common/head.ftl">
<title>江南钱包对账购票</title>
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/reset-min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/common.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/ticket.css" media="screen" />
<script language="JavaScript" type="text/javascript" src="${base}/script/datepicker/WdatePicker.js"></script>
<script language="JavaScript">
	$(document).ready(function(){
		$("#validateForm").validate({
			ignore : "",
			submitHandler : function(form) {
			var amountsize=$("[name='amounts']").size();
			var temp=0;
			for(var i=0;i<amountsize;i++){
			var num=parseInt($("#amount"+i).val());
			temp=temp+num;
			 };
			if(temp>0){
				form.submit();
				}
				else{
				alert("请选择数量");
				}
			}
		})}
	);
    function refresh()
	{
		var goodsId=$("#goodsId").val();
		window.location.href="${base}/goods/buyGoods.htm?goodsId="+goodsId;
	}
</script>
</head>

<body>
<#assign showTag=4> 
    <#include "/template/w/top.ftl">
   <div class="contain">
    <#include "/template/w/left.ftl">
   <div class="right">
      <div class=" ticket">
      <div class="ticketbuyTop"></div><!--ticketbuyTop-->
          <div class="ticketCon">
          
          <form action="${base}/goods/saveOrder.htm" method="post" id="validateForm">
           <@ww.token />
          	<input type="hidden" name="id" id="goodsId" value="${goodsStoreInfo.id}" />
            <div class="ticketbuyTitle">${(goodsStoreInfo.storeName)!"信息异常！"}</div><!--ticketbuyTitle-->
              <div class="playtime">
              </div><!--playtime-->
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class=" infotable">
				  <tr class="first">
				    <td width="40%" height="34" align="center">商品名</td>
				    <td width="20%" height="34" align="center">门市价</td>
				    <td width="20%" height="34" align="center">网购价</td>
				    <td height="34" align="center">数量</td>
				  </tr>
				 <#if goodsinfos?exists && goodsinfos.size() !=0>
				  <#list goodsinfos as goodsInfo>
				  <input type="hidden" name="goodsIds" value="${goodsInfo.id}">
				  <tr>
				    <td width="40%" height="30" align="center">${(goodsInfo.goodsName)!}</td>
				    <td width="20%" height="30" align="center" class="msj">￥${(goodsInfo.showPrice?c)!}</td>
				    <td width="20%" height="30" align="center" class="wgj">￥${(goodsInfo.price?c)!}</td>
				    <td height="30" align="center">
					    <select name="amounts" id="amount${goodsInfo_index}">
					    	 <option value="0">0</option>
						     <#list goodsInfo.minAmount..goodsInfo.maxAmount as x>
				      		  	<option value="${x}">${x}</option>
				      		 </#list>
					    </select>
				    </td>
				  </tr>
				 </#list>
				 <#else>
				  <tr>
				  	<td  height="30" align="center" colspan="4">暂无商品可预订</td>
                  </tr>	
                </#if>
				</table>
				<div class="inputgroup mar_top">
				  <div class="fiegroinfo"><span>联系人</span><input name="linkName" id="linkName" type="text" class="text" validate="{required:true,chcharacter:true}"/></div><!--fiegroinfo-->
				  <div class="fiegroinfo"><span>手机号</span><input name="mobile" id="mobile" type="text" class="text" validate="{required:true,mobile:true}"/></div><!--fiegroinfo-->
				   <div class="fiegroinfo"><span>确认手机号</span><input autocomplete="off" onpaste="return false" name="mobile1" type="text" class="text" id="mobile1" validate="{required:true,minlength:11,equalTo:'#mobile'}"/></div><!--fiegroinfo-->
   				  <div class="fiegroinfo"><span>身份证号码</span><input type="text" class="text" name="idCard" id="idcard" validate="{required:true,idcardno:true,minlength:18}"/></div>
				  <div class="fiegroinfo"><span>确认身份证</span><input autocomplete="off" onpaste="return false" type="text" class="text" name="tempIdCard" id="tempIdCard" validate="{required:true,equalTo:'#idcard'}"/></div>
			  	  <div class="fiegroinfo"><span>备注</span><input type="text" class="text" name="order.remark" id="order.remark" /></div>
				   <div class="fiegroinfo"><span>支付方式</span><input name="" type="radio" value="" checked="checked"/>
				     <img src="${base}/style/w/images/help/zfb.jpg" width="70" height="25" align="absmiddle" /></div><!--fiegroinfo-->
				   <div class="warmtips">温馨提示：支付完成后用户会收到一条短信，凭短信订单号到窗口换票。</div><!--warmtips-->
				   <div class="confirm">
				   <input name="" type="submit" value="  "  /></div><!--confirm-->
				</div><!--inputgroup-->

			</form>

        </div><!--ticketCon-->
      </div><!--ticket-->
      
   </div><!--right-->
</div><!--contain-->

  <#include "/template/w/foot.ftl">
</body>
</html>
