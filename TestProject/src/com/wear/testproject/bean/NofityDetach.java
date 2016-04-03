package com.wear.testproject.bean;

import java.io.Serializable;

public class NofityDetach implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 private String   faddtime;
	 private int   fdatastatus;
	 private String   fdetachinfo;
	 private String    fremark;
	 private String    fupdatetime;
	public String getFaddtime() {
		return faddtime;
	}
	public void setFaddtime(String faddtime) {
		this.faddtime = faddtime;
	}
	public int getFdatastatus() {
		return fdatastatus;
	}
	public void setFdatastatus(int fdatastatus) {
		this.fdatastatus = fdatastatus;
	}
	public String getFdetachinfo() {
		return fdetachinfo;
	}
	public void setFdetachinfo(String fdetachinfo) {
		this.fdetachinfo = fdetachinfo;
	}
	public String getFremark() {
		return fremark;
	}
	public void setFremark(String fremark) {
		this.fremark = fremark;
	}
	public String getFupdatetime() {
		return fupdatetime;
	}
	public void setFupdatetime(String fupdatetime) {
		this.fupdatetime = fupdatetime;
	}
	 
	 
}
