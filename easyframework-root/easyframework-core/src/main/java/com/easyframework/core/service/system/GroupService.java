package com.easyframework.core.service.system;

import java.util.List;

import com.easyframework.common.orm.PropertyFilter;
import com.easyframework.common.ui.DataGrid;
import com.easyframework.common.ui.PageHelper;
import com.easyframework.vo.system.GroupVO;

public interface GroupService {

	public DataGrid<GroupVO> getDataGrid(PageHelper pageHelper, List<PropertyFilter> filters);

	public GroupVO get(String id);

	public GroupVO create(GroupVO group);

	public GroupVO modify(GroupVO group);

	public void delete(GroupVO group);
}
