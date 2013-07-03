package com.easyframework.model.system;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 菜单管理类
 *
 * @author QuantSeven
 * 2013-6-17 下午1:33:41
 */
public class Menu implements java.io.Serializable {
	private static final long serialVersionUID = -363872729247021817L;
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
	private String parentId;
	private String parentName;

	private List<Menu> children = Lists.newArrayList();
	public Menu() {
	}

	public Menu(String id, String name, String resourceType, String url, String icon,String iconOpen, String isOpen,String isLeaf,String status, Integer sort, String description) {
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

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getIconOpen() {
		return iconOpen;
	}

	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}
	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}
}