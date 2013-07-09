$(function() {
	var i18nGroup = EasyUtil.getI18NMessage("group"); // 每次进来获取国际化的信息
	var commonBtn = EasyUtil.getI18NMessage("common.btn"); // 每次进来获取国际化的信息
	var that = currentPanel;
	var groupDataGrid = null,groupUserDataGrid=null,selectedItem = null,selectUserItem = null;
	var groupFormDialog = $("<div id='groupFormDialog'></div>");
	groupDataGrid = $(that).find('#groupDataGrid').datagrid({
		url : 'group/list',
		pagination : true,
		pagePosition : 'bottom',
		singleSelect : true,
		fitColumns : true,
		fit : true,
		frozenColumns : [ [ {
			field : 'id',
			title : i18nGroup.txt.id,
			width : 150,
			checkbox : true
		} ] ],
		columns : [ [ {
			field : 'name',
			title : i18nGroup.txt.name,
			width : 100
		}, {
			field : 'groupType',
			title : i18nGroup.txt.grouptype,
			width : 100
		}, {
			field : 'sort',
			title : i18nGroup.txt.sort,
			width : 200
		}, {
			field : 'description',
			title : i18nGroup.txt.description,
			width : 100
		}, {
			field : 'status',
			title : i18nGroup.txt.status,
			width : 35,
			formatter : function(value, row, index) {
				if (value === '1') {
					return '<span class="badge badge-success">' + i18nGroup.txt.enable + '</span>';
				} else {
					return '<span class="badge badge-important">' + i18nGroup.txt.disable + '</span>';
				}
			}
		}, ] ],
		onClickRow : function(rowIndex, rowData) {
			selectedItem = rowData;
		}
	});

	$(that).find("#add").bind("click", function() {
		groupForm(i18nGroup.txt.addgroup, 'group/form');
	});
	$(that).find("#delete").bind("click", function() {
		if (selectedItem) {
			EasyUtil.confirm(i18nGroup.txt.suredelete, function(r) {
				if (r) {
					EasyUtil.ajaxData('group/delete', {
						data : EasyUtil.converToJsonStr(selectedItem),
						contentType : 'application/json'
					});
					groupDataGrid.datagrid('reload'); // 重新载入当前页面数据
				}
			});
		} else {
			EasyUtil.errorMsg(i18nGroup.txt.noselete);
		}
	});
	$(that).find("#edit").bind("click", function() {
		if (selectedItem) {
			groupForm(i18nGroup.txt.editgroup, 'group/form?id=' + selectedItem.id);
		} else {
			EasyUtil.errorMsg(i18nGroup.txt.noselete);
		}
	});
	$(that).find("#search").bind("click", function() {
		groupDataGrid.datagrid('load', EasyUtil.serializeFieldValues($('#searchForm')));
	});

	var groupForm = function(title, url) {
		groupFormDialog.dialog({
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
				groupFormDialog.dialog('destroy');
			},
			buttons : [ {
				text : commonBtn.save,
				iconCls : 'icon-ok',
				handler : function() {
					EasyUtil.openProgress(); // 显示进度条
					$('#groupForm').form('submit', {
						onSubmit : function() {
							var isValid = $(this).form('validate');
							if (!isValid) {
								EasyUtil.closeProgress(); // 如果表单是无效的则隐藏进度条
							}
							return isValid;
						},
						success : function(data) {
							selectedItem = null;
							groupDataGrid.datagrid('reload'); // 重新载入当前页面数据
							EasyUtil.closeProgress();
							groupFormDialog.dialog("close");
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
				iconCls:'icon-cancel',
				handler : function() {
					groupFormDialog.dialog("close");
				}
			} ]
		});
	};
	/*用户组的用户操作*/
	groupUserDataGrid = $(that).find('#groupUserDataGrid').datagrid({
		url : 'group/list',
		pagination : true,
		pagePosition : 'bottom',
		singleSelect : true,
		fitColumns : true,
		fit : true,
		frozenColumns : [ [ {
			field : 'id',
			title : i18nGroup.user.txt.id,
			width : 150,
			checkbox : true
		} ] ],
		columns : [ [ {
			field : 'accountNo',
			title : i18nGroup.user.txt.accountno,
			width : 100
		},{
			field : 'name',
			title : i18nGroup.user.txt.name,
			width : 100
		}]]
	});
});