package com.easyframework.test.system;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.easyframework.common.ui.JsonModel;
import com.easyframework.common.utils.json.JsonMapper;
import com.easyframework.core.service.system.UserService;
import com.easyframework.model.system.User;
import com.easyframework.test.base.BaseServiceTest;

public class UserServiceTest extends BaseServiceTest {

	private UserService userService;
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	@Rollback(false)
	@Test
	public void testSave(){
		User u = userService.getUserByLoginName("qya");
		u.setDescription("test");
		userService.modify(u);
	}
}
