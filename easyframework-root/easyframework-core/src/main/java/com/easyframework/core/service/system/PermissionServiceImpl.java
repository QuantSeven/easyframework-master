package com.easyframework.core.service.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.easyframework.dao.system.PermissionDao;
import com.easyframework.model.system.Permission;
import com.google.common.collect.Maps;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	private PermissionDao permissionDao;

	@Resource
	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

	@Override
	public Permission findByRoleIdAndResouceId(String roleId, String resourceId) {
		String hql = "FROM Permission p WHERE p.roleId=:roleId AND p.resourceId=:resourceId";
		Map<String, String> paramMap = Maps.newHashMap();
		paramMap.put("roleId", roleId);
		paramMap.put("resourceId", resourceId);
		List<Permission> permissions = permissionDao.find(hql, paramMap);
		if (permissions != null && !permissions.isEmpty()) {
			return permissions.get(0);
		}
		return null;
	}

}
