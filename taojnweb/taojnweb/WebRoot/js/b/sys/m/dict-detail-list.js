jQuery(document).ready(function() { 
	window.formatter = {
		useFlag : function(value, row, index) {
			return row.useFlag ? '启用' : '停用';
		},
	};
	// 字典明细加载 
	var $datagrid1 = $('#datagrid1').datagrid({
		border : false,
		remoteSort : true,
		singleSelect : true,
		toolbar : '#toolbar',
		url : context.url('/manage/sys/dict!datagrid1','id='+$("#dictId").val()),
		onSelect : function() {
			$('#btnEdit,#btnDel').linkbutton('enable');
		},
		onBeforeLoad : function() {
			$('#btnEdit,#btnDel').linkbutton('disable');
		}
	});
	// 编辑字典明细
	window.btnEditHandler = function() {
		var row = $datagrid1.datagrid('getSelected');
		util.open({  
			id : 'dictailForm',
			title : '编辑数据字典',
			iconCls : 'icon-edit',
			maximizable : false,
			url : context.url('/manage/sys/dict!detailEdit', "id=" + row.id)
		});
	};
	// 添加字典明细
	window.btnAddHandler = function() {
		util.open({
			id : 'dictailForm',
			title : '新增数据字典内容',
			iconCls : 'icon-add',
			maximizable : false,
			url : context.url('/manage/sys/dict!detailAdd','id='+$("#dictId").val())
		});
	};
	//删除字典明细
	window.btnDelHandler = function() {
		util.confirm('提示', '你确定要删除该记录吗？', function() {
			var row = $datagrid1.datagrid('getSelected');
			var url = context.url('/manage/sys/dict!detailDel');
			var params = {id : row.id};
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
	//刷新数据明细	
	window.refreshDataGrid = function() {
		$datagrid1.datagrid('reload');
	};
});