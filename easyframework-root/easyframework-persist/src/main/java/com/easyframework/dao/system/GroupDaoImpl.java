package com.easyframework.dao.system;

import org.springframework.stereotype.Repository;

import com.easyframework.model.system.Group;

import framework.generic.dao.GenericHibernateDaoImpl;

@Repository("groupDao")
public class GroupDaoImpl extends GenericHibernateDaoImpl<Group, String> implements GroupDao {

}
