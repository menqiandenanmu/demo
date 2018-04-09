<div title="功能菜单1" class="easyui-panel" data-options="border:false,fit:true,tools:'#tools'" style="overflow-y:auto;">
	<!--Tree-->
	<ul id="treemenu">
	<#if funcs?exists>
		<#list funcs as funIndex>
		<li data-options="iconCls:'icon-gearsgroup',state:'closed',attributes:{type:'folder'}"><span>${funIndex.funName}</span>
			<ul>
				<#list funIndex.childs as funItem>
				<li data-options="iconCls:'icon-gears',attributes:{type:'func'}">
					<a class="func" id="${funIndex.id}" url="${base}/${funItem.funUrl}">${funItem.funName}</a>
				</li>
				</#list>
			</ul>
		</li>
		</#list>
	</#if>
	</ul>

	<!--Accordion-->
	<div id="accordionmenu" class="easyui-accordion" data-options="border:false,fit:true">
	<#if funcs?exists>
		<#list funcs as funIndex>
		<div class="menulist" title="${funIndex.funName}" data-options="iconCls:'icon-ok'">
			<dl>
				<#list funIndex.childs as funItem>
				<dd class="func" id="${funIndex.id}" url="${base}/${funItem.funUrl}">
					<span><img src="${base}/images/icons/other.gif" /></span>
					<a>${funItem.funName}</a>
				</dd>
				</#list>
			</dl>
		</div>
		</#list>
	</#if>
	</div>
</div>
<div id="tools">
	<a href="javascript:void(0)" title="刷新菜单" class="icon-reload" onclick=""></a>
	<a href="javascript:void(0)" title="切换菜单显示方式" class="icon-layouttree" onclick="$('#treemenu,#accordionmenu').toggle();"></a>
	<a href="javascript:void(0)" title="隐藏菜单栏" class="layout-button-left" onclick="$(document.body).layout('collapse','west');return false;"></a>
</div>
<script>
jQuery(document).ready(function() {
	// 树型菜单点击事件
	var $treemenu = $('#treemenu').tree({
		onClick : function(node) {
			if (node.attributes.type == 'func') {
				var target = $('a.func', node.target);
				var title = target.text();
				var id = target.attr("id");
				var url = target.attr("url");
				showFuncWin(title, id, url);
			} else {
				$treemenu.tree('toggle', node.target);
			}
		}
	});
	// 可折叠菜单事件
	$('dd.func').click(function() {
		var title = $(this).text();
		var id = $(this).attr("id");
		var url = $(this).attr("url");
		showFuncWin(title, id, url);
	});
	// 默认显示树型菜单，隐藏可折叠菜单
	$('#accordionmenu').hide();
});
</script>