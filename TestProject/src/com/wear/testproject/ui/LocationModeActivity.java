package com.wear.testproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.bean.LbsBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.impl.ILbsListener;
import com.wear.testproject.webmanager.WebLbsManager;
import com.wear.testproject.widget.WindowsToast;

public class LocationModeActivity extends BaseActivity implements OnClickListener,ILbsListener{
	
	private TextView mTvLeft;

	private ImageView mIvLeft;

	private TextView mTvMainInfo;

	private ImageView mIvRight;
	
	private LinearLayout mLLSafeMode ;
	
	private LinearLayout mLLBattery ;
	
	private LinearLayout mLLLoactionMode ;
	
	private SerialNumber mSerialNumber ;
	
	private void getIntentData(){
		Intent intent = getIntent();
		mSerialNumber = (SerialNumber)intent.getSerializableExtra("serialNumber");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_mode);
		initView();
		getIntentData();
		WebLbsManager.getInstance(mContext).setmILbslistener(this);
	}
	
	private CheckBox mCbSafeMode ;
	
	private CheckBox mCbSafeBattery ;
	
	private CheckBox mCbLocationMode ;

	private void initView() {
		mCbSafeMode = (CheckBox)findViewById(R.id.cb_safe_mode);
		mCbSafeBattery = (CheckBox)findViewById(R.id.cb_safe_battery);
		mCbLocationMode = (CheckBox)findViewById(R.id.cb_location_mode);
		mLLSafeMode = (LinearLayout)findViewById(R.id.ll_safe_mode);
		mLLBattery = (LinearLayout)findViewById(R.id.ll_safe_battery);
		mLLLoactionMode = (LinearLayout)findViewById(R.id.ll_location_mode);
		mIvRight = (ImageView) findViewById(R.id.iv_right);
		mIvLeft = (ImageView) findViewById(R.id.iv_back);
		mTvLeft = (TextView) findViewById(R.id.tv_left);
		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.location_mode));
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvLeft.setOnClickListener(this);
		
		mLLSafeMode.setOnClickListener(this);
		mLLBattery.setOnClickListener(this);
		mLLLoactionMode.setOnClickListener(this);
		
		mLLBattery.setOnClickListener(this);
		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_left:
			finish();
			break;
		case R.id.ll_safe_battery:
			mCbSafeMode.setChecked(false);
			mCbSafeBattery.setChecked(true);
			mCbLocationMode.setChecked(false);
			WebLbsManager.getInstance(mContext).setLbs(mContext,mSerialNumber.getSerialnumber(), 0, 360);
			break ;
		case R.id.ll_safe_mode:
			mCbSafeMode.setChecked(true);
			mCbSafeBattery.setChecked(false);
			mCbLocationMode.setChecked(false);
			WebLbsManager.getInstance(mContext).setLbs(mContext,mSerialNumber.getSerialnumber(), 0, 180);
			break;
		case R.id.ll_location_mode:
			mCbSafeMode.setChecked(false);
			mCbSafeBattery.setChecked(false);
			mCbLocationMode.setChecked(true);
			WebLbsManager.getInstance(mContext).setLbs(mContext,mSerialNumber.getSerialnumber(), 1, 60);
			break ;
		default:
			break;
		}
	}
	@Override
	public void searchLbs(LbsBean lbsBean) {
		WindowsToast.makeText(mContext, lbsBean.getInfo()).show();
	}
	
	@Override
	public void setLbs(LbsBean lbsBean) {
		WindowsToast.makeText(mContext, lbsBean.getInfo()).show();
	}
}
