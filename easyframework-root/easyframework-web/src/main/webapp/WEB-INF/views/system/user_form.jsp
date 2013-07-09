<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<form id="userForm" action="${action }" method="post" class="form">
	<div class="form-area" title='<spring:message code="user.txt.baseinfo"/>' >
		<ul>
			<li>
				<label><spring:message code="user.txt.id"/>:</label>
				<input type="text" name="id"  readonly="readonly" value="${user.id }"/>
			</li>
			<li>
				<label><spring:message code="user.txt.accountno"/>:</label>
				<input  type="text" name="accountNo" value="${user.accountNo }" class="easyui-validatebox" placeholder="accountNo" required="true"/>
			</li>
			<li>
				<label><spring:message code="user.txt.username"/>:</label>
				<input type="text" id="username" name="username" value="${user.username }" class="easyui-validatebox" required="true"/>
			</li>
			<li>
				<label><spring:message code="user.txt.password"/>:</label>
				<input type="text" id="password" name="password" value="${user.password }" class="easyui-validatebox" required="true"/>
			</li>
			<li>
				<label><spring:message code="user.txt.email"/>:</label>
				<input type="text" id="email" name="email" value="${user.email }" class="easyui-validatebox" data-options="validType:'email'" />
			</li>
			<li>
				<label><spring:message code="user.txt.phone"/>:</label>
				<input type="text" id="phone" name="phone" value="${user.phone }" class="easyui-validatebox" data-options="number:true" />
			</li>
		</ul>
	</div>
</form>
