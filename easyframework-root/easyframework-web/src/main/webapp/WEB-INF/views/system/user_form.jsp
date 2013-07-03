<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<form id="userForm" action="${action }" method="post" class="form">
	<div class="form-content">
		<div class="form-area" title='基本信息'>
			<ul>
				<li>
					<label><spring:message code="user.txt.id" />:</label>
				 	<input type="text" name="id" id="id" value="${user.id}" readonly="readonly" />
				</li>
				<li>
					<label><spring:message code="user.txt.username" />:</label>
				 	<input type="text" name="username" id="username" value="${user.username}"/>
				</li>
				<li>
					<label><spring:message code="user.txt.accountno" />:</label>
				 	<input type="text" name="accountno" id="accountno" value="${user.accountno}"/>
				</li>
				
			</ul>
		</div>
		<div class="form-area" title='<spring:message code="user.txt.baseinfo"/>'>
			<ul>
				<li>
					<label><spring:message code="user.txt.id" />:</label>
				 	<input type="text" name="id" id="id" value="${user.id}" readonly="readonly" />
				</li>
				<li>
					<label><spring:message code="user.txt.username" />:</label>
				 	<input type="text" name="username" id="username" value="${user.username}"/>
				</li>
				<li>
					<label><spring:message code="user.txt.accountno" />:</label>
				 	<input type="text" name="accountno" id="accountno" value="${user.accountno}"/>
				</li>
			</ul>
		</div>
		<div class="form-area" title='<spring:message code="user.txt.baseinfo"/>'>
			<ul>
				<li>
					<label><spring:message code="user.txt.id" />:</label>
				 	<input type="text" name="id" id="id" value="${user.id}" readonly="readonly" />
				</li>
				<li>
					<label><spring:message code="user.txt.username" />:</label>
				 	<input type="text" name="username" id="username" value="${user.username}"/>
				</li>
				<li>
					<label><spring:message code="user.txt.accountno" />:</label>
				 	<input type="text" name="accountno" id="accountno" value="${user.accountno}"/>
				</li>
				<li>
					<label><spring:message code="user.txt.email" />:</label>
				 	<input type="text" name="email" id="email" value="${user.email}"/>
				</li>
			</ul>
		</div>
		<div class="form-area" title='<spring:message code="user.txt.baseinfo"/>'>
			<ul>
				<li>
					<label><spring:message code="user.txt.id" />:</label>
				 	<input type="text" name="id" id="id" value="${user.id}" readonly="readonly" />
				</li>
				<li>
					<label><spring:message code="user.txt.username" />:</label>
				 	<input type="text" name="username" id="username" value="${user.username}"/>
				</li>
				<li>
					<label><spring:message code="user.txt.accountno" />:</label>
				 	<input type="text" name="accountno" id="accountno" value="${user.accountno}"/>
				</li>
				<li>
					<label><spring:message code="user.txt.email" />:</label>
				 	<input type="text" name="email" id="email" value="${user.email}"/>
				</li>
			</ul>
		</div>
	</div>
</form>
