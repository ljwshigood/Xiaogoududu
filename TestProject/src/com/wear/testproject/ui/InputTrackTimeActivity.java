package com.wear.testproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.bean.MoveBean;
import com.wear.testproject.bean.MoveComponment;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.TrackBeanComponment;
import com.wear.testproject.impl.IElectFenceListener;
import com.wear.testproject.utils.DateUtils;
import com.wear.testproject.webmanager.WebMovementManager;
import com.wear.testproject.widget.CommonDialog;

public class InputTrackTimeActivity extends BaseActivity implements OnClickListener,IElectFenceListener{
	
	private TextView mEtStartTime;

	private TextView mEtEndTime;

	private LinearLayout mLLOK;

	private TextView mTvMainInfo;

	private ImageView mIvBack;

	private ImageView mIvRight ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_track_time);
		getIntentData();
		initView();
		WebMovementManager.getInstance(mContext).setmIElectFenceListener(this);
		initData();
	}
	
	private void initData(){
		mEtStartTime.setText(DateUtils.getFormatDate1(System.currentTimeMillis())+" 00:00:00");
		mEtEndTime.setText(DateUtils.getFormatDate(System.currentTimeMillis()));
	}
	
	private SerialNumber mSerialNumber ;
	
	private void getIntentData() {
		Intent intent = getIntent();
		mSerialNumber = (SerialNumber) intent.getSerializableExtra("serialNumber");
	}
	
	private TextView mTvLeft ;
		
	private void initView() {
		mIvRight = (ImageView)findViewById(R.id.iv_right);
		mIvBack = (ImageView) findViewById(R.id.iv_back);
		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mEtStartTime = (TextView) findViewById(R.id.et_start_time);
		mEtEndTime = (TextView) findViewById(R.id.et_end_time);
		mLLOK = (LinearLayout) findViewById(R.id.ll_ok);
		mTvMainInfo.setText(mContext.getString(R.string.auto_guiji_query));
		mIvBack.setImageResource(R.drawable.ic_back);
		mTvLeft = (TextView)findViewById(R.id.tv_left);
		mTvLeft.setVisibility(View.VISIBLE);
		mTvLeft.setOnClickListener(this);
		mTvLeft.setText(mContext.getString(R.string.back));
		mIvBack.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
		mLLOK.setOnClickListener(this);
		mIvBack.setOnClickListener(this);
		mEtStartTime.setOnClickListener(this);
		mEtEndTime.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_left:
			finish();
			break;
		case R.id.ll_ok:
			WebMovementManager.getInstance(mContext).searchMovment(mContext, mSerialNumber.getSerialnumber(), mEtStartTime.getText().toString().trim(), mEtEndTime.getText().toString().trim());
			break;
		case R.id.et_start_time:
			CommonDialog.getInstance(mContext).showDialog(InputTrackTimeActivity.this, 0 ,null, 1,new Handler() {

				public void handleMessage(android.os.Message msg) {
					if(msg.what == 1){
						String time = (String)msg.obj;
						mEtStartTime.setText(time);
					}
				};
			});
			break ;
		case R.id.et_end_time:
			CommonDialog.getInstance(mContext).showDialog(InputTrackTimeActivity.this, 0 ,null, 1,new Handler() {

				public void handleMessage(android.os.Message msg) {
					if(msg.what == 1){
						String time = (String)msg.obj;
						mEtEndTime.setText(time);
					}
				};
			});
			break ;
		default:
			break;
		}
	}


	@Override
	public void searchElectFence(MoveComponment bean) {
		
	}


	@Override
	public void searchMovement(TrackBeanComponment bean) {
		Intent intent = new Intent(mContext,SearchMovementActivity.class);
		intent.putExtra("trackBeanComponment", bean);
		startActivity(intent);
	}


	@Override
	public void addElectFence(ResultBean bean) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void modifyElectFence(ResultBean bean) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteElectFence(ResultBean bean) {
		// TODO Auto-generated method stub
		
	}
}
