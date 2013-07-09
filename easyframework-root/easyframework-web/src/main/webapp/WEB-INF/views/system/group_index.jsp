<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="${jsBasePath}/system/group_index.js"></script>
<style>
<!--
.left-panel{
	
}
-->
</style>
<div class="easyui-layout" fit="true" border="false">
	<div region="north" border="false" style="height: 78px;overflow: hidden;">
		<div class="box">
			<div class="heading">
				<img src="img/icon/group.png" /> <span><spring:message code="group.txt.usermgr"/></span>
				<div class="buttons">
					<a id="add" class="btn button"><i class="icon-plus"></i><spring:message code="common.btn.add"/></a>
					<a id="edit" class="btn button"><i class="icon-pencil"></i><spring:message code="common.btn.edit"/></a>
					<a id="delete" class="btn button" ><i class="icon-trash"></i><spring:message code="common.btn.delete"/></a>
					<a id="search" class="btn button" ><i class="icon-search"></i><spring:message code="common.btn.search"/></a>
				</div>
			</div>
		</div>
		<div class="datagrid-search">
			<form class="form-search" id="searchForm" >
				<ul>
					<li>
						<label><spring:message code="group.txt.name" />：</label> 
						<input type="text" placeholder='' name="filter_LIKES_name" />
					</li>
				</ul>
			</form>
		</div>
	</div>
	<!-- 中间tab面板 -->
	<div region="center" border="false" style="overflow: hidden;">
		<table id="groupDataGrid"></table>
	</div>
	<!-- 左边tab面板 -->
	<div region="east" border="false" style="overflow: hidden; width:350px;" title="用户组成员">
		<table id="groupUserDataGrid"></table>
	</div>
</div>
