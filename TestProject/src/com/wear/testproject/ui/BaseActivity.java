package com.wear.testproject.ui;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Window;
import cn.jpush.android.api.JPushInterface;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.wear.testproject.R;

public class BaseActivity extends Activity {

	public Context mContext;

	protected DisplayImageOptions mOptionsStyle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);

		mOptionsStyle = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();

		mContext = BaseActivity.this;
	}

	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		JPushInterface.onResume(this);
	}

}
