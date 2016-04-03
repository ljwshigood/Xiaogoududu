package com.wear.testproject.bean;

import java.io.Serializable;

public class TrackBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1761040366515621450L;
	
	private String battery;
	
	private String faddress;
	
	private String lat;
	
	private String lon;
	
	private String fpicture;
	
	private String time;
	
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}
	public String getFaddress() {
		return faddress;
	}
	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getFpicture() {
		return fpicture;
	}
	public void setFpicture(String fpicture) {
		this.fpicture = fpicture;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	

}
