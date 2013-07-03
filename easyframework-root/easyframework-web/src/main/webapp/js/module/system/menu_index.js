$(function() {
	var i18nMenu = EasyUtil.getI18NMessage("menu"); // 每次进来获取国际化的信息
	var that = currentPanel, menuTreeGrid = null, selectedItem = null;
	var menuPanal = $(that).find("#menu");
	console.info(menuPanal);
	menuTreeGrid = $(that).find('#menuTreeGrid').treegrid({
		url : 'menu/treeMenu',
		idField:'id',
		fitColumns:true,
		animate:true,
	    treeField:'name',    
	    columns:[[    
	        {field:'name',title:'name',width:180},
	        {field:'url',title:'url',width:180} 
	    ]],    
	    onContextMenu: function(e, row) {
			e.preventDefault();
			menuTreeGrid.treegrid('unselectAll');
			menuTreeGrid.treegrid('select', row.id);
			menuPanal.menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
	});

	$(that).find("#add").bind("click", function() {
		userFormDialog('添加用户', 'user/form');
	});
	$(that).find("#delete").bind("click", function() {
		if (selectedItem) {
			EasyUtil.confirm("确定要删除选中的记录么？", function(r) {
				if (r) {
					EasyUtil.ajaxData('user/delete', {
						data : EasyUtil.converToJsonStr(selectedItem),
						contentType : 'application/json'
					});
					userDataGrid.datagrid('reload'); // 重新载入当前页面数据
				}
			});
		} else {
			EasyUtil.errorMsg('请选择一条记录');
		}
	});
	$(that).find("#edit").bind("click", function() {
		if (selectedItem) {
			userFormDialog('添加用户', 'user/form?id=' + selectedItem.id);
		} else {
			EasyUtil.errorMsg('请选择一条记录');
		}
	});
	$(that).find("#seach").bind("click", function() {
		userDataGrid.datagrid('load', EasyUtil.serializeFieldValues($('#searchForm')));
	});

	var userFormDialog = function(title, url) {
		$.modal({
			title : title,
			width : 600,
			height : 410,
			href : url,
			style : {
				overflow : 'hidden'
			},
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					EasyUtil.openProgress(); // 显示进度条
					$('#userForm').form('submit', {
						onSubmit : function() {
							var isValid = $(this).form('validate');
							if (!isValid) {
								EasyUtil.closeProgress(); // 如果表单是无效的则隐藏进度条
							}
							return isValid;
						},
						success : function(data) {
							selectedItem = null;
							userDataGrid.datagrid('reload'); // 重新载入当前页面数据
							EasyUtil.closeProgress();
							$.modal.instance.dialog("close");
							EasyUtil.successMsg(data);
						},
						onLoadError : function(data) {
							EasyUtil.errorMsg(data);
							EasyUtil.closeProgress();
						}
					});
				}
			}, {
				text : '关闭',
			    iconCls:'icon-refresh icon-spin',
				handler : function() {
					$.modal.instance.dialog("close");
				}
			} ]
		});
	};
});