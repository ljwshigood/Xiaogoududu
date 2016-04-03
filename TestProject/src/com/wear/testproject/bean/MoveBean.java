package com.wear.testproject.bean;

import java.io.Serializable;

public class MoveBean implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	private String createTime;
	private int id;
	private String locationbd;
	private String locationgd;
	private String model;
	private String name;
	private int scope;
	private String serialNumber;
	private String status;
	private String updateTime;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocationbd() {
		return locationbd;
	}

	public void setLocationbd(String locationbd) {
		this.locationbd = locationbd;
	}

	public String getLocationgd() {
		return locationgd;
	}

	public void setLocationgd(String locationgd) {
		this.locationgd = locationgd;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
