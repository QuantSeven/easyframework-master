<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="common/header.jsp"%>
<script type="text/javascript">
<!--
$(function(){
	$("#index_tabs").tabs({
		fit:true,
		style:{overflow: 'hidden'},
		onSelect:function(title,index){
			currentPanel = $(this).tabs("getTab",index);
		}
	});
});
//-->
</script>
<body class="easyui-layout" fit="true">
	<!-- 顶部logo面板 -->
	<div region="north" border="false" style="height: 57px;overflow: hidden;">
		<div class="nav-panel">
			<div class="logo">
					
			</div>
			
			<div class="user-info">
				<a href="${basePath }/logout"><i class="icon-off icon-white"></i>注销</a>
			</div>
			
			<div class="separator"/>
			</div>
			<div class="user-info">
				<a href="${basePath }/logout"><i class="icon-user icon-white"></i><shiro:principal property="username"/></a>
			</div>
		</div>
	</div>
	<!-- 导航菜单 -->
	<div title="导航菜单" region="west" style="width: 200px;">
		<%@include file="common/menu.jsp"%>
	</div>
	<!-- 中间tab面板 -->
	<div region="center" border="false" style="overflow: hidden;">
		<div id="index_tabs" >   
			<div title="首页" collapsible="true">
				<%@include file="common/portal.jsp"%>
			</div>
		</div>
	</div>
	<!-- 底部版本面板 -->
	<div region="south" style="height: 40px;overflow: hidden;">
		<%@include file="common/footer.jsp"%>
	</div>
</body>
</html>
