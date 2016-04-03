package com.wear.testproject.bean;

import java.io.Serializable;

public class LbsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2435391498750761575L;
	
	private int state ;
	
	private String lbs ;
	
	private String gps ;
	
	private String info ;

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

	public String getLbs() {
		return lbs;
	}

	public void setLbs(String lbs) {
		this.lbs = lbs;
	}

	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}
}
	
