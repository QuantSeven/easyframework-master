package com.easyframework.core.service.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.easyframework.common.orm.PropertyFilter;
import com.easyframework.common.ui.DataGrid;
import com.easyframework.common.ui.UIHelper;
import com.easyframework.common.utils.security.Identities;
import com.easyframework.dao.system.UserDao;
import com.easyframework.model.system.User;
import com.google.common.collect.Maps;

import framework.generic.page.Pagination;

@Service("userService")
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User create(User user) {
		user.setId(Identities.uuid());
		return userDao.save(user);
	}

	@Override
	public User modify(User user) {
		User u = userDao.get(user.getId());
		BeanUtils.copyProperties(user, u, new String[] { "description", "phone", "position", "sex", "status", "groups", "roles" });
		return userDao.update(u);
	}

	@Override
	public User get(String id) {
		return userDao.get(id);
	}

	@Override
	public User getUserByLoginName(String username) {
		String hql = "FROM User u WHERE u.username =:username";
		Map<String, String> paramMap = Maps.newHashMap();
		paramMap.put("username", username);
		List<User> lst = userDao.find(hql, paramMap);
		if (lst != null && !lst.isEmpty()) {
			return lst.get(0);
		}
		return null;
	}

	@Override
	public DataGrid<User> getDataGrid(UIHelper uiHelper, Map<String, Object> paramMap) {
		String hql = "FROM User u";
		Pagination<User> pagination = new Pagination<User>();
		pagination.setPageNo(uiHelper.getPage());
		pagination.setPageSize(uiHelper.getRows());
		pagination = userDao.findPage(hql, pagination, paramMap);
		return new DataGrid<User>(pagination.getTotalCount(), pagination.getResult());
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public DataGrid<User> getDataGrid(UIHelper uiHelper, List<PropertyFilter> filters) {
		Pagination<User> pagination = new Pagination<User>();
		pagination.setPageNo(uiHelper.getPage());
		pagination.setPageSize(uiHelper.getRows());
		pagination = userDao.findPage(pagination, filters);
		return new DataGrid<User>(pagination.getTotalCount(), pagination.getResult());
	}

}
