package com.wear.testproject.bean;

import java.io.Serializable;

public class pushBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int state;
	
	public String info;
	
	public String alias;
	
	public String tag;
	
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	

}
