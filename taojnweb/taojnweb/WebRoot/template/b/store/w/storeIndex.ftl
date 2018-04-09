<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>江南钱包对账</title>
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/goods.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/reset-min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${base}/style/w/css/common.css" media="screen" />
<#include "/template/w/common/utilsPage.ftl">
</head>
	<body>
	<#assign showTag=4>
		<#include "template/w/top.ftl">
		<div class="contain">
			<#include "template/w/left.ftl">
			<div class="right">

				<div class="hotel">

					<div class="hotel_con">

						<div class="jdcx_end">
						<form method="post" action="${base}/goods/index.htm">
							<p>
								<#--span class="cxtj">商品分类：</span><span class="cxtj_r">
								<select name="goodsStoreInfo.storeType">
												<option value="">全部</option>
		
											</select>
								</span-->
								<span class="cxtj">指定商品：</span><span class="cxtj_r">
								<input name="goodsInfo.goodsName" type="text" value="${(goodsInfo.goodsName)!("请输入关键字")}" onFocus="this.value=''" class="txxt" />
								</span>
								<input name="" type="submit" class="search_btn" />
							</p>
							</form>
						</div>
						<!--jdcx_end_goods-->
					<#if resultPage.result?exists && resultPage.result.size() gt 0>
        					<#list resultPage.result as res>
						<div class="goods_list">
							<div class="box_con">
								<div class="good_box">
									<div class="goods_pic">
										<a href="${base}/goods/index!info.htm?id=${res.id}"><img src="${res.imageUrl}" width="300"
												height="240" />
										</a>
									</div>
									<h3>
										${res.goodsName!}
									</h3>
									<p>
										<a href="${base}/goods/index!info.htm?id=${res.id}" class="buy_common">在线预订</a>
										<span class="current_price">¥${(res.price)?c}</span>
										<span class="bypast">门市价：¥${(res.showPrice)?c}</span>
									</p>


								</div>
								<!--good_box-->
							</div>
							<!--box_con-->

						</div>
						<!--goods_list-->
						</#list>
						 <div class="paggings ">
							<@paginate pageCount=resultPage.totalPage currentPage=resultPage.thisPageNumber url=pageUrl></@paginate>
						</div>
						<#else>
				        	<center>暂无相关商品信息</center>
				        </#if>
						<!--paggings-->
					</div>

				</div>
			</div>
		</div>
	<#include "template/w/foot.ftl">

	</body>
	<!-- InstanceEnd -->
</html>
	