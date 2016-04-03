package com.wear.testproject.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.wear.testproject.R;
import com.wear.testproject.bean.AccountInfoBean;
import com.wear.testproject.bean.AlarmComponment;
import com.wear.testproject.bean.NewAlarmBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.SlienTimeBean;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.impl.IAlarmListener;
import com.wear.testproject.webmanager.WebAlarmManager;

public class FunctionSetActivity extends BaseActivity implements OnClickListener,IAlarmListener{
	
	private TextView mTvLeft ; 
	
	private ImageView mIvLeft ;
	
	private ImageView mIvRight ;
	
	private TextView mTvMainInfo ;
	
	private LinearLayout mLLAboutUs ;
	
	private LinearLayout mLLPayValues ;
	
	private LinearLayout mllAlarmSet ;
	
	private LinearLayout mLLFaceBack ;
	
	private LinearLayout mLLlocationMode ;

	private LinearLayout mLLAlarmAndTip ;
	
	private LinearLayout mLLFamily ;
	
	private SerialNumber mSerialNumber ;

	private UserInfoBean mUserBean ;
	
	private ImageView mIvPhoto ;
	
	private TextView mTvNickName ;
	
	private LinearLayout mLLAccountInfo ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_set);
		initView();
		getIntentData();
		initUserPhotoInfo(mSerialNumber.getFpicture(),mIvPhoto);
		initData();
		WebAlarmManager.getInstance(mContext).setmIAlarmListener(this);
	}
	
	private void initData(){
		mTvNickName.setText(mSerialNumber.getNickname());
	}

	private void getIntentData(){
		Intent intent = getIntent();
		mSerialNumber = (SerialNumber)intent.getSerializableExtra("serialNumber");
		mUserBean = (UserInfoBean)intent.getSerializableExtra("userInfo");
	}
	
	private void initUserPhotoInfo(String url,ImageView iv){
		ImageLoader.getInstance().displayImage(url, iv,mOptionsStyle, 
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingStarted(String imageUri, View view) {
						
					}
	
					@Override
					public void onLoadingFailed(String imageUri, View view,FailReason failReason) {
					}
	
					@Override
					public void onLoadingComplete(String imageUri, View view,Bitmap loadedImage) {
						
					}
				}, new ImageLoadingProgressListener() {
					@Override
					public void onProgressUpdate(String imageUri, View view,int current, int total) {
						
					}
				});
	}
	
	private LinearLayout mLLFindWatch ;
	
	private LinearLayout mLLShutDown ;
	
	
	private void initView(){
		mLLFindWatch =(LinearLayout)findViewById(R.id.ll_find_watch);
		mLLShutDown= (LinearLayout)findViewById(R.id.ll_to_where);
		mLLAccountInfo = (LinearLayout)findViewById(R.id.ll_account_info);
		mTvNickName = (TextView)findViewById(R.id.tv_nick_name);
		mIvPhoto = (ImageView)findViewById(R.id.iv_account);
		mLLFamily = (LinearLayout)findViewById(R.id.ll_family_member);
		mLLAlarmAndTip =(LinearLayout)findViewById(R.id.ll_alarm_and_tip);
		mLLlocationMode = (LinearLayout)findViewById(R.id.ll_location_mode);
		mLLFaceBack = (LinearLayout)findViewById(R.id.ll_face_back);
		mllAlarmSet = (LinearLayout)findViewById(R.id.ll_alarm_set);
		mLLPayValues = (LinearLayout)findViewById(R.id.ll_pay_value);
		mLLAboutUs = (LinearLayout)findViewById(R.id.ll_about_us);
		mIvRight = (ImageView)findViewById(R.id.iv_right);
		mIvLeft = (ImageView)findViewById(R.id.iv_back);
		mTvLeft = (TextView)findViewById(R.id.tv_left);
		mTvMainInfo = (TextView)findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.action_settings));
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvLeft.setOnClickListener(this);
		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
		mLLAboutUs.setOnClickListener(this);
		mllAlarmSet.setOnClickListener(this);
		mLLPayValues.setOnClickListener(this);
		mLLFaceBack.setOnClickListener(this);
		mLLlocationMode.setOnClickListener(this);
		mLLAlarmAndTip.setOnClickListener(this);
		mLLFindWatch.setOnClickListener(this);
		mLLShutDown.setOnClickListener(this);
		mLLFamily.setOnClickListener(this);
		mLLAccountInfo.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent ;
		switch (v.getId()) {
		case R.id.ll_find_watch:
			WebAlarmManager.getInstance(mContext).serialNumAction(mSerialNumber.getSerialnumber(), "1");
			break;
		case R.id.ll_to_where:
			WebAlarmManager.getInstance(mContext).serialNumAction(mSerialNumber.getSerialnumber(), "2");
			break;
		case R.id.ll_account_info:
			intent = new Intent(mContext,DeviceInfoSetActivity.class);
			Bundle bundle2 = new Bundle();
			bundle2.putSerializable("serialNumber", mSerialNumber);
			intent.putExtras(bundle2);
			startActivity(intent);
			break ;
		case R.id.ll_family_member:
			intent = new Intent(mContext,FamilyMemberActivity.class);
			startActivity(intent);
			break ;
		case R.id.ll_alarm_and_tip:
			intent = new Intent(mContext,AlarmAndTipTypeActivity.class);
			intent.putExtra("serialNumber", mSerialNumber);
			startActivity(intent);
			break ;
		case R.id.ll_location_mode:
			intent = new Intent(mContext,LocationModeActivity.class);
			intent.putExtra("serialNumber", mSerialNumber);
			startActivity(intent);
			break ;
		case R.id.ll_face_back:
			intent = new Intent(mContext,AdviceFedbackActivity.class);
			intent.putExtra("userInfo", mUserBean);
			startActivity(intent);
			break ;
		case R.id.ll_pay_value:
			intent = new Intent(mContext,ChangePayActivity.class);
			intent.putExtra("serialNumber", mSerialNumber);
			startActivity(intent);
			break ;
		case R.id.tv_left:
			finish();
			break;
		case R.id.ll_about_us:
			intent = new Intent(mContext,AboutVersionActivity.class);
			startActivity(intent);
			break ;
		case R.id.ll_alarm_set:
			intent = new Intent(mContext,AlarmListActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("serialNumber", mSerialNumber);
			intent.putExtras(bundle);
			startActivity(intent);
			break ;
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
		
	}
}
