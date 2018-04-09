<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/template/w/common/head.ftl">
<title>江南钱包对账商品预订</title>
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/reset-min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/common.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/ticket.css" media="screen" />
<script language="JavaScript" type="text/javascript" src="${base}/script/b/ticket/w/change.js"></script>
</head>
	<body>
	<#assign showTag=4> 
	<#include "/template/w/top.ftl">
		<div class="contain">
			<#include "/template/w/left.ftl">
			<div class="right">
				<div class=" ticket">
					<div class="ticketTitle">
						<ul id="myTab0">
							<#if goodsStoreInfo?exists>     
					        	<li>
					        	${(goodsStoreInfo.storeName)!}
					        	 </li>
				        	 </#if>     
						</ul>
					</div>
					<!--ticketTitle-->
					<#if goodsStoreInfo?exists> 
					<div class="ticketCon" style="display:block">
						<div class="qhCon">
							<div class="ticketContop" >
								<img src="${(titleImg)!}" />
								<div class="des_right">
									<h2>
										${(goodsStoreInfo.storeName)!}
									</h2>
									<DIV class="adv">
										${(goodsStoreInfo.summarize)!}
									</DIV>
									<div class="buy_info">
										<a href="${base}/goods/buyGoods.htm?id=${goodsStoreInfo.id}" class="buy_common">立即购买</a>
										
									</div>

								</div>
								<!--des-->

							</div>
							<!--ticketContop-->
							<div class="ticketConbottom">
								<div class="infogroup">
									<h2 class="postitonR">
										套餐信息
									</h2>
									<#if goodsInfos?exists && goodsInfos.size() !=0>
                   						<#list goodsInfos as goodsInfo>
									<div class="pxfl">
										<span class="px">${(goodsInfo.goodsName)!}</span><span class="price">
										<em>¥</em>${(goodsInfo.price?c)!}</span>
									</div>
									<!--pxfl-->
									<div class="infoCon">
										${(goodsInfo.remark)!}
									</div>
									<!--infoCon-->
									</#list>
									</#if>
								</div>
								<!--infogroup-->
								<div class="infogroup">
									<h2>
										地址
									</h2>
									<!--pxfl-->
									<div class="infoCon">
										<p>
											地址：${goodsStoreInfo.addr}
										</p>
										<p>
											电话：${(goodsStoreInfo.serviceTel)!("暂无电话")}
										</p>
										<p>
											公交：${(goodsStoreInfo.busInfo)!("暂无公交")}
										</p>
									</div>
									<!--infoCon-->
								</div>
								<!--infogroup-->

								<div class="infogroup">
									<h2>
										详细信息
									</h2>
									<!--pxfl-->
									<div class="infoCon">
										${(content)!}
									</div>
									<!--infoCon-->
									<!--pxfl-->
									<!--infoCon-->
								</div>
								<!--infogroup-->
							</div>
							<!--ticketConbottom-->
						</div>
						<!--童话世界区域-->
					</div>
					<!--ticketCon-->
					</#if>
				</div>
				<!--ticket-->
			</div>
			<!--right-->
		</div>
		<!--contain-->
		<#include "/template/w/foot.ftl">
	</body>
</html>