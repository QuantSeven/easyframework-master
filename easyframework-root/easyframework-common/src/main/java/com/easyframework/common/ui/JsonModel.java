package com.easyframework.common.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.easyframework.common.constant.MessageStatus;

public class JsonModel<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = -32336840010189686L;

	private boolean success = false; // 是否成功
	private String msg = ""; // 返回消息
	private Integer statusCode = MessageStatus.OK; // 状态 默认才横杠、、、
	private String forwardUrl = ""; // 跳转url
	private T object = null; // 实体对象
	private List<T> dataList = new ArrayList<T>(0);// 实体对象列表

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

}
