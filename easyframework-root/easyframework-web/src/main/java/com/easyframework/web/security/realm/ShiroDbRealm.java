package com.easyframework.web.security.realm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.easyframework.core.service.system.PermissionService;
import com.easyframework.core.service.system.UserService;
import com.easyframework.model.system.Group;
import com.easyframework.model.system.Permission;
import com.easyframework.model.system.Role;
import com.easyframework.model.system.User;
import com.google.common.collect.Lists;

public class ShiroDbRealm extends AuthorizingRealm {

	private UserService userService;
	private PermissionService permissionService;

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Resource
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	protected Set<Role> userRoles;
	protected Set<com.easyframework.model.system.Resource> resources;

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = userService.getUserByLoginName(token.getUsername());
		if (user != null) {
			List<com.easyframework.model.system.Resource> resourceList = Lists.newArrayList();
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			Set<Role> userRoles = user.getRoles();
			for (Role role : userRoles) {
				info.addRole(role.getName());
				resources = role.getResources();
				for (com.easyframework.model.system.Resource resource : resources) {
					resourceList.add(resource);
				}
			}
			Set<Group> userGroups = user.getGroups();
			for (Group group : userGroups) {
				userRoles = group.getRoles();
				for (Role role : userRoles) {
					info.addRole(role.getName());
					resources = role.getResources();
					for (com.easyframework.model.system.Resource resource : resources) {
						resourceList.add(resource);
					}
				}
			}

			// 将该用户的所有资源进行排序排列
			Collections.sort(resourceList, new Comparator<com.easyframework.model.system.Resource>() {
				public int compare(com.easyframework.model.system.Resource o1, com.easyframework.model.system.Resource o2) {
					return o1.getId().compareTo(o2.getId());
				}

			});
			resourceList = removeDuplicate(resourceList);
			SecurityUtils.getSubject().getSession().setAttribute("resourceList", resourceList);
			return new SimpleAuthenticationInfo(user, user.getPassword(), null, getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<String> actions = Lists.newArrayList();
		if (user != null) {
			Set<Role> userRoles = user.getRoles();
			for (Role role : userRoles) {
				info.addRole(role.getName());
				resources = role.getResources();
				for (com.easyframework.model.system.Resource resource : resources) {
					Permission permission = permissionService.findByRoleIdAndResouceId(role.getId(), resource.getId());
					if (permission != null && permission.getActions() != null && !"".equals(permission.getActions())) {
						String[] actionString = permission.getActions().split(",");
						actions.addAll(Arrays.asList(actionString));
					}
				}
			}
			for (Group group : user.getGroups()) {
				userRoles = group.getRoles();
				for (Role role : userRoles) {
					resources = role.getResources();
					info.addRole(role.getName());
					for (com.easyframework.model.system.Resource resource : resources) {
						Permission permission = permissionService.findByRoleIdAndResouceId(role.getId(), resource.getId());
						if (permission != null && permission.getActions() != null && !"".equals(permission.getActions())) {
							String[] actionString = permission.getActions().split(",");
							actions.addAll(Arrays.asList(actionString));
						}
					}
				}
			}
			actions = removeDuplicate(actions);
			info.addStringPermissions(actions);
			return info;
		}
		return null;
	}

	private List removeDuplicate(List list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		return newList;
	}
}
