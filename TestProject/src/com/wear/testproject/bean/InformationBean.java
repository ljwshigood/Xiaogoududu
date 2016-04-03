package com.wear.testproject.bean;

import java.io.Serializable;
import java.util.List;

public class InformationBean implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private List<SerialNumber> serialNumber ;
	
	private	List<NotifyFecnce> notifyFecnce ;
	
	private List<NofityDetach> nofityDetach ;
	
	private List<NotifyFee> notifyFee ;
	
	private List<NotifyBattery> notifyBattery ;

	public List<SerialNumber> getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(List<SerialNumber> serialNumber) {
		this.serialNumber = serialNumber;
	}

	public List<NotifyFecnce> getNotifyFecnce() {
		return notifyFecnce;
	}

	public void setNotifyFecnce(List<NotifyFecnce> notifyFecnce) {
		this.notifyFecnce = notifyFecnce;
	}

	public List<NofityDetach> getNofityDetach() {
		return nofityDetach;
	}

	public void setNofityDetach(List<NofityDetach> nofityDetach) {
		this.nofityDetach = nofityDetach;
	}

	public List<NotifyFee> getNotifyFee() {
		return notifyFee;
	}

	public void setNotifyFee(List<NotifyFee> notifyFee) {
		this.notifyFee = notifyFee;
	}

	public List<NotifyBattery> getNotifyBattery() {
		return notifyBattery;
	}

	public void setNotifyBattery(List<NotifyBattery> notifyBattery) {
		this.notifyBattery = notifyBattery;
	}
	
}
