<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<form id="menuForm" action="${action }" method="post" class="form">
	<div class="form-area" title='<spring:message code="menu.txt.baseinfo"/>' >
		<ul>
			<li>
				<label ><spring:message code="menu.txt.id"/>:</label>
				<input type="text" id="id" name="id" value="${menu.id }" readonly="readonly" />
			</li>
			<li>
				<label ><spring:message code="menu.txt.name"/>:</label>
				<input type="text" id="name" name="name" value="${menu.name }"  />
			</li>
			<li>
				<label ><spring:message code="menu.txt.url"/>:</label>
				<input type="text" id="url" name="url" value="${menu.url }"  />
			</li>
			<li>
				<label ><spring:message code="menu.txt.iconcls"/>:</label>
				<input type="text" id="iconCls" name="iconCls" value="${menu.iconCls }"  />
			</li>
		</ul>
	</div>
</form>

