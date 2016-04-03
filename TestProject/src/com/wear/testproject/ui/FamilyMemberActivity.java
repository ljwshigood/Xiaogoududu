package com.wear.testproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.adapter.FamilyAdapter;

public class FamilyMemberActivity extends BaseActivity implements OnClickListener{
	
	private FamilyAdapter mFamilyAdapter ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_family_member);
		initView();
		initData();
	}
	
	private ListView mLvFamily ;
	
	private TextView mTvLeft ; 
	
	private ImageView mIvLeft ;
	
	private ImageView mIvRight ;
	
	private TextView mTvMainInfo ; 
	
	private TextView mTvRight ;
	
	private void initData(){
		mFamilyAdapter = new FamilyAdapter(mContext);
		mLvFamily.setAdapter(mFamilyAdapter);
	}

	private void initView(){
		mTvRight = (TextView)findViewById(R.id.tv_right);
		mIvRight = (ImageView)findViewById(R.id.iv_right);
		mIvLeft = (ImageView)findViewById(R.id.iv_back);
		mTvLeft = (TextView)findViewById(R.id.tv_left);
		mLvFamily = (ListView)findViewById(R.id.lv_family);
		mTvMainInfo = (TextView)findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.family_member));
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvRight.setText(mContext.getString(R.string.add));
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
		case R.id.tv_right:
			Intent intent = new Intent(mContext,AddFamilyActivity.class);
			startActivity(intent);
			break ;
		default:
			break;
		}
	}

}
