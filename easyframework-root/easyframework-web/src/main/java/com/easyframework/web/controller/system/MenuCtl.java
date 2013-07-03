package com.easyframework.web.controller.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easyframework.core.service.system.MenuService;
import com.easyframework.model.system.Menu;

@Controller
@RequestMapping("menu/*")
public class MenuCtl {

	private MenuService menuService;

	@Resource
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	@RequestMapping(value = "index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		return "system/menu_index";
	}
	@RequestMapping(value = "leftMenu", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Menu> leftMenu() {
		return menuService.getLeftMenus();
	}

	@RequestMapping(value = "treeMenu", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Menu> treeMenu() {
		return menuService.getTreeMenus();
	}
}
