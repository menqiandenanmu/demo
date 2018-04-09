<!DOCTYPE html>
<html>

<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if (typeof WeixinJSBridge == "undefined"){
		    if( document.addEventListener ){
		        document.addEventListener('WeixinJSBridgeReady', jsApiCall, false);
		    }else if (document.attachEvent){
		        document.attachEvent('WeixinJSBridgeReady', jsApiCall); 
		        document.attachEvent('onWeixinJSBridgeReady', jsApiCall);
		    }
		}else{
		    jsApiCall();
		}
	});	
	
	function jsApiCall(){
		WeixinJSBridge.invoke('getBrandWCPayRequest',{
			"appId":"${wxJSApiPayParam.appId}",
			"timeStamp":"${wxJSApiPayParam.timeStamp}",
			"nonceStr":"${wxJSApiPayParam.nonceStr}",
			"package":"${wxJSApiPayParam.packageStr}",
			"signType":"${wxJSApiPayParam.signType}",
			"paySign":"${wxJSApiPayParam.paySign}"
		},function(res){
			if(res.err_msg == "get_brand_wcpay_request:ok" ) {//-----支付成功
				// 使用以上方式判断前端返回,提示：res.err_msg 将在用户支付成功后返回ok，但并不保证它绝对可靠。
				// 跳转至订单支付结果页
				document.location.href="${base}/pay/successPay.htm?orderId=${orderId}";
			}else{//-----支付取消或支付失败
				//如果支付失败，则弹出出错信息
				for(var i in res){//
					alert(i+":"+res[i]);
				} 
				//跳转至支付失败
				document.location.href="${base}/pay/failPay.htm?orderId=${orderId}&message="+res.err_msg;
			}
		});
	}		
</script>
</head>

<body>
</body>
</html>
