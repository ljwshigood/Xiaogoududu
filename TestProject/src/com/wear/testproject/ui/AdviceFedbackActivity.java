package com.wear.testproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.webmanager.WebFeedBackManager;

public class AdviceFedbackActivity extends BaseActivity implements OnClickListener{
	
	//43 jiekou
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advice_feckback);
		getIntentData();
		initView();
	}
	
	private UserInfoBean mUserInfoBean ;

	private void getIntentData(){
		Intent intent = getIntent();
		mUserInfoBean = (UserInfoBean)intent.getSerializableExtra("userInfo");
	}
	
	
	private TextView mTvLeft ; 
	
	private ImageView mIvLeft ;
	
	private ImageView mIvRight ;
	
	private TextView mTvMainInfo ; 
	
	private LinearLayout mLLSend ;
	
	private EditText mEtContent ;
	
	private void initView(){
		mEtContent = (EditText)findViewById(R.id.et_content);
		mLLSend = (LinearLayout)findViewById(R.id.ll_send);
		mIvRight = (ImageView)findViewById(R.id.iv_right);
		mIvLeft = (ImageView)findViewById(R.id.iv_back);
		mTvLeft = (TextView)findViewById(R.id.tv_left);
		mTvMainInfo = (TextView)findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.face_back));
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvLeft.setOnClickListener(this);
		mLLSend.setOnClickListener(this);
		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_left:
			finish();
			break;
		case R.id.ll_send:
			WebFeedBackManager.getInstance(mContext).postFeedBack(1, mUserInfoBean.getData().getFuniqueid(),mEtContent.getText().toString().trim());
			break ;
		default:
			break;
		}
	}
	
}
