package com.wear.testproject.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.bean.AccountInfoBean;
import com.wear.testproject.bean.DeviceInfoBean;
import com.wear.testproject.bean.LocationComponment;
import com.wear.testproject.bean.NumUsrRelation;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.SerialNumberComponment;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.bean.pushBean;
import com.wear.testproject.impl.IAccountManager;
import com.wear.testproject.webmanager.WebAccountManager;
import com.wear.testproject.widget.WindowsToast;

public class QRCodeResultActivity extends BaseActivity implements OnClickListener,IAccountManager{
	
	private LinearLayout mLLBack;

	private ImageView mIvReleaseBlog;

	private TextView mTvTitle;
	
	private TextView mTvContent ;
	
	private void initView() {
		mTvContent = (TextView)findViewById(R.id.tv_qrcode_result);
		mTvTitle = (TextView) findViewById(R.id.tv_main_title_info);
		mLLBack = (LinearLayout) findViewById(R.id.ll_back);
		mIvReleaseBlog = (ImageView) findViewById(R.id.iv_right);
		mIvReleaseBlog.setVisibility(View.GONE);
		mLLBack.setVisibility(View.VISIBLE);
		mTvTitle.setText(mContext.getString(R.string.qr_code));
		mLLBack.setOnClickListener(this);
	}
	
	private String mResultInfo ;

	private int mAction ;
	
	private String mobile ;
	
	private String pwd ;
	
	private String userName ;
	
	private AccountInfoBean mAccount ;
	
	private void getIntentData(){
		Intent intent = getIntent();
		mResultInfo = intent.getStringExtra("result");
		mTvContent.setText(mResultInfo);
		mAction = intent.getIntExtra("action", -1);
		mAccount = (AccountInfoBean) intent.getSerializableExtra("account");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qrcode_result_activity);
		initView();
		getIntentData();
		if(mAction == 0){
			WebAccountManager.getInstance(mContext).register(mContext, mResultInfo,mAccount.getUsername(), mAccount.getPassword(), mAccount.getUsername());
		}else if(mAction == 1){
			WebAccountManager.getInstance(mContext).getSerialNum(mResultInfo);
		}
		
		WebAccountManager.getInstance(mContext).setmIAcountManager(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_back:
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	public void pushAndroid(pushBean bean) {
		
	}



	@Override
	public void getSerialNum(SerialNumberComponment bean) {
		mCurrentSerialNumber = bean.getData();
		WebAccountManager.getInstance(mContext).setmIAcountManager(this);
		WebAccountManager.getInstance(mContext).numUsrRelation(bean.getData().getFuniqueid());
		
	}

	@Override
	public void userUpdate(ResultBean bean) {
		
	}

	@Override
	public void deviceInfoPhoto(DeviceInfoBean bean) {
		
	}
	
	private SerialNumber mCurrentSerialNumber ;


	@Override
	public void actionSerialView(NumUsrRelation bean) {
		Intent intent = new Intent(mContext,DeviceInfoSetActivity.class);
		intent.putExtra("action", 0);
		intent.putExtra("serialNumber", mCurrentSerialNumber);
		startActivity(intent);
		finish();
	}

	@Override
	public void uupdateSerialView(ResultBean bean) {
		
	}

	@Override
	public void loginSuccess(UserInfoBean bean) {
		
	}

	@Override
	public void registerSuccess(ResultBean bean) {
		
		WindowsToast.makeText(mContext, bean.getInfo()).show();
		if(!bean.getState().equals("2")){
			
			Intent intent = new Intent(mContext,DeviceInfoSetActivity.class);
			UserInfoBean userInfoBean = new UserInfoBean();
			SerialNumber serialNumber = new SerialNumber();
			serialNumber.setSerialnumber(mResultInfo);
			serialNumber.setFpicture("");
			serialNumber.setFuniqueid(bean.getUsrid());
			List<SerialNumber> serialList = new ArrayList<SerialNumber>();
			serialList.add(serialNumber);
			userInfoBean.setSerialNumber(serialList);
			AccountInfoBean account = new AccountInfoBean();
			account.setBirthday("1981-01-01");
			account.setPicture("");
			account.setNickname(mContext.getString(R.string.input_nickname));
			account.setSex("1");
			account.setHeight(mContext.getString(R.string.input_height));
			account.setWeight(mContext.getString(R.string.input_weight));
			account.setFuniqueid(bean.getUsrid());
			userInfoBean.setData(account);
			Bundle bundle2 = new Bundle();
			bundle2.putSerializable("serialNumber", serialNumber);
			intent.putExtras(bundle2);
			startActivity(intent);
		}
		finish();
		
	}

	@Override
	public void searchLoaUsrid(LocationComponment componment) {
		
	}



	@Override
	public void uploadFile(UserInfoBean bean) {
		
	}
	
}
