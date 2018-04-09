
jQuery(document).ready(function(){

			
	
	// 新增功能
	window.btnAddHandler = function() {
		var row = $("#tab").treegrid('getSelected');
		if(row){
			util.open({
				id : 'sysFunctionForm',
				title : '新增功能',
				iconCls : 'icon-add',
				width: 740,
				height: 599,
				maximizable : false,
				resizable: false,
				modal: false,
				url : context.url('/manage/sys/sysFunction!add', 'sysFunctions.parentId='+row.id)
			});
		}else{
			util.open({
				id : 'sysFunctionForm',
				title : '新增功能',
				iconCls : 'icon-add',
				width: 740,
				height: 599,
				maximizable : false,
				resizable: false,
				modal: false,
				url : context.url('/manage/sys/sysFunction!add')
			});
		}
		
	};
	// 修改功能
	window.btnEditHandler = function() {
		var row = $("#tab").treegrid('getSelected');
		if(row){
			util.open({
				id : 'sysFunctionForm',
				title : '修改功能',
				iconCls : 'icon-edit',
				maximizable : false,
				url : context.url('/manage/sys/sysFunction!edit', "id=" + row.id)
			});
		}else{
			util.alert('提示', '请选中编辑项', 'error');
		}
	};
	
	// 删除
	window.btnDelHandler = function() {
		var rows = $("#tab").datagrid('getSelections');
		if(rows.length){
			$.messager.confirm('提示', '是否删除选择数据?', function(s) {
				if(s){
					var parm=[];
					$.each(rows,function(n,row){
						parm.push(row.id);
					})
					$.post(context.url('/manage/sys/sysFunction!del'), 'ids='+parm, function(res) {
						if (res.success) {
							util.show(res.message);
							refreshDataGrid();
						} else {
							util.alert('提示', res.message, 'error');
						}
					});
				}
			});
		}else{
			$.messager.alert('提示', '请选中删除项', 'error');
		}
	};
	
	// 刷新表格
	window.refreshDataGrid = function() {
		$("#tab").treegrid('reload');
	};
});