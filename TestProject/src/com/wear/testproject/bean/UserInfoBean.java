package com.wear.testproject.bean;

import java.io.Serializable;
import java.util.List;

public class UserInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3340285896588351538L;
	
	private int state ;

	private String info ;
	
	private AccountInfoBean data ;
	
	private List<SerialNumber> SerialNumber ;
	
	public AccountInfoBean getData() {
		return data;
	}

	public void setData(AccountInfoBean data) {
		this.data = data;
	}

	public List<SerialNumber> getSerialNumber() {
		return SerialNumber;
	}

	public void setSerialNumber(List<SerialNumber> serialNumber) {
		SerialNumber = serialNumber;
	}

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
