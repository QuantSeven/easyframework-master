<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript" src="${jsBasePath}/system/menu_index.js"></script>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<table id="menuTreeGrid"></table>

<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
	<div id="add" iconCls="icon-ok">增加</div>
	<div id="edit" iconCls="icon-edit">编辑</div>
	<div id="delete" iconCls="icon-remove">>删除</div>
	<div id="permission" iconCls="icon-remove">>Permission</div>
</div>