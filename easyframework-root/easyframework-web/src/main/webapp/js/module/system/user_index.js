$(function() {
	var i18nUser = EasyUtil.getI18NMessage("user"); // 每次进来获取国际化的信息
	var commonBtn = EasyUtil.getI18NMessage("common.btn"); // 每次进来获取国际化的信息
	var that = currentPanel;
	var userDataGrid = null, selectedItem = null;
	var userFormDiv = $("<div id='userFormDiv'></div>");
	userDataGrid = $(that).find('#userDataGrid').datagrid({
		url : 'user/list',
		pagination : true,
		pagePosition : 'bottom',
		singleSelect : true,
		fitColumns : true,
		fit : true,
		frozenColumns : [ [ {
			field : 'id',
			title : i18nUser.txt.id,
			width : 150,
			checkbox : true
		} ] ],
		columns : [ [ {
			field : 'accountNo',
			title : i18nUser.txt.accountno,
			width : 100
		}, {
			field : 'username',
			title : i18nUser.txt.username,
			width : 100
		}, {
			field : 'email',
			title : i18nUser.txt.email,
			width : 200
		}, {
			field : 'position',
			title : i18nUser.txt.position,
			width : 100
		}, {
			field : 'phone',
			title : i18nUser.txt.phone,
			width : 100
		}, {
			field : 'sex',
			title : i18nUser.txt.sex,
			width : 50
		}, {
			field : 'status',
			title : i18nUser.txt.status,
			width : 35,
			formatter : function(value, row, index) {
				if (value === '1') {
					return '<span class="badge badge-success">' + i18nUser.txt.enable + '</span>';
				} else {
					return '<span class="badge badge-important">' + i18nUser.txt.disable + '</span>';
				}
			}
		}, ] ],
		toolbar : '#toolbar',
		onClickRow : function(rowIndex, rowData) {
			selectedItem = rowData;
		}
	});

	$(that).find("#add").bind("click", function() {
		userFormDialog(i18nUser.txt.adduser, 'user/form');
	});
	$(that).find("#delete").bind("click", function() {
		if (selectedItem) {
			EasyUtil.confirm(i18nUser.txt.suredelete, function(r) {
				if (r) {
					EasyUtil.ajaxData('user/delete', {
						data : EasyUtil.converToJsonStr(selectedItem),
						contentType : 'application/json'
					});
					userDataGrid.datagrid('reload'); // 重新载入当前页面数据
				}
			});
		} else {
			EasyUtil.errorMsg(common.txt.pls);
		}
	});
	$(that).find("#edit").bind("click", function() {
		if (selectedItem) {
			userFormDialog(i18nUser.txt.edituser, 'user/form?id=' + selectedItem.id);
		} else {
			EasyUtil.errorMsg(i18nUser.txt.noselete);
		}
	});
	$(that).find("#seach").bind("click", function() {
		userDataGrid.datagrid('load', EasyUtil.serializeFieldValues($('#searchForm')));
	});

	var userFormDialog = function(title, url) {
		userFormDiv.dialog({
			title : title,
			width : 645,
			height : 410,
			href : url,
			modal : true,
			maximizable : true,
			resizable : true,
			style : {
				overflow : 'hidden'
			},
			onClose : function() {
				userFormDiv.dialog('destroy');
			},
			buttons : [ {
				text : commonBtn.save,
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
							userFormDiv.dialog("close");
							EasyUtil.successMsg(data);
						},
						onLoadError : function(data) {
							EasyUtil.errorMsg(data);
							EasyUtil.closeProgress();
						}
					});
				}
			}, {
				text : commonBtn.close,
				handler : function() {
					userFormDiv.dialog("close");
				}
			} ]
		});
	};
});