<div class="easyui-panel" data-options="border:false,fit:true">
<ul class="easyui-tree">
<#if funcs?exists>
	<#list funcs as funIndex>
	<li data-options="iconCls:'icon-gearsgroup',state:'closed'"><span>${funIndex.funName}</span>
		<ul>
			<#list funIndex.childs as funItem>
			<li id="${funIndex.id}" url="${base}/${funItem.funUrl}" data-options="iconCls:'icon-gears'">
				<a href="javascript:void(0)">${funItem.funName}</a>
			</li>
			</#list>
		</ul>
	</li>
	</#list>
</#if>
</ul>
</div>