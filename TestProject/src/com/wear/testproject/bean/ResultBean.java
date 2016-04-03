package com.wear.testproject.bean;

import java.io.Serializable;

public class ResultBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String state;
	
	private String info;
	
	private String usrid;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getUsrid() {
		return usrid;
	}

	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}
}
