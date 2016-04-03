package com.wear.testproject.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.adapter.ContactTypeAdapter;
import com.wear.testproject.bean.ContactBean;

public class AddFamilyActivity extends BaseActivity implements OnClickListener{
	
	
	private TextView mTvLeft;

	private ImageView mIvLeft;

	private ImageView mIvRight;
	
	private TextView mTvMainInfo ;
	
	private ListView mLvContact ;

	private void initView() {
		mIvRight = (ImageView) findViewById(R.id.iv_right);
		mIvLeft = (ImageView) findViewById(R.id.iv_back);
		mTvLeft = (TextView) findViewById(R.id.tv_left);
		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.add_family));
		mTvLeft.setText(mContext.getString(R.string.back));
		mLvContact = (ListView)findViewById(R.id.lv_contact_type);
		mTvLeft.setOnClickListener(this);
		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
	}

	private ContactTypeAdapter mContactTypeAdapter ;
	
	private List<ContactBean> mList = new ArrayList<ContactBean>();
	
	private void initData(){
		Resources res =getResources();
		String[] contactType = res.getStringArray(R.array.contact_type);
		for(int i = 0 ;i < contactType.length ;i++){
			ContactBean contact = new ContactBean();
			contact.setInfo(contactType[i]);
			mList.add(contact);
		}
	
		mContactTypeAdapter = new ContactTypeAdapter(mContext,mList);
		mLvContact.setAdapter(mContactTypeAdapter);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_family);
		initView();
		initData();
		
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
