package com.wear.testproject.ui;

import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.OnCameraChangeListener;
import com.amap.api.maps2d.AMap.OnMapClickListener;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.wear.testproject.R;
import com.wear.testproject.adapter.BlogPopAdapter;
import com.wear.testproject.adapter.BlogPopAdapter.onSerialNumberListner;
import com.wear.testproject.adapter.SliceAdapter;
import com.wear.testproject.adapter.SliceAdapter.IDismissView;
import com.wear.testproject.bean.DeviceInfoBean;
import com.wear.testproject.bean.LbsBean;
import com.wear.testproject.bean.ListenerBean;
import com.wear.testproject.bean.LocationComponment;
import com.wear.testproject.bean.MoveBean;
import com.wear.testproject.bean.MoveComponment;
import com.wear.testproject.bean.NumUsrRelation;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.SerialNumberComponment;
import com.wear.testproject.bean.TrackBeanComponment;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.bean.pushBean;
import com.wear.testproject.impl.IAccountManager;
import com.wear.testproject.impl.IElectFenceListener;
import com.wear.testproject.impl.ILbsListener;
import com.wear.testproject.impl.IWatchListener;
import com.wear.testproject.utils.ImageTools;
import com.wear.testproject.utils.WindowsTools;
import com.wear.testproject.webmanager.WebAccountManager;
import com.wear.testproject.webmanager.WebLbsManager;
import com.wear.testproject.webmanager.WebListenerManager;
import com.wear.testproject.webmanager.WebMovementManager;
import com.wear.testproject.widget.CommonDialog;
import com.wear.testproject.widget.CustomProgressDialog;
import com.wear.testproject.widget.WindowsToast;

