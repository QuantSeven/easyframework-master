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

import com.easyframework.common.orm.PropertyFilter;
import com.easyframework.common.ui.DataGrid;
import com.easyframework.common.ui.JsonModel;
import com.easyframework.common.ui.UIHelper;
import com.easyframework.common.utils.string.StringUtils;
import com.easyframework.core.service.system.UserService;
import com.easyframework.model.system.User;
import com.easyframework.web.controller.AbstractCtl;

@Controller
@RequestMapping("user/*")
public class UserCtl extends AbstractCtl {

	private static Logger logger = LoggerFactory.getLogger(UserCtl.class);

	private UserService userService;

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index() {
		if (SecurityUtils.getSubject().isPermitted("user:view")) {
			System.out.println("aaaaaaaaa");
		}
		return "system/user_index";
	}

	@RequestMapping(value = "list", method = { RequestMethod.POST })
	@ResponseBody
	public DataGrid<User> list(HttpServletRequest request, UIHelper uiHelper) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		return userService.getDataGrid(uiHelper, filters);
	}

	@RequestMapping(value = "form", method = { RequestMethod.GET })
	public String form(Model model, @RequestParam(value = "id", defaultValue = "") String id) {
		if (!StringUtils.isNullOrEmpty(id)) { // 如果存在id,说明是修改
			User user = userService.get(id);
			model.addAttribute("user", user);
		}
		model.addAttribute("action", "user/saveOrUpdate");
		return "system/user_form";
	}

	@RequestMapping(value = "saveOrUpdate", method = { RequestMethod.POST })
	@ResponseBody
	public JsonModel<User> saveOrUpdate(HttpServletRequest request, User user) {
		if (!StringUtils.isNullOrEmpty(user) && !StringUtils.isNullOrEmpty(user.getId())) {
			return update(user);
		}
		return insert(user);
	}

	public JsonModel<User> insert(User user) {
		JsonModel<User> jsonModel = new JsonModel<User>();
		try {
			user = userService.create(user);
			jsonModel.setMsg(getMessage("common.msg.insert.success"));
		} catch (Exception e) {
			jsonModel.setMsg(getMessage("common.msg.insert.error") + "\n" + e.getMessage());
			logger.error(e.getMessage());
		}
		return jsonModel;
	}

	public JsonModel<User> update(User user) {
		JsonModel<User> jsonModel = new JsonModel<User>();
		try {
			user = userService.modify(user);
			jsonModel.setMsg(getMessage("common.msg.update.success"));
		} catch (Exception e) {
			jsonModel.setSuccess(false);
			jsonModel.setMsg(getMessage("common.msg.update.error") + "\n" + e.getMessage());
			logger.error(e.getMessage());
		}
		return jsonModel;
	}

	@RequestMapping(value = "delete", method = { RequestMethod.POST })
	@ResponseBody
	public JsonModel<User> delete(HttpServletRequest request, @RequestBody User user) {
		JsonModel<User> jsonModel = new JsonModel<User>();
		try {
			userService.delete(user);
			jsonModel.setMsg(getMessage("common.msg.delete.success"));
		} catch (Exception e) {
			jsonModel.setMsg(getMessage("common.msg.delete.error") + "\n" + e.getMessage());
			logger.error(e.getMessage());
		}
		return jsonModel;
	}
}
