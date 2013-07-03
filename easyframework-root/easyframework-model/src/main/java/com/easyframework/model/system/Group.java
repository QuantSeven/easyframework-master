package com.easyframework.model.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 组实体类
 * 
 * @author QuantSeven 2013-6-14 上午11:59:38
 */
@Entity
@Table(name = "SYS_GROUP")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Group implements Serializable {
	private static final long serialVersionUID = 3204434198245283805L;
	private String id;
	private String name;
	private String groupType;
	private Integer sort;
	private String parentId;
	private String status;
	private String description;

	private Set<User> users = new HashSet<User>();

	private Set<Role> roles = new HashSet<Role>();

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "GROUP_TYPE", length = 20)
	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	@Column(name = "SORT", length = 4)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "PARENT_ID")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "STATUS", length = 4)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "DESCRIPTION", columnDefinition = "text")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(name = "SYS_USER_GROUP", joinColumns = { @JoinColumn(name = "GROUP_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(name = "SYS_GROUP_ROLE", joinColumns = { @JoinColumn(name = "GROUP_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
