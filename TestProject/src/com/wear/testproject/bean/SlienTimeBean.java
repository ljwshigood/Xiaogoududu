package com.wear.testproject.bean;

import java.io.Serializable;
import java.util.List;

public class SlienTimeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int state ;
	
	private String info ;
	
	private List<SlienceTime> data;

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

	public List<SlienceTime> getData() {
		return data;
	}

	public void setData(List<SlienceTime> data) {
		this.data = data;
	}
	

}
