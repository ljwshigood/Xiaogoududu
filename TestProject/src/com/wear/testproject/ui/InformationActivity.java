package com.wear.testproject.ui;

import java.util.ArrayList;
import java.util.List;

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
import com.wear.testproject.adapter.InfoAdapter;
import com.wear.testproject.adapter.ViewPagerAdapter;
import com.wear.testproject.bean.FriendBeanComponment;
import com.wear.testproject.bean.FriendsComponment;
import com.wear.testproject.bean.InfoBean;
import com.wear.testproject.bean.InformationBean;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.impl.IFriendsListener;
import com.wear.testproject.impl.IInfomationManager;
import com.wear.testproject.webmanager.WebFriendManager;
import com.wear.testproject.webmanager.WebInfomationManager;

public class InformationActivity extends BaseActivity implements OnClickListener ,OnPageChangeListener,IInfomationManager,IFriendsListener{
	
	// 68
	private TextView mTvMainInfo ;
	
	private List<InfoBean> mInfoList = new ArrayList<InfoBean>() ;
	
	private int[] res = new int[]{R.drawable.message_button_find,R.drawable.message_button_arrive,
			R.drawable.message_button_leave,R.drawable.message_button_battary,
			R.drawable.message_button_money,R.drawable.message_button_find,R.drawable.message_button_find};
	
	private void initView(){
		mIvRight = (ImageView)findViewById(R.id.iv_right);
		mIvLeft = (ImageView)findViewById(R.id.iv_back);
		mTvLeft = (TextView)findViewById(R.id.tv_left);
		mLvInfomation = (ListView)findViewById(R.id.lv_information);
		mTvMainInfo = (TextView)findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.control_center));
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvLeft.setOnClickListener(this);
		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
	}
	
	private ListView mLvInfomation ;
	
	private TextView mTvLeft ; 
	
	private ImageView mIvLeft ;
	
	private ImageView mIvRight ;
	
	private LayoutInflater mLayoutInflater ;
	
	private ArrayList<View> views;
	
	private ViewPager viewPager;
	
	private ViewPagerAdapter vpAdapter;
	
	private void initDeviceListData(){
		
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
	
	private UserInfoBean mUserInfoBean ;

	private SerialNumber mSerialNumber ;
	
	private void getIntentData(){
		Intent intent = getIntent();
		mUserInfoBean = (UserInfoBean)intent.getSerializableExtra("userInfoBean");
		mSerialNumber = mUserInfoBean.getSerialNumber().get(0);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);
		getIntentData();
		mLayoutInflater = LayoutInflater.from(mContext);
		initView();
		initData();
		initAdapter();
		views = new ArrayList<View>();
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		vpAdapter = new ViewPagerAdapter(views);
		initDeviceListData();
		WebInfomationManager.getInstance(mContext).setmInfomationMaager(this);
		WebFriendManager.getInstance(mContext).setmIFriendsListener(this);
		WebInfomationManager.getInstance(mContext).queryMsgCenter(mSerialNumber.getFuniqueid());
	}
	
	private InfoAdapter mInfoAdapter ;
	
	
	
	private void initData(){
		String[] info = new String[]{mContext.getString(R.string.friend_request),mContext.getString(R.string.arrive),mContext.getString(R.string.leave),
				mContext.getString(R.string.battery),mContext.getString(R.string.money),mContext.getString(R.string.find),mContext.getString(R.string.zaichu_tip)};
		for(int i = 0 ;i < 6;i++){
			InfoBean bean = new InfoBean();
			bean.setRes(res[i]);
			bean.setInfo(info[i]);
			if(i == 0){
				bean.setDetail(mContext.getString(R.string.view_friends_info));
			}else{
				bean.setDetail(mContext.getString(R.string.non_message));
			}
			mInfoList.add(bean);
		}
		
	}
	
	private void initAdapter(){
		mInfoAdapter = new InfoAdapter(mContext, mInfoList);
		mInfoAdapter.setmSerialNumber(mSerialNumber);
		mLvInfomation.setAdapter(mInfoAdapter);
		mLvInfomation.setOnItemClickListener(mInfoAdapter);
	}

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


	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}


	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}


	@Override
	public void onPageSelected(int position) {
		mInfoList.clear();
		initData();
		mInfoAdapter.notifyDataSetChanged();
		
		SerialNumber serialNumber =  mUserInfoBean.getSerialNumber().get(position);
		WebInfomationManager.getInstance(mContext).queryMsgCenter(serialNumber.getFuniqueid());
	}

	@Override
	public void queryMegCenterNotifyFence(InformationBean bean) {
		
	}

	@Override
	public void queryMegCenterNotifyDetach(InformationBean bean) {
		
	}

	@Override
	public void queryMegCenterNotifyFee(InformationBean bean) {
	
	}

	@Override
	public void queryMegCenterNotifyBattery(InformationBean bean) {
	}

	@Override
	public void queryFriendList(FriendBeanComponment bean) {
		
	}

	@Override
	public void queryFriend(FriendsComponment bean) {
		
	}

	@Override
	public void passFriendReq(ResultBean bean) {
		
	}

	@Override
	public void queryMegCenter(InformationBean bean) {
		if(bean.getNofityDetach() != null && bean.getNofityDetach().size() != 0){
			String detach = String.valueOf(bean.getNofityDetach().get(0).getFaddtime());
			mInfoList.get(2).setDetail(detach);
			mInfoAdapter.notifyDataSetChanged();
		}
		
		if(bean.getNotifyFee() != null && bean.getNotifyFee().size() != 0){
			String fee = String.valueOf(bean.getNotifyFee().get(0).getFbalance());
			mInfoList.get(4).setDetail(fee);
			mInfoAdapter.notifyDataSetChanged();
		}
		
		if(bean.getNotifyBattery() != null && bean.getNotifyBattery().size() != 0){
			String battery = bean.getNotifyBattery().get(0).getBattery();
			if(battery != null && !battery.equals("")){
				int batteryInt = Integer.valueOf(battery);
				mInfoList.get(3).setDetail(String.valueOf(batteryInt));
				mInfoAdapter.notifyDataSetChanged();	
			}
		}
		
	}

}
