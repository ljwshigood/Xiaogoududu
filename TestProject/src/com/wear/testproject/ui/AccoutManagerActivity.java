package com.wear.testproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.wear.testproject.R;

public class AccoutManagerActivity extends BaseActivity implements OnClickListener{

	private LinearLayout mLLLogin;

	private LinearLayout mLLReg ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accout_manager);
		initView();
	}

	private void initView(){
		mLLLogin = (LinearLayout)findViewById(R.id.ll_login);
		mLLReg = (LinearLayout)findViewById(R.id.ll_reg);
		mLLLogin.setOnClickListener(this);
		mLLReg.setOnClickListener(this);
	}
	
	private Intent mIntent ;
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_login:
			mIntent = new Intent(mContext,RegisterActivity.class);
			startActivity(mIntent);
			break;
		case R.id.ll_reg:
			mIntent = new Intent(mContext,LoginActivity.class);
			startActivity(mIntent);
			
			break ;
		default:
			break;
		}
	}

}
