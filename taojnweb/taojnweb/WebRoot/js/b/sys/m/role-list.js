jQuery(document).ready(function() {
	// 列表字段格式化
	window.formatter = {
		roleAccType : function(value, row, index) {
			if (row.roleAccType == 0) {
				return "系统管理";
			} else if (row.roleAccType == 1) {
				return "企业平台";
			} else if (row.roleAccType == 2) {
				return "散客";
			} else if (row.roleAccType == 5)
				return "分销商平台";
			return "";
		},
		useFlag : function(value, row, index) {
			return row.useFlag ? "启用" : "停用";
		}
	};
	// 角色管理列表加载
	var $datagrid = $('#datagrid').datagrid({
		border : false,
		remoteSort : true,
		singleSelect : true,
		toolbar : '#toolbar',
		url : context.url('/manage/sys/role!datagrid'),
		onSelect : function() {
			$('#btnEdit,#btnDel').linkbutton('enable');
		},
		onBeforeLoad : function() {
			$('#btnEdit,#btnDel').linkbutton('disable');
		}
	});
	// 新增角色
	window.btnAddHandler = function() {
		util.open({
			id : 'roleForm',
			title : '新增角色',
			iconCls : 'icon-add',
			maximizable : false,
			url : context.url('/manage/sys/role!add')
		});
	};
	
	// 修改角色
	window.btnEditHandler = function() {
		var row = $datagrid.datagrid('getSelected');
		util.open({
			id : 'roleForm',
			title : '修改角色',
			iconCls : 'icon-edit',
			maximizable : false,
			url : context.url('/manage/sys/role!edit', "id=" + row.id)
		});
	};
	// 删除角色
	window.btnDelHandler = function() {
		util.confirm('提示', '你确定要删除该记录吗？', function() {
			var row = $datagrid.datagrid('getSelected');
			var url = context.url('/manage/sys/role!del');
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
	// 权限设置// <#if res.accountId==res.createLoginId>
	window.btnInfoHandler = function() {
		var row = $datagrid.datagrid('getSelected');
		util.open({
			id : 'roleForm',
			title : '权限维护',
			iconCls : 'icon-edit',
			maximizable : false,
			url : context.url('/manage/sys/role!infoEdit', "id=" + row.id)
		});
	};
	// 查询角色
	$('#btnSearch').click(function() {
		$datagrid.datagrid('load', {
			'role.roleName' : $('#roleName').val(),
			'role.roleAccType' : $('#roleAccType').combobox('getValue'),
			'role.useFlag' : $('#useFlag').combobox('getValue')
		});
	});
	// 刷新表格
	window.refreshDataGrid = function() {
		$datagrid.datagrid('reload');
	};
});