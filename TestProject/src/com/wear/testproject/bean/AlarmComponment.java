package com.wear.testproject.bean;

import java.io.Serializable;
import java.util.List;

public class AlarmComponment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int state;
	
	private String info;

	private List<AlarmBean> data;

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

	public List<AlarmBean> getAlarmList() {
		return data;
	}

	public void setAlarmList(List<AlarmBean> alarmList) {
		this.data = alarmList;
	}

}
