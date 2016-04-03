package com.wear.testproject.bean;

import java.io.Serializable;
import java.util.List;

public class FriendsComponment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String info  ;
	
	private int state ;
	
	private List<SerialNumber> SerialNumber ;

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

	public  List<SerialNumber> getData() {
		return SerialNumber;
	}

	public void setData(List<SerialNumber> data) {
		this.SerialNumber = data;
	}

}
