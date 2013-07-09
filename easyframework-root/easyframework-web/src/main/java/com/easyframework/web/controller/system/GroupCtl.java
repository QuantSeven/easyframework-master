package com.easyframework.web.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.easyframework.common.ui.PageHelper;
import com.easyframework.common.utils.string.StringUtils;
import com.easyframework.core.service.system.GroupService;
import com.easyframework.vo.system.GroupVO;
import com.easyframework.web.controller.AbstractCtl;

@Controller
@RequestMapping("group/*")
public class GroupCtl extends AbstractCtl {

	private static Logger logger = LoggerFactory.getLogger(GroupCtl.class);

	private GroupService groupService;

	@Resource
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index() {
		return "system/group_index";
	}

	@RequestMapping(value = "list", method = { RequestMethod.POST })
	@ResponseBody
	public DataGrid<GroupVO> list(HttpServletRequest request, PageHelper pageHelper) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		return groupService.getDataGrid(pageHelper, filters);
	}

	@RequestMapping(value = "form", method = { RequestMethod.GET })
	public String form(Model model, @RequestParam(value = "id", defaultValue = "") String id) {
		if (!StringUtils.isNullOrEmpty(id)) { // 如果存在id,说明是修改
			GroupVO group = groupService.get(id);
			model.addAttribute("group", group);
		}
		model.addAttribute("action", "group/saveOrUpdate");
		return "system/group_form";
	}

	@RequestMapping(value = "saveOrUpdate", method = { RequestMethod.POST })
	@ResponseBody
	public JsonModel<GroupVO> saveOrUpdate(HttpServletRequest request, GroupVO group) {
		if (!StringUtils.isNullOrEmpty(group) && !StringUtils.isNullOrEmpty(group.getId())) {
			return update(group);
		}
		return insert(group);
	}

	public JsonModel<GroupVO> insert(GroupVO group) {
		JsonModel<GroupVO> jsonModel = new JsonModel<GroupVO>();
		try {
			group = groupService.create(group);
			jsonModel.setMsg(getMessage("common.msg.insert.success"));
		} catch (Exception e) {
			jsonModel.setMsg(getMessage("common.msg.insert.error") + "\n" + e.getMessage());
			logger.error(e.getMessage());
		}
		return jsonModel;
	}

	public JsonModel<GroupVO> update(GroupVO group) {
		JsonModel<GroupVO> jsonModel = new JsonModel<GroupVO>();
		try {
			group = groupService.modify(group);
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
	public JsonModel<GroupVO> delete(HttpServletRequest request, @RequestBody GroupVO group) {
		JsonModel<GroupVO> jsonModel = new JsonModel<GroupVO>();
		try {
			groupService.delete(group);
			jsonModel.setMsg(getMessage("common.msg.delete.success"));
		} catch (Exception e) {
			jsonModel.setMsg(getMessage("common.msg.delete.error") + "\n" + e.getMessage());
			logger.error(e.getMessage());
		}
		return jsonModel;
	}
}
