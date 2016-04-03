package com.wear.testproject.bean;

import java.io.Serializable;

public class RtpBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int state ;
	
	private double lng ;
	
	private double lat ;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}
}
