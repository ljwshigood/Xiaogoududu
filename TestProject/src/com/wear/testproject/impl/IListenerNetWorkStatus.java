package com.wear.testproject.impl;

public interface IListenerNetWorkStatus {
	
	public void startNetWorkRequest(String message);
	
	public void endNetWorkRequest(String message);
	
	public void netWorkError(String message);
	
}
