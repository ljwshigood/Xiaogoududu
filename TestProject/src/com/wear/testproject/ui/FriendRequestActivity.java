package com.wear.testproject.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.adapter.FriendsAdapter;
import com.wear.testproject.bean.FriendBeanComponment;
import com.wear.testproject.bean.FriendsComponment;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.impl.IFriendsListener;
import com.wear.testproject.webmanager.WebFriendManager;

public class FriendRequestActivity extends BaseActivity implements OnClickListener,IFriendsListener{
	
	private TextView mTvMainInfo;

	private ImageView mIvBack;

	private ImageView mIvRight ;
	
	private ListView mLvFriendRequest;
	
	private FriendsAdapter mFriendsAdapter ;
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_left:
			finish();
			break;
		default:
			break;
		}
	}
	
	private SerialNumber mSerialNumber ;
	
	private void getIntentData(){
		Intent intent = getIntent();
		mSerialNumber = (SerialNumber) intent.getSerializableExtra("serialNumber");	
	}
	
	BroadcastReceiver recevier = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			 intent = new Intent(mContext,WelcomeActivity.class);
			 startActivity(intent);
		}
		
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends_request);
		getIntentData();
		initView();
		WebFriendManager.getInstance(mContext).setmIFriendsListener(this);
		WebFriendManager.getInstance(mContext).queryFriendList(mContext, 2, mSerialNumber.getFuniqueid());
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	private void initData(FriendBeanComponment componment){
		mFriendsAdapter = new FriendsAdapter(mContext, componment.getData());
		mLvFriendRequest.setAdapter(mFriendsAdapter);
	}
	
	private TextView mTvLeft ;
	
	private void initView() {
		mIvRight = (ImageView)findViewById(R.id.iv_right);
		mIvBack = (ImageView) findViewById(R.id.iv_back);
		mLvFriendRequest = (ListView)findViewById(R.id.lv_my_friends);
		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.friend_request));
		mIvBack.setImageResource(R.drawable.ic_back);
		mIvRight.setVisibility(View.GONE);
		mTvLeft = (TextView)findViewById(R.id.tv_left);
		mTvLeft.setVisibility(View.VISIBLE);
		mTvLeft.setOnClickListener(this);
		mTvLeft.setText(mContext.getString(R.string.back));
		mIvBack.setOnClickListener(this);
		mIvBack.setVisibility(View.GONE);
	}


	@Override
	public void queryFriendList(FriendBeanComponment bean) {
		initData(bean);
	}

	@Override
	public void queryFriend(FriendsComponment bean) {
		
	}


	@Override
	public void passFriendReq(ResultBean bean) {
		// TODO Auto-generated method stub
		
	}

}
