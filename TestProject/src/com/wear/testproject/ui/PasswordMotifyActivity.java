package com.wear.testproject.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.wear.testproject.R;

public class PasswordMotifyActivity extends BaseActivity {
	
	private EditText mEtOrgPwd ;
	
	private EditText mEtNewPwd ;
	
	private EditText mEtConfirePwd ;
	
	private ImageView mIvBack ;
	
	private ImageView mIvNext ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pwd_motify);
		initView() ;
	}
	
	private void initView(){
		mEtOrgPwd  =(EditText)findViewById(R.id.et_org_pwd);
		mEtNewPwd = (EditText)findViewById(R.id.et_new_pwd);
		mEtConfirePwd = (EditText)findViewById(R.id.et_confire_pwd);
	}
}
