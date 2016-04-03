package com.wear.testproject.ui;

import com.wear.testproject.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class LeaveTipActivity extends BaseActivity implements OnClickListener{
	

	private TextView mTvLeft;

	private ImageView mIvLeft;

	private ImageView mIvRight;
	
	private TextView mTvMainInfo ;

	private void initView() {
		mIvRight = (ImageView) findViewById(R.id.iv_right);
		mIvLeft = (ImageView) findViewById(R.id.iv_back);
		mTvLeft = (TextView) findViewById(R.id.tv_left);
		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.leave));
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvLeft.setOnClickListener(this);
		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leave_tip);
		initView();
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
