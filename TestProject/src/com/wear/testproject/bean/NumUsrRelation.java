package com.wear.testproject.bean;

import java.io.Serializable;

public class NumUsrRelation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4510659854474878413L;

	private int state;
	
	private String serialnumid;
	
	private String usrid;

	private String info;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSerialnumid() {
		return serialnumid;
	}

	public void setSerialnumid(String serialnumid) {
		this.serialnumid = serialnumid;
	}

	public String getUsrid() {
		return usrid;
	}

	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	

}
