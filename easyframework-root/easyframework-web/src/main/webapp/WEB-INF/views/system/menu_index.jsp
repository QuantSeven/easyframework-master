<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script type="text/javascript" src="${jsBasePath}/system/menu_index.js"></script>
<div class="easyui-layout" fit="true" border="false">
	<div region="north" border="false" style="height: 38px;overflow: hidden;">
		<div class="box">
			<div class="heading">
				<img src="img/menu/arrow_in.png" /> <span><spring:message code="menu.txt.menumgr"/></span>
				<div class="buttons">
					<a id="add" class="btn button"><i class="icon-plus"></i><spring:message code="common.btn.add"/></a>
					<a id="edit" class="btn button" onclick=""><i class="icon-pencil"></i><spring:message code="common.btn.edit"/></a>
					<a id="delete" class="btn button" ><i class="icon-trash"></i><spring:message code="common.btn.delete"/></a>
					<a id="search" class="btn button" ><i class="icon-search"></i><spring:message code="common.btn.search"/></a>
				</div>
			</div>
		</div>
	</div>
	<!-- 中间tab面板 -->
	<div region="center" border="false" style="overflow: hidden;">
		<table id="menuTreeGrid"></table>
	</div>
</div>


<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
	<div id="add" iconCls="icon-save"><spring:message code="common.btn.add"/></div>
	<div id="edit" iconCls="icon-save"><spring:message code="common.btn.edit"/></div>
	<div id="delete" iconCls="icon-save"><spring:message code="common.btn.delete"/></div>
	<div id="setPermission" iconCls="icon-save"><spring:message code="common.btn.permission"/></div>
</div>
