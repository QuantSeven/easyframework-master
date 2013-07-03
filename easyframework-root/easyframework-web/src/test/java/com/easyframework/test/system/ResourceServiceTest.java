package com.easyframework.test.system;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.easyframework.common.utils.json.JsonMapper;
import com.easyframework.core.service.system.ResourceService;
import com.easyframework.model.system.Menu;
import com.easyframework.test.base.BaseServiceTest;

public class ResourceServiceTest extends BaseServiceTest {

	
	private ResourceService resourceService;

	@Resource
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	@Test
	public void testFindMenus(){
		for(Menu rs : resourceService.findMenus()){
			
			System.out.println(JsonMapper.toJson(rs));
		}
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
