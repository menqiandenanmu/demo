jQuery(document).ready(function() {
	// 系统参数列表加载

	var $datagrid = $('#datagrid').datagrid({
		border : false,
		remoteSort : true,
		singleSelect : true,
		toolbar : '#toolbar',
		url : context.url('/manage/sys/param!datagrid'),
		onSelect : function() {
			$('#btnEdit').linkbutton('enable');
		},
		onBeforeLoad : function() {
			$('#btnEdit').linkbutton('disable');
		}
	});
	// 修改参数值
	window.btnEditHandler = function() {
		var row = $datagrid.datagrid('getSelected');
		util.open({
			id : 'roleForm',
			title : '修改参数',
			iconCls : 'icon-edit',
			maximizable : false,
			url : context.url('/manage/sys/param!edit', "id=" + row.id)
		});
	};
	// 查询角色
	$('#btnSearch').click(function() {
		$datagrid.datagrid('load', {
			'param.paramName' : $('#paramName').val(),
			'param.paramCode' : $('#paramCode').val()
		});
	});
	// 刷新表格
	window.refreshDataGrid = function() {
		$datagrid.datagrid('reload');
	};
});