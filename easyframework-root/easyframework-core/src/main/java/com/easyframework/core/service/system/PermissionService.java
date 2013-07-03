package com.easyframework.core.service.system;

import com.easyframework.model.system.Permission;

public interface PermissionService {

	Permission findByRoleIdAndResouceId(String roleId, String resourceId);
}
