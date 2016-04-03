package com.wear.testproject.bean;

import java.io.Serializable;

public class NotifyBattery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String battery;
	
	private String  fupdatetime;
	
	private String serialNumber;

	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public String getFupdatetime() {
		return fupdatetime;
	}

	public void setFupdatetime(String fupdatetime) {
		this.fupdatetime = fupdatetime;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	
}
