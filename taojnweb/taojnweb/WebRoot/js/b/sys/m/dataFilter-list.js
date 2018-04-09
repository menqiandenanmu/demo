jQuery(document).ready(function() {
	// 列表字段格式化
	window.formatter = {
		filterType : function(value, row, index) { 
		    if(row.filterType == 0)
			   return '手机'
			else if(row.filterType ==1) 
			   return '身份证'
		    else if(row.filterType ==2) 
			   return 'IP'
		    else if(row.filterType ==3) 
			   return '其它'
		},
	};
	// 黑名单列表加载
	var $datagrid = $('#datagrid').datagrid({
		border : false,
		remoteSort : true,
		singleSelect : true,
		toolbar : '#toolbar',
		url : context.url('/manage/sys/dataFilter!datagrid'),
		onSelect : function() {
			$('#btnEdit,#btnDel').linkbutton('enable');
		},
		onBeforeLoad : function() {
			$('#btnEdit,#btnDel').linkbutton('disable');
		}
	});
	// 新增黑名单
	window.btnAddHandler = function() {
		util.open({
			id : 'operatorForm',
			title : '新增黑名单',
			iconCls : 'icon-add',
			maximizable : false,
			url : context.url('/manage/sys/dataFilter!add')
		});
	};
	// 修改黑名单
	window.btnEditHandler = function() {
		var row = $datagrid.datagrid('getSelected');
		util.open({
			id : 'operatorForm',
			title : '修改黑名单',
			iconCls : 'icon-edit',
			maximizable : false,
			url : context.url('/manage/sys/dataFilter!edit', "id=" + row.id)
		});
	};
	// 删除黑名单
	window.btnDelHandler = function() {
		util.confirm('提示', '你确定要删除该记录吗？', function() {
			var row = $datagrid.datagrid('getSelected');
			var url = context.url('/manage/sys/dataFilter!del');
			var params = {
				id : row.id
			};
			$.post(url, params, function(res) {
				if (res.success) {
					util.show(res.message);
					refreshDataGrid();
				} else {
					util.alert('提示', res.message, 'error');
				}
			});
		});
	};
	// 详细查看
	window.btnDetailHandler = function() {
		var row = $datagrid.datagrid('getSelected');
		util.open({
			id : 'operatorForm',
			title : '黑名单详细信息',
			iconCls : 'icon-edit',
			maximizable : false,
			url : context.url('/manage/sys/dataFilter!info', "id=" + row.id)
		});
	};
	// 查询黑名单
	$('#btnSearch').click(function() {
		$datagrid.datagrid('load', {
			'sysDataFilter.createUserName' : $('#createUserName').val(),
			'sysDataFilter.filterType' : $('#filterType').combobox('getValue')
		});
	});
	// 刷新表格
	window.refreshDataGrid = function() {
		$datagrid.datagrid('reload');
	};
});