package com.easyframework.dao.system;

import org.springframework.stereotype.Repository;

import com.easyframework.model.system.User;

import framework.generic.dao.GenericHibernateDaoImpl;

@Repository("userDao")
public class UserDaoImpl extends GenericHibernateDaoImpl<User, String> implements UserDao {

	
}
