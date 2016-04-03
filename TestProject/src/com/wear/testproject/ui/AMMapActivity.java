package com.wear.testproject.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.InfoWindowAdapter;
import com.amap.api.maps2d.AMap.OnInfoWindowClickListener;
import com.amap.api.maps2d.AMap.OnMapLoadedListener;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.AMap.OnMarkerDragListener;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.Marker;
import com.wear.testproject.R;

public class AMMapActivity extends BaseActivity implements OnMarkerClickListener,
									OnInfoWindowClickListener, OnMarkerDragListener, OnMapLoadedListener,
									OnClickListener, InfoWindowAdapter {
	
	private MapView mapView;
	
	private AMap aMap ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState); 
	}
	
	private void initView(){
		
	}
	
	private void initAMMap(){
		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
		}
	}
	
	private void setUpMap() {
		aMap.setOnMarkerDragListener(this);// 设置marker可拖拽事件监听器
		aMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器
		aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
		aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
		aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式
		addMarkersToMap();// 往地图上添加marker
	}
	
	private void addMarkersToMap() {
		
	}
	
	@Override
	public View getInfoContents(Marker arg0) {
		return null;
	}

	@Override
	public View getInfoWindow(Marker arg0) {
		return null;
	}

	@Override
	public void onClick(View v) {
		
	}

	@Override
	public void onMapLoaded() {
		
	}

	@Override
	public void onMarkerDrag(Marker arg0) {
		
	}

	@Override
	public void onMarkerDragEnd(Marker arg0) {
		
	}

	@Override
	public void onMarkerDragStart(Marker arg0) {
		
	}

	@Override
	public void onInfoWindowClick(Marker arg0) {
		
	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		return false;
	}

}
