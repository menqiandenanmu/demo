jQuery(document).ready(function() {
	// 列表字段格式化
	window.formatter = {
		sex : function(value, row, index) {
			return row.sex == 1 ? '男' : '女';
		},
		status : function(value, row, index) {
			return row.status == 1 ? '正常' : '锁定';
		},
		adminFlag : function(value, row, index) {
			return row.adminFlag == true ? '是' : '否';
		},
		refundFlag : function(value, row, index) {
			return row.refundFlag == 1 ? '是' : '否';
		}
	};
	// 操作员管理列表加载
	var $datagrid = $('#datagrid').datagrid({
		border : false,
		remoteSort : true,
		singleSelect : true,
		toolbar : '#toolbar',
		url : context.url('/manage/sys/operator!datagrid'),
		onSelect : function() {
			$('#btnEdit,#btnDel,#btnRefund').linkbutton('enable');
		},
		onBeforeLoad : function() {
			$('#btnEdit,#btnDel,#btnRefund').linkbutton('disable');
		}
	});
	// 新增操作员
	window.btnAddHandler = function() {
		util.open({
			id : 'operatorForm',
			title : '新增操作员',
			iconCls : 'icon-add',
			maximizable : false,
			url : context.url('/manage/sys/operator!add')
		});
	};
	// 修改操作员
	window.btnEditHandler = function() {
		var row = $datagrid.datagrid('getSelected');
		util.open({
			id : 'operatorForm',
			title : '修改操作员',
			iconCls : 'icon-edit',
			maximizable : false,
			url : context.url('/manage/sys/operator!edit', "id=" + row.id)
		});
	};
	// 删除操作员
	window.btnDelHandler = function() {
		util.confirm('提示', '你确定要删除该记录吗？', function() {
			var row = $datagrid.datagrid('getSelected');
			var url = context.url('/manage/sys/operator!del');
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
	// 删除操作员
	window.btnRefundHandler = function() {
		util.confirm('提示', '你确定要修改该操作员的退款权限吗？', function() {
			var row = $datagrid.datagrid('getSelected');
			var url = context.url('/manage/sys/operator!refund');
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
			title : '操作员详细信息',
			iconCls : 'icon-edit',
			maximizable : false,
			url : context.url('/manage/sys/operator!info', "id=" + row.id)
		});
	};
	// 角色设置
	window.btnRoleHandler = function() {
		var row = $datagrid.datagrid('getSelected');
		util.open({
			id : 'operatorForm',
			title : '操作员角色维护',
			iconCls : 'icon-edit',
			maximizable : false,
			url : context.url('/manage/sys/operator!roleEdit', "id=" + row.id)
		});
	};
	// 查询操作员
	$('#btnSearch').click(function() {
		$datagrid.datagrid('load', {
			'accountLogin.realName' : $('#realName').val(),
			'accountLogin.loginName' : $('#loginName').val(),
			'accountLogin.adminFlag' : $('#adminFlag').combobox('getValue'),
			'accountLogin.status' : $('#status').combobox('getValue')
		});
	});
	// 刷新表格
	window.refreshDataGrid = function() {
		$datagrid.datagrid('reload');
	};
});