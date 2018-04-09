jQuery(document).ready(function() {
	// 列表字段格式化
	window.formatter = {
		sex : function(value, row, index) {
			return row.useFlag == 0 ? '停用' : '启用';
		},
	};
	// 子系统管理列表加载
	var $datagrid = $('#datagrid').datagrid({
		border : false,
		remoteSort : true,
		singleSelect : true,
		toolbar : '#toolbar',
		url : context.url('/manage/sys/subSysMenu!datagrid'),
		onSelect : function() {
			$('#btnEdit,#btnDel').linkbutton('enable');
		},
		onBeforeLoad : function() {
			$('#btnEdit,#btnDel').linkbutton('disable');
		}
	});
	// 新增子系统
	window.btnAddHandler = function() {
		util.open({
			id : 'operatorForm',
			title : '新增子系统菜单',
			iconCls : 'icon-add',
			maximizable : false,
			url : context.url('/manage/sys/subSysMenu!add')
		});
	};
	// 修改子系统
	window.btnEditHandler = function() {
		var row = $datagrid.datagrid('getSelected');
		util.open({
			id : 'operatorForm',
			title : '修改子系统菜单',
			iconCls : 'icon-edit',
			maximizable : false,
			url : context.url('/manage/sys/subSysMenu!edit', "id=" + row.id)
		});
	};
	// 删除子系统
	window.btnDelHandler = function() {
		util.confirm('提示', '你确定要删除该记录吗？', function() {
			var row = $datagrid.datagrid('getSelected');
			var url = context.url('/manage/sys/subSysMenu!del');
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
			title : '子系统菜单详细信息',
			iconCls : 'icon-edit',
			maximizable : false,
			url : context.url('/manage/sys/subSysMenu!info', "id=" + row.id)
		});
	};
	// 查询子系统列表
	$('#btnSearch').click(function() {
		$datagrid.datagrid('load', {
			'subsystemMeiuInfo.subsysId' : $('#subsysId').val(),
			'subsystemMeiuInfo.subsystemName' : $('#subsystemName').val(),
			'subsystemMeiuInfo.subsysMenuname' : $('#subsysMenuname').val(),
			'subsystemMeiuInfo.subsysMenuurl' : $('#subsysMenuurl').val(),
			'subsystemMeiuInfo.remark' : $('#remark').val(),
			'subsystemMeiuInfo.useFlag' : $('#useFlag').combobox('getValue')
		});
	});
	// 刷新表格
	window.refreshDataGrid = function() {
		$datagrid.datagrid('reload');
	};
});