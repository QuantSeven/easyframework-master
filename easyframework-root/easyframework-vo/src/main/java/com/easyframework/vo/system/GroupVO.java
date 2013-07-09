package com.easyframework.vo.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class GroupVO implements Serializable {

	private static final long serialVersionUID = -4436105092102988912L;
	private String id;
	private String name;
	private String groupType;
	private Integer sort;
	private String parentId;
	private String status;
	private String description;
	
	private Set<UserVO> users = new HashSet<UserVO>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<UserVO> getUsers() {
		return users;
	}

	public void setUsers(Set<UserVO> users) {
		this.users = users;
	}
}
