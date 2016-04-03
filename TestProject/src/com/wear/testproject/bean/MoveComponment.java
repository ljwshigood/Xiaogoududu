package com.wear.testproject.bean;

import java.io.Serializable;
import java.util.List;

public class MoveComponment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2159671639618905308L;
	
	private int state ;
	
	private String info ;
	
	private SerialNumber SerialNum ;
	
	public SerialNumber getSerialNum() {
		return SerialNum;
	}

	public void setSerialNum(SerialNumber serialNum) {
		SerialNum = serialNum;
	}

	public List<MoveBean> getData() {
		return data;
	}

	public void setData(List<MoveBean> data) {
		this.data = data;
	}

	private List<MoveBean> data ;
	
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

	
}
