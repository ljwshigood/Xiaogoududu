package com.wear.testproject.widget;

import android.view.View;

import com.wear.testproject.R;
import com.wear.testproject.wheel.NumericWheelAdapter;
import com.wear.testproject.wheel.WheelView;


public class WheelDoubleHourMinMain {

	private View view;
	
	private WheelView wv_hours_one;
	
	private WheelView wv_mins_one;
	
	private WheelView wv_hours_two;
	
	private WheelView wv_mins_two;
	
	public int screenheight;
	
	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}
	
	public WheelDoubleHourMinMain(View view) {
		super();
		this.view = view;
		setView(view);
	}
	public WheelDoubleHourMinMain(View view,boolean hasSelectTime) {
		super();
		this.view = view;
		setView(view);
	}
	
	/**
	 * 
	 */
	public void initDateTimePicker(int h_one,int m_one,int h_two,int m_two) {

		wv_hours_one = (WheelView)view.findViewById(R.id.hour_one);
		wv_mins_one = (WheelView)view.findViewById(R.id.mins_one);
		
		wv_hours_two = (WheelView)view.findViewById(R.id.hour_two);
		wv_mins_two = (WheelView)view.findViewById(R.id.mins_two);
		
		wv_hours_one.setAdapter(new NumericWheelAdapter(0, 23));
		wv_hours_one.setCyclic(true);// 可循环滚动
		//wv_hours_one.setLabel("时");// 添加文字
		wv_hours_one.setCurrentItem(h_one);
		
		
		wv_mins_one.setAdapter(new NumericWheelAdapter(0, 59));
		wv_mins_one.setCyclic(true);// 可循环滚动
		//wv_mins_one.setLabel("分");// 添加文字
		wv_mins_one.setCurrentItem(m_one);
		
		wv_hours_two.setAdapter(new NumericWheelAdapter(0, 23));
		wv_hours_two.setCyclic(true);// 可循环滚动
		//wv_hours_two.setLabel("时");// 添加文字
		wv_hours_two.setCurrentItem(h_two);
		
		
		wv_mins_two.setAdapter(new NumericWheelAdapter(0, 59));
		wv_mins_two.setCyclic(true);// 可循环滚动
		//wv_mins_two.setLabel("分");// 添加文字
		wv_mins_two.setCurrentItem(m_two);

		int textSize = 0;
	
		textSize = 60 ;
		wv_hours_one.TEXT_SIZE = textSize;
		wv_mins_one.TEXT_SIZE = textSize;
		
		wv_hours_two.TEXT_SIZE = textSize;
		wv_mins_two.TEXT_SIZE = textSize;

	}

	public String getFromDateTime() {
		StringBuffer sb = new StringBuffer();
		if(Integer.valueOf(wv_hours_one.getCurrentItem()) > 10){
			sb.append(wv_hours_one.getCurrentItem()).append(":");	
		}else{
			sb.append("0"+wv_hours_one.getCurrentItem()).append(":");
		}
		
		if(Integer.valueOf(wv_mins_one.getCurrentItem()) > 10 ){
			sb.append(wv_mins_one.getCurrentItem());	
		}else{
			sb.append("0"+wv_mins_one.getCurrentItem());
		}
		
		return sb.toString();
	}
	
	public String getToDateTime() {
		StringBuffer sb = new StringBuffer();
		if(Integer.valueOf(wv_hours_two.getCurrentItem()) > 10){
			sb.append(wv_hours_two.getCurrentItem()).append(":");	
		}else{
			sb.append("0"+wv_hours_two.getCurrentItem()).append(":");
		}
		
		if(Integer.valueOf(wv_mins_two.getCurrentItem()) > 10 ){
			sb.append(wv_mins_two.getCurrentItem());	
		}else{
			sb.append("0"+wv_mins_two.getCurrentItem());
		}
		return sb.toString();
	}
}
