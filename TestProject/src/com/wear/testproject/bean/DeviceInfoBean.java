package com.wear.testproject.bean;

import java.io.Serializable;

public class DeviceInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int state;
	
	private String info;
	
	private String usrid;
	
	private String imgurl;
	
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
	public String getUsrid() {
		return usrid;
	}
	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
}
