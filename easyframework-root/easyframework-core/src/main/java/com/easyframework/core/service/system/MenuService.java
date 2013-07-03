package com.easyframework.core.service.system;

import java.util.List;

import com.easyframework.model.system.Menu;

public interface MenuService {

	List<Menu> getLeftMenus();

	List<Menu> getTreeMenus();
}
