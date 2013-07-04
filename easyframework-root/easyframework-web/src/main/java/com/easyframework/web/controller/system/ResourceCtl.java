package com.easyframework.web.controller.system;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.easyframework.core.service.system.ResourceService;

@Controller
@RequestMapping("res")
public class ResourceCtl {

	private ResourceService resourceService;

	@Resource
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
}
