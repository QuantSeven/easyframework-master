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

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

	private ResourceDao resourceDao;

	@Resource
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	@Override
	public List<Menu> findMenus() {
		List<com.easyframework.model.system.Resource> resources = (List<com.easyframework.model.system.Resource>)SecurityUtils.getSubject().getSession().getAttribute("resourceList");
		List<Menu> menus = Lists.newArrayList();
		if (null == resources || resources.isEmpty()) {
			return null;
		}
		for (com.easyframework.model.system.Resource rs : resources) {
			if(ResourceType.MENU.equals(rs.getResourceType())){
				Menu menu = DozerUtil.map(rs, Menu.class);
				if (null == rs.getParent()) {
					menu.setChildren(getSubMenu(resources, menu));
					menus.add(menu);
				}else {
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
	/*@Override
	public List<com.easyframework.model.system.Resource> findMenus() {
		String hql = "FROM Resource r WHERE r.resourceType='menu'";
		List<com.easyframework.model.system.Resource> menus = resourceDao.find(hql);
		if (menus != null && !menus.isEmpty()) {
			StringBuffer sb = new StringBuffer();
			for (com.easyframework.model.system.Resource menu : menus) {
				if (menu.getChildren() != null && !menu.getChildren().isEmpty()) {
					sb.append("<div title=\"" + menu.getName() + "\" border=\"false=\"><ul class=\"bigicon menuItem\">");
					for(com.easyframework.model.system.Resource childMenu : menu.getChildren()){
						sb.append("<li>");
						sb.append("<div>");
						sb.append("<a link=\""+childMenu.getUrl()+"\" href=\"#\">");
							sb.append("<span iconcls=\""+childMenu.getIconCls()+"\" class=\"img\">");
								sb.append("<img src=\""+childMenu.getIcon()+"\"/>");
							sb.append("</span>");
							sb.append("<span class=\"nav\">"+childMenu.getName()+"</span>");
						sb.append("</a>");
						sb.append("</div>");
						sb.append("</li>");
					}
					sb.append("</ul></div>");
				}
			}
		}
		return menus;
	}*/

}
