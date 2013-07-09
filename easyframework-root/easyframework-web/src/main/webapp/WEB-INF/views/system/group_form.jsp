<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<form id="groupForm" action="${action }" method="post" class="form">
	<div class="form-area" title='<spring:message code="group.txt.baseinfo"/>' >
		<ul>
			<li>
				<label><spring:message code="group.txt.name"/>:</label>
				<input type="hidden" name="id"  readonly="readonly" value="${group.id }"/>
				<input type="text" id="name" name="name" value="${group.name }" class="easyui-validatebox" required="true"/>
			</li>
			<li>
				<label><spring:message code="group.txt.sort"/>:</label>
				<input type="text" id="sort" name="sort" value="${group.sort }" class="easyui-validatebox" required="true"/>
			</li>
			<li>
				<label><spring:message code="group.txt.grouptype"/>:</label>
				<input type="text" id="groupType" name="groupType" value="${group.groupType }" class="easyui-validatebox" data-options="number:true" />
			</li>
			<li>
				<label><spring:message code="group.txt.status"/>:</label>
				<input type="text" id="status" name="status" value="${group.status }" class="easyui-validatebox" data-options="number:true" />
			</li>
			<li style="height:65px;width: 98%">
				<label><spring:message code="group.txt.description"/>:</label>
				<textarea style="width:506px" id="description" name="description" value="${group.description }" class="easyui-validatebox" data-options="number:true"></textarea>
			</li>
		</ul>
	</div>
</form>
