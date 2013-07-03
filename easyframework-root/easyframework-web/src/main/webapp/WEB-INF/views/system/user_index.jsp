<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script type="text/javascript" src="${jsBasePath}/system/user_index.js"></script>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="easyui-layout" fit="true" border="false">
	<div region="north" border="false" style="height: 40px;">
		<form class="form-search" id="searchForm">
			<ul>
		   		<li>
					<label><spring:message code="user.txt.accountno"/> ：</label>
					<input type="text" placeholder='' name="filter_LIKES_accountNo" /></li>
				</li>
		   		<li>
					<label><spring:message code="user.txt.username"/> ：</label>
					<input type="text" placeholder='' name="filter_LIKES_username" /></li>
				</li>
		   		<li>
					<label><spring:message code="user.txt.email"/> ：</label>
					<input type="text" placeholder='' name="filter_LIKES_email" /></li>
				</li>
			</ul>
		</form>
	</div>
	<!-- 中间tab面板 -->
	<div region="center" border="false" style="overflow: hidden;">
		<table id="userDataGrid"></table>
	</div>
</div>
<div id="toolbar" style="display: none">
	<a id="add" href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
	<a id="edit" href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
	<a id="delete" href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	<a id="seach" href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
</div>