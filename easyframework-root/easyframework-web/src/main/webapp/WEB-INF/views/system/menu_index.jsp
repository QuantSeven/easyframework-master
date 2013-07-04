<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script type="text/javascript" src="${jsBasePath}/system/menu_index.js"></script>

<table id="menuTreeGrid"></table>


<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
	<div id="add" iconCls="icon-save"><spring:message code="common.btn.add"/></div>
	<div id="edit" iconCls="icon-save"><spring:message code="common.btn.edit"/></div>
	<div id="delete" iconCls="icon-save"><spring:message code="common.btn.delete"/></div>
	<div id="setPermission" iconCls="icon-save"><spring:message code="common.btn.permission"/></div>
</div>
