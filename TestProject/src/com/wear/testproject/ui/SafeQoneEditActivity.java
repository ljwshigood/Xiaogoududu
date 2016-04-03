package com.wear.testproject.ui;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.OnMapClickListener;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.Circle;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.wear.testproject.R;
import com.wear.testproject.bean.MoveBean;
import com.wear.testproject.bean.MoveComponment;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.TrackBeanComponment;
import com.wear.testproject.impl.IElectFenceListener;
import com.wear.testproject.webmanager.WebMovementManager;
import com.wear.testproject.widget.WindowsToast;

public class SafeQoneEditActivity extends BaseActivity implements OnClickListener, AMapLocationListener,OnMapClickListener,OnGeocodeSearchListener,IElectFenceListener{

	private AMap aMap;

	private MapView mapView;

	private TextView mTvMainInfo;

	private TextView mTvLeft;

	private ImageView mIvLeft;

	private ImageView mIvRight;

	private TextView mTvRight;
	
	private SeekBar mSeekBar ;

	private LinearLayout mLLQuery ;
	
	private EditText mEtAddress ;
	
	private EditText mEtName ;
	
	private LinearLayout mLLDeleteQone ;
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_safe_qone_edit);
		mapView = (MapView) findViewById(R.id.map);

		mapView.onCreate(bundle);// 此方法必须重写
		
		aMapLocManager = LocationManagerProxy.getInstance(this);
		aMapLocManager.requestLocationData(LocationProviderProxy.AMapNetwork,
				2000, 10, this);
		initView();
		getIntentData();
		convertGeoceoder();
	}

	private void initView() {
		if (aMap == null) {
			aMap = mapView.getMap();
		}
		mLLDeleteQone = (LinearLayout)findViewById(R.id.ll_delete);
		mLLDeleteQone.setOnClickListener(this);
		mEtName = (EditText)findViewById(R.id.et_set_name);
		mLLQuery = (LinearLayout)findViewById(R.id.ll_ok);
		mEtAddress = (EditText)findViewById(R.id.et_address);
		mTvRight = (TextView) findViewById(R.id.tv_right);
		mTvRight.setOnClickListener(this);
		mIvRight = (ImageView) findViewById(R.id.iv_right);
		mIvLeft = (ImageView) findViewById(R.id.iv_back);
		mTvLeft = (TextView) findViewById(R.id.tv_left);
		mSeekBar = (SeekBar)findViewById(R.id.pb_circle);
		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvLeft.setOnClickListener(this);
		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
		mLLQuery.setOnClickListener(this);
		mTvRight.setText(mContext.getString(R.string.save));
		
		aMap.setOnMapClickListener(SafeQoneEditActivity.this);
		mSeekBar.setMax(1400);
		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
				mCurrentRadius = 100+seekBar.getProgress();
				circle.setRadius(mCurrentRadius);
				aMap.invalidate();
			}
		});
	}

	private Marker marker ;
	
	private Circle circle;
	
	public void drawMarkers(double latitude,double longitude,int radius) {
		if(marker != null){
			marker.remove();
		}
		if(circle != null){
			circle.remove();
		}
		marker = aMap.addMarker(new MarkerOptions()
				.position(
						new LatLng(latitude,longitude))
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ic_location_mark))
				.draggable(true));
		marker.showInfoWindow();
		
		circle = aMap.addCircle(new CircleOptions().center(new LatLng(latitude, longitude))
				.radius(radius).strokeColor(Color.argb(80, 255, 1, 1))
				.fillColor(Color.argb(10, 255, 0, 0)).strokeWidth(3));
	}
	
	double lation;

	double Longitude;

	private UiSettings mUiSettings;
	
	private int mAction ;
	
	private SerialNumber mSerialNumber ;
	
	private MoveBean mMove ;

	private void getIntentData() {
		Intent intent = getIntent();
		mAction = intent.getIntExtra("action", -1);
		mSerialNumber  =  (SerialNumber) intent.getSerializableExtra("serialNumber");
		mMove = (MoveBean)intent.getSerializableExtra("move");
		if(mAction == 0){
			mLLDeleteQone.setVisibility(View.GONE);
		}else if(mAction == 1){
			mEtName.setText(mMove.getName());
			mLLDeleteQone.setVisibility(View.VISIBLE);
		}
	}
	
	private void initData(){
		
	}

