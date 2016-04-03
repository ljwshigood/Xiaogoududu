package com.wear.testproject.ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.bean.AlarmBean;
import com.wear.testproject.bean.AlarmComponment;
import com.wear.testproject.bean.NewAlarmBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.SlienTimeBean;
import com.wear.testproject.impl.IAlarmListener;
import com.wear.testproject.utils.Constants;
import com.wear.testproject.utils.WindowsTools;
import com.wear.testproject.webmanager.WebAlarmManager;
import com.wear.testproject.widget.WheelHourMinMain;
import com.wear.testproject.widget.WindowsToast;

public class AlaramSetActivity extends BaseActivity implements OnClickListener,IAlarmListener{

	private TextView mTvLeft;

	private ImageView mIvLeft;

	private TextView mTvMainInfo;

	private ImageView mIvRight;

	private WheelHourMinMain wheelHourMinuteMain;

	private LinearLayout mLLDate;

	public boolean isDate(String str_input, String rDateFormat) {
		if (!isNull(str_input)) {
			SimpleDateFormat formatter = new SimpleDateFormat(rDateFormat);
			formatter.setLenient(false);
			try {
				formatter.format(formatter.parse(str_input));
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		return false;
	}

	private DateFormat dateFormat = new SimpleDateFormat("hh:mm");

	public static boolean isNull(String str) {
		if (str == null)
			return true;
		else
			return false;
	}

	private void initDateTimePicker(View view, Activity context) {

		wheelHourMinuteMain = new WheelHourMinMain(view);
		wheelHourMinuteMain.screenheight = WindowsTools.getWindowsHeight(context) / 2;

		Calendar calendar = Calendar.getInstance();
		if(mAlarmBean != null){
			String time = mAlarmBean.getFtime();
			String[] timeArray = time.split(":");
			try {
				calendar.setTime(dateFormat.parse(timeArray[1]+":"+timeArray[2]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			try {
				calendar.setTime(dateFormat.parse("23:59"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		int hour = calendar.get(Calendar.HOUR);
		int min = calendar.get(Calendar.MINUTE);
		wheelHourMinuteMain.initDateTimePicker(hour, min);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_set);
		initView();
		getIntentData();
		initData();
		initDateTimePicker(mLLDate, AlaramSetActivity.this);
		WebAlarmManager.getInstance(mContext).setmIAlarmListener(this);
	}

	private int mAction = 0;

	private AlarmBean mAlarmBean;
	
	private SerialNumber mSerialNumber ;

	private void getIntentData() {
		Intent intent = getIntent();
		mAction = intent.getIntExtra("action", -1);
		if (mAction == 0) {
			mLLDelete.setVisibility(View.GONE);
		} else if (mAction == 1) {
			mLLDelete.setVisibility(View.VISIBLE);
		}
		mAlarmBean = (AlarmBean) intent.getSerializableExtra("alarmBean");
		mSerialNumber = (SerialNumber) intent.getSerializableExtra("serialNumber");
	}
	
	private String mRepeart ;
	
	private char[] mRepearChar = {'0','0','0','0','0','0','0','0'};

	private void initData() {
		if(mAction == 1){
			
			mEtAlarmName.setText(mAlarmBean.getFname());
			String time = mAlarmBean.getFtime();
			String[] timeArray = time.split(":");
			String repeart = timeArray[0];
			mRepeart = Integer.toBinaryString(Integer.valueOf(repeart));
			switch (mRepeart.length()) {
			case 1:
				mRepeart = "0000000" +mRepeart ;
				break;
			case 2:
				mRepeart = "000000" +mRepeart ;
				break;
			case 3:
				mRepeart = "00000" +mRepeart ;
				break;
			case 4:
				mRepeart = "0000" +mRepeart ;
				break;
			case 5:
				mRepeart = "000" +mRepeart ;
				break;
			case 6:
				mRepeart = "00" +mRepeart ;
				break;
			case 7:
				mRepeart = "0" +mRepeart ;
				break;

			default:
				break;
			}
			
			mRepearChar = mRepeart.toCharArray();
			
			if(mRepearChar[1] == '0'){
				mCbSeven.setChecked(false);
			}else{
				mCbSeven.setChecked(true);
			}
			
			if(mRepearChar[2] == '0'){
				mCbOne.setChecked(false);
			}else{
				mCbOne.setChecked(true);
			}
			
			if(mRepearChar[3] == '0'){
				mCbTwo.setChecked(false);
			}else{
				mCbTwo.setChecked(true);
			}
			
			if(mRepearChar[4] == '0'){
				mCbThree.setChecked(false);
			}else{
				mCbThree.setChecked(true);
			}
			
			if(mRepearChar[5] == '0'){
				mCbFour.setChecked(false);
			}else{
				mCbFour.setChecked(true);
			}
			
			if(mRepearChar[6] == '0'){
				mCbFive.setChecked(false);
			}else{
				mCbFive.setChecked(true);
			}
			
			if(mRepearChar[7] == '0'){
				mCbSix.setChecked(false);
			}else{
				mCbSix.setChecked(true);
			}
			
		}
	}

	private CheckBox mCbAll;

	private CheckBox mCbOne;

	private CheckBox mCbTwo;

	private CheckBox mCbThree;

	private CheckBox mCbFour;

	private CheckBox mCbFive;

	private CheckBox mCbSix;

	private CheckBox mCbSeven;

	private LinearLayout mLLDelete;

	private EditText mEtAlarmName ;
	
	private LinearLayout mLLCompleteSet ;
	
	private void initView() {
		mLLCompleteSet = (LinearLayout) findViewById(R.id.ll_complete_set);	
		mEtAlarmName = (EditText)findViewById(R.id.et_ring_name);
		mLLDelete = (LinearLayout) findViewById(R.id.ll_send);
		mLLDelete.setOnClickListener(this);
		mLLCompleteSet.setOnClickListener(this);
		mLLDate = (LinearLayout) findViewById(R.id.ll_date);
		mIvRight = (ImageView) findViewById(R.id.iv_right);
		mIvLeft = (ImageView) findViewById(R.id.iv_back);
		mTvLeft = (TextView) findViewById(R.id.tv_left);
		mCbAll = (CheckBox) findViewById(R.id.btn_all);
		mCbOne = (CheckBox) findViewById(R.id.btn_one);
		mCbTwo = (CheckBox) findViewById(R.id.btn_two);
		mCbThree = (CheckBox) findViewById(R.id.btn_three);
		mCbFour = (CheckBox) findViewById(R.id.btn_four);
		mCbFive = (CheckBox) findViewById(R.id.btn_five);
		mCbSix = (CheckBox) findViewById(R.id.btn_six);
		mCbSeven = (CheckBox) findViewById(R.id.btn_seven);

		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.alarm_set));
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvLeft.setOnClickListener(this);
		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);

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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_left:
			finish();
			break;
		case R.id.ll_complete_set:
			String str = new String(mRepearChar);
			String tmp = Integer.valueOf(str,2).toString();
			str = tmp +":"+wheelHourMinuteMain.getTime();
			if(mAction == 0){
				WebAlarmManager.getInstance(mContext).newAlarm(mContext,mSerialNumber.getFuniqueid(), mEtAlarmName.getText().toString().trim(), str);
			}else{
				WebAlarmManager.getInstance(mContext).updateAlarm(mContext, mAlarmBean.getFalarmid(), mEtAlarmName.getText().toString().trim(), str);
			}
			break ;
		case R.id.ll_send:
			WebAlarmManager.getInstance(mContext).deleteAlarm(mContext, mAlarmBean.getFalarmid());
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
		WindowsToast.makeText(mContext, bean.getInfo()).show();
		setResult(Constants.EDITER_ALARM);
		finish();
	}

	@Override
	public void updateAlarm(NewAlarmBean bean) {
		WindowsToast.makeText(mContext, bean.getInfo()).show();
		setResult(Constants.EDITER_ALARM);
		finish();
	}

	@Override
	public void deleteAlarm(NewAlarmBean bean) {
		WindowsToast.makeText(mContext, bean.getInfo()).show();
		setResult(Constants.EDITER_ALARM);
		finish();
	}

	@Override
	public void searchSerialParm(SlienTimeBean bean) {
		
	}

	@Override
	public void serialNumAction(SlienTimeBean bean) {
		
	}
}
