/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing.client.android.encode;

import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.google.zxing.client.android.FinishListener;
import com.google.zxing.client.android.Intents;
import com.wear.testproject.R;

/**
 * This class encodes data from an Intent into a QR code, and then displays it
 * full screen so that another person can scan it with their device.
 * 
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class EncodeActivity extends Activity implements OnClickListener{

	private static final String TAG = EncodeActivity.class.getSimpleName();

	private static final int MAX_BARCODE_FILENAME_LENGTH = 24;
	private static final Pattern NOT_ALPHANUMERIC = Pattern
			.compile("[^A-Za-z0-9]");
	private static final String USE_VCARD_KEY = "USE_VCARD";

	private QRCodeEncoder qrCodeEncoder;

	private LinearLayout mLLBack;

	private ImageView mIvReleaseBlog;

	private TextView mTvTitle;
	
	private ImageView mIvLeft ;
	
	private TextView mTvLeft ;

	private void initView() {
		mTvLeft = (TextView)findViewById(R.id.tv_left);
		mIvLeft = (ImageView)findViewById(R.id.iv_back);
		mTvTitle = (TextView) findViewById(R.id.tv_main_title_info);
		mLLBack = (LinearLayout) findViewById(R.id.ll_back);
		mIvReleaseBlog = (ImageView) findViewById(R.id.iv_right);
		mIvReleaseBlog.setVisibility(View.GONE);
		mLLBack.setVisibility(View.VISIBLE);
		mTvLeft.setOnClickListener(this);
		mIvLeft.setVisibility(View.GONE);
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvTitle.setText(mContext.getString(R.string.qr_code));
		mTvLeft.setOnClickListener(this);
		mLLBack.setOnClickListener(this);
	}

	private Context mContext ;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		mContext = EncodeActivity.this;
		
		Intent intent = getIntent();
		if (intent == null) {
			finish();
		} else {
			String action = intent.getAction();
			if (Intents.Encode.ACTION.equals(action)
					|| Intent.ACTION_SEND.equals(action)) {
				setContentView(R.layout.encode);
			} else {
				finish();
			}
		}
		initView();
	}

	private static CharSequence makeBarcodeFileName(CharSequence contents) {
		String fileName = NOT_ALPHANUMERIC.matcher(contents).replaceAll("_");
		if (fileName.length() > MAX_BARCODE_FILENAME_LENGTH) {
			fileName = fileName.substring(0, MAX_BARCODE_FILENAME_LENGTH);
		}
		return fileName;
	}

	@Override
	protected void onResume() {
		super.onResume();
		// This assumes the view is full screen, which is a good assumption
		WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		Point displaySize = new Point();
		display.getSize(displaySize);
		int width = displaySize.x;
		int height = displaySize.y;
		int smallerDimension = width < height ? width : height;
		smallerDimension = smallerDimension * 7 / 8;

		Intent intent = getIntent();
		if (intent == null) {
			return;
		}

		try {
			boolean useVCard = intent.getBooleanExtra(USE_VCARD_KEY, false);
			qrCodeEncoder = new QRCodeEncoder(this, intent, smallerDimension,useVCard);
			Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
			if (bitmap == null) {
				Log.w(TAG, "Could not encode barcode");
				showErrorMessage(R.string.msg_encode_contents_failed);
				qrCodeEncoder = null;
				return;
			}

			ImageView view = (ImageView) findViewById(R.id.image_view);
			view.setImageBitmap(bitmap);

			/*
			 * if (intent.getBooleanExtra(Intents.Encode.SHOW_CONTENTS, true)) {
			 * contents.setText(qrCodeEncoder.getDisplayContents());
			 * setTitle(qrCodeEncoder.getTitle()); } else {
			 * contents.setText(""); setTitle(""); }
			 */
		} catch (WriterException e) {
			Log.w(TAG, "Could not encode barcode", e);
			showErrorMessage(R.string.msg_encode_contents_failed);
			qrCodeEncoder = null;
		}
	}

	private void showErrorMessage(int message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(message);
		builder.setPositiveButton(R.string.button_ok, new FinishListener(this));
		builder.setOnCancelListener(new FinishListener(this));
		builder.show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_left:
			finish();
			break ;
		case R.id.ll_back:
			finish();
			break;

		default:
			break;
		}
	}
}
