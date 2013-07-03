package com.easyframework.core.service.system;

import java.util.List;
import java.util.Map;

import com.easyframework.common.orm.PropertyFilter;
import com.easyframework.common.ui.DataGrid;
import com.easyframework.common.ui.UIHelper;
import com.easyframework.model.system.User;

public interface UserService {

	public User create(User user);

	public User modify(User user);

	public void delete(User user);

	public User get(String id);

	public User getUserByLoginName(String username);

	public DataGrid<User> getDataGrid(UIHelper uiHelper, Map<String, Object> paramMap);
	
	public DataGrid<User> getDataGrid(UIHelper uiHelper, List<PropertyFilter> filters);
}
