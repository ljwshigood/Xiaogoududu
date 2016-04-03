package com.wear.testproject.bean;

import java.io.Serializable;

public class AlarmBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4970282940577832857L;
	
	private String falarmid;
	
	private int fdatastatus;
	
	private String fname;
	
	private String ftime;
	
	private String hour;
	
	private String minute;
	
	private String week;
	
	public String getFalarmid() {
		return falarmid;
	}
	public void setFalarmid(String falarmid) {
		this.falarmid = falarmid;
	}
	public int getFdatastatus() {
		return fdatastatus;
	}
	public void setFdatastatus(int fdatastatus) {
		this.fdatastatus = fdatastatus;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFtime() {
		return ftime;
	}
	public void setFtime(String ftime) {
		this.ftime = ftime;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	
}
