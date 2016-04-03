package com.wear.testproject.bean;

import java.io.Serializable;

public class ListenerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String state;
	
	private String listenStatus;
	
	private String info ;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getListenStatus() {
		return listenStatus;
	}

	public void setListenStatus(String listenStatus) {
		this.listenStatus = listenStatus;
	}

}
