package com.easyframework.core.service.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.easyframework.common.constant.ResourceType;
import com.easyframework.common.utils.dozer.DozerUtil;
import com.easyframework.dao.system.ResourceDao;
import com.easyframework.model.system.Menu;
import com.google.common.collect.Lists;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	private ResourceDao resourceDao;

	@Resource
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	@Override
	public List<Menu> getLeftMenus() {
		List<com.easyframework.model.system.Resource> resources = (List<com.easyframework.model.system.Resource>) SecurityUtils.getSubject().getSession().getAttribute("resourceList");
		List<Menu> menus = Lists.newArrayList();
		if (null == resources || resources.isEmpty()) {
			return null;
		}
		for (com.easyframework.model.system.Resource rs : resources) {
			if (ResourceType.MENU.equals(rs.getResourceType())) {
				Menu menu = DozerUtil.map(rs, Menu.class);
				if (null == rs.getParent()) {
					menu.setChildren(getSubMenu(resources, menu));
					menus.add(menu);
				} else {
					menu.setParentId(rs.getParent().getId());
				}
			}
		}
		return menus;
	}

	private List<Menu> getSubMenu(List<com.easyframework.model.system.Resource> allList, Menu menu) {
		List<Menu> sub_list = Lists.newArrayList();
		List<com.easyframework.model.system.Resource> list = Lists.newArrayList();
		list.addAll(allList);
		int index = 0;
		Menu tmp;
		while (!list.isEmpty() && index < list.size()) {
			com.easyframework.model.system.Resource rs = list.get(index);
			tmp = DozerUtil.map(rs, Menu.class);
			if (null != rs.getParent()) {
				tmp.setParentId(rs.getParent().getId());
			}
			if (null != tmp.getParentId() && tmp.getParentId().equals(menu.getId())) {
				list.remove(index);
				tmp.setChildren(getSubMenu(list, tmp));
				sub_list.add(tmp);
			} else {
				index++;
			}
		}
		if (sub_list.size() == 0) {
			return null;
		}
		return sub_list;
	}

	@Override
	public List<Menu> getTreeMenus() {
		String hql = "FROM Resource r WHERE r.resourceType='menu'";
		List<com.easyframework.model.system.Resource> resources = resourceDao.find(hql);
		List<Menu> menus = Lists.newArrayList();
		if (null == resources || resources.isEmpty()) {
			return null;
		}
		for (com.easyframework.model.system.Resource rs : resources) {
			Menu menu = DozerUtil.map(rs, Menu.class);
			if (null == rs.getParent()) {
				menu.setChildren(getSubMenu(resources, menu));
				menus.add(menu);
			}
		}
		return menus;
	}

}
