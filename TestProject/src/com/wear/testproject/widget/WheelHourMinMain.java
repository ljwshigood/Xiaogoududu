package com.wear.testproject.widget;

import android.view.View;

import com.wear.testproject.R;
import com.wear.testproject.wheel.NumericWheelAdapter;
import com.wear.testproject.wheel.WheelView;


public class WheelHourMinMain {

	private View view;
	
	private WheelView wv_hours;
	
	private WheelView wv_mins;
	
	public int screenheight;
	
	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}
	
	public WheelHourMinMain(View view) {
		super();
		this.view = view;
		setView(view);
	}
	public WheelHourMinMain(View view,boolean hasSelectTime) {
		super();
		this.view = view;
		setView(view);
	}
	
	/**
	 * 
	 */
	public void initDateTimePicker(int h,int m) {

		wv_hours = (WheelView)view.findViewById(R.id.hour);
		wv_mins = (WheelView)view.findViewById(R.id.mins);
		
		wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
		wv_hours.setCyclic(true);// 可循环滚动
		//wv_hours.setLabel("时");// 添加文字
		wv_hours.setCurrentItem(h);
		
		
		wv_mins.setAdapter(new NumericWheelAdapter(0, 59));
		wv_mins.setCyclic(true);// 可循环滚动
	//	wv_mins.setLabel("分");// 添加文字
		wv_mins.setCurrentItem(m);

		int textSize = 0;
	
		textSize = 60;
		wv_hours.TEXT_SIZE = textSize;
		wv_mins.TEXT_SIZE = textSize;

	}

	public String getTime() {
		StringBuffer sb = new StringBuffer();
		if(Integer.valueOf(wv_hours.getCurrentItem()) > 10){
			sb.append(wv_hours.getCurrentItem()).append(":");	
		}else{
			sb.append("0"+wv_hours.getCurrentItem()).append(":");
		}
		
		if(Integer.valueOf(wv_mins.getCurrentItem()) > 10 ){
			sb.append(wv_mins.getCurrentItem());	
		}else{
			sb.append("0"+wv_mins.getCurrentItem());
		}
		return sb.toString();
	}
}
