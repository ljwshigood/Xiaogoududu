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
import com.wear.testproject.adapter.SafeQoneListAdapter;
import com.wear.testproject.adapter.ViewPagerAdapter;
import com.wear.testproject.bean.MoveComponment;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.TrackBeanComponment;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.impl.IElectFenceListener;
import com.wear.testproject.utils.Constants;
import com.wear.testproject.utils.ImageTools;
import com.wear.testproject.webmanager.WebMovementManager;

public class SafeQoneListActivity extends BaseActivity implements OnClickListener,IElectFenceListener,OnPageChangeListener{
	
	private ListView mLvSafeList ;
	
	private SafeQoneListAdapter mSafeListAdapter ;
	
	private TextView mTvMainInfo ;
	
	private TextView mTvLeft ; 
	
	private ImageView mIvLeft ;
	
	private ImageView mIvRight ;
	
	private TextView mTvRight ;
	
	private ArrayList<View> views;
	
	private ViewPager viewPager;
	
	private ViewPagerAdapter vpAdapter;
	
	private LayoutInflater mLayoutInflater ;
	
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
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_safe_qone_list);
		getIntentData();
		mLayoutInflater = LayoutInflater.from(mContext);
		initView();
		initData();
		WebMovementManager.getInstance(mContext).searchElectFence(mContext, mSerialNumber.getSerialnumber());
		WebMovementManager.getInstance(mContext).setmIElectFenceListener(this);
	}
	
	private UserInfoBean mUserInfoBean ;

	private SerialNumber mSerialNumber ;
	
	private void getIntentData(){
		Intent intent = getIntent();
		mUserInfoBean = (UserInfoBean)intent.getSerializableExtra("userInfoBean");
		mSerialNumber = mUserInfoBean.getSerialNumber().get(0);
	}
	
	private void initView(){
		mTvRight = (TextView)findViewById(R.id.tv_right);
		mIvRight = (ImageView)findViewById(R.id.iv_right);
		mIvLeft = (ImageView)findViewById(R.id.iv_back);
		mTvLeft = (TextView)findViewById(R.id.tv_left);
		mTvMainInfo = (TextView)findViewById(R.id.tv_main_title_info);
		mLvSafeList = (ListView)findViewById(R.id.lv_safe_list);
		mTvMainInfo.setText(mContext.getString(R.string.safe_qone_set));	
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
	
	
	private void initData(MoveComponment bean){
		mSafeListAdapter = new SafeQoneListAdapter(mContext,bean.getData());
		mLvSafeList.setAdapter(mSafeListAdapter);
		mSafeListAdapter.setmSerialNumber(mSerialNumber);
		mLvSafeList.setOnItemClickListener(mSafeListAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_left:
			finish();
			break;
		case R.id.tv_right:
			Intent intent = new Intent(mContext,SafeQoneEditActivity.class);
			intent.putExtra("action", 0);
			intent.putExtra("serialNumber", mSerialNumber);
			startActivityForResult(intent,Constants.EDITER_QONE);
			break ;
		default:
			break;
		}
		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		WebMovementManager.getInstance(mContext).setmIElectFenceListener(this);
		WebMovementManager.getInstance(mContext).searchElectFence(mContext, mSerialNumber.getSerialnumber());
		
	}

	@Override
	public void searchElectFence(MoveComponment bean) {
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
	 	mSerialNumber =  mUserInfoBean.getSerialNumber().get(position);
		WebMovementManager.getInstance(mContext).searchElectFence(mContext, mSerialNumber.getSerialnumber());
	}

	@Override
	public void searchMovement(TrackBeanComponment bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addElectFence(ResultBean bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyElectFence(ResultBean bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteElectFence(ResultBean bean) {
		// TODO Auto-generated method stub
		
	}
	
}
