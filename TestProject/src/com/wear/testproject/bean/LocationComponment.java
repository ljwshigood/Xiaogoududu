package com.wear.testproject.bean;

import java.io.Serializable;
import java.util.List;

public class LocationComponment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int state ;
	
	private String info ;
	
	private List<LocationBean> data ;

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

	public List<LocationBean> getData() {
		return data;
	}

	public void setData(List<LocationBean> data) {
		this.data = data;
	}
	
}
