$(function() {
	var that = currentPanel;
	var i18nMenu = EasyUtil.getI18NMessage("menu"); // 每次进来获取国际化的信息
	var  menuTreeGrid = null, selectedItem = null;
	var menuFormDiv = $("<div id='menuFormDiv'></div>");
	var menuContext = $(that).find('#menu');
	menuTreeGrid = $('#menuTreeGrid').treegrid({
		url : 'menu/tree',
		idField : 'id',
		fitColumns : true,
		fit : true,
		treeField : 'name',
		columns : [ [ {
			title : '名称',
			field : 'name',
			width : 180
		}, {
			title : '地址',
			field : 'url',
			width : 180
		} ] ],
		onContextMenu : function(e, row) {
			e.preventDefault();
			menuTreeGrid.treegrid('unselectAll');
			menuTreeGrid.treegrid('select', row.id);
			menuContext.menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		},
	});

	$(that).find("#add").on("click",function() {
		menuFormDialog("添加菜单", "menu/form");
	});

	var menuFormDialog = function(title, url) {
		menuFormDiv.dialog({
			title : title,
			width : 645,
			height : 410,
			href : url,
			modal:true ,
			maximizable:true,
			resizable:true,
			style : {
				overflow : 'hidden'
			},
			onClose : function() {
				menuFormDiv.dialog('destroy');
			},
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					EasyUtil.openProgress(); // 显示进度条
					$('#menuForm').form('submit', {
						onSubmit : function() {
							var isValid = $(this).form('validate');
							if (!isValid) {
								EasyUtil.closeProgress(); // 如果表单是无效的则隐藏进度条
							}
							return isValid;
						},
						success : function(data) {
							selectedItem = null;
							menuTreeGrid.treegrid('reload'); // 重新载入当前页面数据
							EasyUtil.closeProgress();
							menuFormDiv.dialog("close");
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
				handler : function() {
					menuFormDiv.dialog("close");
				}
			} ]
		});
	};
});