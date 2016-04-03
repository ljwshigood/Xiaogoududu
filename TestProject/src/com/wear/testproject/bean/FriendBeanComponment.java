package com.wear.testproject.bean;

import java.io.Serializable;
import java.util.List;

public class FriendBeanComponment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int state ;
	
	private String info ;
	
	private List<FriendsBean> data ;

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

	public List<FriendsBean> getData() {
		return data;
	}

	public void setData(List<FriendsBean> data) {
		this.data = data;
	}
	
	

}
