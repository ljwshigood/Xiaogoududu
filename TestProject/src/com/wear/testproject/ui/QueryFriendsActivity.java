package com.wear.testproject.ui;

import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.wear.testproject.R;
import com.wear.testproject.bean.FriendBeanComponment;
import com.wear.testproject.bean.FriendsComponment;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.impl.IFriendsListener;
import com.wear.testproject.webmanager.WebFriendManager;
import com.wear.testproject.widget.WindowsToast;

public class QueryFriendsActivity extends BaseActivity implements OnClickListener,IFriendsListener{
	
	private TextView mTvLeft;

	private ImageView mIvLeft;

	private ImageView mIvRight;

	private TextView mTvMainInfo;
	
	private TextView mTvRight ;
	
	private UserInfoBean mUserInfoBean ;
	
	private LinearLayout mLLOk ;
	
	private EditText mEtMobile ;
	
	private LinearLayout mLLQueryInfo ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query_friends);
		initView();
		getIntentData();
	}

	private ImageView mIvPhoto ;
	
	private TextView mTvNickName ;
	
	private TextView mMobile ;
	
	private LinearLayout mLLSend ;
	
	private EditText mEtBeizhu ;
	
	
	private void initView() {
		mLLQueryInfo = (LinearLayout)findViewById(R.id.ll_query_info);
		mEtMobile = (EditText)findViewById(R.id.et_search);
		mEtBeizhu = (EditText)findViewById(R.id.et_beizhu);
		mLLSend = (LinearLayout)findViewById(R.id.ll_ok);
		mLLSend.setOnClickListener(this);
		mIvPhoto = (ImageView)findViewById(R.id.iv_account);
		mTvNickName = (TextView)findViewById(R.id.tv_nick_name);
		mMobile = (TextView)findViewById(R.id.tv_account_id);
		mLLOk = (LinearLayout)findViewById(R.id.ll_location_cancel);
		mTvRight =(TextView)findViewById(R.id.tv_right);
		mIvRight = (ImageView) findViewById(R.id.iv_right);
		mIvLeft = (ImageView) findViewById(R.id.iv_back);
		mTvLeft = (TextView) findViewById(R.id.tv_left);
		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.found_friend));
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvLeft.setOnClickListener(this);
		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
		mLLOk.setOnClickListener(this);
		mTvRight.setVisibility(View.VISIBLE);
		mTvRight.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		WebFriendManager.getInstance(mContext).setmIFriendsListener(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		WebFriendManager.getInstance(mContext).setmIFriendsListener(null);
	}
	
	private SerialNumber mSerialNumber ;
	
	private void getIntentData(){
		Intent intent = getIntent();
		mSerialNumber = (SerialNumber) intent.getSerializableExtra("serialNumber");
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_left:
			finish();
			break;
		case R.id.ll_location_cancel:
			WebFriendManager.getInstance(mContext).searchSerialNumber(mEtMobile.getText().toString().trim());
			break ;
		case R.id.ll_ok:
			WebFriendManager.getInstance(mContext).postFriendReq(mContext, mQuerySerialNumber.get(0).getFuniqueid(), 
					mSerialNumber.getFuniqueid(),mEtBeizhu.getText().toString().trim());
			break ;
		default:
			break;
		}

	}

	private void initData(FriendBeanComponment componment) {
		
	}

	
	@Override
	public void queryFriendList(FriendBeanComponment bean) {
		initData(bean);
	}

	private List<SerialNumber> mQuerySerialNumber ;
	
	@Override
	public void queryFriend(FriendsComponment bean) {
		if(bean.getData() == null){
			WindowsToast.makeText(mContext, bean.getInfo()).show();
			mLLQueryInfo.setVisibility(View.GONE);
			return ;
		}else{
			mLLQueryInfo.setVisibility(View.VISIBLE);
		}
		
		mQuerySerialNumber = bean.getData();
		initUserPhotoInfo(mQuerySerialNumber.get(0).getFpicture(),mIvPhoto);
		mTvNickName.setText(mQuerySerialNumber.get(0).getNickname());
		mMobile.setText(mQuerySerialNumber.get(0).getFphonenum());
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

	@Override
	public void passFriendReq(ResultBean bean) {
		// TODO Auto-generated method stub
		
	}
	

}
