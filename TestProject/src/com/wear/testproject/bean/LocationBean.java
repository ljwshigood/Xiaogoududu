package com.wear.testproject.bean;

import java.io.Serializable;

public class LocationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String battery ;
	
	private String address;
	
	private String latitude;
	
	private String longitude;
	
	private String snnumber;
	
	private String fupdatetime;
	
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getSnnumber() {
		return snnumber;
	}
	public void setSnnumber(String snnumber) {
		this.snnumber = snnumber;
	}
	public String getFupdatetime() {
		return fupdatetime;
	}
	public void setFupdatetime(String fupdatetime) {
		this.fupdatetime = fupdatetime;
	}
	
	

}
