<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form id="userForm" action="${action }" method="post" class="form">
	<div class="form-area" title="基本信息" >
		<ul>
			<li>
				<label >编号:</label>
				<input type="text" name="id"  readonly="readonly" value="${user.id }"/>
			</li>
			<li>
				<label >账号:</label>
				<input  type="text" name="accountNo" value="${user.accountNo }" class="easyui-validatebox" placeholder="accountNo" required="true"/>
			</li>
			<li>
				<label >用户名:</label>
				<input type="text" id="username" name="username" value="${user.username }" class="easyui-validatebox" required="true"/>
			</li>
			<li>
				  <label>密码:</label>
				<input type="text" id="password" name="password" value="${user.password }" class="easyui-validatebox" required="true"/>
			</li>
			<li>
				  <label>邮箱:</label>
				<input type="text" id="email" name="email" value="${user.email }" class="easyui-validatebox" data-options="validType:'email'" />
			</li>
			<li>
				  <label>电话号码:</label>
				<input type="text" id="phone" name="phone" value="${user.phone }" class="easyui-validatebox" data-options="number:true" />
			</li>
		</ul>
	</div>
</form>
