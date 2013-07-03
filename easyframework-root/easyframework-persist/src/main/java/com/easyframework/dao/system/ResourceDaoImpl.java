package com.easyframework.dao.system;

import org.springframework.stereotype.Repository;

import com.easyframework.model.system.Resource;

import framework.generic.dao.GenericHibernateDaoImpl;

@Repository("resourceDao")
public class ResourceDaoImpl extends GenericHibernateDaoImpl<Resource, String> implements ResourceDao {

}
