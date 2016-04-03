package com.wear.testproject.impl;

import com.wear.testproject.bean.InformationBean;

public interface IInfomationManager {
	
	public void queryMegCenterNotifyFence(InformationBean bean) ;
	
	public void queryMegCenterNotifyDetach(InformationBean bean) ;
	
	public void queryMegCenterNotifyFee(InformationBean bean) ;
	
	public void queryMegCenterNotifyBattery(InformationBean bean) ;
	
	public void queryMegCenter(InformationBean bean) ;
	
	
}
