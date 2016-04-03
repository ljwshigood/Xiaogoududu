package com.wear.testproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.adapter.AlarmAdapter;
import com.wear.testproject.bean.AlarmComponment;
import com.wear.testproject.bean.NewAlarmBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.SlienTimeBean;
import com.wear.testproject.impl.IAlarmListener;
import com.wear.testproject.utils.Constants;
import com.wear.testproject.webmanager.WebAlarmManager;

public class AlarmListActivity extends BaseActivity implements OnClickListener ,IAlarmListener{

	private ListView mLvAlarm;

	private TextView mTvLeft;

	private ImageView mIvLeft;

	private TextView mTvMainInfo;

	private ImageView mIvRight;

	private TextView mTvRight;

	private SerialNumber mSerialNumber ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_list);
		getIntentData();
		initView();
		WebAlarmManager.getInstance(mContext).setmIAlarmListener(this);
		WebAlarmManager.getInstance(mContext).searchAlarm(mContext,mSerialNumber.getFuniqueid());
	}
	
	private void getIntentData(){
		Intent intent = getIntent();
		mSerialNumber = (SerialNumber)intent.getSerializableExtra("serialNumber");
	}

	private void initView() {
		mTvRight = (TextView) findViewById(R.id.tv_right);
		mIvRight = (ImageView) findViewById(R.id.iv_right);
		mIvLeft = (ImageView) findViewById(R.id.iv_back);
		mTvLeft = (TextView) findViewById(R.id.tv_left);
		mLvAlarm = (ListView) findViewById(R.id.lv_alarm_set);
		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.alarm_set));
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvRight.setText(mContext.getString(R.string.add));
		mTvRight.setOnClickListener(this);
		mTvLeft.setOnClickListener(this);
		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
	}
	
	private AlarmAdapter mAlarmAdapter ;
	
	private void initData(AlarmComponment alarm){
		mAlarmAdapter = new AlarmAdapter(mContext, alarm.getAlarmList());
		mAlarmAdapter.setmSerialNumber(mSerialNumber);
		mLvAlarm.setAdapter(mAlarmAdapter);
		mLvAlarm.setOnItemClickListener(mAlarmAdapter);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_right:
			Intent intent = new Intent(mContext,AlaramSetActivity.class);
			intent.putExtra("action", 0);
			intent.putExtra("serialNumber", mSerialNumber);
			startActivityForResult(intent,Constants.EDITER_ALARM);
			break ;
		case R.id.tv_left:
			finish();
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == Constants.EDITER_ALARM){
			WebAlarmManager.getInstance(mContext).setmIAlarmListener(this);
			WebAlarmManager.getInstance(mContext).searchAlarm(mContext,mSerialNumber.getFuniqueid());	
		}
	}

	@Override
	public void queryAlarmList(AlarmComponment componment) {
		initData(componment);
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
		
	}
}
