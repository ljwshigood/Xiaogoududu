package com.wear.testproject.impl;

import com.wear.testproject.bean.MoveComponment;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.TrackBeanComponment;

public interface IElectFenceListener {
	
	public void searchElectFence(MoveComponment bean);
	
	public void searchMovement(TrackBeanComponment bean);
	
	public void addElectFence(ResultBean bean);
	
	public void modifyElectFence(ResultBean bean);
	
	public void deleteElectFence(ResultBean bean);
	
}
