package com.wear.testproject.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.Polyline;
import com.amap.api.maps2d.model.PolylineOptions;
import com.wear.testproject.R;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.TrackBean;
import com.wear.testproject.bean.TrackBeanComponment;

public class SearchMovementActivity extends BaseActivity implements OnClickListener,OnSeekBarChangeListener{
	
	private TrackBeanComponment mTrackBeanComponment ;
	
	private AMap aMap;
	
	private MapView mapView;
	
	private TextView mTvMainInfo;

	private TextView mTvLeft;

	private ImageView mIvLeft;

	private ImageView mIvRight;

	private TextView mTvRight;
	
	private List<LatLng> latLngList = new ArrayList<LatLng>();
	
	private Handler timer = new Handler();
	
	private Runnable runnable;
	
	private Handler handler =new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			if(msg.what==1){
				int curProgress = mProgressBar.getProgress();
				if(curProgress != mProgressBar.getMax()){
					mProgressBar.setProgress(curProgress+1);
					timer.postDelayed(runnable, 2000);
				}else{
						
				}
				
			}
		};
	};
	
	private SeekBar mProgressBar ;
	
	private void initTrackRequest(){
		/*LatLng location1=new LatLng(43.828, 87.621);
		LatLng location2=new LatLng(60.123,140.987);
		LatLng location3=new LatLng(45.808,106.55);
		latLngList.add(location1);
		latLngList.add(location2);
		latLngList.add(location3);*/
		
		List<TrackBean> list = mTrackBeanComponment.getData();
		if(list == null){
			return ;
		}
		for(int i = 0;i < list.size();i++){
			TrackBean bean = list.get(i);
			LatLng location  =new LatLng(Double.parseDouble(bean.getLat()), Double.parseDouble(bean.getLon()));
			latLngList.add(location);
		}
	}
	
	private ImageView mIvProior ;
	
	private CheckBox mIvPlayer ;
	
	private ImageView mIvPre ;
	
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_search_movement);
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(bundle);
		getIntentData();
		initTrackRequest();
		initMap();
		initView();
		
		runnable = new Runnable() {
			
			@Override
			public void run() {
				handler.sendMessage(Message.obtain(handler, 1));
			}
		};

	}
	

	
	private void initView() {
		mIvPlayer = (CheckBox)findViewById(R.id.cb_play);
		mIvPre = (ImageView)findViewById(R.id.iv_pre);
		mIvProior = (ImageView)findViewById(R.id.iv_prior);
		mProgressBar = (SeekBar)findViewById(R.id.play_progress);
		mTvRight = (TextView) findViewById(R.id.tv_right);
		mIvRight = (ImageView) findViewById(R.id.iv_right);
		mIvLeft = (ImageView) findViewById(R.id.iv_back);
		mTvLeft = (TextView) findViewById(R.id.tv_left);
		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.auto_guiji_query));
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvLeft.setOnClickListener(this);
		mProgressBar.setOnSeekBarChangeListener(this);
		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
		mIvPlayer.setOnClickListener(this);
		mIvPre.setOnClickListener(this);
		mIvProior.setOnClickListener(this);
		mProgressBar.setMax(latLngList.size() - 1);
	}
	
	private UiSettings mUiSettings;
	
	private void initMap(){
		if(aMap == null){
			aMap = mapView.getMap();
		}
		mUiSettings = aMap.getUiSettings();
		mUiSettings.setZoomControlsEnabled(false);
	}
	
	private Polyline polyline;
	
	private void drawPloyLine(LatLng start,LatLng end){
		polyline = aMap.addPolyline((new PolylineOptions()).add(start,end).color(Color.RED));
		polyline.setWidth(3);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}
	
	private void getIntentData(){
		Intent intent = getIntent();
		mTrackBeanComponment = (TrackBeanComponment)intent.getSerializableExtra("trackBeanComponment");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_left:
			finish();
			break;
		case R.id.cb_play:
			if (mIvPlayer.isChecked()) {
				if (latLngList.size() > 0) {
					if (mProgressBar.getProgress() == mProgressBar.getMax()) {
						mProgressBar.setProgress(0);
					}
					timer.postDelayed(runnable, 1000);
				}
			} else {
				timer.removeCallbacks(runnable);
			}
			break ;
		default:
			break;
		}
	}

	private int mCurrentPosition = 0 ;
	

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		if(progress != 0){
			LatLng start = latLngList.get(mCurrentPosition) ;
			mCurrentPosition++ ;
			int mod =  mCurrentPosition % latLngList.size()  ;
			LatLng end = latLngList.get(mod) ;
			LatLngBounds.Builder b = new LatLngBounds.Builder();
			b.include(start);
			b.include(end);
			aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(b.build(),3));
			drawPloyLine(start,end);
		}		
	}



	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		
	}



	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		
	}

}
