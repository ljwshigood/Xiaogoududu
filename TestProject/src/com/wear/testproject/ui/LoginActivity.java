package com.wear.testproject.ui;

import java.util.LinkedHashSet;
import java.util.Set;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import com.wear.testproject.R;
import com.wear.testproject.bean.DeviceInfoBean;
import com.wear.testproject.bean.LocationComponment;
import com.wear.testproject.bean.NumUsrRelation;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.SerialNumberComponment;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.bean.pushBean;
import com.wear.testproject.impl.IAccountManager;
import com.wear.testproject.sharepefrence.SharePerfenceUtil;
import com.wear.testproject.webmanager.WebAccountManager;

public class LoginActivity extends BaseActivity implements OnClickListener,IAccountManager{

	private EditText mEtUserName;

	private EditText mEtPwd;

	private LinearLayout mLLLogin;

	private TextView mTvMainInfo;

	private ImageView mIvBack;

	private ImageView mIvRight ;
	
	private TextView mTvForgetPwd ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		initData();
	}
	
	private void initData(){
		String username = (String) SharePerfenceUtil.getParam(mContext, "userName", "");
		String pwd = (String)SharePerfenceUtil.getParam(mContext, "pwd", "");
		mEtUserName.setText(username);
		mEtPwd.setText(pwd);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		WebAccountManager.getInstance(mContext).setmIAcountManager(this);
	}
	
	
	@Override
	protected void onPause() {
		super.onPause();
		WebAccountManager.getInstance(mContext).setmIAcountManager(null);
	}
	
	private void initView() {
		mTvForgetPwd = (TextView)findViewById(R.id.tv_pwd_find);
		mIvRight = (ImageView)findViewById(R.id.iv_right);
		mIvBack = (ImageView) findViewById(R.id.iv_back);
		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mEtUserName = (EditText) findViewById(R.id.et_user_name);
		mEtPwd = (EditText) findViewById(R.id.et_pwd);
		mLLLogin = (LinearLayout) findViewById(R.id.ll_login_click);
		mTvMainInfo.setText(mContext.getString(R.string.user_login));
		mIvBack.setImageResource(R.drawable.ic_back);
		mIvRight.setVisibility(View.GONE);
		mLLLogin.setOnClickListener(this);
		mIvBack.setOnClickListener(this);
		mTvForgetPwd.setOnClickListener(this);
	}
	
	private String mPwd ;
	
	private String mName ;
	
	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.tv_pwd_find:
			intent = new Intent(mContext, PwdFindActivity.class);
			startActivity(intent);
			break ;
		case R.id.ll_login_click:
			mPwd = mEtPwd.getText().toString();
			mName = mEtUserName.getText().toString();
			WebAccountManager.getInstance(mContext).login(mContext, mPwd, mName);
			break;
		case R.id.iv_back:
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	public void loginSuccess(UserInfoBean bean) {
		SharePerfenceUtil.setParam(mContext, "userName", mName);
		SharePerfenceUtil.setParam(mContext, "pwd", mPwd);
		Intent intent = new Intent(mContext, MainActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("userInfo", bean);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void searchLoaUsrid(LocationComponment componment) {
		
	}

	@Override
	public void getSerialNum(SerialNumberComponment bean) {
		
	}

	@Override
	public void userUpdate(ResultBean bean) {
		
	}

	@Override
	public void actionSerialView(NumUsrRelation bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uupdateSerialView(ResultBean bean) {
		
	}

	@Override
	public void registerSuccess(ResultBean bean) {
		
	}

	@Override
	public void uploadFile(UserInfoBean bean) {
		
	}

	@Override
	public void deviceInfoPhoto(DeviceInfoBean bean) {
		
	}
	
	private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
            case MSG_SET_ALIAS:
                JPushInterface.setAliasAndTags(getApplicationContext(), (String) msg.obj, null, mAliasCallback);
                break;
            case MSG_SET_TAGS:
                JPushInterface.setAliasAndTags(getApplicationContext(), null, (Set<String>) msg.obj, mTagsCallback);
                break;
                
            default:
            	
            }
        }
	};
	
	private final TagAliasCallback mTagsCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
            case 0:
                logs = "Set tag and alias success";
                break;
                
            case 6002:
                logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
               // if (ExampleUtil.isConnected(getApplicationContext())) {
                	mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_TAGS, tags), 1000 * 60);
             //   } else {
                //	Log.i(TAG, "No network");
               // }
                break;
            
            default:
                logs = "Failed with errorCode = " + code;
               // Log.e(TAG, logs);
            }
            
        }
        
    };
	
	
	private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
            case 0:
                logs = "Set tag and alias success";
                break;
            case 6002:
                logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
               // if (ExampleUtil.isConnected(getApplicationContext())) {
                	mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                //}
                break;
            
            default:
            	
            }
            
        }
	    
	};
    
	private static final int MSG_SET_ALIAS = 1001;
	
	private static final int MSG_SET_TAGS = 1002;

	private void setAlias(String alias){
		//调用JPush API设置Alias
		mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
	}
	
	
	private void setTag(String tag){
		String[] sArray = tag.split(",");
		Set<String> tagSet = new LinkedHashSet<String>();
		for (String sTagItme : sArray) {
			tagSet.add(sTagItme);
		}
		//调用JPush API设置Tag
		mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_TAGS, tagSet));

	} 
	
	
	@Override
	public void pushAndroid(pushBean bean) {
		if(bean.getState() == 1){
			setTag(bean.getTag());	
			setAlias(bean.getAlias());
		}
		
	}

}
