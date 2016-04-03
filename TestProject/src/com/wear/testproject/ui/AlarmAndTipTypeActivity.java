package com.wear.testproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.bean.AlarmComponment;
import com.wear.testproject.bean.NewAlarmBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.SlienTimeBean;
import com.wear.testproject.impl.IAlarmListener;
import com.wear.testproject.utils.Constants;
import com.wear.testproject.webmanager.WebAlarmManager;
import com.wear.testproject.widget.WindowsToast;

public class AlarmAndTipTypeActivity extends BaseActivity implements OnClickListener,IAlarmListener{
	
	private TextView mTvLeft;

	private ImageView mIvLeft;

	private TextView mTvMainInfo;

	private ImageView mIvRight;
	
	private RelativeLayout mLLWatchTimeSet ;
	
	private RelativeLayout mRlAlarmTip ;

	private TextView mTvMobileDuration ;
	
	private TextView mTvWatchDuration ;
	
	private CheckBox mCbEveryDay ;
	
	private CheckBox mCbRi ;
	
	private CheckBox mCbOne ;
	
	private CheckBox mCbTwo ;
	
	private CheckBox mCbThree ;
	
	private CheckBox mCbFour ;
	
	private CheckBox mCbFive ;
	
	private CheckBox mCbSix ;
	
	private TextView mTvRight ;
	
	private CheckBox mCbAlarm ;
	
	private void initView() {
		mCbAlarm = (CheckBox)findViewById(R.id.cb_watch_have_class);
		mCbEveryDay = (CheckBox)findViewById(R.id.cb_meizhou);
		mCbOne = (CheckBox)findViewById(R.id.btn_two);
		mCbTwo = (CheckBox)findViewById(R.id.btn_three);
		mCbThree = (CheckBox)findViewById(R.id.btn_four);
		mCbFour = (CheckBox)findViewById(R.id.btn_five);
		mCbFive = (CheckBox)findViewById(R.id.btn_six);
		mCbSix = (CheckBox)findViewById(R.id.btn_seven);
		mCbRi = (CheckBox)findViewById(R.id.btn_one);
		
		mCbAlarm.setOnClickListener(this);
		mTvRight = (TextView)findViewById(R.id.tv_right);
		mTvWatchDuration = (TextView)findViewById(R.id.tv_watch_duration);
		mTvMobileDuration = (TextView)findViewById(R.id.tv_mobile_duration);
		mLLWatchTimeSet = (RelativeLayout)findViewById(R.id.ll_have_class_time);
		mIvRight = (ImageView) findViewById(R.id.iv_right);
		mIvLeft = (ImageView) findViewById(R.id.iv_back);
		mTvLeft = (TextView) findViewById(R.id.tv_left);
		mRlAlarmTip = (RelativeLayout)findViewById(R.id.ll_non_disturbe_duration);
		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.alarm_tip_mode));
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvLeft.setOnClickListener(this);
		mRlAlarmTip.setOnClickListener(this);
		mLLWatchTimeSet.setOnClickListener(this);
		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
		mTvRight.setVisibility(View.VISIBLE);
		mTvRight.setOnClickListener(this);
		mTvRight.setText(mContext.getString(R.string.complete));
	}
	
	private SerialNumber mSerialNumber ;
	
	private void getIntentData(){
		Intent intent = getIntent();
		mSerialNumber = (SerialNumber)intent.getSerializableExtra("serialNumber");
	}
	
	private String mRepeart ;
	
	private char[] mRepearChar = {'0','0','0','0','0','0','0','0'};

	private void initWatchMode(String repeat) {
		mRepeart = Integer.toBinaryString(Integer.valueOf(repeat));
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
		
		
		if(mRepearChar[0] == '0'){
			mCbAlarm.setChecked(false);
		}else{
			mCbAlarm.setChecked(true);
		}
		
		if(mRepearChar[1] == '0'){
			mCbRi.setChecked(false);
		}else{
			mCbRi.setChecked(true);
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
	
	@Override
	public void onClick(View v) {
		Intent intent  ;
		switch (v.getId()) {
		case R.id.cb_watch_have_class:
			if(mCbAlarm.isChecked()){
				mRepearChar[0] = 1 ;	
			}else{
				mRepearChar[0] = 0 ;
			}
			
			break ;
		case R.id.tv_left:
			finish();
			break;
		case R.id.ll_non_disturbe_duration:
			intent = new Intent(mContext,TimerSetActivity.class);
			intent.putExtra("time", mTvMobileDuration.getText().toString().trim());
			startActivity(intent);
			break ;
		case R.id.tv_right:
			String str = new String(mRepearChar);
			String tmp = Integer.valueOf(str,2).toString();
			WebAlarmManager.getInstance(mContext).serialNumAction(mSerialNumber.getFuniqueid(), 3,tmp,mFromTime,mToTime);
			break ;
		case R.id.ll_have_class_time:
			intent = new Intent(mContext,TimerSetActivity.class);
			intent.putExtra("time", mTvWatchDuration.getText().toString().trim());
			intent.putExtra("serialNumber", mSerialNumber);
			startActivityForResult(intent,Constants.TIME_SET);
			break ;
		default:
			break;
		}
	}
	
	private String mToTime  ;
	
	private String mFromTime  ;
	
	private String mWeekValide ;
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Constants.TIME_SET && data != null){
			mToTime = data.getStringExtra("toDate");
			mFromTime = data.getStringExtra("fromDate");
			mWeekValide = data.getStringExtra("weekvalide");
			mTvWatchDuration.setText(mFromTime+"-"+mToTime);
			initWatchMode(mWeekValide);
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_tip_style);
		initView();
		getIntentData();
		WebAlarmManager.getInstance(mContext).setmIAlarmListener(this);
		WebAlarmManager.getInstance(mContext).searchSerialParm(mSerialNumber.getFuniqueid(),1);
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
		if(bean.getData() != null && bean.getData().size() > 0){
			mTvWatchDuration.setText(bean.getData().get(0).getBegintime()+"-"+bean.getData().get(0).getEndtime());
			mToTime = bean.getData().get(0).getBegintime();
			mFromTime = bean.getData().get(0).getEndtime();
			mWeekValide = String.valueOf(bean.getData().get(0).getWeekAndValid()) ;
			initWatchMode(String.valueOf(bean.getData().get(0).getWeekAndValid()));	
		}
	}

	@Override
	public void serialNumAction(SlienTimeBean bean) {
		WindowsToast.makeText(mContext, bean.getInfo()).show();
		finish();
	}


}
