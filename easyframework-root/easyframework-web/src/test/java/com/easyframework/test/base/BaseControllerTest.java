package com.easyframework.test.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath:generic-app.xml", "/applicationContext.xml", "classpath:spring-mvc.xml" })
public class BaseControllerTest {
	@Autowired
	private WebApplicationContext wac;
	
	public MockMvc mockMvc;


	@Before
	public void setup() {
		// 如果控制器包含如上方法 则会报空指针
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

}
