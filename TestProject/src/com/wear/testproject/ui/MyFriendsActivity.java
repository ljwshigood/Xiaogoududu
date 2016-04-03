package com.wear.testproject.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.wear.testproject.R;
import com.wear.testproject.adapter.MyFriendsAdapter;
import com.wear.testproject.adapter.ViewPagerAdapter;
import com.wear.testproject.bean.FriendBeanComponment;
import com.wear.testproject.bean.FriendsComponment;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.impl.IFriendsListener;
import com.wear.testproject.webmanager.WebFriendManager;

public class MyFriendsActivity extends BaseActivity implements OnClickListener,IFriendsListener,OnPageChangeListener{

	private ListView mLvMyFriendsList;

	private MyFriendsAdapter mFriendsListAdapter;

	private TextView mTvMainInfo;

	private TextView mTvLeft;

	private ImageView mIvLeft;

	private ImageView mIvRight;

	private TextView mTvRight;
	
	private ArrayList<View> views;
	
	private ViewPager viewPager;
	
	private ViewPagerAdapter vpAdapter;
	
	private LayoutInflater mLayoutInflater ;
	
	private SerialNumber mCurrentSerialNumber ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends);
		getIntentData();
		mLayoutInflater = LayoutInflater.from(mContext);
		initView();
		initData();
		WebFriendManager.getInstance(mContext).setmIFriendsListener(this);
		
		WebFriendManager.getInstance(mContext).queryFriendList(mContext, 3, mCurrentSerialNumber.getFuniqueid());
	}

	private void initData(){
		
		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                														  LinearLayout.LayoutParams.MATCH_PARENT);
       
        for(int i=0; i < mUserInfoBean.getSerialNumber().size(); i++) {
        	SerialNumber serialNumber =  mUserInfoBean.getSerialNumber().get(i);
        	View view = mLayoutInflater.inflate(R.layout.item_account, null);
        	ImageView iv = (ImageView)view.findViewById(R.id.iv_account);
        	TextView tvAcount = (TextView)view.findViewById(R.id.tv_account_id);
        	TextView tvNickName = (TextView)view.findViewById(R.id.tv_nick_name);
        	initUserPhotoInfo(serialNumber.getFpicture(),iv);
        	tvAcount.setText(serialNumber.getFphonenum());
        	tvNickName.setText(serialNumber.getNickname());
        	view.setLayoutParams(mParams);
            views.add(view);
        } 
        viewPager.setAdapter(vpAdapter);
        viewPager.setOnPageChangeListener(this);
        initPoint();
        mCurrentSerialNumber = mUserInfoBean.getSerialNumber().get(0);
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
	
	private void initPoint(){
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);       
		/*points = new ImageView[pics.length];
        for (int i = 0; i < pics.length; i++) {
        	points[i] = (ImageView) linearLayout.getChildAt(i);
        	points[i].setEnabled(true);
        	points[i].setOnClickListener(this);
        	points[i].setTag(i);
        }
        currentIndex = 0;
        points[currentIndex].setEnabled(false);*/
	}
	
	
	private UserInfoBean mUserInfoBean ;

	private void getIntentData(){
		Intent intent = getIntent();
		mUserInfoBean = (UserInfoBean)intent.getSerializableExtra("userInfoBean");
	}
	
	
	private void initView() {
		mTvRight = (TextView) findViewById(R.id.tv_right);
		mIvRight = (ImageView) findViewById(R.id.iv_right);
		mIvLeft = (ImageView) findViewById(R.id.iv_back);
		mTvLeft = (TextView) findViewById(R.id.tv_left);
		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mLvMyFriendsList = (ListView) findViewById(R.id.lv_my_friends);
		mTvMainInfo.setText(mContext.getString(R.string.my_friends));
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvLeft.setOnClickListener(this);
		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
		mTvRight.setText(mContext.getString(R.string.add));
		mTvRight.setOnClickListener(this);
		views = new ArrayList<View>();
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		vpAdapter = new ViewPagerAdapter(views);
	}

	private void initData(FriendBeanComponment componment) {
		mFriendsListAdapter = new MyFriendsAdapter(mContext, componment.getData(),mOptionsStyle);
		mLvMyFriendsList.setAdapter(mFriendsListAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_left:
			finish();
			break;
		case R.id.tv_right:
			Intent intent = new Intent(mContext,QueryFriendsActivity.class);
			intent.putExtra("serialNumber", mCurrentSerialNumber);
			startActivity(intent);
			break ;
		default:
			break;
		}

	}

	@Override
	public void queryFriendList(FriendBeanComponment bean) {
		initData(bean);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int position) {
		mCurrentSerialNumber = mUserInfoBean.getSerialNumber().get(position);
		WebFriendManager.getInstance(mContext).queryFriendList(mContext, 3, mCurrentSerialNumber.getFuniqueid());
	}

	@Override
	public void queryFriend(FriendsComponment bean) {
		
	}

	@Override
	public void passFriendReq(ResultBean bean) {
		// TODO Auto-generated method stub
		
	}

}
