package com.wear.testproject.bean;

import java.io.Serializable;

public class SerialNumberComponment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String info  ;
	
	private int state ;
	
	private SerialNumber data ;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public SerialNumber getData() {
		return data;
	}

	public void setData(SerialNumber data) {
		this.data = data;
	}

}
