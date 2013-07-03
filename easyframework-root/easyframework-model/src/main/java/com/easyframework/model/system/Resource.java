package com.easyframework.model.system;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * 资源信息实体类
 * 
 * @author QuantSeven 2013-6-14 下午1:16:49
 */
@Entity
@Table(name = "SYS_RESOURCE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Resource implements java.io.Serializable {
	private static final long serialVersionUID = 2377931150989285007L;
	private String id;
	private String name;
	private String resourceType;
	private String url;
	private String icon;
	private String iconCls;
	private String iconOpen;
	private String isOpen;
	private String isLeaf;
	private String status;
	private Integer sort;
	private String description;

	private Resource parent;
	private Set<Resource> children = new HashSet<Resource>();

	public Resource() {
	}

	public Resource(String id, String name, String resourceType, String url, String icon, String iconOpen, String isOpen, String isLeaf, String status, Integer sort, String description) {
		super();
		this.id = id;
		this.name = name;
		this.resourceType = resourceType;
		this.url = url;
		this.icon = icon;
		this.iconOpen = iconOpen;
		this.isOpen = isOpen;
		this.isLeaf = isLeaf;
		this.status = status;
		this.sort = sort;
		this.description = description;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false)
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

	@Column(name = "RESOURCE_TYPE", length = 20)
	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Column(name = "URL", length = 200)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "ICON", length = 200)
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "ICON_CLS", length = 50)
	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Column(name = "ICON_OPEN", length = 10)
	public String getIconOpen() {
		return iconOpen;
	}

	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}

	@Column(name = "IS_OPEN", length = 10)
	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	@Column(name = "IS_LEAF", length = 10)
	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	@Column(name = "STATUS", length = 4)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "SORT", length = 4)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "DESCRIPTION", columnDefinition = "text")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	@JsonBackReference
	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "parent")
	@JsonManagedReference
	public Set<Resource> getChildren() {
		return children;
	}

	public void setChildren(Set<Resource> children) {
		this.children = children;
	}

}