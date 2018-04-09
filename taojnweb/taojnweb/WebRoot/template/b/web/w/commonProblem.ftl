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
<#assign helpTag=2>
<#assign helpTop=2>
		<#include "template/w/top.ftl">
		<div class="contain">
		<div class="helpCon">
				<#include "template/b/web/w/helpTop.ftl">
								<div class="tic_con">
			                          <div class="tic_conNav">
											 <div class="question" id="my3Tab0_Content1" style="display:block">
												 <div class="freshman">
													${content!}                                        
						                        </div><!--freshman-->
					                        </div><!--question-->
									</div><!--tic_conNav-->
								</div><!--tic_con-->
				</div><!-- helpCon -->
		</div><!-- contain -->
		<#include "template/w/foot.ftl">

	</body>
</html>
