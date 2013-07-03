package com.easyframework.dao.system;

import org.springframework.stereotype.Repository;

import com.easyframework.model.system.Role;

import framework.generic.dao.GenericHibernateDaoImpl;

@Repository("roleDao")
public class RoleDaoImpl extends GenericHibernateDaoImpl<Role, String> implements RoleDao {

}
