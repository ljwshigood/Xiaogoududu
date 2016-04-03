package com.wear.testproject.ui;

import com.wear.testproject.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class PwdModifyActivity extends BaseActivity implements OnClickListener {

	private TextView mTvLeft;

	private ImageView mIvLeft;

	private TextView mTvMainInfo;

	private ImageView mIvRight;

	private TextView mTvRight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pwd_motify);
		initView();
	}

	private void initView() {
		mIvRight = (ImageView) findViewById(R.id.iv_right);
		mTvRight =(TextView)findViewById(R.id.tv_right);
		
		mIvLeft = (ImageView) findViewById(R.id.iv_back);
		mTvLeft = (TextView) findViewById(R.id.tv_left);
		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.found_pwd));
		mTvLeft.setText(mContext.getString(R.string.back));
		
		mTvRight.setText(mContext.getString(R.string.complete));
		mTvRight.setOnClickListener(this);

		mTvLeft.setOnClickListener(this);
		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
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

}
