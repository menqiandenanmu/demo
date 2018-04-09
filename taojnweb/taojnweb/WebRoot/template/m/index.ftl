<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>乌镇电子商务管理系统</title>
	<meta name="robots" content="index, follow" />
	<meta name="googlebot" content="index, follow" />
	<meta name="Authors" content="sendinfo.com.cn, 深大智能"/>
	<META HTTP-EQUIV="pragma" CONTENT="no-cache"/>
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache,must-revalidate"/>
	<META HTTP-EQUIV="expires" CONTENT="0"/>
	<#include "/template/m/header.ftl">
	<script type="text/javascript">
		//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
		function msgShow(title, msgString, msgType) {
			$.messager.alert(title, msgString, msgType);
		}
		
		
		//设置登录窗口
		function openPwd() {
		    $('#w').window({
		        title: '修改密码',
		        width: 300,
		        modal: true,
		        shadow: true,
		        closed: true,
		        height: 220,
		        resizable:false
		    });
		}
		
		//关闭登录窗口
		function closePwd() {
		    $('#w').window('close');
		}
		
		
		
		jQuery(document).ready(function(){
			
			// 修改密码
		    openPwd();
		    $('#editpass').click(function() {
		        $('#w').window('open');
		    });
		    $('#btnEp').click(function() {
		        serverLogin();
		    })
			$('#btnCancel').click(function(){closePwd();})
		    $('#loginOut').click(function() {
		        $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
		            if (r) {
		            	 $.post('${base}/manage/sys/operator!logout.htm', function() {
		            		 window.location.href="${base}/manage/index.htm";
		     		    })
		            }
		        });
		    })
		});
		
		//修改密码
		function serverLogin() {
		    var $new1 = $('#new1');
		    var $new2 = $('#new2');
		    var $old = $('#old');
		    if ($new1.val() == '') {
		        msgShow('系统提示', '请输入密码！', 'warning');
		        return false;
		    }
		    if ($new2.val() == '') {
		        msgShow('系统提示', '请在一次输入密码！', 'warning');
		        return false;
		    }
		    if ($new1.val() != $new2.val()) {
		        msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
		        return false;
		    }
		    
		    var url = '${base}/manage/sys/operator!updatePass.htm';
			var params = {
				oldPassword : $old.val(),
				newPassword : $new1.val()
			};
			$.ajax({
					type: "post",//可选get
					url: url,//这里是接收数据的action
					data: params,//传给后台的数据，多个参数用逗号分隔
					dataType:"json",//服务器返回的数据类型 可选XML ,Json jsonp script html text等
					success:tishi,
					error:function(){
						alert("检测失败，请检查网络！");
					}
				});
			/*
		    $.post(url, params, function(res) {
		       	closePwd();
		       	if (res.success) {
					msgShow('系统提示', res.message, 'warning');
				} else {
					msgShow('系统提示', res.message, 'warning');
				}
		     	
		    })
		    */
		}
		function tishi(data){
			$('#new1').val('');
		    $('#new2').val('');
		    $('#old').val('');
			closePwd();
			msgShow('系统提示', data.message, 'warning');
		}
	</script>
</head>
<body class="easyui-layout">
<!-- Top -->
<div data-options="region:'north',border:false" id="topclass"  >
	<#include "/template/m/index-header.ftl">
</div>
<!-- Left -->
<div id="leftmenu" data-options="region:'west',split:true,border:true">
	<#include "/template/m/index-left-accordion.ftl">
</div>
<!-- Center -->
<div data-options="region:'center',border:true" style="background: #ffffff;">
	<div id="workarea" data-options="border:false" style="overflow: hidden;">
		<#include "/template/m/index-welcome.ftl">
	</div>
	<div id="tabsMenu" style="width: 120px; display: none;">
		<div id="closeCurr">关闭</div>
		<div id="closeOther">关闭其他</div>
		<div id="closeAll">关闭所有</div>
		<div class="menu-sep"></div>
		<div id="refresh" data-options="iconCls:'icon-reload'">重新加载</div>
	</div>
</div>
</body>
</html>