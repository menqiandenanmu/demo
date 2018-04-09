<#--页面分页-->
<#macro paginate pageCount,currentPage,url>
<span>
	<#assign maxPage=10>
	<#if currentPage gte pageCount><#assign currentPage = pageCount></#if>
	
	<#if currentPage gt 1>
		<a href="${url}&currPage=${currentPage-1}">上一页</a>
	</#if>
	<#if pageCount gt maxPage>
		<#if currentPage lte 5>
			<#list 1..currentPage+2 as l1>
				<#if l1==currentPage>
					<span class="current">${l1}</span>
				<#else>
					<a href="${url}&currPage=${l1}">${l1}</a>
				</#if>	
			</#list>
			...
			<a href="${url}&currPage=${pageCount-1}">${pageCount-1}</a>
			<a href="${url}&currPage=${pageCount}">${pageCount}</a>
		<#elseif currentPage+4 gte pageCount>
			<a href="${url}&currPage=1">1</a>
			<a href="${url}&currPage=2}">2</a>
			...
			<a href="${url}&currPage=${currentPage-2}">${currentPage-2}</a>
			<a href="${url}&currPage=${currentPage-1}">${currentPage-1}</a>
			<#if currentPage+4 lt pageCount>
				...
			</#if>
			<#if currentPage lt pageCount>
				<#list currentPage..pageCount as l2>
					<#if l2==currentPage>
						<span class="current">${l2}</span>
					<#else>
						<a href="${url}&currPage=${l2}">${l2}</a>
					</#if>	
				</#list>
			<#else>
				<span class="current">${currentPage}</span>
			</#if>	
		<#else>
			<a href="${url}&currPage=1">1</a>
			<a href="${url}&currPage=2">2</a>
			...
			<a href="${url}&currPage=${currentPage-2}">${currentPage-2}</a>
			<a href="${url}&currPage=${currentPage-1}">${currentPage-1}</a>
			<span class="current">${currentPage}</span>
			<a href="${url}&currPage=${currentPage+1}">${currentPage+1}</a>
			<a href="${url}&currPage=${currentPage+2}">${currentPage+2}</a>
			...
			<a href="${url}&currPage=${pageCount-1}">${pageCount-1}</a>
			<a href="${url}&currPage=${pageCount}">${pageCount}</a>			
		</#if>
	</#if>
	<#assign jjj="1" />
	<#if pageCount lte maxPage&&pageCount gt 1>
		<#list 1..pageCount as l3>
			<#if l3==currentPage>
				<span class="current">${l3}</span>
			<#else>
				<a href="${url}&currPage=${l3}">${l3}</a>
			</#if>	
		</#list>
	</#if>
	
	<#if pageCount gt currentPage>
		<a href="${url}&currPage=${currentPage+1}">下一页</a>
	</#if>
	
	<form name="pageForm" method="post" action="${url}" onsubmit="return page">
	总共${pageCount}页 到第<input type="text" name="currPage" title="指定页码" size="3"></input>页<button title="指定页码" type="submit">确定</button>
	</form>
</span>
	
</#macro>

<#--异步调用ajax 返回添加到div 需要引用BaseModel.js
	参数
		url:提交到服务器的url.
		div:返回的内容填充的目标divId
		params:url需要的参数
 -->

<#macro loadpage url,div,params>
	<script language="javascript">
		var page=new ModelBase('${base}${url}','${div}');
		<#if params?exists >
			page.invoke(${params});
		<#else>
			page.invoke();
		</#if>
	</script>
</#macro>

<#--异步调用ajax执行js 方法 需要引用BaseModel.js
	参数
		url:提交到服务器的url.
		func:返回成功后的function方法名
		params:url需要的参数
 -->
<#macro loadfunc url,func,params,>
	<script language="javascript">
		var page=new ModelBase('${base}${url}','');
		page.callback=${func};
		<#if params?exists >
			page.invoke(${params});
		<#else>
			page.invoke();
		</#if>
	</script>
</#macro>