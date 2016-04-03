package com.wear.testproject.ui;

import com.wear.testproject.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PwdFindActivity extends BaseActivity implements OnClickListener {

	private TextView mTvLeft;

	private ImageView mIvLeft;

	private ImageView mIvRight;

	private TextView mTvMainInfo;
	
	private TextView mTvRight ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pwd_find);
		initView();
	}

	private void initView() {
		mTvRight =(TextView)findViewById(R.id.tv_right);
		mIvRight = (ImageView) findViewById(R.id.iv_right);
		mIvLeft = (ImageView) findViewById(R.id.iv_back);
		mTvLeft = (TextView) findViewById(R.id.tv_left);
		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.found_pwd));
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvRight.setText(mContext.getString(R.string.next_step));
		mTvLeft.setOnClickListener(this);
		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
		mTvRight.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_left:
			finish();
			break;
		case R.id.tv_right:
			Intent intent = new Intent(mContext,PwdModifyActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
