package com.easyframework.model.system;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "SYS_PERMISSION")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Permission implements Serializable {

	private static final long serialVersionUID = 2424330972392504784L;

	private String id;
	private String roleId;
	private String resourceId;
	private String actions;

	@Id
	@Column(name = "ID", nullable = false, updatable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ROLE_ID", nullable = false)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "RESOURCE_ID", nullable = false)
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	@Column(name = "ACTIONS")
	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

}
