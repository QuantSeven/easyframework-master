package com.easyframework.common.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * EasyUI DataGrid 辅助类
 * 
 * @author QuantSeven 2013-6-20 上午9:16:47
 */
public class DataGrid<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = -5708823637950957324L;

	private Long total = 0L;
	private List<T> rows = new ArrayList<T>();

	public DataGrid() {
	}

	public DataGrid(Long total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
