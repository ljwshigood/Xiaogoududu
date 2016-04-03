package com.wear.testproject.bean;

import java.io.Serializable;

public class InfoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int res ; 
	
	private String info ;

	private String detail;

	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
