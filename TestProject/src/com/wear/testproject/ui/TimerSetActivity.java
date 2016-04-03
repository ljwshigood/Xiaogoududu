package com.wear.testproject.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.bean.AlarmComponment;
import com.wear.testproject.bean.NewAlarmBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.SlienTimeBean;
import com.wear.testproject.impl.IAlarmListener;
import com.wear.testproject.utils.Constants;
import com.wear.testproject.utils.WindowsTools;
import com.wear.testproject.widget.WheelDoubleHourMinMain;

public class TimerSetActivity extends BaseActivity implements OnClickListener,IAlarmListener {

	private LinearLayout mLLComplete;

	private TextView mTvMainInfo;

	private ImageView mIvBack;

	private ImageView mIvRight;
	
	private LinearLayout mLLDate ;
	
	private TextView mTvLeft ;
	
	private CheckBox mCbAll;

	private CheckBox mCbOne;

	private CheckBox mCbTwo;

	private CheckBox mCbThree;

	private CheckBox mCbFour;

	private CheckBox mCbFive;

	private CheckBox mCbSix;

	private CheckBox mCbSeven;
	
	private char[] mRepearChar = {'0','0','0','0','0','0','0','0'};
	
	private void initView() {
		
		mCbAll = (CheckBox) findViewById(R.id.btn_all);
		mCbOne = (CheckBox) findViewById(R.id.btn_one);
		mCbTwo = (CheckBox) findViewById(R.id.btn_two);
		mCbThree = (CheckBox) findViewById(R.id.btn_three);
		mCbFour = (CheckBox) findViewById(R.id.btn_four);
		mCbFive = (CheckBox) findViewById(R.id.btn_five);
		mCbSix = (CheckBox) findViewById(R.id.btn_six);
		mCbSeven = (CheckBox) findViewById(R.id.btn_seven);
		
		mTvLeft = (TextView)findViewById(R.id.tv_left);
		mLLDate = (LinearLayout)findViewById(R.id.ll_alarm_one);
		mLLComplete = (LinearLayout) findViewById(R.id.ll_complete);
		mIvRight = (ImageView) findViewById(R.id.iv_right);
		mIvBack = (ImageView) findViewById(R.id.iv_back);
		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.time_set));
		mIvBack.setImageResource(R.drawable.ic_back);
		mIvBack.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
		mTvLeft.setVisibility(View.VISIBLE);
		mTvLeft.setOnClickListener(this);
		mLLComplete.setOnClickListener(this);
		mIvBack.setOnClickListener(this);
		mTvLeft.setText(mContext.getString(R.string.back));
		
		mCbAll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				if(mCbAll.isChecked()){
					mCbOne.setChecked(false);
					mCbTwo.setChecked(false);
					mCbThree.setChecked(false);
					mCbFour.setChecked(false);
					mCbFive.setChecked(false);
					mCbSix.setChecked(false);
					mCbSeven.setChecked(false);
					
					mRepearChar[0] = '1';
					
					mRepearChar[1] = '1';
					mRepearChar[2] = '1';
					mRepearChar[3] = '1';
					mRepearChar[4] = '1';
					mRepearChar[5] = '1';
					mRepearChar[6] = '1';
					mRepearChar[7] = '1';
				}
				
			}
		});
		
		mCbOne.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mCbOne.isChecked()) {
					mCbAll.setChecked(false);
					mRepearChar[0] = '1';
					mRepearChar[2] = '1';
				}else{
					mRepearChar[0] = '0';
					mRepearChar[2] = '0';
				}
			}
		});

		mCbTwo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mCbTwo.isChecked()){
					mCbAll.setChecked(false);
					mRepearChar[0] = '1';
					mRepearChar[3] = '1';
				}else{
					mRepearChar[0] = '0';
					mRepearChar[3] = '0';
				}
			}
		});
		
		mCbThree.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mCbThree.isChecked()) {
					mCbAll.setChecked(false);
					mRepearChar[0] = '1';
					mRepearChar[4] = '1';
				}else{
					mRepearChar[0] = '0';
					mRepearChar[4] = '0';
				}

			}
		});
		mCbFour.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mCbFour.isChecked()) {
					mCbAll.setChecked(false);
					mRepearChar[0] = '1';
					mRepearChar[5] = '1';
				}else{
					mRepearChar[0] = '0';
					mRepearChar[5] = '0';
				}
			}
		});
		mCbFive.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mCbFive.isChecked()) {
					mCbAll.setChecked(false);
					mRepearChar[0] = '1';
					mRepearChar[6] = '1';
				}else{
					mRepearChar[0] = '0';
					mRepearChar[6] = '0';
				}

			}
		});
		mCbSix.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mCbSix.isChecked()) {
					mCbAll.setChecked(false);
					mRepearChar[0] = '1';
					mRepearChar[7] = '1';
				}else{
					mRepearChar[0] = '0';
					mRepearChar[7] = '0';
				}

			}
		});
		mCbSeven.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mCbSeven.isChecked()) {
					mCbAll.setChecked(false);
					mRepearChar[0] = '1';
					mRepearChar[1] = '1';
				}else{
					mRepearChar[0] = '0';
					mRepearChar[1] = '0';
				}

			}
		});

	}
	
	private String dateFrom ;
	
	private String dateTo ;
	
	private String mTime ;
	
	private SerialNumber mSerialNumber ;
	
	private void getIntentDate(){
		Intent intent = getIntent();
		mTime = intent.getStringExtra("time");
		dateFrom = mTime.split("-")[0];
		dateTo =  mTime.split("-")[1];
		mSerialNumber = (SerialNumber) intent.getSerializableExtra("serialNumber");
	}
	
	private DateFormat dateFormat = new SimpleDateFormat("hh:mm");

	private WheelDoubleHourMinMain wheelHourMinuteMain;
	
	private void initDateTimePicker(View view) {
		
		wheelHourMinuteMain = new WheelDoubleHourMinMain(view);
		wheelHourMinuteMain.screenheight = WindowsTools.getWindowsHeight(TimerSetActivity.this) / 2;

		Calendar calendar = Calendar.getInstance();
		
		String[] timeFromArray = dateFrom.split(":");
		String[] timeToArray = dateTo.split(":");
		
		int hour_one = 23;
		int min_one = 59;
		
		int hour_two = 23 ;
		int min_two = 59 ;
		
		try {
			calendar.setTime(dateFormat.parse(timeFromArray[0]+":"+timeFromArray[1]));
			hour_one = calendar.get(Calendar.HOUR);
			min_one = calendar.get(Calendar.MINUTE);
			
			calendar.setTime(dateFormat.parse(timeToArray[0]+":"+timeToArray[1]));
			
			hour_two = calendar.get(Calendar.HOUR);
			min_two = calendar.get(Calendar.MINUTE);
			
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		
		wheelHourMinuteMain.initDateTimePicker(hour_one, min_one,hour_two,min_two);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_set);
		getIntentDate();
		initView();
		initDateTimePicker(mLLDate);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_left:
			finish();
			break;
		case R.id.ll_complete:
			String str = new String(mRepearChar);
			String tmp = Integer.valueOf(str,2).toString();
			Intent intent = new Intent();
			intent.putExtra("fromDate", wheelHourMinuteMain.getFromDateTime());
			intent.putExtra("toDate", wheelHourMinuteMain.getToDateTime());
			intent.putExtra("weekvalide", tmp);
			setResult(Constants.TIME_SET,intent);
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	public void queryAlarmList(AlarmComponment componment) {
		
	}

	@Override
	public void newAlarm(NewAlarmBean bean) {
		
	}

	@Override
	public void updateAlarm(NewAlarmBean bean) {
		
	}

	@Override
	public void deleteAlarm(NewAlarmBean bean) {
		
	}

	@Override
	public void searchSerialParm(SlienTimeBean bean) {
		
	}

	@Override
	public void serialNumAction(SlienTimeBean bean) {
		// TODO Auto-generated method stub
		
	}

}