/*	private void setUpMap(LocationComponment location) {
		aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lation,
				Longitude), 12));
		mUiSettings = aMap.getUiSettings();
		mUiSettings.setZoomControlsEnabled(false);
	}*/

	@Override
	public void onLocationChanged(Location location) {

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	private LocationManagerProxy aMapLocManager = null;

	/**
	 * 销毁定位
	 */
	private void stopLocation() {
		if (aMapLocManager != null) {
			aMapLocManager.removeUpdates(this);
			aMapLocManager.destroy();
		}
		aMapLocManager = null;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
		WebMovementManager.getInstance(mContext).setmIElectFenceListener(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
		stopLocation();
		WebMovementManager.getInstance(mContext).setmIElectFenceListener(this);
	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onProviderDisabled(String provider) {

	}

	@Override
	public void onLocationChanged(AMapLocation location) {
		if (location != null) {
			Double geoLat = location.getLatitude();
			Double geoLng = location.getLongitude();
			if(mMove == null){
				mCurrentLatLng = new LatLonPoint(geoLat, geoLng);
				mLocationCity = location.getCityCode();	
			}else{
				String  gd = mMove.getLocationgd() ;
				String[] gdArray = gd.split(",");
				mCurrentLatLng = new LatLonPoint(Double.valueOf(gdArray[1]),Double.valueOf(gdArray[0]));
				mLocationCity = location.getCityCode();
				mCurrentRadius = mMove.getScope() ;
			}
			
			showLocationPlace(mCurrentLatLng.getLatitude(), mCurrentLatLng.getLongitude());
			drawMarkers(geoLat,geoLng,mCurrentRadius);
			stopLocation();
		}
	}

	private void showLocationPlace(double lat, double longt) {
		aMap.moveCamera(CameraUpdateFactory
				.newCameraPosition(new CameraPosition(new LatLng(lat, longt),
						16, 30, 0)));
		aMap.moveCamera(CameraUpdateFactory.zoomIn());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_left:
			finish();
			break;
		case R.id.ll_ok :
			getLatlon(mEtAddress.getText().toString().trim());
			break;
		case R.id.tv_right:
			if(mAction == 0){
				WebMovementManager.getInstance(mContext).addElectFence(mContext, mSerialNumber.getSerialnumber(), 
						mEtName.getText().toString().trim(),
						mCurrentLatLng.getLongitude()+","+mCurrentLatLng.getLatitude(), String.valueOf(mCurrentRadius), 0);
			}else{
				WebMovementManager.getInstance(mContext).modifyElectFence(mContext,String.valueOf(mMove.getId()), mSerialNumber.getSerialnumber(),mEtName.getText().toString().trim(),
						mCurrentLatLng.getLongitude()+","+mCurrentLatLng.getLatitude(), String.valueOf(mCurrentRadius), 0);
			}
			break ;
		case R.id.ll_delete:
			WebMovementManager.getInstance(mContext).deleteElectFence(mContext, mSerialNumber.getSerialnumber(), String.valueOf(mMove.getId()));
			break ;
		default:
			break;
		}
	}
	
	
	private GeocodeSearch mGgeocoderSearch;
	
	private void convertGeoceoder(){
		mGgeocoderSearch = new GeocodeSearch(this);
		mGgeocoderSearch.setOnGeocodeSearchListener(this);
	}
	
	private String mLocationCity ;
	
	public void getLatlon(final String name) {
		GeocodeQuery query = new GeocodeQuery(name, mLocationCity);// 第一个参数表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode，
		mGgeocoderSearch.getFromLocationNameAsyn(query);// 设置同步地理编码请求
	}
	
	private int mCurrentRadius = 100 ;
	
	
	@Override
	public void onMapClick(LatLng latLng) {
		mCurrentLatLng = new LatLonPoint(latLng.latitude, latLng.longitude);
		drawMarkers(latLng.latitude, latLng.longitude,mCurrentRadius);
	}

	private LatLonPoint mCurrentLatLng ;
	
	@Override
	public void onGeocodeSearched(GeocodeResult result, int rCode) {
		
		if (rCode == 0) {
			if (result != null && result.getGeocodeAddressList() != null && result.getGeocodeAddressList().size() > 0) {
				GeocodeAddress address = result.getGeocodeAddressList().get(0);
				mCurrentLatLng = address.getLatLonPoint();
				double lat = address.getLatLonPoint().getLatitude() ;
				double lng = address.getLatLonPoint().getLongitude();
				showLocationPlace(lat, lng);
				drawMarkers(lat,lng,mCurrentRadius); 
			} else {
				WindowsToast.makeText(mContext, "fail").show();
			}
		}else {
			WindowsToast.makeText(mContext, "fail").show();
		} 
		
	}

	@Override
	public void onRegeocodeSearched(RegeocodeResult arg0, int arg1) {
		
		
	}

	@Override
	public void searchElectFence(MoveComponment bean) {
		
	}

	@Override
	public void searchMovement(TrackBeanComponment bean) {
		
	}

	@Override
	public void addElectFence(ResultBean bean) {
		WindowsToast.makeText(mContext, bean.getInfo()).show();
	}

	@Override
	public void modifyElectFence(ResultBean bean) {
		WindowsToast.makeText(mContext, bean.getInfo()).show();
	}

	@Override
	public void deleteElectFence(ResultBean bean) {
		setResult(com.wear.testproject.utils.Constants.REFRESH_QONE);
		WindowsToast.makeText(mContext, bean.getInfo()).show();
	}
}
