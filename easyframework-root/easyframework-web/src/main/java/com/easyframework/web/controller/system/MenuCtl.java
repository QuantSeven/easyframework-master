package com.easyframework.web.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easyframework.common.ui.JsonModel;
import com.easyframework.common.utils.string.StringUtils;
import com.easyframework.core.service.system.MenuService;
import com.easyframework.model.system.Menu;
import com.easyframework.web.controller.AbstractCtl;

@Controller
@RequestMapping("menu/*")
public class MenuCtl extends AbstractCtl {

	private static Logger logger = LoggerFactory.getLogger(MenuCtl.class);

	private MenuService menuService;

	@Resource
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	/*
	 * 导航菜单
	 */
	@RequestMapping(value = "leftMenu", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Menu> leftMenu() {
		return menuService.getLeftMenus();
	}
	
	
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index() {
		if (SecurityUtils.getSubject().isPermitted("menu:view")) {
			System.out.println("aaaaaaaaa");
		}
		return "system/menu_index";
	}

	@RequestMapping(value = "tree", method = { RequestMethod.POST })
	@ResponseBody
	public List<Menu> tree(HttpServletRequest request) {
		return menuService.getTreeMenus();
	}

	@RequestMapping(value = "form", method = { RequestMethod.GET })
	public String form(Model model, @RequestParam(value = "id", defaultValue = "") String id) {
		if (!StringUtils.isNullOrEmpty(id)) { // 如果存在id,说明是修改
			//Menu menu = menuService.get(id);
		//	model.addAttribute("menu", menu);
		}
		model.addAttribute("action", "menu/saveOrUpdate");
		return "system/menu_form";
	}

	@RequestMapping(value = "saveOrUpdate", method = { RequestMethod.POST })
	@ResponseBody
	public JsonModel<Menu> saveOrUpdate(HttpServletRequest request, Menu menu) {
		if (!StringUtils.isNullOrEmpty(menu) && !StringUtils.isNullOrEmpty(menu.getId())) {
			return update(menu);
		}
		return insert(menu);
	}

	public JsonModel<Menu> insert(Menu menu) {
		JsonModel<Menu> jsonModel = new JsonModel<Menu>();
		try {
			//menu = menuService.create(menu);
			jsonModel.setMsg(getMessage("common.msg.insert.success"));
		} catch (Exception e) {
			jsonModel.setMsg(getMessage("common.msg.insert.error") + "\n" + e.getMessage());
			logger.error(e.getMessage());
		}
		return jsonModel;
	}

	public JsonModel<Menu> update(Menu menu) {
		JsonModel<Menu> jsonModel = new JsonModel<Menu>();
		try {
		//	menu = menuService.modify(menu);
			jsonModel.setMsg(getMessage("common.msg.update.success"));
		} catch (Exception e) {
			jsonModel.setMsg(getMessage("common.msg.update.error") + "\n" + e.getMessage());
			logger.error(e.getMessage());
		}
		return jsonModel;
	}

	@RequestMapping(value = "delete", method = { RequestMethod.POST })
	@ResponseBody
	public JsonModel<Menu> delete(HttpServletRequest request, @RequestBody Menu menu) {
		JsonModel<Menu> jsonModel = new JsonModel<Menu>();
		try {
			//menuService.delete(menu);
			jsonModel.setMsg(getMessage("common.msg.delete.success"));
		} catch (Exception e) {
			jsonModel.setMsg(getMessage("common.msg.delete.error") + "\n" + e.getMessage());
			logger.error(e.getMessage());
		}
		return jsonModel;
	}
}
