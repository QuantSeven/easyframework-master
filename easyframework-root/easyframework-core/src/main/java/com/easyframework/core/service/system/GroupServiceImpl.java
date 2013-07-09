package com.easyframework.core.service.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.easyframework.common.orm.PropertyFilter;
import com.easyframework.common.ui.DataGrid;
import com.easyframework.common.ui.PageHelper;
import com.easyframework.common.utils.dozer.DozerUtil;
import com.easyframework.common.utils.security.Identities;
import com.easyframework.dao.system.GroupDao;
import com.easyframework.model.system.Group;
import com.easyframework.vo.system.GroupVO;

import framework.generic.page.Pagination;

@Service("groupService")
public class GroupServiceImpl implements GroupService {

	private GroupDao groupDao;

	@Resource
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public DataGrid<GroupVO> getDataGrid(PageHelper pageHelper, List<PropertyFilter> filters) {
		Pagination<Group> pagination = new Pagination<Group>();
		pagination.setPageNo(pageHelper.getPage());
		pagination.setPageSize(pageHelper.getRows());
		pagination = groupDao.findPage(pagination, filters);
		List<Group> groups = pagination.getResult();
		List<GroupVO> groupVos = DozerUtil.mapList(groups, GroupVO.class);
		return new DataGrid<GroupVO>(pagination.getTotalCount(), groupVos);
	}

	@Override
	public GroupVO get(String id) {
		Group group = groupDao.get(id);
		GroupVO groupVo = DozerUtil.map(group, GroupVO.class);
		return groupVo;
	}

	@Override
	public GroupVO create(GroupVO group) {
		group.setId(Identities.uuid());
		Group entity = DozerUtil.map(group, Group.class);
		entity = groupDao.save(entity);
		GroupVO groupVo = DozerUtil.map(entity, GroupVO.class);
		return groupVo;
	}

	@Override
	public GroupVO modify(GroupVO group) {
		Group entity = groupDao.get(group.getId());
		BeanUtils.copyProperties(group, entity, new String[] { "users", "roles" });
		entity = groupDao.update(entity);
		GroupVO groupVo = DozerUtil.map(entity, GroupVO.class);
		return groupVo;
	}

	@Override
	public void delete(GroupVO group) {
		Group entity = DozerUtil.map(group, Group.class);
		groupDao.delete(entity);
	}

}
