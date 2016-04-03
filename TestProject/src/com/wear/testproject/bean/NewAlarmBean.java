package com.wear.testproject.bean;

import java.io.Serializable;

public class NewAlarmBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int state;
	private String info;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
