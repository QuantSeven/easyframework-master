package com.easyframework.dao.system;

import org.springframework.stereotype.Repository;

import com.easyframework.model.system.Permission;

import framework.generic.dao.GenericHibernateDaoImpl;

@Repository("permissionDao")
public class PermissionDaoImpl extends GenericHibernateDaoImpl<Permission, String> implements PermissionDao {

}