public class MainActivity extends BaseActivity implements OnClickListener,
		IElectFenceListener, OnCameraChangeListener, OnMarkerClickListener,
		IAccountManager, onSerialNumberListner, IDismissView ,IWatchListener,
		ILbsListener,OnMapClickListener{

	private AMap aMap;

	private MapView mapView;

	private ImageView mIvMainTitle;

	private PopupWindow popupWindow;

	private TextView mTvAccountName;

	private int[] mLeftRes = new int[] { R.drawable.ic_leftmenu_map,
			R.drawable.ic_leftmenu_message, R.drawable.ic_leftmenu_fence,
			R.drawable.ic_leftmenu_photo,/* R.drawable.ic_leftmenu_photo, */
			R.drawable.ic_leftmenu_exit };

	private String[] mLeftInfo = null;
	private ListView mLvLeftMenu;

	private SliceAdapter mSliceAdapter;

	private ImageView mIvUserPhoto;

	private LinearLayout mLLFunction;

	private LinearLayout mLLPathDesign;

	private TextView mTvAddress;

	private TextView mTvBattery;

	private LinearLayout mIvSearchMovment;
	
	private LinearLayout mLLCall;
	
	private LinearLayout mLLAutoJieTing ;
	
	private TextView mTvLocationMode ;
	
	private void initView() {
		mLLRight = (RelativeLayout) findViewById(R.id.ll_right);
		mLLRight.setOnClickListener(this);
		mMarkView = LayoutInflater.from(mContext).inflate(R.layout.mark, null);
		mIvMark = (ImageView) mMarkView.findViewById(R.id.iv_photo);
		mIvRight = (ImageView) findViewById(R.id.iv_right);
		mTvRight = (TextView) findViewById(R.id.tv_right);
		mIvFresh = (ImageView) findViewById(R.id.ic_map_fresh);
		mIvLocation = (ImageView) findViewById(R.id.ic_map_back_to_center);
		mLLPathDesign = (LinearLayout) findViewById(R.id.ll_to_where);
		mLLPopMain = (LinearLayout) findViewById(R.id.ll_pop_info);
		mLLAutoJieTing= (LinearLayout)mLLPopMain.findViewById(R.id.ll_auto_jieting);
		mIvBattery = (ImageView)mLLPopMain.findViewById(R.id.iv_battery);
		mLLCall = (LinearLayout)mLLPopMain.findViewById(R.id.ll_call);
		mTvAddress = (TextView) mLLPopMain.findViewById(R.id.tv_address);
		mTvLocationMode = (TextView) mLLPopMain.findViewById(R.id.tv_location_mode);
		mTvBattery = (TextView) mLLPopMain.findViewById(R.id.tv_battery_value);
		mLLCall.setOnClickListener(this);
		mIvSearchMovment = (LinearLayout) mLLPopMain
				.findViewById(R.id.ll_guiji_query);
		mLLFunction = (LinearLayout) mLLPopMain
				.findViewById(R.id.ll_function_set);
		mIvMainTitle = (ImageView) findViewById(R.id.iv_main_title);
		mIvMainTitle.setImageResource(R.drawable.checknet_center_logo);
		mLLTitle = (LinearLayout) findViewById(R.id.ll_title);
		mIvBack = (ImageView) findViewById(R.id.iv_back);
		mIvBack.setOnClickListener(this);
		mTvRight.setVisibility(View.GONE);
		mIvMainTitle.setVisibility(View.VISIBLE);
		mLLFunction.setOnClickListener(this);
		mLLPathDesign.setOnClickListener(this);
		mIvFresh.setOnClickListener(this);
		mIvLocation.setOnClickListener(this);
		mIvRight.setOnClickListener(this);
		mLLAutoJieTing.setOnClickListener(this);
		mIvSearchMovment.setOnClickListener(this);
	}

	private UserInfoBean mUserInfoBean;

	private void getIntentData() {
		Intent intent = getIntent();
		mUserInfoBean = (UserInfoBean) intent.getSerializableExtra("userInfo");
		if(mUserInfoBean.getSerialNumber() != null && mUserInfoBean.getSerialNumber().size() > 0){
			mCurrentSerialNumber = mUserInfoBean.getSerialNumber().get(0);
		}
	}

	private ListView mLvPop;

	private PopupWindow mAccountPopWindows;

	private void showBlogPop(View view) {

		View blogPopView = LayoutInflater.from(mContext).inflate(R.layout.pop_blog_view, null);
		mAccountPopWindows = new PopupWindow(blogPopView,
				250,
				LayoutParams.WRAP_CONTENT);
		mAccountPopWindows.setBackgroundDrawable(new BitmapDrawable());
		mAccountPopWindows.setFocusable(true);
		mAccountPopWindows.setOutsideTouchable(true);
		mAccountPopWindows.update();
		mAccountPopWindows.setTouchable(true);
		blogPopView.setFocusableInTouchMode(true);

		mLvPop = (ListView) blogPopView.findViewById(R.id.lv_pop_blog);
		BlogPopAdapter adapter = new BlogPopAdapter(mContext,
				mUserInfoBean.getSerialNumber(), mOptionsStyle);
		mLvPop.setAdapter(adapter);
		mLvPop.setOnItemClickListener(adapter);
		adapter.setmSerialNumberListener(this);

		int[] location = new int[2];
		view.getLocationOnScreen(location);
		mAccountPopWindows.showAsDropDown(view);

		mLvPop.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					if (mAccountPopWindows != null
							&& mAccountPopWindows.isShowing()) {
						mAccountPopWindows.dismiss();
					}
				}
				return true;
			}
		});
	}

	private void initData() {
		mIvMainTitle.setImageResource(R.drawable.checknet_center_logo);
	}

	private UiSettings mUiSettings;

	private LocationComponment mLocation;

	private double lation, Longitude;

	private View mMarkView;

	private ImageView mIvMark;

	private Marker mMark;

	private Bitmap mBitmap;

	private void setUpMap(LocationComponment location) {
		lation = Double.valueOf(location.getData().get(0).getLatitude());
		Longitude = Double.valueOf(location.getData().get(0).getLongitude());

		if (mMark != null) {
			mMark.destroy();
		}

		if (mBitmap != null) {
			mBitmap.recycle();
			mBitmap = null;
		}

		mBitmap = ImageTools.getBitmapFromView(mMarkView);
		mMark = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
				.position(new LatLng(lation, Longitude))
				.icon(BitmapDescriptorFactory.fromBitmap(mBitmap))
				.draggable(true));
		aMap.setOnMapClickListener(MainActivity.this);
		aMap.setOnMarkerClickListener(MainActivity.this);
		aMap.setOnCameraChangeListener(MainActivity.this);

		CameraUpdate update = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(lation,
						Longitude), 20, 30, 0));
		aMap.animateCamera(update, 500, null);

		mMark.showInfoWindow();

	}

	private ImageView mIvBack;

	private LinearLayout mLLTitle;

	private LinearLayout mLLModifyAccountInfo;

	private ImageView mIvFresh;

	private ImageView mIvLocation;

	private TextView mTvRight;

	private ImageView mIvRight;

	private RelativeLayout mLLRight;

	private CustomProgressDialog mCustomProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getIntentData();
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);

		mLeftInfo = new String[] {
				mContext.getResources().getString(R.string.map_location),
				mContext.getResources().getString(R.string.control_center),
				mContext.getResources().getString(R.string.safe_qone),
				/* mContext.getResources().getString(R.string.love_capture), */
				mContext.getResources().getString(R.string.my_friends),
				mContext.getResources().getString(R.string.safe_quit)

		};
		initView();
		initData();
		mCustomProgress = new CustomProgressDialog(mContext,
				R.style.CustomDialog, "");
		mCustomProgress.show();
		WebLbsManager.getInstance(mContext).setmILbslistener(this);
		WebAccountManager.getInstance(mContext).searchLoaUsrid(mContext,
				mUserInfoBean.getData().getFuniqueid());
		
		WebAccountManager.getInstance(mContext).pushAndroidInfo(1, mUserInfoBean.getData().getFuniqueid());
	}

	private void initLeftMenu(View view) {
		mLLModifyAccountInfo = (LinearLayout) view.findViewById(R.id.ll_header);
		mLvLeftMenu = (ListView) view.findViewById(R.id.lv_slice);
		mTvAccountName = (TextView) view.findViewById(R.id.tv_account_name);
		mIvUserPhoto = (ImageView) view.findViewById(R.id.iv_user_photo);
		mLLModifyAccountInfo.setOnClickListener(this);
	}

	private void initLeftData() {
		mSliceAdapter = new SliceAdapter(mContext, mLeftRes, mLeftInfo);
		mSliceAdapter.setmSericalNumber(mCurrentSerialNumber);
		mLvLeftMenu.setAdapter(mSliceAdapter);
		mSliceAdapter.setmUserInfoBean(mUserInfoBean);
		mLvLeftMenu.setOnItemClickListener(mSliceAdapter);
		mSliceAdapter.setmIDismiss(this);
		mTvAccountName.setText(mUserInfoBean.getData().getNickname());
		initUserPhotoInfo(mUserInfoBean.getData().getPicture(), mIvUserPhoto);
	}

	private void initMarkPhoto(String url, ImageView iv) {
		ImageLoader.getInstance().displayImage(url, iv, mOptionsStyle,
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingStarted(String imageUri, View view) {

					}

					@Override
					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
					}

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						if(loadedImage == null){
							Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher);
							mIvMark.setImageBitmap(ImageTools.toRoundBitmap(
									bitmap, 90));
						}else{
							mIvMark.setImageBitmap(ImageTools.toRoundBitmap(
								loadedImage, 90));
						}
						setUpMap(mLocation);

					}
				}, new ImageLoadingProgressListener() {
					@Override
					public void onProgressUpdate(String imageUri, View view,
							int current, int total) {

					}
				});
	}

	private void initUserPhotoInfo(String url, ImageView iv) {
		ImageLoader.getInstance().displayImage(url, iv, mOptionsStyle,
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingStarted(String imageUri, View view) {

					}

					@Override
					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
					}

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {

					}
				}, new ImageLoadingProgressListener() {
					@Override
					public void onProgressUpdate(String imageUri, View view,
							int current, int total) {

					}
				});
	}

	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
		WebListenerManager.getInstance(mContext).setmIWatchListener(this);
		WebMovementManager.getInstance(mContext).setmIElectFenceListener(this);
		WebAccountManager.getInstance(mContext).setmIAcountManager(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
		WebMovementManager.getInstance(mContext).setmIElectFenceListener(null);
		WebAccountManager.getInstance(mContext).setmIAcountManager(null);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 * 
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.ll_auto_jieting:
			WebListenerManager.getInstance(mContext).searchListener(mContext, mCurrentSerialNumber.getSerialnumber());
			break ;
		case R.id.ll_call:
			CommonDialog.getInstance(mContext).showDialog(MainActivity.this, 2, "拨打: "+mCurrentSerialNumber.getFphonenum(),0,new Handler(){
					@Override
					public void handleMessage(Message msg) {
						super.handleMessage(msg);
						if(msg.what == 1){
							  Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+mCurrentSerialNumber.getFphonenum()));  
							  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					          mContext.startActivity(intent);  	
						}
					}
			});
			break;
		case R.id.ll_guiji_query:
			intent = new Intent(mContext, InputTrackTimeActivity.class);
			intent.putExtra("serialNumber", mCurrentSerialNumber);
			startActivity(intent);
			break;
		case R.id.ic_map_fresh:
			WebAccountManager.getInstance(mContext).searchLoaUsrid(mContext,mUserInfoBean.getData().getFuniqueid());
			break;
		case R.id.ic_map_back_to_center:
			if (aMap != null) {
				setUpMap(mLocation);
			}
			break;
		case R.id.iv_back:
			getPopupWindow();
			popupWindow.showAsDropDown(mLLTitle);
			break;
		case R.id.ll_function_set:
			intent = new Intent(mContext, FunctionSetActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("serialNumber", mCurrentSerialNumber);
			bundle.putSerializable("userInfo", mUserInfoBean);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		case R.id.ll_to_where:
			intent = new Intent(mContext, RouteActivity.class);
			Bundle bundle1 = new Bundle();
			bundle1.putSerializable("location", mLocation);
			intent.putExtras(bundle1);
			startActivity(intent);
			break;
		case R.id.ll_header:
			intent = new Intent(mContext, ModifyAccountInfoActivity.class);
			Bundle bundle2 = new Bundle();
			bundle2.putSerializable("userInfo", mUserInfoBean);
			intent.putExtras(bundle2);
			startActivity(intent);
			break;
		case R.id.ll_right:
			showBlogPop(v);
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 */
	protected void initPopuptWindow() {

		View popupWindow_view = getLayoutInflater().inflate(
				R.layout.layout_menu, null, false);

		popupWindow = new PopupWindow(popupWindow_view,
				WindowsTools.getWindowsWidth(MainActivity.this) * 2 / 3,
				LayoutParams.MATCH_PARENT, true);
		popupWindow.setAnimationStyle(R.style.AnimationFade);
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		popupWindow.setBackgroundDrawable(dw);
		popupWindow_view.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
					popupWindow = null;
				}
				return false;
			}
		});

		initLeftMenu(popupWindow_view);
		initLeftData();
	}

	/***
	 * 
	 */
	private void getPopupWindow() {
		if (null != popupWindow) {
			popupWindow.dismiss();
			return;
		} else {
			initPopuptWindow();
		}
	}

	@Override
	public void onCameraChange(CameraPosition arg0) {

	}

	@Override
	public void onCameraChangeFinish(CameraPosition arg0) {

	}

	private LinearLayout mLLPopMain;
	
	private ImageView mIvBattery ;
	
	@Override
	public boolean onMarkerClick(Marker mark) {
		if (mLLPopMain.getVisibility() == 8) {
			mLLPopMain.setVisibility(View.VISIBLE);
			mIvFresh.setVisibility(View.GONE);
			mIvLocation.setVisibility(View.GONE);
		} else {
			mLLPopMain.setVisibility(View.GONE);
			mIvFresh.setVisibility(View.VISIBLE);
			mIvLocation.setVisibility(View.VISIBLE);
		}

		return false;
	}

	@Override
	public void loginSuccess(UserInfoBean bean) {

	}

	private String setDistanceTime(long distanceTime){
		String distanceStr = "" ;
		if (distanceTime < 60) {//小于一分钟
	        distanceStr = "刚刚";
	    }
	    else if (distanceTime < 60*60) {//时间小于一个小时
	        distanceStr = (long)distanceTime/60+"分钟前";
	    }
	    /*else if(distanceTime < 24*60*60 && [nowDay integerValue] == [lastDay integerValue]){//时间小于一天
	        distanceStr = (long)distanceTime/3600+"小时前";
	    }*/
	   /* else if(distanceTime< 24*60*60*2 && [nowDay integerValue] != [lastDay integerValue]){
	        
	        if ([nowDay integerValue] - [lastDay integerValue] == 1 || ([lastDay integerValue] - [nowDay integerValue] > 10 && [nowDay integerValue] == 1)) {
	            distanceStr = "昨天";
	        }
	        else{
	            [df setDateFormat:@"MM-dd HH:mm"];
	            distanceStr = [df stringFromDate:beDate];
	        }
	    }
	    else if(distanceTime < 24*60*60*365){
	        [df setDateFormat:@"MM-dd HH:mm"];
	        distanceStr = [df stringFromDate:beDate];
	    }
	    else{
	        [df setDateFormat:@"yyyy-MM-dd HH:mm"];
	        distanceStr = [df stringFromDate:beDate];
	    }*/
		return distanceStr ;
	}
	
	private void setBatteryStatus(int battery){
		if(battery <= 10){
			mIvBattery.setImageResource(R.drawable.leftmenu_battary_10);
		}else if(battery > 10 && battery <= 20){
			mIvBattery.setImageResource(R.drawable.leftmenu_battary_20);
		}else if(battery > 20 && battery <= 30){
			mIvBattery.setImageResource(R.drawable.leftmenu_battary_30);
		}else if(battery > 30 && battery <= 40){
			mIvBattery.setImageResource(R.drawable.leftmenu_battary_40);
		}else if(battery > 40 && battery <= 50){
			mIvBattery.setImageResource(R.drawable.leftmenu_battary_50);
		}else if(battery > 50 && battery <= 60){
			mIvBattery.setImageResource(R.drawable.leftmenu_battary_60);
		}else if(battery > 60 && battery <= 70){
			mIvBattery.setImageResource(R.drawable.leftmenu_battary_70);
		}else if(battery > 70 && battery <= 80){
			mIvBattery.setImageResource(R.drawable.leftmenu_battary_80);
		}else if(battery > 80 && battery <= 90){
			mIvBattery.setImageResource(R.drawable.leftmenu_battary_90);
		}else if(battery > 90 && battery <= 100){
			mIvBattery.setImageResource(R.drawable.leftmenu_battary_full);
		}
	}
	
	@Override
	public void searchLoaUsrid(LocationComponment componment) {
		mLocation = componment;
		mCustomProgress.dismiss();
		if (aMap == null) {
			aMap = mapView.getMap();
			mTvAddress.setText(componment.getData().get(0).getAddress());
			mTvBattery.setText(Integer.valueOf(componment.getData().get(0)
					.getBattery())
					+ "%");
			setBatteryStatus(Integer.valueOf(componment.getData().get(0)
					.getBattery()));
			initMarkPhoto(mCurrentSerialNumber.getFpicture(), mIvMark);
			mUiSettings = aMap.getUiSettings();
			mUiSettings.setZoomControlsEnabled(false);
			
			WebMovementManager.getInstance(mContext).searchElectFence(mContext,mCurrentSerialNumber.getSerialnumber());
			
			WebLbsManager.getInstance(mContext).searchLbs(mContext, mCurrentSerialNumber.getSerialnumber());
		}
	}

	private SerialNumber mCurrentSerialNumber;

	@Override
	public void onSerialNumber(SerialNumber number) {
		if (mAccountPopWindows != null) {
			mAccountPopWindows.dismiss();
		}
		mCurrentSerialNumber = number;
		initMarkPhoto(mCurrentSerialNumber.getFpicture(), mIvMark);

		WebMovementManager.getInstance(mContext).searchElectFence(mContext,
				mCurrentSerialNumber.getSerialnumber());
	}

	@Override
	public void dismiss() {
		if (popupWindow != null) {
			popupWindow.dismiss();
		}
	}

	@Override
	public void destoryActivity() {
		finish();
	}

	@Override
	public void getSerialNum(SerialNumberComponment bean) {

	}

	@Override
	public void userUpdate(ResultBean bean) {

	}

	@Override
	public void actionSerialView(NumUsrRelation bean) {

	}

	@Override
	public void uupdateSerialView(ResultBean bean) {

	}

	@Override
	public void registerSuccess(ResultBean bean) {

	}

	@Override
	public void searchElectFence(MoveComponment bean) {

		List<MoveBean> beanList = bean.getData();
		if (beanList == null) {
			return;
		}

		for (int i = 0; i < beanList.size(); i++) {
			MoveBean circleBean = beanList.get(i);
			String locationbd = circleBean.getLocationbd();
			String[] locationLat = locationbd.split(",");
			LatLng latlng = new LatLng(Double.parseDouble(locationLat[1]),
					Double.parseDouble(locationLat[0]));// 北京市经纬度
			aMap.addCircle(new CircleOptions().center(latlng)
					.radius(circleBean.getScope())
					.strokeColor(Color.argb(255, 19, 167, 72))
					.fillColor(Color.argb(50, 203, 240, 143)).strokeWidth(1));
		}

	}

	@Override
	public void searchMovement(TrackBeanComponment bean) {

	}

	@Override
	public void addElectFence(ResultBean bean) {

	}

	@Override
	public void modifyElectFence(ResultBean bean) {
		
	}

	@Override
	public void deleteElectFence(ResultBean bean) {

	}

	@Override
	public void uploadFile(UserInfoBean bean) {

	}

	@Override
	public void deviceInfoPhoto(DeviceInfoBean bean) {

	}

	@Override
	public void pushAndroid(pushBean bean) {
		
	}

	@Override
	public void searchListen(ListenerBean bean) {
		if(bean.getListenStatus() != null && bean.getListenStatus().equals("2")){
			WebListenerManager.getInstance(mContext).sendListener(mContext, mCurrentSerialNumber.getSerialnumber());
		}else{
			WindowsToast.makeText(mContext, bean.getInfo()).show();
		}
		
	}

	@Override
	public void searchLbs(LbsBean lbsBean) {
		if(lbsBean.getGps().equals("1")){
			mTvLocationMode.setText(mContext.getString(R.string.gps_location));
		}else{
			mTvLocationMode.setText(mContext.getString(R.string.jizhan_location));
		}
		
	}

	@Override
	public void setLbs(LbsBean lbsBean) {
		
	}

	@Override
	public void onMapClick(LatLng arg0) {
		if (mLLPopMain.getVisibility() == 8) {
			mLLPopMain.setVisibility(View.VISIBLE);
			mIvFresh.setVisibility(View.GONE);
			mIvLocation.setVisibility(View.GONE);
		} else {
			mLLPopMain.setVisibility(View.GONE);
			mIvFresh.setVisibility(View.VISIBLE);
			mIvLocation.setVisibility(View.VISIBLE);
		}

		
	}

}
