package com.wear.testproject.impl;

import com.wear.testproject.bean.AlarmComponment;
import com.wear.testproject.bean.NewAlarmBean;
import com.wear.testproject.bean.SlienTimeBean;

public interface IAlarmListener {
	
	public void queryAlarmList(AlarmComponment componment);
	
	public void newAlarm(NewAlarmBean bean);
	
	public void updateAlarm(NewAlarmBean bean);
	
	public void deleteAlarm(NewAlarmBean bean);
	
	public void searchSerialParm(SlienTimeBean bean);
	
	public void serialNumAction(SlienTimeBean bean);

}
