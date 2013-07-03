package com.easyframework.web.controller.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easyframework.core.service.system.ResourceService;
import com.easyframework.model.system.Menu;

@Controller
@RequestMapping("res")
public class ResourceCtl {

	private ResourceService resourceService;

	@Resource
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	@RequestMapping(value = "menu", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Menu> getMenus() {
		
		return resourceService.findMenus();
	}
}
