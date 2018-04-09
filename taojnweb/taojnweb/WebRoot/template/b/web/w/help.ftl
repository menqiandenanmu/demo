<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<#include "/template/w/common/head.ftl">
<title>江南钱包对账</title>
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/help.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/reset-min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/common.css" media="screen" />
<script type="text/javascript" src="${base}/script/w/spyd.js"></script>
</head>
<body>
<#assign showTag=8>
<#assign helpTag=1>
<#assign helpTop=1>
		<#include "template/w/top.ftl">
		<!-- InstanceEndEditable -->
		<div class="contain">
			<div class="helpCon">
			<#include "template/b/web/w/helpTop.ftl">
					<div class="tic_con">
						<div class="tic_conNav">
							<div class="helpCenterdis" id="my3Tab0_Content0">
								<ul class="menu_help" id="my3Tab0S">
									<li <#if helpTag?exists && helpTag=1>class="active1"<#else>class="normal1"</#if>  onclick="window.location.href='${base}/help/index.htm'">
										新手上路
									</li>
									<li <#if helpTag?exists && helpTag=3>class="active1"<#else>class="normal1"</#if> onclick="window.location.href='${base}/help/legalNotices.htm'">
										法律声明
									</li>
									<li <#if helpTag?exists && helpTag=4>class="active1"<#else>class="normal1"</#if> onclick="window.location.href='${base}/help/service.htm'">
										服务协议
									</li>
								</ul>
								<!--menu_help-->
							<!--menu_helpej-->
							<div class="freshman" id="my3Tab0S_Content0">
								${content!}
							</div>
						</div>
						<!--question-->
					</div>
					<!--tic_conNav-->
				</div>
				<!--tic_con-->
			</div>
			<!-- InstanceEndEditable -->
		</div>
		<#include "template/w/foot.ftl">

	</body>
</html>
