/**
- * 项目名称：PublcNumber
 * 文件名：CaptureActivity.java 
 * 2015-2-24-上午11:52:03
 * 2015 万家恒通公司-版权所有
 * @version 1.0.0
 */
package com.wear.testproject.ui;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.adapter.MediaInfo;
import com.wear.testproject.adapter.PhotoSelectAdapter;
import com.wear.testproject.adapter.PhotoSelectAdapter.IGetPhoto;
import com.wear.testproject.impl.IOnItemClickListener;
import com.wear.testproject.utils.Constants;
import com.wear.testproject.utils.Logger;
import com.wear.testproject.utils.MediaUtils;
import com.wear.testproject.utils.PictureUtil;

/**
 * 
 * @description:
 *
 * author : liujw
 * modify : 
 * 2015-2-24 上午11:52:03
 *
 * 
 */
public class GalleryActivity extends BaseActivity implements OnClickListener,IOnItemClickListener,IGetPhoto{

	private GridView mGvPhoto ;
	
	private PhotoSelectAdapter mReleasePictureAdapter;
	
	private ArrayList<MediaInfo> mPictureInfoList = new ArrayList<MediaInfo>();

	private LinearLayout mLLBack ;
	
	private TextView mTvMainInfo;

	private TextView mTvLeft;

	private ImageView mIvLeft;

	private ImageView mIvRight;

	private TextView mTvRight;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capture);
		getIntentData();
		initView();
		initData();
	}
	
	private void initView(){
		mLLBack = (LinearLayout)findViewById(R.id.ll_back);
		mTvRight = (TextView)findViewById(R.id.tv_right);
		mLLBack.setOnClickListener(this);
		mGvPhoto = (GridView)findViewById(R.id.gridView);
		mIvRight = (ImageView) findViewById(R.id.iv_right);
		mIvLeft = (ImageView) findViewById(R.id.iv_back);
		mTvLeft = (TextView) findViewById(R.id.tv_left);
		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvLeft.setOnClickListener(this);
		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
		mTvMainInfo.setText(mContext.getString(R.string.select_photo));
		mTvRight.setText(mContext.getString(R.string.save));
		mTvRight.setVisibility(View.GONE);
		mLLBack.setOnClickListener(this);
	}
	
	private int mFlag;
	
	private void getIntentData(){
		Intent intent = getIntent();
		mFlag = intent.getIntExtra("flag", -1);
	}
	
	private void initData(){
		MediaUtils.getImageFolderList(mContext, mPictureInfoList);
		mReleasePictureAdapter = new PhotoSelectAdapter(mContext,mPictureInfoList, mOptionsStyle,this,mFlag);
		mGvPhoto.setAdapter(mReleasePictureAdapter);
		mReleasePictureAdapter.setmIGetPhoto(this);
		mGvPhoto.setOnItemClickListener(mReleasePictureAdapter);
	}
	
	
	@Override
	public void onClick(View v) {
		Intent intent ;
		switch (v.getId()) {
		case R.id.tv_right:
			break ;
		case R.id.tv_left:
			setResult(Constants.UNDO);
			finish();
			break ;
		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Constants.UNDO){
			return ;
		}
		
		if(requestCode == Constants.MODIFY_PHOTO){
			setResult(resultCode, data);
			finish();
		}
	}
	
	@Override
	public void onItemCheckListener(List<MediaInfo> list) {
		
	}
	
	private static final String PATH = Environment.getExternalStorageDirectory() + "/DCIM";
	
	public String name;
	

	@Override
	public void getPhoto() {
		
	}
	
	
}
