<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="common/header.jsp"%>
<script type="text/javascript">
<!--
$(function(){
	$("#index_tabs").tabs({
		fit:true,
		onSelect:function(title,index){
			currentPanel = $("#index_tabs").tabs("getTab",index);
		}
	});
});
//-->
</script>
<body class="easyui-layout" fit="true">
	<!-- 顶部logo面板 -->
	<div region="north" border="false" style="height: 57px;">
		<div id="header">
			<div class="div1">
				<div class="div2">
					<!-- 放logo -->
				</div>
			</div>
		</div>
	</div>
	<!-- 导航菜单 -->
	<div title="导航菜单" region="west" style="width: 200px;">
		<%@include file="common/menu.jsp"%>
	</div>
	<!-- 中间tab面板 -->
	<div region="center" border="false" style="overflow: hidden;">
		<div id="index_tabs" class="easyui-tabs" fit="true" >   
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
