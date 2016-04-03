package com.wear.testproject.bean;

import java.io.Serializable;
import java.util.List;

public class TrackBeanComponment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2165697266238556859L;
	
	private int state;
	
	private String info ;
	
	private List<TrackBean> data ;

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

	public List<TrackBean> getData() {
		return data;
	}

	public void setData(List<TrackBean> data) {
		this.data = data;
	}
	
	
	
}
