package com.wear.testproject.impl;

import com.wear.testproject.bean.DeviceInfoBean;
import com.wear.testproject.bean.LocationComponment;
import com.wear.testproject.bean.NumUsrRelation;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.SerialNumberComponment;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.bean.pushBean;

public interface IAccountManager {
	
	public void pushAndroid(pushBean bean);
	
	public void getSerialNum(SerialNumberComponment bean);
	
	public void userUpdate(ResultBean bean);
	
	public void deviceInfoPhoto(DeviceInfoBean bean);
	
	public void actionSerialView(NumUsrRelation bean);
	
	public void uupdateSerialView(ResultBean bean);
	
	public void loginSuccess(UserInfoBean bean);
	
	public void registerSuccess(ResultBean bean);
	
	public void searchLoaUsrid(LocationComponment componment);
	
	public void uploadFile(UserInfoBean bean);		

}
